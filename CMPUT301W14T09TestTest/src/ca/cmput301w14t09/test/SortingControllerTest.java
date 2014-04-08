package ca.cmput301w14t09.test;

import java.util.ArrayList;

import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author Chun-Han Lee
 * Testing Sorting for comments
 */
public class SortingControllerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public SortingControllerTest(){
		super(TopCommentsActivity.class);
	}
	
	/**
	 * Create two comments with to unique location, create a third location (0,0) and 
	 * make a comparison based on that gps location
	 * @author Chun-Han Lee & ssowemim
	 */
	public void testSortingCommentsByLocation(){
		Comment comment = null, comment1 = null;
		LocationController lc = new LocationController();
		LocationController lc1 = new LocationController();
		LocationController lc2 = new LocationController();
		
		//Creating resources that will be used to make a comment
		GeoLocation geo = new GeoLocation();
		ArrayList<Comment> commList = new ArrayList<Comment>();
		SortingController sc = new SortingController();
		Bitmap bitmap =  BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		SerializableBitmap sBitmap = new SerializableBitmap(bitmap);
		
		geo.setLatitude(77.0);
		geo.setLongitude(89.0);
		lc1.setGeo(geo);
		
		comment = CommentFactory.buildComment(lc1, "test1", "testing1", true, sBitmap, true, "username");
		commList.add(comment);

		geo.setLatitude(25.0);
		geo.setLongitude(12.0);
		lc2.setGeo(geo);
		
		comment1 = CommentFactory.buildComment(lc2, "test2", "testing2", true, sBitmap, true, "username1");
		commList.add(comment1);

		geo.setLongitude(0);
		geo.setLatitude(0);
		lc.setGeo(geo);
		
		// names are put into the list as follows
		assertTrue(commList.get(0).getAuthorName().equals("test1"));
		assertTrue(commList.get(1).getAuthorName().equals("test2"));
		
		ArrayList<Comment> testList = sc.sortTopComments(lc, geo, commList);
		String name = testList.get(0).getAuthorName();
		String name2 = testList.get(1).getAuthorName();

		// after sorting names are now reversed due to their comments location
		assertEquals("test2", name);
		assertEquals("test1", name2);
		commList.clear();
		testList.clear();
	}
	
	/**
	 * Creates two unique comments and puts them into a list, it now simply runs the list through
	 * the sorting picture algorithm and returns another list in the right order of pictures being sorted
	 * or not.
	 * @author ssowemim
	 */
	public void testSortingPicture(){
		Comment comment = null, comment1 = null;
		LocationController lc1 = new LocationController();
		
		//Initialize variables
		GeoLocation geo = new GeoLocation();
		ArrayList<Comment> commList = new ArrayList<Comment>();
		SortingController sc = new SortingController();
		Bitmap bitmap =  BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		SerializableBitmap sBitmap = new SerializableBitmap(bitmap);
		
		geo.setLatitude(20.0);
		geo.setLongitude(10.0);
		lc1.setGeo(geo);
		
		//Building comments
		comment = CommentFactory.buildComment(lc1, "test1", "testing1", true, null, false, "username");
		commList.add(comment);
		
		comment1 = CommentFactory.buildComment(lc1, "test2", "testing2", true, sBitmap, true, "username1");
		commList.add(comment1);
	
		// names are put into the list as follows
		assertEquals("test1",commList.get(0).getAuthorName());
		assertEquals("test2",commList.get(1).getAuthorName());
		
		ArrayList<Comment> testList = sc.sortPicTopComments(commList);
		String name = testList.get(0).getAuthorName();
		String name2 = testList.get(1).getAuthorName();

		// after sorting names are now reversed due to them having picture or not
		assertEquals("test2", name);
		assertEquals("test1", name2);
	}
	}

