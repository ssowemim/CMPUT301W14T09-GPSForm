package controller;

import model.Comment;
import view.ViewFavoritesActivity;
import view.MainActivity;
import view.EditCommentActivity;


public class CommentController {

		
		/**
		 */
		public void attachImage(){
		}

		/** 
		 * @uml.property name="comment"
		 * @uml.associationEnd aggregation="composite" inverse="commentController:model.Comment"
		 */
		private Comment comment;

		/** 
		 * Getter of the property <tt>comment</tt>
		 * @return  Returns the comment.
		 * @uml.property  name="comment"
		 */
		public Comment getComment() {
			return comment;
		}

		/** 
		 * Setter of the property <tt>comment</tt>
		 * @param comment  The comment to set.
		 * @uml.property  name="comment"
		 */
		public void setComment(Comment comment) {
			this.comment = comment;
		}

		/**
		 * @uml.property  name="viewFavoritesActivity"
		 * @uml.associationEnd  inverse="commentController:view.ViewFavoritesActivity"
		 */
		private ViewFavoritesActivity viewFavoritesActivity;

		/**
		 * Getter of the property <tt>viewFavoritesActivity</tt>
		 * @return  Returns the viewFavoritesActivity.
		 * @uml.property  name="viewFavoritesActivity"
		 */
		public ViewFavoritesActivity getViewFavoritesActivity() {
			return viewFavoritesActivity;
		}

		/**
		 * Setter of the property <tt>viewFavoritesActivity</tt>
		 * @param viewFavoritesActivity  The viewFavoritesActivity to set.
		 * @uml.property  name="viewFavoritesActivity"
		 */
		public void setViewFavoritesActivity(
				ViewFavoritesActivity viewFavoritesActivity) {
					this.viewFavoritesActivity = viewFavoritesActivity;
				}

		/**
		 * @uml.property  name="mainActivity"
		 * @uml.associationEnd  inverse="commentController:view.MainActivity"
		 */
		private MainActivity mainActivity;

		/**
		 * Getter of the property <tt>mainActivity</tt>
		 * @return  Returns the mainActivity.
		 * @uml.property  name="mainActivity"
		 */
		public MainActivity getMainActivity() {
			return mainActivity;
		}

		/**
		 * Setter of the property <tt>mainActivity</tt>
		 * @param mainActivity  The mainActivity to set.
		 * @uml.property  name="mainActivity"
		 */
		public void setMainActivity(MainActivity mainActivity) {
			this.mainActivity = mainActivity;
		}

		/**
		 * @uml.property  name="editCommentActivity"
		 * @uml.associationEnd  inverse="commentController:view.EditCommentActivity"
		 */
		private EditCommentActivity editCommentActivity;

		/**
		 * Getter of the property <tt>editCommentActivity</tt>
		 * @return  Returns the editCommentActivity.
		 * @uml.property  name="editCommentActivity"
		 */
		public EditCommentActivity getEditCommentActivity() {
			return editCommentActivity;
		}

		/**
		 * Setter of the property <tt>editCommentActivity</tt>
		 * @param editCommentActivity  The editCommentActivity to set.
		 * @uml.property  name="editCommentActivity"
		 */
		public void setEditCommentActivity(
				EditCommentActivity editCommentActivity) {
			this.editCommentActivity = editCommentActivity;
		}

}
