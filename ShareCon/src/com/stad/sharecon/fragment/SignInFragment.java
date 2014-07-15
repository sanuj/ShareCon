package com.stad.sharecon.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.stad.sharecon.R;
import com.stad.sharecon.adapter.TabsPagerAdapter;
import com.stad.sharecon.model.ContactData;
import com.stad.sharecon.utils.Utility;

public class SignInFragment extends Fragment {

	private ViewPager mPager;
	private TabsPagerAdapter mPagerAdapter;
	private ActionBar mActionBar;
	private ContactData contactData;

	public SignInFragment(Activity activity) {
		Utility.putBooleanInPreferences(activity, Constant.PREF_USER_SIGNED_IN,
				true);
		String userDataJson = Utility.readInternal(activity, "user_data");
		this.contactData = ContactData.makeInstance(userDataJson);
		Log.i("SignInFragment", "*****CONSTRUCTER CALLED**********");
		this.contactData.printLog();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i("SignInFragment", "*************ONCREATEVEIW CALLED*************");

		View rootView = inflater.inflate(R.layout.fragment_sign_in, container,
				false);

		mPager = (ViewPager) rootView.findViewById(R.id.pager);
		mPagerAdapter = new TabsPagerAdapter(this.getChildFragmentManager(),
				contactData);
		mPager.setAdapter(mPagerAdapter);

		mActionBar = ((ActionBarActivity) getActivity()).getSupportActionBar();
		mActionBar.setDisplayHomeAsUpEnabled(false);
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mActionBar.setHomeButtonEnabled(false);
		ActionBar.TabListener tabListener = new ActionBar.TabListener() {
			public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
				mPager.setCurrentItem(tab.getPosition());
			}

			public void onTabUnselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
			}

			public void onTabReselected(ActionBar.Tab tab,
					FragmentTransaction ft) {
			}
		};
		if (mActionBar.getTabCount() == 0) {
			mActionBar.addTab(mActionBar.newTab().setText("Info")
					.setTabListener(tabListener));
			mActionBar.addTab(mActionBar.newTab().setText("Sync")
					.setTabListener(tabListener));
			mActionBar.addTab(mActionBar.newTab().setText("Help")
					.setTabListener(tabListener));
		}
		setHasOptionsMenu(true);

		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				Log.i("SigninFragment", "ViewPager sweeped: " + position);
				mActionBar.setSelectedNavigationItem(position);

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		return rootView;
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.menu_sign_in, menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_item_edit:
			getActivity()
					.getSupportFragmentManager()
					.beginTransaction()
					.addToBackStack(null)
					.replace(
							R.id.container,
							new DetailsFragment(DetailsFragment.EDIT,
									contactData), "DetailsFragment").commit();
			return true;
		case R.id.menu_item_sign_out:
			Utility.putBooleanInPreferences(getActivity(),
					Constant.PREF_USER_SIGNED_IN, false);
			getActivity().getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, new NumberFragment()).commit();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
