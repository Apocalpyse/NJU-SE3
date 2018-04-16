package Servlet.NoteBlSer;

import PO.marketPO.NotePool;
import VO.marketVO.NoteVO;
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
 * Created by chenjin on 2017/6/1.
 */
public class getNoteSt extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Note note;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getNoteSt(){
		super();
		note=new Note();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String notePool=request.getParameter("notePool");
		String stockCodeOrMethodID=request.getParameter("StockCodeOrMethodID");

		ArrayList<NoteVO> arrayList = null;
		try {
			if(notePool.equals("stock")){
				arrayList=note.getNoteSt(NotePool.STOCK,stockCodeOrMethodID);
			}
			else {
				arrayList=note.getNoteSt(NotePool.METHOD,stockCodeOrMethodID);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("noteId",arrayList.get(i).getNoteId() );
					obj.put("title",arrayList.get(i).getTitle());
					obj.put("account",arrayList.get(i).getAccount());
					obj.put("time", arrayList.get(i).getTime());
					obj.put("source",arrayList.get(i).getSource());
					obj.put("stockCodeOrMethodID",arrayList.get(i).getStockCodeOrMethodID());
					obj.put("content",arrayList.get(i).getContent());
					obj.put("view",arrayList.get(i).getView());
					obj.put("praise",arrayList.get(i).getPraise());
					obj.put("criticize",arrayList.get(i).getCriticize());
					obj.put("comment",arrayList.get(i).getComment());
					obj.put("commentAccount",arrayList.get(i).getCommentAccount());
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
