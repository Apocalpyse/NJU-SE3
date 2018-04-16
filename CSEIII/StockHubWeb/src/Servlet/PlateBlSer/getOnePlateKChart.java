package Servlet.PlateBlSer;

import PO.stockPO.StockPool;
import VO.stockVO.KChartVO;
import bl.plateBl.Plate;
import blSer.StockPoolBl;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/6/1.
 */
public class getOnePlateKChart extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Plate plate;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getOnePlateKChart(){
		super();
		plate=new Plate();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String stockPool=request.getParameter("stockPool");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");


		KChartVO kChartVO = new KChartVO();


		try {
			if(stockPool.equals("SHANGZHENG")){
				kChartVO=plate.getOnePlateKChart(StockPool.SHANGZHENG,startDate,endDate);
			}
			else if(stockPool.equals("SHENZHENG")){
				kChartVO=plate.getOnePlateKChart(StockPool.SHENZHENG,startDate,endDate);
			}
			else if(stockPool.equals("HUSHEN300")){
				kChartVO=plate.getOnePlateKChart(StockPool.HUSHEN300,startDate,endDate);
			}
			else if(stockPool.equals("SMALLMIDDLEPLATE")){
				kChartVO=plate.getOnePlateKChart(StockPool.SMALLMIDDLEPLATE,startDate,endDate);
			}
			else if(stockPool.equals("STARTUPPLATE")){
				kChartVO=plate.getOnePlateKChart(StockPool.STARTUPPLATE,startDate,endDate);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("stockCode",kChartVO.getStockCode());
				obj.put("stockName",kChartVO.getStockName());
				obj.put("lastDatePrice",kChartVO.getLastDatePrice());
				obj.put("lastDateIncOrDecRate",kChartVO.getLastDateIncOrDecRate());
				obj.put("date",kChartVO.getDate());
				obj.put("adjClose",kChartVO.getAdjClose());
				obj.put("inOrDeYield",kChartVO.getInOrDeYield());


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
