package ca.cmput301w14t09.view;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;
import ca.cmput301w14t09.model.Picture;


public class PictureAdapter extends ArrayAdapter<Picture>{

	public PictureAdapter(Context context, int resource, List<Picture> model) {
		super(context, resource, model);
	}
}
