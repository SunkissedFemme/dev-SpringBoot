package com.example.demo;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import model.EnhancedInterval;
import model.Interval;
import model.SimpleInterval;
import model.WrapperEnhancedIntervals;
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
	
	/**
	 * Adds an interval to database.
	 * 
	 * @param interval simple interval to be added
	 * @return ResponseEntity<Interval>
	 */
    @PostMapping
    public ResponseEntity<Interval> create(@RequestBody SimpleInterval interval) {
        Interval created = intervalService.create((SimpleInterval) interval);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location).body(created);
    }
    
    /**
     * Removes the interval with the given id.
     * 
     * @param id interval id
     * @return ResponseEntity<HttpStatus>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> removeInterval(@PathVariable("id") Long id) {
      try {
        intervalService.removeInterval(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
    }
    
    /**
     * Get the interval with the given id.
     * 
     * @param id interval id 
     * @return ResponseEntity<Interval>
     */
    @GetMapping("/{id}")
    public ResponseEntity<Interval> getInterval(@PathVariable("id") Long id) {
        Optional<Interval> interval = intervalService.getInterval(id);
        if (interval.isPresent()) {
            return new ResponseEntity<>(interval.get(), HttpStatus.OK);
          } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
          }
    }
    
    /**
     * Get all intervals. 
     * 
     * @return a list containing simple interval objects which wee created
     */
    @GetMapping
    public ResponseEntity<List<Interval>> getAllIntervals() {
        List<Interval> items = intervalService.getAllIntervals();
        return ResponseEntity.ok().body(items);
    }

    
    /**
     * Perform a set of operations on the elements from the given list.
     * 
     * @param intervals intervals
     * @return ResponseEntity<WrapperEnhancedIntervals> a response containing a
     *         list with interval objects which encapsulate additional information like duration, break
     */
    @PostMapping("/calculate")
    public ResponseEntity<WrapperEnhancedIntervals> calculate(@RequestBody WrapperIntervals intervals) {
        List<EnhancedInterval> result = intervalService.calculate(intervals.getIntervals());
        WrapperEnhancedIntervals enhancedInterval = new WrapperEnhancedIntervals();
        enhancedInterval.setIntervals(result);
        return ResponseEntity.ok().body(enhancedInterval);
    }
    
   
    
    
}