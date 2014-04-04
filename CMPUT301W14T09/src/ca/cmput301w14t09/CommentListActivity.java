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

import java.util.ArrayList;
import java.util.Collections;
import android.app.Activity;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.SortingController;

import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.CommentAdapter;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.PictureModelList;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.elasticSearch.Server;

/**
 * 
 * @author Conner
 * @editor ChunHan
 * This activity shows the top comment that was selected in a 
 * previous activity and displays all the replies to that comment
 * This activity shows the top comment that was selected in a 
 * previous activity and displays all the replies to that comment
 *
 * Need to send the user back as a Activity result so the favorite
 * saves to the user profile are sent back as well and favorites
 * are shown immediately
 */
public class CommentListActivity extends ListActivity {

    //Activity request codes to take pictures
    public static final int OBTAIN_PIC_REQUEST_CODE = 117;
    public static final int MEDIA_TYPE_IMAGE = 1;

    protected Intent intent;
    protected User user;
    protected Dialog dialog;
    protected ListView aCommentList;



    protected PopUpReply popUpReply = new PopUpReply(this);



    protected CommentAdapter adapter;

    Comment comment;



    PictureModelList pictureModel;



    protected ListView favList;
    protected String firstComment;



    private Handler  updateHandler;
    private Runnable updateFunction;


    //File uri to store Images
    private Uri fileUri;



    //selected geolocation object used for when person selects geolocation from
    GeoLocation selectedgeo = new GeoLocation();
    GeoLocation selectedgeosort = new GeoLocation();


    //new Location Controller 
    final LocationController lc1 = new LocationController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        favList = (ListView) findViewById(android.R.id.list);

        final Activity commentActivity = this;


        favList.setOnItemClickListener(new OnItemClickListener(){

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                /*
				if(user.getUserName().equals("Guest")) {
					Toast.makeText(getApplicationContext(),
							"Guest Can Not Add Favourites", Toast.LENGTH_LONG).show();
				} else {
					Comment thread = (Comment)(favList.getItemAtPosition(arg2)); 



					user.profile.add(thread);



					FileSaving.saveUserFile(user, commentActivity);
					Toast.makeText(getApplicationContext(),
							"Comment Added To Favourites.", Toast.LENGTH_LONG).show();
				}
                 */

            }
        });




        //https://github.com/baoliangwang/CurrentLocation
        //setup location manager
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        // Retrieve location updates through LocationListener interface
        //https://github.com/baoliangwang/CurrentLocation
        LocationListener locationListener = new LocationListener() {				

            public void onProviderDisabled (String provider) {
            }

            public void onProviderEnabled (String provider) {
            }

            public void onStatusChanged (String provider, int status, Bundle extras) {
            }

            @Override
            public void onLocationChanged(android.location.Location location) {
                lc1.locationchanged(location);
            }
        };
        //request location update
        //https://github.com/baoliangwang/CurrentLocation
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);

        // Handler polling
        updateHandler = new Handler();
        updateFunction = new Runnable() {
            @Override
            public void run() {
                populateListView();
            }
        };

        Thread update = new Thread() {
            public void run() {
                while(true) {
                    try {
                        updateHandler.post(updateFunction);
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        update.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comment_list, menu);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("CURRENT_USER");
        firstComment = (String) getIntent().getSerializableExtra("THREAD_ID");
        System.out.println(firstComment);

        return true;
    }



    /**
     * 
     * @author Chunhan
     * Allows for selection of sorting on the action bar
     * https://developer.android.com/training/basics/actionbar/adding-buttons.html
     * 
     */
    @Override 
    public boolean onOptionsItemSelected(MenuItem item){
        ArrayList<Comment> replyComments = null;
        switch (item.getItemId()){
            case R.id.sortLocation:			
                SortingController sorting = new SortingController();
                try {
                    replyComments = ElasticSearchOperations.pullOneThread(firstComment);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ArrayList<Comment> sortedList = sorting.sortTopComments(lc1, null, replyComments);
                adapter = new CommentAdapter(this,R.layout.comment_view, sortedList);
                favList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.sortDate:
                try {
                    ArrayList<Comment> comment = ElasticSearchOperations.pullOneThread(firstComment);
                    adapter = new CommentAdapter(this,R.layout.comment_view, comment);
                    favList.setAdapter(adapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return true;
            case R.id.sortPicture:
                SortingController sorting1 = new SortingController();
                ArrayList<Comment> commentList = sorting1.sortPictures(firstComment);
                adapter = new CommentAdapter(this,R.layout.comment_view, commentList);
                favList.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                return true;
                
            default:
                return super.onOptionsItemSelected(item);
           
        }
    }

    public void popUp(View v) throws InterruptedException {
        popUpReply.popUp(v, this, fileUri, lc1, selectedgeo, user, firstComment, "Comment Reply");
    }




    /**
     * onStart populates the listview with results from the elasticSearch
     *  query found in ElasticSearchOperations.pullOneThread(firstComment)
     */
    @Override
    protected void onStart() {
        super.onStart();

    }


    public void populateListView() {
        ArrayList<Comment> commentThread = null;


        if(user != null) {
            if(Server.getInstance().isServerReachable(this)) {
                try {
                    commentThread = ElasticSearchOperations.pullOneThread(firstComment);
                    user.profile.cache.add(commentThread);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            FileSaving.saveUserFile(user, this);
            adapter = new CommentAdapter(this,
                    R.layout.comment_view,
                    user.profile.cache.getSubComments(firstComment));


            favList.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
        if (requestCode == 122 && resultCode == Activity.RESULT_OK){

            //succesfully get updated geolocation
            selectedgeo = (GeoLocation) data.getExtras().get("SomeUniqueKey");
            System.out.println("GEO TOP: LAT"+ selectedgeo.getLatitude());
            System.out.println("GEO TOP: LNG"+ selectedgeo.getLongitude());
            Toast.makeText(getApplicationContext(),"Comment Location Updated.", Toast.LENGTH_LONG).show();
        }
        //http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
        if (requestCode == 123 && resultCode == Activity.RESULT_OK){

            //succesfully get updated geolocation
            selectedgeosort = (GeoLocation) data.getExtras().get("SomeUniqueKey");
            System.out.println("GEO TOP: LAT sort"+ selectedgeosort.getLatitude());
            System.out.println("GEO TOP: LNG sort"+ selectedgeosort.getLongitude());
            Toast.makeText(getApplicationContext(),"Your Location Updated.", Toast.LENGTH_LONG).show();
        }


        // if the result is capturing Image
        if (requestCode == OBTAIN_PIC_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                popUpReply.pictureResult(fileUri);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(this.getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            // failed to capture image
            Toast.makeText(this.getApplicationContext(),
                    "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                    .show();
        }
    }


    /**
     * onSaveInstanceState stores the file url as
     * it will be null after returning from camera app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);



        // save file url in bundle as it will be null on screen orientation changes
        outState.putParcelable("file_uri",fileUri);
    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fileUri = savedInstanceState.getParcelable("file_uri");
    }


    public void onBackPressed() {
        Intent intent = getIntent();
        intent.putExtra("CURRENT_USER", user);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();
        finish();
    }

}
