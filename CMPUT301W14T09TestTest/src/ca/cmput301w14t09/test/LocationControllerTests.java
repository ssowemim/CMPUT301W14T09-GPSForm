package ca.cmput301w14t09.test;


import junit.framework.TestCase;


import android.location.Location;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Model.GeoLocation;


/**
 *Author: Cameron Alexander 
 * 
 **/


public class LocationControllerTests extends TestCase{


	public void testGetGeolocation()
	{

		LocationController locationcontroller = new LocationController();
		GeoLocation geo2 = locationcontroller.getGeoLocation();

		assertTrue(geo2 == locationcontroller.getGeoLocation());

	}



	public void testLocationChanged(){

		LocationController locationcontroller = new LocationController();
		Location location = new Location("dummyprovider");
		location.setLatitude(-53.2345);
		location.setLongitude(103.44566);
		assertTrue(location.getLatitude() == -53.2345);
		locationcontroller.locationchanged(location);

		assertTrue(locationcontroller.getGeodefault().getLatitude() == -53.2345);

	}


	public void testCheckLocations(){

		GeoLocation geo = new GeoLocation();

		GeoLocation geo2 = new GeoLocation();
		geo2.setLatitude(4.234);
		geo2.setLongitude(5.434);

		GeoLocation selectedgeo = new GeoLocation();
		selectedgeo.setLatitude(9.345);
		selectedgeo.setLongitude(10.234);

		LocationController locationcontroller = new LocationController();
		locationcontroller.setGeo(geo);
		locationcontroller.setGeodefault(geo2);

		locationcontroller.checklocations(selectedgeo);

		//since selected location not 0.0 0.0 the geolocation used Geo should equal selected
		assertTrue(locationcontroller.getGeo() == selectedgeo);


		selectedgeo.setLatitude(0.0);
		selectedgeo.setLongitude(0.0);

		locationcontroller.checklocations(selectedgeo);

		//since the selected location is 0.0 default location should be used which right now is equal to geo2
		assertTrue(locationcontroller.getGeo() == geo2);	

	}


	public void testResetSelectedLocation(){

		LocationController locationcontroller = new LocationController();


		GeoLocation selectedgeo = new GeoLocation();
		selectedgeo.setLatitude(9.345);
		selectedgeo.setLongitude(10.234);

		locationcontroller.resetselectedlocation(selectedgeo);

		assertTrue(selectedgeo.getLatitude() == 0);
		assertTrue(selectedgeo.getLongitude() == 0);

	}




}
