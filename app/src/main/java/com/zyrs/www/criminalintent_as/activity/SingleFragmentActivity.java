package com.zyrs.www.criminalintent_as.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.zyrs.www.criminalintent_as.R;


public abstract class SingleFragmentActivity extends FragmentActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);
		//��ȡ����Fragment�Ĺ�����FragmentManager
		FragmentManager fm = getSupportFragmentManager();
		//��ȡ��fragmentջ��fragment
		Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
		if(fragment ==null)
		{
			fragment = createFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
		}
	}
	
	public abstract Fragment createFragment();
}
