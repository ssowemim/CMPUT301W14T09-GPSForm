package ca.cmput301w14t09.test;


import junit.framework.TestCase;



import ca.cmput301w14t09.TopCommentsActivity;


public class PictureTest extends TestCase{


	public void setUp() throws Exception {
	}


	public void testDeviceCompatible() {
		//Checks if device is compatible with taking pictures
		TopCommentsActivity pictureActivity = new TopCommentsActivity();
		//assertTrue(pictureActivity.isDeviceSupportCamera());
	}
	

	public void testSavePictures() {
		TopCommentsActivity pictureActivity = new TopCommentsActivity();
		
		//assertFalse(pictureActivity.getOutputMediaFileUri(1).equals(null));
	}

}
