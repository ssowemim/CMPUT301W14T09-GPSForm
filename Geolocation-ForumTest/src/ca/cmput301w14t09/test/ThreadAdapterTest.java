package ca.cmput301w14t09.test;

import java.util.ArrayList;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.ThreadAdapter;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This test cases tests the ThreadAdapter java model class
 * @author Cameron Alexander & ssowemim
 *
 */
public class ThreadAdapterTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public ThreadAdapterTest() {
		super(TopCommentsActivity.class);
	}
	
	/**
	 * This test cases tests the threadAdapter to see if it can be created successfully
	 * @author Cameron Alexander
	 */
	public void testThreadAdapter(){
		ArrayList<Comment> comments = new ArrayList<Comment>(200);
		GeoLocation geo = new GeoLocation();
		LocationController lc = new LocationController();
		Context context = this.getActivity().getApplicationContext();
		SortingController sorting = new SortingController();
        ArrayList<Comment> sortedList = sorting.sortTopComments(lc, geo, comments);
        ThreadAdapter adapter1 = new ThreadAdapter(context,R.layout.thread_view, sortedList);
        
        assertNotNull(adapter1);
		
	}



}
