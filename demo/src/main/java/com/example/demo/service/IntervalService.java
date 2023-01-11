package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.EnhancedInterval;
import com.example.demo.model.SimpleInterval;
import com.example.demo.model.SimpleIntervalDMO;

/* Interface for defining actions with interval objects. */
public interface IntervalService {
	
	/**
	 * Creates and persists a new interval object.
	 * 
	 * @param interval simple interval object
	 * @return created interval
	 */
	SimpleIntervalDMO create(SimpleIntervalDMO interval);

	/**
	 * Removes from database the interval with the given id.
	 * 
	 * @param id interval id
	 */
	void removeInterval(Long id);
	
	/**
	 * Retrieves the interval with the given id.
	 *
	 * @param id interval id
	 * @return interval
	 */
	Optional<SimpleIntervalDMO> getInterval(Long id);
	
	/**
	 * Retrieve from database all interval objects.
	 *
	 * @return list with interval objects; may be empty if no intervals were created
	 */
	List<SimpleIntervalDMO> getAllIntervals();
	
	/**
	 * Performs a set of operations of a list of interval objects.
	 * Following operations will be executed:
	 * 1.for each interval, it calculates the interval duration
	 * 2.for each interval, except the first one, it calculates the 
	 * 	 duration between the end of the previous interval and the 
	 *   start of the new interval
	 * 
	 * @param toBeCalculated list of intervals to perform these operations
	 * @return a list with enhanced interval objects which contain additional info 
	 *         which was calculated (duration, break duration between two interval)
	 */
	List<EnhancedInterval> calculate(List<SimpleInterval> toBeCalculated);
}
