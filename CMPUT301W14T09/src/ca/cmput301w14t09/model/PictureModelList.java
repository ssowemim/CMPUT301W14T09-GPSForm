package ca.cmput301w14t09.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;
import android.widget.ArrayAdapter;


public class PictureModelList {
	private List<Picture> list;
	private ArrayAdapter<Picture> adapter;
	
	public PictureModelList(){
		this.list = new ArrayList<Picture>();
	}
	
	
	public void addPicture(Bitmap pic, String text, Date timestamp) {
		Picture picPost = new Picture(pic, text, timestamp);
		this.list.add(picPost);
		this.adapter.notifyDataSetChanged();
	}
	
	public void addPicureCollection(Collection<Picture> posts){
		this.list.addAll(posts);
		this.adapter.notifyDataSetChanged();
	}
	
	public void clear() {
		this.list.clear();
		this.adapter.notifyDataSetChanged();
	}
	
	public List<Picture> getList() {
		return Collections.unmodifiableList(this.list);
	}
	
	public void setAdapter(ArrayAdapter<Picture> adapter) {
		this.adapter = adapter;
	}
	
}
