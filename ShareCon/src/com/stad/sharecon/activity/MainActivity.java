package com.stad.sharecon.activity;

import java.io.File;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.stad.sharecon.R;
import com.stad.sharecon.fragment.Constant;
import com.stad.sharecon.fragment.NumberFragment;
import com.stad.sharecon.fragment.SignInFragment;
import com.stad.sharecon.utils.Utility;

public class MainActivity extends ActionBarActivity {

	private FrameLayout mFrameLayoutParent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if (Utility.isExternalStorageWritable()) {
			String appStorageName = getResources().getString(R.string.app_name);
			File appStorage = new File(
					Environment.getExternalStorageDirectory(), appStorageName);
			appStorage.mkdirs();

			File pictureStorage = new File(
					Environment.getExternalStorageDirectory() + File.separator
							+ appStorageName, getResources().getString(
							R.string.picture_storage));
			pictureStorage.mkdirs();
		}

		setContentView(R.layout.activity_register);

		mFrameLayoutParent = (FrameLayout) findViewById(R.id.container);
		mFrameLayoutParent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// Utility.hideKeypad(MainActivity.this);
				Utility.hideKeypad(MainActivity.this, v);
			}
		});
		// ActionBar actionBar = getSupportActionBar();
		// actionBar.setDisplayHomeAsUpEnabled(true);

		if (savedInstanceState == null) {
			FragmentTransaction fragmentTransaction = getSupportFragmentManager()
					.beginTransaction();
			if (Utility.getBooleanInPreferences(this,
					Constant.PREF_USER_SIGNED_IN))
				fragmentTransaction.add(R.id.container,
						new SignInFragment(this)).commit();
			else
				fragmentTransaction.add(R.id.container, new NumberFragment())
						.commit();

		}
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		getSupportFragmentManager().findFragmentByTag("ImageFragment")
				.onActivityResult(requestCode, resultCode, data);
	}

}
