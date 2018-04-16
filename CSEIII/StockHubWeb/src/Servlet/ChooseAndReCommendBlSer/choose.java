package Servlet.ChooseAndReCommendBlSer;

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
		String date=request.getParameter("date");
		String stockPool=request.getParameter("stockPool");
		String industryPool=request.getParameter("industryPool");
		String open1=request.getParameter("open1");
		String open2=request.getParameter("open2");
		String close1=request.getParameter("close1");
		String close2=request.getParameter("close2");
		String inDecrease1=request.getParameter("inDecrease1");
		String inDecrease2=request.getParameter("inDecrease2");
		String volume1=request.getParameter("volume1");
		String volume2=request.getParameter("volume2");

		ArrayList<String> open=new ArrayList<String>();
		ArrayList<String> close=new ArrayList<String>();
		ArrayList<String> inDecrease=new ArrayList<String>();
		ArrayList<String> volume=new ArrayList<String>();
		open.add(open1);
		open.add(open2);
		close.add(close1);
		close.add(close2);
		inDecrease.add(inDecrease1);
		inDecrease.add(inDecrease2);
		volume.add(volume1);
		volume.add(volume2);

		ArrayList<StockVO> arrayList=new ArrayList<StockVO>();
		try {
			if(stockPool.equals("ALL")){
				arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.ALL,industryPool,open,close,inDecrease,volume));
			}
			else if(stockPool.equals("SHANGZHENG")){
				arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.SHANGZHENG,industryPool,open,close,inDecrease,volume));
			}
			else if(stockPool.equals("SHENZHENG")){
				arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.SHENZHENG,industryPool,open,close,inDecrease,volume));
			}
			else if(stockPool.equals("HUSHEN300")){
				arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.HUSHEN300,industryPool,open,close,inDecrease,volume));
			}
			else if(stockPool.equals("SMALLMIDDLEPLATE")){
				arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.SMALLMIDDLEPLATE,industryPool,open,close,inDecrease,volume));
			}
			else if(stockPool.equals("STARTUPPLATE")){
				arrayList=chooseAndReCommend.choose(new ChooseVO(date, StockPool.STARTUPPLATE,industryPool,open,close,inDecrease,volume));
			}
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
					obj.put("volume",arrayList.get(i).getVolume());
					obj.put("adjClose",arrayList.get(i).getAdjClose());
					obj.put("code",arrayList.get(i).getCode());
					obj.put("name",arrayList.get(i).getName());
					obj.put("market",arrayList.get(i).getMarket());
					obj.put("increaseOrDecrease",arrayList.get(i).getIncreaseOrDecrease());
					obj.put("preAdjClose",arrayList.get(i).getPreAdjClose());
					obj.put("plate",arrayList.get(i).getPlate());
					obj.put("industry",arrayList.get(i).getIndustry());
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
