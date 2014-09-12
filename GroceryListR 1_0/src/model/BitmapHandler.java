package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap.CompressFormat;
import android.util.Log;

public class BitmapHandler {
	
	static FileOutputStream out;
	
	public static void saveBitmapToFile(String pathName, Bitmap imageBitmap){
		File filename = new File(pathName.substring(0, 41));
		Log.d("DEBUG", "Saving image to: "+pathName.substring(0, 41));
		if(!filename.exists())
			filename.mkdirs();
		filename = new File(pathName);
		try {
			out = new FileOutputStream(filename);
			imageBitmap.compress(CompressFormat.JPEG, 90, out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Bitmap loadFromFile(String filename) {
	      try {
	          File f = new File(filename);
	          if (!f.exists()) { return null; }
	          Bitmap tmp = BitmapFactory.decodeFile(filename);
	          return tmp;
	      } catch (Exception e) {
	          return null;
	      }
	  }
}
