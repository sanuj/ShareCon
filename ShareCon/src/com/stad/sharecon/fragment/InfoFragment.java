package com.stad.sharecon.fragment;

import java.io.File;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stad.sharecon.R;
import com.stad.sharecon.model.ContactData;
import com.stad.sharecon.ui.DetailsOutputLayout;
import com.stad.sharecon.ui.ShortOutputLayout;
import com.stad.sharecon.utils.ImageUtil;
import com.stad.sharecon.utils.Utility;

public class InfoFragment extends Fragment {

	private LinearLayout mLayoutMain;

	private ContactData mContactData = null;

	private TextView mTxtViewName;
	private TextView mTxtViewNickname;
	private TextView mTxtViewPhoneticName;

	private ImageView mImageViewPic;

	public InfoFragment(ContactData contactData) {
		mContactData = contactData;
		Log.i("InfoFragment", "Printing log from infofragment");
		mContactData.printLog();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_info, container,
				false);

		mLayoutMain = (LinearLayout) rootView.findViewById(R.id.layout_main);
		mTxtViewName = (TextView) rootView
				.findViewById(R.id.txt_view_complete_name);
		mTxtViewNickname = (TextView) rootView
				.findViewById(R.id.txt_view_nickname);
		mTxtViewPhoneticName = (TextView) rootView
				.findViewById(R.id.txt_view_phonetic_name);
		mImageViewPic = (ImageView) rootView.findViewById(R.id.img_view_pic);
		setDataToViews();
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

	private void setDataToViews() {

		DetailsOutputLayout phoneLayout = null;
		DetailsOutputLayout emailLayout = null;
		DetailsOutputLayout addressLayout = null;
		DetailsOutputLayout imLayout = null;

		if (Utility.getStringInPreferences(getActivity(),
				Constant.PREF_USER_IMAGE) != null) {
			String filePath = (Environment.getExternalStorageDirectory()
					+ File.separator + getResources().getString(R.string.app_name)
					+ File.separator + getResources().getString(
					R.string.picture_storage)).toString();
			Bitmap image = ImageUtil.getImage(filePath
					+ File.separator
					+ Utility.getStringInPreferences(getActivity(),
							Constant.PREF_USER_IMAGE));
			if (image != null)
				mImageViewPic.setImageBitmap(image);
		}
		if (mContactData != null) {

			if (mContactData.getName() != null) {
				mTxtViewName.setText(mContactData.getName());
			}
			if (mContactData.getPhoneticname() != null) {
				mTxtViewPhoneticName.setText(mContactData.getPhoneticname());
			}
			if (mContactData.getNickName() != null) {
				mTxtViewNickname.setText(mContactData.getNickName());
			}

			Log.i("InfoFragment",
					"mContactData total count: " + mContactData.getTotalCount());
			for (int i = 0; i < mContactData.getTotalCount(); i++) {
				String[] inputField = mContactData.getInputField(i);
				if (inputField != null) {
					if (inputField[ContactData.TYPE].equals(Constant.phone)) {
						if (phoneLayout == null) {
							Log.i("InfoFragment", "creating PhoneLayout");
							phoneLayout = new DetailsOutputLayout(
									getActivity(), inputField[ContactData.TYPE]);
						}
						phoneLayout.addOutputField(
								inputField[ContactData.SUB_TYPE],
								inputField[ContactData.INPUT]);
					} else if (inputField[ContactData.TYPE]
							.equals(Constant.email)) {
						if (emailLayout == null) {
							Log.i("InfoFragment", "creating mEmailLayout");
							emailLayout = new DetailsOutputLayout(
									getActivity(), inputField[ContactData.TYPE]);
						}
						emailLayout.addOutputField(
								inputField[ContactData.SUB_TYPE],
								inputField[ContactData.INPUT]);
					} else if (inputField[ContactData.TYPE]
							.equals(Constant.address)) {
						if (addressLayout == null) {
							Log.i("InfoFragment", "creating mAddressLayout");
							addressLayout = new DetailsOutputLayout(
									getActivity(), inputField[ContactData.TYPE]);
						}
						addressLayout.addOutputField(
								inputField[ContactData.SUB_TYPE],
								inputField[ContactData.INPUT]);
					} else if (inputField[ContactData.TYPE].equals(Constant.im)) {
						if (imLayout == null) {
							Log.i("InfoFragment", "creating mImLayout");
							imLayout = new DetailsOutputLayout(getActivity(),
									inputField[ContactData.TYPE]);
						}
						imLayout.addOutputField(
								inputField[ContactData.SUB_TYPE],
								inputField[ContactData.INPUT]);
					}

				}
			}
			if (phoneLayout != null) {
				Log.i("InfoFragment", "adding mPhoneLayout");
				mLayoutMain.addView(phoneLayout);
			}
			if (emailLayout != null) {
				Log.i("InfoFragment", "adding mEmailLayout");
				mLayoutMain.addView(emailLayout);
			}
			if (addressLayout != null) {
				Log.i("InfoFragment", "adding mAddressLayout");
				mLayoutMain.addView(addressLayout);
			}
			if (imLayout != null) {
				Log.i("InfoFragment", "adding mImLayout");
				mLayoutMain.addView(imLayout);
			}

			if (mContactData.getWebsite() != null) {
				mLayoutMain.addView(new ShortOutputLayout(getActivity(),
						getResources().getString(R.string.txt_view_website)
								.toLowerCase(), mContactData.getWebsite()));
			}
			if (mContactData.getInternetCall() != null) {
				mLayoutMain.addView(new ShortOutputLayout(getActivity(),
						getResources().getString(
								R.string.txt_view_internet_call).toLowerCase(),
						mContactData.getInternetCall()));
			}
			if (mContactData.getNotes() != null) {
				mLayoutMain.addView(new ShortOutputLayout(getActivity(),
						getResources().getString(R.string.txt_view_notes)
								.toLowerCase(), mContactData.getNotes()));
			}

		}

	}

}
