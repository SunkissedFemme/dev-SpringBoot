package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import service.IntervalService;

@RestController
@RequestMapping("api/intervals")
public class HelloController {

	private final IntervalService intervalService=null;
	
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}