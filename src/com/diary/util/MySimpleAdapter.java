package com.diary.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diary.R;

import android.content.Context;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

public class MySimpleAdapter extends SimpleAdapter {

	int week;
	Context context;
	public MySimpleAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to, int week) {
		super(context, data, resource, from, to);
		this.week = week;
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (position < week) {
			if (convertView == null) {

				LayoutInflater iInflater = LayoutInflater.from(context);
				convertView = iInflater.inflate(R.layout.calendar_item, null);
				convertView.setBackgroundColor(Color.WHITE);
			}
		}
		return super.getView(position, convertView, parent);
	}
}
