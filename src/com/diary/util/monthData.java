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
 * 	�����·���Ϣ��
 * 	��1��.�·ݿ�ʼ�ؼ���
 * 	��2��.�·��ж�����
 * @author zsl
 *
 */

public class monthData {
	int[] month1 = {31,27,31,30,31,30,
					31,31,30,31,30,31};
	int[] month2 = {31,28,31,30,31,30,
					31,31,30,31,30,31};
	
	//2017��1��1�գ������ռ���0
	
	public int firstDayInWeek(int month){
		int week = 0;	//��һ�������ڼ�
		int sunDay = 0;
		for (int i = 0; i < month-1; i++) {
			sunDay+=month1[i]; 
		}//������˶�����
		
		week = (sunDay + week)%7;
		return week+1;
	}
}
