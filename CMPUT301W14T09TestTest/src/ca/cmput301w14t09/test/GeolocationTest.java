package ca.cmput301w14t09.test;

import junit.framework.TestCase;


import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;


public class GeolocationTest extends TestCase {

	public void GeolocationupdateTest() {


		LocationController lc = new LocationController();

		double lat = 32.003;
		double lng = -123.233;
		GeoLocation geo = new GeoLocation();
		geo.setLatitude(lat);
		geo.setLongitude(lng);
		lc.setGeo(geo);

		boolean topComment = true;
		String authorname = "";
		String commentText = "";

		Comment comment = CommentFactory.buildComment(lc, authorname, commentText, topComment, null, false, null);

		comment.setGeoLocation(lc.getGeoLocation());

		GeoLocation geo2 = comment.getGeoLocation();

		assertTrue(geo2.getLatitude()== 32.003 && geo2.getLongitude()==-123.233);

	}


	public void testGeolocationupdatelargeinput() {


		LocationController lc = new LocationController();

		double lat1 = 321111111111111111111111.000000;
		double lng1 = -11111111111111111111123.00000001;
		GeoLocation geo = new GeoLocation();
		geo.setLatitude(lat1);
		geo.setLongitude(lng1);
		lc.setGeo(geo);


		boolean topComment1 = true;
		String authorname1 = "";
		String commentText1 = "";

		Comment comment1 = CommentFactory.buildComment(lc, authorname1, commentText1, topComment1, null, false, null);

		comment1.setGeoLocation(lc.getGeoLocation());

		GeoLocation geo21 = comment1.getGeoLocation();

		assertTrue(geo21.getLatitude() == 321111111111111111111111.000000 && geo21.getLongitude() == -11111111111111111111123.00000001 );

	}


	public void testGeolocationzeroupdate() {


		LocationController lc = new LocationController();

		double lat2 = 0;
		double lng2 = 0;
		GeoLocation geo = new GeoLocation();
		geo.setLatitude(lat2);
		geo.setLongitude(lng2);
		lc.setGeo(geo);



		boolean topComment1 = true;
		String authorname1 = "";
		String commentText1 = "";

		Comment comment2 = CommentFactory.buildComment(lc, authorname1, commentText1, topComment1, null, false, null);

		comment2.setGeoLocation(lc.getGeoLocation());

		GeoLocation geo212 = comment2.getGeoLocation();

		Double lat33 = geo212.getLatitude();
		Double lng33 = geo212.getLongitude();

		assertTrue(lat33 == 0 && lng33 == 0);

	}

	public void testGeolocationname() {

		GeoLocation geo213 = new GeoLocation();

		geo213.setName("mylocation");

		assertTrue(geo213.getName()== "mylocation");	

	}


}