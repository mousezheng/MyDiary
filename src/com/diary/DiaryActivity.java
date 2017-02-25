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
	 * ����ʱ������ͼ�ļ�
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.diary);

		titleET = (EditText) findViewById(R.id.title);
		contentET = (EditText) findViewById(R.id.content);
		// ��ȡ��Ŀ�е�Intent
		Intent i = getIntent();
		// ͨ����������ȡ���Ӧֵ
		dateStr = i.getStringExtra("date");
		setTitle(dateStr);
		
		fileUtil = new FileUtil("Diary");
		if (fileUtil.isFile(dateStr)) {	//����ĳ�����ݣ�ֱ����ʾ
			titleET.setText(fileUtil.getFileStr(dateStr+"/title.txt"));
			contentET.setText(fileUtil.getFileStr(dateStr+"/content.txt"));
		}
	}

	/**
	 * ʧȥ�����Զ��������ݣ���������
	 */
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("myLogDiayActivity", "onPause");
		// �����ļ�
		String title = titleET.getText().toString();
		String contentStr = contentET.getText().toString();
		if (title.length()>=2 || contentStr.length()>=2) {
			//�������ݴ��ڵ��ڶ����ַ���������Ϊ�վͱ���
			fileUtil.saveFile(dateStr,"title.txt", title);
			fileUtil.saveFile(dateStr,"content.txt", contentStr);
		}
		Log.d("myLogDiayActivity", fileUtil.getDirPath());
		onStop(); // ʧȥ�����Զ�ֹͣ
		onDestroy(); // ����
	}


}
