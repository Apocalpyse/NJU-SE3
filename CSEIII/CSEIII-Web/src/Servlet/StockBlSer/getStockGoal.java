package Servlet.StockBlSer;

import VO.stockVO.GoalVO;
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
public class getStockGoal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stock stock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getStockGoal(){
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
		String codeOrName=request.getParameter("codeOrName");
		GoalVO goalVO = null;

		try {
			goalVO=stock.getStockGoal(start,end,codeOrName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		JSONObject obj = new JSONObject();
		synchronized(this){
			try {
				obj.put("total",goalVO.getTotal());
				obj.put("stability",goalVO.getStability());
				obj.put("prof",goalVO.getProf());
				obj.put("mobility",goalVO.getMobility());
				obj.put("safety",goalVO.getSafety());
				obj.put("development",goalVO.getDevelopment());

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
