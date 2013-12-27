package com.michingo.robovmbindings.other;

import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.cocoatouch.foundation.NSString;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;

@Library("Foundation")
@NativeClass
public class NSNotification extends NSObject{
private static final ObjCClass objCClass = ObjCClass.getByType(NSNotification.class);
	
	static {
        ObjCRuntime.bind(NSNotification.class);
    }
	
	

}
