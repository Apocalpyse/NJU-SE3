package Servlet.MarketSituationBlSer;

import VO.marketVO.MarketSituationVO;
import VO.stockVO.StockVO;
import bl.marketSituationBl.MarketSituation;
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
public class getMarketSituation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MarketSituation marketSituation;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getMarketSituation(){
		super();
		marketSituation=new MarketSituation();
	}
	public static void main(String[] args){
		MarketSituation marketSituation=new MarketSituation();
		MarketSituationVO arrayList = null;
		try {
			arrayList=marketSituation.getMarketSituation("4/1/16");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList.getDecreaseMoreStockNum());

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");

		MarketSituationVO marketSituationVO=null;
		try {
			marketSituationVO=marketSituation.getMarketSituation(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("date",marketSituationVO.getDate());
				obj.put("totalVolume",marketSituationVO.getTotalVolume());
				obj.put("increaseStopStockNum",marketSituationVO.getIncreaseStopStockNum());
				obj.put("decreaseStopStockNum",marketSituationVO.getDecreaseStopStockNum());
				obj.put("increaseMoreStockNum",marketSituationVO.getIncreaseMoreStockNum());
				obj.put("decreaseMoreStockNum",marketSituationVO.getDecreaseMoreStockNum());
				obj.put("openMinusCloseMoreNum",marketSituationVO.getOpenMinusCloseMoreNum());
				obj.put("openMinusCloseLessNum",marketSituationVO.getOpenMinusCloseLessNum());
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
