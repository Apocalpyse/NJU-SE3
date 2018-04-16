package Servlet.AddBlSer1;

import VO.stockVO.CompanyVO;
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
public class getCompanyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddOne addOne;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getCompanyInfo(){
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

		CompanyVO companyVO = null;
		try {
			companyVO=addOne.getCompanyInfo(code);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("code",companyVO.getCode());
				obj.put("stockName",companyVO.getStockName());
				obj.put("industry",companyVO.getIndustry());
				obj.put("area",companyVO.getArea());
				obj.put("totalassets",companyVO.getTotalassets());
				obj.put("liquidassets",companyVO.getLiquidassets());
				obj.put("fixedassets",companyVO.getFixedassets());
				obj.put("uptime",companyVO.getUptime());
				obj.put("holders",companyVO.getHolders());

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
