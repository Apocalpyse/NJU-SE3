package Servlet.ChooseAndReCommendBlSer;

import PO.marketPO.NotePool;
import PO.stockPO.StockPool;
import VO.stockVO.StockVO;
import VO.userVO.ChooseVO;
import bl.ChooseAndReCommendBl.ChooseAndReCommend;
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
public class choose extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChooseAndReCommend chooseAndReCommend;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public choose(){
		super();
		chooseAndReCommend=new ChooseAndReCommend();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String date=request.getParameter("start");
		String stockPool=request.getParameter("stockPool");
		String industryPool=request.getParameter("industryPool");
		String str1[]=request.getParameterValues("open");
		String str2[]=request.getParameterValues("close");
		String str3[]=request.getParameterValues("inDecrease");
		String str4[]=request.getParameterValues("volume");
		ArrayList<String> open=new ArrayList<String>();
		ArrayList<String> close=new ArrayList<String>();
		ArrayList<String> inDecrease=new ArrayList<String>();
		ArrayList<String> volume=new ArrayList<String>();
		for(int i=0;i<str1.length;i++){
			open.add(str1[i]);
		}
		for(int i=0;i<str2.length;i++){
			close.add(str2[i]);
		}
		for(int i=0;i<str3.length;i++){
			inDecrease.add(str1[i]);
		}
		for(int i=0;i<str4.length;i++){
			volume.add(str2[i]);
		}

		ArrayList<StockVO> arrayList=new ArrayList<StockVO>();
		try {
			//这里要修改
			//修改
			arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.ALL,industryPool,open,close,inDecrease,volume));
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized(this){
			JSONArray objArray = new JSONArray();
			for(int i=0;i<arrayList.size();i++){
				JSONObject obj = new JSONObject();
				try {
					obj.put("date",arrayList.get(i).getDate() );
					obj.put("open",arrayList.get(i).getOpen());
					obj.put("high",arrayList.get(i).getHigh());
					obj.put("low", arrayList.get(i).getLow());
					obj.put("close",arrayList.get(i).getClose());
					obj.put("adjClose",arrayList.get(i).getAdjClose());
					obj.put("code",arrayList.get(i).getCode());
					obj.put("name",arrayList.get(i).getName());
					obj.put("market",arrayList.get(i).getMarket());
					obj.put("increaseOrDecrease",arrayList.get(i).getIncreaseOrDecrease());
					obj.put("preAdjClose",arrayList.get(i).getPreAdjClose());
					obj.put("plate",arrayList.get(i).getPlate());
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
