package ca.cmput301w14t09.test;

import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.elasticSearch.Server;
import android.test.ActivityInstrumentationTestCase2;

/**
 * 
 * @author Chun-Han Lee
 *
 */
public class ServerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public ServerTest() {
		super(TopCommentsActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	/**
	 * @author Chun-Han Lee
	 */
	public void testGetInstance(){
		Server server=Server.getInstance();
		assertNotNull(server);
	}

}