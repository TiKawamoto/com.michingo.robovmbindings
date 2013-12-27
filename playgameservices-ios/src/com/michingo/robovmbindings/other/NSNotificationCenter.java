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
public class NSNotificationCenter extends NSObject{
private static final ObjCClass objCClass = ObjCClass.getByType(NSNotificationCenter.class);
	
	static {
        ObjCRuntime.bind(NSNotificationCenter.class);
    }
	
	//+ (id)defaultCenter
	private static final Selector defaultCenter$ = Selector.register("defaultCenter");
    @Bridge private native static NSNotificationCenter objc_defaultCenter(ObjCClass __self__, Selector __cmd__);
    public static NSNotificationCenter defaultCenter() {
        return objc_defaultCenter(objCClass, defaultCenter$);
    }
    
    //- (void)addObserver:(id)notificationObserver selector:(SEL)notificationSelector name:(NSString *)notificationName object:(id)notificationSender
    private static final Selector addObserver$ = Selector.register("addObserver:selector:name:object:");
    @Bridge private native static void objc_addObserver(NSNotificationCenter __self__, Selector __cmd__, NSObject notificationObserver, Selector notificationSelector, NSString notificationName, NSObject notificationSender);
    public void addObserver(NSObject observer, Selector notificationSelector, String notificationName) {
        objc_addObserver(this, addObserver$, observer, notificationSelector, new NSString(notificationName), null);
    }

    //- (void)postNotificationName:(NSString *)notificationName object:(id)notificationSender
    private static final Selector postNotificationName$ = Selector.register("postNotificationName:object:");
    @Bridge private native static void objc_postNotificationName(NSNotificationCenter __self__, Selector __cmd__, NSString name, NSObject notificationSender);
    public void postNotificationName(String name, NSObject notificationSender) {
        objc_postNotificationName(this, postNotificationName$, new NSString(name), notificationSender);
    }
}
