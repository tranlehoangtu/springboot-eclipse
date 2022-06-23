package com.javacode.controller.admin;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javacode.domain.Flight;
import com.javacode.model.FlightDTO;
import com.javacode.service.FlightService;

@Controller
@RequestMapping(value = "admin/flights")
public class FlightController {

	private FlightService flightService;

	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	@GetMapping(value = "add")
	public String add(Model model) {
		model.addAttribute("flight", new FlightDTO());
		return "admin/flights/addOrEdit";
	}

	@PostMapping(value = "saveOrUpdate")
	public String saveOrUpdate(Model model, @ModelAttribute("flight") FlightDTO flightDTO, BindingResult result)
			throws ParseException {
		if (result.hasErrors()) {
			return "forward:/admin/flights/add";
		}

		Flight entity = new Flight();
		BeanUtils.copyProperties(flightDTO, entity);

		model.addAttribute("message", "Flight is Saved");
		flightService.save(entity);

		return "forward:/admin/flights/";
	}

	@GetMapping(value = "edit/{flightNo}")
	public String edit(Model model, @PathVariable(name = "flightNo") String flightNo) {
		Optional<Flight> entity = flightService.findById(flightNo);

		if (entity.isPresent()) {
			FlightDTO flightDTO = new FlightDTO();
			BeanUtils.copyProperties(entity.get(), flightDTO);

			flightDTO.setIsEdit(true);

			model.addAttribute("message", "this is message");
			model.addAttribute("flight", flightDTO);

			return "/admin/flights/addOrEdit";
		}

		return "forward:/admin/flights";
	}

	@GetMapping(value = "delete/{flightNo}")
	public String delete(Model model, @PathVariable(name = "flightNo") String flightNo) {
		Optional<Flight> entityOpt = flightService.findById(flightNo);

		if (entityOpt.isPresent()) {
			model.addAttribute("message", "Flight " + flightNo + " has been Deleted!");
			flightService.delete(entityOpt.get());

			return "forward:/admin/flights";
		}

		model.addAttribute("message", "Can't delete cause Flight not found !");
		return "forward:/admin/flights";
	}

	@RequestMapping(value = "")
	public String list(Model model, @RequestParam(name = "sbName", required = false) String flightName,
			@RequestParam(name = "sbNo", required = false) String flightNo,
			@RequestParam(name = "page") Optional<Integer> page, @RequestParam(name = "size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("FlightNo"));
		Page<Flight> pageEntity = null;

		if (StringUtils.hasText(flightName) && StringUtils.hasText(flightNo)) {
			pageEntity = flightService.findByFlightNameContainingAndFlightNoContaining(flightName, flightNo, pageable);
			
			model.addAttribute("sbNo", flightNo);
			model.addAttribute("sbName", flightName);
		} else if (StringUtils.hasText(flightNo)) {
			pageEntity = flightService.findByFlightNoContaining(flightNo, pageable);
			model.addAttribute("sbNo", flightNo);
			
		} else if (StringUtils.hasText(flightName)) {
			pageEntity = flightService.findByFlightNameContaining(flightName, pageable);
			model.addAttribute("sbName", flightName);

		} else {
			pageEntity = flightService.findAll(pageable);
		}

		int totalPages = pageEntity.getTotalPages();

		if (totalPages > 0) {
			int start = Math.max(1, currentPage - 2);
			int end = Math.min(currentPage + 2, totalPages);

			if (totalPages > 5) {
				if (end == totalPages)
					start = end - 5;
				else if (start == 1)
					end = start + 5;
			}

			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		if (!pageEntity.hasContent()) {
			model.addAttribute("message", "No Flight Found!");
		}

		model.addAttribute("pageEntity", pageEntity);

		return "admin/flights/list";
	}

}
