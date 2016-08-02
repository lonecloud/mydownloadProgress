package cn.lonecloud.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.NumberFormat;

import cn.lonecloud.model.ProgressState;

public class FileUtil {

	public static void copyFile(File srcFile, File descFile,ProgressState progressState) {
		InputStream in = null;
		OutputStream out = null;
		long fileSize = srcFile.length();
//		String fileName = srcFile.getName();
		long currentLength = 0;
		String precent = "0";
		try {
			in = new FileInputStream(srcFile);
			out = new FileOutputStream(descFile);
			byte[] buffer = new byte[10240];
			int length=0;
			while (length!= -1) {
				long Stime=System.currentTimeMillis();
				length=in.read(buffer);
				out.write(buffer);
				currentLength += length;
				precent = NumberFormat.getPercentInstance().format(
						currentLength / (fileSize * 1.0));// 设置百分比
				progressState.setPrecent(precent);
				progressState.setSpeeed(length
						/ (System.currentTimeMillis() -Stime+1));//设置速度
				System.out.println(progressState.getFileName()+"  " +precent);
			} 
			progressState.setState(1);
		} catch (Exception e) {
			progressState.setState(2);
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
