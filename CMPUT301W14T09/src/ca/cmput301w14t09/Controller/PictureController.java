package ca.cmput301w14t09.Controller;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ca.cmput301w14t09.PictureActivity;
import ca.cmput301w14t09.model.PictureModelList;


public class PictureController{
	public static final int MAX_BITMAP_DIMENSIONS = 50;
	public static final int MAX_TEXT_LENGTH = 100;
	
	private PictureModelList model;
	private PictureActivity activity;
	
	public PictureController(PictureModelList model, PictureActivity activity) {
		this.model = model;
		this.activity = activity;
	}

	public void addPicPost(Bitmap pic) {
		
		if (pic.getWidth() > MAX_BITMAP_DIMENSIONS || pic.getHeight() > MAX_BITMAP_DIMENSIONS) {
			double scalingFactor = pic.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
			if (pic.getHeight() > pic.getWidth())
				scalingFactor = pic.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
			
			int newWidth = (int)Math.round(pic.getWidth() / scalingFactor);
			int newHeight = (int)Math.round(pic.getHeight() / scalingFactor);
			
			pic = Bitmap.createScaledBitmap(pic, newWidth, newHeight, false);
		}
		this.model.addPicPost(pic);
	}
	
	public void clearModel() {
		this.model.clear();
	}

}