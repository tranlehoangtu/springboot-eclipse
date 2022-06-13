package com.javacode.TechPolyShop.controller.admin;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.javacode.TechPolyShop.domain.Account;
import com.javacode.TechPolyShop.model.AccountDTO;
import com.javacode.TechPolyShop.service.AccountService;

@Controller
@RequestMapping(value = "admin/accounts")
public class AccountController {
	private AccountService accountService;

	@Autowired
	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}

	@GetMapping(value = "add")
	public String add(Model model) {
		AccountDTO accountDTO = new AccountDTO();
		accountDTO.setIsEdit(false);
		
		model.addAttribute("account", accountDTO);

		return "admin/accounts/addOrEdit";
	}

	@PostMapping(value = "saveOrUpdate")
	public String saveOrUpdate(Model model, @Valid @ModelAttribute("account") AccountDTO accountDTO,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("Have Some Errors");
			return "admin/accounts/addOrEdit";
		}

		Account entity = new Account();
		BeanUtils.copyProperties(accountDTO, entity);

		model.addAttribute("message", "Account is Saved !");
		accountService.save(entity);

		return "forward:/admin/accounts";
	}

	@RequestMapping(value = "")
	public String list(Model model) {
		List<Account> list = accountService.findAll();

		model.addAttribute("accounts", list);
		model.addAttribute("notify", "For presentation purpose, in this case we show password but we should not");

		return "admin/accounts/list";
	}

	@GetMapping(value = "edit/{username}")
	public String edit(Model model, @PathVariable("username") String username) {
		Optional<Account> entityOpt = accountService.findById(username);
		AccountDTO accountDTO = new AccountDTO();

		if (entityOpt.isPresent()) {
			Account entity = entityOpt.get();

			BeanUtils.copyProperties(entity, accountDTO);
			
			accountDTO.setIsEdit(true);
			accountDTO.setPassword("");
			
			model.addAttribute("account", accountDTO);

			return "admin/accounts/addOrEdit";
		}
		model.addAttribute("message", "Account is not existed !");

		return "forward:/admin/accounts";
	}

	@GetMapping(value = "delete/{username}")
	public String delete(Model model, @PathVariable(name = "username") String username) {
		if (accountService.findById(username).isPresent()) {
			accountService.deleteById(username);
			model.addAttribute("message", "Account is Deleted !");
		} else
			model.addAttribute("message", "Invalid Request !!");

		return "forward:/admin/accounts";
	}


//
//	@GetMapping(value = "search")
//	public String search(Model model, @RequestParam(name = "name", required = false) String name) {
//		List<Account> list = null;
//
//		if (StringUtils.hasText(name))
//			list = service.findByNameContaining(name);
//		else
//			list = service.findAll();
//
//		model.addAttribute("accounts", list);
//
//		return "admin/accounts/search";
//	}
//
//	@GetMapping(value = "searchpaginated")
//	public String search(Model model, @RequestParam(name = "name", required = false) String name,
//			@RequestParam(name = "page") Optional<Integer> page, @RequestParam(name = "size") Optional<Integer> size) {
//
//		int currentPage = page.orElse(1);
//		int pageSize = size.orElse(5);
//
//		Pageable pageable = PageRequest.of(currentPage - 1, pageSize, Sort.by("name"));
//		Page<Account> resultPage = null;
//
//		if (StringUtils.hasText(name)) {
//			resultPage = service.findByNameContaining(name, pageable);
//			model.addAttribute("name", name);
//		}
//
//		else {
//			resultPage = service.findAll(pageable);
//
//			for (Account account : resultPage.getContent()) {
//				System.out.println(account.toString());
//			}
//		}
//
//		int totalPages = resultPage.getTotalPages();
//
//		if (totalPages > 0) {
//			int start = Math.max(1, currentPage - 2);
//			int end = Math.min(currentPage + 2, totalPages);
//
//			if (totalPages > 5) {
//				if (end == totalPages)
//					start = end - 5;
//				else if (start == 1)
//					end = start + 5;
//			}
//
//			List<Integer> pageNumbers = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
//
//			model.addAttribute("pageNumbers", pageNumbers);
//
//		}
//		System.out.println("categoryPage: " + resultPage.getNumber());
//		model.addAttribute("categoryPage", resultPage);
//
//		return "admin/accounts/searchpaginated";
//	}
}
