package com.javacode.ProductManager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/")
	public String viewHomePage(Model model) {

		List<Product> listProduct = productService.listAll();

		model.addAttribute("products", listProduct);

		return "index";
	}

	@RequestMapping(value = "/new")
	public String showNewProductForm(Model model) {

		model.addAttribute("product", new Product());

		return "new_product";
	}
	
	@RequestMapping(value = "/edit/{id}")
	public String showEditProductForm(@PathVariable(name="id") Long id, Model model) {
		Product product = productService.get(id);
		model.addAttribute("product", product);
		
		return "edit_product";
	}
	
	@RequestMapping(value = "/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		productService.delete(id);
		
		return "redirect:/";
	}

	@RequestMapping(value = "/save")
	public String saveProduct(@ModelAttribute ("product") Product product) {
		productService.save(product);
		
		return "redirect:/";
	}

}
