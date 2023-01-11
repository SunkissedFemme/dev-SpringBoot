package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;

import com.example.demo.model.Interval;

///** Hard coded repository for interval objects. */
//@Configuration
//public class IntervalRepository {
//	
//	private List<Interval> intervals;
//	
//	public IntervalRepository() {
//		intervals = new ArrayList<>();
//	}
//	
//	/**
//	 * Adds given interval in list.
//	 *
//	 * @param i interval to be added
//	 */
//	public void addInterval(Interval i) {
//		intervals.add(i);
//	}
//	
//	/**
//	 * Removes from the list the interval with the given id.
//	 * If the interval with the given id is not found in the database, an exception is thrown.
//	 * 
//	 * @param id interval id
//	 */
//	public void removeInterval(Long id) {
//		Optional<Interval> interval = getInterval(id);
//		if(interval.isEmpty()) {
//			throw new RuntimeException("Interval with id: "+ id+ " can not deleted. It does not exist anymore in database.");
//		}else {
//			intervals.remove(interval.get());
//		}
//	}
//	
//	/**
//	 * Get the interval with the given id.
//	 * 
//	 * @param id interval id
//	 * @return Optional<Interval>
//	 */
//	public Optional<Interval> getInterval(Long id) {
//		return intervals
//		.stream()
//		.filter(i -> i.getId().equals(id))
//		.findFirst();
//		
//	}
//	
//	
//	/**
//	 * Retrieve a list with all created intervals.
//	 * 
//	 * @return list with interval objects
//	 */
//	public List<Interval> getAllIntervals(){
//		return intervals;
//	}
//
//
//}
