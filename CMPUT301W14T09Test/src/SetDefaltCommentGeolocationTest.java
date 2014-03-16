import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.GeoLocation;


public class SetDefaltCommentGeolocationTest {

	@Test
	public void SetDefaultCommentGeolocationTest() {
		
		
		LocationController lc = new LocationController();
		
		double lat = 32.003;
		double lng = -123.233;
		
		lc.setlat(lat);
		lc.setlng(lng);
		lc.setGeoLocation();
		
		
		boolean topComment = true;
		String authorname = "cam";
		String commentText = "Hello my name is Cameron";
		
		Comment comment = CreateComment.newComment(lc, authorname, commentText, topComment);
		
		comment.setGeoLocation(lc.getGeoLocation());
		
		GeoLocation geo2 = comment.getGeoLocation();
		
		double lat2 = geo2.getLatitude();
		double lng2 = geo2.getLongitude();

		assertTrue(lat == lat2 && lng == lng2);
			

   }
}