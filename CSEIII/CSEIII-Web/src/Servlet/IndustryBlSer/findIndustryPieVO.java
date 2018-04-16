package Servlet.IndustryBlSer;

import VO.stockVO.StockPieVO;
import bl.industryBl.Industry;
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
public class findIndustryPieVO extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Industry industry;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public findIndustryPieVO(){
		super();
		industry=new Industry();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");
		String industryName=request.getParameter("industryName");

		StockPieVO stockPieVO = null;
		try {
			stockPieVO=industry.findIndustryPieVO(date,industryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("date",stockPieVO.getDate());
				obj.put("decStop",stockPieVO.getDecStop());
				obj.put("rateLessNeg5",stockPieVO.getRateLessNeg5());
				obj.put("rateFromNeg5ToZero",stockPieVO.getRateFromNeg5ToZero());
				obj.put("rateFromZeroTo5",stockPieVO.getRateFromZeroTo5());
				obj.put("rateMore5",stockPieVO.getRateMore5());
				obj.put("incStop",stockPieVO.getIncStop());

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
