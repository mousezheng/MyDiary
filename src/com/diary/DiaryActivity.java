package com.diary;

import android.app.Activity;
import android.os.Bundle;

public class DiaryActivity extends Activity{
	
	/**
	 * ����ʱ������ͼ�ļ�
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary);
	}
	
	/**
	 * ʧȥ�����Զ��������ݣ���������
	 */
	@Override
	protected void onPause() {
		super.onPause();
		
		
		onStop();	//ʧȥ�����Զ�ֹͣ
		onDestroy();	//����
	}
}
