import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CreateComment;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.GeoLocation;


public class GeolocationTest {

	@Test
	public void GeolocationupdateTest() {
		
		
		LocationController lc = new LocationController();
		
		double lat = 32.003;
		double lng = -123.233;
		
		lc.setlat(lat);
		lc.setlng(lng);
		lc.setGeoLocation();
		
		
		boolean topComment = true;
		String authorname = "";
		String commentText = "";
		
		Comment comment = CreateComment.newComment(lc, authorname, commentText, topComment);
		
		comment.setGeoLocation(lc.getGeoLocation());
		
		GeoLocation geo2 = comment.getGeoLocation();
		
		double lat2 = geo2.getLatitude();
		double lng2 = geo2.getLongitude();

		assertTrue(lat == lat2 && lng == lng2);
		
	}
		
		@Test
		public void Geolocationinputtest() {
			
			
			LocationController lc = new LocationController();
			
			double lat1 = 321111111111111111111111.000000;
			double lng1 = -11111111111111111111123.00000001;
			
			lc.setlat(lat1);
			lc.setlng(lng1);
			lc.setGeoLocation();
			
			
			boolean topComment1 = true;
			String authorname1 = "";
			String commentText1 = "";
			
			Comment comment1 = CreateComment.newComment(lc, authorname1, commentText1, topComment1);
			
			comment1.setGeoLocation(lc.getGeoLocation());
			
			GeoLocation geo21 = comment1.getGeoLocation();
			
			double lat21 = geo21.getLatitude();
			double lng21 = geo21.getLongitude();

			assertTrue(lat1 == lat21 && lng1 == lng21);
			
		}
		
		
		
   }