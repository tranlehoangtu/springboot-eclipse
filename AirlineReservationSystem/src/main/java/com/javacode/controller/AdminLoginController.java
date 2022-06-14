package com.javacode.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javacode.domain.Account;
import com.javacode.model.AccountDTO;
import com.javacode.service.AccountService;

@Controller
public class AdminLoginController {

	private AccountService accountService;
	private HttpSession session;

	@Autowired
	public AdminLoginController(AccountService accountService, HttpSession session) {
		this.accountService = accountService;
		this.session = session;
	}

	@RequestMapping(value = "alogin")
	public String login(Model model) {
		model.addAttribute("account", new AccountDTO());
		return "admin/flights/login";
	}

	@PostMapping(value = "alogin")
	public String login(@ModelAttribute(name = "account") AccountDTO accountDTO) {
		Optional<Account> accOpt = accountService.findById(accountDTO.getUsername());
		
		return null;
	}
}

























