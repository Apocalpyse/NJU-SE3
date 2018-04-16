package Servlet.BuyStockBlSer;

import VO.userVO.BuyVO;
import bl.buyStockBl.BuyStock;

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
public class realBuy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BuyStock buyStock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public realBuy(){
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
		String code=request.getParameter("code");
		String volume=request.getParameter("volume");
		String isBuy=request.getParameter("isBuy");


		String string= null;
		try {
			if(isBuy.equals("true")){
				string = buyStock.realBuy(new BuyVO(account,date,code,volume,true))+"";
			}
			else {
				string = buyStock.realBuy(new BuyVO(account,date,code,volume,false))+"";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter pw = response.getWriter();
		pw.write(string);
		pw.close();

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
