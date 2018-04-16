package Servlet.PlateBlSer;

import VO.plateVO.OnePlateVO;
import VO.plateVO.TotalPlateVO;
import bl.plateBl.Plate;
import blSer.StockPoolBl;
import org.json.JSONArray;
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
public class getOnePlateInfo extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private Plate plate;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getOnePlateInfo(){
		super();
		plate=new Plate();
	}
	public static void main(String[] args){
		Plate plate=new Plate();

		OnePlateVO arrayList = null;
		try {
			arrayList=plate.getOnePlateInfo(StockPoolBl.SMALLMIDDLEPLATE,"4/1/16");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList.getStockVOS().size());

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("date");
		String stockPoolBl=request.getParameter("stockPoolBl");


		OnePlateVO onePlateVO = null;


		try {
			if(stockPoolBl.equals("ALL")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.ALL,date);
			}
			else if(stockPoolBl.equals("MAINPLATE")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.MAINPLATE,date);
			}
			else if(stockPoolBl.equals("SHANGZHENG")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.SHANGZHENG,date);
			}
			else if(stockPoolBl.equals("SHENZHENG")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.SHENZHENG,date);
			}
			else if(stockPoolBl.equals("HUSHEN300")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.HUSHEN300,date);
			}
			else if(stockPoolBl.equals("SMALLMIDDLEPLATE")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.SMALLMIDDLEPLATE,date);
			}
			else if(stockPoolBl.equals("STARTUPPLATE")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.STARTUPPLATE,date);
			}
			else if(stockPoolBl.equals("SELECTPLATE")){
				onePlateVO=plate.getOnePlateInfo(StockPoolBl.SELECTPLATE,date);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<onePlateVO.getStockVOS().size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("date",onePlateVO.getStockVOS().get(i).getDate());
					obj.put("open",onePlateVO.getStockVOS().get(i).getOpen());
					obj.put("high",onePlateVO.getStockVOS().get(i).getHigh());
					obj.put("low", onePlateVO.getStockVOS().get(i).getLow());
					obj.put("close",onePlateVO.getStockVOS().get(i).getClose());
					obj.put("volume", onePlateVO.getStockVOS().get(i).getVolume());
					obj.put("adjClose",onePlateVO.getStockVOS().get(i).getAdjClose());
					obj.put("code",onePlateVO.getStockVOS().get(i).getCode());
					obj.put("name",onePlateVO.getStockVOS().get(i).getName());
					obj.put("market",onePlateVO.getStockVOS().get(i).getMarket());
					obj.put("increaseOrDecrease",onePlateVO.getStockVOS().get(i).getIncreaseOrDecrease());
					obj.put("preAdjClose",onePlateVO.getStockVOS().get(i).getPreAdjClose());
					obj.put("plate",onePlateVO.getStockVOS().get(i).getPlate());
					obj.put("industry",onePlateVO.getStockVOS().get(i).getIndustry());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objArray.put(obj);
			}


			PrintWriter pw = response.getWriter();
			pw.write(objArray.toString());
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
