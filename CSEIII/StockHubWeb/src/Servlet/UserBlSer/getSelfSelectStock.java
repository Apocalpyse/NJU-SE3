package Servlet.UserBlSer;

import VO.userVO.SelfSelectStockVO;
import bl.userBl.User;
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
public class getSelfSelectStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private User user;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getSelfSelectStock(){
		super();
		user=new User();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String account=request.getParameter("account");


		SelfSelectStockVO selfSelectStockVO= null;
		try {
			selfSelectStockVO=user.getSelfSelectStock(account);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("account",selfSelectStockVO.getAccount());
				obj.put("code",selfSelectStockVO.getCode());
				obj.put("name",selfSelectStockVO.getName());
				obj.put("market", selfSelectStockVO.getMarket());


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
