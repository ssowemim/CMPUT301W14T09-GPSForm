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
import java.util.Date;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.LocationListener;
import android.location.LocationManager;
import android.widget.EditText;
import android.widget.Toast;
import ca.cmput301w14t09.model.GeoLocation;

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
    private double lat;
    private double lng;
    LocationManager lm = null;
    int count = 0;

    public GeoLocation getGeoLocation() {
        return geo;
    }

    public void setGeoLocation() {
        geo.setLatitude(lat);
        geo.setLongitude(lng);
    }

    public ArrayList<String> getLocationNames(){
        return null;
    }
    
    public void setlat(double lat){
    	this.lat = lat;
    }

    public void setlng(double lng){
    	this.lng = lng;
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
    
    public void locationchanged(android.location.Location location, EditText tv2, EditText tv3){
        if(location != null){
        	
            if(count<2){
            	
                lat = location.getLatitude();
                lng = location.getLongitude();

                Date date = new Date(location.getTime());
                
                //set GUI textviews
                tv2.setText(""+lng);
                tv3.setText(""+lat);
                
                //set geolocation to current location
                setGeoLocation();
                
                //count to stop from retreiving location
                count = count + 1;
            }
        }

    }

    /**
    * updatelocation function responsible for updating the geolocation points in locationcontroller
    * if the user chooses to update the GPS coordinate points that are set default when new comment is initiated.
    * @param LocationController
    */

    @SuppressLint("NewApi")
	public void updatelocation(Context context, String longitude, String latitude) {  
    	
        // Fix for passing in blank parameters.
        if(latitude.isEmpty() == true) { 
            lat = 1;
        }
        
        else {
            lat = Double.parseDouble(latitude);
        }
        
        if(longitude.isEmpty() == true) {
            lng = 1;
        }
        else {
            lng = Double.parseDouble(longitude);
        }
        
        //sets geolocation to new given one
        setGeoLocation();

        //toast message displayed on successful update
        String update ="Your location has been updated";
        
        // When clicked, show a toast with the TextView text Game, Help, Home
        Toast.makeText(context, update, Toast.LENGTH_SHORT).show();  

    }
}

