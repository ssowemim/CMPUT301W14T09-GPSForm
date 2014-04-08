/**
 * License GPLv3: GNU GPL Version 3 <http://gnu.org/licenses/gpl.html>. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ca.cmput301w14t09;


import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.MapView;

/**
 *  This class is responsible for finding currentlocation on the mapquest map, this was created to seperate creation from location finding
 *  All of this code was found at 
 *  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation
 *  I downloaded the Android Maps API & Samples which included the library I used and all the bits for this class
 *  geolocation that will be returned upon leaving activity
 *  @author Cameron Alexander
 */

public class ChooseLocationActivitySetupLocation
{

	private GeoPoint currentLocation;

	/**
	 * Get your current location
	 * @return
	 */
	public GeoPoint getCurrentLocation(){

		return currentLocation;
	}

	/**
	 * set your curent geo location
	 * @param currentLocation
	 */
	public void setCurrentLocation(GeoPoint currentLocation){

		this.currentLocation = currentLocation;
	}

	/**
	 * set up a MyLocationOverlay and execute the runnable once we have a location fix  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	public void setupMyLocation(
			final ChooseLocationActivity chooseLocationActivity,
			final MapView map){

		chooseLocationActivity.setMyLocationOverlay(new MyLocationOverlay(
				chooseLocationActivity, map));
		chooseLocationActivity.getMyLocationOverlay().enableMyLocation();
		chooseLocationActivity.getMyLocationOverlay().runOnFirstFix(
				new Runnable(){

					@Override
					public void run(){

						currentLocation = chooseLocationActivity
								.getMyLocationOverlay().getMyLocation();
						map.getController().animateTo(currentLocation);
						map.getController().setZoom(14);
						map.getOverlays().add(
								chooseLocationActivity.getMyLocationOverlay());
						chooseLocationActivity.getMyLocationOverlay()
								.setFollowing(true);
					}
				});
	}
}