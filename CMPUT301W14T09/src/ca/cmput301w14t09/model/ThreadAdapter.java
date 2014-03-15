package ca.cmput301w14t09.model;

import java.util.ArrayList;

import ca.cmput301w14t09.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

// http://devtut.wordpress.com/2011/06/09/custom-arrayadapter-for-a-listview-android/

public class ThreadAdapter extends ArrayAdapter<CommentThread>{

    private ArrayList<CommentThread> objects;

    public ThreadAdapter(Context context, int textViewResourceId, ArrayList<CommentThread> objects) {
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
            v = inflater.inflate(R.layout.thread_view, null);
        }

        CommentThread i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tt = (TextView) v.findViewById(R.id.toptext);
            TextView ttd = (TextView) v.findViewById(R.id.toptextdata);
            TextView mt = (TextView) v.findViewById(R.id.middletext);
            TextView mtd = (TextView) v.findViewById(R.id.middletextdata);

            // check to see if each individual textview is null.
            // if not, assign some text!
            if (tt != null){
                tt.setText("Author: ");
            }
            if (ttd != null) {
                if (i.getComments().getFirst() != null) {
                    ttd.setText(i.getComments().getFirst().getAuthorName());
                }
            }
           
            if (mtd != null){
                mtd.setText(i.getName());
            }


        }

        // the view must be returned to our activity
        return v;

    }
}
