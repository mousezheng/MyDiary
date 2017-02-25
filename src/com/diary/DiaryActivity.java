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
			saveFile("tilte", title);
			saveFile("content", contentStr);
		}
		Log.d("myLogDiayActivity", fileUtil.getDirPath());
		onStop(); // ʧȥ�����Զ�ֹͣ
		onDestroy(); // ����
	}

	/**
	 * ���ַ������浽�ļ���
	 * 
	 * @param FileName
	 * @param fileContent
	 */
	private void saveFile(String fileName, String fileContent) {
		// TODO Auto-generated method stub
		File file = fileUtil.creatFile(dateStr, fileName);
		try { // ������д�ļ�
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fileContent.getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		Toast.makeText(this, fileName+"����ɹ�", Toast.LENGTH_SHORT);
	}
}
