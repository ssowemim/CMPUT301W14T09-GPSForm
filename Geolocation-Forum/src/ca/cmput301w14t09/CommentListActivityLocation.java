/**
 * License GPLv3: GNU GPL Version 3 <http://gnu.org/licenses/gpl.html>. This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details. You should have received a copy of the GNU General Public License along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package ca.cmput301w14t09;


import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import ca.cmput301w14t09.Controller.LocationController;

public class CommentListActivityLocation{

	private MapView map;

	/**
	 * Return a mapview
	 * @return
	 */
	public MapView getMap(){

		return map;
	}

	/**
	 * set the mapview
	 * @param map
	 */
	public void setMap(MapView map){

		this.map = map;
	}

	/**
	 * set your map and enable default zoom controls  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	public void setupMapView(CommentListActivity commentListActivity){

		this.map = (MapView) commentListActivity.findViewById(R.id.map);
		map.setBuiltInZoomControls(true);
	}

	/**
	 * set up a MyLocationOverlay and execute the runnable once we have a location fix  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	public void setupMyLocation(final CommentListActivity commentListActivity,
			final LocationController lc1){

		commentListActivity.setMyLocationOverlay(new MyLocationOverlay(
				commentListActivity, map));
		commentListActivity.getMyLocationOverlay().enableMyLocation();
		commentListActivity.getMyLocationOverlay().runOnFirstFix(new Runnable(){

			@Override
			public void run(){

				commentListActivity.setCurrentLocation(commentListActivity
						.getMyLocationOverlay().getMyLocation());
				lc1.setGeodefault(commentListActivity.getCurrentLocation()
						.getLatitude(), commentListActivity
						.getCurrentLocation().getLongitude());
				map.getController().animateTo(
						commentListActivity.getCurrentLocation());
				map.getController().setZoom(14);
				map.getOverlays().add(
						commentListActivity.getMyLocationOverlay());
				commentListActivity.getMyLocationOverlay().setFollowing(true);
			}
		});
	}
}