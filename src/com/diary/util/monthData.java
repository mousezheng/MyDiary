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
 * 	生成月份信息，
 * 	（1）.月份开始控几个
 * 	（2）.月份有多少天
 * @author zsl
 *
 */

public class monthData {
	int[] month1 = {31,27,31,30,31,30,
					31,31,30,31,30,31};
	int[] month2 = {31,28,31,30,31,30,
					31,31,30,31,30,31};
	
	//2017年1月1日，星期日记作0
	
	public int firstDayInWeek(int month){
		int week = 0;	//第一天是星期几
		int sunDay = 0;
		for (int i = 0; i < month-1; i++) {
			sunDay+=month1[i]; 
		}//计算过了多少天
		
		week = (sunDay + week)%7;
		return week+1;
	}
}
