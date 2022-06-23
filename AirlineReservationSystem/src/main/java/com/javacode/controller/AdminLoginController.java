package com.javacode.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.javacode.domain.Account;
import com.javacode.domain.Flight;
import com.javacode.model.AccountDTO;
import com.javacode.service.AccountService;
import com.javacode.service.FlightService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminLoginController {

	private final AccountService accountService;
	private final FlightService flightService;
	private final HttpSession session;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@GetMapping(value = "add")
	public String add(Model model) {
		model.addAttribute("account", new AccountDTO());
		return "admin/accounts/addOrEdit";
	}

	@GetMapping(value = "signout")
	public String signout(Model model) {
		session.removeAttribute("username");
		model.addAttribute("account", new AccountDTO());

		return "admin/accounts/login";
	}

	@PostMapping(value = "saveOrUpdate")
	public String saveOrUpdate(Model model, @ModelAttribute(name = "account") AccountDTO accountDTO,
			BindingResult result) {

		if (result.hasErrors()) {
			return "admin/accounts/login";
		}

		String username = accountDTO.getUsername();
		String password = accountDTO.getPassword();
		String repassword = accountDTO.getConfirmPassword();

		if (accountService.signup(username, password, repassword)) {
			Account entity = new Account();

			BeanUtils.copyProperties(accountDTO, entity);

			entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
			accountService.save(entity);
		} else {
			model.addAttribute("message", "Username does Exist");
			return "/admin/accounts/addOrEdit";
		}
		model.addAttribute("message", "Sign Up Successfully");
		model.addAttribute("account", new AccountDTO());

		return "/admin/accounts/login";
	}

	@GetMapping(value = "alogin")
	public String login(Model model) {
		model.addAttribute("account", new AccountDTO());
		return "admin/accounts/login";
	}

	@PostMapping(value = "alogin")
	public String login(Model model, @Valid @ModelAttribute(name = "account") AccountDTO accountDTO,
			BindingResult result) {

		System.out.println("Username: " + accountDTO.getUsername() + " | Password: " + accountDTO.getPassword());

		if (result.hasErrors()) {
			model.addAttribute("message", "Invalid Username or Password");
			return "admin/accounts/login";
		}

		Account entity = new Account();
		BeanUtils.copyProperties(accountDTO, entity);

		if (accountService.login(entity.getUsername(), entity.getPassword())) {
			session.setAttribute("username", entity.getUsername());
			Object redirectUri = session.getAttribute("redirect-uri");

			if (redirectUri != null) {
				session.removeAttribute("redirect-uri");
				return "redirect:" + redirectUri;
			}

			return "redirect:/dashboard";
		}

		model.addAttribute("message", "Wrong Username or Password");
		return "/admin/accounts/login";
	}

	@GetMapping(value = "asignup")
	public String signup(Model model) {
		model.addAttribute("account", new AccountDTO());
		return "admin/flights/";
	}
	
	@GetMapping(value = "dashboard")
	public String loadDashBoard(Model model) {
		List<Flight> listEntity = flightService.findAll();
		model.addAttribute("flights", listEntity);
		
		return "admin/dashboard/index";
	}
}
