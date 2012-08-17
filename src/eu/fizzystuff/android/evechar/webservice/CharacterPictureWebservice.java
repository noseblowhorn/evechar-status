package eu.fizzystuff.android.evechar.webservice;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class CharacterPictureWebservice {
	public Bitmap GetCharacterPicture(int characterId) {
		try {
			URL url = new URL("http://image.eveonline.com/character/"
					+ Integer.toString(characterId) + "_512.jpg");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			conn.connect();
			int response = conn.getResponseCode();
			if (response != HttpURLConnection.HTTP_OK) {
				throw new IOException("HTTP response code: " + response);
			}
			InputStream is = conn.getInputStream();
			
			return BitmapFactory.decodeStream(is);
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void GetCharacterPictureAsync(int characterId,
			ICharacterPictureCallback callback) {

	}
}
