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

package ca.cmput301w14t09.elasticSearch;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Type;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * This code was borrowed from @zjullion to aid in being able to serialize bitmap pictures. 
 * Provides custom base64 serialization / deserialization for Bitmaps.
 * Algorithm taken from: http://stackoverflow.com/questions/9224056/android-bitmap-to-base64-string
 * @author zjullion, ssowemim
 */

/**
 * 
 * JsonBitmapConverter takes a bitmap and converts it into json
 *
 */

public class JsonBitmapConverter implements JsonDeserializer<Bitmap>,
JsonSerializer<Bitmap> {

	public static void main(String[] args) {

	}

	/**
	 * serialize turns a bitmap into a Json primivite type
	 */

	@Override
	public JsonElement serialize(Bitmap src, Type typeOfSrc,
			JsonSerializationContext context) {

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		src.compress(Bitmap.CompressFormat.JPEG, 80, stream);		
		String base64Encoded = Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
		return new JsonPrimitive(base64Encoded);
	}

	/**
	 * deserialize turns a Json primitive into a bitmap
	 */

	@Override
	public Bitmap deserialize(JsonElement src, Type typeOfSrc,
			JsonDeserializationContext context) throws JsonParseException {

		String base64Encoded = src.getAsJsonPrimitive().getAsString();
		byte[] data = Base64.decode(base64Encoded, Base64.NO_WRAP);
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

}
