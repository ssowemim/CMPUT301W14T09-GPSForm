package view;

import java.util.ArrayList;
import model.Favorite;
import controller.CommentController;


public class ViewFavoritesActivity {

		
		/**
		 */
		public ArrayList<Favorite> getFavorites(){
			return null;
		}

		/**
		 * @uml.property  name="commentController"
		 * @uml.associationEnd  aggregation="composite" inverse="viewFavoritesActivity:controller.CommentController"
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
