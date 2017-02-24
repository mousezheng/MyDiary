package com.diary;

import android.app.Activity;
import android.os.Bundle;

public class DiaryActivity extends Activity{
	
	/**
	 * 创建时加载视图文件
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary);
	}
	
	/**
	 * 失去焦点自动保存内容，并加销毁
	 */
	@Override
	protected void onPause() {
		super.onPause();
		
		
		onStop();	//失去焦点自动停止
		onDestroy();	//销毁
	}
}
