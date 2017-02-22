package com.diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Test;

import com.diary.util.MonthData;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	SimpleAdapter adapter;		//创建适配器
	GridView calendarGV;		//创建GridView
	TextView timeTV;			//显示当前年月
	Map<String, Object> dataElementMap;	//Map类型的数据元素用于添加到List中
	List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	String[] from = { "num", "leftSign", "rightSign" };		//日期显示数据集
	//日期显示 Item所对应的 控件ID
	int[] to = { R.id.textNum_item, R.id.leftSign_item, R.id.rightSign_item };
	int year = 2017;
	int month = 3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		// 去掉标题栏
		setContentView(R.layout.calendar);

		calendarGV = (GridView) findViewById(R.id.GV_calendar);
		timeTV = (TextView)findViewById(R.id.time);
		setYearMonth();			
		year = 2018;
		month = 1;
		timeTV.setText(year+"-"+month);
		adapter = new SimpleAdapter(this, dataFactory(),		//创建简单的适配器
				R.layout.calendar_item, from, to);

		calendarGV.setAdapter(adapter);					//加载适配器
		addWeekHead();
		
	}

	/**
	 * 加入日历中的元素
	 * 
	 * @return
	 */
	public List<Map<String, Object>> dataFactory() {
		MonthData monthData = new MonthData();
		int week = monthData.firstDayInWeek(year,month);
		int monthDay = monthData.getMonthDay(year,month);
		for (int i = 0; i < week; i++) {
			dataElementMap = new HashMap<String, Object>(); // 创建临时 Map
			dataElementMap.put(from[0], ""); // 逐个放入数据
			dataElementMap.put(from[1], "");
			dataElementMap.put(from[2], "");
			dataList.add(dataElementMap);
		}

		for (int i = 0; i < monthDay; i++) {
			dataElementMap = new HashMap<String, Object>(); // 创建临时 Map
			dataElementMap.put(from[0], i+1); // 逐个放入数据
			dataElementMap.put(from[1], "*");
			dataElementMap.put(from[2], "0");
			dataList.add(dataElementMap);
		}
		return dataList;
	}

	/**
	 * 实现对表头 GridView的设置
	 */
	public void addWeekHead() {
		List<Map<String, Object>> data = null;
		String[] week = {"日","一","二","三","四","五","六"};
		data = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < week.length; i++) {
			Map tempMap = new HashMap<String, Object>();
			tempMap.put("Num",week[i]);
			data.add(tempMap);
		}
		SimpleAdapter headAdapter = new SimpleAdapter(this, data,
				R.layout.calendar_item_head, new String[] { "Num" },
				new int[] { R.id.textNum_item_head });
		
		GridView weekHeadGV = (GridView)findViewById(R.id.GV_calendar_head);
		weekHeadGV.setAdapter(headAdapter);
	}
	
	public void setYearMonth(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");  
		String date=sdf.format(new java.util.Date());  
		String[] num = date.split("-");	//从“-”分开
		year = Integer.parseInt(num[0]);
		month = Integer.parseInt(num[1]);
	}
}




