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

package ca.cmput301w14t09.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @author Conner
 * Caching model for to allow saving for the comments 
 * 
 */
public class Cache implements Serializable {

    private static final long serialVersionUID = 3735065364081988008L;
    private static final int MAX_LENGTH = 200;
    public ArrayList<Comment> comments;

    public Cache() {
        comments = new ArrayList<Comment>(MAX_LENGTH);
    }

    public ArrayList<Comment> getCache(){
        return comments;
    }

    /**
     * Add a comment to the cache.
     * @param comment
     */
    public void add(Comment comment) {
        if (comment == null) return;
        if (existsInCache(comment) == false) {
            if (comments.size() < MAX_LENGTH) {
                comments.add(comment);
            }
            else {
                replaceTail(comment);
            } 
        }
    } 

    /**
     * Simplifies Cache by allowing us to pass in an ArrayList each element
     * of which is parsed using add(single comment).
     * @param comments
     */
    public void add(ArrayList<Comment> newComments) {
        if (newComments == null) return;

        for(int index = 0; index < MAX_LENGTH && index < newComments.size(); index++) {
            add(newComments.get(index));
        }
    }

    /**
     * In the event comment list size is exceeded, replace tail comment with newly cached comment.
     * Called by add(comment) if max size exceeded.
     * @param comment
     */
    private void replaceTail(Comment comment) {
        int tailIndex = MAX_LENGTH - 1;
        comments.remove(tailIndex);
        comments.add(tailIndex, comment);
        Collections.rotate(comments, 1);
    }

    /**
     * Checks if a comment text for a new comment is already cached.
     * @param comment
     * @return
     */
    private boolean existsInCache(Comment comment) {
        boolean found = false;
        if(comments.size() > 0) {
            for(int index = 0; index < MAX_LENGTH && index < comments.size(); index++) {
                if(comment.getUuid().equals(comments.get(index).getUuid())) {   
                    found = true;
                    replaceComment(index, comment);
                    break;
                }
            }
        }
        return found;
    }
    
    /**
     * replaces the old comment in the cache with the new comment
     * @param index
     * @param comment
     */
    private void replaceComment(int index, Comment comment) {
        comments.remove(index);
        comments.add(comment);
    }

    /**
     * Get a list of all top-comments from cache.
     * @return the comments
     */
    public ArrayList<Comment> getTopComments(boolean isTopComment) {
        ArrayList<Comment> topComments = new ArrayList<Comment>();
        for(int index = 0; index < MAX_LENGTH && index < comments.size(); index++) {
            if(comments.get(index).getTopComment() == true) {
                topComments.add(comments.get(index));
            }
        }
        return topComments;
    }

    /**
     * Get a sub-list of all comments with the specified thread id from cache.
     * @return the comments
     */
    public ArrayList<Comment> getSubComments(String threadId) {
        ArrayList<Comment> commonThreadComments = new ArrayList<Comment>();

        for(int index = 0; index < MAX_LENGTH && index < comments.size(); index++) {
            if(comments.get(index).getThreadId().equals(threadId)) {
                commonThreadComments.add(comments.get(index));
            }
        }
        return commonThreadComments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }
    
  
}
