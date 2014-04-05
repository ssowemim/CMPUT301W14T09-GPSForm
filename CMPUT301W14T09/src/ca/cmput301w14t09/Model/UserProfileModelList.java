package ca.cmput301w14t09.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import android.graphics.Bitmap;

public class UserProfileModelList {
	private List<UserProfileModel> list;
	
	public UserProfileModelList(){
		this.list = new ArrayList<UserProfileModel>();
	}
	
	public void addUserProfile(String uniqueID, String fLName, String sex, String phone, 
							   String email, String bio, Bitmap pic){
		
		UserProfileModel upModel = new UserProfileModel(uniqueID, fLName, sex, phone, email, bio, pic);
	}
	
	public void addUserProfileCollections(Collection<UserProfileModel> profiles){
		list.addAll(profiles);
	}
	
	public List<UserProfileModel> getList(){
		return (List<UserProfileModel>) Collections.unmodifiableCollection(this.list);
	}

	public void clear() {
		this.list.clear();
	}
}
