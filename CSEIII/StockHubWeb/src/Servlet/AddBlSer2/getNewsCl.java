package Servlet.AddBlSer2;

import VO.marketVO.NewsVO;
import bl.add2.AddTwo;
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
 * Created by chenjin on 2017/6/10.
 */
public class getNewsCl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddTwo addTwo;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getNewsCl(){
		super();
		addTwo=new AddTwo();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String classify=request.getParameter("classify");

		ArrayList<NewsVO> arrayList = null;
		try {
			arrayList=addTwo.getNewsCl(classify);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("newsID",arrayList.get(i).getNewsID() );
					obj.put("title",arrayList.get(i).getTitle());
					obj.put("classify",arrayList.get(i).getClassify());
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
