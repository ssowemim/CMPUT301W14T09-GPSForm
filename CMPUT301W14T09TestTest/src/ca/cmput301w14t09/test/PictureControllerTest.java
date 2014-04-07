package ca.cmput301w14t09.test;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.PictureController;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;

/**
 * 
 * @author sswoemim
 *
 */
public class PictureControllerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public PictureControllerTest() {
		super(TopCommentsActivity.class);
	}

	/**
	 * Checking to see if the Uri composed that will be used to save pictures doesn't end up
	 * being null.
	 * This also tests to see if a Uri is actually composed.
	 */
	public void testGetOutputMediaFileUri(){
		PictureController pCTest = new PictureController();
		Uri uriTest = null;
		uriTest = pCTest.getOutputMediaFileUri(1);
		assertNotNull(uriTest);
		
	}
	
	/**
	 * Purpose of preview captured image is simply putting a picture onto an imageview.
	 */
	@SuppressWarnings("null")
	/**public void testPreviewCapturedImage(){
		PictureController pCTest = new PictureController();
		ImageView iVTest;
		
		//assertTrue(iVTest.getDrawable() == null);
		
		Bitmap bitmapTest = null;
		Uri uriTest;
		
		/**
		 * bitmapTest is set to null going in, but if it comes out being not null then we know
		 * previewCaptured worked. 
		 */
	/**	uriTest = pCTest.getOutputMediaFileUri(1);
	//	bitmapTest = pCTest.previewCapturedImage(uriTest, bitmapTest, iVTest);
		
		assertNull(bitmapTest);
		//assertTrue(iVTest.getDrawable() == null);

	} **/

	/**
	 * The finalize method shrinks an image if the image size is too big, 
	 * so I am testing this, but sending in an image with a big size and hopefully, it shrinks it 
	 * causing the size to change.
	 * 
	 */
	public void testFinalizePicture(){
		PictureController pCTest = new PictureController();
		Bitmap bitmapTest = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		bitmapTest = Bitmap.createScaledBitmap(bitmapTest, 200, 200, false);
		Bitmap bitmapTest1;
		int height, width, height1, width1;

		height = bitmapTest.getHeight();
		width = bitmapTest.getWidth();
		bitmapTest1 = pCTest.finalizePicture(bitmapTest, this.getActivity()); 
		height1 = bitmapTest1.getHeight();
		width1 = bitmapTest1.getWidth();
		
		assertFalse(height == height1);
		assertFalse(width == width1);

	}
	
	/**
	 * This method is testing the hasPicture method.
	 * This method simply returns a boolean on if there is a picture or not.
	 * And this two cases are testing by seing in a null in finalize picture and also an actual picture.
	 */
	public void testHasPicture(){
		PictureController pCTest = new PictureController();
		Bitmap bitmapTest = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		bitmapTest = Bitmap.createScaledBitmap(bitmapTest, 200, 200, false);
		Bitmap bitmapTest1, bitmapTest2 = null;
		int height, width, height1, width1;

		height = bitmapTest.getHeight();
		width = bitmapTest.getWidth();
		bitmapTest1 = pCTest.finalizePicture(bitmapTest, this.getActivity()); 
		height1 = bitmapTest1.getHeight();
		width1 = bitmapTest1.getWidth();
		
		assertFalse(height == height1);
		assertFalse(width == width1);
		
		assertTrue(pCTest.hasPicture);

		bitmapTest2 = pCTest.finalizePicture(bitmapTest2, getActivity());
		assertFalse(pCTest.hasPicture);
	}

}
