package ca.cmput301w14t09.view;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import ca.cmput301w14t09.R;
import ca.cmput301w14t09.model.Picture;


public class PictureAdapter extends ArrayAdapter<Picture>{

	public PictureAdapter(Context context, int resource, List<Picture> model) {
		super(context, resource, model);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(this.getContext());
			convertView = inflater.inflate(R.layout.pic_post, null);
		}

		Picture projectPictureModel = this.getItem(position);
		if (projectPictureModel != null) {

			ImageView picImageView = (ImageView)convertView.findViewById(R.id.pic_image_view);
			if (picImageView != null)
				picImageView.setImageBitmap(projectPictureModel.getPicture());
			
			TextView picText = (TextView)convertView.findViewById(R.id.pic_text);
			if (picText != null)
				picText.setText(projectPictureModel.getText());
			
			TextView picTimestamp = (TextView)convertView.findViewById(R.id.pic_timestamp);
			if (picTimestamp != null)
				picTimestamp.setText(projectPictureModel.getTimestamp().toString());
		}

		return convertView;

	}

}