package Servlet.SearchBlSer;

import VO.stockVO.OneStockInfoVO;
import bl.searchBl.Search;
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
public class findAllStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Search search;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public findAllStock(){
		super();
		search=new Search();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String codeOrName=request.getParameter("codeOrName");


		OneStockInfoVO oneStockInfoVO = null;
		try {
			//以下修改
			oneStockInfoVO=search.findAllStock(codeOrName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("stockVOS",oneStockInfoVO.getStockVOS());
				//还有
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
