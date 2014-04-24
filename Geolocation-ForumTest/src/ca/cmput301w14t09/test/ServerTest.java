package ca.cmput301w14t09.test;

import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.elasticSearch.Server;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This test case just simply tests the server
 * @author Chun-Han Lee & ssowemim
 */
public class ServerTest extends ActivityInstrumentationTestCase2<TopCommentsActivity> {

	public ServerTest() {
		super(TopCommentsActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	/**
	 * Testing to see if an instance of a server is actually received
	 * @author Chun-Han Lee & ssowemim
	 */
	public void testGetInstance(){
		Server server= null;
		assertNull(server);
		
		server = Server.getInstance();
		assertNotNull(server);
	}

}
