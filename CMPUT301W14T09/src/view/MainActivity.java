package view;

import java.util.ArrayList;


import ca.geocomment.R;
import ca.geocomment.R.layout;
import ca.geocomment.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import model.Comment;
import model.GeoLocation;
import controller.CommentController;


public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(ca.geocomment.R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

		
		/**
		 */
		public ArrayList<Comment> getCommentsByLocation(GeoLocation location){
			return null;
		}

			
			/**
			 */
			public ArrayList<Comment> getCommentsByPostTime(){
				return null;
			}


			/**
			 * @uml.property  name="commentController"
			 * @uml.associationEnd  aggregation="composite" inverse="mainActivity:controller.CommentController"
			 */
			private CommentController commentController;


			/**
			 * Getter of the property <tt>commentController</tt>
			 * @return  Returns the commentController.
			 * @uml.property  name="commentController"
			 */
			public CommentController getCommentController() {
				return commentController;
			}

			/**
			 * Setter of the property <tt>commentController</tt>
			 * @param commentController  The commentController to set.
			 * @uml.property  name="commentController"
			 */
			public void setCommentController(CommentController commentController) {
				this.commentController = commentController;
			}

}
