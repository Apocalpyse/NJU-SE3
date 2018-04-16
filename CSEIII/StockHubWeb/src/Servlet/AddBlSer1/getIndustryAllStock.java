package Servlet.AddBlSer1;

import bl.add1.AddOne;
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
 * Created by chenjin on 2017/6/15.
 */
public class getIndustryAllStock extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private AddOne addOne;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getIndustryAllStock(){
		super();
		addOne=new AddOne();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String industryName=request.getParameter("industryName");


		ArrayList<String> arrayList=new ArrayList<String>();
		try {
			arrayList=addOne.getIndustryAllStock(industryName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONObject obj = new JSONObject();
			try {
				obj.put("stockName",arrayList);

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
