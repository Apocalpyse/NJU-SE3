package Servlet.PlateBlSer;

import VO.plateVO.OnePlateVO;
import VO.plateVO.TotalPlateVO;
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
 * Created by chenjin on 2017/5/25.
 */
public class getTotalPlateInfo extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private Plate plate;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getTotalPlateInfo(){
		super();
		plate=new Plate();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");


		TotalPlateVO totalPlateVO = null;
		try {
			totalPlateVO=plate.getTotalPlateInfo(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("date",totalPlateVO.getDate());
				obj.put("plateName",totalPlateVO.getPlateName());
				obj.put("companyNum",totalPlateVO.getCompanyNum());
				obj.put("averageOpen",totalPlateVO.getAverageOpen());
				obj.put("averageClose",totalPlateVO.getAverageClose());
				obj.put("averageAdjClose",totalPlateVO.getAverageAdjClose());
				obj.put("preAverageAdjClose",totalPlateVO.getPreAverageAdjClose());
				obj.put("increaseOrDecreaseMoney",totalPlateVO.getIncreaseOrDecreaseMoney());
				obj.put("increaseOrDecreaseRate",totalPlateVO.getIncreaseOrDecreaseRate());
				obj.put("totalVolume",totalPlateVO.getTotalVolume());

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
