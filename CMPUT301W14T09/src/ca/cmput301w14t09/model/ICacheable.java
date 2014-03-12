package ca.cmput301w14t09.model.comment;

import android.app.Activity;

/**
 * Extend this interface to create a class that can be written/read to disk.
 * @author mcmorris
 * @param <T> - object to serialize and load from file.
 */
public interface ICacheable<T> {	
	void serialize(String userName, Activity main);
	T load(String userName, String name, Activity main);
}