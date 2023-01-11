package com.example.demo.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.EnhancedInterval;
import com.example.demo.model.EnhancedWithBreakInterval;
import com.example.demo.model.Interval;
import com.example.demo.model.SimpleInterval;
import com.example.demo.model.SimpleIntervalDMO;
import com.example.demo.repository.MyIntervalRepository;

@Service
public class IntervalServiceImpl implements IntervalService{
	
	@Autowired
	private MyIntervalRepository intervalRepository;
	private  SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	//private  IntervalRepository intervalRepository;
	
	
	public IntervalServiceImpl() {
		//intervalRepository = new IntervalRepository();
		
		//adds default data
		//intervalRepository.addInterval(defaultIntervals().get(0));
	}

	
	@Override
	 public SimpleIntervalDMO create(SimpleIntervalDMO interval) {
		 SimpleIntervalDMO copy = new SimpleIntervalDMO(
	                interval.getId(),
	                interval.getStart(),
	                interval.getEnd());
	        
	        intervalRepository.save(copy);
	        
	        return copy;
	 }
	
	@Override
	public void removeInterval(Long id) {
		intervalRepository.deleteById(id);
	}
	 
	@Override
	public Optional<SimpleIntervalDMO> getInterval(Long id) {
		return intervalRepository.findById(id);
	}
	 
	@Override
	public List<SimpleIntervalDMO> getAllIntervals() {
		List<SimpleIntervalDMO> allIntervals = new ArrayList<>();
		intervalRepository.findAll().forEach(allIntervals::add);
		return allIntervals;
	} 
	
	/**
	 * Creates default data. Used only for testing reasons.
	 * 
	 * @return list of new created interval objects
	 */
	private static List<Interval> defaultIntervals() {
		return List.of( new SimpleInterval(1L, "24/06/2014 08:22:07", "28/12/2016 12:10:14"));
	}
	 
	@Override
	public List<EnhancedInterval> calculate(List<SimpleInterval> toBeCalculated){
		 
		 List<EnhancedInterval> result = new ArrayList<>();
		
	     EnhancedInterval enhancedInterval=null;
	     //for simplifying the logic, supposed that intervals are ordered ascending
		 for(int i=0; i<toBeCalculated.size();i++) {
			 Interval interval = toBeCalculated.get(i);
			//for the all intervals, calculate duration between start and end date
			 String duration = calculateDuration(interval.getStart(), interval.getEnd());
			 if(i==0) {
				 enhancedInterval = new EnhancedInterval(
						 interval.getId(), 
						 interval.getStart(), 
						 interval.getEnd(), 
						 duration);
			 }
			 else {
				//for the rest of the intervals, calculate break duration between end date
				 //of the previous interval and start date of the current one
				String breakDuration = calculateDuration(toBeCalculated.get(i-1).getEnd(), interval.getStart());
				enhancedInterval = new EnhancedWithBreakInterval(
						interval.getId(), 
						interval.getStart(), 
						interval.getEnd(), 
						duration, 
						breakDuration);
			 }
			
			 result.add(enhancedInterval);
		 }
		 return result;
		
	 }
	 
	/**
	 * Calculates duration between a given start and end date.
	 * 
	 * @param startDate 
	 * @param endDate
	 * @return a string representing duration expressed in years, days, hours, minutes and seconds
	 */
	 private String calculateDuration(String startDate, String endDate) {
		 StringBuilder duration = new StringBuilder("");
	        try {
	  
	            Date d1 = dateFormat.parse(startDate);
	            Date d2 = dateFormat.parse(endDate);
	  
	            // Calcualte time difference
	            // in milliseconds
	            long difference_In_Time
	                = d2.getTime() - d1.getTime();
	  
	            long difference_In_Seconds
	                = TimeUnit.MILLISECONDS
	                      .toSeconds(difference_In_Time)
	                  % 60;
	  
	            long difference_In_Minutes
	                = TimeUnit
	                      .MILLISECONDS
	                      .toMinutes(difference_In_Time)
	                  % 60;
	  
	            long difference_In_Hours
	                = TimeUnit
	                      .MILLISECONDS
	                      .toHours(difference_In_Time)
	                  % 24;
	  
	            long difference_In_Days
	                = TimeUnit
	                      .MILLISECONDS
	                      .toDays(difference_In_Time)
	                  % 365;
	  
	            long difference_In_Years
	                = TimeUnit
	                      .MILLISECONDS
	                      .toDays(difference_In_Time)
	                  / 365l;
	  
	    duration.append(difference_In_Years)
	            .append(" years, ")
	            .append(difference_In_Days)
	            .append(" days, ")
	            .append(difference_In_Hours)
	            .append(" hours, ")
	            .append(difference_In_Minutes)
	            .append(" minutes, ")
	            .append(difference_In_Seconds)
	            .append(" seconds");
	        }
	        catch (ParseException e) {
	            e.printStackTrace();
	        }
			return duration.toString(); 
	 }


	
}
