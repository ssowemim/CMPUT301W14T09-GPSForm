package ca.cmput301w14t09.model;

import java.util.Collection;



public class Picture {

	/**
	 * @uml.property  name="MAX_SIZE" readOnly="true"
	 */
	private static int MAX_SIZE = 100;

		
		/**
		 */
		public boolean validatePictureSize(android.graphics.Bitmap picture){
			return false;	
		}


		/**
		 * @uml.property   name="comment"
		 * @uml.associationEnd   multiplicity="(0 -1)" inverse="picture:model.Comment"
		 */
		private Collection<Comment> comment;


		/** 
		 * Getter of the property <tt>comment</tt>
		 * @return  Returns the comment.
		 * @uml.property  name="comment"
		 */
		public Collection<Comment> getComment() {
			return comment;
		}


		/** 
		 * Setter of the property <tt>comment</tt>
		 * @param comment  The comment to set.
		 * @uml.property  name="comment"
		 */
		public void setComment(Collection<Comment> comment) {
			this.comment = comment;
		}

}
