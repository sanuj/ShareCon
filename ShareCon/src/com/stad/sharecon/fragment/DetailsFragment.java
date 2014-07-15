package com.stad.sharecon.fragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.stad.sharecon.R;
import com.stad.sharecon.model.ContactData;
import com.stad.sharecon.ui.DetailsInputField;
import com.stad.sharecon.ui.DetailsInputLayout;
import com.stad.sharecon.ui.ShortInputLayout;
import com.stad.sharecon.utils.ImageUtil;
import com.stad.sharecon.utils.Utility;

public class DetailsFragment extends Fragment implements View.OnClickListener,
		DialogInterface.OnClickListener {

	private ImageView mImgViewName;
	private ImageView mImgViewPic;
	// private ImageView mImgViewPhoneAdd;
	// private ImageView mImgViewEmailAdd;
	// private ImageView mImgViewAddressAdd;

	private Button mButtonAddExtraFields;

	private EditText mEditTxtNamePrefix;
	private EditText mEditTxtFirstName;
	private EditText mEditTxtName;
	private EditText mEditTxtMiddleName;
	private EditText mEditTxtLastName;
	private EditText mEditTxtNameSuffix;

	private LinearLayout mLLayoutInputFields = null;
	private LinearLayout mLLayoutPhoneInput = null;
	private LinearLayout mLLayoutEmailInput = null;
	private LinearLayout mLLayoutAddressInput = null;
	private LinearLayout mLLayoutIMInput = null;
	private LinearLayout mLLayoutInputFieldTriple = null;

	public static final int REGISTER = 0;
	public static final int EDIT = 1;
	private int type;

	private boolean hasCompleteName = false;

	private View mRootView = null;

	private List<View> mInputFieldList;

	private FieldPickerFragment mNewFieldChooser = null;

	private ContactData contactData = null;

	public DetailsFragment(int type, ContactData contactData) {
		Log.i("DetailsFragment",
				"#################--------CONSTRUCTOR CALLED-------########################");
		this.type = type;
		this.contactData = contactData;
		mInputFieldList = new ArrayList<View>();
		mNewFieldChooser = new FieldPickerFragment(this);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// if (savedInstanceState != null) {
		// return super.onCreateView(inflater, container, savedInstanceState);
		// }
		mLLayoutInputFields = null;
		mLLayoutPhoneInput = null;
		mLLayoutEmailInput = null;
		mLLayoutAddressInput = null;
		mLLayoutIMInput = null;
		mLLayoutInputFieldTriple = null;
		Log.i("DetailsFragment", "this.type = " + this.type);
		mRootView = inflater.inflate(R.layout.fragment_details, container,
				false);
		setHasOptionsMenu(true);

		setIdToView();

		makeInitialFields();
		ActionBar actionBar = ((ActionBarActivity) getActivity())
				.getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

		mImgViewName.setOnClickListener(this);
		// mImgViewPhoneAdd.setOnClickListener(this);
		// mImgViewEmailAdd.setOnClickListener(this);
		// mImgViewAddressAdd.setOnClickListener(this);
		mButtonAddExtraFields.setOnClickListener(this);

		return mRootView;
	}

	/*
	 * Getting views from their respective IDs.
	 */
	private void setIdToView() {

		mImgViewName = (ImageView) mRootView.findViewById(R.id.img_view_name);
		mImgViewPic = (ImageView) mRootView.findViewById(R.id.img_view_pic);
		mEditTxtNamePrefix = (EditText) mRootView
				.findViewById(R.id.edit_txt_name_prefix);
		mEditTxtFirstName = (EditText) mRootView
				.findViewById(R.id.edit_txt_first_name);
		mEditTxtName = (EditText) mRootView.findViewById(R.id.edit_txt_name);
		mEditTxtMiddleName = (EditText) mRootView
				.findViewById(R.id.edit_txt_middle_name);
		mEditTxtLastName = (EditText) mRootView
				.findViewById(R.id.edit_txt_last_name);
		mEditTxtNameSuffix = (EditText) mRootView
				.findViewById(R.id.edit_txt_name_suffix);

		// mImgViewPhoneAdd = (ImageView) mRootView
		// .findViewById(R.id.img_view_phone_add);
		// mImgViewEmailAdd = (ImageView) mRootView
		// .findViewById(R.id.img_view_email_add);
		// mImgViewAddressAdd = (ImageView) mRootView
		// .findViewById(R.id.img_view_address_add);
		mLLayoutInputFields = (LinearLayout) mRootView
				.findViewById(R.id.layout_input_fields);
		// mLLayoutPhoneInput = (LinearLayout) mRootView
		// .findViewById(R.id.layout_phone_input);
		// mLLayoutEmailInput = (LinearLayout) mRootView
		// .findViewById(R.id.layout_email_input);
		// mLLayoutAddressInput = (LinearLayout) mRootView
		// .findViewById(R.id.layout_address_input);
		mLLayoutInputFieldTriple = (LinearLayout) mRootView
				.findViewById(R.id.layout_input_field_triple);
		mButtonAddExtraFields = (Button) mRootView
				.findViewById(R.id.button_add_more_fields);

	}

	/*
	 * Adding all the views when the fragment is created.
	 */
	private void makeInitialFields() {
		switch (this.type) {
		case REGISTER:
			Log.i("DetailsFragment", "makeinitialfields called from REGISTER");
			createLayout(Constant.phone, null, null);
			createLayout(Constant.email, null, null);
			createLayout(Constant.address, null, null);
			mImgViewPic.setVisibility(View.GONE);
			// DetailsInputField phoneField = new
			// DetailsInputField(getActivity(),
			// Constant.phone, R.array.phone_options, this);
			// mLLayoutPhoneInput.addView(phoneField);
			// mInputFieldViews.add(phoneField);
			//
			// DetailsInputField emailField = new
			// DetailsInputField(getActivity(),
			// Constant.email, R.array.email_options, this);
			// mLLayoutEmailInput.addView(emailField);
			// mInputFieldViews.add(emailField);
			//
			// DetailsInputField addressField = new
			// DetailsInputField(getActivity(),
			// Constant.address, R.array.address_options, this);
			// mLLayoutAddressInput.addView(addressField);
			// mInputFieldViews.add(addressField);

			ShortInputLayout websiteLayout = new ShortInputLayout(
					getActivity(), R.string.txt_view_website, Constant.website);
			mLLayoutInputFields.addView(websiteLayout);
			mInputFieldList.add(websiteLayout);
			break;
		case EDIT:
			Log.i("DetailsFragment", "makeInitialFields called from EDIT");
			String filePath = (Environment.getExternalStorageDirectory()
					+ File.separator
					+ getResources().getString(R.string.app_name)
					+ File.separator + getResources().getString(
					R.string.picture_storage)).toString();
			Bitmap image = ImageUtil.getImage(filePath
					+ File.separator
					+ Utility.getStringInPreferences(getActivity(),
							Constant.PREF_USER_IMAGE));
			if (image != null)
				mImgViewPic.setImageBitmap(image);
			else
				Log.i("DetailsFragment", "profile pic is null");
				
			mImgViewPic.setOnClickListener(this);
			setDataToViews();
			if (mLLayoutInputFields.findViewWithTag(Constant.phonetic_name) != null) {
				mNewFieldChooser.removeItem(FieldPickerFragment.PHONETIC_NAME);
			}
			if (mLLayoutInputFields.findViewWithTag(Constant.im) != null) {
				mNewFieldChooser.removeItem(FieldPickerFragment.IM);
			}
			if (mLLayoutInputFields.findViewWithTag(Constant.notes) != null) {
				mNewFieldChooser.removeItem(FieldPickerFragment.NOTES);
			}
			if (mLLayoutInputFields.findViewWithTag(Constant.nickname) != null) {
				mNewFieldChooser.removeItem(FieldPickerFragment.NICKNAME);
			}
			if (mLLayoutInputFields.findViewWithTag(Constant.internet_call) != null) {
				mNewFieldChooser.removeItem(FieldPickerFragment.INTERNET_CALL);
			}
			break;
		}
	}

	private void setDataToViews() {
		contactData.printLog();
		if (contactData != null) {

			mEditTxtName.setText(contactData.getName());

			mEditTxtNamePrefix.setText(contactData.getPrefix());
			mEditTxtFirstName.setText(contactData.getFirst());
			mEditTxtMiddleName.setText(contactData.getMiddle());
			mEditTxtLastName.setText(contactData.getLast());
			mEditTxtNameSuffix.setText(contactData.getSuffix());

			Log.i("DetailsFragment",
					"mContactData total count: " + contactData.getTotalCount());
			for (int i = 0; i < contactData.getTotalCount(); i++) {
				String[] inputField = contactData.getInputField(i);
				Log.i("DetailsFragment", inputField.toString());
				if (inputField != null) {
					Log.i("DetailsFragment", "inputField type: "
							+ inputField[ContactData.TYPE]);
					if (inputField[ContactData.TYPE].equals(Constant.phone)) {
						if (mLLayoutPhoneInput == null) {
							Log.i("DetailsFragment", "creating PhoneLayout");
							createLayout(Constant.phone,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						} else {
							Log.i("DetailsFragment", "creating inputField");
							addInputField(mLLayoutPhoneInput,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						}
					} else if (inputField[ContactData.TYPE]
							.equals(Constant.email)) {
						if (mLLayoutEmailInput == null) {
							createLayout(Constant.email,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						} else {
							addInputField(mLLayoutEmailInput,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						}
					} else if (inputField[ContactData.TYPE]
							.equals(Constant.address)) {
						if (mLLayoutAddressInput == null) {
							createLayout(Constant.address,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						} else {
							addInputField(mLLayoutAddressInput,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						}
					} else if (inputField[ContactData.TYPE].equals(Constant.im)) {
						if (mLLayoutIMInput == null) {
							createLayout(Constant.im,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						} else {
							addInputField(mLLayoutIMInput,
									inputField[ContactData.SUB_TYPE],
									inputField[ContactData.INPUT]);
						}
					}

				}
			}

			if (mLLayoutPhoneInput == null)
				createLayout(Constant.phone, null, null);
			if (mLLayoutEmailInput == null)
				createLayout(Constant.email, null, null);
			if (mLLayoutAddressInput == null)
				createLayout(Constant.address, null, null);

			ShortInputLayout websiteLayout = new ShortInputLayout(
					getActivity(), R.string.txt_view_website, Constant.website);
			EditText editTxt = (EditText) websiteLayout
					.findViewWithTag(Constant.edit_txt_input);
			editTxt.setText(contactData.getWebsite());
			mLLayoutInputFields.addView(websiteLayout);
			mInputFieldList.add(websiteLayout);
			if (contactData.getPhoneticname() != null) {
				ShortInputLayout phoneticNameLayout = new ShortInputLayout(
						getActivity(), R.string.txt_view_phonetic_name,
						Constant.phonetic_name);
				editTxt = (EditText) phoneticNameLayout
						.findViewWithTag(Constant.edit_txt_input);
				editTxt.setText(contactData.getPhoneticname());
				mLLayoutInputFields.addView(phoneticNameLayout);
				mInputFieldList.add(phoneticNameLayout);
			}
			if (contactData.getNickName() != null) {
				ShortInputLayout nicknameLayout = new ShortInputLayout(
						getActivity(), R.string.txt_view_nickname,
						Constant.nickname);
				editTxt = (EditText) nicknameLayout
						.findViewWithTag(Constant.edit_txt_input);
				editTxt.setText(contactData.getNickName());
				mLLayoutInputFields.addView(nicknameLayout);
				mInputFieldList.add(nicknameLayout);
			}
			if (contactData.getNotes() != null) {
				ShortInputLayout notesLayout = new ShortInputLayout(
						getActivity(), R.string.txt_view_notes, Constant.notes);
				editTxt = (EditText) notesLayout
						.findViewWithTag(Constant.edit_txt_input);
				editTxt.setText(contactData.getNotes());
				mLLayoutInputFields.addView(notesLayout);
				mInputFieldList.add(notesLayout);
			}
			if (contactData.getInternetCall() != null) {
				ShortInputLayout internetCallLayout = new ShortInputLayout(
						getActivity(), R.string.txt_view_internet_call,
						Constant.internet_call);
				editTxt = (EditText) internetCallLayout
						.findViewWithTag(Constant.edit_txt_input);
				editTxt.setText(contactData.getInternetCall());
				mLLayoutInputFields.addView(internetCallLayout);
				mInputFieldList.add(internetCallLayout);
			}
		}

	}

	private void createLayout(String type, String subType, String input) {
		if (type.equals(Constant.phone)) {
			mLLayoutPhoneInput = new DetailsInputLayout(getActivity(),
					Constant.phone);
			addInputField(mLLayoutPhoneInput, subType, input);
			// if (subType != null) {
			// Spinner spinner = (Spinner) mLLayoutPhoneInput
			// .findViewWithTag(Constant.spinner_input);
			// ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
			// int position = adapter.getPosition(subType);
			// spinner.setSelection(position);
			// }
			// if (input != null) {
			// EditText editTxt = (EditText) mLLayoutPhoneInput
			// .findViewWithTag(Constant.edit_txt_input);
			// editTxt.setText(input);
			// }
			ImageView imgViewAdd = (ImageView) mLLayoutPhoneInput
					.findViewWithTag(Constant.img_view_phone_add);
			if (imgViewAdd != null) {
				imgViewAdd.setOnClickListener(this);
			}
			mLLayoutInputFieldTriple.addView(mLLayoutPhoneInput);
		} else if (type.equals(Constant.email)) {
			mLLayoutEmailInput = new DetailsInputLayout(getActivity(),
					Constant.email);
			addInputField(mLLayoutEmailInput, subType, input);
			// if (subType != null) {
			// Spinner spinner = (Spinner) mLLayoutEmailInput
			// .findViewWithTag(Constant.spinner_input);
			// ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
			// int position = adapter.getPosition(subType);
			// spinner.setSelection(position);
			// }
			// if (input != null) {
			// EditText editTxt = (EditText) mLLayoutEmailInput
			// .findViewWithTag(Constant.edit_txt_input);
			// editTxt.setText(input);
			// }
			ImageView imgViewAdd = (ImageView) mLLayoutEmailInput
					.findViewWithTag(Constant.img_view_email_add);
			if (imgViewAdd != null) {
				imgViewAdd.setOnClickListener(this);
			}
			mLLayoutInputFieldTriple.addView(mLLayoutEmailInput);
		} else if (type.equals(Constant.address)) {
			mLLayoutAddressInput = new DetailsInputLayout(getActivity(),
					Constant.address);
			addInputField(mLLayoutAddressInput, subType, input);
			// if (subType != null) {
			// Spinner spinner = (Spinner) mLLayoutAddressInput
			// .findViewWithTag(Constant.spinner_input);
			// ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
			// int position = adapter.getPosition(subType);
			// spinner.setSelection(position);
			// }
			// if (input != null) {
			// EditText editTxt = (EditText) mLLayoutAddressInput
			// .findViewWithTag(Constant.edit_txt_input);
			// editTxt.setText(input);
			// }
			ImageView imgViewAdd = (ImageView) mLLayoutAddressInput
					.findViewWithTag(Constant.img_view_address_add);
			if (imgViewAdd != null) {
				imgViewAdd.setOnClickListener(this);
			}
			mLLayoutInputFieldTriple.addView(mLLayoutAddressInput);
		} else if (type.equals(Constant.im)) {
			mLLayoutIMInput = new DetailsInputLayout(getActivity(), Constant.im);
			addInputField(mLLayoutIMInput, subType, input);
			// if (subType != null) {
			// Spinner spinner = (Spinner) mLLayoutIMInput
			// .findViewWithTag(Constant.spinner_input);
			// ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
			// int position = adapter.getPosition(subType);
			// spinner.setSelection(position);
			// }
			// if (input != null) {
			// EditText editTxt = (EditText) mLLayoutIMInput
			// .findViewWithTag(Constant.edit_txt_input);
			// editTxt.setText(input);
			// }
			ImageView imgViewAdd = (ImageView) mLLayoutIMInput
					.findViewWithTag(Constant.img_view_im_add);
			if (imgViewAdd != null) {
				imgViewAdd.setOnClickListener(this);
			}
			mLLayoutInputFieldTriple.addView(mLLayoutIMInput);
		}
	}

	/*
	 * Adding buttons to the ActionBar.
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onCreateOptionsMenu(android.view.Menu,
	 * android.view.MenuInflater)
	 */
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.menu_details, menu);
		super.onCreateOptionsMenu(menu, inflater);
	}

	/*
	 * Handling Clicks on ActionBar buttons.
	 * 
	 * @see
	 * android.support.v4.app.Fragment#onOptionsItemSelected(android.view.MenuItem
	 * )
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id) {
		case R.id.menu_item_finish:
			Log.i("DetailsFragment", "menu_item_finish");
			saveContactData();
			Log.i("DetailsFragment",
					"mInputFieldView size: " + mInputFieldList.size());
			for (int i = 0; i < mInputFieldList.size(); i++) {
				Log.i("DetailsFragment", (String) mInputFieldList.get(i)
						.getTag());
			}
			FragmentManager fm = getActivity().getSupportFragmentManager();
			fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
			fm.beginTransaction()
					.replace(R.id.container, new SignInFragment(getActivity()))
					.commit();
			Utility.hideKeypad(getActivity(), mButtonAddExtraFields);
			return true;
		case android.R.id.home:
			getActivity().getSupportFragmentManager().popBackStack();
		}
		return super.onOptionsItemSelected(item);
	}

	/*
	 * Method to save all the data entered by the user when finish is clicked in
	 * the ActionBar.
	 */
	private void saveContactData() {

		ContactData data = new ContactData();
		if (hasCompleteName) {
			String prefix = mEditTxtNamePrefix.getText().toString();
			String first = mEditTxtFirstName.getText().toString();
			String middle = mEditTxtMiddleName.getText().toString();
			String last = mEditTxtLastName.getText().toString();
			String suffix = mEditTxtNameSuffix.getText().toString();
			data.setName(prefix, first, middle, last, suffix);

		} else {
			String name = mEditTxtName.getText().toString();
			data.setName(null, name, null, null, null);
		}

		for (int i = 0; i < mInputFieldList.size(); i++) {
			View parent = mInputFieldList.get(i);
			String tag = (String) parent.getTag();
			if (tag == null) {
				Log.i("DetailsFragment", "Tag is null.");
			}
			if (tag.equals(Constant.phone) || tag.equals(Constant.email)
					|| tag.equals(Constant.address) || tag.equals(Constant.im)) {
				Spinner spinner = (Spinner) parent
						.findViewWithTag(Constant.spinner_input);
				EditText editTxt = (EditText) parent
						.findViewWithTag(Constant.edit_txt_input);
				data.setInputField(tag, spinner.getSelectedItem().toString(),
						editTxt.getText().toString());
			} else if (tag.equals(Constant.website)) {
				data.setWebsite(((EditText) parent
						.findViewWithTag(Constant.edit_txt_input)).getText()
						.toString());
			} else if (tag.equals(Constant.notes)) {
				data.setNotes(((EditText) parent
						.findViewWithTag(Constant.edit_txt_input)).getText()
						.toString());
			} else if (tag.equals(Constant.phonetic_name)) {
				data.setPhoneticName(((EditText) parent
						.findViewWithTag(Constant.edit_txt_input)).getText()
						.toString());
			} else if (tag.equals(Constant.nickname)) {
				data.setnickName(((EditText) parent
						.findViewWithTag(Constant.edit_txt_input)).getText()
						.toString());
			} else if (tag.equals(Constant.internet_call)) {
				data.setInternetCall(((EditText) parent
						.findViewWithTag(Constant.edit_txt_input)).getText()
						.toString());
			}
		}

		data.printLog();
		JSONObject json = data.makeJSON();
		Utility.storeInternal(getActivity(), json.toString(), "user_data");
		Log.i("DetailsFragment-JSON", data.makeJSON().toString());

	}

	/*
	 * Handling Clicks on Buttons and ImageViews
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_view_pic:
			getActivity()
					.getSupportFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(
							R.id.container,
							new ImageFragment(
									ImageFragment.FROM_DETAILS_FRAGMENT_EDIT), "ImageFragment")
					.commit();
			break;
		case R.id.img_view_name:
			if (mEditTxtLastName.getVisibility() == View.VISIBLE) {
				Utility.setVisibility(View.GONE, mEditTxtNamePrefix,
						mEditTxtFirstName, mEditTxtName, mEditTxtMiddleName,
						mEditTxtLastName, mEditTxtNameSuffix);
				mEditTxtName.setVisibility(View.VISIBLE);
				mImgViewName.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_action_expand));
				hasCompleteName = false;
			} else {
				Utility.setVisibility(View.VISIBLE, mEditTxtNamePrefix,
						mEditTxtFirstName, mEditTxtName, mEditTxtMiddleName,
						mEditTxtLastName, mEditTxtNameSuffix);
				mEditTxtName.setVisibility(View.GONE);
				mImgViewName.setImageDrawable(getResources().getDrawable(
						R.drawable.ic_action_collapse));
				hasCompleteName = true;
			}
			break;

		// case R.id.img_view_phone_add:
		// addInputField(mLLayoutPhoneInput);
		// break;
		//
		// case R.id.img_view_email_add:
		// addInputField(mLLayoutEmailInput);
		// break;
		//
		// case R.id.img_view_address_add:
		// addInputField(mLLayoutAddressInput);
		// break;

		case R.id.button_add_more_fields:
			FragmentManager fm = getActivity().getSupportFragmentManager();
			mNewFieldChooser.show(fm, "fieldChooser");
		}

		String tag = (String) v.getTag();
		if (tag != null) {
			Log.i("DetailsFragment", tag);
			if (tag.equals(Constant.img_view_im_add)) {
				addInputField(mLLayoutIMInput, null, null);
			} else if (tag.equals(Constant.img_view_phone_add)) {
				addInputField(mLLayoutPhoneInput, null, null);
			} else if (tag.equals(Constant.img_view_email_add)) {
				addInputField(mLLayoutEmailInput, null, null);
			} else if (tag.equals(Constant.img_view_address_add)) {
				addInputField(mLLayoutAddressInput, null, null);
			} else if (tag.equals(Constant.img_view_subtract)) {
				View viewLayout = (View) v.getParent();
				mInputFieldList.remove(viewLayout);
				((ViewGroup) (viewLayout.getParent())).removeView(viewLayout);
			}
		}
	}

	/*
	 * Adding DetailsInputField like Phone, Email, Address, IM in their
	 * respective layouts.
	 */
	private void addInputField(LinearLayout rootLayout, String subType,
			String input) {

		DetailsInputField field = null;
		// switch (rootLayout.getId()) {
		//
		// case R.id.layout_phone_input:
		// field = new DetailsInputField(getActivity(), Constant.phone,
		// R.array.phone_options, this);
		// rootLayout.addView(field);
		// mInputFieldViews.add(field);
		//
		// break;
		// case R.id.layout_email_input:
		// field = new DetailsInputField(getActivity(), Constant.email,
		// R.array.email_options, this);
		// rootLayout.addView(field);
		// mInputFieldViews.add(field);
		// break;
		//
		// case R.id.layout_address_input:
		// field = new DetailsInputField(getActivity(), Constant.address,
		// R.array.address_options, this);
		// rootLayout.addView(field);
		// mInputFieldViews.add(field);
		// break;
		// }

		String tag = (String) rootLayout.getTag();

		if (tag != null) {
			if (tag.equals(Constant.im)) {
				LinearLayout layout = (LinearLayout) rootLayout
						.findViewWithTag(Constant.im);
				field = new DetailsInputField(getActivity(), Constant.im,
						R.array.im_options, this);
				layout.addView(field);
				mInputFieldList.add(field);
			} else if (tag.equals(Constant.phone)) {
				field = new DetailsInputField(getActivity(), Constant.phone,
						R.array.phone_options, this);
				rootLayout.addView(field);
				mInputFieldList.add(field);
			} else if (tag.equals(Constant.email)) {
				field = new DetailsInputField(getActivity(), Constant.email,
						R.array.email_options, this);
				rootLayout.addView(field);
				mInputFieldList.add(field);
			} else if (tag.equals(Constant.address)) {
				field = new DetailsInputField(getActivity(), Constant.address,
						R.array.address_options, this);
				rootLayout.addView(field);
				mInputFieldList.add(field);
			}
			if (subType != null) {
				Spinner spinner = (Spinner) field
						.findViewWithTag(Constant.spinner_input);
				ArrayAdapter adapter = (ArrayAdapter) spinner.getAdapter();
				int position = adapter.getPosition(subType);
				spinner.setSelection(position);
			}
			if (input != null) {
				EditText editTxt = (EditText) field
						.findViewWithTag(Constant.edit_txt_input);
				editTxt.setText(input);
			}
		}

	}

	/*
	 * Handling DialogBox Clicks
	 * 
	 * @see
	 * android.content.DialogInterface.OnClickListener#onClick(android.content
	 * .DialogInterface, int)
	 */
	@Override
	public void onClick(DialogInterface dialog, int which) {
		String item = mNewFieldChooser.getItem(which);

		if (item.equals(FieldPickerFragment.PHONETIC_NAME)) {
			ShortInputLayout phoneticNameLayout = new ShortInputLayout(
					getActivity(), R.string.txt_view_phonetic_name,
					Constant.phonetic_name);
			mLLayoutInputFields.addView(phoneticNameLayout);
			mInputFieldList.add(phoneticNameLayout);
		} else if (item.equals(FieldPickerFragment.IM)) {
			createLayout(Constant.im, null, null);
		} else if (item.equals(FieldPickerFragment.NICKNAME)) {
			ShortInputLayout nicknameLayout = new ShortInputLayout(
					getActivity(), R.string.txt_view_nickname,
					Constant.nickname);
			mLLayoutInputFields.addView(nicknameLayout);
			mInputFieldList.add(nicknameLayout);
		} else if (item.equals(FieldPickerFragment.INTERNET_CALL)) {
			ShortInputLayout internetCallLayout = new ShortInputLayout(
					getActivity(), R.string.txt_view_internet_call,
					Constant.internet_call);
			mLLayoutInputFields.addView(internetCallLayout);
			mInputFieldList.add(internetCallLayout);
		} else if (item.equals(FieldPickerFragment.NOTES)) {
			ShortInputLayout notesLayout = new ShortInputLayout(getActivity(),
					R.string.txt_view_notes, Constant.notes);
			mLLayoutInputFields.addView(notesLayout);
			mInputFieldList.add(notesLayout);
		}
		mNewFieldChooser.removeItem(item);
		if (mNewFieldChooser.isEmpty()) {
			mButtonAddExtraFields.setVisibility(View.GONE);
		}
	}
}