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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Date;
import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;

/**
 * 
 * @author mcmorris
 * Favorites marks the member variable Comment as favorited.  Favorites can have their own favorite names.
 * Primarily included for flexible design going forward.
 * 
 */

public class Favorite  implements Serializable, Comparable<Favorite> {
	private String name;
	private Comment comment;

	/**
	 * compareTo compares this Favorite to another Favorite.
	 * @param otherFavorite - other Favorite object to compare to.
	 * @return - this or otherFavorite, whichever has earlier date.
	 */

	public int compareTo(Favorite otherFavorite) {
		Date compareDate = ((Favorite) otherFavorite).comment.getPostDate();
		return this.comment.getPostDate().compareTo(compareDate);
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}