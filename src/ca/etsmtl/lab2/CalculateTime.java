package ca.etsmtl.lab2;

public class CalculateTime {
	
	private long lStartTime;
	private long lEndTime;	 
	
	
	public CalculateTime(){
		
	}
	
	public void startTimer(){
		lStartTime = System.currentTimeMillis();
	}
	
	public void endTimer(){
		lEndTime = System.currentTimeMillis();
		System.out.println(getTimeDifference());
	}
	
	public String getTimeDifference(){
		long difference = lEndTime - lStartTime;
		
		return difference+" milliseconds";
	}

}
