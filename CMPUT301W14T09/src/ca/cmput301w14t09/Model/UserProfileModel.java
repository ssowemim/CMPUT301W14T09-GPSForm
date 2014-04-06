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
 * 
 * @author Femi
 *
 */
public class UserProfileModel {
	
	private String uniqueID;
	private String firstLastName;
	private String sex;
	private String phone;
	private String email;
	private String biography;
	private Bitmap picture;
	
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
	 * @param uniqueID
	 */
	public void setUniqueID(String uniqueID){
		this.uniqueID = uniqueID;
	}
	
	/**
	 * @return uniqueID
	 */
	public String getUniqueID(){
		return uniqueID;
	}
	
	/**
	 * @param fLName
	 */
	public void setFirstLastName(String fLName){
		this.firstLastName=fLName;
	}
	
	/**
	 * @return fLName
	 */
	public String getFirstLastName(){
		return firstLastName;
	}
	
	/**
	 * @param sex
	 */
	public void setSex(String sex){
		this.sex=sex;
	}
	
	/**
	 * @return sex
	 */
	public String getSex(){
		return sex;
	}
	
	/**
	 * @param phone
	 */
	public void setPhone(String phone){
		this.phone=phone;
	}
	
	/**
	 * @return phone
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * @param email
	 */
	public void setEmail(String email){
		this.email=email;
	}
	
	/**
	 * @return email
	 */
	public String getEmail(){
		return email;
	}
	
	/**
	 * @param bio
	 */
	public void setBiography(String bio){
		this.biography=bio;
	}
	
	/**
	 * @return biography
	 */
	public String getBiography(){
		return biography;
	}

	/**
	 * @param pic
	 */
	public void setPicture(Bitmap pic){
		this.picture=pic;
	}
	
	/**
	 * @return picture
	 */
	public Bitmap getPicture(){
		return picture;
	}
}
