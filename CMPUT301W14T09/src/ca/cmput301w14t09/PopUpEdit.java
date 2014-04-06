/**

License GPLv3: GNU GPL Version 3
<http://gnu.org/licenses/gpl.html>.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
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

    /**
     * Creates a popup edit window with the given input parameters.
     * @param comment - edited comment.
     */
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
					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				dialog.dismiss();
			}  
		});
	}

}
