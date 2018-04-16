package Servlet.StockBlSer;

import PO.stockPO.StockPool;
import VO.stockVO.TwentyStockVO;
import bl.stockBl.Stock;
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
public class getStockGoalTwenty extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stock stock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getStockGoalTwenty(){
		super();
		stock=new Stock();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String stockPool=request.getParameter("stockPool");

		TwentyStockVO arrayList= null;

		try {
			if(stockPool.equals("ALL")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.ALL,new ArrayList<>());
			}
			else if(stockPool.equals("MAINPLATE")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.MAINPLATE,new ArrayList<>());
			}
			else if(stockPool.equals("SHANGZHENG")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.SHANGZHENG,new ArrayList<>());
			}
			else if(stockPool.equals("SHENZHENG")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.SHENZHENG,new ArrayList<>());
			}
			else if(stockPool.equals("HUSHEN300")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.HUSHEN300,new ArrayList<>());
			}
			else if(stockPool.equals("SMALLMIDDLEPLATE")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.SMALLMIDDLEPLATE,new ArrayList<>());
			}
			else if(stockPool.equals("STARTUPPLATE")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.STARTUPPLATE,new ArrayList<>());
			}
			else if(stockPool.equals("SELECTPLATE")){
				arrayList=stock.getStockGoalTwenty(start,end, StockPool.SELECTPLATE,new ArrayList<>());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.getGoal().size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("start",arrayList.getStart() );
					obj.put("end",arrayList.getEnd());
					obj.put("code",arrayList.getGoal().get(i).getCode());
					obj.put("name", arrayList.getGoal().get(i).getName());
					obj.put("total",arrayList.getGoal().get(i).getTotal());
					obj.put("stability",arrayList.getGoal().get(i).getStability());
					obj.put("prof",arrayList.getGoal().get(i).getProf());
					obj.put("mobility",arrayList.getGoal().get(i).getMobility());
					obj.put("safety",arrayList.getGoal().get(i).getSafety());
					obj.put("development",arrayList.getGoal().get(i).getDevelopment());

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
