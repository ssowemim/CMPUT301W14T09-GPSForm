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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import android.graphics.Bitmap;

/**
 * The user profile model list takes in the final information of the userprofile and pushes this 
 * information onto the server
 * @author Femi
 *
 */
public class UserProfileModelList {
	private List<UserProfileModel> list;
	
	public UserProfileModelList(){
		list = new ArrayList<UserProfileModel>();
	}
	
	/**
	 * This method addds the information into a model, that model is now put into an array list
	 * and then pushes it onto elastic search operation
	 * @param uniqueID
	 * @param fLName
	 * @param sex
	 * @param phone
	 * @param email
	 * @param bio
	 * @param pic
	 */
	public void addUserProfile(String uniqueID, String fLName, String sex, String phone, 
							   String email, String bio, Bitmap pic){
		
		UserProfileModel uPModel = new UserProfileModel(uniqueID, fLName, sex, phone, email, bio, pic);
		list.add(uPModel);
		
		try {
			ElasticSearchOperations.pushUserProfile(uPModel);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds all te profiles into the list
	 * @param profiles
	 */
	public void addUserProfileCollections(Collection<UserProfileModel> profiles){
		list.addAll(profiles);
	}
	
	/**
	 * This returns the list of model, without it being able to change.
	 * @return
	 */
	public List<UserProfileModel> getList(){
		return (List<UserProfileModel>) list;
	}
}
