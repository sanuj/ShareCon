package com.stad.sharecon.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.stad.sharecon.R;

public class ImagePickerFragment extends DialogFragment {

	OnClickListener listener;
	
	public ImagePickerFragment(OnClickListener listener) {
		this.listener = listener;
	}

//	@Override
//	public void onAttach(Activity activity) {
//		this.listener = (OnClickListener) activity;
//		super.onAttach(activity);
//	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.title_choose_pic);
		builder.setItems(R.array.options, listener);

		Log.i("ImagePickerFragment", "Dialog Built");
		return builder.create();
	}

}