package repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;

import model.Interval;

@Configuration
public class IntervalRepository {
	
	private List<Interval> intervals;
	
	public IntervalRepository() {
		intervals = new ArrayList<>();
	}
	
//	public static IntervalRepository getInstance() {
//		if(instance == null) {
//			instance = new IntervalRepository();
//		}
//		return instance;
//	}
	
	public void addInterval(Interval i) {
		intervals.add(i);
	}
	
	public Optional<Interval> getInterval(Long id) {
		return intervals
		.stream()
		.filter(i -> i.getId().equals(id))
		.findFirst();
		
	}
	
	public void removeInterval(Interval i) {
		intervals.remove(i);
	}
	
	public List<Interval> getAllIntervals(){
		return intervals;
	}

}
