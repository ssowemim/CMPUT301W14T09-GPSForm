package ca.cmput301w14t09.FileManaging;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * SerializableBitmap is a class that takes an existing Bitmap, wraps it
 * in a class that adds standard Serialization and JsonSerialization support such that we do not need to make
 * custom controller calls every time we need to store information for a Bitmap.
 * 
 * See:
 * http://stackoverflow.com/questions/5871482/serializing-and-de-serializing-android-graphics-bitmap-in-java
 * http://stackoverflow.com/questions/6002800/android-serializable-problem
 * @author bilec, mcmorris
 */
public class SerializableBitmap implements JsonDeserializer<Bitmap>, JsonSerializer<Bitmap>, Serializable {
    private static final long serialVersionUID = 111696345129311948L;
    public byte[] imageByteArray;
    public Bitmap bitmap;

    public SerializableBitmap() {
        this.bitmap = null;
    }
    
    public SerializableBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    /**
     * serialize turns a bitmap into a Json primitive type
     * @param src
     * @param typeOfSrc
     * @param context
     * @return 
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
     * @param src
     * @param typeOfSrc
     * @param context
     * @return 
     */
    @Override
    public Bitmap deserialize(JsonElement src, Type typeOfSrc,
            JsonDeserializationContext context) throws JsonParseException {
        String base64Encoded = src.getAsJsonPrimitive().getAsString();
        byte[] data = Base64.decode(base64Encoded, Base64.NO_WRAP);
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    /**
     * writeObject implements Serializable.
     * Converts the Bitmap into a byte array, but that byte-array uses base64Encoding to cut down on filesize.
     * @param out
     * @throws IOException
     */
    private void writeObject(ObjectOutputStream out) throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        if (bitmap != null) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);           
            byte bitmapBytes[] = stream.toByteArray();
            out.write(bitmapBytes, 0, bitmapBytes.length);
        }
    }

    /**
     * readObject creates a new ByteArray stream to hold data from ObjectInputStream, converts it
     * to bytearray, then to String, and uses Base64Decode to read the bitmap as it was serialized
     * (also using Base64Encoding), again, to cut down on filesize.
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int b;
        
        // Read all data from input stream into a new byteStream.
        while((b = in.read()) != -1)
            byteStream.write(b);
        // Cast data from byteStream to array.
        byte bitmapBytes[] = byteStream.toByteArray();
        bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
    }
}