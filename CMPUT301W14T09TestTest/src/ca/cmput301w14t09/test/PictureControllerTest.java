package ca.cmput301w14t09.test;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.PictureController;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee, sswoemim
 *
 */
public class PictureControllerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public PictureControllerTest(String name) {
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
	
	public void testPreviewCapturedImage(){
		PictureController pCTest = new PictureController();
		ImageView iVTest= new ImageView(null);
		
		//iVTest (ImageView) should have nothing so the .getDrawable() method should return null
		assertTrue(iVTest.getDrawable() == null);
		
		Bitmap bitmapTest = null;
		//BitmapFactory.Options options = new BitmapFactory.Options();
		Uri uriTest;
		
		uriTest = pCTest.getOutputMediaFileUri(1);
		bitmapTest = pCTest.previewCapturedImage(uriTest, bitmapTest, iVTest);
		assertNotNull(bitmapTest);
	//	iVTest.setImageBitmap(bitmapTest);
	//	assertTrue(iVTest.getDrawable() != null);
		
		
		
		
	}

}
