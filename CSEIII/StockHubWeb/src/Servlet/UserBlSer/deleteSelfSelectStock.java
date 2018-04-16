package Servlet.UserBlSer;

import VO.userVO.SelfSelectStockVO;
import bl.userBl.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/25.
 */
public class deleteSelfSelectStock extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private User user;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public deleteSelfSelectStock(){

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
		String code[]=request.getParameterValues("code");
		String name[]=request.getParameterValues("name");
		String market[]=request.getParameterValues("market");

		ArrayList<String> one=null,two=null,three=null;
		for(int i=0;i<code.length;i++){
			one.add(code[i]);
		}
		for(int i=0;i<name.length;i++){
			two.add(name[i]);
		}
		for(int i=0;i<market.length;i++){
			three.add(market[i]);
		}

		String string= null;
		try {
			string = user.deleteSelfSelectStock(new SelfSelectStockVO(account,one,two,three))+"";
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
