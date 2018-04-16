package Servlet.UserBlSer;

import VO.userVO.UserVO;
import bl.userBl.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/5/25.
 */
public class changeUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public changeUserInfo(){

		super();
		user=new User();
	}
	public static void main(String[] args){
		System.out.println(123);
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");

		String account=request.getParameter("account");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String realName=request.getParameter("realName");
		String mail=request.getParameter("mail");
		String birth=request.getParameter("birth");
		String phone=request.getParameter("phone");



		String string= null;
		try {
			string = user.changeUserInfo(new UserVO(account,username,password,realName,mail,birth,phone))+"";
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter pw = response.getWriter();
		pw.write(string);
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}


}
