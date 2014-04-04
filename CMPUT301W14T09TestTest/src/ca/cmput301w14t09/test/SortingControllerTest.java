package ca.cmput301w14t09.test;

import java.util.ArrayList;

import junit.framework.TestCase;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author chunhan
 * Testing Sorting for comments
 */
public class SortingControllerTest extends TestCase {

	public void testSortingCommentsByLocation(){
		Comment comment = null;
		LocationController lc = new LocationController();
		GeoLocation geo = new GeoLocation();
		ArrayList<Comment> commList = new ArrayList<Comment>();
		SortingController sc = new SortingController();
		
		geo.setLongitude(34.0);
		geo.setLatitude(-111.0);
		lc.setGeo(geo);
		comment = CommentFactory.buildComment(lc, "test1", "testing1", true, null, null);
		commList.add(comment);
		
		geo.setLongitude(35.0);
		geo.setLatitude(-1.0);
		lc.setGeo(geo);
		comment = CommentFactory.buildComment(lc, "test2", "testing2", true, null, null);
		commList.add(comment);

		geo.setLatitude(54.0);
		geo.setLongitude(23.0);
		lc.setGeo(geo);
		ArrayList<Comment> testList = sc.sortTopComments(lc, null, commList);
		String name = testList.get(0).getAuthorName();
		String name2 = testList.get(1).getAuthorName();
		assertTrue(name.contains("test1"));
		assertTrue(name2.contains("test2"));
	}
}
