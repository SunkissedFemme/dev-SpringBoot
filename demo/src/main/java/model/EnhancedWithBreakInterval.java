package model;

public class EnhancedWithBreakInterval extends EnhancedInterval{
private final String breakDuration;
	
	public EnhancedWithBreakInterval(Long id, String start, String end, String duration, String breakDuration) {
		super(id, start, end, duration);
		// TODO Auto-generated constructor stub
		this.breakDuration=breakDuration;
	}
	
	public String getBreakDuration() {
		return breakDuration;
	}

}
