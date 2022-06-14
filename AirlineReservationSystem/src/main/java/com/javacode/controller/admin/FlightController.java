package com.javacode.controller.admin;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		System.out.println(flightDTO.getFlightDate());
		System.out.println(entity.getFlightDate());

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
			
			model.addAttribute("flight", flightDTO);
			return "/admin/flights/addOrEdit";
		}

		return "forward:/admin/flights";
	}
	
	@GetMapping(value = "delete/{flightNo}")
	public String delete(Model model, @PathVariable(name = "flightNo") String flightNo) {
		Optional<Flight> entityOpt = flightService.findById(flightNo);
		
		if(entityOpt.isPresent()) {
			model.addAttribute("message", "Flight " + flightNo + " has been Deleted!");			
			flightService.delete(entityOpt.get());
			
			return "forward:/admin/flights";
		}
		
		model.addAttribute("message", "Can't delete cause Flight not found !");
		return "forward:/admin/flights";
	}

	@RequestMapping(value = "")
	public String list(Model model) {
		List<Flight> listEntity = flightService.findAll();
		
		List<FlightDTO> list = new ArrayList<FlightDTO>();
		
		for(Flight item : listEntity) {
			FlightDTO replaceItem = new FlightDTO();
			BeanUtils.copyProperties(item, replaceItem);
			
			list.add(replaceItem);
		}
		
		model.addAttribute("flights", list);
		return "admin/flights/list";
	}

	
}
