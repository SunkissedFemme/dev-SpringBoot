package model;

public class EnhancedInterval extends SimpleInterval {
	private final String duration;

	
	public EnhancedInterval(Long id, String start, String end, String duration) {
		super(id, start, end);
		// TODO Auto-generated constructor stub
		this.duration=duration;
	}
	
	public String getDuration() {
		return duration;
	}


}
