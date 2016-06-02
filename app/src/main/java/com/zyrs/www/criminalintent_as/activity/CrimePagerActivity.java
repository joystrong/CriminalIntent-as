package com.zyrs.www.criminalintent_as.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import com.zyrs.www.criminalintent_as.R;
import com.zyrs.www.criminalintent_as.model.Crime;
import com.zyrs.www.criminalintent_as.model.CrimeLab;
import java.util.ArrayList;
import java.util.UUID;

public class CrimePagerActivity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<Crime> mCrimes;
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		mCrimes = CrimeLab.get(this).getCrimes();
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
			
			@Override
			public int getCount() {
				return mCrimes.size();
			}
			
			@Override
			public Fragment getItem(int arg0) {
				Crime c = mCrimes.get(arg0);
				return CrimeFragment.newInstance(c.getId());
			}
		});
		UUID crimeId = (UUID)getIntent().getSerializableExtra("uuid");
		for(int i = 0; i <mCrimes.size() ; i++ )
		{
			if(mCrimes.get(i).getId().equals(crimeId))
			{
				mViewPager.setCurrentItem(i);
				break;
			}
		}
		//��ǰҳ�ı�/�л�ʱ���ı����������
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				Crime c = mCrimes.get(arg0);
				Log.d("CrimePager", "onpagerselected");
				if(c.getTitle() != null)
				{
					setTitle(c.getTitle());
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}
}
