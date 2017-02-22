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

	SimpleAdapter adapter;		//����������
	GridView calendarGV;		//����GridView
	TextView timeTV;			//��ʾ��ǰ����
	Map<String, Object> dataElementMap;	//Map���͵�����Ԫ��������ӵ�List��
	List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
	String[] from = { "num", "leftSign", "rightSign" };		//������ʾ���ݼ�
	//������ʾ Item����Ӧ�� �ؼ�ID
	int[] to = { R.id.textNum_item, R.id.leftSign_item, R.id.rightSign_item };
	int year = 2017;
	int month = 3;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);		// ȥ��������
		setContentView(R.layout.calendar);

		calendarGV = (GridView) findViewById(R.id.GV_calendar);
		timeTV = (TextView)findViewById(R.id.time);
		setYearMonth();			
		year = 2018;
		month = 1;
		timeTV.setText(year+"-"+month);
		adapter = new SimpleAdapter(this, dataFactory(),		//�����򵥵�������
				R.layout.calendar_item, from, to);

		calendarGV.setAdapter(adapter);					//����������
		addWeekHead();
		
	}

	/**
	 * ���������е�Ԫ��
	 * 
	 * @return
	 */
	public List<Map<String, Object>> dataFactory() {
		MonthData monthData = new MonthData();
		int week = monthData.firstDayInWeek(year,month);
		int monthDay = monthData.getMonthDay(year,month);
		for (int i = 0; i < week; i++) {
			dataElementMap = new HashMap<String, Object>(); // ������ʱ Map
			dataElementMap.put(from[0], ""); // �����������
			dataElementMap.put(from[1], "");
			dataElementMap.put(from[2], "");
			dataList.add(dataElementMap);
		}

		for (int i = 0; i < monthDay; i++) {
			dataElementMap = new HashMap<String, Object>(); // ������ʱ Map
			dataElementMap.put(from[0], i+1); // �����������
			dataElementMap.put(from[1], "*");
			dataElementMap.put(from[2], "0");
			dataList.add(dataElementMap);
		}
		return dataList;
	}

	/**
	 * ʵ�ֶԱ�ͷ GridView������
	 */
	public void addWeekHead() {
		List<Map<String, Object>> data = null;
		String[] week = {"��","һ","��","��","��","��","��"};
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
		String[] num = date.split("-");	//�ӡ�-���ֿ�
		year = Integer.parseInt(num[0]);
		month = Integer.parseInt(num[1]);
	}
}




