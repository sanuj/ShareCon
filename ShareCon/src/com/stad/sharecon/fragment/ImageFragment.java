package com.stad.sharecon.fragment;

import java.io.File;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.stad.sharecon.R;
import com.stad.sharecon.utils.ImageUtil;
import com.stad.sharecon.utils.Utility;

public class ImageFragment extends Fragment implements View.OnClickListener,
		DialogInterface.OnClickListener {

	private ImageView mImgViewPic;
	private int from;

	public static final int FROM_NUMBER_FRAGMENT = 0;
	public static final int FROM_DETAILS_FRAGMENT_EDIT = 1;
	private static final int REQUEST_TAKE_PHOTO = 2;

	private String filePath;

	public ImageFragment(int from) {
		this.from = from;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i("ImageFragment", "*************ONCREATEVIEW CALLED**************");

		View rootView = inflater.inflate(R.layout.fragment_image, container,
				false);

		filePath = (Environment.getExternalStorageDirectory() + File.separator
				+ getResources().getString(R.string.app_name) + File.separator + getResources()
				.getString(R.string.picture_storage)).toString();

		mImgViewPic = (ImageView) rootView.findViewById(R.id.img_view_pic);

		Bitmap image = ImageUtil.getImage(filePath
				+ File.separator
				+ Utility.getStringInPreferences(getActivity(),
						Constant.PREF_USER_IMAGE));
		if (image != null)
			mImgViewPic.setImageBitmap(ImageUtil.rotateBitmap(filePath
					+ File.separator
					+ Utility.getStringInPreferences(getActivity(),
							Constant.PREF_USER_IMAGE)));
		Button buttonChoosePic = (Button) rootView
				.findViewById(R.id.button_choose_pic);
		buttonChoosePic.setOnClickListener(this);
		setHasOptionsMenu(true);
		((ActionBarActivity) getActivity()).getSupportActionBar()
				.setDisplayHomeAsUpEnabled(true);

		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_set_image, menu);
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_next:
			switch (from) {
			case FROM_NUMBER_FRAGMENT:
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.addToBackStack(null)
						.replace(
								R.id.container,
								new DetailsFragment(DetailsFragment.REGISTER,
										null)).commit();
				// new ImageFragment(
				// ImageFragment.FROM_DETAILS_FRAGMENT_EDIT))

				return true;
			case FROM_DETAILS_FRAGMENT_EDIT:
				getActivity().getSupportFragmentManager().popBackStack();
				return true;
			}
		case android.R.id.home:
			getActivity().getSupportFragmentManager().popBackStack();
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.button_choose_pic:
			Log.i("ImageFragment", "creating new ImagePickerFragment");
			FragmentManager fm = getActivity().getSupportFragmentManager();
			DialogFragment newPhotoChooser = new ImagePickerFragment(this);
			newPhotoChooser.show(fm, "photoChooser");
			Log.i("ImageFragment", "showing ImagePickerFragment");
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Log.i("ImageFragment", "onActivityResult launched");
		super.onActivityResult(requestCode, resultCode, data);
		// if (requestCode == Constant.REQUEST_IMAGE_CAPTURE
		// && resultCode == Activity.RESULT_OK) {
		// Bundle extras = data.getExtras();
		// mImage = (Bitmap) extras.get("data");
		// mImgViewPic.setImageBitmap(mImage);
		// }
		Log.i("ImageFragment", "request code: " + requestCode + ", result code: " + resultCode);
		if (data == null) Log.i("ImageFragment", "DATA IS NULL...");
		Log.i("ImageFragment", "Begining if-else for camera and gallery");
		if (requestCode == REQUEST_TAKE_PHOTO) {
			mImgViewPic.setImageBitmap(ImageUtil.rotateBitmap(filePath
					+ File.separator
					+ Utility.getStringInPreferences(getActivity(),
							Constant.PREF_USER_IMAGE)));
		} else if (requestCode == Constant.REQUEST_IMAGE_LOAD
				&& resultCode == Activity.RESULT_OK && data != null) {
			Log.i("ImageFragment", "onActivityResult launched for gallery picture");
			Uri selectedImage = data.getData();
			String[] filePathColumn = { MediaStore.Images.Media.DATA };

			Cursor cursor = getActivity().getContentResolver().query(
					selectedImage, filePathColumn, null, null, null);
			cursor.moveToFirst();

			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
			String picturePath = cursor.getString(columnIndex);
			cursor.close();
			Bitmap image = ImageUtil.rotateBitmap(picturePath);
//			Bitmap image = BitmapFactory.decodeFile(picturePath);
			if (image == null) Log.i("ImageFragment", "image from gallery is null");
			mImgViewPic.setImageBitmap(image);

			String fileName = Utility.getStringInPreferences(getActivity(),
					Constant.PREF_USER_NUMBER) + "-image";
			if (image != null) {
				ImageUtil.saveImageJpeg(image, filePath, fileName);
			}
			// String picturePath contains the path of selected Image
		}
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		switch (which) {
		case Constant.TAKE_PHOTO:
			// Log.i("MainActivity", "in ImagePickerFragment");
			// Intent takePictureIntent = new Intent(
			// MediaStore.ACTION_IMAGE_CAPTURE);
			// if (takePictureIntent.resolveActivity(getActivity()
			// .getPackageManager()) != null)
			// startActivityForResult(takePictureIntent,
			// Constant.REQUEST_IMAGE_CAPTURE);

			Intent takePictureIntent = new Intent(
					MediaStore.ACTION_IMAGE_CAPTURE);
			// Ensure that there's a camera activity to handle the intent
			if (takePictureIntent.resolveActivity(getActivity()
					.getPackageManager()) != null) {
				// Create the File where the photo should go
				File photoFile = null;
				photoFile = new File(filePath
						+ File.separator
						+ Utility.getStringInPreferences(getActivity(),
								Constant.PREF_USER_IMAGE));
				// Continue only if the File was successfully created
				if (photoFile != null) {
					takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
							Uri.fromFile(photoFile));
					startActivityForResult(takePictureIntent,
							REQUEST_TAKE_PHOTO);
				}
			}
			break;
		case Constant.CHOOSE_PHOTO:
			Intent i = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			Log.i("ImageFragment", "startingActivityForResult for loading image from gallery");
			startActivityForResult(i, Constant.REQUEST_IMAGE_LOAD);
			break;
		}
	}

}