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
 * 
 * @author Femi
 *
 */
public class UserProfileModelList {
	private List<UserProfileModel> list;
	
	public UserProfileModelList(){
		list = new ArrayList<UserProfileModel>();
	}
	
	/**
	 * 
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
	 * 
	 * @param profiles
	 */
	public void addUserProfileCollections(Collection<UserProfileModel> profiles){
		list.addAll(profiles);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<UserProfileModel> getList(){
		return (List<UserProfileModel>) Collections.unmodifiableCollection(list);
	}
}
