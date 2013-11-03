package com.michingo.robovmbindings.gpgs;

import org.robovm.rt.bro.ValuedEnum;

/** 
 * @author Michael Hadash */

public enum GPGRevisionStatus implements ValuedEnum{
	GPGRevisionStatusUnknown(0),
	GPGRevisionStatusOK(1),
	GPGRevisionStatusDeprecated(2),
	GPGRevisionStatusInvalid(3);

    private final int n;

    private GPGRevisionStatus(int n) {
        this.n = n;
    }

    public int value() {
        return n;
    }

}
