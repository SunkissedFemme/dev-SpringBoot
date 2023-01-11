package com.example.demo.model;

/* Encapsulates data about an interval with following attributes: id, start and end, duration and break. */
public class EnhancedWithBreakInterval extends EnhancedInterval{

	private final String breakDuration;
	
	public EnhancedWithBreakInterval(Long id, String start, String end, String duration, String breakDuration) {
		super(id, start, end, duration);
		this.breakDuration=breakDuration;
	}
	
	public String getBreakDuration() {
		return breakDuration;
	}

}
