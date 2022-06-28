package com.javacode.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javacode.domain.Account;
import com.javacode.service.AccountService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/admin/accounts")
public class AccountController {
	private final AccountService accountService;

	@GetMapping(value = "")
	public String list(Model model) {

		List<Account> listEntity = accountService.findAll();
		
		model.addAttribute("accounts", listEntity);
		
		return "admin/accounts/list";
	}
	
	@GetMapping(value = "delete/{username}")
	public String delete(Model model, @PathVariable(name = "username") String username) {
		
		Optional<Account> accountOpt = accountService.findById(username);
		if(accountOpt.isPresent()) {
			accountService.delete(accountOpt.get());
			model.addAttribute("message", accountOpt.get().getUsername() + " is Deleted !!");
			return "redirect:/admin/accounts/";
		}
		
		model.addAttribute("message", "No account found");
		return "redirect:/admin/accounts/";
	}
}
