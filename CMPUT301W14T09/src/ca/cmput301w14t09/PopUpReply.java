package ca.cmput301w14t09;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;


public class PopUpReply extends PopUpComment {
    public PopUpReply(Activity caller) {
        super(caller);
    }

    //@SuppressLint("NewApi")
    public void popUp(View v, final Activity caller, final Uri fileUri, final LocationController lc1, final GeoLocation selectedgeo, final User user, final String firstComment, String windowName) {
    	dialog = new Dialog(caller);

        dialog.setContentView(R.layout.pop_up_comment);
        dialog.setTitle(windowName);


        authorText=(EditText)dialog.findViewById(R.id.authorText);
        commentText=(EditText)dialog.findViewById(R.id.commentText);

        //new Location Controller 
        //final LocationController lc = new LocationController();

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
        addPicImageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // capture picture
                captureImage(fileUri);
                //attachment = true;
            }
        });

        // Checks camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(caller.getApplicationContext(),
                    "No Camera Detected.", Toast.LENGTH_LONG).show();
        }

        dialog.show();

        //update location button
        btnSetLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { 
                Intent intent = new Intent(dialog.getContext(), ChooseLocationActivity.class);
                caller.startActivityForResult(intent, 122);
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
            @Override
            public void onClick(View v) {
                String text1 = commentText.getText().toString();
                String text2 = authorText.getText().toString();
                user.getProfile().setAuthorName(text2);
                FileSaving.saveUserFile(user, caller);

                picture = pictureController.finalizePicture(picture, (ListActivity) caller);
                Boolean hasPicture = pictureController.getHasPicture();

                //check locations to see which one to use
                lc1.checklocations(selectedgeo);

                SerializableBitmap serializePic = new SerializableBitmap(picture);
                comment = CommentFactory.buildReplyComment(lc1, text2, text1, false, serializePic, firstComment, hasPicture);
                
                //reset selected location for comments

                try {
                    ElasticSearchOperations.postThread(comment);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                lc1.resetselectedlocation(selectedgeo);
                System.out.println("Reset selected location back to default" +
                        ":"+selectedgeo.getLatitude());
                
                dialog.dismiss();
            }
        });

    }
    
}
