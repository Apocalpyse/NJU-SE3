package Servlet.BuyStockBlSer;

import VO.stockVO.HoldVO;
import bl.buyStockBl.BuyStock;
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
public class getHoldBuy  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuyStock buyStock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getHoldBuy(){
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
		String date=request.getParameter("date");


		HoldVO holdVO= new HoldVO();
		try {
			holdVO=buyStock.getHoldBuy(account,date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("account",holdVO.getAccount());
				obj.put("date",holdVO.getDate());
				obj.put("totalMoney",holdVO.getTotalMoney());
				obj.put("holdCode",holdVO.getHoldCode());
				obj.put("holdMoney",holdVO.getHoldMoney());
				obj.put("holdCopies",holdVO.getHoldCopies());


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
