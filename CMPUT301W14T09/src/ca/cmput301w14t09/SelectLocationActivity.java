package ca.cmput301w14t09;

import ca.cmput301w14t09.Controller.LocationController;
import android.app.Activity;
import android.content.Context;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SelectLocationActivity extends Activity {


    //probobly wont need this activity, but will be useful in connecting with Connners UI

    //LocationManager lm = null;
    Context context = getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        //Get textviews from interface
        //TextView tv = (TextView) findViewById(R.id.locationtext);
        final EditText tv2 = (EditText) findViewById(R.id.longtext3);
        final EditText tv3 = (EditText) findViewById(R.id.lattext3);


        //new Location Controller 
        final LocationController lc = new LocationController();


        //get Location Manager setup
        lc.setLocationManager(context);


        // Obtain LocationManager service 
        //LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        // Retrieve location updates through LocationListener interface
        final LocationListener locationListener = new LocationListener(){
            // To Do: override the four methods.


            public void onProviderDisabled (String provider){

            }

            public void onProviderEnabled (String provider){


            }

            public void onStatusChanged (String provider, int status, Bundle extras){


            }

            @Override
            public void onLocationChanged(android.location.Location location) {

                lc.locationchanged(location, tv2, tv3);


            }
        };






        //set up location update request.
        //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        lc.requestLocationUpdates(locationListener);



        //update location button
        Button btnSimple2 = (Button) findViewById(R.id.changebutton);
        btnSimple2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Context context1 = getApplication();
                lc.updatelocation(context1,tv2.getText().toString(), tv3.getText().toString());


            }
        });


    }


}
