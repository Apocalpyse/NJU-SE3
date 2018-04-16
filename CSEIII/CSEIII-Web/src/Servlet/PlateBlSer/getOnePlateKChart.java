package Servlet.PlateBlSer;

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
		String stockPoolBl=request.getParameter("stockPoolBl");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");


		KChartVO kChartVO = null;
		try {
			//这里修改
			kChartVO=plate.getOnePlateKChart(StockPoolBl.ALL,startDate,endDate);
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
