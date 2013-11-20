package com.michingo.robovmbindings.gpp;

import org.robovm.cocoatouch.foundation.NSError;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;

@Library(Library.INTERNAL)
@NativeClass()
public class GPPShareDelegate extends NSObject{
	private static final ObjCClass objCClass = ObjCClass.getByType(GPPShareDelegate.class);

	static {
		ObjCRuntime.bind(GPPShareDelegate.class);
	}
	
	
	// Reports the status of the share action.  |error| is nil upon success.  This callback takes
	// preference over |finishedSharing:|.  You should implement one of these.
	//- (void)finishedSharingWithError:(NSError *)error;
	private static final Selector finishedSharingWithError$ = Selector.register("finishedSharingWithError:");
    @Bridge private native static void objc_finishedSharingWithError(GPPShareDelegate __self__, Selector __cmd__, NSError error);
    public void finishedSharingWithError(NSError error){
    	objc_finishedSharingWithError(this, finishedSharingWithError$, error);
    }
	

	// Reports the status of the share action, |shared| is |YES| if user has successfully shared her
	// post, |NO| otherwise, such as if the user canceled the post.  This callback is superseded by
	// |finishedSharingWithError:|.  You should implement one of these.
	//- (void)finishedSharing:(BOOL)shared;
    private static final Selector finishedSharing$ = Selector.register("finishedSharing:");
    @Bridge private native static void objc_finishedSharing(GPPShareDelegate __self__, Selector __cmd__, boolean shared);
    public void finishedSharing(boolean shared){
    	objc_finishedSharing(this, finishedSharing$, shared);
    }
}
