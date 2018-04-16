package Servlet.InvestBlSer;

import VO.marketVO.InvestVO;
import bl.InvestBl.Invest;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by chenjin on 2017/6/9.
 */
public class getInvestT extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Invest invest;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getInvestT(){
		super();
		invest=new Invest();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String investID=request.getParameter("investID");

		InvestVO investVO = null;
		try {
			investVO=invest.getInvestT(investID);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("newsID",investVO.getInvestID() );
				obj.put("title",investVO.getTitle());
				obj.put("author",investVO.getAuthor());
				obj.put("time", investVO.getTime());
				obj.put("view",investVO.getView());
				obj.put("praise",investVO.getPraise());
				obj.put("criticize",investVO.getCriticize());
				obj.put("content",investVO.getContent());
				obj.put("comment",investVO.getComment());
				obj.put("commentAccount",investVO.getCommentAccount());
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
