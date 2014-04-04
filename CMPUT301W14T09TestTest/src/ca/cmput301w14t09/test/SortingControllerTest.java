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
		lc.setGeo(geo);
		
		for (int i = 0; i< 4; i++){
			geo.setLatitude(i);
			lc.setGeo(geo);
			comment = CommentFactory.buildComment(lc, "test"+i, "testing"+i, true, null, null);
			commList.add(comment);
		}
		geo.setLatitude(54.0);
		ArrayList<Comment> testList = sc.sortTopComments(null, geo, commList);
		String name = testList.get(0).getAuthorName();
		assertTrue(name.equals("test3"));
	}
}
