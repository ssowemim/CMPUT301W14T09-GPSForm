package ca.cmput301w14t09.test;

import android.test.ActivityInstrumentationTestCase2;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Model.GeoLocation;


/**
 * Testing if the initializing of the longitude and latitude works perfectly, 
 * with along of getting proper outputs.
 * @author Cameron Alexander & ssowemim
 */
public class LocationControllerTests extends ActivityInstrumentationTestCase2<TopCommentsActivity>{
	public LocationControllerTests() {
		super(TopCommentsActivity.class);
	}


	/**
	 * Testing if geolocation receives its input properly.
	 * @author Cameron Alexander & ssowemim
	 */
	public void testGetGeolocation(){

		LocationController locationcontroller = new LocationController();

		//checking if initializing of variable works fine.
		GeoLocation geo2 = null;
		assertNull(geo2);
		
		geo2 = locationcontroller.getGeoLocation();
		assertNotNull(geo2);
	}

	/**
	 * Check dafault vs selected location, if selected location exists use it if not use default
	 * @author Cameron Alexander & ssowemim 
	 */
	public void testCheckLocations(){

		GeoLocation geo = new GeoLocation();

		GeoLocation geo2 = new GeoLocation();
		geo2.setLatitude(4.234);
		geo2.setLongitude(5.434);
		
		//Testing if geo2 was set properly
		assertEquals(4.234, geo2.getLatitude());
		assertEquals(5.434, geo2.getLongitude());

		GeoLocation selectedgeo = new GeoLocation();
		selectedgeo.setLatitude(9.345);
		selectedgeo.setLongitude(10.234);
		
		//Testing if selectedgeo was set properly
		assertEquals(9.345, selectedgeo.getLatitude());
		assertEquals(10.234, selectedgeo.getLongitude());

		LocationController locationcontroller = new LocationController();
		locationcontroller.setGeo(geo);
		locationcontroller.setGeodefault(geo2.getLatitude(), geo2.getLongitude());

		locationcontroller.checklocations(selectedgeo);

		//since selected location not 0.0 0.0 the geolocation used Geo should equal selected
		assertTrue(locationcontroller.getGeoLocation() == selectedgeo);

		selectedgeo.setLatitude(0.0);
		selectedgeo.setLongitude(0.0);
		
		//Testing if selectedgeo was set properly
		assertEquals(0.0, selectedgeo.getLatitude());
		assertEquals(0.0, selectedgeo.getLongitude());

		locationcontroller.checklocations(selectedgeo);

		//since the selected location is 0.0 default location should be used which right now is equal to geo2
		assertFalse(locationcontroller.getGeoLocation() == geo2);	

	}

	/**
	 * testing to see if resetting the selected location works just as implmented
	 * @author Cameron Alexander & ssowemim
	 */
	public void testResetSelectedLocation(){

		//Initializes variables
		LocationController locationcontroller = new LocationController();
		GeoLocation selectedgeo = new GeoLocation();
		selectedgeo.setLatitude(9.345);
		selectedgeo.setLongitude(10.234);

		//Making sure that the location actually changes
		assertEquals(9.345, selectedgeo.getLatitude());
		assertEquals(10.234, selectedgeo.getLongitude());
		
		locationcontroller.resetselectedlocation(selectedgeo);

		//Making sure that the location has actually been reset to 0.
		assertTrue(selectedgeo.getLatitude() == 0);
		assertTrue(selectedgeo.getLongitude() == 0);

	}
}
