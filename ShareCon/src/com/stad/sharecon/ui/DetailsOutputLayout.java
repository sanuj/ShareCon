package com.stad.sharecon.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.stad.sharecon.R;
import com.stad.sharecon.fragment.Constant;

/*
 */
public class DetailsOutputLayout extends LinearLayout {

	Context mContext = null;

	public DetailsOutputLayout(Context context, String type) {
		super(context);

		mContext = context;
		// Preparing parent layout = layout_im_input
		this.setTag(type);
		this.setOrientation(LinearLayout.VERTICAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		layoutParams.bottomMargin = (int) getResources().getDimension(
				R.dimen.layout_vertical_margin);
		layoutParams.topMargin = (int) getResources().getDimension(
				R.dimen.layout_vertical_margin);
		this.setLayoutParams(layoutParams);
		this.setOrientation(LinearLayout.VERTICAL);

		// Preparing TextView for heading
		TextView heading = new TextView(context);
		LayoutParams headingParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		heading.setLayoutParams(headingParams);
		if (type.equals(Constant.phone))
			heading.setText(getResources().getString(R.string.txt_view_phone).toLowerCase());
		else if (type.equals(Constant.email))
			heading.setText(getResources().getString(R.string.txt_view_email).toLowerCase());
		else if (type.equals(Constant.address))
			heading.setText(getResources().getString(R.string.txt_view_address).toLowerCase());
		else if (type.equals(Constant.im))
			heading.setText(getResources().getString(R.string.txt_view_im).toLowerCase());
		heading.setTypeface(null, Typeface.BOLD);
		// heading.setTextSize( ,getResources().getDimension(
		// R.dimen.txt_size_heading));
		heading.setTextSize(18);
		this.addView(heading);

		Divider line = new Divider(context);
		this.addView(line);

	}

	public void addOutputField(String spinnerTxt, String txtViewTxt) {
		LinearLayout layout = new LinearLayout(mContext);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		layout.setLayoutParams(layoutParams);

		/* Spinner creation */
		Spinner spinner = new Spinner(mContext);
		spinner.setTag(Constant.spinner_input);
		spinner.setClickable(false);
		LayoutParams spinnerParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		spinnerParams.weight = 2;
		spinner.setLayoutParams(spinnerParams);
		String[] spinnerTxtArray = { spinnerTxt };
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(mContext,
				android.R.layout.simple_spinner_item, spinnerTxtArray);
		dataAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		layout.addView(spinner);

		/* TextView creation */
		TextView txtView = new TextView(mContext);
		LayoutParams txtViewParams = new LayoutParams(0,
				LayoutParams.MATCH_PARENT);
		txtViewParams.weight = 5;
		txtView.setLayoutParams(txtViewParams);
		txtView.setText(txtViewTxt);
		layout.addView(txtView);

		this.addView(layout);

	}

}
