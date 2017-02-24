package com.diary.util;

/**
 * 生成月份信息， （1）.月份开始控几个 （2）.月份有多少天
 * 
 * @author zsl
 * 
 */

public class MonthData {
	int[] monthDay = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	
	/**
	 * // 2017年1月1日，星期日记作0
	 * 第一天星期几
	 * @param year
	 * @param month
	 * @return
	 */
	public int firstDayInWeek(int year, int month) {
		int week = getFistYearDayInweeK(year); // 第一天是星期几
		int sunDay = 0; // 总天数
		if (!isLeapYear(year)) { // 如果不是平年
			monthDay[1] = 29;
		}
		for (int i = 0; i < month - 1; i++) {
			sunDay += monthDay[i];
		}// 计算过了多少天
		if(sunDay!=0){
			week = (sunDay + week) % 7;
		}
		
		return week;
	}

	/**
	 * 判断闰年
	 * 
	 * @param year
	 * @return
	 */
	private boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return false; // 整除400不是平年
		} else {
			if (year % 100 == 0)
				return true;// 不整出400但是是100的倍数是平年
			if (year % 4 == 0)
				return false; // 不整除400也不整除100但整除4不是平年
		}
		return true;
	}

	/**
	 * 获取该年第一天星期几
	 * 
	 * @param year
	 * @return
	 */
	private int getFistYearDayInweeK(int year) {
		// 根据2017年第一天是星期日 记作 0
		int week2017 = 0;
		int week = 0;
		int sunDay = 0; // 日子总数
		if (year > 2017) { // 大于2017加
			for (int i = 2017; i < year; i++) {
				if (isLeapYear(i))
					sunDay += 365;
				else
					sunDay += 366;
			}
			week = (sunDay + week2017) % 7;
		} else { // 小于2017 减
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
	 * 返回某年某月天数,从0开始月份减一
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public int getMonthDay(int year, int month) {
		if (!isLeapYear(year)) { // 如果不是平年
			monthDay[1] = 28;
		}

		return monthDay[month - 1];
	}
}
