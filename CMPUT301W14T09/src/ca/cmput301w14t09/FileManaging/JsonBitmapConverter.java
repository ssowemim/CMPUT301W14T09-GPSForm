package ca.cmput301w14t09.FileManaging;

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

public class JsonBitmapConverter implements JsonDeserializer<Bitmap>,
									JsonSerializer<Bitmap> {

	public static void main(String[] args) {

	}

	@Override
	public JsonElement serialize(Bitmap src, Type typeOfSrc,
			JsonSerializationContext context) {
		
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		src.compress(Bitmap.CompressFormat.JPEG, 80, stream);
		String base64Encoded = Base64.encodeToString(stream.toByteArray(), Base64.NO_WRAP);
		return new JsonPrimitive(base64Encoded);
	}

	@Override
	public Bitmap deserialize(JsonElement src, Type typeOfSrc,
			JsonDeserializationContext context) throws JsonParseException {
		
		String base64Encoded = src.getAsJsonPrimitive().getAsString();
		byte[] data = Base64.decode(base64Encoded, Base64.NO_WRAP);
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}

}
