package ca.cmput301w14t09.model;

import java.util.Date;

import android.graphics.Bitmap;



public class Picture {
	private Bitmap picture;
	private String text;
	private Date timestamp;
		
	protected Picture(Bitmap pic) {
		this.picture = pic;
	}
	
	public Bitmap getPicture() {
		return this.picture;
	}
	
	public Date getTimestamp() {
		return this.timestamp;
	}
	
	public String getText() {
		return this.text;
	}
}
