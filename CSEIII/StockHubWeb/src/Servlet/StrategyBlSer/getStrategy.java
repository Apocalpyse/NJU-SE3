package Servlet.StrategyBlSer;




import VO.strategyVO.InputStrategyVO;
import VO.strategyVO.StrategyType;
import VO.strategyVO.StrategyVO;
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
public class getStrategy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StrategyController strategyController;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public getStrategy() {
		super();
		strategyController = new StrategyController();

	}
	public static void main(String[] args){
		StrategyController strategyController=new StrategyController();

		StrategyVO strategyVO = new StrategyVO();
		try {
			strategyVO=strategyController.getStrategy(new InputStrategyVO(StrategyType.MOMENT,"10","5","4/1/16","6/15/17",StockPoolBl.STARTUPPLATE,new ArrayList<String>()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println( strategyVO.getStaticalVariableVO().getYearYieldRate());

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

		StrategyVO strategyVO = new StrategyVO();

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
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.ALL, new ArrayList<String>()));
			} else if (stockPoolBl.equals("MAINPLATE")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.MAINPLATE, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SHANGZHENG")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SHANGZHENG,new ArrayList<String>()));
			} else if (stockPoolBl.equals("SHENZHENG")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SHENZHENG, new ArrayList<String>()));
			} else if (stockPoolBl.equals("HUSHEN300")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.HUSHEN300, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SMALLMIDDLEPLATE")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SMALLMIDDLEPLATE, new ArrayList<String>()));
			} else if (stockPoolBl.equals("STARTUPPLATE")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.STARTUPPLATE, new ArrayList<String>()));
			} else if (stockPoolBl.equals("SELECTPLATE")) {
				strategyVO = strategyController.getStrategy(new InputStrategyVO(strategyType, formationPeriod, holdingPeriod, start, end, StockPoolBl.SELECTPLATE, new ArrayList<String>()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		synchronized (this) {
			JSONObject obj = new JSONObject();
			try {
				obj.put("yearYieldRate", strategyVO.getStaticalVariableVO().getYearYieldRate());
				obj.put("benchmarkYearYieldRate", strategyVO.getStaticalVariableVO().getBenchmarkYearYieldRate());
				obj.put("alpha", strategyVO.getStaticalVariableVO().getAlpha());
				obj.put("beta", strategyVO.getStaticalVariableVO().getBeta());
				obj.put("sharpeRatio", strategyVO.getStaticalVariableVO().getSharpeRatio());
				obj.put("maxWithdraw", strategyVO.getStaticalVariableVO().getMaxWithdraw());
				obj.put("date", strategyVO.getStrategyGraphVO().getDate());
				obj.put("strategyYield", strategyVO.getStrategyGraphVO().getStrategyYield());
				obj.put("benchmarkYield", strategyVO.getStrategyGraphVO().getBenchmarkYield());
				obj.put("maxWithdrawStart", strategyVO.getStrategyGraphVO().getMaxWithdrawStart());
				obj.put("maxWithdrawEnd", strategyVO.getStrategyGraphVO().getMaxWithdrawEnd());
				obj.put("generalScore", strategyVO.getStrategyEvaluateVO().getGeneralScore());
				obj.put("yield", strategyVO.getStrategyEvaluateVO().getYield());
				obj.put("resistRisk", strategyVO.getStrategyEvaluateVO().getResistRisk());
				obj.put("stable", strategyVO.getStrategyEvaluateVO().getStable());
				obj.put("riskYield", strategyVO.getStrategyEvaluateVO().getRiskYield());
				obj.put("yieldSpace", strategyVO.getStrategyEvaluateVO().getYieldSpace());

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
