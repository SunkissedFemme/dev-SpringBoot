package service;

import java.util.List;

import org.springframework.stereotype.Service;

import model.Interval;
import model.SimpleInterval;
import repository.IntervalRepository;

@Service
public class IntervalServiceImpl implements IntervalService{
	
	private  IntervalRepository intervalRepository;
	
	public IntervalServiceImpl() {
		intervalRepository = new IntervalRepository();
		intervalRepository.addInterval(defaultIntervals().get(0));
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
	 
	 public List<Interval> getAll() {
	       
	        return intervalRepository.getAllIntervals();
	    } 
	 private static List<Interval> defaultIntervals() {
		    return List.of(
		      new SimpleInterval(1L, "24/06/2014 08:22:07", "28/12/2016 12:10:14")
		    );
		  }
}
