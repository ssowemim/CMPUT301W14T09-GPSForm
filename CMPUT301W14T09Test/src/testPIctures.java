import ca.cmput301w14t09.model.Pictures;
import junit.framework.TestCase;


public class testPictures extends TestCase {

	public testPictures(String name) {
		super(name);
	}
	
	public void testCheckPictureExist(){
		Pictures pic= new Pictures();

		String picName = "TestingPictures";

		pic.setPictureName(picName);
        pic.setPictureContent();

		String picGetName = area.getPictureName();

		assertTrue(picGetName.equals(picName));
        /**
         pictureUploaded is a method, that return true or false if .setPictureContent fully uploaded and 
         saved a picture with errors or failures.
         **/
        assertTrue(pic.pictureUploaded())
	}
}
