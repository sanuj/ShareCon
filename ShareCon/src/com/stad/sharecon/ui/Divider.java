package com.stad.sharecon.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;

import com.stad.sharecon.R;

public class Divider extends View {

	public Divider(Context context) {
		super(context);
		LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,
				(int) getResources().getDimension(R.dimen.line_height));
		layoutParams.bottomMargin = (int) getResources().getDimension(
				R.dimen.line_margin);
		layoutParams.topMargin = (int) getResources().getDimension(
				R.dimen.line_margin);
		this.setLayoutParams(layoutParams);
		this.setBackgroundColor(Color.rgb(192, 192, 192));
		this.setVisibility(View.VISIBLE);

	}

}
