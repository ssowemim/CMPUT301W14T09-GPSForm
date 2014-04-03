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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.Controller.SortingController;
import ca.cmput301w14t09.FileManaging.CommentFactory;

import ca.cmput301w14t09.FileManaging.FileLoading;

import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.CommentAdapter;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.PictureModelList;
import ca.cmput301w14t09.Model.ThreadAdapter;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.elasticSearch.Server;

/**
 * 
 * @author Conner
 * @editor ChunHan
<<<<<<< HEAD
 * This activity shows the top comment that was selected in a 
 * previous activity and displays all the replies to that comment
=======
 *This activity shows the top comment that was selected in a 
 *previous activity and displays all the replies to that comment
>>>>>>> branch 'master' of https://github.com/Mrbilec/CMPUT301W14T09-GPSForm.git
 *
 * Need to send the user back as a Activity result so the favorite
 * saves to the user profile are sent back as well and favorites
 * are shown immediately
 */
public class CommentListActivity extends ListActivity {

    //Activity request codes to take pictures
    public static final int OBTAIN_PIC_REQUEST_CODE = 117;
    public static final int MEDIA_TYPE_IMAGE = 1;

    //File uri to store Images
    private Uri fileUri;

    PictureController pictureController;

    protected Intent intent;
    protected User user;
    protected Dialog dialog;
    protected ListView aCommentList;
    CommentAdapter adapter;
    Comment comment;

    ImageButton addPicImageButton;
    ImageView picImagePreview;
    Bitmap picture = null;

    PictureModelList pictureModel;

    EditText authorText;
    EditText commentText;

    protected ListView favList;
    protected String firstComment;
    protected CommentListActivity commentActivity;

    //selected geolocation object used for when person selects geolocation fom
    GeoLocation selectedgeo = new GeoLocation();
    GeoLocation selectedgeosort = new GeoLocation();


    //new Location Controller 
    final LocationController lc1 = new LocationController();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        favList = (ListView) findViewById(android.R.id.list);

        favList.setOnItemClickListener(new OnItemClickListener(){

            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

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
            }

        });

        commentActivity = this;
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
        getMenuInflater().inflate(R.menu.comment_list, menu);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("CURRENT_USER");
        firstComment = (String) getIntent().getSerializableExtra("THREAD_ID");
        System.out.println(firstComment);
        populateListView();


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
                ArrayList<Comment> sortedList = sorting.sortCommentsByLocation(lc1, firstComment);
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

    /**
     * onStart populates the listview with results from the elasticSearch
     *  query found in ElasticSearchOperations.pullOneThread(firstComment)
     */
    @Override
    protected void onStart() {
        super.onStart();


    }

    private void populateListView() {
        ArrayList<Comment> commentThread = null;

        try {
            commentThread = ElasticSearchOperations.pullOneThread(firstComment);
            user.profile.cache.add(commentThread);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        adapter = new CommentAdapter(this,
                R.layout.comment_view,
                user.profile.cache.getSubComments(firstComment));

        favList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        //      adapter = new CommentAdapter(this,
        //      R.layout.comment_view, comment);
    }


    //@SuppressLint("NewApi")
    public void replyComment(View v) {

        dialog = new Dialog(this);

        dialog.setContentView(R.layout.pop_up_comment);
        dialog.setTitle("Replies to comments");

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
                //      attachment = true;
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

            }
        });

        //save button
        save.setOnClickListener(new View.OnClickListener() {

            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                String text1 = commentText.getText().toString();
                String text2 = authorText.getText().toString();
                user.getProfile().setAuthorName(text2);
                FileSaving.saveUserFile(user, commentActivity );

                
                picture = pictureController.finalizePicture(picture, commentActivity);

                //check locations to see which one to use
                lc1.checklocations(selectedgeo);
                System.out.println("Sel LAT:"+selectedgeo.getLatitude());

                SerializableBitmap serializePic = new SerializableBitmap(picture);
                comment = CommentFactory.buildReplyComment(lc1, text2, text1, false, serializePic, firstComment);

                //reset selected locaton for comments
                lc1.resetselectedlocation(selectedgeo);

                try
                {
                    ElasticSearchOperations.postThread(comment);
                    Thread.sleep(1000);
                    adapter.notifyDataSetChanged();
                    recreate();

                } catch (InterruptedException e)
                {

                    e.printStackTrace();
                }

                dialog.dismiss();

            }
        });
        onStart();

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


    public void onBackPressed()
    {
        Intent intent = getIntent();
        intent.putExtra("CURERNT_USER", user);
        setResult(Activity.RESULT_OK, intent);
        super.onBackPressed();

    }


}
