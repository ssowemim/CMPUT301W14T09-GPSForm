package ca.cmput301w14t09.test;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.PictureController;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This test case is for testing the picture controller, so checking if hasPicture returns true if there is really 
 * a picture and making sure the size of the image is shrunk appropriately so elastic search operations doesn't lag etc.
 * @author sswoemim
 */
public class PictureControllerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public PictureControllerTest() {
		super(TopCommentsActivity.class);
	}

	/**
	 * Checking to see if the Uri composed that will be used to save pictures doesn't end up
	 * being null.
	 * This also tests to see if a Uri is actually composed.
	 * @author ssowemim
	 */
	public void testGetOutputMediaFileUri(){
		PictureController pCTest = new PictureController();
		Uri uriTest = null;
		
		//Tests to see if the uriTest is truly  empty
		assertNull(uriTest);
		uriTest = pCTest.getOutputMediaFileUri(1);
		
		//Tests to see if a uriTest was actually generated.
		assertNotNull(uriTest);
	}

	/**
	 * The finalize method shrinks an image if the image size is too big, 
	 * so I am testing this, but sending in an image with a big size and hopefully, it shrinks it 
	 * causing the size to change.
	 * @author ssowemim
	 */
	public void testFinalizePicture(){
		PictureController pCTest = new PictureController();
		Bitmap bitmapTest = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		bitmapTest = Bitmap.createScaledBitmap(bitmapTest, 200, 200, false);
		Bitmap bitmapTest1;
		int height, width, height1, width1;

		//initialize variables
		height = bitmapTest.getHeight();
		width = bitmapTest.getWidth();
		
		//Test variables were initialized properly
		assertEquals(200, height);
		assertEquals(200, width);
		
		bitmapTest1 = pCTest.finalizePicture(bitmapTest, this.getActivity()); 
		height1 = bitmapTest1.getHeight();
		width1 = bitmapTest1.getWidth();
		
		//size of picture were shrunk appropriately.
		assertEquals(60, height1);
		assertEquals(60, width1);
		
		//Final checking to see picture size was reduced
		assertFalse(height == height1);
		assertFalse(width == width1);

	}
	
	/**
	 * This method is testing the hasPicture method.
	 * This method simply returns a boolean on if there is a picture or not.
	 * And this two cases are testing by seing in a null in finalize picture and also an actual picture.
	 * @author ssowemim
	 */
	public void testHasPicture(){
		PictureController pCTest = new PictureController();
		Bitmap bitmapTest = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		bitmapTest = Bitmap.createScaledBitmap(bitmapTest, 200, 200, false);
		Bitmap bitmapTest1, bitmapTest2 = null;
		int height, width, height1, width1;

		//initialize variables
		height = bitmapTest.getHeight();
		width = bitmapTest.getWidth();
		
		//Test variables were initialized properly
		assertEquals(200, height);
		assertEquals(200, width);
		
		bitmapTest1 = pCTest.finalizePicture(bitmapTest, this.getActivity()); 
		height1 = bitmapTest1.getHeight();
		width1 = bitmapTest1.getWidth();
		
		//size of picture were shrunk appropriately.
		assertEquals(60, height1);
		assertEquals(60, width1);
		
		//Final checking to see picture size was reduced
		assertFalse(height == height1);
		assertFalse(width == width1);
		
		//Checks to see if the hasPicture returns the appropriate response
		assertTrue(pCTest.hasPicture);

		//Checks to see if the hasPicture returns the appropriate response
		bitmapTest2 = pCTest.finalizePicture(bitmapTest2, getActivity());
		assertFalse(pCTest.hasPicture);
	}

}
