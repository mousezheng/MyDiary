package com.diary.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class FileUtil {
	// 获取内存根目录
	// String rootPath;
	private String dirPath;


	public FileUtil(String dirPath) {
		// TODO Auto-generated constructor stub
		this.dirPath = Environment.getExternalStorageDirectory().getPath()
				+ "/" + dirPath;
	}

	/**
	 * 获取文件列表名称，将其转换为年月日，存入list集合中
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
	 * 字符串类型转化为Date
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
	 * 指定文件夹，指定文件名，创建文件夹和文件
	 * 
	 * @param dirPath
	 * @param filePath
	 */
	public File creatFile(String time, String filePath) {
		File dir = new File(dirPath + "/" + time);
		File file = new File(dirPath + "/" + time + "/" + filePath);
		if (!dir.exists()) {// 判断目录是否存在
			dir.mkdirs(); // 创建
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.d("bug", "创建文件失败");
				e.printStackTrace();
			}
		}
		return file;
	}
	/**
	 * 获取文件路径
	 * @return
	 */
	public String getDirPath() {
		return dirPath;
	}

}
