package data.Add2Data;

import PO.marketPO.NewsPO;
import PO.stockPO.EvaluateStockS;
import PO.stockPO.StockPO;
import PO.stockPO.StockPool;
import VO.marketVO.NewsVO;
import blSer.StockPoolBl;
import data.MySqlConnecter.Connecter;
import data.rootMethod.MethodForAddSer;
import data.rootMethod.MethodForNews;
import data.rootMethod.MethodForStock;
import data.stockData.FindStockInfoData;
import dataSer.Add2DataSer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by delong chang on 2017/6/8.
 */
public class Add2Data implements Add2DataSer{
    private String sql;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    private Connection con ;

    public static void main(String[] args){
        Add2Data a=new Add2Data();
        ArrayList<NewsPO> b=new ArrayList<>();
        try {
            b=a.getNewsCl("证券");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<NewsPO> getNewsCl(String classify) throws Exception {
        ArrayList<NewsPO> result=new ArrayList<>();
        MethodForNews methodForNews=new MethodForNews();
        sql="SELECT * FROM news WHERE classify='"+classify+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            result=methodForNews.dealWithResult(rs);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<EvaluateStockS> getIndustryStockEva(String industryName, String start, String end) throws Exception {
        ArrayList<EvaluateStockS> result=new ArrayList<>();
        ArrayList<String> codes=new ArrayList<>();
        ArrayList<StockPO> stockPOS=new ArrayList<>();
        EvaluateStockS evaluateStockS;
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        MethodForStock methodForStock=new MethodForStock();
        MethodForAddSer methodForAddSer=new MethodForAddSer();
        start=methodForStock.convertData(start);
        end=methodForStock.convertData(end);
        sql="SELECT code FROM codelist WHERE industry='"+industryName+"'";
        try{
            Connecter connecter=new Connecter();
            con=connecter.getConnection();
            preparedStatement=con.prepareStatement(sql);
            ResultSet rs=preparedStatement.executeQuery();
            while (rs.next()){
                codes.add(rs.getNString(1));
                evaluateStockS=methodForAddSer.getEvaluateStock(codes.get(codes.size()-1),start,end);
                if(evaluateStockS.getCode()!=null){
                    result.add(evaluateStockS);
                 }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public ArrayList<EvaluateStockS> getPlateStockEva(StockPool stockPool, String start, String end) throws Exception {
        ArrayList<EvaluateStockS> result=new ArrayList<>();
        ArrayList<StockPO> stockPOS=new ArrayList<>();
        FindStockInfoData findStockInfoData=new FindStockInfoData();
        MethodForStock methodForStock=new MethodForStock();
        MethodForAddSer methodForAddSer=new MethodForAddSer();
        EvaluateStockS evaluateStockS;
        start=methodForStock.convertData(start);
        end=methodForStock.convertData(end);
        switch (stockPool){
            case ALL:
               stockPOS=findStockInfoData.findStockInfoOneday(methodForStock.convertDateBack(start));
               for(int i=0;i<stockPOS.size();i++){
                   evaluateStockS=new EvaluateStockS();
                   ArrayList<String> adj=new ArrayList<>();
                   ArrayList<String> iord=new ArrayList<>();
                   ArrayList<String> volume=new ArrayList<>();
                   evaluateStockS.setName(stockPOS.get(i).getName());
                   evaluateStockS.setCode(stockPOS.get(i).getCode());
                   adj.add(stockPOS.get(i).getAdjClose());
                   iord.add(stockPOS.get(i).getIncreaseOrDecrease());
                   volume.add(stockPOS.get(i).getVolume());
                   evaluateStockS.setAdjClose(adj);
                   evaluateStockS.setiOrD(iord);
                   evaluateStockS.setVolume(volume);
                   result.add(evaluateStockS);
               }
                break;
            case SHANGZHENG:
                sql="SELECT code FROM codelist";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    ArrayList<String> codes=new ArrayList<>();
                    while (rs.next()){
                        if(rs.getNString(1).split("")[0].equals("6")){
                            codes.add(rs.getNString(1));
                            evaluateStockS=methodForAddSer.getEvaluateStock(codes.get(codes.size()-1),start,end);
                            if(evaluateStockS.getCode()!=null){
                                result.add(evaluateStockS);
                            }
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SHENZHENG:
                sql="SELECT code FROM codelist";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    ArrayList<String> codes=new ArrayList<>();
                    while (rs.next()){
                        if(rs.getNString(1).split("")[0].equals("0")||rs.getNString(1).split("")[0].equals("2")||rs.getNString(1).split("")[0].equals("3")){
                            codes.add(rs.getNString(1));
                            evaluateStockS=methodForAddSer.getEvaluateStock(codes.get(codes.size()-1),start,end);
                            if(evaluateStockS.getCode()!=null){
                                result.add(evaluateStockS);
                            }
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case HUSHEN300:
                sql="SELECT code FROM hs300";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    ArrayList<String> codes=new ArrayList<>();
                    while (rs.next()){
                        codes.add(rs.getNString(1));
                        evaluateStockS=methodForAddSer.getEvaluateStock(codes.get(codes.size()-1),start,end);
                        if(evaluateStockS.getCode()!=null){
                            result.add(evaluateStockS);
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case SMALLMIDDLEPLATE:
                sql="SELECT code FROM codelist";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    ArrayList<String> codes=new ArrayList<>();
                    while (rs.next()){
                        if(rs.getNString(1).split("")[2].equals("2")){
                            codes.add(rs.getNString(1));
                            evaluateStockS=methodForAddSer.getEvaluateStock(codes.get(codes.size()-1),start,end);
                            if(evaluateStockS.getCode()!=null){
                                result.add(evaluateStockS);
                            }
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
            case STARTUPPLATE:
                sql="SELECT code FROM codelist";
                try{
                    Connecter connecter=new Connecter();
                    con=connecter.getConnection();
                    preparedStatement=con.prepareStatement(sql);
                    ResultSet rs=preparedStatement.executeQuery();
                    ArrayList<String> codes=new ArrayList<>();
                    while (rs.next()){
                        if(rs.getNString(1).split("")[0].equals("3")){
                            codes.add(rs.getNString(1));
                            evaluateStockS=methodForAddSer.getEvaluateStock(codes.get(codes.size()-1),start,end);
                            if(evaluateStockS.getCode()!=null){
                                result.add(evaluateStockS);
                            }
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
                break;
        }
        return result;
    }
}
