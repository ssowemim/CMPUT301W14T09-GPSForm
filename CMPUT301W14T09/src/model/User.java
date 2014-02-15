package model;

import controller.UserController;



public class User {

	/**
	 * @uml.property  name="profile" readOnly="true"
	 */
	private Profile profile;

	/**
	 * Getter of the property <tt>profile</tt>
	 * @return  Returns the profile.
	 * @uml.property  name="profile"
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @uml.property  name="userName"
	 */
	private String userName;

	/**
	 * Getter of the property <tt>userName</tt>
	 * @return  Returns the userName.
	 * @uml.property  name="userName"
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter of the property <tt>userName</tt>
	 * @param userName  The userName to set.
	 * @uml.property  name="userName"
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @uml.property  name="currentLocation"
	 */
	private GeoLocation currentLocation;

	/**
	 * Getter of the property <tt>currentLocation</tt>
	 * @return  Returns the currentLocation.
	 * @uml.property  name="currentLocation"
	 */
	public GeoLocation getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * Setter of the property <tt>currentLocation</tt>
	 * @param currentLocation  The currentLocation to set.
	 * @uml.property  name="currentLocation"
	 */
	public void setCurrentLocation(GeoLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * @uml.property  name="deviceId"
	 */
	private String deviceId;

	/**
	 * Getter of the property <tt>deviceId</tt>
	 * @return  Returns the deviceId.
	 * @uml.property  name="deviceId"
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * Setter of the property <tt>deviceId</tt>
	 * @param deviceId  The deviceId to set.
	 * @uml.property  name="deviceId"
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @uml.property   name="profile1"
	 * @uml.associationEnd   inverse="user:model.Profile"
	 */
	private Profile profile1;

	/** 
	 * Getter of the property <tt>profile1</tt>
	 * @return  Returns the profile1.
	 * @uml.property  name="profile1"
	 */
	public Profile getProfile1() {
		return profile1;
	}

	/** 
	 * Setter of the property <tt>profile1</tt>
	 * @param profile1  The profile1 to set.
	 * @uml.property  name="profile1"
	 */
	public void setProfile1(Profile profile1) {
		this.profile1 = profile1;
	}

	/** 
	 * @uml.property name="userController"
	 * @uml.associationEnd inverse="user:controller.UserController"
	 */
	private UserController userController;

	/** 
	 * Getter of the property <tt>userController</tt>
	 * @return  Returns the userController.
	 * @uml.property  name="userController"
	 */
	public UserController getUserController() {
		return userController;
	}

	/** 
	 * Setter of the property <tt>userController</tt>
	 * @param userController  The userController to set.
	 * @uml.property  name="userController"
	 */
	public void setUserController(UserController userController) {
		this.userController = userController;
	}

}
