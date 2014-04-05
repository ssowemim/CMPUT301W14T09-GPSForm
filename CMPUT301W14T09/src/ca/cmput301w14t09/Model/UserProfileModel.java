package ca.cmput301w14t09.Model;

import android.graphics.Bitmap;

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
	
	public void setUniqueID(String uniqueID){
		this.uniqueID = uniqueID;
	}
	
	public String uniqueID(){
		return uniqueID;
	}
	
	public void setFirstLastName(String fLName){
		this.firstLastName=fLName;
	}
	
	public String getFirstLastName(){
		return firstLastName;
	}
	
	public void setSex(String sex){
		this.sex=sex;
	}
	
	public String getSex(){
		return sex;
	}
	
	public void setPhone(String phone){
		this.phone=phone;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setBiography(String bio){
		this.biography=bio;
	}
	
	public String getBiography(){
		return biography;
	}

	public void setPicture(Bitmap pic){
		this.picture=pic;
	}
	
	public Bitmap getPicture(){
		return picture;
	}
}
