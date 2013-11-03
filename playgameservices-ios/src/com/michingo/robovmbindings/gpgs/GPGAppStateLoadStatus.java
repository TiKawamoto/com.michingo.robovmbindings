package com.michingo.robovmbindings.gpgs;

import org.robovm.rt.bro.ValuedEnum;

//TODO: test this
public enum GPGAppStateLoadStatus implements ValuedEnum {
	GPGAppStateLoadStatusUnknownError(0),
	GPGAppStateLoadStatusSuccess(1),
	GPGAppStateLoadStatusNotFound(2);
	
	private final int n;
	
	private GPGAppStateLoadStatus(int n) { this.n = n; }
    public int value() { return n; }
}
