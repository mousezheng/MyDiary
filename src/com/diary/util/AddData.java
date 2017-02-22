package com.diary.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.View;
import android.widget.Adapter;
import android.widget.GridView;
import android.widget.SimpleAdapter;

/**
 * 	用来完成对数据集的加载配置任务，
 * @author zsl
 *
 */
public class AddData {
//	SimpleAdapter calendarAdapter;	//数据适配器
//	GridView gvCalendar;			//相应GridView
	String[] from = {"num"};
//	Context context;
//	int resource;
//	int[] to;
	Map<String, Object> dataElementMap;
	List<Map<String, Object>> dataList;
//	
//	/**
//	 * 初始化数据，并设置到GridView
//	 * @param view
//	 * @param context
//	 * @param resource
//	 * @param to
//	 */
//	public AddData(View view,Context context,int resource,int[] to) {
//		gvCalendar = (GridView) view;
//		this.context = context;
//		this.to = to;
//		this.resource = resource;
//		dataList = new ArrayList<Map<String,Object>>();
//		gvCalendar.setAdapter(adapterFactory());
//	}
	

	
//	/**
//	 * 配置适配器
//	 * @return 
//	 */
//	public  SimpleAdapter adapterFactory(){
//		dataFactory();			//制作数据适配器时需要生产数据
//		calendarAdapter = new SimpleAdapter(context, dataList, resource, from, to);
//		return calendarAdapter;
//	}
	
}
