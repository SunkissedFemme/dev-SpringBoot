package com.example.demo.model;

/* Encapsulates data about an interval with following attributes: id, start and end and duration. */
public class EnhancedInterval extends SimpleInterval {
	
	private final String duration;

	public EnhancedInterval(Long id, String start, String end, String duration) {
		super(id, start, end);
		this.duration=duration;
	}
	
	public String getDuration() {
		return duration;
	}


}
