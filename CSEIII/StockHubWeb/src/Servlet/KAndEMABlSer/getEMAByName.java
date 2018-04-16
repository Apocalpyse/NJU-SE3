package Servlet.KAndEMABlSer;

import VO.stockVO.EMAVO;
import VO.stockVO.InputStockByNameVO;
import bl.KAndEMABl.KAndEMA;
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
public class getEMAByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KAndEMA kAndEMA;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getEMAByName(){
		super();
		kAndEMA=new KAndEMA();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String name=request.getParameter("name");
		String numOfEMA=request.getParameter("numOfEMA");
		EMAVO emavo = null;
		try {
			emavo=kAndEMA.getEMAByName(new InputStockByNameVO(start,end,name),numOfEMA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("code",emavo.getCode());
				obj.put("name",emavo.getName());
				obj.put("start",emavo.getStart());
				obj.put("end",emavo.getEnd());
				obj.put("numOfDate",emavo.getNumOfDate());
				obj.put("date",emavo.getDate());
				obj.put("yield",emavo.getYield());
				obj.put("maxYield",emavo.getMaxYield());
				obj.put("minYield",emavo.getMinYield());
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
