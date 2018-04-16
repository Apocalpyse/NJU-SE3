package Servlet.BuyStockBlSer;

import VO.userVO.BuyVO;
import bl.buyStockBl.BuyStock;
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
public class getRealBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuyStock buyStock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */

	public getRealBuy(){
		super();
		buyStock=new BuyStock();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String account=request.getParameter("account");
		String start=request.getParameter("start");
		String end=request.getParameter("end");

		ArrayList<BuyVO> arrayList = new ArrayList<BuyVO>();
		try {
			arrayList=buyStock.getRealBuy(account,start,end);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("account",arrayList.get(i).getAccount() );
					obj.put("date",arrayList.get(i).getDate());
					obj.put("code",arrayList.get(i).getCode());
					obj.put("volume",arrayList.get(i).getVolume());
					obj.put("isBuy",arrayList.get(i).isBuy());


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
