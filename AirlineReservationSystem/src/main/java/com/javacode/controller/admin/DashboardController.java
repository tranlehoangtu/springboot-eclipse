package com.javacode.controller.admin;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javacode.domain.Flight;
import com.javacode.service.FlightService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("admin/dashboard")
public class DashBoardController {
	private final FlightService flightService;
	
	@GetMapping(value = "")
	public String list(Model model) {
		Pageable pageable = PageRequest.of(0, 10);
		Page<Flight> listEntity = flightService.findAll(pageable);

		model.addAttribute("flights", listEntity);
		
		return "/admin/dashboard/index";
	}
}
