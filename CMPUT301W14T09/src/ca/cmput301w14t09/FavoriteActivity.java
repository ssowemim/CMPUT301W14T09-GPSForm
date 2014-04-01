package ca.cmput301w14t09;

import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.PictureModelList;
import ca.cmput301w14t09.Model.ThreadAdapter;
import ca.cmput301w14t09.Model.User;


public class FavoriteActivity extends ListActivity
{

    //Activity request codes to take pictures
    public static final int OBTAIN_PIC_REQUEST_CODE = 117;
    public static final int MEDIA_TYPE_IMAGE = 1;


    PictureController pictureController;

    protected Intent intent;
    protected User user;
    protected Dialog dialog;
    protected ListView favList;
    Comment comment;

    ImageButton addPicImageButton;
    ImageView picImagePreview;
    Bitmap picture = null;

    PictureModelList pictureModel;

    EditText authorText;
    EditText commentText;
    ThreadAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        favList = (ListView)findViewById(android.R.id.list);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.favorite, menu);

        intent = getIntent();
        user = (User) intent.getSerializableExtra("CURRENT_USER"); 
        setList();

        return true;
    }

    /**
     * onResume popluates the listview with results from
     * elasticSearch, finding all of the top comments
     * @param thread
     */

    public void setList() {

        ArrayList<Comment> comments = user.getProfile().getFavorites();
        adapter1 = new ThreadAdapter(this,
                R.layout.thread_view, comments);
        favList.setAdapter(adapter1);
        adapter1.notifyDataSetChanged();

    }

}
