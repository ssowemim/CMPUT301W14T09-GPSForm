import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import ca.cmput301w14t09.TopCommentsActivity;


public class PictureTest extends TestCase{


	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testDeviceCompatible() {
		//Checks if device is compatible with taking pictures
		TopCommentsActivity pictureActivity = new TopCommentsActivity();
		assertTrue(pictureActivity.isDeviceSupportCamera());
	}
	
	@Test
	public void testSavePictures() {
		TopCommentsActivity pictureActivity = new TopCommentsActivity();
		
		//assertFalse(pictureActivity.getOutputMediaFileUri(1).equals(null));
	}

}
