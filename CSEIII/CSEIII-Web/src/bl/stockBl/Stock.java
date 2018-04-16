package bl.stockBl;

import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.stockVO.GoalVO;
import VO.stockVO.TwentyStockVO;
import blSer.StockBlSer;
import dataSer.FindPlateInfoDataSer;
import dataSer.FindStockInfoDataSer;
import dataSer.IndustryDataSer;
import dataSer.StrategyDataSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class Stock implements StockBlSer {
    @Override
    public GoalVO getStockGoalOne(String date, String codeOrName) throws Exception {
        Stock stock=new Stock();
        StrategyDataSer strategyDataSer = null;
        String start=strategyDataSer.getPreviousTradeDate(date,30);
        return stock.getStockGoal(start,date,codeOrName);
    }

    @Override
    public GoalVO getStockGoal(String start, String end, String codeOrName) throws Exception {
        Stock stock=new Stock();
        FindStockInfoDataSer findStockInfoDataSer = null;
        ArrayList<StockPO> arrayList;
        if(stock.isInt(codeOrName)){
            arrayList=findStockInfoDataSer.findStockInfoByCode(start,end,codeOrName);
        }
        else {
            arrayList=findStockInfoDataSer.findStockInfoByName(start,end,codeOrName);
        }
        double stability = 0;
        double one=0;
        for(int i=0;i<arrayList.size();i++){
            one=one+Integer.parseInt(arrayList.get(i).getClose());
        }
        one=stability/arrayList.size();
        for(int i=0;i<arrayList.size();i++){
            stability=(stability+Math.pow((one-Double.parseDouble(arrayList.get(i).getClose())),2));
        }
        stability=stability/arrayList.size();

        double prof=0;
        double two0=0,two1=0,two2=0;
        for(int j=0; j<(arrayList.size()/3);j++){
            two0=two0+Double.parseDouble(arrayList.get(j).getIncreaseOrDecrease());
        }
        for(int k=0; k<(arrayList.size()*2/3); k++){
            two1=two1+Double.parseDouble(arrayList.get(k).getIncreaseOrDecrease());
        }
        for(int m=0; m<arrayList.size(); m++){
            two2=two2+Double.parseDouble(arrayList.get(m).getIncreaseOrDecrease());
        }
        prof=two0*0.1+two1*0.3+two2*0.6;

        double mobility=0;
        double three=0;
        for(int i=0;i<arrayList.size();i++){
            double th=Math.log(Double.parseDouble(arrayList.get(i).getAdjClose()))-Math.abs(Double.parseDouble(arrayList.get(i).getClose()));
            three=three+Double.parseDouble(arrayList.get(i).getVolume())/(Math.abs(th));
        }
        mobility=three/arrayList.size();

        double safety;;
        double fo=0;
        FindPlateInfoDataSer findPlateInfoDataSer = null;
        for (int i=0;i<arrayList.size();i++){
            double four=Double.parseDouble((findPlateInfoDataSer.findTotalPlateInfo(arrayList.get(i).getDate()).getIncreaseOrDecreaseRate().get(3)));
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>four){
                fo=fo+1;
            }
        }
        safety=fo/arrayList.size();

        double development=0;
        double fi=0;
        IndustryDataSer industryDataSer = null;
        for (int i=0;i<arrayList.size();i++){
            double five=Double.parseDouble(industryDataSer.getIndustryInfo(arrayList.get(i).getDate(),industryDataSer.getIndustryName(codeOrName)).getIncreaseOrDecreaseRate());
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>five){
                fi=fi+1;
            }
        }
        development=fi/arrayList.size();

        double total=mobility+prof+stability+safety+development;
        return new GoalVO(total+"",stability+"",prof+"",mobility+"",safety+"",development+"");

    }

    @Override
    public TwentyStockVO getStockGoalTwenty(String start, String end, StockPool stockPool, ArrayList<String> codeOrName) throws Exception {
        Stock stock = null;
        IndustryDataSer industryDataSer = null;
        ArrStockPO arrStockPO;
        ArrayList<String> name = null, code = null;
        ArrayList<GoalVO> goal = null;
        if (!stockPool.equals(StockPool.SELECTPLATE)) {
            arrStockPO = industryDataSer.getPoolInfo(start, end, stockPool);
            for (int i = 0; i < arrStockPO.getName().size(); i++) {
                GoalVO goalVO = new GoalVO(stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getTotal(),
                        stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getStability(),
                        stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getProf(),
                        stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getMobility(),
                        stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getSafety(),
                        stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getDevelopment());
                name.add(arrStockPO.getName().get(i));
                code.add(arrStockPO.getCode().get(i));
                goal.add(goalVO);
            }
        } else {
            for (int i = 0; i < codeOrName.size(); i++) {
                GoalVO goalVO = new GoalVO(stock.getStockGoal(start, end, codeOrName.get(i)).getTotal(),
                        stock.getStockGoal(start, end, codeOrName.get(i)).getStability(),
                        stock.getStockGoal(start, end, codeOrName.get(i)).getProf(),
                        stock.getStockGoal(start, end, codeOrName.get(i)).getMobility(),
                        stock.getStockGoal(start, end, codeOrName.get(i)).getSafety(),
                        stock.getStockGoal(start, end, codeOrName.get(i)).getDevelopment());
                if (stock.isInt(codeOrName.get(i))) {
                    name.add(industryDataSer.getName(codeOrName.get(i)));
                    code.add(codeOrName.get(i));
                    goal.add(goalVO);
                } else {
                    name.add(codeOrName.get(i));
                    code.add(industryDataSer.getCode(codeOrName.get(i)));
                    goal.add(goalVO);
                }
            }
        }
        return new TwentyStockVO(start, end, name, code, goal);
    }
    @Override
    public TwentyStockVO getStockGoalTwentyIn(String start, String end, String industry) throws Exception {
        Stock stock = null;
        IndustryDataSer industryDataSer = null;
        ArrStockPO arrStockPO;
        ArrayList<String> name = null, code = null;
        ArrayList<GoalVO> goal = null;
        arrStockPO = industryDataSer.getIndustryInfo(start, end, industry);
        for (int i = 0; i < arrStockPO.getName().size(); i++) {
            GoalVO goalVO = new GoalVO(stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getTotal(),
                    stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getStability(),
                    stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getProf(),
                    stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getMobility(),
                    stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getSafety(),
                    stock.getStockGoal(start, end, arrStockPO.getCode().get(i)).getDevelopment());
            name.add(arrStockPO.getName().get(i));
            code.add(arrStockPO.getCode().get(i));
            goal.add(goalVO);
        }
        return new TwentyStockVO(start, end, name, code, goal);
    }

    @Override
    public ArrayList<String> getMaxProfit(String start, String end, StockPool stockPool,ArrayList<String> codeOrName) throws Exception {
        return null;
    }

    @Override
    public ArrayList<String> getMaxProfitIn(String start, String end, String industry) throws Exception {
        return null;
    }

    public boolean isInt(String string){
        try {
            int num=Integer.valueOf(string);//把字符串强制转换为数字
            return true;//如果是数字，返回True
        } catch (Exception e) {
            return false;//如果抛出异常，返回False
        }
    }
}
