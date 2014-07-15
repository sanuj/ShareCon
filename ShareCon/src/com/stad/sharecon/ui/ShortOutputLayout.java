package com.stad.sharecon.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stad.sharecon.R;

public class ShortOutputLayout extends LinearLayout {
	
	public ShortOutputLayout(Context context, String heading, String txtViewTxt) {
		super(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT);
		params.bottomMargin = (int) getResources().getDimension(
				R.dimen.layout_vertical_margin);
		params.topMargin = (int) getResources().getDimension(
				R.dimen.layout_vertical_margin);
		this.setLayoutParams(params);
		this.setOrientation(LinearLayout.VERTICAL);

		TextView txtViewHeading = new TextView(context);
		LayoutParams txtViewHeadingParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		txtViewHeading.setLayoutParams(txtViewHeadingParams);
		txtViewHeading.setText(heading);
		txtViewHeading.setTypeface(null, Typeface.BOLD);
		Log.i("ShortinputLayout",
				"textsize"
						+ getResources().getDimension(R.dimen.txt_size_heading));
		txtViewHeading.setTextSize(TypedValue.COMPLEX_UNIT_PX, (int) getResources()
				.getDimension(R.dimen.txt_size_heading));
		this.addView(txtViewHeading);

		Divider line = new Divider(context);
		this.addView(line);
		
		TextView txtView = new TextView(context);
		LayoutParams txtViewParams = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		txtViewParams.rightMargin = (int) getResources().getDimension(
				R.dimen.txt_view_short_input_margin);
		txtView.setLayoutParams(txtViewParams);
		txtView.setText(txtViewTxt);
		this.addView(txtView);

	}


}
