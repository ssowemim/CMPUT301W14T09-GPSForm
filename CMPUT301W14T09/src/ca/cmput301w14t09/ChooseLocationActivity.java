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
 */


package ca.cmput301w14t09;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import ca.cmput301w14t09.Model.GeoLocation;

import com.mapquest.android.Geocoder;
import com.mapquest.android.maps.AnnotationView;
import com.mapquest.android.maps.DefaultItemizedOverlay;
import com.mapquest.android.maps.GeoPoint;
import com.mapquest.android.maps.ItemizedOverlay;
import com.mapquest.android.maps.MapActivity;
import com.mapquest.android.maps.MapView;
import com.mapquest.android.maps.MyLocationOverlay;
import com.mapquest.android.maps.Overlay;
import com.mapquest.android.maps.OverlayItem;

/**
 *  This class is responsible for creating the MapQuest activity , it allows the user to search for cities, click on the map
 *  to set location and retrieve address info.
 *  All of this code was found at 
 *  http://developer.mapquest.com/web/products/featured/android-maps-api/documentation
 *  I downloaded the Android Maps API & Samples which included the library I used and all the bits for this class
 *  geolocation that will be returned upon leaving activity
 *  @author Cameron Alexander
 */

public class ChooseLocationActivity extends MapActivity {
	protected MapView map;
	private MyLocationOverlay myLocationOverlay;
	AnnotationView annotation;
	GeoPoint currentLocation;
	int id = 0;
	Geocoder code = null;
	GeocodeTask geocodeTask;
	ReverseGeocodeTask reverseGeocodeTask;
	GeoLocation geo = new GeoLocation();


	/**
	 * OnCreate method code found from
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location_layout);


		setupMapView();
		setupMyLocation();

		//used for reverse geocoding
		annotation = new AnnotationView(map);
		code = new Geocoder(getApplicationContext());
		int id = getLayoutId();


		//setup views and overlay
		setupViews();
		setupOverlays();

		//toast to tell user to click on map
		//http://developer.android.com/guide/topics/ui/notifiers/toasts.html
		Toast.makeText(getApplicationContext(),"Click on map to Set your location and then click Back to Confirm.", Toast.LENGTH_LONG).show();



	}


	/**
	 *set your map and enable default zoom controls 
	 *http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	protected void setupMapView() {
		this.map = (MapView) findViewById(R.id.map);
		map.setBuiltInZoomControls(true);

	}


	/**
	 * set up a MyLocationOverlay and execute the runnable once we have a location fix 
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private void setupMyLocation() {
		this.myLocationOverlay = new MyLocationOverlay(this, map);
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.runOnFirstFix(new Runnable() {
			@Override
			public void run() {

				currentLocation = myLocationOverlay.getMyLocation();
				map.getController().animateTo(currentLocation);
				map.getController().setZoom(14);
				map.getOverlays().add(myLocationOverlay);
				myLocationOverlay.setFollowing(true);
			}
		});
	}


	/**
	 * add an itemized overlay to map 
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private void addPoiOverlay() {

		// use a custom POI marker by referencing the bitmap file directly,
		// using the filename as the resource ID 
		Drawable icon = getResources().getDrawable(R.drawable.ic_launcher);
		final DefaultItemizedOverlay poiOverlay = new DefaultItemizedOverlay(icon);

		// set GeoPoints and title/snippet to be used in the annotation view 
		OverlayItem poi1 = new OverlayItem(new GeoPoint (53.54439,-113.49093), "Edmonton, Alberta", "");
		poiOverlay.addItem(poi1);


		// add a tap listener for the POI overlay 
		poiOverlay.setTapListener(new ItemizedOverlay.OverlayTapListener() {
			@Override
			public void onTap(GeoPoint pt, MapView mapView) {
				// when tapped, show the annotation for the overlayItem 
				int lastTouchedIndex = poiOverlay.getLastFocusedIndex();
				if(lastTouchedIndex>-1){
					OverlayItem tapped = poiOverlay.getItem(lastTouchedIndex);
					annotation.showAnnotationView(tapped);
				}
			}
		});

		map.getOverlays().add(poiOverlay);
	}    



	/**
	 * enable features of the overlay 
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	@Override
	protected void onResume() {
		myLocationOverlay.enableMyLocation();
		myLocationOverlay.enableCompass();
		super.onResume();
	}


	/**
	 * disable features of the overlay when in the background 
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	@Override
	protected void onPause() {
		super.onPause();
		myLocationOverlay.disableCompass();
		myLocationOverlay.disableMyLocation();
	}

	/**
	 * check is route is displayed
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	@Override
	public boolean isRouteDisplayed() {
		return false;
	}

	/**
	 * Get the id of the layout file.
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	protected int getLayoutId() {
		return R.layout.location_layout;
	}

	/**
	 * Sets up view and hooks up event handlers.
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private void setupViews() {
		Button button = (Button) findViewById(R.id.mq_geocode_btn);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				geocode();
			}
		});



		EditText mqGeocodeInput = (EditText) findViewById(R.id.mq_geocode_input);
		mqGeocodeInput.setOnKeyListener(new OnKeyListener() {

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
						(keyCode == KeyEvent.KEYCODE_ENTER)) {

					geocode();
					return true;
				} 

				return false;
			}
		});
	}



	/**
	 * Geocode background task
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	@SuppressLint("NewApi")
	private class GeocodeTask extends AsyncTask<String, Void, List<Address>> {
		protected List<Address> doInBackground(String... location) {
			try {
				return getGeocoder().getFromLocationName(location[0], 1);
			} catch (Exception e) {
				return null;
			}

		}

		/**
		 * Returns geocode
		 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
		 * @return
		 */
		private Geocoder getGeocoder() {
			return code;
		}

		protected void onPostExecute(List<Address> result) {
			if (result == null || result.size() == 0) {
				Toast.makeText(getApplicationContext(), "No match found!", Toast.LENGTH_SHORT).show();
			} else {
				Address address = result.get(0);
				map.getController().setCenter(new GeoPoint(address.getLatitude(), address.getLongitude()));
				geocodeTask = null;
			}
		}
	}

	/**
	 * ReversesGeocode background task
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	@SuppressLint("NewApi")
	private class ReverseGeocodeTask extends AsyncTask<GeoPoint, Void, List<Address>> {
		protected List<Address> doInBackground(GeoPoint... geoPoint) {
			try {

				geo.setLatitude(geoPoint[0].getLatitude());
				geo.setLongitude(geoPoint[0].getLongitude());

				System.out.println("hey: geolat "+geo.getLatitude());
				System.out.println("hey: geolng "+geo.getLongitude());
				return getGeocoder().getFromLocation(geoPoint[0].getLatitude(), geoPoint[0].getLongitude(), 1);

			} catch (Exception e) {
				return null;
			}

		}

		/**
		 * Used in searching for a adress when mapped clicked
		 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
		 */
		protected void onPostExecute(List<Address> result) {
			if (result == null || result.size() == 0) {
				Toast.makeText(getApplicationContext(), "No match found!", Toast.LENGTH_SHORT).show();

			} else {
				Address address = result.get(0);
				StringBuilder addressText = new StringBuilder();
				for (int i =0; i < address.getMaxAddressLineIndex(); i++) {
					addressText.append(address.getAddressLine(i)+"\n");
				}
				Toast.makeText(getApplicationContext(), "Match found :\n" + addressText, Toast.LENGTH_SHORT).show();
				reverseGeocodeTask = null;
			}
		}
	}

	/**
	 * returns geocoder
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 * @return
	 */
	public Geocoder getGeocoder() {
		return code;
	}

	/**
	 * Add an overlay to reverse geocode on touch event.
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private void setupOverlays() {
		map.getOverlays().add(new ReverseGeocodeOverlay());
	}

	/**
	 * Execute the geocode task for location input
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private void geocode() {
		if (geocodeTask == null) {
			EditText mqGeocodeInput = (EditText) findViewById(R.id.mq_geocode_input);
			String location = mqGeocodeInput.getText().toString();
			if (location.length() > 0) {
				geocodeTask = new GeocodeTask();
				geocodeTask.execute(location);
			}
		}
	}

	/**
	 * An overlay class to handle the reverse geocoding on touch. 
	 * http://developer.mapquest.com/web/products/featured/android-maps-api/documentation samples download
	 */
	private class ReverseGeocodeOverlay extends Overlay {

		@Override
		public boolean onTap(GeoPoint p, MapView mapView) {
			if (reverseGeocodeTask == null) {
				reverseGeocodeTask = new ReverseGeocodeTask();
				reverseGeocodeTask.execute(p);
			}
			return false;
		}

	}

	/**
	 * On back pressed pack up the geolocation that was selected and send back to parent activity to be processed
	 * http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
	 */

	public void onBackPressed()
	{
		// Update _workorder object
		Intent intent = getIntent();
		intent.putExtra("SomeUniqueKey", geo);
		setResult(Activity.RESULT_OK, intent);
		super.onBackPressed();

	}




}
