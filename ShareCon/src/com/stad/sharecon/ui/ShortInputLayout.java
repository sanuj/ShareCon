package com.stad.sharecon.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stad.sharecon.R;
import com.stad.sharecon.fragment.Constant;

/*
 * Creates a LinearLayout containing a TextView for heading and an
 * EditText for getting inputs.
 */
public class ShortInputLayout extends LinearLayout {

	public ShortInputLayout(Context context, int headingId, String tag) {
		super(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.bottomMargin = (int) getResources().getDimension(
				R.dimen.layout_vertical_margin);
		params.topMargin = (int) getResources().getDimension(
				R.dimen.layout_vertical_margin);
		this.setLayoutParams(params);
		this.setOrientation(LinearLayout.VERTICAL);
		this.setTag(tag);

		TextView txtView = new TextView(context);
		LayoutParams txtViewParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		txtView.setLayoutParams(txtViewParams);
		txtView.setText(headingId);
		txtView.setTypeface(null, Typeface.BOLD);
		Log.i("ShortinputLayout",
				"textsize"
						+ getResources().getDimension(R.dimen.txt_size_heading));
		txtView.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) getResources()
				.getDimension(R.dimen.txt_size_heading));
		this.addView(txtView);

		Divider line = new Divider(context);
		this.addView(line);

		EditText editTxt = new EditText(context);
		LayoutParams editTxtParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		editTxtParams.rightMargin = (int) getResources().getDimension(
				R.dimen.txt_view_short_input_margin);
		editTxt.setLayoutParams(editTxtParams);
		editTxt.setTag(Constant.edit_txt_input);
		this.addView(editTxt);

	}
}
