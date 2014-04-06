package ca.cmput301w14t09.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee, sswoemim
 * Testing for serializedbitmap
 * Two public methods are required for the class and there is no way to test 
 * the methods.
 *
 */
public class SerializableBitmapTest extends ActivityInstrumentationTestCase2<TopCommentsActivity>
{
	public SerializableBitmapTest()
	{

		super(TopCommentsActivity.class);

	}

	public void testSerializeBitmap(){
		Bitmap picture = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		SerializableBitmap sbp = new SerializableBitmap(picture);

		assertNotNull(sbp);
	}

}
