package Servlet.AddBlSer1;

import VO.stockVO.SimpleMainEventVO;
import bl.add1.AddOne;
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
public class getSimpleEventInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddOne addOne;
	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public getSimpleEventInfo(){
		super();
		addOne=new AddOne();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String industryName=request.getParameter("industryName");
		String date=request.getParameter("date");

		ArrayList<SimpleMainEventVO> arrayList = null;
		try {
			arrayList=addOne.getSimpleEventInfo(industryName,date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("Industry",arrayList.get(i).getIndustry());
					obj.put("title",arrayList.get(i).getTitle());
					obj.put("time",arrayList.get(i).getTime());

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
