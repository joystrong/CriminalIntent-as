package com.zyrs.www.criminalintent_as.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import com.zyrs.www.criminalintent_as.R;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DatePickerFragment extends android.support.v4.app.DialogFragment {
	public static final String EXTRA_DATE = "com.zyrs.android.criminalintent.date";
	private Date mDate;
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		View v = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
		DatePicker dp = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
		dp.init(year, month, day, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				 mDate = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
				 getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
		return new AlertDialog.Builder(getActivity())
				.setView(dp)
				.setTitle("选择日期")
				.setPositiveButton(android.R.string.ok, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						sendResult(Activity.RESULT_OK);
					}
				})
				.create();
	}
	
	public static DatePickerFragment newIntance(Date date)
	{
		Bundle bundle = new Bundle();
		bundle.putSerializable(EXTRA_DATE, date);
		DatePickerFragment df = new DatePickerFragment();
		df.setArguments(bundle);
		return df;
	}
	/**
	 * ���÷���CrimeFragment���
	 * @param resultCode
	 */
	private void sendResult(int resultCode)
	{
		if(getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, i);
	}
}