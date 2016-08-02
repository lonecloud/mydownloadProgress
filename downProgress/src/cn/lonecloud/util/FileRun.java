package cn.lonecloud.util;

import java.io.File;

import javax.servlet.http.HttpSession;

import cn.lonecloud.model.ProgressState;

public class FileRun implements Runnable {
	
//	private HttpSession session;
	
	private File srcFile;
	
	private File descFile;
	
	
	private ProgressState progressState;
	public FileRun(HttpSession session, File srcFile, File descFile,ProgressState progressState) {
		super();
//		this.session = session;
		this.srcFile = srcFile;
		this.descFile = descFile;
		this.progressState=progressState;
	}


	@Override
	public void run() {
//		Object object = session.getAttribute("list");
//		if (object!=null) {
//			list=(List<ProgressState>)object;
//		}else{
//			list=new ArrayList<ProgressState>();
//		}
//		ProgressState progressState=new ProgressState();
		FileUtil.copyFile(srcFile, descFile,progressState);
//		System.out.println(progressState);
//		list.add(progressState);
//		session.setAttribute("list", list);
	}
	

	public File getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}

	public File getDescFile() {
		return descFile;
	}

	public void setDescFile(File descFile) {
		this.descFile = descFile;
	}


}
