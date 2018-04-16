package Servlet.NoteBlSer;

import PO.marketPO.NotePool;
import VO.marketVO.NoteVO;
import bl.noteBl.Note;

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
public class addNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Note note;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public addNote(){
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
		String title=request.getParameter("title");
		String account=request.getParameter("account");
		String time=request.getParameter("time");
		String source=request.getParameter("source");
		String stockCodeOrMethodID=request.getParameter("stockCodeOrMethodID");
		String content=request.getParameter("content");
		String view=request.getParameter("view");
		String praise=request.getParameter("praise");
		String criticize=request.getParameter("criticize");
		String str1[]=request.getParameterValues("comment");
		String str2[]=request.getParameterValues("commentAccount");

		ArrayList<String> comment=new ArrayList<String>();
		ArrayList<String> commentAccount=new ArrayList<String>();
		for(int i=0;i<str1.length;i++){
			comment.add(str1[i]);
		}
		for(int i=0;i<str2.length;i++){
			commentAccount.add(str2[i]);
		}

		String string= null;
		try {
			if(source.equals("stock")){
				string = note.addNote(new NoteVO(newsID,title,account,time, NotePool.STOCK,stockCodeOrMethodID,content,Integer.parseInt(view),
						Integer.parseInt(praise),Integer.parseInt(criticize),comment,commentAccount))+"";
			}
			else {
				string = note.addNote(new NoteVO(newsID,title,account,time, NotePool.METHOD,stockCodeOrMethodID,content,Integer.parseInt(view),
						Integer.parseInt(praise),Integer.parseInt(criticize),comment,commentAccount))+"";
			}
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
