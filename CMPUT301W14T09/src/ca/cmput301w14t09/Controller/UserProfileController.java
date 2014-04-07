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


import android.graphics.Bitmap;
import ca.cmput301w14t09.Model.UserProfileModelList;

public class UserProfileController{

	public static final int MAX_BIO_LENGTH = 100;
	public static final int MAX_PHONE_LENGTH = 12;
	public static final int MAX_BITMAP_DIMENSIONS = 50;
	
	private UserProfileModelList model;
	String bio, phone;
	Bitmap pic;
	
	public UserProfileController(UserProfileModelList uPModelList) {
		this.model = uPModelList;
		
	}

	public void finalizeVariables(String uniqueID, String fLName,
			String sex, String phone, String email,
			String bio, Bitmap pic) {
		
		if (pic.getWidth() > MAX_BITMAP_DIMENSIONS || pic.getHeight() > MAX_BITMAP_DIMENSIONS){
			double scalingFactor = pic.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
			if(pic.getHeight() > pic.getWidth())
				scalingFactor = pic.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
			
			int newWidth = (int)Math.round(pic.getWidth() / scalingFactor);
			int newHeight = (int)Math.round(pic.getHeight() / scalingFactor);
			
			pic = Bitmap.createScaledBitmap(pic, newWidth, newHeight, false);
		} 
		
		if (bio.length() > MAX_BIO_LENGTH)
			bio=bio.substring(0, MAX_BIO_LENGTH);
		
		if (phone.length() > MAX_PHONE_LENGTH)
			phone = phone.substring(0, MAX_PHONE_LENGTH);
		
		this.bio =bio;
		this.phone = phone;
		this.pic = pic;
		model.addUserProfile(uniqueID, fLName, sex, phone, email, bio, pic);
	}
	
	/**
	 * @return
	 */
	public String getNewbio(){
		return bio;
	} 
	
	/**
	 * @return
	 */
	public String getNewphone(){
		return phone;
	}
	
	/**
	 * @return
	 */
	public Bitmap getNewPicture(){
		return pic;
	}
}
