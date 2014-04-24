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


//http://stackoverflow.com/questions/5871482/serializing-and-de-serializing-android-graphics-bitmap-in-java

package ca.cmput301w14t09.Controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.ListActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import ca.cmput301w14t09.R;

public class PictureController extends Activity{
    public static final int OBTAIN_PIC_REQUEST_CODE = 117;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MAX_BITMAP_DIMENSIONS = 60;   
    //Directory name to store captured images
    private static final String IMAGE_DIRECTORY_NAME = "CAMERA";
    public boolean hasPicture=false;
    
    /**
     * Picture Controller, makes adjustment to pictures    
     */
    public PictureController() {
    	
    }
    /**
     * getOutputMediaFileUri Creates the File Uri
     * that will be used to store images
     * @param type
     * @return
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * getOutputMediaFile Returns the image
     * @param v
     */
    private static File getOutputMediaFile(int type) {

        //External Sdcard Location
        File mediaStorageDir = new File(
                Environment
                .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return mediaFile;
    }

    /**
     * previewCaputuredImage displays the image
     * taken into an ImageView for preview
     */
    public Bitmap previewCapturedImage(Uri fileUri, Bitmap picture, ImageView picImagePreview) {
        try {
            picImagePreview.setVisibility(View.VISIBLE);

            //bitmap factory
            BitmapFactory.Options options = new BitmapFactory.Options();

            //down-sizing image into a smaller size will throw exception for larger images
            options.inSampleSize = 8;

            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(), options);
             
            picImagePreview.setImageBitmap(bitmap);
            picture = bitmap;

        } catch(NullPointerException e) {
            e.printStackTrace();
        }
        
        return picture;
    }

    /**
     * finalize picture takes in a picture and an activity.
     * Purpose is to create a scaled picture if picture size is too big
     * and for activity is for being able to retrieve a default image if no picture
     * is taken.
     * @param picture
     * @param activity
     * @return
     */
    public Bitmap finalizePicture(Bitmap picture, ListActivity activity) {
    	if (picture == null) {
    		picture = BitmapFactory.decodeResource(activity.getResources(), R.drawable.no_img);
    		picture = Bitmap.createScaledBitmap(picture, 1, 1, false);
    		hasPicture = false;
    	}
    	else{
    		hasPicture = true;

    		if(picture.getWidth() > MAX_BITMAP_DIMENSIONS || picture.getHeight() > MAX_BITMAP_DIMENSIONS){
    			double scalingFactor = picture.getWidth()*1.0 / MAX_BITMAP_DIMENSIONS;

    			if(picture.getHeight() > picture.getWidth())
    				scalingFactor = picture.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;

    			//new sizes the new picutre will be compressed too.
    			int newWidth = (int)Math.round(picture.getWidth()/scalingFactor);
    			int newHeight = (int)Math.round(picture.getHeight()/scalingFactor);

    			picture = Bitmap.createScaledBitmap(picture, newWidth, newHeight, false);
    		}
    	}
		return picture;
    }

    /**
     * This returns the boolean that checks off if a picture was taken or not.
     * @return
     */
	public Boolean getHasPicture() {
		return hasPicture;
	}


}
