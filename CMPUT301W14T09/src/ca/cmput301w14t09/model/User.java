package ca.cmput301w14t09.model;



public class User {


	private Profile profile;
	private String userName;
	private GeoLocation currentLocation;
	private String deviceId;
	private Profile profile1;
	
	public Profile getProfile() {
		return profile;
	}

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}

	public GeoLocation getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(GeoLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Profile getProfile1() {
		return profile1;
	}

	public void setProfile1(Profile profile1) {
		this.profile1 = profile1;
	}

}
