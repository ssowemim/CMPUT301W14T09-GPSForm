package ca.cmput301w14t09.test;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Model.UserProfileModel;
import ca.cmput301w14t09.Model.UserProfileModelList;
import junit.framework.TestCase;

/**
 * 
 * @author ssowemim
 *
 */
public class UserProfileModelListTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public UserProfileModelListTest(){

		super(TopCommentsActivity.class);

	}

	/**
	 * @author ssowemim
	 */
	public void testAddUserProfile(){
		
		List<UserProfileModel> list = new ArrayList<UserProfileModel>();
		//Making sure the list should be empty
		assertEquals(0, list.size());
		
		
		// This method adds the user profile to the server and also to a list. 
		UserProfileModelList testUPMList = new UserProfileModelList();
		testUPMList.addUserProfile("UNIQUEID", "Johnnie Barber", "Male", "1234567899", 
								   "bestbarber@gmail.com", "My barber shop is the best in town", 
								   BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img));
		// Checking if the model was infact added to the list
		list = testUPMList.getList();
		//Checking to see that the list of the size and changed from zero
		assertFalse(0 == list.size());
		
	}
}
