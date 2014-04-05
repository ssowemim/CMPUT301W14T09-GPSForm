package ca.cmput301w14t09;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.elasticSearch.ElasticSearchOperations;
import ca.cmput301w14t09.elasticSearch.Server;


public class PopUpEdit extends PopUp {

    public PopUpEdit(Activity caller) {
        super(caller);
    }

    public void popUpEdit(final Comment comment) {
        final Dialog dialog = new Dialog(caller);
        dialog.setContentView(R.layout.pop_up_edit);
        dialog.setTitle("Comment Text");

        Button Submit=(Button)dialog.findViewById(R.id.Submit);
        final EditText commentText=(EditText)dialog.findViewById(R.id.editComment);

        commentText.setHint(comment.getCommentText());
        dialog.show();

        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                String str = "commentText = \\\"" + commentText.getText() + "\\\"\"";

                if(Server.getInstance().isServerReachable(caller)) {
                    try {
                        ElasticSearchOperations.updateComment(comment, str);
                        

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ClientProtocolException e)
                    {

                        e.printStackTrace();
                    } catch (IOException e)
                    {

                        e.printStackTrace();
                    }
                }
                dialog.dismiss();
            }  

        });
    }

}
