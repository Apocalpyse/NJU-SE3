package Servlet.NoteBlSer;

import bl.noteBl.Note;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/5/25.
 */
public class addComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Note note;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addComment(){
		super();
		note=new Note();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String newsID=request.getParameter("newsID");
		String com=request.getParameter("com");
		String comAccount=request.getParameter("comAccount");



		String string= null;
		try {
			string = note.addComment(newsID,com,comAccount)+"";
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
