package com.javacode.TechPolyShop.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javacode.TechPolyShop.domain.Account;
import com.javacode.TechPolyShop.model.AdminLoginDTO;
import com.javacode.TechPolyShop.service.AccountService;

@Controller
public class AdminLoginController {

	private AccountService accountService;
	private HttpSession session;

	@Autowired
	public AdminLoginController(AccountService accountService, HttpSession session) {
		this.accountService = accountService;
		this.session = session;
	}

	@GetMapping(value = "alogin")
	public String login(Model model) {
		model.addAttribute("account", new AdminLoginDTO());
		return "/admin/accounts/login";
	}

	@PostMapping(value = "alogin")
	public String login(Model model, @Valid @ModelAttribute("account") AdminLoginDTO adminLoginDTO,
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "/admin/accounts/login";
		}
		
		Account account = accountService.login(adminLoginDTO.getUsername(), adminLoginDTO.getPassword());
		
		if(account == null) {
			model.addAttribute("message", "Invalid Username or Password");
			return "/admin/accounts/login";
		}

		session.setAttribute("username", account.getUsername());
		
		Object redirectUri = session.getAttribute("redirect-uri");
		
		if(redirectUri != null) {
			session.removeAttribute("redirect-uri");
			return "redirect:" + redirectUri;
		}
		
		return "forward:/admin/categories";
	}

}
