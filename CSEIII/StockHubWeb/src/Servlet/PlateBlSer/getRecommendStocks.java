package Servlet.PlateBlSer;

import PO.stockPO.StockPool;
import VO.stockVO.StockVO;
import bl.plateBl.Plate;
import blSer.StockPoolBl;
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
public class getRecommendStocks extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Plate plate;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getRecommendStocks(){
		super();
		plate=new Plate();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");
		String stockPool=request.getParameter("stockPool");

		ArrayList<StockVO> arrayList = null;

			try {
				if(stockPool.equals("SHANGZHENG")){
					arrayList=plate.getRecommendStocks(StockPool.SHANGZHENG,date);
				}
				else if(stockPool.equals("SHENZHENG")){
					arrayList=plate.getRecommendStocks(StockPool.SHENZHENG,date);
				}
				else if(stockPool.equals("HUSHEN300")){
					arrayList=plate.getRecommendStocks(StockPool.HUSHEN300,date);
				}
				else if(stockPool.equals("SMALLMIDDLEPLATE")){
					arrayList=plate.getRecommendStocks(StockPool.SMALLMIDDLEPLATE,date);
				}
				else if(stockPool.equals("STARTUPPLATE")){
					arrayList=plate.getRecommendStocks(StockPool.STARTUPPLATE,date);
				}
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
					obj.put("volume",arrayList.get(i).getVolume());
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
