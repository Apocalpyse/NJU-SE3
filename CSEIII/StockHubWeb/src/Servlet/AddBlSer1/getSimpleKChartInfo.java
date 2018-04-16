package Servlet.AddBlSer1;

import VO.stockVO.KChartVO;
import bl.add1.AddOne;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/6/10.
 */
public class getSimpleKChartInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddOne addOne;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getSimpleKChartInfo(){
		super();
		addOne=new AddOne();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String code=request.getParameter("code");
		String start=request.getParameter("start");
		String end=request.getParameter("end");

		KChartVO kChartVO = new KChartVO();
		try {
			kChartVO=addOne.getSimpleKChartInfo(code,start,end);
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
