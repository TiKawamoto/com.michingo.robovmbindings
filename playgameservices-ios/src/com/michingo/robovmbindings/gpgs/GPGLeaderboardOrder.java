package com.michingo.robovmbindings.gpgs;

import org.robovm.rt.bro.ValuedEnum;

/** 
 * @author Michael Hadash */

public enum GPGLeaderboardOrder implements ValuedEnum{
	GPGLeaderboardOrderUnknown(0),
	GPGLeaderboardOrderLargerIsBetter(1),
	GPGLeaderboardOrderSmallerIsBetter(2);

    private final int n;

    private GPGLeaderboardOrder(int n) {
        this.n = n;
    }

    public int value() {
        return n;
    }

}
