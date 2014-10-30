package com.eventManager.utils;

public final class LastActionRealised {
	private String lastAction ="";
	private static volatile LastActionRealised instance = null;
	
	private LastActionRealised() {}
	
	public final static LastActionRealised getInstance(){
		if(LastActionRealised.instance == null) {
			synchronized (LastActionRealised.class) {
				if(LastActionRealised.instance == null) {
					LastActionRealised.instance = new LastActionRealised();
				}
			}
		}
		return LastActionRealised.instance;
	}
		
	public String getLastAction() {
		return lastAction;
	}

	public void setLastAction(String lastAction) {
		this.lastAction = lastAction;
	}

}
