package ca.cmput301w14t09.test;

import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import android.test.ActivityInstrumentationTestCase2;


public class SortingControllerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public SortingControllerTest(Class<TopCommentsActivity> name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	public void testSortingByDefaultLocation(){
		Comment comment = null;
		LocationController lc = new LocationController();
		for (int i = 0; i< 4; i++){
			comment = CommentFactory.buildComment(lc, "test"+i, "testing"+i, true, null);
			try {
				ElasticSearchOperations.postThread(comment);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			assertNotNull(ElasticSearchOperations.pullThreads());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
