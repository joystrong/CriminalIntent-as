package com.zyrs.www.criminalintent_as.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import com.zyrs.www.criminalintent_as.R;
import com.zyrs.www.criminalintent_as.model.Crime;
import com.zyrs.www.criminalintent_as.model.CrimeLab;
import com.zyrs.www.criminalintent_as.util.DateFormats;

import java.util.Date;
import java.util.UUID;

/**
 * CrimeFragment
 * ��������Fragment
 * @author Administrator
 *
 */
public class CrimeFragment extends Fragment {
	private static final String EXTRA_CRIME_ID = "com.zyrs.android.criminalintent.crime_id";
	private static final String DIALOG_TAG = "date";
	private static final int REQUEST_DATE = 0;
	private Crime mCrime;
	private Button mDate;
	private CheckBox mCheckBox;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mCrime = new Crime();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_crime, container, false);
		//Intent��ʽ��ȡ�б�����������
		/*
		Intent i = getActivity().getIntent();
		*/
		UUID crimeId = (UUID)getArguments().getSerializable(EXTRA_CRIME_ID);
		mCrime = CrimeLab.get(getActivity()).getCrime(crimeId);
		EditText title = (EditText)v.findViewById(R.id.crime_title);
		mDate = (Button)v.findViewById(R.id.crime_date);
		mCheckBox = (CheckBox)v.findViewById(R.id.crime_sloved);
		mDate.setText(DateFormats.getLocalDate(mCrime.getDate()));
		//mDate.setEnabled(false);
		mDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fm = getActivity().getSupportFragmentManager();
				DatePickerFragment dialog = DatePickerFragment.newIntance(mCrime.getDate());
				dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
				dialog.show(fm, DIALOG_TAG);
			}
		});
		mCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				mCrime.setSolved(isChecked);
			}
		});
		title.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				mCrime.setTitle(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
		//Crime c = CrimeLab.get(getActivity()).getCrime((UUID)i.getSerializableExtra("uuid"));
		title.setText(mCrime.getTitle());
		updateDate();
		mCheckBox.setChecked(mCrime.isSolved());
		return v;
	}
	
	public static CrimeFragment newInstance(UUID crime_id)
	{
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_CRIME_ID, crime_id);
		CrimeFragment fragment = new CrimeFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
	
	public void returnResult()
	{
		getActivity().setResult(Activity.RESULT_OK, null);
	}
	
	/**
	 * ��ӦDatePickerFragment�����޸�
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK)return;
		if(requestCode == REQUEST_DATE){
			Date date = (Date)data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
			mCrime.setDate(date);
			updateDate();
		}
	}
	/**
	 * �޸İ�ť�е�����
	 */
	private void updateDate()
	{
		mDate.setText(DateFormats.getLocalDate(mCrime.getDate()));
	}
}
