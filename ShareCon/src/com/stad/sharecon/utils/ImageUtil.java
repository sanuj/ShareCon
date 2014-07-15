package com.stad.sharecon.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

public class ImageUtil {

	public static void saveImageJpeg(Bitmap finalBitmap, String filePath,
			String fileName) {
		File myDir = new File(filePath);
		myDir.mkdirs();
		// Random generator = new Random();
		// int n = 10000;
		// n = generator.nextInt(n);
		File file = new File(myDir, fileName + ".jpeg");
		if (file.exists())
			file.delete();
		try {
			FileOutputStream out = new FileOutputStream(file);
			finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Bitmap getImage(String filePath) {

		File imgFile = new File(filePath);

		if (imgFile.exists()) {
			return BitmapFactory.decodeFile(imgFile.getAbsolutePath());
		} else {
			Log.i("Utility", "Image file not present.");
		}
		return null;
	}

	public static int getExifOrientation(String filepath) {
		int degree = 0;
		ExifInterface exif = null;
		try {
			exif = new ExifInterface(filepath);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		if (exif != null) {
			int orientation = exif.getAttributeInt(
					ExifInterface.TAG_ORIENTATION, -1);
			if (orientation != -1) {
				// We only recognize a subset of orientation tag values.
				switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
				}

			}
		}

		return degree;
	}

	public static Bitmap rotateBitmap(String filePath) {
		int degree = ImageUtil.getExifOrientation(filePath);
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		Bitmap image = BitmapFactory.decodeFile(filePath);
		if (image != null)
			return Bitmap.createBitmap(image, 0, 0, image.getWidth(),
					image.getHeight(), matrix, true);
		return null;
	}

}
