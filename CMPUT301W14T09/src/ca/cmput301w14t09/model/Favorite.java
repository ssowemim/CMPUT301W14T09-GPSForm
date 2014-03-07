package ca.cmput301w14t09.model;

import java.util.Collection;




public class Favorite {

	private Comment comment;
	private Comment comment1;
	private String name;
	private Profile profile;
	private Collection<Profile> profile1;
	private Comment comment2;
	
	public Comment getComment() {
		return comment;
	}

	public Comment getComment1() {
		return comment1;
	}

	public void setComment1(Comment comment1) {
		this.comment1 = comment1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Collection<Profile> getProfile1() {
		return profile1;
	}

	public void setProfile1(Collection<Profile> profile1) {
		this.profile1 = profile1;
	}

	public Comment getComment2() {
		return comment2;
	}

	public void setComment2(Comment comment2) {
		this.comment2 = comment2;
	}

}
