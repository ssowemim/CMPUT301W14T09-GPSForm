package ca.cmput301w14t09;

import android.app.Activity;
import android.app.Dialog;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.FileManaging.FileSaving;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;
import ca.cmput301w14t09.Model.User;


public class PopUpSelect extends PopUp{

    PopUpReply popUpReply;
    PopUpEdit pop_up_edit;

    public PopUpSelect(Activity caller) {
        super(caller);
    }



    public void popUpSelect(final Activity caller, final Uri fileUri, final LocationController lc1, final GeoLocation selectedgeo, final User user, final String firstComment, String windowName, final Comment comment) {
        final Dialog dialog = new Dialog(caller);
        popUpReply = new PopUpReply(caller);
        pop_up_edit = new PopUpEdit(caller);

        dialog.setContentView(R.layout.pop_up_select);
        dialog.setTitle("Options");

        Button Favourite=(Button)dialog.findViewById(R.id.Favourite);
        Button Edit=(Button)dialog.findViewById(R.id.Edit);
        Button Reply=(Button)dialog.findViewById(R.id.Reply);

        dialog.show();

        Favourite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {

                if(user.getUserName().equals("Guest")) {
                    Toast.makeText(caller.getApplicationContext(),
                            "Guest Can Not Save Favourites", Toast.LENGTH_LONG).show(); 
                } else {
                    user.profile.add(comment);
                    FileSaving.saveUserFile(user, caller);
                }
                dialog.dismiss();
            }
        });

        Edit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                if(user.getUserName().equals(comment.getUserName())) {
                    dialog.dismiss();
                    pop_up_edit.popUpEdit(comment);
                } else {
                    Toast.makeText(caller.getApplicationContext(),
                            "You are not the original Author", Toast.LENGTH_LONG).show();   
                }

            }
        });

        Reply.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v)
            {

                dialog.dismiss();
                popUpReply.popUp(caller, fileUri, lc1, selectedgeo, user, firstComment, "Comment Reply");

            }
        });


    }
}
