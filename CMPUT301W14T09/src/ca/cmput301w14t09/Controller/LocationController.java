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

import android.content.Intent;
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
	LocationManager lm = null;
	Intent intent = null;

	/**
	 * function returns geolocation object
	 * @return
	 */
	public GeoLocation getGeoLocation() {
		return geo;
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

		}
	}

	/**
	 * This function compares a geolocation to another geolocation in this case default or selected
	 * If selected contains points then the geolocation will be set to defaultgeo
	 * @param selectedgeo
	 */
	public void checklocations(GeoLocation selectedgeo){
		if(selectedgeo.getLatitude()!= 0 && selectedgeo.getLongitude()!= 0){

			geo = selectedgeo;

		}
		else{

			geo = geodefault;

		}
	}

	/**
	 * This function resets a geolocations lat and long coordinates to 0.0 0.0
	 * @param selectedgeo
	 */
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
	public void setGeodefault(double lat, double lng)
	{
		geodefault.setLatitude(lat);
		geodefault.setLongitude(lng);

	}


	/**
	 * @param geo the geo to set
	 */
	public void setGeo(GeoLocation geo)
	{

		this.geo = geo;
	}


}

