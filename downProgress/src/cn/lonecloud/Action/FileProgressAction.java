package cn.lonecloud.Action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import cn.lonecloud.model.ProgressState;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 文件进度Action
 * 
 * @author lonecloud
 * 
 */
public class FileProgressAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpSession session = ServletActionContext.getRequest()
			.getSession();

	List<ProgressState> list;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Object object = session.getAttribute("list");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("pragma", "no-cache");
		response.setHeader("cache-control", "no-cache");
		response.setHeader("expires", "0");
		if (object != null) {
			list = (List<ProgressState>) object;
		}
		
		for (ProgressState progressState : list) {
			JSONObject jsonObject = JSONObject.fromObject(progressState);
//			sb.append(jsonObject.toString());
		}
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		response.getWriter().write(jsonArray.toString());
		return NONE;
	}
}
