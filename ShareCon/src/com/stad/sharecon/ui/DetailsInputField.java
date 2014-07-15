package com.stad.sharecon.ui;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.stad.sharecon.R;
import com.stad.sharecon.fragment.Constant;

public class DetailsInputField extends LinearLayout {

	// private int spinnerId, editTxtId, imgViewId;
	// private static int index = 0;

	public DetailsInputField(Context context, String type, int string,
			OnClickListener listener) {
		super(context);
		int hint = 0; // dummy 0
		if (type.equals(Constant.phone))
			hint = R.string.edit_txt_phone;
		else if(type.equals(Constant.email))
			hint = R.string.edit_txt_email;
		else if(type.equals(Constant.address))
			hint = R.string.edit_txt_address;
		else if (type.equals(Constant.im))
			hint = R.string.edit_txt_im;
		else if (type.equals(Constant.date))
			hint = R.string.edit_txt_date;

		this.setTag(type);
		this.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		this.setLayoutParams(layoutParams);

		/* Spinner creation */
		Spinner spinner = new Spinner(context);
		spinner.setTag(Constant.spinner_input);
//		spinner.setClickable(false);
		LayoutParams spinnerParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		spinnerParams.weight = 2;
		spinner.setLayoutParams(spinnerParams);
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(context,
				android.R.layout.simple_spinner_item, context.getResources()
						.getStringArray(string));
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		this.addView(spinner);

		/* EditText creation */
		EditText editTxt = new EditText(context);
		editTxt.setTag(Constant.edit_txt_input);
		LayoutParams editTxtParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		editTxtParams.weight = 4;
		editTxt.setLayoutParams(editTxtParams);
		editTxt.setHint(hint);
		this.addView(editTxt);

		/* ImageView creation */
		ImageView imgView = new ImageView(context);
		imgView.setTag(Constant.img_view_subtract);
		LayoutParams imgViewParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		imgViewParams.weight = 1;
		imgViewParams.bottomMargin = imgViewParams.topMargin = imgViewParams.leftMargin = imgViewParams.rightMargin = (int) context
				.getResources().getDimension(R.dimen.button_margin);
		imgView.setLayoutParams(imgViewParams);
		imgView.setImageDrawable(context.getResources().getDrawable(
				R.drawable.subtract_input_field));
		imgView.setOnClickListener(listener);
		this.addView(imgView);
	}
}
