package com.michingo.robovmbindings.other;

import java.nio.ByteBuffer;
import org.robovm.cocoatouch.foundation.NSObject;
import org.robovm.objc.ObjCClass;
import org.robovm.objc.ObjCRuntime;
import org.robovm.objc.Selector;
import org.robovm.objc.annotation.NativeClass;
import org.robovm.rt.bro.Struct;
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
	
	private BytePtr pointer;
	
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
		
		//free the old pointer
		free();
		
		//allocate memory and create a new pointer
		pointer = Struct.malloc(BytePtr.class, buffer.capacity());
		
		//create a buffer that handles the exact same memory as the pointer
		ByteBuffer bufTemp = pointer.asByteBuffer(buffer.capacity());
		
		//reset buffer positions
		buffer.position(0);
		bufTemp.position(0);
		
		//copies the data of the buffer to the pointer using the temporary buffer.
		bufTemp.put(buffer);
		
		//call the objective-c class. Converts the pointer to a void pointer since this class requires a (const void *)
		objc_initWithBytes(this, initWithBytes$, pointer.as(VoidPtr.class), buffer.capacity());
	}
	
	//- (void)getBytes:(void *)buffer length:(NSUInteger)length;
	private static final Selector getBytes$ = Selector.register("getBytes:length:");
	@Bridge private native static void objc_getBytes(NSData __self__, Selector __cmd__, VoidPtr buffer, int length);
	public ByteBuffer getBytes() {
		
		//get the length of the data
		int len = length();
		
		//create a new void pointer and allocate memory
		VoidPtr p = Struct.malloc(VoidPtr.class, len);
		
		//call the objective-c class. This writes the bytes to the pointer's memory
		objc_getBytes(this, getBytes$, p, len);
		
		//convert the void pointer to a byte pointer
		BytePtr b = p.as(BytePtr.class);
		
		//create a ByteBuffer that handles the data of the pointer.
		ByteBuffer bufPointer = b.asByteBuffer(len);
		
		//create another buffer
		ByteBuffer bufOther = ByteBuffer.allocate(len);
		
		//reset buffer positions
		bufPointer.position(0);
		bufOther.position(0);
		
		//copy the data from the pointer to this buffer.
		bufOther.put(bufPointer);
		
		//free the memory used by the pointer
		p.free();
		
		//return the newly created buffer
		return bufOther;
	}
	
	public void free(){
		if (pointer!=null){
			pointer.free();
			pointer = null;
		}
	}
}
