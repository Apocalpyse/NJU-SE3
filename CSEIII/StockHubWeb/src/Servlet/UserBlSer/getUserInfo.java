package Servlet.UserBlSer;

import VO.userVO.UserVO;
import bl.userBl.User;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/5/25.
 */
public class getUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getUserInfo(){
		super();
		user=new User();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String account=request.getParameter("account");

		UserVO userVO= null;
		try {
			userVO=user.getUserInfo(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("account",userVO.getAccount());
				obj.put("username",userVO.getUsername());
				obj.put("password",userVO.getPassword());
				obj.put("realName", userVO.getRealName());
				obj.put("mail",userVO.getMail());
				obj.put("birth",userVO.getBirth());
				obj.put("phone",userVO.getPhone());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			PrintWriter pw = response.getWriter();
			pw.write(obj.toString());
			pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
