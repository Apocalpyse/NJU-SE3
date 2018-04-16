package Servlet.StockBlSer;

import PO.stockPO.StockPool;
import VO.stockVO.TwentyStockVO;
import bl.stockBl.Stock;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

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
		String codeOrName[]=request.getParameterValues("codeOrName");
		TwentyStockVO twentyStockVO= null;
		ArrayList<String> arrayList = null;
		for(int i=0;i<codeOrName.length;i++){
			arrayList.add(codeOrName[i]);
		}
		try {
			if(stockPool.equals("ALL")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.ALL,arrayList);
			}
			else if(stockPool.equals("MAINPLATE")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.MAINPLATE,arrayList);
			}
			else if(stockPool.equals("SHANGZHENG")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.SHANGZHENG,arrayList);
			}
			else if(stockPool.equals("SHENZHENG")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.SHENZHENG,arrayList);
			}
			else if(stockPool.equals("HUSHEN300")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.HUSHEN300,arrayList);
			}
			else if(stockPool.equals("SMALLMIDDLEPLATE")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.SMALLMIDDLEPLATE,arrayList);
			}
			else if(stockPool.equals("STARTUPPLATE")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.STARTUPPLATE,arrayList);
			}
			else if(stockPool.equals("SELECTPLATE")){
				twentyStockVO=stock.getStockGoalTwenty(start,end,StockPool.SELECTPLATE,arrayList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		synchronized(this){
			try {
				obj.put("start",twentyStockVO.getStart());
				obj.put("end",twentyStockVO.getEnd());
				obj.put("name",twentyStockVO.getName());
				obj.put("code",twentyStockVO.getCode());
				obj.put("goal",twentyStockVO.getGoal());


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		PrintWriter pw = response.getWriter();
		pw.write(obj.toString());
		pw.close();
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
