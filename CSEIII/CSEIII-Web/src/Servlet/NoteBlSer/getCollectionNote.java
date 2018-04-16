package Servlet.NoteBlSer;

import PO.marketPO.NotePool;
import VO.marketVO.SimpleNoteVO;
import bl.noteBl.Note;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class getCollectionNote extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Note note;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getCollectionNote(){
		super();
		note=new Note();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String account=request.getParameter("account");
		String notePool=request.getParameter("notePool");

		ArrayList<SimpleNoteVO> arrayList = null;
		try {
			if(notePool.equals("stock")){
				arrayList=note.getCollectionNote(account, NotePool.STOCK);
			}
			else{
				arrayList=note.getCollectionNote(account, NotePool.METHOD);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("newsID",arrayList.get(i).getNoteID());
					obj.put("title",arrayList.get(i).getTitle());
					obj.put("account",arrayList.get(i).getAccount());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objArray.put(obj);
			}
			PrintWriter pw = response.getWriter();
			pw.write(objArray.toString());
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
