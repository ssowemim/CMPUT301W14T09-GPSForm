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

/**
 * This userprofileController is used to making necessary changes to the information entered
 * in the user profile right before it is saved onto the server. Just so we wouln't have a lag
 * in picture being too big or excessive text field inputs.
 * @author ssowemim
 *
 */
public class UserProfileController{

	//initialize max in inputs
	public static final int MAX_BIO_LENGTH = 100;
	public static final int MAX_PHONE_LENGTH = 12;
	public static final int MAX_BITMAP_DIMENSIONS = 50;
	
	//Instance variables
	private UserProfileModelList model;
	String bio, phone;
	Bitmap pic;
	
	/**
	 * Constructor, takes in a model that will be used to finalize the push onto the server
	 * @param uPModelList
	 */
	public UserProfileController(UserProfileModelList uPModelList) {
		this.model = uPModelList;
		
	}

	/**
	 * All the inputs from the userProfile are pushed to finalize variables to make changes
	 * and then push onto the server.
	 * @param uniqueID
	 * @param fLName
	 * @param sex
	 * @param phone
	 * @param email
	 * @param bio
	 * @param pic
	 */
	public void finalizeVariables(String uniqueID, String fLName,
			String sex, String phone, String email,
			String bio, Bitmap pic) {
		
		//Shrinks picture size if too big
		if (pic.getWidth() > MAX_BITMAP_DIMENSIONS || pic.getHeight() > MAX_BITMAP_DIMENSIONS){
			double scalingFactor = pic.getWidth() * 1.0 / MAX_BITMAP_DIMENSIONS;
			if(pic.getHeight() > pic.getWidth())
				scalingFactor = pic.getHeight() * 1.0 / MAX_BITMAP_DIMENSIONS;
			
			int newWidth = (int)Math.round(pic.getWidth() / scalingFactor);
			int newHeight = (int)Math.round(pic.getHeight() / scalingFactor);
			
			pic = Bitmap.createScaledBitmap(pic, newWidth, newHeight, false);
		} 
		
		//Shrinks bio length if its too long
		if (bio.length() > MAX_BIO_LENGTH)
			bio=bio.substring(0, MAX_BIO_LENGTH);
		
		//Shrinks phone length if its too long
		if (phone.length() > MAX_PHONE_LENGTH)
			phone = phone.substring(0, MAX_PHONE_LENGTH);
		
		this.bio =bio;
		this.phone = phone;
		this.pic = pic;
		model.addUserProfile(uniqueID, fLName, sex, phone, email, bio, pic);
	}
	
	/**
	 * returns the new bio, method was made to aid in testing.
	 * @return
	 */
	public String getNewbio(){
		return bio;
	} 
	
	/**
	 * returns the new phone, method was ceated to aid in testing.
	 * @return
	 */
	public String getNewphone(){
		return phone;
	}
	
	/**
	 * returns the same bitmap, with different dimension. Method was created to aid
	 * in testing.
	 * @return
	 */
	public Bitmap getNewPicture(){
		return pic;
	}
}
