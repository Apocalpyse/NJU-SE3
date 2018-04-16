package Servlet.NoteBlSer;

import VO.marketVO.NoteVO;
import bl.noteBl.Note;
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
public class getNoteT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Note note;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getNoteT(){
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

		NoteVO noteVO = null;
		try {
			noteVO=note.getNoteT(newsID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("noteId",noteVO.getNoteId() );
				obj.put("title",noteVO.getTitle());
				obj.put("account",noteVO.getAccount());
				obj.put("time", noteVO.getTime());
				obj.put("source",noteVO.getSource());
				obj.put("stockCodeOrMethodID",noteVO.getStockCodeOrMethodID());
				obj.put("content",noteVO.getContent());
				obj.put("view",noteVO.getView());
				obj.put("praise",noteVO.getPraise());
				obj.put("criticize",noteVO.getCriticize());
				obj.put("comment",noteVO.getComment());
				obj.put("commentAccount",noteVO.getCommentAccount());
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
