package com.javacode.TechPolyShop.controller.admin;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.javacode.TechPolyShop.domain.Category;
import com.javacode.TechPolyShop.domain.Product;
import com.javacode.TechPolyShop.model.CategoryDTO;
import com.javacode.TechPolyShop.model.ProductDTO;
import com.javacode.TechPolyShop.service.CategoryService;
import com.javacode.TechPolyShop.service.ProductService;
import com.javacode.TechPolyShop.service.StorageService;

@Controller
@RequestMapping(value = "admin/products")
public class ProductController {
	private ProductService productService;
	private CategoryService categoryService;
	private StorageService storageService;

	@Autowired
	public ProductController(ProductService productService, CategoryService categoryService,
			StorageService storageService) {
		this.productService = productService;
		this.categoryService = categoryService;
		this.storageService = storageService;
	}

	@ModelAttribute("categories")
	public List<CategoryDTO> getCategories() {
		return categoryService.findAll().stream().map(item -> {
			CategoryDTO categoryDTO = new CategoryDTO();
			BeanUtils.copyProperties(item, categoryDTO);
			return categoryDTO;
		}).toList();
	}

	@GetMapping(value = "add")
	public String add(Model model) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setIsEdit(false);

		model.addAttribute("product", productDTO);
		return "admin/products/addOrEdit";
	}

	@GetMapping(value = "edit/{productId}")
	public String edit(Model model, @PathVariable("productId") Long productId) {
		Optional<Product> entityOpt = productService.findById(productId);
		ProductDTO productDTO = new ProductDTO();

		if (entityOpt.isPresent()) {
			Product entity = entityOpt.get();

			BeanUtils.copyProperties(entity, productDTO);
			productDTO.setIsEdit(true);

			model.addAttribute("product", productDTO);

			return "admin/products/addOrEdit";
		}
		model.addAttribute("message", "Product is not existed !");

		return "forward:/admin/products";
	}

	@GetMapping("/images/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
		Resource file = storageService.loadAsResource(filename);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}

	@SuppressWarnings("deprecation")
	@GetMapping(value = "delete/{productId}")
	public String delete(Model model, @PathVariable(name = "productId") Long productId) throws IOException {

		Optional<Product> opt = productService.findById(productId);

		if (opt.isPresent()) {
			if (!StringUtils.isEmpty(opt.get().getImage())) {
				storageService.delete(opt.get().getImage());
			}

			model.addAttribute("message", "Product is Deleted!");
			productService.delete(opt.get());
		} else {
			model.addAttribute("message", "Product is not Found!");
		}

		return "forward:/admin/products";
	}

	@PostMapping(value = "saveOrUpdate")
	public String saveOrUpdate(Model model, @Valid @ModelAttribute("product") ProductDTO productDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			return "admin/products/addOrEdit";
		}

		Product entity = new Product();
		BeanUtils.copyProperties(productDTO, entity);

		Category category = new Category();
		category.setCategoryId(productDTO.getCategoryId());
		entity.setCategory(category);

		if (!productDTO.getImageFile().isEmpty()) {
			UUID uuid = UUID.randomUUID();
			String uuString = uuid.toString();
			System.out.println("Image File: " + productDTO.getImageFile());

			entity.setImage(storageService.getStoredFilename(productDTO.getImageFile(), uuString));
			storageService.store(productDTO.getImageFile(), entity.getImage());
		}

		model.addAttribute("message", "Product is Saved !");
		productService.save(entity);

		return "forward:/admin/products/";
	}

	@RequestMapping(value = "")
	public String list(Model model) {
		List<Product> list = productService.findAll();

		for (Product item : list) {
			System.out.println(item.toString());
		}

		model.addAttribute("products", list);
		return "admin/products/list";
	}

	@GetMapping(value = "search")
	public String search(Model model, @RequestParam(name = "name", required = false) String name) {
		List<Product> list = null;

		if (StringUtils.hasText(name)) {
//			list = service.findByNameContaining(name);
		} else
			list = productService.findAll();

		model.addAttribute("products", list);

		return "admin/products/search";
	}

	@GetMapping(value = "searchpaginated")
	public String search(Model model, @RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "page") Optional<Integer> page, @RequestParam(name = "size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(5);

		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
		Page<Product> resultPage = null;

		if (StringUtils.hasText(name)) {
//			resultPage = service.findByNameContaining(name, pageable);
			model.addAttribute("name", name);
		}

		else {
			resultPage = productService.findAll(pageable);

			for (Product category : resultPage.getContent()) {
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

		return "admin/products/searchpaginated";
	}
}
