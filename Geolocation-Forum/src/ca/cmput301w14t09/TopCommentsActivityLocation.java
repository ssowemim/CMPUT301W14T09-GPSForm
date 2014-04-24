/**
 * License GPLv3: GNU GPL Version 3 <http://gnu.org/licenses/gpl.html>. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ca.cmput301w14t09;


import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.GeoPoint;
import ca.cmput301w14t09.Controller.LocationController;

/**
 * 
 * @author Cameron
 * TopCommentsActivityLocation handles all the functions that deal with setting up mapquest on the TopCommentsActivity
 * This code was taken from http://developer.mapquest.com/web/products/featured/android-maps-api/documentation
 */

public class TopCommentsActivityLocation
{

	private MapView map;
	private MyLocationOverlay myLocationOverlay;
	private GeoPoint currentLocation;

	public MapView getMap()
	{

		return map;
	}

	public void setMap(MapView map)
	{

		this.map = map;
	}

	public MyLocationOverlay getMyLocationOverlay()
	{

		return myLocationOverlay;
	}

	public void setMyLocationOverlay(MyLocationOverlay myLocationOverlay)
	{

		this.myLocationOverlay = myLocationOverlay;
	}

	public GeoPoint getCurrentLocation()
	{

		return currentLocation;
	}

	public void setCurrentLocation(GeoPoint currentLocation)
	{

		this.currentLocation = currentLocation;
	}

	/**
	 * set your map and enable default zoom controls  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	public void setupMapView(TopCommentsActivity topCommentsActivity)
	{

		this.map = (MapView) topCommentsActivity.findViewById(R.id.map);
		map.setBuiltInZoomControls(true);
	}

	/**
	 * set up a MyLocationOverlay and execute the runnable once we have a location fix  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	public void setupMyLocation(final LocationController lc1,
			TopCommentsActivity topCommentsActivity)
	{

		this.myLocationOverlay = new MyLocationOverlay(topCommentsActivity, map);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable()
		{

			@Override
			public void run()
			{

				currentLocation = myLocationOverlay.getMyLocation();
				lc1.setGeodefault(currentLocation.getLatitude(),
						currentLocation.getLongitude());
				map.getController().animateTo(currentLocation);
				map.getController().setZoom(14);
				map.getOverlays().add(myLocationOverlay);
				myLocationOverlay.setFollowing(true);
			}
		});
	}
}