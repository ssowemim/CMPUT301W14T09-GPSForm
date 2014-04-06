package ca.cmput301w14t09.test;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Model.UserProfileModelList;
import junit.framework.TestCase;

/**
 * 
 * @author Chun-Han Lee
 *
 */
public class UserProfileModelListTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public UserProfileModelListTest()
	{

		super(TopCommentsActivity.class);

	}

	/**
	 * @author Chun-Han Lee
	 */
	public void testUserProfileModelList(){
		UserProfileModelList upml = new UserProfileModelList();
		Bitmap picture = BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		upml.addUserProfile("123", "Tester", "Unisex", "12345678", "tester@tester.test", "hahahahatest", picture);

	}
}
