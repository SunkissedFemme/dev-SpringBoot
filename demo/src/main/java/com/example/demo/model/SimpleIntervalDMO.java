package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="intervals")
public class SimpleIntervalDMO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    

	private String start;
	private String end;
	
	public SimpleIntervalDMO() {
		
	}
    
    public SimpleIntervalDMO(
    		Long id, 
    		String start, 
    		String end) {
    	this.id = id;
    	this.start=start;
    	this.end=end;
    }
    
    public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}
    

}
