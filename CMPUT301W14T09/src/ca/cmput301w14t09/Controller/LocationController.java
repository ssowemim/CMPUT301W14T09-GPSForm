/**
 
License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
@param thread
 */

package ca.cmput301w14t09.Controller;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import ca.cmput301w14t09.Model.GeoLocation;

/**
 * 
 * @author Cameron Alexander
 * This class is reponsible for getting geolocation when it is requested. It can setup a location
 * manager and take geolocation update requests. It will create a geolocation object using the android GPS location
 * listener.  This controller is also used for updating the location of a user if the user wants to post a comment in a
 * location other then the default location that is used when user chooses to make new comment.
 * @param LocationController
 * 
 */

public class LocationController {

    private GeoLocation geo = new GeoLocation();
    GeoLocation geodefault = new GeoLocation();
    GeoLocation selcommentlocation = new GeoLocation();
    LocationManager lm = null;
    Intent intent = null;
    

    public GeoLocation getGeoLocation() {
        return geo;
    }

 
    public ArrayList<String> getLocationNames(){
        return null;
    }
    
    /**
     * setLocationManager function responsible for setting up 
     *location manager wherever is called and sets in locationcontroller.
     *@param LocationController
     */
    
    public void setLocationManager(Context context) {

        // Obtain LocationManager service 
        @SuppressWarnings("static-access")
        LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);

    }
    
    /**
     * Request a location update using the location manager and a location listener from activity
     * @param LocationController
     */

    public void requestLocationUpdates(LocationListener locationListener){

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

    }

    /**
   	* location changed function responsible for getting location points from GPS location on android activity
   	* and then setting the geolocation in Location controller to the current location of the GPS.
   	* @param LocationController
    */
    
    public void locationchanged(android.location.Location location){
        if(location != null){
        	
                geodefault.setLatitude(location.getLatitude());
                geodefault.setLongitude(location.getLongitude()); 
                //System.out.println("geodefaultset"+geodefault.getLatitude());
                //System.out.println("geodefaultset"+geodefault.getLongitude());
              
            }
        }
    
    public void checklocations(GeoLocation selectedgeo){
    	if(selectedgeo.getLatitude()!= 0 && selectedgeo.getLongitude()!= 0){
    		geo = selectedgeo;
    		 System.out.println("used selected geo"+geo.getLatitude());
    	}
    	else{
    		geo = geodefault;
    		 System.out.println("used default geo"+geo.getLatitude());
    	}
    	
    }
    
    public void resetselectedlocation(GeoLocation selectedgeo){
    	
    	double latitude = 0.0;
		double longitude = 0.0;
		selectedgeo.setLatitude(latitude);
		selectedgeo.setLongitude(longitude);
    	
    }


	
	/**
	 * @return the geodefault
	 */
	public GeoLocation getGeodefault()
	{
	
		return geodefault;
	}


	
	/**
	 * @param geodefault the geodefault to set
	 */
	public void setGeodefault(GeoLocation geodefault)
	{
	
		this.geodefault = geodefault;
	}

    
}

