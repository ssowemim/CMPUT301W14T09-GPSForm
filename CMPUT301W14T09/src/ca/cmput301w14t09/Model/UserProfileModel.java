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

package ca.cmput301w14t09.Model;

import android.graphics.Bitmap;

/**
 * All the information for the user profile are stored in this model.
 * @author ssowemim
 *
 */
public class UserProfileModel {
	
	//Initialize variable
	private String uniqueID;
	private String firstLastName;
	private String sex;
	private String phone;
	private String email;
	private String biography;
	private Bitmap picture;
	
	//bind each variable to each specific field
	public UserProfileModel(String uniqueID, String fLName, String sex, String phone,
							String email, String bio, Bitmap pic){
		this.uniqueID = uniqueID;
		this.firstLastName = fLName;
		this.sex = sex;
		this.phone = phone;
		this.email = email;
		this.biography = bio;
		this.picture = pic;
		
	}
	
	/**
	 * set unique ID
	 * @param uniqueID
	 */
	public void setUniqueID(String uniqueID){
		this.uniqueID = uniqueID;
	}
	
	/**
	 * get the uniqueID
	 * @return uniqueID
	 */
	public String getUniqueID(){
		return uniqueID;
	}
	
	/**
	 * set the first, last name
	 * @param fLName
	 */
	public void setFirstLastName(String fLName){
		this.firstLastName=fLName;
	}
	
	/**
	 * get the first, last name
	 * @return fLName
	 */
	public String getFirstLastName(){
		return firstLastName;
	}
	
	/**
	 * set sex(male/female)
	 * @param sex
	 */
	public void setSex(String sex){
		this.sex=sex;
	}
	
	/**
	 * get sex
	 * @return sex
	 */
	public String getSex(){
		return sex;
	}
	
	/**
	 * set the phone number
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone=phone;
	}
	
	/**
	 * gets the phone number
	 * @return phone
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * set the email
	 * @param email
	 */
	public void setEmail(String email){
		this.email=email;
	}
	
	/**
	 * return the  email
	 * @return email
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * set the biography
	 * @param bio
	 */
	public void setBiography(String bio){
		this.biography=bio;
	}
	
	/**
	 * get the biography
	 * @return biography
	 */
	public String getBiography(){
		return biography;
	}

	/**
	 * set the picture
	 * @param pic
	 */
	public void setPicture(Bitmap pic){
		this.picture=pic;
	}
	
	/**
	 * get the picture
	 * @return picture
	 */
	public Bitmap getPicture(){
		return picture;
	}
}
