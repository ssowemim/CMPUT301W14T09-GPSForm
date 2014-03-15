package ca.cmput301w14t09;


import java.util.ArrayList;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.CommentAdapter;
import ca.cmput301w14t09.model.User;

/**
 * 
 * @author Conner
 * @editor Chun-Han Lee
 *This activity will need to be implemented when we can generate comments 
 *and add them to the list view on the TopComments Activity
 *
 */
public class CommentListActivity extends ListActivity implements OnClickListener{

    //final CountDownLatch latch = new CountDownLatch(1);
    
    protected ListView favList;
    protected Dialog dialog;
    protected User user;
    protected Intent intent;
    protected String firstComment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);
        favList = (ListView) findViewById(android.R.id.list);
/*
        favList.setOnItemClickListener(new OnItemClickListener(){

           public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {

                String getCommentText = (String) (favList.getItemAtPosition(arg2)); 
                dialog = new Dialog(CommentListActivity.this);

                dialog.setContentView(R.layout.pop_up_favorite);
                dialog.setTitle("Favorite Dialog");


                Button favorites=(Button)dialog.findViewById(R.id.favButton);



                dialog.show();

                //cancel button
                favorites.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();

                    }
                });
            }

        }); */


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comment_list, menu);
                
        firstComment = (String) getIntent().getSerializableExtra("THREAD_ID");
        System.out.println(firstComment);
        
        
        return true;
    }
    
    // From TopCommentsActivity code 
    @Override
    protected void onStart() {
        super.onStart();
        
        try {
            ArrayList<Comment> comment = ElasticSearchOperations.pullOneThread(firstComment);
                       
            CommentAdapter adapter = new CommentAdapter(this,
                    R.layout.comment_view, comment);
            favList.setAdapter(adapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }



    public void saveFavorites(View v){
        onStart();
    }

    // Needs to be present for clicking 
    @Override
    public void onClick(View arg0) {

    }


}
