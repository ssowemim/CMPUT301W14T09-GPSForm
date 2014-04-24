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

package ca.cmput301w14t09.FileManaging;

import java.util.Date;
import java.util.UUID;

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.Model.Comment;
import ca.cmput301w14t09.Model.GeoLocation;

/**
 * 
 * @author Conner, Michael
 * CommentFactory creates top comments and reply comments
 * when requested by methods. reply comments has not
 * been completed yet
 * 
 */
public class CommentFactory {

	/**
	 * newComment creates a new top comment and fills out
	 * the required parameters for a top comments
	 * @param lc
	 * @param authorname
	 * @param commentText
	 * @param topComment
	 * @param attachment
	 * @param picture
	 * @return
	 */
	public static Comment buildComment(LocationController lc, String authorname, String commentText, Boolean topComment, SerializableBitmap picture, Boolean hasPicture, String userName) {
		Comment comment = new Comment();
		comment = instantiateComment(comment, lc, authorname, commentText, topComment, picture, hasPicture);
		String date = new String();
		date = removeDateColon(comment.getPostDate().toString());
		comment.setThreadId((comment.getAuthorName() + " " + date));
		comment.setUserName(userName);
		return comment;
	}

	/**
	 * Build Reply creates a comment specific to a reply of a prarent comment
	 * @param lc
	 * @param authorname
	 * @param commentText
	 * @param topComment
	 * @param picture
	 * @param threadId
	 * @param hasPicture
	 * @param userName
	 * @return
	 */
	public static Comment buildReplyComment(LocationController lc, String authorname, String commentText, Boolean topComment, SerializableBitmap picture, String threadId, Boolean hasPicture, String userName) {
		Comment comment = new Comment();
		comment = instantiateComment(comment, lc, authorname, commentText, topComment, picture, hasPicture);
		comment.setThreadId(threadId);
		comment.setUserName(userName);
		comment.setUuid(UUID.randomUUID().toString());
		return comment;
	}

	/**
	 * Sets all the comments information into a comment model
	 * @param comment
	 * @param lc
	 * @param authorname
	 * @param commentText
	 * @param topComment
	 * @param picture
	 * @param hasPicture
	 * @return
	 */
	private static Comment instantiateComment(Comment comment, LocationController lc, String authorname, String commentText, Boolean topComment, SerializableBitmap picture, Boolean hasPicture) {
		comment.setAuthorName(authorname);
		comment.setCommentText(commentText);
		comment.setPicture(picture);
		GeoLocation geo = lc.getGeoLocation();
		comment.setGeoLocation(geo);
		comment.setTopComment(topComment);
		comment.setPostDate(new Date());
		comment.setHasPicture(hasPicture);
		comment.setUuid(UUID.randomUUID().toString());
		return comment;
	}

	/**
	 * Formating for the storing of the date when comment is created
	 * @param date
	 * @return
	 */
	private static String removeDateColon(String date) {
		date = date.replaceAll(":", "");
		return date;
	}

}
