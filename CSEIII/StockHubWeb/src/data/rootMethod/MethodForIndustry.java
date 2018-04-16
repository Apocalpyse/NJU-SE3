package data.rootMethod;

import PO.industryPO.IndustryPO;
import PO.stockPO.ArrStockPO;
import PO.stockPO.StockPO;
import data.MySqlConnecter.Connecter;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delong chang on 2017/6/8.
 */
public class MethodForIndustry {

    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

//    public static void main(String[] args){
//        MethodForIndustry methodForIndustry=new MethodForIndustry();
//        methodForIndustry.storageData("6/8/17");
//        methodForIndustry.storageData("6/7/17");
//        methodForIndustry.storageData("6/6/17");
//        methodForIndustry.storageData("6/5/17");
//    }

    public int iscodeOrName(String codeOrName){
        int result=0;
        String temp=codeOrName.split("")[0];
        if(Character.isDigit(temp.charAt(0))){
            result=0;
        }else {
            result=1;
        }
        return result;
    }

    public ArrStockPO getPool(ArrStockPO result,String sql){
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                result.getCode().add(rs.getNString(1));
                result.getName().add(rs.getNString(2));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public void storageData(String date){
        ArrayList<IndustryPO> result=new ArrayList<>();
        MethodForStock methodForStock=new MethodForStock();
        date=methodForStock.convertData(date);
        IndustryPO industryPO;
        ArrayList<String> cnames=new ArrayList<>();
        String sql="SELECT industry FROM codeList GROUP BY industry HAVING COUNT(*)>1 ";
        try {
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                cnames.add(rs.getNString(1));
            }
            for(int i=0;i<cnames.size();i++){ //每个行业内计算;
                ArrayList<StockPO> stockPOS=new ArrayList<>();
                StockPO stockPO=new StockPO();
                sql="SELECT code FROM stocksbasic WHERE industry='"+cnames.get(i)+"'";
                preparedStatement=con.prepareStatement(sql);
                rs=preparedStatement.executeQuery();
                while (rs.next()){
                    sql="SELECT * FROM codeList WHERE code='"+rs.getNString(1)+"'";
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet resultSet=preparedStatement.executeQuery();
                    if(resultSet.next()){
                        sql="SELECT * FROM s"+rs.getNString(1)+" WHERE date='"+date+"'";
                        preparedStatement=con.prepareStatement(sql);
                        ResultSet res=preparedStatement.executeQuery();
                        while (res.next()){
                            stockPO.setOpen(res.getNString(2));
                            stockPO.setClose(res.getNString(4));
                            stockPO.setIncreaseOrDecrease(res.getNString(8));
                            stockPO.setVolume(res.getNString(6));
                            stockPOS.add(stockPO);
                        }
                    }// 获取一个行业的所有个股信息;
                }
                double open=0;
                double close=0;
                double rate=0;
                double money=0;
                double volume=0;
                for(int j=0;j<stockPOS.size();j++){
                    if(stockPOS.get(j)!=null){
                        open+=Double.parseDouble(stockPOS.get(j).getOpen());
                        close+=Double.parseDouble(stockPOS.get(j).getClose());
                        rate+=Double.parseDouble(stockPOS.get(j).getIncreaseOrDecrease());
                        volume+=Double.parseDouble(stockPOS.get(j).getVolume());
                    }
                }
                if(stockPOS.size()!=0){
                    BigDecimal open2   =   new   BigDecimal(open/stockPOS.size());
                    open=open2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal close2   =   new   BigDecimal(close/stockPOS.size());
                    close=close2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal rate2   =   new   BigDecimal(rate/stockPOS.size());
                    rate=rate2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal volume2   =   new   BigDecimal(volume/stockPOS.size());
                    volume=volume2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    BigDecimal money2   =   new   BigDecimal(close-open);
                    money=money2.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
                    //计算出该行业的信息;
                }

                industryPO=new IndustryPO();
                industryPO.setDate(methodForStock.convertDateBack(date));
                industryPO.setAverageClose(Double.toString(close));
                industryPO.setAverageOpen(Double.toString(open));
                industryPO.setCompanyNum(Integer.toString(stockPOS.size()));
                industryPO.setIncreaseOrDecreaseMoney(Double.toString(money));
                industryPO.setIncreaseOrDecreaseRate(Double.toString(rate));
                industryPO.setTotalVolume(Double.toString(volume));
                industryPO.setIndustryName(cnames.get(i));
                result.add(industryPO);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println("start");
        for(int i=0;i<result.size();i++){
            sql="INSERT INTO industry VALUES ('"+result.get(i).getDate()+"', '"+result.get(i).getIndustryName()+"', '"+result.get(i).getCompanyNum()+"', '"+result.get(i).getAverageOpen()+"', '"+result.get(i).getAverageClose()+"', '"+result.get(i).getIncreaseOrDecreaseMoney()+"', '"+result.get(i).getIncreaseOrDecreaseRate()+"', '"+result.get(i).getTotalVolume()+"')";
            try{
                Connecter connecter=new Connecter();
                con=connecter.getConnection();
                preparedStatement=con.prepareStatement(sql);
                preparedStatement.execute();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        System.out.println("end");
    }
}
