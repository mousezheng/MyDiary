package com.diary.util;

/**
 * �����·���Ϣ�� ��1��.�·ݿ�ʼ�ؼ��� ��2��.�·��ж�����
 * 
 * @author zsl
 * 
 */

public class MonthData {
	int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	// 2017��1��1�գ������ռ���0
	public int firstDayInWeek(int year, int month) {
		int week = getFistYearDayInweeK(year); // ��һ�������ڼ�
		int sunDay = 0; // ������
		if (!isLeapYear(year)) { // �������ƽ��
			monthDay[1] = 29;
		}
		for (int i = 0; i < month - 1; i++) {
			sunDay += monthDay[i];
		}// ������˶�����
		if(sunDay!=0){
			week = (sunDay + week) % 7;
		}
		
		return week;
	}

	/**
	 * �ж�����
	 * 
	 * @param year
	 * @return
	 */
	private boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return false; // ����400����ƽ��
		} else {
			if (year % 100 == 0)
				return true;// ������400������100�ı�����ƽ��
			if (year % 4 == 0)
				return false; // ������400Ҳ������100������4����ƽ��
		}
		return true;
	}

	/**
	 * ��ȡ�����һ�����ڼ�
	 * 
	 * @param year
	 * @return
	 */
	private int getFistYearDayInweeK(int year) {
		// ����2017���һ���������� ���� 0
		int week2017 = 0;
		int week = 0;
		int sunDay = 0; // ��������
		if (year > 2017) { // ����2017��
			for (int i = 2017; i < year; i++) {
				if (isLeapYear(i))
					sunDay += 365;
				else
					sunDay += 366;
			}
			week = (sunDay + week2017) % 7;
		} else { // С��2017 ��
			for (int i = 2016; i >= year; i--) {
				if (isLeapYear(i))
					sunDay += 365;
				else
					sunDay += 366;
			}
			week = 6 - (sunDay + week2017) % 7+1;
		}
		return week % 7;
	}

	/**
	 * ����ĳ��ĳ������,��0��ʼ�·ݼ�һ
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getMonthDay(int year, int month) {
		if (!isLeapYear(year)) { // �������ƽ��
			monthDay[1] = 28;
		}

		return monthDay[month - 1];
	}
}
