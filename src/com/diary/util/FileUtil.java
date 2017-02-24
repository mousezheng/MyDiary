package com.diary.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class FileUtil {
	// ��ȡ�ڴ��Ŀ¼
	// String rootPath;
	private String dirPath;


	public FileUtil(String dirPath) {
		// TODO Auto-generated constructor stub
		this.dirPath = Environment.getExternalStorageDirectory().getPath()
				+ "/" + dirPath;
	}

	/**
	 * ��ȡ�ļ��б����ƣ�����ת��Ϊ�����գ�����list������
	 * 
	 * @param filePath
	 * @return
	 */
	public List<MyDate> getFileNameList() {
		List<MyDate> fileNameList = new ArrayList<MyDate>();
		File[] file = new File(this.dirPath).listFiles();
		for (int i = 0; i < file.length; i++) {
			fileNameList.add(stringToDate(file[i].getName()));
		}
		return fileNameList;
	}

	/**
	 * �ַ�������ת��ΪDate
	 * 
	 * @param fileName
	 * @return
	 */
	private MyDate stringToDate(String fileName) {
		MyDate date = new MyDate();
		String[] dateStr = fileName.split("-");
		date.setYear(Integer.parseInt(dateStr[0]));
		date.setMonth(Integer.parseInt(dateStr[1]));
		date.setDay(Integer.parseInt(dateStr[2]));
		return date;
	}

	/**
	 * ָ���ļ��У�ָ���ļ����������ļ��к��ļ�
	 * 
	 * @param dirPath
	 * @param filePath
	 */
	public File creatFile(String time, String filePath) {
		File dir = new File(dirPath + "/" + time);
		File file = new File(dirPath + "/" + time + "/" + filePath);
		if (!dir.exists()) {// �ж�Ŀ¼�Ƿ����
			dir.mkdirs(); // ����
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("bug", "�����ļ�ʧ��");
				e.printStackTrace();
			}
		}
		return file;
	}
	/**
	 * ��ȡ�ļ�·��
	 * @return
	 */
	public String getDirPath() {
		return dirPath;
	}

}
