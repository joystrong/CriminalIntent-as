package com.zyrs.www.criminalintent_as.util;

import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * ���ڸ�ʽת��
 * @author Administrator
 *
 */
public class DateFormats {

	private static final String DATE_FORMATE="yyyy年MM月dd日,kk:mm";
	public static String getLocalDate(Date date)
	{
		String dateStr = "";
		if(date != null){
		  dateStr = DateFormat.format(DATE_FORMATE, date).toString();
		}
		else{
		  dateStr = DateFormat.format(DATE_FORMATE, new Date()).toString();
		}
		return dateStr;
	}
	
	public static Date getDate(String dateStr)
	{
		Calendar c = Calendar.getInstance();
		if(!"".equals(dateStr))
		{
			String dateTemp = DATE_FORMATE.split(",")[0];
			String timeTemp = DATE_FORMATE.split(",")[0];
			c.set(Integer.parseInt(dateTemp.substring(0, 3)), Integer.parseInt(dateTemp.substring(5, 6)), Integer.parseInt(dateTemp.substring(8, 9)), Integer.parseInt(timeTemp.substring(0, 1)), Integer.parseInt(timeTemp.substring(3, 4)));
		}
		return c.getTime();
	}
}
