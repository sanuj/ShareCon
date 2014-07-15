package com.stad.sharecon.fragment;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

import com.stad.sharecon.R;

public class FieldPickerFragment extends DialogFragment {

	OnClickListener listener;
	private ArrayList<String> items;
	
	public static final String PHONETIC_NAME = "Phonetic Name";
	public static final String IM = "IM";
	public static final String NOTES = "Notes";
	public static final String NICKNAME = "Nickname";
	public static final String INTERNET_CALL = "Internet Call";
	
	
	public FieldPickerFragment(OnClickListener listener) {
		this.listener = listener;
		Log.i("fieldPickerFragment", "adding items to list");
		items = new ArrayList<String>();
		items.add(PHONETIC_NAME);
		items.add(IM);
		items.add(NOTES);
		items.add(NICKNAME);
		items.add(INTERNET_CALL);
		Log.i("FieldPickerFragment", "items added to list");
		Log.i("FieldPickerFragment List", items.toString());
	}

//	@Override
//	public void onAttach(Activity activity) {
//		this.listener = (OnClickListener) activity;
//		super.onAttach(activity);
//	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.title_more_fields);
		String[] items_string = new String[items.size()];
		items_string = items.toArray(items_string);
		Log.i("FieldPickerFragment", items_string.toString());
		builder.setItems(items_string, listener);
		return builder.create();
	}
	
	public String getItem(int index) {
		return items.get(index);
	}
	
	public void removeItem(String item) {
		this.items.remove(item);
	}
	
	public boolean isEmpty() {
		if (items.size() == 0) {
			return true;
		}
		return false;
	}
}