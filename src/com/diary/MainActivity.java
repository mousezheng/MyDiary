package com.diary;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diary.util.MonthData;
import com.diary.util.MySimpleAdapter;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements
		OnItemClickListener {

	private GestureDetector myGestureDetector;
	private SimpleAdapter adapter; // ����������
	private GridView calendarGV; // ����GridView
	private TextView timeTV; // ��ʾ��ǰ����
	private Map<String, Object> dataElementMap; // Map���͵�����Ԫ��������ӵ�List��
	private List<Map<String, Object>> dataList;

	private String[] from = { "num", "leftSign", "rightSign" }; // ������ʾ���ݼ�
	// ������ʾ Item����Ӧ�� �ؼ�ID
	private int[] to = { R.id.textNum_item, R.id.leftSign_item, R.id.rightSign_item };
	private int year = 2017;
	private int month = 3;
	private int week = 0;
	private int day = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // ȥ��������
		setContentView(R.layout.calendar);

		calendarGV = (GridView) findViewById(R.id.GV_calendar);
		timeTV = (TextView) findViewById(R.id.time);

		setYearMonth();
		timeTV.setText(year + "-" + month);
		adapter = new MySimpleAdapter(this, dataFactory(), // �����򵥵�������
				R.layout.calendar_item, from, to,week,year,month,day);
		calendarGV.setAdapter(adapter); // ����������
		addWeekHead();

		myGestureDetector = new GestureDetector(new MyGestureListener());
		calendarGV.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				myGestureDetector.onTouchEvent(event);
				return false;
			}
		});

		calendarGV.setOnItemClickListener(this);// ���ü�����
	}

	/**
	 * ���������е�Ԫ��
	 * 
	 * @return
	 */
	public List<Map<String, Object>> dataFactory() {
		dataList = new ArrayList<Map<String, Object>>();
		MonthData monthData = new MonthData();
		week = monthData.firstDayInWeek(year, month);
		int monthDay = monthData.getMonthDay(year, month);
		for (int i = 0; i < week; i++) {
			dataElementMap = new HashMap<String, Object>(); // ������ʱ Map
			dataElementMap.put(from[0], ""); // �����������
			dataElementMap.put(from[1], "");
			dataElementMap.put(from[2], "");
			dataList.add(dataElementMap);
		}

		for (int i = 0; i < monthDay; i++) {
			dataElementMap = new HashMap<String, Object>(); // ������ʱ Map
			dataElementMap.put(from[0], i + 1); // �����������
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
		String[] week = { "��", "һ", "��", "��", "��", "��", "��" };
		data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < week.length; i++) {
			Map tempMap = new HashMap<String, Object>();
			tempMap.put("Num", week[i]);
			data.add(tempMap);
		}
		SimpleAdapter headAdapter = new SimpleAdapter(this, data,
				R.layout.calendar_item_head, new String[] { "Num" },
				new int[] { R.id.textNum_item_head });

		GridView weekHeadGV = (GridView) findViewById(R.id.GV_calendar_head);
		weekHeadGV.setAdapter(headAdapter);
	}

	/**
	 * ����year����Month,
	 * 2017��2��24�� ����day
	 */
	public void setYearMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new java.util.Date());
		String[] num = date.split("-"); // �ӡ�-���ֿ�
		year = Integer.parseInt(num[0]);
		month = Integer.parseInt(num[1]);
		day = Integer.parseInt(num[2]);
	}

	/**
	 * ������������ӽ��в���
	 * ʵ����ת���ռǱ�ҳ��
	 * @param parent
	 * @param view
	 * @param position
	 * @param id
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		String showText = year + "-" + month + "-" + (position + 1 - week);// �������ǰ������
		if (week < position + 1) {	//�޸�bug2017��2��24��14:32:06����һ�����û��Ӧ
			//Toast.makeText(this, showText, Toast.LENGTH_LONG).show();
			//��ת������showText����
			Intent i = new Intent(this, DiaryActivity.class);
			i.putExtra("date", showText);
			startActivity(i);	//��ת
		}
	}

	/**
	 * ���������Ը���GridView
	 */
	public void updataGridViwe() {
		timeTV.setText(year + "-" + month);
		adapter = new MySimpleAdapter(this, dataFactory(), // �����򵥵�������
				R.layout.calendar_item, from, to,week,year,month,day);
		calendarGV.setAdapter(adapter); // ����������
		calendarGV.setOnItemClickListener(this);// ���ü�����
	}

	class MyGestureListener extends SimpleOnGestureListener {

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			if (e1.getX() - e2.getX() > 50) {
				up();
				updataGridViwe();
			} else if (e2.getX() - e1.getX() > 50) {
				down();
				updataGridViwe();
			}

			return super.onFling(e1, e2, velocityX, velocityY);
		}

		private void down() {
			month = month - 1;
			if (month <= 0) {
				month = 12;
				year = year - 1;
			}
		}

		private void up() {
			month = month + 1;
			if (month > 12) {
				month = 1;
				year = year + 1;
			}
		}
	}

}
