package Servlet.StockBlSer;

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

/**
 * Created by chenjin on 2017/5/25.
 */
public class getStockGoalTwentyIn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stock stock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getStockGoalTwentyIn(){
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
		String industry=request.getParameter("industry");

		TwentyStockVO twentyStockVO= null;


		try {
			twentyStockVO=stock.getStockGoalTwentyIn(start,end,industry);
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
