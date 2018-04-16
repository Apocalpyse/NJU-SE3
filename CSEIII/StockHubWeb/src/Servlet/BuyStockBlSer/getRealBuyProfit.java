package Servlet.BuyStockBlSer;


import VO.userVO.ProfitVO;
import bl.buyStockBl.BuyStock;
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
public class getRealBuyProfit extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private BuyStock buyStock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getRealBuyProfit(){
		super();
		buyStock=new BuyStock();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String account=request.getParameter("account");
		String start=request.getParameter("start");
		String end=request.getParameter("end");


		ProfitVO profitVO= new ProfitVO();
		try {
			profitVO=buyStock.getRealBuyProfit(account,start,end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("totalProfit",profitVO.getTotalProfit());
				obj.put("dates",profitVO.getDates());
				obj.put("datesProfit",profitVO.getDatesProfit());

			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			PrintWriter pw = response.getWriter();
			pw.write(obj.toString());
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
