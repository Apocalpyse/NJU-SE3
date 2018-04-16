package Servlet.IndustryBlSer;


import VO.industryVO.IndustryVO;
import bl.industryBl.Industry;
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
public class findTotalIndustryMaxDeRate extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Industry industry;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public findTotalIndustryMaxDeRate(){
		super();
		industry=new Industry();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");

		ArrayList<IndustryVO> arrayList = null;
		try {
			arrayList=industry.findTotalIndustryMaxDeRate(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("date",arrayList.get(i).getDate() );
					obj.put("industryName",arrayList.get(i).getIndustryName());
					obj.put("companyNum",arrayList.get(i).getCompanyNum());
					obj.put("averageOpen", arrayList.get(i).getAverageOpen());
					obj.put("averageClose",arrayList.get(i).getAverageClose());
					obj.put("increaseOrDecreaseMoney",arrayList.get(i).getIncreaseOrDecreaseMoney());
					obj.put("increaseOrDecreaseRate",arrayList.get(i).getIncreaseOrDecreaseRate());
					obj.put("totalVolume",arrayList.get(i).getTotalVolume());

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
