package com.eventManager.utils;

public class LastURLVisited {
	private String lastUrl ="";
	private static volatile LastURLVisited instance = null;
	
	private LastURLVisited() {}
	
	public final static LastURLVisited getInstance(){
		if(LastURLVisited.instance == null) {
			synchronized (LastURLVisited.class) {
				if(LastURLVisited.instance == null) {
					LastURLVisited.instance = new LastURLVisited();
				}
			}
		}
		return LastURLVisited.instance;
	}
		
	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}

}
