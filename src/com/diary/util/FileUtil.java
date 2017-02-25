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
	 * 
	 * @return
	 */
	public String getDirPath() {
		return dirPath;
	}

	/**
	 * 是否是文件,存在
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
	 * 存在该题目文件直接读字符串
	 * 
	 * @return
	 */
	public String getFileStr(String fileName) {
		// TODO Auto-generated method stub
		String fileStr = null;
		File file = new File(dirPath + "/" + fileName);
		if (file.exists()) { // 防止文件不存在
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
	 * 将字符串保存到文件中
	 * 
	 * @param FileName
	 * @param fileContent
	 */
	public void saveFile(String dateStr,String fileName, String fileContent) {
		// TODO Auto-generated method stub
		File file = this.creatFile(dateStr, fileName);
		try { // 创建流写文件
			FileOutputStream outputStream = new FileOutputStream(file);
			outputStream.write(fileContent.getBytes());
			outputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		// Toast.makeText(this, fileName+"保存成功", Toast.LENGTH_SHORT);
	}
}
