package org.kepennar.facemash.mvc.model;

public class Vote {

	

	private String winnerId;
	private String loserId;
	
	public Vote() { }
	
	public Vote(String winnerId, String loserId) {
		super();
		this.winnerId = winnerId;
		this.loserId = loserId;
	}

	public String getWinnerId() {
		return winnerId;
	}
	public void setWinnerId(String winnerId) {
		this.winnerId = winnerId;
	}
	public String getLoserId() {
		return loserId;
	}
	public void setLoserId(String loserId) {
		this.loserId = loserId;
	}
	
	
}
