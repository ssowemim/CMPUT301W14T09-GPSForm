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

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;

import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Comment;

import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.PictureModelList;
import ca.cmput301w14t09.Model.ThreadAdapter;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.elasticSearch.Server;

/**
 * 
 * @author ssowemim, Conner
 * TopCommentsActivity handles all the functions that the pop_up_comment.xml has to offer.
 * Controlling the longitude & latitude. 
 * Attaching a picture to a comment.
 * Most of the code referring to handling the picture is taken from
 * http://www.androidhive.info/2013/09/android-working-with-camera-api/
 * Making a comment with an author.
 *
 */
public class TopCommentsActivity extends ListActivity {

    //Activity request codes to take pictures
    public static final int OBTAIN_PIC_REQUEST_CODE = 117;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int FAVORITE_LIST = 42;

    //File uri to store Images
    private Uri fileUri;

    private TopCommentsActivity topActivity;

    PictureController pictureController;

    protected Intent intent;
    protected User user;
    protected Dialog dialog;
    protected ListView aCommentList;
    Comment comment;

    ImageButton addPicImageButton;
    ImageView picImagePreview;
    Bitmap picture = null;

    PictureModelList pictureModel;

    EditText authorText;
    EditText commentText;
    ThreadAdapter adapter1;

    //selected geolocation object used for when person selects geolocation fom
    GeoLocation selectedgeo = new GeoLocation();
    GeoLocation selectedgeosort = new GeoLocation();


    //new Location Controller 
    final LocationController lc1 = new LocationController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_comments);
        topActivity = this;
        //	attachment = false;

        aCommentList = (ListView) findViewById(android.R.id.list);

        aCommentList.setOnItemClickListener(new OnItemClickListener(){
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                Comment thread = (Comment)(aCommentList.getItemAtPosition(arg2)); 

                // Pass in comment object
                commentThread(thread);
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
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_comments, menu);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("CURRENT_USER");
        populateListview();



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
        switch (item.getItemId()){
            case R.id.sortLocation:			
                SortingController sorting = new SortingController();
                ArrayList<Comment> sortedList = sorting.sortCommentsByLocation(lc1, null);
                adapter1 = new ThreadAdapter(this,R.layout.thread_view, sortedList);
                aCommentList.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                return true;

            case R.id.sortDate:
                ArrayList<Comment> topComments = null;
                try {
                    topComments = ElasticSearchOperations.pullThreads();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                adapter1 = new ThreadAdapter(this,R.layout.thread_view, topComments);
                aCommentList.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                return true;

            case R.id.sortPicture:
                SortingController sorting1 = new SortingController();
                ArrayList<Comment> commentList = sorting1.sortPictures(null);
                adapter1 = new ThreadAdapter(this,R.layout.thread_view, commentList);
                aCommentList.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                return true;

            case R.id.selectDiffLocation:
                Intent intentdiff = new Intent(getApplicationContext(), ChooseLocationActivity.class);
                startActivityForResult(intentdiff, 123);
                return true;

            case R.id.sortByDiffLocation:
                SortingController sorting2 = new SortingController();
                ArrayList<Comment> sortedList1 = sorting2.sortTopComments(selectedgeosort);
                adapter1 = new ThreadAdapter(this,R.layout.thread_view, sortedList1);
                aCommentList.setAdapter(adapter1);
                adapter1.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * onResume popluates the listview with results from
     * elasticSearch, finding all of the top comments
     * @param thread
     */
    @Override
    public void onResume() {
        super.onResume();
    }

    
    private void populateListview() {
        ArrayList<Comment> topComments = null;

        if(Server.getInstance().isServerReachable(this)) {
            try {
                topComments = ElasticSearchOperations.pullThreads();
                user.profile.cache.add(topComments);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        

        adapter1 = new ThreadAdapter(this,
                R.layout.thread_view,
                topComments);
                //user.profile.cache.getTopComments(true));
        
        aCommentList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();
    }
    
    /**
     * popUp is a dialog that is invoked when the new
     * comment button is pressed.
     * It allows for most attributes of a comment to be
     * modified and then pushed to elasticSearch
     * @param v
     */
    @SuppressLint("NewApi")
    public void popUp(View v) {

        dialog = new Dialog(this);

        dialog.setContentView(R.layout.pop_up_comment);
        dialog.setTitle("New Top Comment");

        authorText=(EditText)dialog.findViewById(R.id.authorText);
        commentText=(EditText)dialog.findViewById(R.id.commentText);

        //new Location Controller 
        //final LocationController lc = new LocationController();
        this.pictureController = new PictureController();

        //https://github.com/baoliangwang/CurrentLocation
        //setup location manager
        //LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        authorText.setText(user.getProfile().getAuthorName());
        Button save=(Button)dialog.findViewById(R.id.save);
        Button btnCancel=(Button)dialog.findViewById(R.id.cancel);

        //update location button
        ImageButton btnSetLocation = (ImageButton)dialog.findViewById(R.id.changebutton);

        picImagePreview = (ImageView)dialog.findViewById(R.id.picImagePreview);  
        addPicImageButton = (ImageButton) dialog.findViewById(R.id.takePicture);

        dialog.show();

        //Capture image button click event		
        this.addPicImageButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // capture picture
                captureImage();
                //attachment = true;

                //	attachment = true;

            }
        });

        // Checks camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "No Camera Detected.", Toast.LENGTH_LONG).show();
        }



        dialog.show();

        //update location button
        btnSetLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) { 

                Intent intent = new Intent(dialog.getContext(), ChooseLocationActivity.class);
                startActivityForResult(intent, 122);

            }
        });

        //cancel button
        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();
                lc1.resetselectedlocation(selectedgeo);

            }
        });

        //save button
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String text1 = commentText.getText().toString();
                String text2 = authorText.getText().toString();
                user.getProfile().setAuthorName(text2);
                FileSaving.saveUserFile(user, topActivity);

                picture = pictureController.finalizePicture(picture, topActivity);

                //check locations to see which one to use
                lc1.checklocations(selectedgeo);
              //  System.out.println("Sel LAT:"+selectedgeo.getLatitude());

        		SerializableBitmap serializePic = new SerializableBitmap(picture);
                comment = CommentFactory.buildComment(lc1, text2, text1, true, serializePic);

                //reset selected locaton for comments
                lc1.resetselectedlocation(selectedgeo);
                System.out.println("Reset selected location back to default" +
                		":"+selectedgeo.getLatitude());

                try
                {
                    ElasticSearchOperations.postThread(comment);
             //      Thread.sleep(1000);
                    adapter1.notifyDataSetChanged();
           //         recreate();

                } catch (InterruptedException e)
                {

                    e.printStackTrace();
                }

                dialog.dismiss();

            }
        });

    }


    public void saveComment(){

    }

    /**
     * isDeviceSupportCamera does a check to see
     * if device hardware camera is present or not
     * @return
     */
    public boolean isDeviceSupportCamera() {
        if(getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)){
            //returns true if device has a camera
            return true;
        }else {
            //returns false if device doesn't have a camera
            return false;
        }
    }

    /**
     * captureImage will launch camera app request image capture
     * Creates the intent to take a picture, and then starts it
     */
    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = pictureController.getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        // starts the image capture intent
        startActivityForResult(intent, OBTAIN_PIC_REQUEST_CODE);

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

    /**
     * onActivityResult will Receive the activity result
     * method and will be called after closing the camera,
     * the geolocation, or the commentList. this method 
     * is always called when camera is closed, geolocation
     * is finished, or commentList is finished.
     */
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


                // successfully captured the image
                // display it in image view
                picture = pictureController.previewCapturedImage(fileUri, picture, picImagePreview, comment);
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } 

            else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        }

        if (requestCode == FAVORITE_LIST && resultCode == Activity.RESULT_OK) {
            user = (User) data.getSerializableExtra("CURRENT_USER");
        }
    }

    /**
     * viewFavorites checks to see if you are guest or not
     * since guest cannot have favorites.
     * Then the method starts up the FavoritesListActivity.
     * This activity has yet to be written
     * @param v
     */
    public void viewFavorites(View v) {
        if(user.getUserName().equals("Guest")) {
            Toast.makeText(getApplicationContext(),
                    "Guest Can Not Have Favourites", Toast.LENGTH_LONG).show();
        }

        else {
            Intent intent = new Intent(this, FavoriteActivity.class);
            intent.putExtra("CURRENT_USER", user);   
            startActivity(intent);

        }
    }

    /**
     * commentThread takes in thread and then starts a new
     * activity while passing the contents of thread to the 
     * activity
     * @param thread
     */
    public void commentThread(Comment thread) {
        String stringId = new String();
        stringId = thread.getThreadId();

        Intent intent = new Intent(this, CommentListActivity.class);
        intent.putExtra("THREAD_ID", stringId);
        intent.putExtra("CURRENT_USER", user);                

        startActivityForResult(intent, FAVORITE_LIST);

    }
    
    //http://stackoverflow.com/questions/17242713/how-to-pass-parcelable-object-from-child-to-parent-activity
    public void onBackPressed()
    {
    	
        finish();
       
    }

}
