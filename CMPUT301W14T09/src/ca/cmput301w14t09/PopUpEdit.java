package ca.cmput301w14t09;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.cmput301w14t09.Model.Comment;


public class PopUpEdit extends PopUp {

    public PopUpEdit(Activity caller) {
        super(caller);
    }
    
    public void popUpEdit(Comment comment) {
        final Dialog dialog = new Dialog(caller);
        dialog.setContentView(R.layout.pop_up_edit);
        dialog.setTitle("Coment Text");
        
        Button Submit=(Button)dialog.findViewById(R.id.Submit);
        EditText commentText=(EditText)dialog.findViewById(R.id.editComment);
        
        Submit.setOnClickListener(new View.OnClickListener() {
            
            @Override
            public void onClick(View v)
            {
                //ESClient client = new ESClient();
                
                
            }
        });
    }

}
