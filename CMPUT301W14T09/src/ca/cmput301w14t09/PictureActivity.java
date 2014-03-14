package ca.cmput301w14t09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.model.Picture;
import ca.cmput301w14t09.model.PictureModelList;
import ca.cmput301w14t09.view.PictureAdapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

public class PictureActivity extends Activity{

	public static final int OBTAIN_PIC_REQUEST_CODE = 117;


	EditText searchPostsEditText;
	ImageView addPicImageView;
	ListView pictureList;
	ImageButton addPicImageButton;

	private Bitmap currentPicture;
	PictureModelList model;
	PictureController controller;
	PictureAdapter adapter;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_top_comments);

//		this.searchPostsEditText = (EditText)this.findViewById(R.id.search_posts_edit_text);
	//	this.addPicImageView = (ImageView)this.findViewById(R.id.add_pic_image_view);
		this.addPicImageButton = (ImageButton)this.findViewById(R.id.imageButton1);
		this.pictureList = (ListView)this.findViewById(R.id.aCommentList);

		this.model = new PictureModelList();
		this.controller = new PictureController(this.model, this);
		this.adapter = new PictureAdapter(this, R.layout.pic_post, model.getList());

		this.pictureList.setAdapter(this.adapter);
		this.model.setAdapter(this.adapter);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == OBTAIN_PIC_REQUEST_CODE && resultCode == RESULT_OK) {
			this.currentPicture = (Bitmap)data.getExtras().get("data");
			this.addPicImageView.setImageBitmap(this.currentPicture);
		}
	}


	public void obtainPicture(View view) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		startActivityForResult(intent, OBTAIN_PIC_REQUEST_CODE);
	}


	public void pushPicture(View view) {
		this.controller.addPicPost(this.currentPicture);
		this.addPicImageView.setImageResource(R.drawable.camera);
		this.currentPicture = null;
	}
}
