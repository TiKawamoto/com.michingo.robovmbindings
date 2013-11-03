package com.michingo.robovmbindings.gpgs;

import org.robovm.rt.bro.ValuedEnum;

public enum GPGAppStateWriteStatus implements ValuedEnum {
	GPGAppStateWriteStatusUnknownError(0),
	GPGAppStateWriteStatusSuccess(1),
	GPGAppStateWriteStatusBadKeyDataOrVersion(2),
	GPGAppStateWriteStatusKeysQuotaExceeded(3),
	GPGAppStateWriteStatusNotFound(4),
	GPGAppStateWriteStatusConflict(5),
	GPGAppStateWriteStatusSizeExceeded(6);
	
	private final int n;
	
	private GPGAppStateWriteStatus(int n) { this.n = n; }
    public int value() { return n; }
}
