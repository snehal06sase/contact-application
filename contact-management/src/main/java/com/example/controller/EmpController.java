package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpController {

	@GetMapping("/")
	public String home() {
		// TODO Auto-generated constructor stub
		return "index";
	}

}
