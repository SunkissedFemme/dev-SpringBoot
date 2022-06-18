package com.example.demo;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.Interval;
import model.SimpleInterval;
import model.WrapperIntervals;
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
    public ResponseEntity<Interval> create(@RequestBody SimpleInterval interval) {
        Interval created = intervalService.create((SimpleInterval) interval);
        System.out.println("Create a new resource");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
               .buildAndExpand(created.getId())
               .toUri();
//        
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("todo", "/api/intervals/" + created.getId().toString());
//        return new ResponseEntity<>(created, httpHeaders, HttpStatus.CREATED);
        return ResponseEntity.created(location).body(created);
    }
    
    @PostMapping("/calculate")
    public ResponseEntity<Interval> calculate(@RequestBody WrapperIntervals intervals) {
        Interval created = intervalService.create((SimpleInterval) intervals.getIntervals().get(0));
        System.out.println("Calculating");
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
               .buildAndExpand(created.getId())
               .toUri();
        return ResponseEntity.created(location).body(created);
    }
    
    @GetMapping
    public ResponseEntity<List<Interval>> findAll() {
        List<Interval> items = intervalService.getAll();
        return ResponseEntity.ok().body(items);
    }
    
//	@GetMapping("/")
//	public String index() {
//		return "Greetings from Spring Boot!";
//	}

}