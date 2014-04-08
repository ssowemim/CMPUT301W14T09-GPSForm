package ca.cmput301w14t09.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
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
	 * @author Chun-Han Lee
	 */
	public void testSortingCommentsByLocation(){
		Comment comment = null, comment1 = null;
		LocationController lc = new LocationController();
		LocationController lc1 = new LocationController();
		LocationController lc2 = new LocationController();
		
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
		
		ArrayList<Comment> testList = sc.sortTopComments(lc, null, commList);
		String name = testList.get(0).getAuthorName();
		String name2 = testList.get(1).getAuthorName();
		
		// names are put into the list as follows
		assertTrue(commList.get(0).getAuthorName().equals("test1"));
		assertTrue(commList.get(1).getAuthorName().equals("test2"));

		// after sorting names are now reversed due to their comments location
		assertEquals("test2", name);
		assertEquals("test1", name2);
		commList.clear();
		testList.clear();
	}
	
	/**
	 * @author ssowemim
	 */
	public void testSortingPicture(){
		Comment comment = null, comment1 = null;
		LocationController lc = new LocationController();
		LocationController lc1 = new LocationController();
		LocationController lc2 = new LocationController();
		
		GeoLocation geo = new GeoLocation();
		ArrayList<Comment> commList = new ArrayList<Comment>();
		SortingController sc = new SortingController();
		Bitmap bitmap =  BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.no_img);
		SerializableBitmap sBitmap = new SerializableBitmap(bitmap);
		
		geo.setLatitude(20.0);
		geo.setLongitude(10.0);
		lc1.setGeo(geo);
		
		comment = CommentFactory.buildComment(lc1, "test1", "testing1", true, sBitmap, false, "username");
		commList.add(comment);

		geo.setLatitude(25.0);
		geo.setLongitude(12.0);
		lc2.setGeo(geo);
		
		comment1 = CommentFactory.buildComment(lc2, "test2", "testing2", true, sBitmap, true, "username1");
		commList.add(comment1);

		geo.setLongitude(0);
		geo.setLatitude(0);
		lc.setGeo(geo);
		
		ArrayList<Comment> testList = sc.sortPicTopComments(commList);
		String name = testList.get(0).getAuthorName();
		String name2 = testList.get(1).getAuthorName();
		
		// names are put into the list as follows
		assertTrue(commList.get(0).getAuthorName().equals("test2"));
		assertTrue(commList.get(1).getAuthorName().equals("test1"));

		// after sorting names are now reversed due to their comments location
		assertEquals("test2", name);
		assertEquals("test1", name2);
	}
	}

