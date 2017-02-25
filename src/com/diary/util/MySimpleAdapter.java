package com.diary.util;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.diary.R;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MySimpleAdapter extends SimpleAdapter {

	private int week;
	private Context context;
	private int year;
	private int month;
	private int day;
	private final FileUtil fileUtil = new FileUtil("Diary");
	private final List<MyDate> myDates = fileUtil.getFileNameList();

	public MySimpleAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, int week, int year, int month, int day) {
		super(context, data, resource, from, to);
		this.week = week;
		this.context = context;
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (position < week) {
			if (convertView == null) {
				LayoutInflater iInflater = LayoutInflater.from(context);
				convertView = iInflater.inflate(R.layout.calendar_item, null);
				convertView.setBackgroundColor(Color.WHITE);
				return super.getView(position, convertView, parent);
			}
		} else {
			convertView = todayView(position);
			if (convertView != null) {
				return super.getView(position, convertView, parent);
			} else {
				convertView = isChangeColor(position);
				if (convertView != null)
					return super.getView(position, convertView, parent);
			}
		}

		return super.getView(position, convertView, parent);
	}

	private View isChangeColor(int position) {
		// TODO Auto-generated method stub
		int weekThis = week; // 第一天周几
		int day = position + 1 - weekThis;
		if (this.dateIsInList(year, month, day)) {
			View tempConvertView = changeViewColor(R.drawable.background_green);
			//传参数是穿了年月日
			// tempConvertView.setTag(year + "-" + month + "-" + day);
			return tempConvertView;
		}
		return null;
	}

	/**
	 * 设置今天日期所对应的View
	 * 
	 * @param position
	 * @return
	 */
	private View todayView(int position) {
		View convertView = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String date = sdf.format(new java.util.Date());
		String[] num = date.split("-"); // 从“-”分开
		int yearNow = Integer.parseInt(num[0]);
		int monthNow = Integer.parseInt(num[1]);
		if (yearNow == year && monthNow == month && position == week + day - 1) {
			convertView = changeViewColor(R.drawable.background_today);
		}
		return convertView;
	}

	/**
	 * 给有内容的设置为 绿色
	 * 
	 * @return
	 */
	private View changeViewColor(int drawable) {
		View convertView = null;
		LayoutInflater iInflater = LayoutInflater.from(context);
		convertView = iInflater.inflate(R.layout.calendar_item, null);
		convertView.setBackgroundResource(drawable);
		return convertView;
	}

	/**
	 * 判断年月日是否在，指定序列中
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	private boolean dateIsInList(int year, int month, int day) {
		for (MyDate date : myDates) {
			if (date.getYear() == year && date.getMonth() == month
					&& date.getDay() == day) {
				return true;
			}
		}
		return false;
	}

}
