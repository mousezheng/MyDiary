package com.diary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.diary.util.AddData;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
	
	SimpleAdapter adapter;
	GridView calendarGV;
	Map<String, Object> dataElementMap;
	List<Map<String, Object>> dataList = new ArrayList<Map<String,Object>>();
	String[] from = {"num","leftSign","rightSign"};
	int[] to = {R.id.textNum_item,R.id.leftSign_item,R.id.rightSign_item};
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        setContentView(R.layout.calendar);
        
        calendarGV = (GridView)findViewById(R.id.GV_calendar);
        
//       new AddData(findViewById(id_GradeView),this,R.id.calendar_item,new int[]{R.id.textNum_item});
        
        adapter = new SimpleAdapter(this, dataFactory(), R.layout.calendar_item,
        		from, to);
        
        calendarGV.setAdapter(adapter);
    }
	
	/**
	 * 加入日历中的元素
	 * @return 
	 */
	public  List<Map<String, Object>> dataFactory(){
		
		for (int i = 0; i < 2; i++) {
			dataElementMap = new HashMap<String, Object>();	//创建临时 Map
			dataElementMap.put(from[0], "");			//逐个放入数据
			dataElementMap.put(from[1], "");
			dataElementMap.put(from[2], "");
			dataList.add(dataElementMap);
		}
		
		for (int i = 0; i < 31; i++) {
			dataElementMap = new HashMap<String, Object>();	//创建临时 Map
			dataElementMap.put(from[0], i);			//逐个放入数据
			dataElementMap.put(from[1], "*");
			dataElementMap.put(from[2], "0");
			dataList.add(dataElementMap);
		}
		return dataList;
	}
	
}
