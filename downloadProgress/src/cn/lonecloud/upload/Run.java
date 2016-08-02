package cn.lonecloud.upload;

import java.io.File;

import org.apache.commons.fileupload.FileItem;

public class Run implements Runnable{
	
	FileItem fileItem;
	
	String savepath;
	
	int num;
	
	Run(FileItem file,String savepath,int num){
		this.fileItem=file;
		this.savepath=savepath;
		this.num=num;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (num==2) {
			throw new RuntimeException();
		}
		System.out.println(savepath);
		// 文件
		// 设置文件的大小
		Long size = fileItem.getSize();
		// 获取文件的名字
		String fileName = fileItem.getName();
		System.out.println("文件名为" + fileName + "文件大小为" + size);
		// 创建文件
		File file = new File(savepath, fileName);
		
		// 对文件进行写操作
		try {
			fileItem.write(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fileItem.delete();
	}
}
