import junit.framework.TestCase;

import org.junit.Test;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;


public class GeolocationTest extends TestCase {
/*
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

		Comment comment = CreateComment.newComment(lc, authorname, commentText, topComment, false);

		comment.setGeoLocation(lc.getGeoLocation());

		GeoLocation geo2 = comment.getGeoLocation();

		assertTrue(geo2.getLatitude()== 32.003 && geo2.getLongitude()==-123.233);

	}

	@Test
	public void testGeolocationupdatelargeinput() {


		LocationController lc = new LocationController();

		double lat1 = 321111111111111111111111.000000;
		double lng1 = -11111111111111111111123.00000001;

		lc.setlat(lat1);
		lc.setlng(lng1);
		lc.setGeoLocation();


		boolean topComment1 = true;
		String authorname1 = "";
		String commentText1 = "";

		Comment comment1 = CreateComment.newComment(lc, authorname1, commentText1, topComment1, false);

		comment1.setGeoLocation(lc.getGeoLocation());

		GeoLocation geo21 = comment1.getGeoLocation();

		assertTrue(geo21.getLatitude() == 321111111111111111111111.000000 && geo21.getLongitude() == -11111111111111111111123.00000001 );

	}


	@Test
	public void testGeolocationzeroupdate() {


		LocationController lc = new LocationController();

		double lat2 = 0;
		double lng2 = 0;

		lc.setlat(lat2);
		lc.setlng(lng2);
		lc.setGeoLocation();


		boolean topComment1 = true;
		String authorname1 = "";
		String commentText1 = "";

		Comment comment2 = CreateComment.newComment(lc, authorname1, commentText1, topComment1, false );

		comment2.setGeoLocation(lc.getGeoLocation());

		GeoLocation geo212 = comment2.getGeoLocation();

		Double lat33 = geo212.getLatitude();
		Double lng33 = geo212.getLongitude();

		assertTrue(lat33 == 0 && lng33 == 0);

	}

	@Test
	public void testGeolocationname() {

		GeoLocation geo213 = new GeoLocation();

		geo213.setName("mylocation");

		assertTrue(geo213.getName()== "mylocation");	

	}
*/


}