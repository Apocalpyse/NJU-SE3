package Servlet.StrategyBlSer;

import VO.strategyVO.InputStrategyVO;
import VO.strategyVO.StrategyType;
import VO.strategyVO.YieldRateDistributionVO;
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
public class getYieldRateDistribution extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StrategyController strategyController;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getYieldRateDistribution() {
		super();
		strategyController = new StrategyController();
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


		YieldRateDistributionVO yieldRateDistributionVO = new YieldRateDistributionVO();
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
				yieldRateDistributionVO = strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.ALL, new ArrayList<String>()));
			} else if (stockPoolBl.equals("MAINPLATE")) {
				yieldRateDistributionVO= strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.MAINPLATE, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SHANGZHENG")) {
				yieldRateDistributionVO= strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SHANGZHENG, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SHENZHENG")) {
				yieldRateDistributionVO = strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SHENZHENG, new ArrayList<String>()));
			} else if (stockPoolBl.equals("HUSHEN300")) {
				yieldRateDistributionVO= strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.HUSHEN300, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SMALLMIDDLEPLATE")) {
				yieldRateDistributionVO = strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SMALLMIDDLEPLATE,new ArrayList<String>()));
			} else if (stockPoolBl.equals("STARTUPPLATE")) {
				yieldRateDistributionVO= strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.STARTUPPLATE, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SELECTPLATE")) {
				yieldRateDistributionVO = strategyController.getYieldRateDistribution(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SELECTPLATE, new ArrayList<String>()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		synchronized (this) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("positiveYieldNum", yieldRateDistributionVO.getPositiveYieldNum());
				obj.put("negativeYieldNum", yieldRateDistributionVO.getNegativeYieldNum());
				obj.put("winRate", yieldRateDistributionVO.getWinRate());
				obj.put("yieldRate", yieldRateDistributionVO.getYieldRate());
				obj.put("positiveFrequency", yieldRateDistributionVO.getPositiveFrequency());
				obj.put("negativeFrequency", yieldRateDistributionVO.getNegativeYieldNum());


			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			PrintWriter pw = response.getWriter();
			pw.write(obj.toString());
			pw.close();
		}

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
