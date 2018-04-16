package Presetation.plateUI;

import Presetation.mainUI.tableView.Stock;
import VO.InputStockByCodeVO;
import VO.StockPieVO;
import VO.StockVO;
import bl.KAndEMABl.KAndEMAController;
import bl.calculateBl.Calculate;
import bl.calculateBl.Translate;
import bl.plateBl.PlateController;
import blSer.KAndEMABlSer;
import blSer.PlateBlSer;
import blSer.StockPoolBl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
/**
 * Created by A on 2017/5/17.
 */
public class SpecificPlateController {
    public static String platename="板块名称";

    @FXML private Label PlateNameLabel;
    @FXML private LineChart LineChartForSpecificIndustry;
    @FXML private PieChart PieChartForSpecificIndustry;
    @FXML private AnchorPane AnchorPaneForSpecificIndustry;
    @FXML private TableView TableForSpecificIndustry;
    @FXML private TabPane TabPaneForSpecificIndustry;
    @FXML private Tab IncreaseTabForSpecificIndustry;
    @FXML private TableView IncreaseForSpecificIndustry;
    @FXML private Tab DecreaseTabForSpecificIndustry;
    @FXML private TableView DecreaseForSpecificIndustry;
    @FXML private Tab VolumeTabForSpecificIndustry;
    @FXML private TableView VolumeForSpecificIndustry;
    @FXML private Tab PotentialTabForSpecificIndustry;
    @FXML private TableView PotentialForSpecificIndustry;
    @FXML private DatePicker DatePickerForSpecificPlate;

    PlateBlSer plateBlSer;

    public SpecificPlateController(){
        plateBlSer=new PlateController();
    }

    /**
     *
//     * @param platename 可能有“主板”，“中小板”，“创业板”三种
     */
//    public void initialize(String platename){
//        PlateNameLabel.setText(platename);
//        PlateNameLabel.setVisible(true);
//        Calendar calendar=Calendar.getInstance();
//        Translate translate=new Translate();
//        String date=translate.myToString(calendar);
//        getWholeData(platename,date);
//    }
    public void initialize() throws RemoteException {
        PlateNameLabel.setText(platename);
        PlateNameLabel.setVisible(true);
        Calendar calendar=Calendar.getInstance();
        Translate translate=new Translate();
        String date=translate.myToString(calendar);
        getWholeData(platename,date);
    }

    //寻找某一日具体版块信息的监听
    public void searchPlate() throws RemoteException {
        if(DatePickerForSpecificPlate.getValue()==null){
            System.out.println("未选择时间");
        }else {//用户选择了时间
            String platename=PlateNameLabel.getText();
            StringBuffer sb = new StringBuffer();
            String[] temp;
            temp = DatePickerForSpecificPlate.getValue().toString().split("-");
            sb.append(temp[1]);
            sb.append("/");
            sb.append(temp[2]);
            sb.append("/");
            sb.append(temp[0]);
            String date = sb.toString();
            getWholeData(platename,date);
        }
    }

    public void getWholeData(String platename,String date) throws RemoteException {
        setLineChartForSpecificIndustry(platename,date);
        setPieChartForSpecificIndustry(platename,date);
        setTableForSpecificIndustry(platename,date);
        setIncreaseForSpecificIndustry(platename,date);
        setDecreaseForSpecificIndustry(platename,date);
        setVolumeForSpecificIndustry(platename,date);
        setPotentialForSpecificIndustry(platename,date);
    }

    public void setLineChartForSpecificIndustry(String platename,String date){
        Calculate calculate=new Calculate();
        String start=calculate.getBeforeSomeTradeDate(date,30);//获取前30天的日期
        KAndEMABlSer kAndEMABlSer=new KAndEMAController();
        ArrayList<StockVO> stockVOS=new ArrayList<>();
        InputStockByCodeVO inputStockByCodeVO=new InputStockByCodeVO();
        if(platename.equals("主板")){
            //此时需要设置成沪深300,沪深300的股票代码是000300
            LineChartForSpecificIndustry.setTitle("沪深300");
            LineChartForSpecificIndustry.setVisible(true);
            inputStockByCodeVO=new InputStockByCodeVO(start,date,"000300");
        }else if(platename.equals("中小板")){
            //中小板指的股票代码是399005
            LineChartForSpecificIndustry.setTitle("中小板指");
            LineChartForSpecificIndustry.setVisible(true);
            inputStockByCodeVO=new InputStockByCodeVO(start,date,"399005");

        }else if(platename.equals("创业板")){
            ////创业板指的股票代码是399006
            LineChartForSpecificIndustry.setTitle("创业板指");
            LineChartForSpecificIndustry.setVisible(true);
            inputStockByCodeVO=new InputStockByCodeVO(start,date,"399006");
        }else {
            System.out.println("板块选择错误");
        }

        try {
            stockVOS=kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (NullPointerException e){
            System.out.println("找不到数据");
        }catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1=new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2=new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO=stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD=stockVO.getIncreaseOrDecrease();
            iOrD=iOrD.substring(0,iOrD.length()-1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        LineChartForSpecificIndustry.getData().addAll(series1,series2);
        LineChartForSpecificIndustry.setVisible(true);
    }

    public void setPieChartForSpecificIndustry(String platename,String date) throws RemoteException {
        StockPieVO stockPieVO=new StockPieVO();
        if(platename.equals("主板")){
            stockPieVO=plateBlSer.getPlatePieVO(StockPoolBl.MAINPLATE,date);
        }else if(platename.equals("中小板")){
            stockPieVO=plateBlSer.getPlatePieVO(StockPoolBl.SMALLMIDDLEPLATE,date);
        }else if(platename.equals("创业板")){
            stockPieVO=plateBlSer.getPlatePieVO(StockPoolBl.STARTUPPLATE,date);
        }else {
            System.out.println("板块选择错误");
        }
        if((stockPieVO!=null)&&(stockPieVO.getDate()!=null)){
            ObservableList<PieChart.Data> pieData= FXCollections.observableArrayList(
                    new PieChart.Data("跌停",stockPieVO.getDecStop()),
                    new PieChart.Data("跌幅<-5%",stockPieVO.getRateLessNeg5()),
                    new PieChart.Data("-5<=跌幅<0",stockPieVO.getRateFromNeg5ToZero()),
                    new PieChart.Data("0<=涨幅<5",stockPieVO.getRateFromZeroTo5()),
                    new PieChart.Data("涨幅>5%",stockPieVO.getRateMore5()),
                    new PieChart.Data("涨停",stockPieVO.getIncStop())
            );
            PieChartForSpecificIndustry.setData(pieData);
            PieChartForSpecificIndustry.setVisible(true);
        }

    }

    public void setTableForSpecificIndustry(String platename,String date){
        ArrayList<StockVO> stockVOS=new ArrayList<>();
        if(platename.equals("主板")){
            stockVOS=plateBlSer.getRecommendStocks(StockPoolBl.MAINPLATE,date);
        }else if(platename.equals("中小板")){
            stockVOS=plateBlSer.getRecommendStocks(StockPoolBl.SMALLMIDDLEPLATE,date);
        }else if(platename.equals("创业板")){
            stockVOS=plateBlSer.getRecommendStocks(StockPoolBl.STARTUPPLATE,date);
        }else {
            System.out.println("板块选择错误");
        }
        if((stockVOS!=null)&&(stockVOS.size()>0)){
            TableColumn codeCol = new TableColumn("代码");
            TableColumn nameCol = new TableColumn("名称");
            TableColumn openCol = new TableColumn("开盘价");
            TableColumn closeCol = new TableColumn("收盘价");
            TableColumn increaseOrDecreaseCol = new TableColumn("涨跌幅");
            TableColumn volumeCol = new TableColumn("成交量");
            ObservableList<Stock> data=FXCollections.observableArrayList();
            for (int i = 0; i < stockVOS.size(); i++) {
                StockVO stockVO=stockVOS.get(i);
                data.add(
                        new Stock(stockVO.getCode(),stockVO.getName(),stockVO.getOpen(),stockVO.getClose(),stockVO.getIncreaseOrDecrease(),stockVO.getVolume())
                );
            }
            codeCol.setCellFactory(
                    new PropertyValueFactory<>("code")
            );
            nameCol.setCellFactory(
                    new PropertyValueFactory<>("name")
            );
            openCol.setCellFactory(
                    new PropertyValueFactory<>("open")
            );
            closeCol.setCellFactory(
                    new PropertyValueFactory<>("close")
            );
            increaseOrDecreaseCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecrease")
            );
            volumeCol.setCellFactory(
                    new PropertyValueFactory<>("volume")
            );
            TableForSpecificIndustry.setItems(data);
            TableForSpecificIndustry.getColumns().addAll(codeCol,nameCol,openCol,closeCol,increaseOrDecreaseCol,volumeCol);
            TableForSpecificIndustry.setVisible(true);
        }

    }

    public void setIncreaseForSpecificIndustry(String platename,String date) throws RemoteException {
        ArrayList<StockVO> stockVOS=new ArrayList<>();
        if(platename.equals("主板")){
            stockVOS=plateBlSer.getIncList(StockPoolBl.MAINPLATE,date);
        }else if(platename.equals("中小板")){
            stockVOS=plateBlSer.getIncList(StockPoolBl.SMALLMIDDLEPLATE,date);
        }else if(platename.equals("创业板")){
            stockVOS=plateBlSer.getIncList(StockPoolBl.STARTUPPLATE,date);
        }else {
            System.out.println("板块选择错误");
        }
        if((stockVOS!=null)&&(stockVOS.size()>0)){
            TableColumn codeCol = new TableColumn("代码");
            TableColumn nameCol = new TableColumn("名称");
            TableColumn openCol = new TableColumn("开盘价");
            TableColumn closeCol = new TableColumn("收盘价");
            TableColumn increaseOrDecreaseCol = new TableColumn("涨跌幅");
            TableColumn volumeCol = new TableColumn("成交量");
            ObservableList<Stock> data=FXCollections.observableArrayList();
            for (int i = 0; i < stockVOS.size(); i++) {
                StockVO stockVO=stockVOS.get(i);
                data.add(
                        new Stock(stockVO.getCode(),stockVO.getName(),stockVO.getOpen(),stockVO.getClose(),stockVO.getIncreaseOrDecrease(),stockVO.getVolume())
                );
            }
            codeCol.setCellFactory(
                    new PropertyValueFactory<>("code")
            );
            nameCol.setCellFactory(
                    new PropertyValueFactory<>("name")
            );
            openCol.setCellFactory(
                    new PropertyValueFactory<>("open")
            );
            closeCol.setCellFactory(
                    new PropertyValueFactory<>("close")
            );
            increaseOrDecreaseCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecrease")
            );
            volumeCol.setCellFactory(
                    new PropertyValueFactory<>("volume")
            );
            IncreaseForSpecificIndustry.setItems(data);
            IncreaseForSpecificIndustry.getColumns().addAll(codeCol,nameCol,openCol,closeCol,increaseOrDecreaseCol,volumeCol);
            IncreaseForSpecificIndustry.setVisible(true);
        }

    }

    public void setDecreaseForSpecificIndustry(String platename,String date){
        ArrayList<StockVO> stockVOS=new ArrayList<>();
        if(platename.equals("主板")){
            stockVOS=plateBlSer.getDecList(StockPoolBl.MAINPLATE,date);
        }else if(platename.equals("中小板")){
            stockVOS=plateBlSer.getDecList(StockPoolBl.SMALLMIDDLEPLATE,date);
        }else if(platename.equals("创业板")){
            stockVOS=plateBlSer.getDecList(StockPoolBl.STARTUPPLATE,date);
        }else {
            System.out.println("板块选择错误");
        }
        if((stockVOS!=null)&&(stockVOS.size()>0)) {
            TableColumn codeCol = new TableColumn("代码");
            TableColumn nameCol = new TableColumn("名称");
            TableColumn openCol = new TableColumn("开盘价");
            TableColumn closeCol = new TableColumn("收盘价");
            TableColumn increaseOrDecreaseCol = new TableColumn("涨跌幅");
            TableColumn volumeCol = new TableColumn("成交量");
            ObservableList<Stock> data = FXCollections.observableArrayList();
            for (int i = 0; i < stockVOS.size(); i++) {
                StockVO stockVO = stockVOS.get(i);
                data.add(
                        new Stock(stockVO.getCode(), stockVO.getName(), stockVO.getOpen(), stockVO.getClose(), stockVO.getIncreaseOrDecrease(), stockVO.getVolume())
                );
            }
            codeCol.setCellFactory(
                    new PropertyValueFactory<>("code")
            );
            nameCol.setCellFactory(
                    new PropertyValueFactory<>("name")
            );
            openCol.setCellFactory(
                    new PropertyValueFactory<>("open")
            );
            closeCol.setCellFactory(
                    new PropertyValueFactory<>("close")
            );
            increaseOrDecreaseCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecrease")
            );
            volumeCol.setCellFactory(
                    new PropertyValueFactory<>("volume")
            );
            DecreaseForSpecificIndustry.setItems(data);
            DecreaseForSpecificIndustry.getColumns().addAll(codeCol, nameCol, openCol, closeCol, increaseOrDecreaseCol, volumeCol);
            DecreaseForSpecificIndustry.setVisible(true);
        }
    }

    public void setVolumeForSpecificIndustry(String platename,String date) throws RemoteException {
        ArrayList<StockVO> stockVOS=new ArrayList<>();
        if(platename.equals("主板")){
            stockVOS=plateBlSer.getVolumeList(StockPoolBl.MAINPLATE,date);
        }else if(platename.equals("中小板")){
            stockVOS=plateBlSer.getVolumeList(StockPoolBl.SMALLMIDDLEPLATE,date);
        }else if(platename.equals("创业板")){
            stockVOS=plateBlSer.getVolumeList(StockPoolBl.STARTUPPLATE,date);
        }else {
            System.out.println("板块选择错误");
        }
        if((stockVOS!=null)&&(stockVOS.size()>0)) {
            TableColumn codeCol = new TableColumn("代码");
            TableColumn nameCol = new TableColumn("名称");
            TableColumn openCol = new TableColumn("开盘价");
            TableColumn closeCol = new TableColumn("收盘价");
            TableColumn increaseOrDecreaseCol = new TableColumn("涨跌幅");
            TableColumn volumeCol = new TableColumn("成交量");
            ObservableList<Stock> data = FXCollections.observableArrayList();
            for (int i = 0; i < stockVOS.size(); i++) {
                StockVO stockVO = stockVOS.get(i);
                data.add(
                        new Stock(stockVO.getCode(), stockVO.getName(), stockVO.getOpen(), stockVO.getClose(), stockVO.getIncreaseOrDecrease(), stockVO.getVolume())
                );
            }
            codeCol.setCellFactory(
                    new PropertyValueFactory<>("code")
            );
            nameCol.setCellFactory(
                    new PropertyValueFactory<>("name")
            );
            openCol.setCellFactory(
                    new PropertyValueFactory<>("open")
            );
            closeCol.setCellFactory(
                    new PropertyValueFactory<>("close")
            );
            increaseOrDecreaseCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecrease")
            );
            volumeCol.setCellFactory(
                    new PropertyValueFactory<>("volume")
            );
            VolumeForSpecificIndustry.setItems(data);
            VolumeForSpecificIndustry.getColumns().addAll(codeCol, nameCol, openCol, closeCol, increaseOrDecreaseCol, volumeCol);
            VolumeForSpecificIndustry.setVisible(true);
        }
    }

    public void setPotentialForSpecificIndustry(String platename,String date) throws RemoteException {
        ArrayList<StockVO> stockVOS=new ArrayList<>();
        if(platename.equals("主板")){
            stockVOS=plateBlSer.getPotentialList(StockPoolBl.MAINPLATE,date);
        }else if(platename.equals("中小板")){
            stockVOS=plateBlSer.getPotentialList(StockPoolBl.SMALLMIDDLEPLATE,date);
        }else if(platename.equals("创业板")){
            stockVOS=plateBlSer.getPotentialList(StockPoolBl.STARTUPPLATE,date);
        }else {
            System.out.println("板块选择错误");
        }
        if((stockVOS!=null)&&(stockVOS.size()>0)) {
            TableColumn codeCol = new TableColumn("代码");
            TableColumn nameCol = new TableColumn("名称");
            TableColumn openCol = new TableColumn("开盘价");
            TableColumn closeCol = new TableColumn("收盘价");
            TableColumn increaseOrDecreaseCol = new TableColumn("涨跌幅");
            TableColumn volumeCol = new TableColumn("成交量");
            ObservableList<Stock> data = FXCollections.observableArrayList();
            for (int i = 0; i < stockVOS.size(); i++) {
                StockVO stockVO = stockVOS.get(i);
                data.add(
                        new Stock(stockVO.getCode(), stockVO.getName(), stockVO.getOpen(), stockVO.getClose(), stockVO.getIncreaseOrDecrease(), stockVO.getVolume())
                );
            }
            codeCol.setCellFactory(
                    new PropertyValueFactory<>("code")
            );
            nameCol.setCellFactory(
                    new PropertyValueFactory<>("name")
            );
            openCol.setCellFactory(
                    new PropertyValueFactory<>("open")
            );
            closeCol.setCellFactory(
                    new PropertyValueFactory<>("close")
            );
            increaseOrDecreaseCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecrease")
            );
            volumeCol.setCellFactory(
                    new PropertyValueFactory<>("volume")
            );
            PotentialForSpecificIndustry.setItems(data);
            PotentialForSpecificIndustry.getColumns().addAll(codeCol, nameCol, openCol, closeCol, increaseOrDecreaseCol, volumeCol);
            PotentialForSpecificIndustry.setVisible(true);
        }
    }
}
