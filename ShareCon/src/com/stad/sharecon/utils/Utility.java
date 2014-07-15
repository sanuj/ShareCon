package com.stad.sharecon.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class Utility {

	public static void setVisibility(int type, View... params) {
		for (int i = 0; i < params.length; i++) {
			params[i].setVisibility(type);
		}
	}

	public static void showToastMessage(Context context, int msg) {
		String stringMsg = context.getResources().getString(msg);
		Toast.makeText(context, stringMsg, Toast.LENGTH_SHORT).show();
	}

	public static void storeInternal(Activity activity, String txt,
			String fileName) {
		try {
			FileOutputStream fos = activity.openFileOutput(fileName,
					Context.MODE_PRIVATE);
			try {
				fos.write(txt.getBytes());
				fos.close();
				Log.i("Utility", "ContactData stored in " + fileName);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Log.i("Utility", activity.getFilesDir().getPath());
	}

	public static String readInternal(Activity activity, String fileName) {
		String data = null;
		try {
			if (activity == null)
				Log.i("Utility", "activity is null");
			FileInputStream fis = activity.openFileInput(fileName);
			try {
				int count = 0, tempInp = 0;
				do {
					tempInp = fis.read();
					count++;
				} while (tempInp != -1);
				Log.i("Utility", "Count: " + count);
				byte[] dataBytes = new byte[count - 1];
				// ArrayList<Byte> bytes = new ArrayList<Byte>();
				fis = activity.openFileInput(fileName);
				fis.read(dataBytes);
				data = new String(dataBytes);
				Log.i("Utility", data);
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}

	public static void hideKeypad(Context context, View view) {

		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
	}

	/* Checks if external storage is available for read and write */
	public static boolean isExternalStorageWritable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			return true;
		}
		return false;
	}

	/* Checks if external storage is available to at least read */
	public static boolean isExternalStorageReadable() {
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)
				|| Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			return true;
		}
		return false;
	}

	

	public static void putStringInPreferences(Activity activity, String key,
			String value) {
		SharedPreferences userData = activity.getPreferences(0);
		SharedPreferences.Editor editor = userData.edit();
		editor.putString(key, value);
		editor.commit();
	}

	public static String getStringInPreferences(Activity activity, String key) {
		SharedPreferences userData = activity.getPreferences(0);
		return userData.getString(key, null);
	}
	
	public static void putBooleanInPreferences(Activity activity, String key, Boolean value) {
		SharedPreferences userData = activity.getPreferences(0);
		SharedPreferences.Editor editor = userData.edit();
		editor.putBoolean(key, value);
		editor.commit();
	}
	
	public static Boolean getBooleanInPreferences(Activity activity, String key) {
		SharedPreferences userData = activity.getPreferences(0);
		return userData.getBoolean(key, false);
	}

}
