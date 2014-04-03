package ca.cmput301w14t09;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.PictureController;
import ca.cmput301w14t09.FileManaging.CommentFactory;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.FileManaging.SerializableBitmap;
import ca.cmput301w14t09.Model.User;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;


public class PopUp {
    protected Dialog dialog;
    protected Activity caller;  
    
    public PopUp(Activity caller) {
        this.caller = caller;
    }
    //  void popUp();
    //  void replyComment(View v);
  
}
