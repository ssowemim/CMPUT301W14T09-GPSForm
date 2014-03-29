package ca.cmput301w14t09;

import java.util.List;

import android.annotation.SuppressLint;
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
    
    

    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.location_layout);
      setupMapView();
      setupMyLocation();
      
      // initialize the annotation to be shown later 
      
      annotation = new AnnotationView(map);
      code = new Geocoder(getApplicationContext());
      int id = getLayoutId();
      
      
      //setup 
      setupViews();
      setupOverlays();
      addPoiOverlay();
     
      
     
      
    }

    // set your map and enable default zoom controls 
    protected void setupMapView() {
      this.map = (MapView) findViewById(R.id.map);
      map.setBuiltInZoomControls(true);
   		
    }

    // set up a MyLocationOverlay and execute the runnable once we have a location fix 
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
    
  
    
 // add an itemized overlay to map 
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
  

    // enable features of the overlay 
    @Override
    protected void onResume() {
      myLocationOverlay.enableMyLocation();
      myLocationOverlay.enableCompass();
      super.onResume();
    }

    // disable features of the overlay when in the background 
    @Override
    protected void onPause() {
      super.onPause();
      myLocationOverlay.disableCompass();
      myLocationOverlay.disableMyLocation();
    }

    @Override
    public boolean isRouteDisplayed() {
      return false;
    }
    
    /**
	 * Get the id of the layout file.
	 * @return
	 */
	protected int getLayoutId() {
	    return R.layout.location_layout;
	}
	
	 /**
     * Sets up view and hooks up event handlers.
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

        private Geocoder getGeocoder() {
			// TODO Auto-generated method stub
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

	public Geocoder getGeocoder() {
		return code;
	}
	
	  /**
     * Add an overlay to reverse geocode on touch event.
     */
    private void setupOverlays() {
        map.getOverlays().add(new ReverseGeocodeOverlay());
    }
    
    /**
     * Execute the geocode task for location input
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
     *
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
  
   
  }
