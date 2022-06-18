package com.example.demo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.Interval;
import model.SimpleInterval;
import service.IntervalServiceImpl;

@RestController
@ComponentScan("com.example.demo")
@RequestMapping("api/intervals")
public class IntervalController {

	private final IntervalServiceImpl intervalService;
	
	public IntervalController() {
		this.intervalService = new IntervalServiceImpl();
	}
	
	
    @PostMapping
    public ResponseEntity<Interval> create(@RequestBody Interval interval) {
        Interval created = intervalService.create((SimpleInterval) interval);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
    
//	@GetMapping("/")
//	public String index() {
//		return "Greetings from Spring Boot!";
//	}

}