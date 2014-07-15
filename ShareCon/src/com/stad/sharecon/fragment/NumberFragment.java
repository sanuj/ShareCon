package com.stad.sharecon.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.stad.sharecon.R;
import com.stad.sharecon.utils.EditTxtUtil;
import com.stad.sharecon.utils.Utility;

public class NumberFragment extends Fragment implements OnClickListener {

	private EditText mEditTxtNumber;
	private EditText mEditTxtPswd;
	private EditText mEditTxtConfPswd;
	private TextView mTxtViewRegister;
	private TextView mTxtViewSignIn;
	private TextView mTxtViewForgotPswd;
	private Button mButtonSignIn;
	private Button mButtonRegister;
	private Button mButtonCurrentNumber;

	public NumberFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ActionBar actionBar = ((ActionBarActivity) getActivity())
				.getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		actionBar.setHomeButtonEnabled(false);

		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		View rootView = inflater.inflate(R.layout.fragment_number, container,
				false);
		setHasOptionsMenu(true);
		setIdToView(rootView);

		mEditTxtNumber.setOnClickListener(this);
		mTxtViewRegister.setOnClickListener(this);
		mTxtViewSignIn.setOnClickListener(this);
		mButtonRegister.setOnClickListener(this);
		mButtonSignIn.setOnClickListener(this);
		mButtonCurrentNumber.setOnClickListener(this);
		return rootView;
	}

	private void setIdToView(View rootView) {
		mTxtViewRegister = (TextView) rootView
				.findViewById(R.id.txt_view_register);
		mTxtViewSignIn = (TextView) rootView
				.findViewById(R.id.txt_view_sign_in);
		mTxtViewForgotPswd = (TextView) rootView
				.findViewById(R.id.txt_view_forgot_pswd);
		mEditTxtNumber = (EditText) rootView.findViewById(R.id.edit_txt_number);
		mEditTxtPswd = (EditText) rootView.findViewById(R.id.edit_txt_pswd);
		mEditTxtConfPswd = (EditText) rootView
				.findViewById(R.id.edit_txt_conf_pswd);
		mButtonSignIn = (Button) rootView.findViewById(R.id.button_sign_in);
		mButtonRegister = (Button) rootView.findViewById(R.id.button_register);
		mButtonCurrentNumber = (Button) rootView
				.findViewById(R.id.button_current_number);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_register_number, menu);
		super.onCreateOptionsMenu(menu, inflater);

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// int id = item.getItemId();
		// if (id == R.id.menu_item_register) {
		// getActivity().getSupportFragmentManager().beginTransaction()
		// .addToBackStack(null)
		// .replace(R.id.container, new ImageFragment()).commit();
		// return true;
		// }
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.txt_view_register:
			Utility.setVisibility(View.VISIBLE, mEditTxtConfPswd,
					mButtonRegister, mTxtViewSignIn);
			Utility.setVisibility(View.GONE, mButtonSignIn, mTxtViewRegister,
					mTxtViewForgotPswd);
			break;
		case R.id.txt_view_sign_in:
			Utility.setVisibility(View.GONE, mEditTxtConfPswd, mButtonRegister,
					mTxtViewSignIn);
			Utility.setVisibility(View.VISIBLE, mButtonSignIn,
					mTxtViewRegister, mTxtViewForgotPswd);
			break;
		case R.id.button_register:
			if (validate("Register")) {
				Utility.putStringInPreferences(getActivity(),
						Constant.PREF_USER_NUMBER, mEditTxtNumber.getText()
								.toString());
				Utility.putStringInPreferences(getActivity(),
						Constant.PREF_USER_PASSWORD, mEditTxtPswd.getText()
								.toString());
				Utility.putStringInPreferences(getActivity(),
						Constant.PREF_USER_IMAGE, mEditTxtNumber.getText()
								+ "-image.jpeg");
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(
								R.id.container,
								new ImageFragment(
										ImageFragment.FROM_NUMBER_FRAGMENT),
								"ImageFragment").addToBackStack(null).commit();
				Utility.hideKeypad(getActivity(), v);
			}
			break;
		case R.id.button_sign_in:
			if (validate("SignIn")) {
				getActivity()
						.getSupportFragmentManager()
						.beginTransaction()
						.replace(R.id.container,
								new SignInFragment(getActivity())).commit();
				Utility.hideKeypad(getActivity(), v);
			}
			break;
		case R.id.button_current_number:
			TelephonyManager manager = (TelephonyManager) getActivity()
					.getApplicationContext().getSystemService(
							Context.TELEPHONY_SERVICE);
			String line1Number = manager.getLine1Number();
			Log.i("NumberFragment", "Current Number: " + line1Number);
			mEditTxtNumber.setText(line1Number);
		}
	}

	private boolean validate(String type) {
		if (EditTxtUtil.isEmpty(mEditTxtNumber)
				&& EditTxtUtil.isEmpty(mEditTxtPswd)) {
			Utility.showToastMessage(getActivity(),
					R.string.toast_enter_number_pswd);
			return false;
		} else if (EditTxtUtil.isEmpty(mEditTxtNumber)) {
			Utility.showToastMessage(getActivity(), R.string.toast_enter_number);
			return false;
		} else if (EditTxtUtil.isEmpty(mEditTxtPswd)) {
			Utility.showToastMessage(getActivity(), R.string.toast_enter_pswd);
			return false;
		}
		if (type.equals("Register")) {
			if (EditTxtUtil.isEmpty(mEditTxtConfPswd)) {
				Utility.showToastMessage(getActivity(),
						R.string.toast_confirm_pswd);
				return false;
			}
		}
		return true;
	}
}