package com.stad.sharecon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.stad.sharecon.fragment.HelpFragment;
import com.stad.sharecon.fragment.InfoFragment;
import com.stad.sharecon.fragment.SyncFragment;
import com.stad.sharecon.model.ContactData;

public class TabsPagerAdapter extends FragmentPagerAdapter {

	private ContactData contactData = null;
	public TabsPagerAdapter(FragmentManager fm, ContactData contactData) {
		super(fm);
		this.contactData = contactData;
		Log.i("TabsPagerAdapter", "creating pager adapter");
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			Log.i("TabsPagerAdapter", "creating new InfoFragment");
			return new InfoFragment(contactData);
		case 1:
			Log.i("TabsPagerAdapter", "creating new SyncFragment");
			return new SyncFragment();
		case 2:
			Log.i("TabsPagerAdapter", "creating new HelpFragent");
			return new HelpFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
