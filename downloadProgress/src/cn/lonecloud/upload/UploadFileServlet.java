package cn.lonecloud.upload;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadFileServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置字符集
		response.setContentType("text/html;charset=utf-8");// 设置响应字符集
		String savepath = getServletContext().getRealPath("/upload");// 设置上传文件夹
		System.out.println(savepath);
		File saveDir = new File(savepath);// 创建文件夹
		if (!saveDir.exists()) {
			saveDir.mkdirs();

		}
		//Factory先运行还是监听器先运行
		DiskFileItemFactory factory = new DiskFileItemFactory();// 创建一个工厂
		File file2=new File(saveDir+"ups");
		if (!file2.exists()) {
			file2.mkdirs();
		}
		factory.setRepository(file2);
		ServletFileUpload sfu = new ServletFileUpload(factory);// 设置一个
		sfu.setHeaderEncoding("utf-8");// 设置请求头字符集
		sfu.setFileSizeMax(1024 * 1024 * 150);// 设置单文件最大值
		sfu.setSizeMax(1024 * 1024 * 200);// 设置文件最大上传大小
		factory.setSizeThreshold(0);//设置缓存
		// 设置监听器
		UploadProgressListener pListener;
		try {
//			pListener = new UploadProgressListener(request,sfu);
			pListener = new UploadProgressListener(request);
			sfu.setProgressListener(pListener);
		} catch (FileUploadException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		List<FileItem> itemList = null;
		try {
			
			itemList = sfu.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			// 对表中的每一个项目进行遍历
			int i=0;
			for (FileItem fileItem : itemList) {
				i++;
				if (i==2) {
//					throw new RuntimeException();
				}
				String filedName = fileItem.getFieldName();
				System.out.println("控件名为：" + filedName);

				if (fileItem.isFormField()) {
					// 普通的文本项，例如传进来的文本非文件
					String value = fileItem.getString();
					value = new String(value.getBytes("iso8859-1"), "utf-8");
					System.out.println("普通内容" + filedName + "值为" + value);
				} else {
					
					try {
						new Thread(new Run(fileItem,savepath,i)).start();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
			request.getRequestDispatcher("/success.jsp").forward(request,
					response);

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}

}
