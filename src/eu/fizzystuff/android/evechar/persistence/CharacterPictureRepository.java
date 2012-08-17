package eu.fizzystuff.android.evechar.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class CharacterPictureRepository {
	private Context context;
	
	public CharacterPictureRepository(Context ctx) {
		context = ctx;
	}
	
	public Bitmap GetPicture(int characterId) {
		String externalStorageState = Environment.getExternalStorageState();
		if (externalStorageState.equals(Environment.MEDIA_MOUNTED) || externalStorageState.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			File dir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
			FileInputStream stream;
			try {
				stream = new FileInputStream(new File(dir, Integer.toString(characterId) + ".jpg"));
			} catch (FileNotFoundException e) {
				return null;
			}
			Bitmap bmp = BitmapFactory.decodeStream(stream);
			return bmp;
		} else {
			return null;
		}
	}
}
