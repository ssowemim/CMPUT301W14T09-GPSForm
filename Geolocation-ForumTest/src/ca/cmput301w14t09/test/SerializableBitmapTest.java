package ca.cmput301w14t09.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;

/**
 * 
 * @author Chun-Han Lee, sswoemim
 * Testing for serializedbitmap
 * Two public methods are required for the class and there is no way to test 
 * those methods.
 *
 */
public class SerializableBitmapTest extends ActivityInstrumentationTestCase2<TopCommentsActivity>{
	public SerializableBitmapTest(){
		super(TopCommentsActivity.class);
	}

	/**
	 * This testing that the serializeable bitmap is actually working perfectly
	 * @author ssowemim
	 */
	public void testSerializeBitmap(){
		Bitmap picture = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		SerializableBitmap sbp = new SerializableBitmap(picture);
		
		//This test checking that sbp isn't simply a picture and is actually serialized.
		assertNotSame(picture, sbp);
		
		/*
		 * This checking that after we get the serialized picture and convert it back into
		 *  a bitmap it equals the first bitmap picture
		 */
		assertEquals(picture, sbp.bitmap);
		
	}

}
