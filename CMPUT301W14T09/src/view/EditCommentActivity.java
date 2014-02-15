package view;

import model.Comment;
import controller.CommentController;
import controller.UserController;


public class EditCommentActivity {

	/**
	 * @uml.property  name="comment"
	 * @uml.associationEnd  aggregation="composite" inverse="editCommentActivity:model.Comment"
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
	 * @uml.property  name="commentToEdit"
	 */
	private Comment commentToEdit;

	/**
	 * Getter of the property <tt>commentToEdit</tt>
	 * @return  Returns the commentToEdit.
	 * @uml.property  name="commentToEdit"
	 */
	public Comment getCommentToEdit() {
		return commentToEdit;
	}

	/**
	 * Setter of the property <tt>commentToEdit</tt>
	 * @param commentToEdit  The commentToEdit to set.
	 * @uml.property  name="commentToEdit"
	 */
	public void setCommentToEdit(Comment commentToEdit) {
		this.commentToEdit = commentToEdit;
	}

		
		/**
		 */
		public void PostComment(Comment comment){
		}

		/**
		 * @uml.property  name="commentController"
		 * @uml.associationEnd  aggregation="composite" inverse="editCommentActivity:controller.CommentController"
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

		/**
		 * @uml.property  name="userController"
		 * @uml.associationEnd  inverse="editCommentActivity:controller.UserController"
		 */
		private UserController userController;

		/**
		 * Getter of the property <tt>userController</tt>
		 * @return  Returns the userController.
		 * @uml.property  name="userController"
		 */
		public UserController getUserController() {
			return userController;
		}

		/**
		 * Setter of the property <tt>userController</tt>
		 * @param userController  The userController to set.
		 * @uml.property  name="userController"
		 */
		public void setUserController(UserController userController) {
			this.userController = userController;
		}

		/**
		 * @uml.property  name="userController1"
		 * @uml.associationEnd  inverse="editCommentActivity1:controller.UserController"
		 */
		private UserController userController1;

		/**
		 * Getter of the property <tt>userController1</tt>
		 * @return  Returns the userController1.
		 * @uml.property  name="userController1"
		 */
		public UserController getUserController1() {
			return userController1;
		}

		/**
		 * Setter of the property <tt>userController1</tt>
		 * @param userController1  The userController1 to set.
		 * @uml.property  name="userController1"
		 */
		public void setUserController1(UserController userController1) {
			this.userController1 = userController1;
		}

		/**
		 * @uml.property  name="userController2"
		 * @uml.associationEnd  inverse="editCommentActivity2:controller.UserController"
		 */
		private UserController userController2;

		/**
		 * Getter of the property <tt>userController2</tt>
		 * @return  Returns the userController2.
		 * @uml.property  name="userController2"
		 */
		public UserController getUserController2() {
			return userController2;
		}

		/**
		 * Setter of the property <tt>userController2</tt>
		 * @param userController2  The userController2 to set.
		 * @uml.property  name="userController2"
		 */
		public void setUserController2(UserController userController2) {
			this.userController2 = userController2;
		}
}
