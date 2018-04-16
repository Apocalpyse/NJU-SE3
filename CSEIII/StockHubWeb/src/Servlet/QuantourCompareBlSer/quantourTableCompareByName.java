package Servlet.QuantourCompareBlSer;


import VO.stockVO.InputStockByCodeVO;
import VO.stockVO.InputStockByNameVO;
import VO.stockVO.StockCompareTotalVO;
import bl.QuantourCompare.QuantourCompare;
import org.json.JSONArray;
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
public class quantourTableCompareByName extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private QuantourCompare quantourCompare;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public quantourTableCompareByName(){
		super();
		quantourCompare=new QuantourCompare();
	}
	public static void main(String[] args){
		QuantourCompare quantourCompare=new QuantourCompare();
		ArrayList<InputStockByNameVO> arrayList=new ArrayList<InputStockByNameVO>();
		ArrayList<StockCompareTotalVO> arrayList1 = null;
		arrayList.add(new InputStockByNameVO("1/5/16","5/30/17","平安银行"));
		arrayList.add(new InputStockByNameVO("1/5/16","5/30/17","沙河股份"));
		try {
			arrayList1=quantourCompare.quantourTableCompareByName(arrayList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList1.get(0).getLogarithmicYieldVariance());

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String start[]=request.getParameterValues("start");
		String end[]=request.getParameterValues("end");
		String name[]=request.getParameterValues("name");
		ArrayList<StockCompareTotalVO> arrayList1 = null;



		ArrayList<InputStockByNameVO> arrayList=new ArrayList<InputStockByNameVO>();
		for(int i=0;i<start.length;i++){
			arrayList.add(new InputStockByNameVO(start[i],end[i],name[i]));
		}
		try {
			arrayList1=quantourCompare.quantourTableCompareByName(arrayList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList1.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("code",arrayList1.get(i).getCode() );
					obj.put("name",arrayList1.get(i).getName());
					obj.put("high",arrayList1.get(i).getHigh());
					obj.put("low", arrayList1.get(i).getLow());
					obj.put("increaseOrDecrease",arrayList1.get(i).getIncreaseOrDecrease());
					obj.put("logarithmicYieldVariance",arrayList1.get(i).getLogarithmicYieldVariance());

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
