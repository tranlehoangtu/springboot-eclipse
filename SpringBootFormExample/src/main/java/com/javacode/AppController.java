package com.javacode;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {

	@GetMapping(value = "/")
	public String home() {
		System.out.println("Going home...");

		return "index";
	}

	@GetMapping(value = "/register")
	public String showForm(Model model) {
		User user = new User();
		List<String> professionList = Arrays.asList("Developer", "Tester");

		model.addAttribute("user", user);
		model.addAttribute("professionList", professionList);

		return "register_form";
	}

	@PostMapping(value = "/register")
	public String submitForm(@ModelAttribute User user) {
		System.out.println(user);

		return "register_success";
	}
}
