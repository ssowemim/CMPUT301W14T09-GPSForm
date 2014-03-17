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

package ca.cmput301w14t09.view;

import ca.cmput301w14t09.model.*;


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
}
