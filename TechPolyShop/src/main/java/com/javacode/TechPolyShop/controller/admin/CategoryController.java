package com.javacode.TechPolyShop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

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

import com.javacode.TechPolyShop.domain.Category;
import com.javacode.TechPolyShop.model.CategoryDTO;
import com.javacode.TechPolyShop.service.CategoryService;

@Controller
@RequestMapping(value = "admin/categories")
public class CategoryController {
	private CategoryService service;

	@Autowired
	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping(value = "add")
	public String add(Model model) {
		model.addAttribute("category", new CategoryDTO());
		return "admin/categories/addOrEdit";
	}

	@GetMapping(value = "edit/{categoryId}")
	public String edit(Model model, @PathVariable("categoryId") Long categoryId) {
		Optional<Category> entityOpt = service.findById(categoryId);
		CategoryDTO categoryDTO = new CategoryDTO();

		if (entityOpt.isPresent()) {
			Category entity = entityOpt.get();

			BeanUtils.copyProperties(entity, categoryDTO);
			categoryDTO.setIsEdit(true);

			model.addAttribute("category", categoryDTO);

			return "admin/categories/addOrEdit";
		}
		model.addAttribute("message", "Category is not existed !");

		return "forward:/admin/categories";
	}

	@GetMapping(value = "delete/{categoryId}")
	public String delete(Model model, @PathVariable(name = "categoryId") Long categoryId) {
		if (service.findById(categoryId).isPresent()) {
			service.deleteById(categoryId);
			model.addAttribute("message", "Category is Deleted !");
		} else
			model.addAttribute("message", "Invalid Request !!");

		return "forward:/admin/categories/search";
	}

	@PostMapping(value = "saveOrUpdate")
	public String saveOrUpdate(Model model, @Valid @ModelAttribute("category") CategoryDTO categoryDTO,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Have Some Errors");
			return "admin/categories/addOrEdit";
		}

		Category entity = new Category();
		BeanUtils.copyProperties(categoryDTO, entity);

		model.addAttribute("message", "Category is Saved !");
		service.save(entity);
		return "forward:/admin/categories";
	}

	@RequestMapping(value = "")
	public String list(Model model) {
		List<Category> list = service.findAll();

		model.addAttribute("categories", list);
		return "admin/categories/list";
	}

	@GetMapping(value = "search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name) {
		List<Category> list = null;

		if (StringUtils.hasText(name))
			list = service.findByNameContaining(name);
		else
			list = service.findAll();

		model.addAttribute("categories", list);

		return "admin/categories/search";
	}

	@GetMapping(value = "searchpaginated")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "page") Optional<Integer> page, @RequestParam(name = "size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Category> resultPage = null;

		if (StringUtils.hasText(name)) {
			resultPage = service.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		}

		else {
			resultPage = service.findAll(pageable);

			for (Category category : resultPage.getContent()) {
				System.out.println(category.toString());
			}
		}

		int totalPages = resultPage.getTotalPages();

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
		System.out.println("categoryPage: " + resultPage.getNumber());
		model.addAttribute("categoryPage", resultPage);

		return "admin/categories/searchpaginated";
	}
}
