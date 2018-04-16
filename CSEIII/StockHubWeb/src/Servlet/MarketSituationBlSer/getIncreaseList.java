package Servlet.MarketSituationBlSer;

import VO.stockVO.StockVO;
import bl.marketSituationBl.MarketSituation;
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
public class getIncreaseList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MarketSituation marketSituation;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getIncreaseList(){
		super();
		marketSituation=new MarketSituation();
	}

	public static void main(String[] args){
		MarketSituation marketSituation=new MarketSituation();
		ArrayList<StockVO> arrayList = null;
		try {
			arrayList=marketSituation.getIncreaseList("4/1/16");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList.size());
		System.out.println(arrayList.get(0).getClose());

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");

		ArrayList<StockVO> arrayList = null;
		try {
			arrayList=marketSituation.getIncreaseList(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("date",arrayList.get(i).getDate() );
					obj.put("open",arrayList.get(i).getOpen());
					obj.put("high",arrayList.get(i).getHigh());
					obj.put("low", arrayList.get(i).getLow());
					obj.put("close",arrayList.get(i).getClose());
					obj.put("volume", arrayList.get(i).getVolume());
					obj.put("adjClose",arrayList.get(i).getAdjClose());
					obj.put("code",arrayList.get(i).getCode());
					obj.put("name",arrayList.get(i).getName());
					obj.put("market",arrayList.get(i).getMarket());
					obj.put("increaseOrDecrease",arrayList.get(i).getIncreaseOrDecrease());
					obj.put("preAdjClose",arrayList.get(i).getPreAdjClose());
					obj.put("plate",arrayList.get(i).getPlate());
					obj.put("industry",arrayList.get(i).getIndustry());
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
