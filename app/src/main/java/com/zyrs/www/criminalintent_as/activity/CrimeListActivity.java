package com.zyrs.www.criminalintent_as.activity;

import android.support.v4.app.Fragment;

/**
 * CrimeListActivity
 * �����б�Activity
 * @author Administrator
 *
 */
public class CrimeListActivity extends SingleFragmentActivity {

	@Override
	public Fragment createFragment() {
		return new CrimeListFragment();
	}

}
