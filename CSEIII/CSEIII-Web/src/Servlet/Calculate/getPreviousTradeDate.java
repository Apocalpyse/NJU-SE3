package Servlet.Calculate;

import bl.calculateBl.Calculate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/5/25.
 */
public class getPreviousTradeDate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Calculate calculate;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getPreviousTradeDate(){
		super();
		calculate=new Calculate();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");


		String string=calculate.getPreviousTradeDate(date);
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
