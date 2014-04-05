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
import java.util.UUID;

import ca.cmput301w14t09.Model.GeoLocation;

/**
 * 
 * @author mcmorris
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

	public User() {
		this.userName = "";
		this.deviceId = "";
	}

	public User(String userName) {
		this.userName = userName;
		this.profile = new Profile(userName);
		this.deviceId = "";
		
		if (userName.equalsIgnoreCase("guest"))
			this.uniqueID="00000".toString();
		else
			this.uniqueID = UUID.randomUUID().toString();
	}

	
	public String getUniqueID(){
		return uniqueID;
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
}
