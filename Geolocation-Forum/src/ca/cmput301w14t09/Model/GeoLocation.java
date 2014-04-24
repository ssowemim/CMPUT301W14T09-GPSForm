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

/**
 * @author Cameron
 * GeoLocation is an object that holds the
 * latitude and longitude of a comment
 *
 */

public class GeoLocation implements Serializable {

	private static final long serialVersionUID = 60L;
	private double latitude;
	private double longitude;
	private String name;

	/**
	 * initiate the Geolocation object with set lat lng as 0 and name as ""
	 *
	 */
	public GeoLocation() {
		latitude = 0.0;
		longitude = 0.0;
		name = "";
	}

	/**
	 * get latitude
	 * @return latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * set latitude
	 * @param latitude
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * get longitude
	 * @return longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * set longitude
	 * @param longitude
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * return name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
