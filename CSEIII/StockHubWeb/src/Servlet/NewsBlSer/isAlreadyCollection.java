package Servlet.NewsBlSer;

import bl.newsBl.News;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/6/15.
 */
public class isAlreadyCollection extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private News news;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public isAlreadyCollection(){
		super();
		news=new News();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String newsID=request.getParameter("newsID");
		String account=request.getParameter("account");



		String string= null;
		try {
			string = news.isAlreadyCollection(account,newsID)+"";
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
