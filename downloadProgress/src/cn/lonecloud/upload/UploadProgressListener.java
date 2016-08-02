package cn.lonecloud.upload;

import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;

public class UploadProgressListener implements ProgressListener {
	
	private HttpServletRequest request;

	private long kiloBytes=-1;
	
//	private ServletFileUpload sfu;
	
//	private List<FileItem> list;
	public UploadProgressListener(HttpServletRequest request) throws FileUploadException {
		super();
//		this.sfu=sfu;
		this.setRequest(request);
//		list=sfu.parseRequest(request);
	}


	@Override
	//pBytesRead已经读取的大小  pContentLength 文件总大小  PItems项目0为非读取1表示在读取
	public void update(long pBytesRead, long pContentLength, int PItems) {
		Long kBytes=pBytesRead/1024;//获取这个的byte数
		System.out.println(PItems);
		if (kiloBytes==kBytes) {
			return ;
//			Pitem是做什么用
//			PItems项目代表文件上传文件上川的文件上传的顺序0文本1.。。后面是上传的文件子项目
//			多文件上传的上传异常时在监听器上还是在factory抛出异常
//			后面文件是否异常
//			在监听器里面获取文件的文件名
//			判断文件上传是否成功
			
			
		}
		kiloBytes=kBytes;
		System.out.println("我们正在读取文件项目"+PItems);
//		System.out.println("这个文件名"+list.get(PItems+1).getFieldName());
		if (pContentLength==-1) {
			System.out.println("到目前为止我们已经读取了"+pBytesRead+"字节的数据");
			
		}else{
			System.out.println("到目前为止已经读取了"+pContentLength+"中的"+pBytesRead+"字节的数据");
		}
		request.getSession().setAttribute("progress", NumberFormat.getPercentInstance().format(pBytesRead/pContentLength*1.0));
	}


	public HttpServletRequest getRequest() {
		return request;
	}


	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
	

}
