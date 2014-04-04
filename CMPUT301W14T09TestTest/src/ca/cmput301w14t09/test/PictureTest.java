package ca.cmput301w14t09.test;


import android.graphics.Bitmap;
import android.test.ActivityInstrumentationTestCase2;
import junit.framework.TestCase;



import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.Model.Picture;


public class PictureTest extends ActivityInstrumentationTestCase2<TopCommentsActivity>{


	public PictureTest() {
		super(TopCommentsActivity.class);
	}

	public void testFinalizePicture(){
		PictureController pc = new PictureController();
		Bitmap bp = pc.finalizePicture(null, getActivity());
		assertNull(bp);
	}

}
