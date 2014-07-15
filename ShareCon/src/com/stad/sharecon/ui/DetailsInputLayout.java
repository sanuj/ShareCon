package com.stad.sharecon.ui;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stad.sharecon.R;
import com.stad.sharecon.fragment.Constant;

/*
 * Creates a LinearLayout, containing another LinearLayout for IM Heading and
 * Add Button, and a DetailedInputField consisting of a Spinner, an EditText and a Subtract button.
 */
public class DetailsInputLayout extends LinearLayout {

	public DetailsInputLayout(Context context, String type) {
		super(context);

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

		// Preparing child layout for heading and add button
		LinearLayout layout = new LinearLayout(context);
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));
		layout.setOrientation(LinearLayout.HORIZONTAL);

		// Preparing TextView for heading
		TextView heading = new TextView(context);
		LayoutParams headingParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		headingParams.weight = 6;
		heading.setLayoutParams(headingParams);

		if (type.equals(Constant.phone))
			heading.setText(R.string.txt_view_phone);
		else if (type.equals(Constant.email))
			heading.setText(R.string.txt_view_email);
		else if (type.equals(Constant.address))
			heading.setText(R.string.txt_view_address);
		else if (type.equals(Constant.im))
			heading.setText(R.string.txt_view_im);
		heading.setTypeface(null, Typeface.BOLD);

		heading.setTextSize(18);
		layout.addView(heading);

		
		// Preparing ImageView for add button
		ImageView imageAdd = new ImageView(context);
		if (type.equals(Constant.phone))
			imageAdd.setTag(Constant.img_view_phone_add);
		else if (type.equals(Constant.email))
			imageAdd.setTag(Constant.img_view_email_add);
		else if (type.equals(Constant.address))
			imageAdd.setTag(Constant.img_view_address_add);
		else if (type.equals(Constant.im))
			imageAdd.setTag(Constant.img_view_im_add);

		imageAdd.setImageDrawable(getResources().getDrawable(
				R.drawable.add_input_field));
		LayoutParams imageAddParams = new LayoutParams(0,
				LayoutParams.WRAP_CONTENT);
		imageAddParams.bottomMargin = (int) getResources().getDimension(
				R.dimen.button_margin);
		imageAddParams.rightMargin = (int) getResources().getDimension(
				R.dimen.button_margin);
		imageAddParams.leftMargin = (int) getResources().getDimension(
				R.dimen.button_margin);
		imageAddParams.topMargin = (int) getResources().getDimension(
				R.dimen.button_margin);
		imageAddParams.weight = 1;
		imageAdd.setLayoutParams(imageAddParams);
		layout.addView(imageAdd);
		this.addView(layout);

		Divider line = new Divider(context);
		this.addView(line);

	}

}
