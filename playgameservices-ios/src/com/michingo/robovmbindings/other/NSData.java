package com.michingo.robovmbindings.other;

import java.nio.ByteBuffer;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.ptr.BytePtr;
import org.robovm.rt.bro.ptr.VoidPtr;

@Library("Foundation")
@NativeClass()
public class NSData extends NSObject{
	private static final ObjCClass objCClass = ObjCClass.getByType(NSData.class);

	static {
		ObjCRuntime.bind(NSData.class);
	}
	
	//- (NSUInteger)length;
	private static final Selector length$ = Selector.register("length");
	@Bridge private native static int objc_length(NSData __self__, Selector __cmd__);
	public int length() {
		return objc_length(this, length$);
	}
	
	//- (const void *)bytes NS_RETURNS_INNER_POINTER;
	private static final Selector bytes$ = Selector.register("bytes");
	@Bridge private native static VoidPtr objc_bytes(NSData __self__, Selector __cmd__);
	public ByteBuffer getBytes2() {
		VoidPtr p = objc_bytes(this, bytes$);
		BytePtr b = p.as(BytePtr.class);
		return b.asByteBuffer(length());
	}
	
	//- (id)initWithBytes:(const void *)bytes length:(NSUInteger)length;
	private static final Selector initWithBytes$ = Selector.register("initWithBytes:length:");
	@Bridge private native static NSData objc_initWithBytes(NSData __self__, Selector __cmd__, VoidPtr bytes, int length);
	public NSData(final ByteBuffer buffer) {
		BytePtr b = new BytePtr();
		ByteBuffer bufTemp = b.asByteBuffer(buffer.capacity());
		bufTemp.put(buffer);
		objc_initWithBytes(this, initWithBytes$, b.as(VoidPtr.class), buffer.capacity());
	}
	
	//- (void)getBytes:(void *)buffer length:(NSUInteger)length;
	private static final Selector getBytes$ = Selector.register("getBytes:length:");
	@Bridge private native static void objc_getBytes(NSData __self__, Selector __cmd__, VoidPtr buffer, int length);
	public ByteBuffer getBytes() {
		int len = length();
		VoidPtr p = new VoidPtr();
		objc_getBytes(this, getBytes$, p, len);
		BytePtr b = p.as(BytePtr.class);
		return b.asByteBuffer(len);
	}
}
