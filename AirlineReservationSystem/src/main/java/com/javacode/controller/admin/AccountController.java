package com.javacode.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javacode.domain.Account;
import com.javacode.model.AccountDTO;
import com.javacode.service.AccountService;

@Controller
@RequestMapping(value = "/admin/accounts")
public class AccountController {
	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(value = "")
	public String list(Model model) {

		List<Account> listEntity = accountService.findAll();

		List<AccountDTO> list = new ArrayList<AccountDTO>();

		for (Account item : listEntity) {
			AccountDTO replaceItem = new AccountDTO();
			BeanUtils.copyProperties(item, replaceItem);

			list.add(replaceItem);
		}

		model.addAttribute("accounts", list);
		
		return "admin/accounts/list";
	}
}
