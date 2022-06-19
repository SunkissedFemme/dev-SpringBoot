package service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import model.EnhancedInterval;
import model.EnhancedWithBreakInterval;
import model.Interval;
import model.SimpleInterval;
import repository.IntervalRepository;

@Service
public class IntervalServiceImpl implements IntervalService{
	
	private  IntervalRepository intervalRepository;
	private  SimpleDateFormat sdf= new SimpleDateFormat(
            "dd/MM/yyyy HH:mm:ss");
	
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
	 
	 public Optional<Interval> find(Long id) {
	        return intervalRepository.getInterval(id);
	    }
	 
	 public List<Interval> getAll() {
	       
	        return intervalRepository.getAllIntervals();
	    } 
	 private static List<Interval> defaultIntervals() {
		    return List.of(
		      new SimpleInterval(1L, "24/06/2014 08:22:07", "28/12/2016 12:10:14")
		    );
		  }
	 
	 public List<EnhancedInterval> calculate(List<SimpleInterval> toBeCalculated){
		 List<EnhancedInterval> result = new ArrayList<>();
		
			EnhancedInterval interval_=null;
		 for(int i=0; i<toBeCalculated.size();i++) {
			 Interval interval = toBeCalculated.get(i);
			 String duration = findDifference(interval.getStart(), interval.getEnd());
			 if(i==0) {
				 interval_ = new EnhancedInterval(interval.getId(), interval.getStart(), interval.getEnd(), duration);
			 }
			 else {
				String breakDuration = findDifference(interval.getEnd(), toBeCalculated.get(i-1).getStart());
				 interval_ = new EnhancedWithBreakInterval(interval.getId(), interval.getStart(), interval.getEnd(), duration, breakDuration);
			 }
			
			 result.add(interval_);
		 }
		 return result;
		
	 }
	 
	 private String findDifference(String startDate, String endDate) {
		 StringBuilder sb1 = new StringBuilder("");
	        try {
	  
	            // parse method is used to parse
	            // the text from a string to
	            // produce the date
	            Date d1 = sdf.parse(startDate);
	            Date d2 = sdf.parse(endDate);
	  
	            // Calucalte time difference
	            // in milliseconds
	            long difference_In_Time
	                = d2.getTime() - d1.getTime();
	  
	            // Calucalte time difference in seconds,
	            // minutes, hours, years, and days
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
	  
	            // Print the date difference in
	            // years, in days, in hours, in
	            // minutes, and in seconds
	            System.out.print(
	                "Difference"
	                + " between two dates is: ");
	  
	            // Print result
	            System.out.println(
	                difference_In_Years
	                + " years, "
	                + difference_In_Days
	                + " days, "
	                + difference_In_Hours
	                + " hours, "
	                + difference_In_Minutes
	                + " minutes, "
	                + difference_In_Seconds
	                + " seconds");
	            sb1.append(difference_In_Years)
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
			return sb1.toString(); 
	 }


	public void deleteById(long id) {
		intervalRepository.removeInterval(id);
		
	}
}
