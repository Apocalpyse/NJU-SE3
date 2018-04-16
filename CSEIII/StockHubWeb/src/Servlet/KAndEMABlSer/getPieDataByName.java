package Servlet.KAndEMABlSer;

import VO.stockVO.InputStockByCodeVO;
import VO.stockVO.InputStockByNameVO;
import VO.stockVO.PieVO;
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
public class getPieDataByName extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private KAndEMA kAndEMA;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getPieDataByName(){
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

		PieVO pieVO = new PieVO();
		try {
			pieVO=kAndEMA.getPieDataByName(new InputStockByNameVO(start,end,name));
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("name",pieVO.getName());
				obj.put("code",pieVO.getCode());
				obj.put("start",pieVO.getStart());
				obj.put("end",pieVO.getEnd());
				obj.put("increaseLessDays",pieVO.getIncreaseLessDays());
				obj.put("increaseMoreDays",pieVO.getIncreaseMoreDays());
				obj.put("decreaseLessDays",pieVO.getDecreaseLessDays());
				obj.put("decreaseMoredays",pieVO.getIncreaseMoreDays());

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
