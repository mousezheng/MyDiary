package com.diary.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EncodingUtils;

import android.os.Environment;
import android.provider.OpenableColumns;
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
	 * 
	 * @return
	 */
	public String getDirPath() {
		return dirPath;
	}

	/**
	 * �Ƿ����ļ�,����
	 * 
	 * @return
	 */
	public Boolean isFile(String time) {
		File file = new File(dirPath + "/" + time);
		if (file.exists()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * ���ڸ���Ŀ�ļ�ֱ�Ӷ��ַ���
	 * 
	 * @return
	 */
	public String getFileStr(String fileName) {
		// TODO Auto-generated method stub
		String fileStr = null;
		File file = new File(dirPath + "/" + fileName);
		if (file.exists()) { // ��ֹ�ļ�������
			try {
				FileInputStream fin = new FileInputStream(file);
				int length = fin.available();
				byte[] buffer = new byte[length];
				fin.read(buffer);
				fileStr = EncodingUtils.getString(buffer, "UTF-8");
				fin.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return fileStr;
	}

	/**
	 * ���ַ������浽�ļ���
	 * 
	 * @param FileName
	 * @param fileContent
	 */
	public void saveFile(String dateStr,String fileName, String fileContent) {
		// TODO Auto-generated method stub
		File file = this.creatFile(dateStr, fileName);
		try { // ������д�ļ�
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fileContent.getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		// Toast.makeText(this, fileName+"����ɹ�", Toast.LENGTH_SHORT);
	}
}
