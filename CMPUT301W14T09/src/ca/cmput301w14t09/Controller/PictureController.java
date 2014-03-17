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


package ca.cmput301w14t09.Controller;

import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import ca.cmput301w14t09.TopCommentsActivity;
import ca.cmput301w14t09.model.PictureModelList;

/**
 * PictureController deals with all functions that
 * manipulate the picture to be uploaded to elasticSearch
 *
 */
public class PictureController{
	public static final int MAX_BITMAP_DIMENSIONS = 50;
	public static final int MAX_TEXT_LENGTH = 100;
	
	private PictureModelList model;
	private TopCommentsActivity activity;
		
	public PictureController(PictureModelList model, TopCommentsActivity activity) {
		this.model = model;
		this.activity = activity;
	}

	/**
	 * addPicPost takes a picture and formats it properly to be
	 * stored on elasticSearch
	 * @param pic
	 * @param author
	 * @param Comment
	 */
	public void addPicPost(Bitmap pic, String author, String Comment) {
		
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
	
	/**
	 * clearModel empties the space where the
	 * picture is being stored
	 */
	public void clearModel() {
		this.model.clear();
	}

}