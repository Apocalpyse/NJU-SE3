package bl.stockBl;


import PO.platePO.TotalPlatePO;

import PO.stockPO.EvaluateStockS;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.stockVO.GoalVO;
import VO.stockVO.TwentyStockVO;
import blSer.StockBlSer;
import data.Add2Data.Add2Data;
import data.industryData.IndustryData;
import data.plateData.FindPlateInfoData;
import data.stockData.FindStockInfoData;
import data.strategyData.StrategyData;
import dataSer.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by A on 2017/5/21.
 */
public class Stock implements StockBlSer {
    public static void main(String[] args){

        Stock stock=new Stock();
        GoalVO goalVO=null;
        try {
           goalVO =stock.getStockGoal("5/17/16","6/15/16","000001");
        } catch (Exception e) {
            e.printStackTrace();
        }

            System.out.println(goalVO.getTotal());
        System.out.println(goalVO.getStability());
        System.out.println(goalVO.getProf());
        System.out.println(goalVO.getMobility());
        System.out.println(goalVO.getSafety());
        System.out.println(goalVO.getDevelopment());



        /*System.out.println(twentyStockVO.getTotal());
        System.out.println(twentyStockVO.getStability());
        System.out.println(twentyStockVO.getProf());
        System.out.println(twentyStockVO.getDevelopment());
        System.out.println(twentyStockVO.getSafety());
        System.out.println(twentyStockVO.getMobility());
        System.out.println(twentyStockVO.getCode());
        System.out.println(twentyStockVO.getName());
*/


    }
    @Override
    public GoalVO getStockGoalOne(String date, String codeOrName) throws Exception{
        Stock stock=new Stock();
        StrategyDataSer strategyDataSer = new StrategyData();
        String start=strategyDataSer.getPreviousTradeDate(date,30);
        return stock.getStockGoal(start,date,codeOrName);
    }

    @Override
    public GoalVO getStockGoal(String start, String end, String codeOrName) throws Exception {
        Stock stock=new Stock();
    FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
    IndustryDataSer industryDataSer=new IndustryData();
        ArrayList<StockPO> arrayList=new ArrayList<StockPO>();
        String code=codeOrName,name=codeOrName;
        if(stock.isInt(codeOrName)){
            arrayList=findStockInfoDataSer.findStockInfoByCode(start,end,codeOrName);
            name=industryDataSer.getName(codeOrName);
        }
        else {
            arrayList=findStockInfoDataSer.findStockInfoByName(start,end,codeOrName);
            code=industryDataSer.getCode(codeOrName);
        }

        double stability = 0;
        double one=0;
        for(int i=0;i<arrayList.size();i++){
            one=one+Double.parseDouble(arrayList.get(i).getClose());

        }

        one=one/arrayList.size();
        for(int i=0;i<arrayList.size();i++){
            stability=(stability+Math.pow((one-Double.parseDouble(arrayList.get(i).getClose())),2));
        }
        stability=stability/arrayList.size();
        String str=stability/0.005+"";
        String string[]=str.split("\\.");
        int o=Integer.parseInt(string[0]);
        int sta=98-o*4;
        if(sta<=60){
            sta=60;
        }


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
        String str1=prof+"";
        String string1[]=str1.split("\\.");
        int o1=Integer.parseInt(string1[0]);
        int pr=85+o1*2;
        if(pr>98){
            pr=98;
        }
        if(pr<=60){
            pr=60;
        }

        double mobility=0;
        double three=0;
        for(int i=0;i<arrayList.size();i++){
            three=three+Double.parseDouble(arrayList.get(i).getVolume());
        }
        mobility=three/arrayList.size();

        String str2=mobility/200000+"";
        String string2[]=str2.split("\\.");
        int o2=Integer.parseInt(string2[0]);
        int mo=70+o2*1;
        if(mo>=98){
            pr=98;
        }
        if(mo<=60){
            pr=60;
        }


        double safety;;
        double fo=0;
        FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();

        ArrayList<TotalPlatePO> arrayList1=findPlateInfoDataSer.findOnePlateInfo(start,end,StockPool.SHANGZHENG);

        for (int i=0;i<arrayList.size();i++){
            if(Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease())>Double.parseDouble(arrayList1.get(i).getIncreaseOrDecreaseRate().get(0))){
                fo=fo+1;
            }
        }
        safety=fo/arrayList.size()*100;
        String str3=safety+"";
        String string3[]=str3.split("\\.");
        int o3=Integer.parseInt(string3[0]);
        int sa=o3+20;
        if(sa>=98){
            sa=98;
        }
        if(mo<=50){
            sa=50;
        }


        double development=0;
        double fi=0;
        for (int i=0;i<arrayList.size();i++){
           fi=fi+Double.parseDouble(arrayList.get(i).getIncreaseOrDecrease());
        }
        development=fi/arrayList.size()*500;
        String str4=development+"";
        String string4[]=str4.split("\\.");
        int o4=Integer.parseInt(string4[0]);
        int de=60+o4;
        if(de>=95){
            de=95;
        }
        if(de<=60){
            de=60;
        }

        double total=(sta+pr+mo+sa+de)/5;
        String str5=total+"";
        String string5[]=str5.split("\\.");
        int o5=Integer.parseInt(string5[0]);
        int to=o5;
        if(to>=98){
            to=98;
        }
        if(to<=60){
            to=60;
        }
        return new GoalVO(code,name,to+"",sta+"",pr+"",mo+"",sa+"",de+"");

    }

    @Override
    public TwentyStockVO getStockGoalTwenty(String start, String end, StockPool stockPool, ArrayList<String> codeOrName) throws Exception {
        Stock stock = new Stock();
        Add2DataSer add2DataSer=new Add2Data();
        ArrayList<EvaluateStockS> evaluateStockSS=new ArrayList<EvaluateStockS>();
        FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
        ArrayList<GoalVO> goal = new ArrayList<GoalVO>();
        if (!stockPool.equals(StockPool.SELECTPLATE)) {
            evaluateStockSS = add2DataSer.getPlateStockEva(stockPool,start,end);
            System.out.println(evaluateStockSS.size());
            FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
            ArrayList<TotalPlatePO> arrayList1=findPlateInfoDataSer.findOnePlateInfo(start,end,StockPool.SHANGZHENG);
            System.out.println(321);
            for (int i = 0; i < evaluateStockSS.size(); i++) {
                double stability = 0;
                double one=0;
                for(int i1=0;i1<evaluateStockSS.get(i).getAdjClose().size();i1++){
                    one=one+Double.parseDouble(evaluateStockSS.get(i).getAdjClose().get(i1));

                }

                one=one/evaluateStockSS.get(i).getAdjClose().size();
                for(int i1=0;i1<evaluateStockSS.get(i).getAdjClose().size();i1++){
                    stability=(stability+Math.pow((one-Double.parseDouble(evaluateStockSS.get(i).getAdjClose().get(i1))),2));
                }
                if(evaluateStockSS.get(i).getAdjClose().size()!=0){
                    stability=stability/evaluateStockSS.get(i).getAdjClose().size();
                }


                String str=stability/0.005+"";
                String string[]=str.split("\\.");
                int o=Integer.parseInt(string[0]);
                int sta=98-o*4;
                if(sta<=60){
                    sta=60;
                }


                double prof=0;
                double two0=0,two1=0,two2=0;
                for(int j=0; j<(evaluateStockSS.get(i).getiOrD().size()/3);j++){
                    two0=two0+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(j));
                }
                for(int k=0; k<(evaluateStockSS.get(i).getiOrD().size()*2/3); k++){
                    two1=two1+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(k));
                }
                for(int m=0; m<evaluateStockSS.get(i).getiOrD().size(); m++){
                    two2=two2+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(m));
                }
                prof=two0*0.1+two1*0.3+two2*0.6;
                String str1=prof+"";
                String string1[]=str1.split("\\.");
                int o1=Integer.parseInt(string1[0]);
                int pr=85+o1*2;
                if(pr>=98){
                    pr=98;
                }
                if(pr<=60){
                    pr=60;
                }

                double mobility=0;
                double three=0;
                for(int i1=0;i1<evaluateStockSS.get(i).getVolume().size();i1++){
                    three=three+Double.parseDouble(evaluateStockSS.get(i).getVolume().get(i1));
                }
               if(evaluateStockSS.get(i).getVolume().size()!=0){
                   mobility=three/evaluateStockSS.get(i).getVolume().size();
               }

                String str2=mobility/200000+"";
                String string2[]=str2.split("\\.");
                int o2=Integer.parseInt(string2[0]);
                int mo=70+o2*1;
                if(mo>=98){
                    pr=98;
                }
                if(mo<=60){
                    pr=60;
                }


                double safety = 0;;
                double fo=0;

                for (int i1=0;i1<evaluateStockSS.get(i).getiOrD().size();i1++){
                    if(Double.parseDouble(evaluateStockSS.get(i).getVolume().get(i1))>Double.parseDouble(arrayList1.get(i1).getIncreaseOrDecreaseRate().get(0))){
                        fo=fo+1;
                    }
                }
               if(evaluateStockSS.get(i).getVolume().size()!=0){
                   safety=fo/evaluateStockSS.get(i).getVolume().size();
               }
                String str3=safety+"";
                String string3[]=str3.split("\\.");
                int o3=Integer.parseInt(string3[0]);
                int sa=o3+20;
                if(sa>=98){
                    sa=98;
                }
                if(mo<=60){
                    sa=60;
                }


                double development=0;
                double fi=0;
                for (int i1=0;i1<evaluateStockSS.get(i).getiOrD().size();i1++){
                    fi=fi+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(i1));
                }
               if(evaluateStockSS.get(i).getiOrD().size()!=0){
                   development=fi/evaluateStockSS.get(i).getiOrD().size()*80;
               }
                String str4=development+"";
                String string4[]=str4.split("\\.");
                int o4=Integer.parseInt(string4[0]);
                int de=o4+60;
                if(de>=95){
                    de=95;
                }
                if(de<=60){
                    de=60;
                }

                double total=(sta+pr+mo+sa+de)/5;
                String str5=total+"";
                String string5[]=str5.split("\\.");
                int o5=Integer.parseInt(string5[0]);
                int to=o5;
                if(to>=98){
                    to=98;
                }
                if(to<=60){
                    to=60;
                }
                goal.add(new GoalVO(evaluateStockSS.get(i).getCode(),evaluateStockSS.get(i).getName(),to+"",sta+"",pr+"",mo+"",sa+"",de+""));

            }
        }
        else {


        }

        Collections.sort(goal, new Comparator<GoalVO>() {
            @Override
            public int compare(GoalVO o1, GoalVO o2) {
                GoalVO stu1=(GoalVO) o1;
                GoalVO stu2=(GoalVO)o2;
                if(Double.parseDouble(stu1.getTotal())<Double.parseDouble(stu2.getTotal())){
                    return 1;
                }else if(Double.parseDouble(stu1.getTotal())==Double.parseDouble(stu2.getTotal())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        ArrayList<GoalVO> goalVO=new ArrayList<GoalVO>();
        for(int i=0;i<20;i++){
            goalVO.add(goal.get(i));
        }
        return new TwentyStockVO(start, end, goalVO);

    }
    @Override
    public TwentyStockVO getStockGoalTwentyIn(String start, String end, String industry) throws Exception {
        Stock stock = new Stock();
        Add2DataSer add2DataSer=new Add2Data();
        ArrayList<EvaluateStockS> evaluateStockSS=new ArrayList<EvaluateStockS>();
        FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
        ArrayList<GoalVO> goal = new ArrayList<GoalVO>();

            evaluateStockSS = add2DataSer.getIndustryStockEva(industry,start,end);
            FindPlateInfoDataSer findPlateInfoDataSer=new FindPlateInfoData();
            ArrayList<TotalPlatePO> arrayList1=findPlateInfoDataSer.findOnePlateInfo(start,end,StockPool.SHANGZHENG);
            System.out.println(321);
            for (int i = 0; i < evaluateStockSS.size(); i++) {
                double stability = 0;
                double one=0;
                for(int i1=0;i1<evaluateStockSS.get(i).getAdjClose().size();i1++){
                    one=one+Double.parseDouble(evaluateStockSS.get(i).getAdjClose().get(i1));

                }

                one=one/evaluateStockSS.get(i).getAdjClose().size();
                for(int i1=0;i1<evaluateStockSS.get(i).getAdjClose().size();i1++){
                    stability=(stability+Math.pow((one-Double.parseDouble(evaluateStockSS.get(i).getAdjClose().get(i1))),2));
                }
                if(evaluateStockSS.get(i).getAdjClose().size()!=0){
                    stability=stability/evaluateStockSS.get(i).getAdjClose().size();
                }


                String str=stability/0.005+"";
                String string[]=str.split("\\.");
                int o=Integer.parseInt(string[0]);
                int sta=98-o*4;
                if(sta<=60){
                    sta=60;
                }


                double prof=0;
                double two0=0,two1=0,two2=0;
                for(int j=0; j<(evaluateStockSS.get(i).getiOrD().size()/3);j++){
                    two0=two0+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(j));
                }
                for(int k=0; k<(evaluateStockSS.get(i).getiOrD().size()*2/3); k++){
                    two1=two1+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(k));
                }
                for(int m=0; m<evaluateStockSS.get(i).getiOrD().size(); m++){
                    two2=two2+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(m));
                }
                prof=two0*0.1+two1*0.3+two2*0.6;
                String str1=prof+"";
                String string1[]=str1.split("\\.");
                int o1=Integer.parseInt(string1[0]);
                int pr=85+o1*2;
                if(pr>=98){
                    pr=98;
                }
                if(pr<=60){
                    pr=60;
                }

                double mobility=0;
                double three=0;
                for(int i1=0;i1<evaluateStockSS.get(i).getVolume().size();i1++){
                    three=three+Double.parseDouble(evaluateStockSS.get(i).getVolume().get(i1));
                }
                if(evaluateStockSS.get(i).getVolume().size()!=0){
                    mobility=three/evaluateStockSS.get(i).getVolume().size();
                }

                String str2=mobility/200000+"";
                String string2[]=str2.split("\\.");
                int o2=Integer.parseInt(string2[0]);
                int mo=70+o2*1;
                if(mo>=98){
                    pr=98;
                }
                if(mo<=60){
                    pr=60;
                }


                double safety = 0;;
                double fo=0;

                for (int i1=0;i1<evaluateStockSS.get(i).getiOrD().size();i1++){
                    if(Double.parseDouble(evaluateStockSS.get(i).getVolume().get(i1))>Double.parseDouble(arrayList1.get(i1).getIncreaseOrDecreaseRate().get(0))){
                        fo=fo+1;
                    }
                }
                if(evaluateStockSS.get(i).getVolume().size()!=0){
                    safety=fo/evaluateStockSS.get(i).getVolume().size();
                }
                String str3=safety+"";
                String string3[]=str3.split("\\.");
                int o3=Integer.parseInt(string3[0]);
                int sa=o3+20;
                if(sa>=98){
                    sa=98;
                }
                if(mo<=60){
                    sa=60;
                }


                double development=0;
                double fi=0;
                for (int i1=0;i1<evaluateStockSS.get(i).getiOrD().size();i1++){
                    fi=fi+Double.parseDouble(evaluateStockSS.get(i).getiOrD().get(i1));
                }
                if(evaluateStockSS.get(i).getiOrD().size()!=0){
                    development=fi/evaluateStockSS.get(i).getiOrD().size()*80;
                }
                String str4=development+"";
                String string4[]=str4.split("\\.");
                int o4=Integer.parseInt(string4[0]);
                int de=o4+60;
                if(de>=95){
                    de=98;
                }
                if(de<=60){
                    de=60;
                }

                double total=(sta+pr+mo+sa+de)/5;
                String str5=total+"";
                String string5[]=str5.split("\\.");
                int o5=Integer.parseInt(string5[0]);
                int to=o5;
                if(to>=98){
                    to=98;
                }
                if(to<=60){
                    to=60;
                }


                goal.add(new GoalVO(evaluateStockSS.get(i).getCode(),evaluateStockSS.get(i).getName(),to+"",sta+"",pr+"",mo+"",sa+"",de+""));

            }


        Collections.sort(goal, new Comparator<GoalVO>() {
            @Override
            public int compare(GoalVO o1, GoalVO o2) {
                GoalVO stu1=(GoalVO) o1;
                GoalVO stu2=(GoalVO)o2;
                if(Double.parseDouble(stu1.getTotal())<Double.parseDouble(stu2.getTotal())){
                    return 1;
                }else if(Double.parseDouble(stu1.getTotal())==Double.parseDouble(stu2.getTotal())){
                    return 0;
                }else{
                    return -1;
                }
            }
        });
        ArrayList<GoalVO> goalVO=new ArrayList<GoalVO>();
        for(int i=0;i<20;i++){
            if(i>goal.size()-1){
                break;
            }
            goalVO.add(goal.get(i));
        }
        return new TwentyStockVO(start, end, goalVO);

    }

    @Override
    public ArrayList<String> getMaxProfit(String start, String end, StockPool stockPool, ArrayList<String> codeOrName) throws Exception {
        Stock stock=new Stock();
        FindStockInfoDataSer findStockInfoDataSer=new FindStockInfoData();
        TwentyStockVO twentyStockVO=stock.getStockGoalTwenty(start,end,stockPool,codeOrName);
        ArrayList<StockPO> arrayList=findStockInfoDataSer.findStockInfoByCode(start,end,twentyStockVO.getGoal().get(0).getCode());
        ArrayList<String> arrayList1=new ArrayList<String>();
        arrayList1.add("0");
        for (int i=1;i<arrayList.size();i++){
            arrayList1.add((Double.parseDouble(arrayList.get(i).getAdjClose())-Double.parseDouble(arrayList.get(0).getAdjClose())/
                    Double.parseDouble(arrayList.get(0).getAdjClose())+""));
        }
        return arrayList1;
    }

    @Override
    public ArrayList<String> getMaxProfitIn(String start, String end, String industry) throws Exception {
        Stock stock=new Stock();
       FindStockInfoData findStockInfoData=new FindStockInfoData();
        TwentyStockVO twentyStockVO=stock.getStockGoalTwentyIn(start,end,industry);
        ArrayList<StockPO> arrayList=findStockInfoData.findStockInfoByCode(start,end,twentyStockVO.getGoal().get(0).getCode());
        ArrayList<String> arrayList1=null;
        arrayList1.add("0");
        for (int i=1;i<arrayList.size();i++){
            arrayList1.add((Double.parseDouble(arrayList.get(i).getAdjClose())-Double.parseDouble(arrayList.get(0).getAdjClose())/
                    Double.parseDouble(arrayList.get(0).getAdjClose())+""));
        }
        return arrayList1;
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
