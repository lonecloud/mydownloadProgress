package cn.lonecloud.Action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import cn.lonecloud.model.ProgressState;
import cn.lonecloud.util.FileRun;
import cn.lonecloud.util.StringUtil;

import com.opensymphony.xwork2.ActionSupport;

public class UploadAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private File[] upload;

	// 提交过来的file的名字
	private String[] uploadFileName;

	// 提交过来的file的MIME类型
	private String[] uploadContentType;
	
	private HttpSession session;
	
	List<ProgressState> list=new ArrayList<ProgressState>();

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	@Override
	public String execute() throws Exception {
		session=ServletActionContext.getRequest().getSession();
		String path = ServletActionContext.getServletContext().getRealPath(
				"upload");
		String DatePath = StringUtil.getDirStr();
		System.out.println(path);
		File file = new File(path, DatePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		int i=0;
		List<ProgressState> list=new ArrayList<ProgressState>();
		session.setAttribute("list", list);
		for (File srcFile : upload) {
			System.out.println(srcFile.getName());
//			String[] split = srcFile.getName().split("\\.");
//			String fileName = StringUtil.getRandomStr() + "."
//					+ split[split.length - 1];
			String fileName=uploadFileName[i];
			File descFile = new File(file, fileName);
			ProgressState progressState =new ProgressState(fileName,srcFile.length());
			list.add(progressState);
			Thread myThread=new Thread(new FileRun(session, srcFile, descFile,progressState));
			System.out.println(progressState.toString());
			myThread.start();
			i++;
		}
		session.setAttribute("list", list);
		// 设置文件
//		try {
//			FileUtils.copyFile(upload, descFile);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return INPUT;
//		}
//		UserInfo info = new UserInfo();
//		info.setFileName(DatePath + fileName);
//		info.setUserId(userId);
//		info.setUserName(userName);
//		HttpSession session = ServletActionContext.getRequest().getSession();
//		session.setAttribute("info", info);
//		return SUCCESS;
		return super.execute();
	}
}
