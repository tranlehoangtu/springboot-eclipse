package com.javacode.springbootstaterweb;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {

	@RequestMapping("/test")
	public String test() {
		System.out.println("--AppController");
		
		return "test";
	}
	
	@RequestMapping("/list_contact")
	public String getAllContact(Model model) {
		List<Contact> contactList = new ContactBussiness().getAllContact();
		
		model.addAttribute("contacts", contactList);
		return "contact.html";
	}
	
}
