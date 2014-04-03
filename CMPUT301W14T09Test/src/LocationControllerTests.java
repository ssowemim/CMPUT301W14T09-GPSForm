import static org.junit.Assert.assertTrue;

import org.junit.Test;

import android.location.Location;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Model.GeoLocation;



public class LocationControllerTests
{

	@Test
	public void testGetGeolocation()
	{
		
		LocationController locationcontroller = new LocationController();
		GeoLocation geo2 = locationcontroller.getGeoLocation();
		assertTrue(geo2 == locationcontroller.getGeoLocation());
		
	}
	
	
	@Test
	public void testLocationChanged(){
		
		LocationController locationcontroller = new LocationController();
		Location location = new Location("dummyprovider");
		location.setLatitude(-53.2345);
		location.setLongitude(103.44566);
		assertTrue(location.getLatitude() == -53.2345);
		locationcontroller.locationchanged(location);
		
		assertTrue(locationcontroller.getGeodefault().getLatitude() == -53.2345);
		
	}
	
	@Test
	public void 
	
	

}
