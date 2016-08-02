package cn.lonecloud.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GetProgressServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");// 设置字符集
		response.setContentType("text/html;charset=utf-8");// 设置响应字符集
		HttpSession session = request.getSession();
		Object progress = session.getAttribute("progress");
		if(progress==null){
			progress="0%";
		}
		System.out.println(progress.toString());
		response.getWriter().write(progress.toString());
	}

}
