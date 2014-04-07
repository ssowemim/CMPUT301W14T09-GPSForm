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

import java.io.Serializable;

import ca.cmput301w14t09.Model.GeoLocation;

/**
 * 
 * @author Michael, Conner, ssowemim
 * User stores user information, and a reference to profile.
 * The user is not the profile, so profile specific information not
 * stored here.
 * 
 */
public class User implements Serializable {

	public Profile profile;
	private GeoLocation currentLocation;
	private String deviceId;
	private String userName;
	private String uniqueID;
	private static final long serialVersionUID = 1L;		

	/**
	 * The constructor when a user name input is not passed
	 */
	public User() {
		this.userName = "";
		this.deviceId = "";
		this.uniqueID = "007";
	}

	/**
	 * The constructor when a user name input is passed
	 * @param userName
	 */
	public User(String userName) {
		this.userName = userName;
		this.profile = new Profile(userName);
		this.deviceId = "";

		if (userName.equalsIgnoreCase("guest"))
			this.uniqueID="00000".toString();
		else
			this.uniqueID = userName;
	}

	/**
	 * get the uniqueID 
	 * @return uniqueID
	 */
	public String getUniqueID(){
		return uniqueID;
	}

	/**
	 * get the profile
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**set the profile
	 * @param profile the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/**
	 * get the current location
	 * @return the currentLocation
	 */
	public GeoLocation getCurrentLocation() {
		return currentLocation;
	}

	/**
	 * set the current location
	 * @param currentLocation the currentLocation to set
	 */
	public void setCurrentLocation(GeoLocation currentLocation) {
		this.currentLocation = currentLocation;
	}

	/**
	 * get the deviceID
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * set the deviceID
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * get the user name
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * set the user name
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
