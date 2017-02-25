package com.diary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.diary.util.FileUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class DiaryActivity extends Activity {

	private EditText titleET;
	private EditText contentET;
	private String dateStr;
	private FileUtil fileUtil;

	/**
	 * 创建时加载视图文件
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary);

		titleET = (EditText) findViewById(R.id.title);
		contentET = (EditText) findViewById(R.id.content);
		// 获取项目中的Intent
		Intent i = getIntent();
		// 通过“键”获取其对应值
		dateStr = i.getStringExtra("date");
		setTitle(dateStr);
		
		fileUtil = new FileUtil("Diary");
	}

	/**
	 * 失去焦点自动保存内容，并加销毁
	 */
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("myLogDiayActivity", "onPause");
		// 保存文件
		String title = titleET.getText().toString();
		String contentStr = contentET.getText().toString();
		if (title.length()>=2 || contentStr.length()>=2) {
			//输入内容大于等于二个字符，算作不为空就保存
			saveFile("tilte", title);
			saveFile("content", contentStr);
		}
		Log.d("myLogDiayActivity", fileUtil.getDirPath());
		onStop(); // 失去焦点自动停止
		onDestroy(); // 销毁
	}

	/**
	 * 将字符串保存到文件中
	 * 
	 * @param FileName
	 * @param fileContent
	 */
	private void saveFile(String fileName, String fileContent) {
		// TODO Auto-generated method stub
		File file = fileUtil.creatFile(dateStr, fileName);
		try { // 创建流写文件
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fileContent.getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Toast.makeText(this, fileName+"保存成功", Toast.LENGTH_SHORT);
	}
}
