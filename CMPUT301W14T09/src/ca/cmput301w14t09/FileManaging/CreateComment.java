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

import ca.cmput301w14t09.Controller.LocationController;
import ca.cmput301w14t09.model.Comment;
import ca.cmput301w14t09.model.GeoLocation;

import java.util.Date;

/**
 * 
 * @author Conner
 * CreateComment creates top comments and reply comments
 * when requested by methods. reply comments has not
 * been completed yet
 * 
 */
public class CreateComment {
	
	/**
	 * newComment creates a new top comment and fills out
	 * the required parameters for a top comments
	 * @param lc
	 * @param authorname
	 * @param commentText
	 * @param topComment
	 * @return
	 */
	public static Comment newComment(LocationController lc, String authorname, String commentText, Boolean topComment) {
		Comment comment = new Comment();
		comment.setAuthorName(authorname);
		comment.setCommentText(commentText);
		
		GeoLocation geo = lc.getGeoLocation();
		comment.setGeoLocation(geo);
		
		comment.setTopComment(topComment);
		comment.setPostDate(new Date());
		comment.setThreadId((comment.getAuthorName() + " " + comment.getPostDate().toString()));
		return comment;
	}

}
