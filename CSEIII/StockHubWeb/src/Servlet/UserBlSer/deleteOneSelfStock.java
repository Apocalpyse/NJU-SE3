package Servlet.UserBlSer;

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
public class deleteOneSelfStock extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private User user;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteOneSelfStock(){

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
		String stockCodeOrName=request.getParameter("stockCodeOrName");



		String string= null;
		try {
			string = user.deleteOneSelfStock(account,stockCodeOrName)+"";
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
