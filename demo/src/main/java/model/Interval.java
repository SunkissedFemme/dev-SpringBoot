package model;

/*Base class for creating an interval. */

public abstract class Interval {
	
	private final Long id;
    private final String start;
	private final String end;
    
    public Interval(
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
    
   

}
