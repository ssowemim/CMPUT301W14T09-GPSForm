package ca.cmput301w14t09.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import android.graphics.Bitmap;

public class UserProfileModelList {
	private List<UserProfileModel> list;
	
	public UserProfileModelList(){
		list = new ArrayList<UserProfileModel>();
	}
	
	public void addUserProfile(String uniqueID, String fLName, String sex, String phone, 
							   String email, String bio, Bitmap pic){
		
		UserProfileModel uPModel = new UserProfileModel(uniqueID, fLName, sex, phone, email, bio, pic);
		list.add(uPModel);
		
		try {
			ElasticSearchOperations.pushUserProfile(uPModel);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addUserProfileCollections(Collection<UserProfileModel> profiles){
		list.addAll(profiles);
	}
	
	public List<UserProfileModel> getList(){
		return (List<UserProfileModel>) Collections.unmodifiableCollection(list);
	}
}
