package Servlet.QuantourCompareBlSer;

import VO.stockVO.InputStockByCodeVO;
import VO.stockVO.StockCompareEverydayVO;

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
public class quantourChartCompareByCode extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private QuantourCompare quantourCompare;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public quantourChartCompareByCode(){
		super();
		quantourCompare=new QuantourCompare();
	}
	public static void main(String[] args){
		QuantourCompare quantourCompare=new QuantourCompare();
		ArrayList<InputStockByCodeVO> arrayList=new ArrayList<InputStockByCodeVO>();
		ArrayList<ArrayList<StockCompareEverydayVO>> arrayList1 = null;
		arrayList.add(new InputStockByCodeVO("1/5/16","5/30/17","000001"));
		arrayList.add(new InputStockByCodeVO("1/5/16","5/30/17","000004"));
		try {
			arrayList1=quantourCompare.quantourChartCompareByCode(arrayList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(arrayList1.get(0).get(0).getCode());

	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String start[]=request.getParameterValues("start");
		String end[]=request.getParameterValues("end");
		String code[]=request.getParameterValues("code");



		ArrayList<InputStockByCodeVO> arrayList=new ArrayList<InputStockByCodeVO>();
		for(int i=0;i<start.length;i++){
			arrayList.add(new InputStockByCodeVO(start[i],end[i],code[i]));
		}

		ArrayList<ArrayList<StockCompareEverydayVO>> arrayList1 = null;
		try {
			arrayList1=quantourCompare.quantourChartCompareByCode(arrayList);
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized(this){
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<arrayList1.size();i++){
				JSONArray objArray = new JSONArray();
				for (int j=0;j<arrayList1.get(i).size();j++){
					JSONObject obj = new JSONObject();
					try {
						obj.put("code",arrayList1.get(i).get(j).getCode() );
						obj.put("name",arrayList1.get(i).get(j).getName());
						obj.put("date",arrayList1.get(i).get(j).getDate());
						obj.put("logarithmicYield", arrayList1.get(i).get(j).getLogarithmicYield());
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					objArray.put(obj);
				}
				jsonArray.put(objArray.toString());
			}
			PrintWriter pw = response.getWriter();
			pw.write(jsonArray.toString());
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
