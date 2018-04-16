package Servlet.StrategyBlSer;

import VO.strategyVO.InputStrategyVO;
import VO.strategyVO.RelationGraphVO;
import VO.strategyVO.StrategyType;

import bl.strategyBl.StrategyController;
import blSer.StockPoolBl;
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
public class getRelationGraphData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StrategyController strategyController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getRelationGraphData() {
		super();
		strategyController = new StrategyController();
	}
	public static void main(String[] args){
		StrategyController strategyController=new StrategyController();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		String type = request.getParameter("type");
		String formationPeriod = request.getParameter("formationPeriod");
		String holdingPeriod = request.getParameter("holdingPeriod");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String stockPoolBl = request.getParameter("stockPoolBl");
		String type1 = request.getParameter("type1");


		RelationGraphVO relationGraphVO = new RelationGraphVO();
		StrategyType strategyType = StrategyType.MOMENT;
		if (type.equals("MOMENT")) {
			strategyType = StrategyType.MOMENT;
		} else if (type.equals("AVERAGE")) {
			strategyType = StrategyType.AVERAGE;
		} else if (type.equals("MA")) {
			strategyType = StrategyType.MA;
		}

		try {
			if (stockPoolBl.equals("ALL")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.ALL, new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("MAINPLATE")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.MAINPLATE, new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("SHANGZHENG")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SHANGZHENG, new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("SHENZHENG")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SHANGZHENG,new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("HUSHEN300")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.HUSHEN300,new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("SMALLMIDDLEPLATE")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SMALLMIDDLEPLATE, new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("STARTUPPLATE")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.STARTUPPLATE, new ArrayList<String>()), Integer.parseInt(type1));
			} else if (stockPoolBl.equals("SELECTPLATE")) {
				relationGraphVO = strategyController.getRelationGraphData(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SELECTPLATE, new ArrayList<String>()), Integer.parseInt(type1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (this) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("period", relationGraphVO.getPeriod());
				obj.put("overYield",relationGraphVO.getOverYield());
				obj.put("winRate", relationGraphVO.getWinRate());


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
