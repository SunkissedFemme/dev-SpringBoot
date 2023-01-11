package com.example.demo.model;

import javax.persistence.Entity;

/* Encapsulates data about an interval with following attributes: id, start and end. */
public class SimpleInterval extends Interval{

	public SimpleInterval(Long id, String start, String end) {
		super(id, start, end);
		// TODO Auto-generated constructor stub
	}

}
