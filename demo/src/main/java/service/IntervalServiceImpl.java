package service;

import org.springframework.stereotype.Service;

import model.Interval;
import model.SimpleInterval;
import repository.IntervalRepository;

@Service
public class IntervalServiceImpl implements IntervalService{
	
	private  IntervalRepository intervalRepository;
	
	public IntervalServiceImpl() {
		intervalRepository = new IntervalRepository();
	}
//	public IntervalServiceImpl(IntervalRepository intervalRepository) {
//		this.intervalRepository=intervalRepository;
//	}

	
	 public Interval create(SimpleInterval interval) {
		 Interval copy = new SimpleInterval(
	                interval.getId(),
	                interval.getStart(),
	                interval.getEnd());
	        
	        intervalRepository.addInterval(copy);
	        
	        return copy;
	    }
}
