package Servlet.StockBlSer;


import PO.stockPO.StockPool;
import bl.stockBl.Stock;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class getMaxProfit extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Stock stock;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getMaxProfit(){
		super();
		stock=new Stock();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		String stockPool=request.getParameter("stockPool");
		String codeOrName[]=request.getParameterValues("codeOrName");

		ArrayList<String> arrayList = null;
		for(int i=0;i<codeOrName.length;i++){
			arrayList.add(codeOrName[i]);
		}
		try {
			if(stockPool.equals("ALL")){
				arrayList=stock.getMaxProfit(start,end, StockPool.ALL,arrayList);
			}
			else if(stockPool.equals("MAINPLATE")){
				arrayList=stock.getMaxProfit(start,end,StockPool.MAINPLATE,arrayList);
			}
			else if(stockPool.equals("SHANGZHENG")){
				arrayList=stock.getMaxProfit(start,end,StockPool.SHANGZHENG,arrayList);
			}
			else if(stockPool.equals("SHENZHENG")){
				arrayList=stock.getMaxProfit(start,end,StockPool.SHENZHENG,arrayList);
			}
			else if(stockPool.equals("HUSHEN300")){
				arrayList=stock.getMaxProfit(start,end,StockPool.HUSHEN300,arrayList);
			}
			else if(stockPool.equals("SMALLMIDDLEPLATE")){
				arrayList=stock.getMaxProfit(start,end,StockPool.SMALLMIDDLEPLATE,arrayList);
			}
			else if(stockPool.equals("STARTUPPLATE")){
				arrayList=stock.getMaxProfit(start,end,StockPool.STARTUPPLATE,arrayList);
			}
			else if(stockPool.equals("SELECTPLATE")){
				arrayList=stock.getMaxProfit(start,end,StockPool.SELECTPLATE,arrayList);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		PrintWriter pw = response.getWriter();
		pw.write(arrayList.toString());
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
