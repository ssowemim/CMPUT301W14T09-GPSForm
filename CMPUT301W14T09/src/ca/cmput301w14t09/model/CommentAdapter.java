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

package ca.cmput301w14t09.model;

import java.util.ArrayList;

import ca.cmput301w14t09.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

// http://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/

public class CommentAdapter extends ArrayAdapter<Comment>{

    private ArrayList<Comment> objects;

    public CommentAdapter(Context context, int textViewResourceId, ArrayList<Comment> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.comment_view, null);
        }

        Comment i = objects.get(position);

        if (i != null) {
            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView geoLocation = (TextView) v.findViewById(R.id.geoLocation);
            TextView geoLocationData = (TextView) v.findViewById(R.id.geoLocationData);
            TextView authorName = (TextView) v.findViewById(R.id.authorName);
            TextView authorNameData = (TextView) v.findViewById(R.id.authorNameData);
            TextView postDate = (TextView) v.findViewById(R.id.postDate);
            TextView postDateData = (TextView) v.findViewById(R.id.postDateData);
            TextView commentName = (TextView) v.findViewById(R.id.commentName);
            TextView commentData = (TextView) v.findViewById(R.id.commentData);
            Button replyBtn = (Button) v.findViewById(R.id.replyBtn);
            Button favoriteBtn = (Button) v.findViewById(R.id.favoriteBtn);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (geoLocation != null) {
                geoLocation.setText("Location: ");
            }
            if (geoLocationData != null) {
                if (i.getGeoLocation() != null) {
                    geoLocationData.setText(i.getGeoLocation().getName());
                }
            }
            if (authorName != null) {
                authorName.setText("Author: ");
            }
            if (authorNameData != null) {
                if (i.getAuthorName() != null) {
                    authorNameData.setText(i.getAuthorName());
                }
            }
            if (postDate != null) {
                postDate.setText("Post Date: ");
            }
            if (postDateData != null) {
                if (i.getPostDate() != null) {
                    postDateData.setText(i.getPostDate().toString());
                }
            }
            if (commentName != null) {
                commentName.setText("");
            }
            if (commentData != null) {
                if (i.getCommentText() != null) {
                    postDateData.setText(i.getCommentText());
                }
            }

            // FIXME: Need to create delegates for buttons here.

        }

        // the view must be returned to our activity
        return v;

    }
}
