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

package ca.cmput301w14t09.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import android.graphics.Bitmap;
import android.widget.ArrayAdapter;

/**
 * PictureModelList holds an array of pictures
 *
 */
public class PictureModelList {

	private List<Picture> list;
	private ArrayAdapter<Picture> adapter;

	public PictureModelList(){
		this.list = new ArrayList<Picture>();
	}

	public void addPicture(Bitmap pic, String text, Date timestamp) {
		Picture picPost = new Picture(pic);
		this.list.add(picPost);
		this.adapter.notifyDataSetChanged();
	}

	public void addPicureCollection(Collection<Picture> posts){
		this.list.addAll(posts);
		this.adapter.notifyDataSetChanged();
	}
	public void addPicPost(Bitmap pic) {
		Picture picPost = new Picture(pic);
		this.list.add(picPost);
		this.adapter.notifyDataSetChanged();

	}


	public void addPicPostCollection(Collection<Picture> posts) {
		this.list.addAll(posts);
		this.adapter.notifyDataSetChanged();
	}


	public void clear() {
		this.list.clear();
		this.adapter.notifyDataSetChanged();
	}

	public List<Picture> getList() {
		return Collections.unmodifiableList(this.list);
	}

	public void setAdapter(ArrayAdapter<Picture> adapter) {
		this.adapter = adapter;
	}

}
