import java.util.ArrayList;

import ca.cmput301w14t09.model.Area;
import ca.cmput301w14t09.model.GeoLocation;
import junit.framework.TestCase;


public class testGeolocation extends TestCase {

	public testGeolocation(String name) {
		super(name);
	}
	
	
	public void testGeolocation(){
		
		//53.5333  113.5000
		
		GeoLocation geo = new GeoLocation();
		
		double lat1 = 53.5333;
		double lon1 = 113.500;
		
		geo.setLatitude(lat1);
		
		geo.setLongitude(lon1);
		
		double lat = geo.getLatitude();
		
		double lon = geo.getLongitude();
		
		assertTrue(lat1 == lat);
		assertTrue(lon1 == lon);
			
	}

}
