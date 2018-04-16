package Servlet.InvestBlSer;

import VO.marketVO.InvestVO;
import bl.InvestBl.Invest;
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
 * Created by chenjin on 2017/6/9.
 */
public class getInvestD extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Invest invest;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getInvestD(){
		super();
		invest=new Invest();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");

		ArrayList<InvestVO> arrayList = null;
		try {
			arrayList=invest.getInvestD(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("investID",arrayList.get(i).getInvestID());
					obj.put("title",arrayList.get(i).getTitle());
					obj.put("author",arrayList.get(i).getAuthor());
					obj.put("time", arrayList.get(i).getTime());
					obj.put("view",arrayList.get(i).getView());
					obj.put("praise",arrayList.get(i).getPraise());
					obj.put("criticize",arrayList.get(i).getCriticize());
					obj.put("content",arrayList.get(i).getContent());
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
