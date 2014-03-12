package ca.cmput301w14t09.model;

import java.io.Serializable;

import ca.cmput301w14t09.model.GeoLocation;


/**
 * Stores user information, and a reference to profile.
 * The user is not the profile, so profile specific information not
 * stored here.
 * 
 * @author mcmorris
 */
public class User implements Serializable {
	private Profile profile;
	private GeoLocation currentLocation;
	private String deviceId;
	
	private String userName;
	private String authorName;
	
	private static final long serialVersionUID = 1L;		
	
	public User() {
		
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * @return the currentLocation
	 */
	public GeoLocation getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(GeoLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
