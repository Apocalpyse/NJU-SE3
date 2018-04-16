package Presetation.mainUI;
/**
 * Created by chr on 2017/3/4.
 */


import Presetation.industryUI.SpecificIndustry;
import Presetation.industryUI.SpecificIndustryController;
import Presetation.mainUI.tableView.Stock;
import Presetation.mainUI.tableView.TotalIndustry;
import Presetation.mainUI.tableView.TotalIndustrySimple;
import Presetation.mainUI.tableView.TotalPlateInfo;
import Presetation.messageUI.MessageFrame;
import Presetation.plateUI.SpecificPlate;
import Presetation.plateUI.SpecificPlateController;
import Presetation.userUI.UserFrame;
import VO.*;
import bl.KAndEMABl.KAndEMAController;
import bl.calculateBl.Calculate;
import bl.calculateBl.Translate;
import bl.industryBl.IndustryController;
import bl.plateBl.PlateController;
import bl.strategyBl.StrategyController;
import bl.userBl.UserController;
import blSer.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rmi.ClientRunner;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by thinkpad on 2017/3/4.
 */
public class MainController {

    public MainController() {
    }

    /*--------------------------------------------------------主页----------MainController------------------------------------------------------------------------------ */
    @FXML
    private TextField SearchText;
    @FXML
    private TabPane MainTabPane;
    @FXML
    private Button BForSingleStockTab;
    @FXML
    private Button BForQuantourCompareTab;
    @FXML
    private Button BForPlateTab;
    @FXML
    private Button BForMarketTab;
    @FXML
    private Button BForQuantitativeInvestmentTab;
    @FXML
    private Button BForSelfSelectStockTab;
    @FXML
    private Button BForIndustryTab;
    @FXML
    private Button User;

    public static UserVO userVO = null;


    public void IndustryButtonClicked() {
        MainTabPane.getSelectionModel().select(IndustryTab);

        BForIndustryTab.setStyle("-fx-background-color: #1A1A1A");
        BForSingleStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void SingleStockButtonClicked() {
        MainTabPane.getSelectionModel().select(SingleStockTab);

        BForSingleStockTab.setStyle("-fx-background-color: #1A1A1A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void QuantourCompareButtonClicked() {
        MainTabPane.getSelectionModel().select(QuantourCompareTab);
        BForQuantourCompareTab.setStyle("-fx-background-color: #1A1A1A");
        BForSingleStockTab.setStyle("-fx-background-color: #2A2A2A ");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void DetailsButtonClicked() {
        MainTabPane.getSelectionModel().select(DetailsTab);
        BForPlateTab.setStyle("-fx-background-color: #1A1A1A");
        BForSingleStockTab.setStyle("-fx-background-color:  #2A2A2A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void MarketButtonClicked() {
        MainTabPane.getSelectionModel().select(MarketTab);
        BForMarketTab.setStyle("-fx-background-color: #1A1A1A");
        BForSingleStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void QuantitativeInvestmentButtonClicked() {
        MainTabPane.getSelectionModel().select(QuantitativeInvestmentTab);
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #1A1A1A");
        BForSingleStockTab.setStyle("-fx-background-color:  #2A2A2A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void SelfSelectButtonClicked() {
        try {
            userVO.getAccount();
        } catch (NullPointerException e) {
            try {
                MessageFrame messageFrame = new MessageFrame();
                messageFrame.start(new Stage());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        setStockPoolForSelfSelect();
        MainTabPane.getSelectionModel().select(SelfSelectStockTab);
        BForSelfSelectStockTab.setStyle("-fx-background-color: #1A1A1A");
        BForSingleStockTab.setStyle("-fx-background-color:  #2A2A2A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");
    }

    public void SearchFieldPressed() {

    }


    public void UserClicked() {
        UserFrame userFrame = new UserFrame(userVO);
        try {
            userFrame.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public UserVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UserVO userVO) {
        MainController.userVO = userVO;
    }

    public void initialize() {
        ClientRunner runner = new ClientRunner();

        ErrorTextForC.setVisible(false);
//        ErrorTextForK.setVisible(false);
//        ErrorTextForM.setVisible(false);
        ErrorTextForQ.setVisible(false);
//        ErrorTextForS.setVisible(false);
//        ErrorTextForP.setVisible(false);

        StockNameForKChart.setVisible(false);
        StockNameForEMA5.setVisible(false);
        StockNameForEMA10.setVisible(false);
        StockNameForEMA30.setVisible(false);
        StockNameForEMA60.setVisible(false);
        StockNameForEMA120.setVisible(false);
        StockCodeForKChart.setVisible(false);
        StockCodeForEMA5.setVisible(false);
        StockCodeForEMA10.setVisible(false);
        StockCodeForEMA30.setVisible(false);
        StockCodeForEMA60.setVisible(false);
        StockCodeForEMA120.setVisible(false);
        StockNameForPieChart.setVisible(false);
        StockCodeForPieChart.setVisible(false);
        BackToAllPlate.setVisible(false);
        EMAChartForK5.setVisible(false);
        EMAChartForK10.setVisible(false);
        EMAChartForK30.setVisible(false);
        EMAChartForK60.setVisible(false);
        EMAChartForK120.setVisible(false);
        EMAChartForC.setVisible(false);
        LogarithmicYield.setVisible(false);
        CompareTableForC.setVisible(false);
        IncreaseRank.setVisible(false);
        DecreaseRank.setVisible(false);
        VolumeRank.setVisible(false);
        TableViewForRelativeIndexReturn.setVisible(false);
        TableViewForStrategy.setVisible(false);
        StockPoolForStrategy.setVisible(false);
        StockPoolForSelfSelect.setVisible(false);
        StockPoolForER.setVisible(false);
        StockPoolForIR.setVisible(false);
        LineChartForStrategy.setVisible(false);
        AreaChartForExcessReturn.setVisible(false);
        AreaChartForWinRate.setVisible(false);
        BarChartForRelativeIndexReturn.setVisible(false);
        EvaluationTable.setVisible(false);

        PlateChoiceForQ.setItems(FXCollections.observableArrayList("全部股票", "自选股", "主板", "中小板", "创业板"));
//        MarketChoice.setItems(FXCollections.observableArrayList("上证A股"));
//        MarketChoiceForP.setItems(FXCollections.observableArrayList("上证A股"));
        StrategyChoiceBoxForQ.setItems(FXCollections.observableArrayList("动量策略", "均值回归", "MA择时策略"));
        FormationChoiceBoxForQ.setItems(FXCollections.observableArrayList("5天", "10天", "20天"));

        setStockPoolForSelfSelect();
        StrategyChoiceBoxForQ.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
                    FormationFieldForQ.setVisible(true);
                    FormationChoiceBoxForQ.setVisible(false);
                }
                if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
                    FormationChoiceBoxForQ.setVisible(true);
                    FormationFieldForQ.setVisible(false);
                }
                if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
                    FormationFieldForQ.setVisible(true);
                    FormationChoiceBoxForQ.setVisible(false);
                }
            }
        });

//        Calendar calendar=Calendar.getInstance();
//        Translate translate=new Translate();
//        String date=translate.myToString(calendar);

        String date="4/1/14";

        //市场
        QueryForM(date);

        //板块
        initializeWholePlate(date);

        //行业
        initializeWholeIndustry(date);
    }

/*--------------------------------------------------------主页----------MainController------------------------------------------------------------------------------ */

    /*------------------------------------------------------个股-------------StockController----------------------------------------------------------------------------------------- */
//    @FXML private Label ErrorTextForK;
    @FXML
    private Tab SingleStockTab;
    @FXML
    private AnchorPane APForDiscussBoard;//个股讨论区
    @FXML
    private javafx.scene.control.ScrollPane CompanyScrollForStock;//公司简介
    @FXML
    private TabPane TabPaneForK;
    @FXML
    private Button QueryButtonForK;
    @FXML
    private DatePicker StartTimeForK;
    @FXML
    private DatePicker EndTimeForK;
    @FXML
    private TextField StockNameForK;
    @FXML
    private PieChart PieChartForK;
    @FXML
    private Label StockNameForPieChart;
    @FXML
    private Label StockCodeForPieChart;
    @FXML
    private AnchorPane KChartPane;
    @FXML
    private LineChart EMAChartForK5;
    @FXML
    private LineChart EMAChartForK10;
    @FXML
    private LineChart EMAChartForK30;
    @FXML
    private LineChart EMAChartForK60;
    @FXML
    private LineChart EMAChartForK120;
    @FXML
    private Label StockNameForKChart;
    @FXML
    private Label StockCodeForKChart;
    @FXML
    private Label StockNameForEMA5;
    @FXML
    private Label StockCodeForEMA5;
    @FXML
    private Label StockNameForEMA10;
    @FXML
    private Label StockCodeForEMA10;
    @FXML
    private Label StockNameForEMA30;
    @FXML
    private Label StockCodeForEMA30;
    @FXML
    private Label StockNameForEMA60;
    @FXML
    private Label StockCodeForEMA60;
    @FXML
    private Label StockNameForEMA120;
    @FXML
    private Label StockCodeForEMA120;
    @FXML
    private Button CollectionButton;

    @FXML private Label StockNameForPieChart1;//个股主页股票代码
    @FXML protected Label StockNameForPieChart2;//个股主页股票名称

    private KAndEMABlSer kser;
    private UserBlSer userBlSer;

    public void QueryForK() {
        this.kser = new bl.KAndEMABl.KAndEMAController();
//        ErrorTextForK.setVisible(false);
        try {
            StockNameForK.getText();
//            StartTimeForK.getValue().toString();
//            EndTimeForK.getValue().toString();
        } catch (NullPointerException e) {
//            ErrorTextForK.setVisible(true);
//            ErrorTextForK.setText("请输入完整数据");
        }

        //解决开始日期或结束日期可能是空的问题
        if ((StartTimeForK.getValue() == null) && (EndTimeForK.getValue() == null)) {
            System.out.println("in line 323");
            Calendar nowCa = Calendar.getInstance();
            Translate translate = new Translate();
//            String nowStr = translate.myToString(nowCa);
            String nowStr="4/1/14";
            Calculate calculate = new Calculate();
            String beforeStr = calculate.getBeforeSomeTradeDate(nowStr, 60);
            String now[] = nowStr.split("/");
            String before[] = beforeStr.split("/");
            LocalDate nowDate = LocalDate.of(Integer.parseInt(now[2]) + 2000, Integer.parseInt(now[0]), Integer.parseInt(now[1]));
            EndTimeForK.setValue(nowDate);
            LocalDate beforeDate = LocalDate.of(Integer.parseInt(before[2]) + 2000, Integer.parseInt(before[0]), Integer.parseInt(before[1]));
            StartTimeForK.setValue(beforeDate);
        } else if (StartTimeForK.getValue() == null) {
            StringBuffer sb = new StringBuffer();
            String[] temp;
            temp = EndTimeForK.getValue().toString().split("-");
            sb.append(temp[1]);
            sb.append("/");
            sb.append(temp[2]);
            sb.append("/");
            sb.append(temp[0]);
            String end = sb.toString();
            Calculate calculate = new Calculate();
            String startStr = calculate.getBeforeSomeTradeDate(end, 60);
            String before[] = startStr.split("/");
            LocalDate beforeDate = LocalDate.of(Integer.parseInt(before[2]) + 2000, Integer.parseInt(before[0]), Integer.parseInt(before[1]));
            StartTimeForK.setValue(beforeDate);
        } else if (EndTimeForK.getValue() == null) {
            LocalDate localDate = LocalDate.now();
            EndTimeForK.setValue(localDate);
        }

        if (!StartTimeForK.getValue().isBefore(EndTimeForK.getValue())) {
            System.out.println("开始时间应该在结束时间之前");
        } else {
            if (Character.isDigit(StockNameForK.getText().toCharArray()[0])) {  //判断用户输入的是代码还是名称
                InputStockByCodeVO vo = new InputStockByCodeVO();  //如果用户输入的是代码，则获取InputStockByCodeVO
                ArrayList<StockVO> resultVO;
                String start;
                String end;
                StringBuffer sb = new StringBuffer();
                String[] temp;
                temp = StartTimeForK.getValue().toString().split("-");
                sb.append(temp[1]);
                sb.append("/");
                sb.append(temp[2]);
                sb.append("/");
                sb.append(temp[0]);
                start = sb.toString();
                sb = new StringBuffer();
                temp = EndTimeForK.getValue().toString().split("-");
                sb.append(temp[1]);
                sb.append("/");
                sb.append(temp[2]);
                sb.append("/");
                sb.append(temp[0]);
                end = sb.toString();
                vo.setStart(start);
                vo.setEnd(end);
                vo.setCode(StockNameForK.getText());

                try {
                    resultVO = this.kser.getDataByCode(vo);
                    KChartPane.getChildren().remove(0, KChartPane.getChildren().size());
                    setKChartByCode(resultVO);
                    setPieChartForKByCode(vo);
                    setEMAChart5ByCode(vo);
                    setEMAChart10ByCode(vo);
                    setEMAChart20ByCode(vo);
                    setEMAChart30ByCode(vo);
                    setEMAChart60ByCode(vo);
                } catch (NullPointerException e) {
//                        ErrorTextForK.setText("找不到数据");
//                        ErrorTextForK.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                InputStockByNameVO vo = new InputStockByNameVO();  //如果用户输入的是名称，则获取InputStockByNameVO
                ArrayList<StockVO> resultVO;
                String start;
                String end;
                try {
                    StringBuffer sb = new StringBuffer();
                    String[] temp;
                    temp = StartTimeForK.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    start = sb.toString();
                    sb = new StringBuffer();
                    temp = EndTimeForK.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    end = sb.toString();
                    vo.setStart(start);
                    vo.setEnd(end);
                    vo.setName(StockNameForK.getText());
                } catch (NullPointerException e) {
//                        ErrorTextForK.setVisible(true);
//                        ErrorTextForK.setText("请输入完整信息");
                }
                setPieChartForKByName(vo);
                setEMAChart5ByName(vo);
                setEMAChart10ByName(vo);
                setEMAChart20ByName(vo);
                setEMAChart30ByName(vo);
                setEMAChart60ByName(vo);
                try {
                    resultVO = this.kser.getDataByName(vo);
                    setKChartByName(resultVO);
                } catch (NullPointerException e) {
//                        ErrorTextForK.setVisible(true);
//                        ErrorTextForK.setText("找不到数据");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void setPieChartForKByCode(InputStockByCodeVO vo) {
        PieVO resultVO;
        try {
            resultVO = this.kser.getPieDataByCode(vo);
            StockCodeForPieChart.setText(resultVO.getCode());
            StockCodeForPieChart.setVisible(true);
            StockNameForPieChart.setText(resultVO.getName());
            StockNameForPieChart.setVisible(true);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(resultVO.getIncreaseLessDays())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(resultVO.getIncreaseMoreDays())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(resultVO.getDecreaseLessDays())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(resultVO.getDecreaseMoredays()))
            );
            int countOfRemove = 0;
            if (Integer.parseInt(resultVO.getIncreaseLessDays()) == 0) {
                pieChartData.remove(0);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getIncreaseMoreDays()) == 0) {
                pieChartData.remove(1 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseLessDays()) == 0) {
                pieChartData.remove(2 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseMoredays()) == 0) {
                pieChartData.remove(3 - countOfRemove);
                countOfRemove++;
            }
            PieChartForK.setData(pieChartData);
            PieChartForK.setVisible(true);
            PieChartForK.setLegendSide(Side.RIGHT);
        } catch (NullPointerException e) {
//            ErrorTextForK.setText("找不到搜索结果");
//            ErrorTextForK.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setPieChartForKByName(InputStockByNameVO vo) {
        PieVO resultVO;
        try {
            resultVO = this.kser.getPieDataByName(vo);
            StockNameForPieChart.setText(resultVO.getName());
            StockNameForPieChart.setVisible(true);
            StockCodeForPieChart.setText(resultVO.getCode());
            StockCodeForPieChart.setVisible(true);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(resultVO.getIncreaseLessDays())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(resultVO.getIncreaseMoreDays())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(resultVO.getDecreaseLessDays())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(resultVO.getDecreaseMoredays()))
            );
            if (Integer.parseInt(resultVO.getIncreaseLessDays()) == 0) {
                pieChartData.remove(0);
            }
            if (Integer.parseInt(resultVO.getIncreaseMoreDays()) == 0) {
                pieChartData.remove(1);
            }
            if (Integer.parseInt(resultVO.getDecreaseLessDays()) == 0) {
                pieChartData.remove(2);
            }
            if (Integer.parseInt(resultVO.getDecreaseMoredays()) == 0) {
                pieChartData.remove(3);
            }
            PieChartForK.setData(pieChartData);
            PieChartForK.setVisible(true);
        } catch (NullPointerException e) {
//            ErrorTextForK.setText("找不到搜索结果");
//            ErrorTextForK.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setKChartByCode(ArrayList<StockVO> vo) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setForceZeroInRange(false);
        KChart bc = new KChart(xAxis, yAxis);
        XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
        for (int i = 0; i < vo.size(); i++) {
            series.getData().add(
                    new XYChart.Data<String, Double>(vo.get(i).getDate(), Double.parseDouble(vo.get(i).getOpen()), new KChart.CandleStickExtraValues<Double>(Double.parseDouble(vo.get(i).getClose()), Double.parseDouble(vo.get(i).getHigh()), Double.parseDouble(vo.get(i).getLow()))));
        }
        XYChart.Data<Number, Number> point = new XYChart.Data<>(0, 1);
        bc.addHorizontalValueMarker(point);
        bc.setData(FXCollections.observableArrayList(series));
        bc.setLayoutX(196);
        bc.setLayoutY(10);
        this.KChartPane.getChildren().add(bc);
    }

    private void setKChartByName(ArrayList<StockVO> vo) {
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();
        yAxis.setForceZeroInRange(false);
        KChart bc = new KChart(xAxis, yAxis);
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        for (int i = 0; i < vo.size(); i++) {
            series.getData().add(
                    new XYChart.Data<String, Double>(vo.get(i).getDate(), Double.parseDouble(vo.get(i).getOpen()), new KChart.CandleStickExtraValues<Double>(Double.parseDouble(vo.get(i).getClose()), Double.parseDouble(vo.get(i).getHigh()), Double.parseDouble(vo.get(i).getLow()))));
        }
        bc.getData().addAll(series);
        bc.setPrefSize(877, 320);
        this.KChartPane.getChildren().add(bc);
    }

    private void setEMAChart5ByCode(InputStockByCodeVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByCode(vo, "5");
            StockNameForEMA5.setVisible(true);
            StockNameForEMA5.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(Double.parseDouble(emavo.getMinYield()), Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 3);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK5.setCreateSymbols(true);
            } else {
                EMAChartForK5.setCreateSymbols(false);
            }
            ((NumberAxis) EMAChartForK5.getYAxis()).setForceZeroInRange(false);
            series.setName(emavo.getName());
            EMAChartForK5.setData(FXCollections.observableArrayList(series));
            EMAChartForK5.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart10ByCode(InputStockByCodeVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByCode(vo, "10");
            StockNameForEMA10.setVisible(true);
            StockNameForEMA10.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK10.setCreateSymbols(true);
            } else {
                EMAChartForK10.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK10.getYAxis()).setForceZeroInRange(false);
            EMAChartForK10.setData(FXCollections.observableArrayList(series));
            EMAChartForK10.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart20ByCode(InputStockByCodeVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByCode(vo, "20");
            StockNameForEMA30.setVisible(true);
            StockNameForEMA30.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK30.setCreateSymbols(true);
            } else {
                EMAChartForK30.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK30.getYAxis()).setForceZeroInRange(false);
            EMAChartForK30.setData(FXCollections.observableArrayList(series));
            EMAChartForK30.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart30ByCode(InputStockByCodeVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByCode(vo, "30");
            StockNameForEMA60.setVisible(true);
            StockNameForEMA60.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK60.setCreateSymbols(true);
            } else {
                EMAChartForK60.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK60.getYAxis()).setForceZeroInRange(false);
            EMAChartForK60.setData(FXCollections.observableArrayList(series));
            EMAChartForK60.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart60ByCode(InputStockByCodeVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByCode(vo, "60");
            StockNameForEMA120.setVisible(true);
            StockNameForEMA120.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK120.setCreateSymbols(true);
            } else {
                EMAChartForK120.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK120.getYAxis()).setForceZeroInRange(false);
            EMAChartForK120.setData(FXCollections.observableArrayList(series));
            EMAChartForK120.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart5ByName(InputStockByNameVO vo) {
        System.out.println("in line 691");
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByName(vo, "5");
            StockNameForEMA5.setVisible(true);
            StockNameForEMA5.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK5.setCreateSymbols(true);
            } else {
                EMAChartForK5.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK5.getYAxis()).setForceZeroInRange(false);
            EMAChartForK5.setData(FXCollections.observableArrayList(series));
            EMAChartForK5.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart10ByName(InputStockByNameVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByName(vo, "10");
            StockNameForEMA10.setVisible(true);
            StockNameForEMA10.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK10.setCreateSymbols(true);
            } else {
                EMAChartForK10.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK10.getYAxis()).setForceZeroInRange(false);
            EMAChartForK10.setData(FXCollections.observableArrayList(series));
            EMAChartForK10.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart20ByName(InputStockByNameVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByName(vo, "20");
            StockNameForEMA30.setVisible(true);
            StockNameForEMA30.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK30.setCreateSymbols(true);
            } else {
                EMAChartForK30.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK30.getYAxis()).setForceZeroInRange(false);
            EMAChartForK30.setData(FXCollections.observableArrayList(series));
            EMAChartForK30.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart30ByName(InputStockByNameVO vo) {
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByName(vo, "30");
            StockNameForEMA60.setVisible(true);
            StockNameForEMA60.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK60.setCreateSymbols(true);
            } else {
                EMAChartForK60.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK60.getYAxis()).setForceZeroInRange(false);
            EMAChartForK60.setData(FXCollections.observableArrayList(series));
            EMAChartForK60.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChart60ByName(InputStockByNameVO vo) {
        System.out.println("in line 792");
        EMAVO emavo = new EMAVO();
        try {
            emavo = this.kser.getEMAByName(vo, "60");
            StockNameForEMA120.setVisible(true);
            StockNameForEMA120.setText(emavo.getName());
            NumberAxis yAxis = new NumberAxis(0, Double.parseDouble(emavo.getMaxYield()), Double.parseDouble(emavo.getMaxYield()) / 5);
            XYChart.Series series = new XYChart.Series();
            for (int i = 0; i < emavo.getDate().size(); i++) {
                series.getData().add(new XYChart.Data(emavo.getDate().get(i), Double.parseDouble(emavo.getYield().get(i))));
            }
            if (emavo.getDate().size() < 30) {
                EMAChartForK120.setCreateSymbols(true);
            } else {
                EMAChartForK120.setCreateSymbols(false);
            }
            series.setName(emavo.getName());
            ((NumberAxis) EMAChartForK120.getYAxis()).setForceZeroInRange(false);
            EMAChartForK120.setData(FXCollections.observableArrayList(series));
            EMAChartForK120.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void CollectionButtonOnClicked() {
        this.userBlSer = new UserController();
        try {
            userVO.getAccount();
        } catch (NullPointerException e) {
//          ErrorTextForK.setText("请先登录");
//          ErrorTextForK.setVisible(true);
        }
        try {
            StockNameForK.getText();
        } catch (NullPointerException e) {
//          ErrorTextForK.setText("请输入股票代码或名称");
//          ErrorTextForK.setVisible(true);
        }
        try {
            userBlSer.addOneSelfSelectStock(userVO.getAccount(), StockNameForK.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*----------------------------------------------------------个股---------StockController----------------------------------------------------------------------------------------- */


    /*---------------------------------------------------------股票比较----------QuantourCompareController----------------------------------------------------------------------------------------- */
    @FXML
    private Tab QuantourCompareTab;
    @FXML
    private Label ErrorTextForC;
    @FXML
    private TextField StockNameAForC;
    @FXML
    private TextField StockNameBForC;
    @FXML
    private DatePicker StartTimeForC;
    @FXML
    private DatePicker EndTimeForC;
    @FXML
    private Button QueryButtonForC;
    @FXML
    private LineChart EMAChartForC;
    @FXML
    private PieChart PieChartAForC;
    @FXML
    private PieChart PieChartBForC;
    @FXML
    private LineChart LogarithmicYield;
    @FXML
    private TableView CompareTableForC;

    private QuantourCompareBlSer qser;

    public void QueryForC() {
        ErrorTextForC.setVisible(false);
        this.kser = new KAndEMAController();
        this.qser = new bl.quantourCompareBl.QuantourCompareController();
        if (!StartTimeForC.getValue().isBefore(EndTimeForC.getValue())) {
            ErrorTextForC.setVisible(true);
            ErrorTextForC.setText("输入格式不正确");
        } else {
            InputStockByCodeVO codeVOA = new InputStockByCodeVO();
            InputStockByNameVO nameVOA = new InputStockByNameVO();
            InputStockByCodeVO codeVOB = new InputStockByCodeVO();
            InputStockByNameVO nameVOB = new InputStockByNameVO();
            if (Character.isDigit(StockNameAForC.getText().toCharArray()[0])) { //判断用户输入的是代码还是名称
                try {
                    String start;
                    String end;
                    StringBuffer sb = new StringBuffer();
                    String[] temp;
                    temp = StartTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    start = sb.toString();
                    sb = new StringBuffer();
                    temp = EndTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    end = sb.toString();

                    codeVOA.setStart(start);
                    codeVOA.setEnd(end);
                    codeVOA.setCode(StockNameAForC.getText());
                } catch (NullPointerException e) {
                    ErrorTextForC.setVisible(true);
                    ErrorTextForC.setText("请输入完整信息");
                }
                setPieChartOneByCode(codeVOA);
                PieChartAForC.setVisible(true);
            } else {
                try {
                    String start;
                    String end;

                    StringBuffer sb = new StringBuffer();
                    String[] temp;
                    temp = StartTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    start = sb.toString();
                    sb = new StringBuffer();
                    temp = EndTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    end = sb.toString();
                    nameVOA.setStart(start);
                    nameVOA.setEnd(end);
                    nameVOA.setName(StockNameAForC.getText());
                } catch (NullPointerException e) {
                    ErrorTextForC.setVisible(true);
                    ErrorTextForC.setText("请输入完整信息");
                }
                setPieChartOneByName(nameVOA);
                PieChartAForC.setVisible(true);
            }
            if (Character.isDigit(StockNameBForC.getText().toCharArray()[0])) { //判断用户输入的是代码还是名称
                try {
                    String start;
                    String end;
                    StringBuffer sb = new StringBuffer();
                    String[] temp;
                    temp = StartTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    start = sb.toString();
                    sb = new StringBuffer();
                    temp = EndTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    end = sb.toString();
                    codeVOB.setStart(start);
                    codeVOB.setEnd(end);
                    codeVOB.setCode(StockNameBForC.getText());
                } catch (NullPointerException e) {
                    ErrorTextForC.setVisible(true);
                    ErrorTextForC.setText("请输入完整信息");
                }

                setPieChartTwoByCode(codeVOB);
                PieChartBForC.setVisible(true);
                EMAChartForC.setVisible(true);
                ArrayList<InputStockByCodeVO> codeVO = new ArrayList<InputStockByCodeVO>();
                codeVO.add(codeVOA);
                codeVO.add(codeVOB);
                setEMAChartForCByCode(codeVOA, codeVOB);
                setLogarithmicYieldByCode(codeVO);
                setCompareTableByCodeForC(codeVO);
                CompareTableForC.setVisible(true);
            } else {
                try {
                    String start;
                    String end;

                    StringBuffer sb = new StringBuffer();
                    String[] temp;
                    temp = StartTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    start = sb.toString();
                    sb = new StringBuffer();
                    temp = EndTimeForC.getValue().toString().split("-");
                    sb.append(temp[1]);
                    sb.append("/");
                    sb.append(temp[2]);
                    sb.append("/");
                    sb.append(temp[0]);
                    end = sb.toString();
                    nameVOB.setStart(start);
                    nameVOB.setEnd(end);
                    nameVOB.setName(StockNameBForC.getText());
                } catch (NullPointerException e) {
                    ErrorTextForC.setVisible(true);
                    ErrorTextForC.setText("请输入完整信息");
                }

                setPieChartTwoByName(nameVOB);
                PieChartBForC.setVisible(true);
                EMAChartForC.setVisible(true);
                ArrayList<InputStockByNameVO> nameVOForL = new ArrayList<InputStockByNameVO>();
                nameVOForL.add(nameVOA);
                nameVOForL.add(nameVOB);
                setEMAChartForCByName(nameVOA, nameVOB);
                setLogarithmicYieldByName(nameVOForL);
                setCompareTableByNameForC(nameVOForL);
                CompareTableForC.setVisible(true);
            }
        }
    }

    private void setEMAChartForCByCode(InputStockByCodeVO vo1, InputStockByCodeVO vo2) {
        ArrayList<StockVO> resultVO;
        EMAChartForC.getData().remove(0, EMAChartForC.getData().size());
        try {
            resultVO = this.kser.getDataByCode(vo1);
            XYChart.Series seriesOne = new XYChart.Series();
            XYChart.Series seriesTwo = new XYChart.Series();
            for (int i = 0; i < resultVO.size(); i++) {
                seriesOne.getData().add(new XYChart.Data(resultVO.get(i).getDate(), Double.parseDouble(resultVO.get(i).getClose())));
            }
            seriesOne.setName(resultVO.get(0).getName());
            resultVO = this.kser.getDataByCode(vo2);
            for (int i = 0; i < resultVO.size(); i++) {
                seriesTwo.getData().add(new XYChart.Data(resultVO.get(i).getDate(), Double.parseDouble(resultVO.get(i).getClose())));
            }
            seriesTwo.setName(resultVO.get(0).getName());
            if (seriesOne.getData().size() < 30) {
                EMAChartForC.setCreateSymbols(true);
            } else {
                EMAChartForC.setCreateSymbols(false);
            }
            ((NumberAxis) EMAChartForC.getYAxis()).setForceZeroInRange(false);
            EMAChartForC.getData().add(seriesOne);
            EMAChartForC.getData().add(seriesTwo);
            EMAChartForC.setVisible(true);
        } catch (NullPointerException e) {
            ErrorTextForC.setVisible(true);
            ErrorTextForC.setText("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setEMAChartForCByName(InputStockByNameVO vo1, InputStockByNameVO vo2) {
        ArrayList<StockVO> resultVO;
        EMAChartForC.getData().remove(0, EMAChartForC.getData().size());
        try {
            resultVO = this.kser.getDataByName(vo1);
            XYChart.Series seriesOne = new XYChart.Series();
            XYChart.Series seriesTwo = new XYChart.Series();
            for (int i = 0; i < resultVO.size(); i++) {
                seriesOne.getData().add(new XYChart.Data(resultVO.get(i).getDate(), Double.parseDouble(resultVO.get(i).getClose())));
            }
            seriesOne.setName(resultVO.get(0).getName());
            resultVO = this.kser.getDataByName(vo1);
            for (int i = 0; i < resultVO.size(); i++) {
                seriesTwo.getData().add(new XYChart.Data(resultVO.get(i).getDate(), Double.parseDouble(resultVO.get(i).getClose())));
            }
            seriesTwo.setName(resultVO.get(0).getName());
            if (seriesOne.getData().size() < 30) {
                EMAChartForC.setCreateSymbols(true);
            } else {
                EMAChartForC.setCreateSymbols(false);
            }
            ((NumberAxis) EMAChartForC.getYAxis()).setForceZeroInRange(false);
            EMAChartForC.getData().add(seriesOne);
            EMAChartForC.getData().add(seriesTwo);
            EMAChartForC.setVisible(true);

        } catch (NullPointerException e) {
            ErrorTextForC.setVisible(true);
            ErrorTextForC.setText("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPieChartOneByCode(InputStockByCodeVO vo) {
        PieVO resultVO;
        try {
            resultVO = this.kser.getPieDataByCode(vo);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(resultVO.getIncreaseLessDays())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(resultVO.getIncreaseMoreDays())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(resultVO.getDecreaseLessDays())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(resultVO.getDecreaseMoredays()))
            );
            int countOfRemove = 0;
            if (Integer.parseInt(resultVO.getIncreaseLessDays()) == 0) {
                pieChartData.remove(0);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getIncreaseMoreDays()) == 0) {
                pieChartData.remove(1 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseLessDays()) == 0) {
                pieChartData.remove(2 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseMoredays()) == 0) {
                pieChartData.remove(3 - countOfRemove);
                countOfRemove++;
            }
            PieChartAForC.setData(pieChartData);
            PieChartAForC.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setPieChartOneByName(InputStockByNameVO vo) {
        PieVO resultVO;
        try {
            resultVO = this.kser.getPieDataByName(vo);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(resultVO.getIncreaseLessDays())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(resultVO.getIncreaseMoreDays())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(resultVO.getDecreaseLessDays())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(resultVO.getDecreaseMoredays()))
            );
            int countOfRemoe = 0;
            if (Integer.parseInt(resultVO.getIncreaseLessDays()) == 0) {
                pieChartData.remove(0);
                countOfRemoe++;
            }
            if (Integer.parseInt(resultVO.getIncreaseMoreDays()) == 0) {
                pieChartData.remove(1 - countOfRemoe);
                countOfRemoe++;
            }
            if (Integer.parseInt(resultVO.getDecreaseLessDays()) == 0) {
                pieChartData.remove(2 - countOfRemoe);
                countOfRemoe++;
            }
            if (Integer.parseInt(resultVO.getDecreaseMoredays()) == 0) {
                pieChartData.remove(3 - countOfRemoe);
                countOfRemoe++;
            }
            PieChartBForC.setData(pieChartData);
            PieChartBForC.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPieChartTwoByCode(InputStockByCodeVO vo) {
        PieVO resultVO;
        try {
            resultVO = this.kser.getPieDataByCode(vo);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(resultVO.getIncreaseLessDays())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(resultVO.getIncreaseMoreDays())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(resultVO.getDecreaseLessDays())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(resultVO.getDecreaseMoredays()))
            );
            int countOfRemove = 0;
            if (Integer.parseInt(resultVO.getIncreaseLessDays()) == 0) {
                pieChartData.remove(0);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getIncreaseMoreDays()) == 0) {
                pieChartData.remove(1 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseLessDays()) == 0) {
                pieChartData.remove(2 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseMoredays()) == 0) {
                pieChartData.remove(3 - countOfRemove);
                countOfRemove++;
            }
            PieChartBForC.setData(pieChartData);
            PieChartBForC.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPieChartTwoByName(InputStockByNameVO vo) {
        PieVO resultVO;
        try {
            resultVO = this.kser.getPieDataByName(vo);
            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(resultVO.getIncreaseLessDays())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(resultVO.getIncreaseMoreDays())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(resultVO.getDecreaseLessDays())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(resultVO.getDecreaseMoredays()))
            );
            int countOfRemove = 0;
            if (Integer.parseInt(resultVO.getIncreaseLessDays()) == 0) {
                pieChartData.remove(0);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getIncreaseMoreDays()) == 0) {
                pieChartData.remove(1 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseLessDays()) == 0) {
                pieChartData.remove(2 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(resultVO.getDecreaseMoredays()) == 0) {
                pieChartData.remove(3 - countOfRemove);
                countOfRemove++;
            }
            PieChartBForC.setData(pieChartData);
            PieChartBForC.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLogarithmicYieldByCode(ArrayList<InputStockByCodeVO> vo) {
        ArrayList<ArrayList<StockCompareEverydayVO>> resultVO;
        LogarithmicYield.getData().remove(0, LogarithmicYield.getData().size());
        try {
            resultVO = this.qser.quantourChartCompareByCode(vo);
            ArrayList<StockCompareEverydayVO> resultVOOne = resultVO.get(0);
            ArrayList<StockCompareEverydayVO> resultVOTwo = resultVO.get(1);
            XYChart.Series seriesOne = new XYChart.Series();
            XYChart.Series seriesTwo = new XYChart.Series();
            for (int i = 0; i < resultVOOne.size(); i++) {
                seriesOne.getData().add(new XYChart.Data(resultVOOne.get(i).getDate(), Double.parseDouble(resultVOOne.get(i).getLogarithmicYield())));
            }
            for (int i = 0; i < resultVOTwo.size(); i++) {
                seriesTwo.getData().add(new XYChart.Data(resultVOTwo.get(i).getDate(), Double.parseDouble(resultVOTwo.get(i).getLogarithmicYield())));
            }
            seriesOne.setName(resultVOOne.get(0).getName());
            seriesTwo.setName(resultVOTwo.get(0).getName());
            LogarithmicYield.getData().add(seriesOne);
            LogarithmicYield.getData().add(seriesTwo);
            LogarithmicYield.setLegendSide(Side.TOP);
            LogarithmicYield.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setLogarithmicYieldByName(ArrayList<InputStockByNameVO> vo) {
        ArrayList<ArrayList<StockCompareEverydayVO>> resultVO;
        LogarithmicYield.getData().remove(0, LogarithmicYield.getData().size());
        try {
            resultVO = this.qser.quantourChartCompareByName(vo);
            ArrayList<StockCompareEverydayVO> resultVOOne = resultVO.get(0);
            ArrayList<StockCompareEverydayVO> resultVOTwo = resultVO.get(1);
            XYChart.Series seriesOne = new XYChart.Series();
            XYChart.Series seriesTwo = new XYChart.Series();
            for (int i = 0; i < resultVOOne.size(); i++) {
                seriesOne.getData().add(new XYChart.Data(resultVOOne.get(i).getDate(), Double.parseDouble(resultVOOne.get(i).getLogarithmicYield())));
            }
            for (int i = 0; i < resultVOTwo.size(); i++) {
                seriesTwo.getData().add(new XYChart.Data(resultVOTwo.get(i).getDate(), Double.parseDouble(resultVOTwo.get(i).getLogarithmicYield())));
            }
            LogarithmicYield.getData().add(seriesOne);
            LogarithmicYield.getData().add(seriesTwo);
            LogarithmicYield.setVisible(true);
            LogarithmicYield.setLegendSide(Side.TOP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogarithmicYield.setVisible(true);
    }

    private void setCompareTableByCodeForC(ArrayList<InputStockByCodeVO> vo) {
        ArrayList<StockCompareTotalVO> resultVO;
        CompareTableForC.getColumns().remove(0, CompareTableForC.getColumns().size());
        try {
            resultVO = this.qser.quantourTableCompareByCode(vo);
            TableColumn dataType = new TableColumn("数据类型");
            TableColumn quantourA = new TableColumn("股票A");
            TableColumn quantourB = new TableColumn("股票B");
            ObservableList<tableDataForC> tableDataForMS = FXCollections.observableArrayList(
                    new tableDataForC("最高价", resultVO.get(0).getHigh(), resultVO.get(1).getHigh()),
                    new tableDataForC("最低价", resultVO.get(0).getLow(), resultVO.get(1).getLow()),
                    new tableDataForC("涨跌幅", resultVO.get(0).getIncreaseOrDecrease(), resultVO.get(1).getIncreaseOrDecrease()),
                    new tableDataForC("对数收益率防差", resultVO.get(0).getLogarithmicYieldVariance(), resultVO.get(1).getLogarithmicYieldVariance())
            );
            dataType.setMinWidth(120);
            dataType.setCellValueFactory(
                    new PropertyValueFactory<>("dataType")
            );
            quantourA.setMinWidth(120);
            quantourA.setCellValueFactory(
                    new PropertyValueFactory<>("quantourA")
            );
            quantourB.setMinWidth(120);
            quantourB.setCellValueFactory(
                    new PropertyValueFactory<>("quantourB")
            );
            CompareTableForC.setContextMenu(new ContextMenu(new MenuItem("添加"), new MenuItem("选择")));
            CompareTableForC.setEditable(false);
            CompareTableForC.setItems(tableDataForMS);
            CompareTableForC.getColumns().addAll(dataType, quantourA, quantourB);
            CompareTableForC.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setCompareTableByNameForC(ArrayList<InputStockByNameVO> vo) {
        ArrayList<StockCompareTotalVO> resultVO;
        CompareTableForC.getColumns().remove(0, CompareTableForC.getColumns().size());
        try {
            resultVO = this.qser.quantourTableCompareByName(vo);
            TableColumn dataType = new TableColumn("数据类型");
            TableColumn quantourA = new TableColumn("股票A");
            TableColumn quantourB = new TableColumn("股票B");
            ObservableList<tableDataForC> tableDataForMS = FXCollections.observableArrayList(
                    new tableDataForC("最高价", resultVO.get(0).getHigh(), resultVO.get(1).getHigh()),
                    new tableDataForC("最低价", resultVO.get(0).getLow(), resultVO.get(1).getLow()),
                    new tableDataForC("涨跌幅", resultVO.get(0).getIncreaseOrDecrease(), resultVO.get(1).getIncreaseOrDecrease()),
                    new tableDataForC("对数收益率防差", resultVO.get(0).getLogarithmicYieldVariance(), resultVO.get(1).getLogarithmicYieldVariance())
            );
            dataType.setMinWidth(120);
            dataType.setCellValueFactory(
                    new PropertyValueFactory<>("dataType")
            );
            quantourA.setMinWidth(120);
            quantourA.setCellValueFactory(
                    new PropertyValueFactory<>("quantourA")
            );
            quantourB.setMinWidth(120);
            quantourB.setCellValueFactory(
                    new PropertyValueFactory<>("quantourB")
            );
            CompareTableForC.setContextMenu(new ContextMenu(new MenuItem("添加"), new MenuItem("选择")));
            CompareTableForC.setEditable(false);
            CompareTableForC.setItems(tableDataForMS);
            CompareTableForC.getColumns().addAll(dataType, quantourA, quantourB);
            CompareTableForC.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




/*------------------------------------------------------------股票比较-------QuantourCompareController----------------------------------------------------------------------------------------- */


    /*-----------------------------------------------------------市场--------MarketSituationController----------------------------------------------------------------------------------------- */
    @FXML
    private Tab MarketTab;
//   @FXML private Label ErrorTextForM;
//   @FXML private DatePicker DateForMarket;
//   @FXML private ChoiceBox MarketChoice;
//   @FXML private Button QueryButtonForM;

    @FXML
    private LineChart Hushen300ForMarket;//沪深300
    @FXML
    private LineChart ShangzhengForMarket;//上证
    @FXML
    private LineChart ShenzhenForMarket;//深圳


    @FXML
    private TabPane TabPaneForNews;//新闻区选择
    @FXML
    private Tab ImportantnewsForMarket;//要闻
    @FXML
    private Tab OutburstnewsForMarket;//突发

    @FXML
    private TabPane TabPaneForM;//市场总体情况榜单的选择
    @FXML
    private Tab StatisticTab1;//涨幅榜
    @FXML
    private Tab StatisticTab2;//跌幅榜
    @FXML
    private Tab StatisticTab3;//成交量榜
    @FXML
    private Tab StatisticTab4;//潜力榜
    @FXML
    private TableView IncreaseRank;//涨幅榜
    @FXML
    private TableView DecreaseRank;//跌幅榜
    @FXML
    private TableView VolumeRank;//成交量榜
    @FXML
    private TableView PotentialRank;//潜力榜

    @FXML
    private PieChart PieChartForM;//市场总体情况的饼状图

//   @FXML private TableView TableViewForMarketSituation;

    private MarketSituationBlSer mser;

    public void QueryForM(String date) {
        this.mser = new bl.marketSituationBl.MarketSituationController();
        this.kser = new KAndEMAController();
//        ErrorTextForM.setVisible(false);

//        if(DateForMarket.getValue()!=null&&MarketChoice.getSelectionModel()!=null){

        try {
//                StringBuffer sb = new StringBuffer();
//                String[] temp;
//                temp = DateForMarket.getValue().toString().split("-");
//                sb.append(temp[1]);
//                sb.append("/");
//                sb.append(temp[2]);
//                sb.append("/");
//                sb.append(temp[0]);
//                date = sb.toString();

            try {
                MarketSituationVO vo = this.mser.getMarketSituation(date);
            } catch (NullPointerException e) {
//                    ErrorTextForM.setText("找不到数据");
//                    ErrorTextForM.setVisible(true);
                Hushen300ForMarket.setVisible(false);
                ShangzhengForMarket.setVisible(false);
                ShenzhenForMarket.setVisible(false);

                PieChartForM.setVisible(false);
                IncreaseRank.setVisible(false);
                DecreaseRank.setVisible(false);
                VolumeRank.setVisible(false);
                PotentialRank.setVisible(false);
//                    TableViewForMarketSituation.setVisible(false);
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            setHushen300ForMarket(date);
            setShangzhengForMarket(date);
            setShenzhenForMarket(date);

            setPieChartForM(date);//绘制饼状图;
//                setTableViewForMarketSituation(date);
            setIncreaseRank(date);//绘制涨幅榜;
            setDecreaseRank(date);//绘制跌幅榜;
            setVolumeRank(date);//绘制成交量排行榜;
            setPotentialRank(date);//绘制潜力榜
        } catch (NullPointerException e) {
//                ErrorTextForM.setVisible(true);
//                ErrorTextForM.setText("请输入完整信息");
        }

//        }else {
////            ErrorTextForM.setText("请输入完整信息");
////            ErrorTextForM.setVisible(true);
//        }
    }

    private void setPieChartForM(String date) {
        try {
            MarketSituationVO vo = this.mser.getMarketSituation(date);
            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("涨停数", Integer.parseInt(vo.getIncreaseStopStockNum())),
                    new PieChart.Data("跌停数", Integer.parseInt(vo.getDecreaseStopStockNum())),
                    new PieChart.Data("涨幅大于5%", Integer.parseInt(vo.getIncreaseMoreStockNum())),
                    new PieChart.Data("跌幅大于5%", Integer.parseInt(vo.getDecreaseMoreStockNum())),
                    new PieChart.Data("涨幅小于5%", Integer.parseInt(vo.getOpenMinusCloseMoreNum())),
                    new PieChart.Data("跌幅小于5%", Integer.parseInt(vo.getOpenMinusCloseLessNum()))
            );
            int countOfRemove = 0;
            if (Integer.parseInt(vo.getIncreaseStopStockNum()) == 0) {
                pieData.remove(0);
                countOfRemove++;
            }
            if (Integer.parseInt(vo.getDecreaseStopStockNum()) == 0) {
                pieData.remove(1 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(vo.getIncreaseMoreStockNum()) == 0) {
                pieData.remove(2 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(vo.getDecreaseMoreStockNum()) == 0) {
                pieData.remove(3 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(vo.getOpenMinusCloseMoreNum()) == 0) {
                pieData.remove(4 - countOfRemove);
                countOfRemove++;
            }
            if (Integer.parseInt(vo.getOpenMinusCloseLessNum()) == 0) {
                pieData.remove(5 - countOfRemove);
                countOfRemove++;
            }
            PieChartForM.setData(pieData);
            PieChartForM.setVisible(true);
            PieChartForM.getData();
        } catch (NullPointerException e) {
//            ErrorTextForM.setVisible(true);
//            ErrorTextForM.setText("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //市场涨跌情况饼状图;

    private void setIncreaseRank(String date) {
        ArrayList<StockVO> resultVO;
        IncreaseRank.getColumns().remove(0, IncreaseRank.getColumns().size());
        try {
            if (this.mser.getIncreaseList(date) == null) {
//                ErrorTextForM.setVisible(true);
//                ErrorTextForM.setText("找不到搜索结果！");
            } else {
                resultVO = this.mser.getIncreaseList(date);
                TableColumn increaseList = new TableColumn("涨幅榜");
                TableColumn data = new TableColumn("");
                ObservableList<tableDataForM> tableDataForM = FXCollections.observableArrayList();
                for (int i = 0; i < resultVO.size(); i++) {
                    tableDataForM.add(i, new tableDataForM(resultVO.get(i).getName(), resultVO.get(i).getIncreaseOrDecrease()));
                }
                increaseList.setMinWidth(120);
                data.setMinWidth(120);
                increaseList.setCellValueFactory(
                        new PropertyValueFactory<>("quantourName")
                );
                data.setCellValueFactory(
                        new PropertyValueFactory<>("data")
                );
                MenuItem menuItem1 = new MenuItem("添加到自选股");
                IncreaseRank.setContextMenu(new ContextMenu(menuItem1));
                menuItem1.setOnAction(event -> {
                            Object object = IncreaseRank.getSelectionModel().getSelectedItem();
                            UserBlSer userBlSer = new UserController();
                            try {
                                if (userBlSer.isMySelfSelectStock(((tableDataForM) object).getQuantourName(), userVO.getAccount())) {

                                } else {
                                    userBlSer.addOneSelfSelectStock(userVO.getAccount(), ((tableDataForM) object).getQuantourName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
                IncreaseRank.setItems(tableDataForM);
                IncreaseRank.getColumns().addAll(increaseList, data);
                IncreaseRank.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //涨幅榜;

    private void setDecreaseRank(String date) {
        ArrayList<StockVO> resultVO;
        DecreaseRank.getColumns().remove(0, DecreaseRank.getColumns().size());
        try {
            if (this.mser.getDecreaseList(date) == null) {
//                ErrorTextForM.setVisible(true);
//                ErrorTextForM.setText("找不到搜索结果！");
            } else {
                resultVO = this.mser.getDecreaseList(date);
                TableColumn decreaseList = new TableColumn("跌幅榜");
                TableColumn data = new TableColumn("");
                ObservableList<tableDataForM> tableDataForM = FXCollections.observableArrayList();
                for (int i = 0; i < resultVO.size(); i++) {
                    tableDataForM.add(i, new tableDataForM(resultVO.get(i).getName(), resultVO.get(i).getIncreaseOrDecrease()));
                }
                decreaseList.setMinWidth(120);
                data.setMinWidth(120);
                decreaseList.setCellValueFactory(
                        new PropertyValueFactory<>("quantourName")
                );
                data.setCellValueFactory(
                        new PropertyValueFactory<>("data")
                );
                MenuItem menuItem1 = new MenuItem("添加到自选股");
                DecreaseRank.setContextMenu(new ContextMenu(menuItem1));
                menuItem1.setOnAction(event -> {
                            Object object = DecreaseRank.getSelectionModel().getSelectedItem();
                            UserBlSer userBlSer = new UserController();
                            try {
                                if (userBlSer.isMySelfSelectStock(((tableDataForM) object).getQuantourName(), userVO.getAccount())) {

                                } else {
                                    userBlSer.addOneSelfSelectStock(userVO.getAccount(), ((tableDataForM) object).getQuantourName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
                DecreaseRank.getColumns().addAll(decreaseList, data);
                DecreaseRank.setItems(tableDataForM);
                DecreaseRank.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //跌幅榜;

    private void setVolumeRank(String date) {
        ArrayList<StockVO> resultVO;
        VolumeRank.getColumns().remove(0, VolumeRank.getColumns().size());
        try {
            if (this.mser.getVolumeList(date) == null) {
//                ErrorTextForM.setVisible(true);
//                ErrorTextForM.setText("找不到搜索结果！");
            } else {
                resultVO = this.mser.getVolumeList(date);
                TableColumn volumeList = new TableColumn("成交量排行榜");
                TableColumn data = new TableColumn("");
                ObservableList<tableDataForM> tableDataForM = FXCollections.observableArrayList();
                for (int i = 0; i < resultVO.size(); i++) {
                    tableDataForM.add(i, new tableDataForM(resultVO.get(i).getName(), resultVO.get(i).getVolume()));
                }
                volumeList.setMinWidth(120);
                volumeList.setCellValueFactory(
                        new PropertyValueFactory<>("quantourName")
                );
                data.setMinWidth(120);
                data.setCellValueFactory(
                        new PropertyValueFactory<>("data")
                );
                MenuItem menuItem1 = new MenuItem("添加到自选股");
                VolumeRank.setContextMenu(new ContextMenu(menuItem1));
                menuItem1.setOnAction(event -> {
                            Object object = VolumeRank.getSelectionModel().getSelectedItem();
                            UserBlSer userBlSer = new UserController();
                            try {
                                if (userBlSer.isMySelfSelectStock(((tableDataForM) object).getQuantourName(), userVO.getAccount())) {

                                } else {
                                    userBlSer.addOneSelfSelectStock(userVO.getAccount(), ((tableDataForM) object).getQuantourName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
                VolumeRank.setItems(tableDataForM);
                VolumeRank.getColumns().addAll(volumeList, data);
                VolumeRank.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //成交量排行榜;

    private void setPotentialRank(String date) {
        ArrayList<StockVO> resultVO;
        PotentialRank.getColumns().remove(0, PotentialRank.getColumns().size());
        try {
            if (this.mser.getVolumeList(date) == null) {
//                ErrorTextForM.setVisible(true);
//                ErrorTextForM.setText("找不到搜索结果！");
            } else {
                resultVO = this.mser.getPotentialList(date);
                TableColumn volumeList = new TableColumn("潜力排行榜");
                TableColumn data = new TableColumn("");
                ObservableList<tableDataForM> tableDataForM = FXCollections.observableArrayList();
                for (int i = 0; i < resultVO.size(); i++) {
                    tableDataForM.add(i, new tableDataForM(resultVO.get(i).getName(), resultVO.get(i).getVolume()));
                }
                volumeList.setMinWidth(120);
                volumeList.setCellValueFactory(
                        new PropertyValueFactory<>("quantourName")
                );
                data.setMinWidth(120);
                data.setCellValueFactory(
                        new PropertyValueFactory<>("data")
                );
                MenuItem menuItem1 = new MenuItem("添加到自选股");
                PotentialRank.setContextMenu(new ContextMenu(menuItem1));
                menuItem1.setOnAction(event -> {
                            Object object = PotentialRank.getSelectionModel().getSelectedItem();
                            UserBlSer userBlSer = new UserController();
                            try {
                                if (userBlSer.isMySelfSelectStock(((tableDataForM) object).getQuantourName(), userVO.getAccount())) {

                                } else {
                                    userBlSer.addOneSelfSelectStock(userVO.getAccount(), ((tableDataForM) object).getQuantourName());
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                );
                PotentialRank.setItems(tableDataForM);
                PotentialRank.getColumns().addAll(volumeList, data);
                PotentialRank.setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } //潜力排行榜;

    private void setHushen300ForMarket(String date) {
        Calculate calculate = new Calculate();
        String start = calculate.getBeforeSomeTradeDate(date, 30);//获取前30天的日期
        //沪深300的股票代码是000300
//        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "000300");
        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "401");
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO = stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD = stockVO.getIncreaseOrDecrease();
            iOrD = iOrD.substring(0, iOrD.length() - 1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        Hushen300ForMarket.getData().addAll(series1, series2);
        Hushen300ForMarket.setCreateSymbols(false);
        Hushen300ForMarket.setVisible(true);
    }

    public void setShangzhengForMarket(String date) {
        Calculate calculate = new Calculate();
        String start = calculate.getBeforeSomeTradeDate(date, 30);//获取前30天的日期
        //上证指数的股票代码是000001
        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "000001");
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO = stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD = stockVO.getIncreaseOrDecrease();
            iOrD = iOrD.substring(0, iOrD.length() - 1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        ShangzhengForMarket.getData().addAll(series1, series2);
        ShangzhengForMarket.setCreateSymbols(false);
        ShangzhengForMarket.setVisible(true);
    }

    public void setShenzhenForMarket(String date) {
        Calculate calculate = new Calculate();
        String start = calculate.getBeforeSomeTradeDate(date, 30);//获取前30天的日期
        //深圳成指的股票代码是399001
//        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "399001");
        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "402");
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO = stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD = stockVO.getIncreaseOrDecrease();
            iOrD = iOrD.substring(0, iOrD.length() - 1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        ShenzhenForMarket.getData().addAll(series1, series2);
        ShenzhenForMarket.setCreateSymbols(false);
        ShenzhenForMarket.setVisible(true);
    }

    //监听
    public void goToHushen300Stock() {
        MainTabPane.getSelectionModel().select(SingleStockTab);

        BForSingleStockTab.setStyle("-fx-background-color: #1A1A1A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");

        StockNameForK.setText("000300");
        QueryForK();
    }

    public void goToShangzhengStock() {
        MainTabPane.getSelectionModel().select(SingleStockTab);

        BForSingleStockTab.setStyle("-fx-background-color: #1A1A1A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");

        StockNameForK.setText("000001");
    }

    public void goToShenzhenStock() {
        MainTabPane.getSelectionModel().select(SingleStockTab);

        BForSingleStockTab.setStyle("-fx-background-color: #1A1A1A");
        BForQuantourCompareTab.setStyle("-fx-background-color: #2A2A2A");
        BForMarketTab.setStyle("-fx-background-color: #2A2A2A");
        BForPlateTab.setStyle("-fx-background-color: #2A2A2A");
        BForQuantitativeInvestmentTab.setStyle("-fx-background-color: #2A2A2A");
        BForSelfSelectStockTab.setStyle("-fx-background-color: #2A2A2A");
        BForIndustryTab.setStyle("-fx-background-color: #2A2A2A");

        StockNameForK.setText("399001");
    }

/*----------------------------------------------------------市场---------MarketSituationController----------------------------------------------------------------------------------------- */

    /*---------------------------------------------------------板块----------PlateController----------------------------------------------------------------------------------------- */
    @FXML
    private Tab DetailsTab;

    //板块整体情况图
    @FXML
    private LineChart Hushen300chartForPlate;
    @FXML
    private LineChart ZhongxiaochartForPlate;
    @FXML
    private LineChart ChuangyechartForPlate;

    //板块整体情况表
    @FXML
    private TableView Hushen300tableForPlate;
    @FXML
    private TableView ZhongxiaotableForPlate;
    @FXML
    private TableView ChuangyetableForPlate;

    //板块整体情况简易表格
    @FXML
    private TableView Hushen300WholeSimpleForPlate;
    @FXML
    private TableView ZhongxiaoWholeSimpleForPlate;
    @FXML
    private TableView ChuangyeWholeSimpleForPlate;

    //@FXML private DatePicker TimeForPlate;
//@FXML private Button QueryButtonForP;
//@FXML private TableView TableViewForP;
    @FXML
    private Button BackToAllPlate;//返回板块整体信息
//@FXML private Label ErrorTextForP;
//@FXML private ChoiceBox MarketChoiceForP;

    private PlateBlSer plateBlSer;
    private KAndEMABlSer kAndEMABlSer = new KAndEMAController();

    private void initializeWholePlate(String date) {
        setWholeSimpleForPlate(date);
        setHushen300chartForPlate(date);
        setZhongxiaochartForPlate(date);
        setChuangyechartForPlate(date);
        setHushen300tableForPlate(date);
        setZhongxiaotableForPlate(date);
        setChuangyetableForPlate(date);
    }

    private void setWholeSimpleForPlate(String dateForP) {
//    TableViewForP.getColumns().remove(0,TableViewForP.getColumns().size());
        try {
            TotalPlateVO plateVOS = new TotalPlateVO();
            try {
                plateVOS = this.plateBlSer.getTotalPlateInfo(dateForP);
            } catch (NullPointerException e) {
                System.out.println("找不到数据");
            }

            if (plateVOS.getPlateName().size() == 3) {
                TableColumn firstCol = new TableColumn(null);
                TableColumn secondCol = new TableColumn(null);
                TableColumn thirdCol = new TableColumn(null);
                TableColumn forthCol = new TableColumn(null);
                final ObservableList<TotalPlateInfo> data1 = FXCollections.observableArrayList(
                        new TotalPlateInfo("公司数", plateVOS.getCompanyNum().get(0), "前日收盘", plateVOS.getPreAverageAdjClose().get(0)),
                        new TotalPlateInfo("平均开盘", plateVOS.getAverageOpen().get(0), "涨跌幅", plateVOS.getIncreaseOrDecreaseRate().get(0)),
                        new TotalPlateInfo("平均收盘", plateVOS.getAverageClose().get(0), "涨跌额", plateVOS.getIncreaseOrDecreaseMoney().get(0)),
                        new TotalPlateInfo("平均复权收盘", plateVOS.getAverageAdjClose().get(0), "总交易量", plateVOS.getTotalVolume().get(0))
                );
                final ObservableList<TotalPlateInfo> data2 = FXCollections.observableArrayList(
                        new TotalPlateInfo("公司数", plateVOS.getCompanyNum().get(1), "前日收盘", plateVOS.getPreAverageAdjClose().get(1)),
                        new TotalPlateInfo("平均开盘", plateVOS.getAverageOpen().get(1), "涨跌幅", plateVOS.getIncreaseOrDecreaseRate().get(1)),
                        new TotalPlateInfo("平均收盘", plateVOS.getAverageClose().get(1), "涨跌额", plateVOS.getIncreaseOrDecreaseMoney().get(1)),
                        new TotalPlateInfo("平均复权收盘", plateVOS.getAverageAdjClose().get(1), "总交易量", plateVOS.getTotalVolume().get(1))
                );
                final ObservableList<TotalPlateInfo> data3 = FXCollections.observableArrayList(
                        new TotalPlateInfo("公司数", plateVOS.getCompanyNum().get(2), "前日收盘", plateVOS.getPreAverageAdjClose().get(2)),
                        new TotalPlateInfo("平均开盘", plateVOS.getAverageOpen().get(2), "涨跌幅", plateVOS.getIncreaseOrDecreaseRate().get(2)),
                        new TotalPlateInfo("平均收盘", plateVOS.getAverageClose().get(2), "涨跌额", plateVOS.getIncreaseOrDecreaseMoney().get(2)),
                        new TotalPlateInfo("平均复权收盘", plateVOS.getAverageAdjClose().get(2), "总交易量", plateVOS.getTotalVolume().get(2))
                );
                firstCol.setCellValueFactory(
                        new PropertyValueFactory<>("first")
                );
                secondCol.setCellValueFactory(
                        new PropertyValueFactory<>("second")
                );
                thirdCol.setCellValueFactory(
                        new PropertyValueFactory<>("third")
                );
                forthCol.setCellValueFactory(
                        new PropertyValueFactory<>("forth")
                );
                Hushen300WholeSimpleForPlate.setItems(data1);
                Hushen300WholeSimpleForPlate.getColumns().addAll(firstCol, secondCol, thirdCol);
                Hushen300WholeSimpleForPlate.setVisible(true);
                ZhongxiaoWholeSimpleForPlate.setItems(data2);
                ZhongxiaoWholeSimpleForPlate.getColumns().addAll(firstCol, secondCol, thirdCol);
                ZhongxiaoWholeSimpleForPlate.setVisible(true);
                ChuangyeWholeSimpleForPlate.setItems(data3);
                ChuangyeWholeSimpleForPlate.getColumns().addAll(firstCol, secondCol, thirdCol);
                ChuangyeWholeSimpleForPlate.setVisible(true);
            }

        } catch (NullPointerException e) {
//        ErrorTextForP.setVisible(true);
//        ErrorTextForP.setText("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setHushen300chartForPlate(String date) {
        Calculate calculate = new Calculate();
        String start = calculate.getBeforeSomeTradeDate(date, 30);//获取前30天的日期
        //沪深300的股票代码是000300
//        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "000300");
        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "10");
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO = stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD = stockVO.getIncreaseOrDecrease();
            iOrD = iOrD.substring(0, iOrD.length() - 1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        Hushen300chartForPlate.getData().addAll(series1, series2);
        Hushen300chartForPlate.setCreateSymbols(false);
        Hushen300chartForPlate.setVisible(true);
    }

    private void setZhongxiaochartForPlate(String date) {
        Calculate calculate = new Calculate();
        String start = calculate.getBeforeSomeTradeDate(date, 30);//获取前30天的日期
        //中小板指的股票代码是399005
//        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "399005");
        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "1");
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO = stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD = stockVO.getIncreaseOrDecrease();
            iOrD = iOrD.substring(0, iOrD.length() - 1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        ZhongxiaochartForPlate.getData().addAll(series1, series2);
        ZhongxiaochartForPlate.setCreateSymbols(false);
        ZhongxiaochartForPlate.setVisible(true);
    }

    public void setChuangyechartForPlate(String date) {
        Calculate calculate = new Calculate();
        String start = calculate.getBeforeSomeTradeDate(date, 30);//获取前30天的日期
        //创业板指的股票代码是399006
//        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "399006");
        InputStockByCodeVO inputStockByCodeVO = new InputStockByCodeVO(start, date, "2017");
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = kAndEMABlSer.getDataByCode(inputStockByCodeVO);
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        } catch (Exception e) {
            e.printStackTrace();
        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("日期");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("复权收盘指数");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("涨跌幅");

        for (int i = 0; i < stockVOS.size(); i++) {
            StockVO stockVO = stockVOS.get(i);
            series1.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(stockVO.getAdjClose())));
            String iOrD = stockVO.getIncreaseOrDecrease();
            iOrD = iOrD.substring(0, iOrD.length() - 1);
            series2.getData().add(new XYChart.Data(stockVO.getDate(),
                    Double.parseDouble(iOrD)));
        }

        ChuangyechartForPlate.getData().addAll(series1, series2);
        ChuangyechartForPlate.setCreateSymbols(false);
        ChuangyechartForPlate.setVisible(true);
    }

    public void setHushen300tableForPlate(String date) {
        ArrayList<StockVO> stockVOS = new ArrayList<>();
        try {
            stockVOS = plateBlSer.getRecommendStocks(StockPoolBl.MAINPLATE, date);
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }
        if (stockVOS == null) {
            System.out.println("找不到数据");
        } else {
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
            Hushen300tableForPlate.setItems(data);
            Hushen300tableForPlate.getColumns().addAll(codeCol, nameCol, openCol, closeCol, increaseOrDecreaseCol, volumeCol);
            Hushen300tableForPlate.setVisible(true);
        }

    }

    public void setZhongxiaotableForPlate(String date) {
        ArrayList<StockVO> stockVOS = new ArrayList<>();

        try {
            stockVOS = plateBlSer.getRecommendStocks(StockPoolBl.SMALLMIDDLEPLATE, date);
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }

        if (stockVOS == null) {
            System.out.println("找不到数据");
        } else {
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
            ZhongxiaotableForPlate.setItems(data);
            ZhongxiaotableForPlate.getColumns().addAll(codeCol, nameCol, openCol, closeCol, increaseOrDecreaseCol, volumeCol);
            ZhongxiaotableForPlate.setVisible(true);
        }

    }

    public void setChuangyetableForPlate(String date) {
        ArrayList<StockVO> stockVOS = new ArrayList<>();

        try {
            stockVOS = plateBlSer.getRecommendStocks(StockPoolBl.STARTUPPLATE, date);
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }

        if (stockVOS == null) {
            System.out.println("找不到数据");
        } else {
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
            ChuangyetableForPlate.setItems(data);
            ChuangyetableForPlate.getColumns().addAll(codeCol, nameCol, openCol, closeCol, increaseOrDecreaseCol, volumeCol);
            ChuangyetableForPlate.setVisible(true);
        }

    }


    public void BackToAllPlateButtonClicked() {
        BackToAllPlate.setVisible(false);
        Calendar calendar = Calendar.getInstance();
        Translate translate = new Translate();
        String date = translate.myToString(calendar);
//    StringBuffer sb=new StringBuffer();
//    String[] temp;
//    temp=TimeForPlate.getValue().toString().split("-");
//    sb.append(temp[1]);
//    sb.append("/");
//    sb.append(temp[2]);
//    sb.append("/");
//    sb.append(temp[0]);
//    date=sb.toString();
//    setTableViewForP(date);
    }

    //监听
    public void goToHushen300SpecificPlate() {
        SpecificPlateController.platename = "主板";
        SpecificPlate specificPlate = new SpecificPlate("主板");
        try {
            specificPlate.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToZhongxiaoSpecificPlate() {
        SpecificPlateController.platename = "中小板";
        SpecificPlate specificPlate = new SpecificPlate("中小板");
        try {
            specificPlate.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goToChuangyeSpecificPlate() {
        SpecificPlateController.platename = "创业板";
        SpecificPlate specificPlate = new SpecificPlate("创业板");
        try {
            specificPlate.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*----------------------------------------------------------板块---------PlateController----------------------------------------------------------------------------------------- */

    /*---------------------------------------------------------行业----------IndustryController----------------------------------------------------------------------------------------- */
    @FXML
    private Tab IndustryTab;
    @FXML
    private AnchorPane AnchorPaneForIndustry;
    @FXML
    private PieChart PieForIndustry;
    @FXML
    private TableView TableForIndustry;
    @FXML
    private TabPane TabPaneForIndustry;
    @FXML
    private Tab IncForIndustry;
    @FXML
    private TableView IncreaseRank1;
    @FXML
    private Tab DecForIndustry;
    @FXML
    private TableView DecreaseRank1;
    @FXML
    private Tab VolumeForIndustry;
    @FXML
    private TableView VolumeRank11;
    @FXML
    private Tab PotentialForIndustry;
    @FXML
    private TableView PotentialRank1;
    @FXML
    private TextField SearchIndustryText;
    @FXML
    private Button SearchButtonForIndustry;

    IndustryBlSer industryBlSer = new IndustryController();

    public void initializeWholeIndustry(String date) {
        setPieForIndustry(date);
        setTableForIndustry(date);
        setIncreaseRank1(date);
        setDecreaseRank1(date);
        setVolumeRank11(date);
        setPotentialRank1(date);
    }

    public void setPieForIndustry(String date) {

        StockPieVO totalIndustryPieVO = new StockPieVO();
        try {
            totalIndustryPieVO = industryBlSer.findTotalIndustryPieVO(date);

            ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                    new PieChart.Data("涨幅小于5%", totalIndustryPieVO.getDecStop()),
                    new PieChart.Data("跌幅<-5%", totalIndustryPieVO.getRateLessNeg5()),
                    new PieChart.Data("-5<=跌幅<0", totalIndustryPieVO.getRateFromNeg5ToZero()),
                    new PieChart.Data("0<=涨幅<5", totalIndustryPieVO.getRateFromZeroTo5()),
                    new PieChart.Data("涨幅>5%", totalIndustryPieVO.getRateMore5()),
                    new PieChart.Data("涨停", totalIndustryPieVO.getIncStop())
            );


            PieForIndustry.setData(pieData);
            PieForIndustry.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }


    }

    public void setTableForIndustry(String date) {
        ArrayList<IndustryVO> industryVOS = new ArrayList<>();
        try {
            industryVOS = industryBlSer.findIndustryInfoOneday(date);
            ObservableList<TotalIndustry> industryData = FXCollections.observableArrayList();
            for (int i = 0; i < industryVOS.size(); i++) {
                IndustryVO industryVO = industryVOS.get(i);
                industryData.add(i, new TotalIndustry(
                        industryVO.getIndustryName(), industryVO.getAverageOpen(),
                        industryVO.getAverageClose(), industryVO.getCompanyNum(),
                        industryVO.getIncreaseOrDecreaseRate(), industryVO.getIncreaseOrDecreaseMoney(),
                        industryVO.getTotalVolume(), industryVO.getIndustryScore()));
            }

            TableColumn industryNameCol = new TableColumn("行业名称");
            industryNameCol.setCellFactory(
                    new PropertyValueFactory<>("industryName")
            );

            TableColumn companyNumCol = new TableColumn("公司家数");
            companyNumCol.setCellFactory(
                    new PropertyValueFactory<>("companyNum")
            );

            TableColumn averageOpenCol = new TableColumn("平均开盘指数");
            averageOpenCol.setCellFactory(
                    new PropertyValueFactory<>("averageOpen")
            );

            TableColumn averageCloseCol = new TableColumn("平均收盘指数");
            averageCloseCol.setCellFactory(
                    new PropertyValueFactory<>("averageClose")
            );

            TableColumn increaseOrDecreaseRateCol = new TableColumn("涨跌幅");
            increaseOrDecreaseRateCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseRate")
            );

            TableColumn increaseOrDecreaseMoneyCol = new TableColumn("涨跌额");
            increaseOrDecreaseMoneyCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseMoney")
            );

            TableColumn totalVolumeCol = new TableColumn("总交易量");
            totalVolumeCol.setCellFactory(
                    new PropertyValueFactory<>("totalVolume")
            );

            TableColumn industryScoreCol = new TableColumn("行业潜力");
            industryScoreCol.setCellFactory(
                    new PropertyValueFactory<>("industryScore")
            );

            TableForIndustry.setItems(industryData);
            TableForIndustry.getColumns().addAll(industryNameCol, companyNumCol, averageOpenCol, averageCloseCol,
                    increaseOrDecreaseRateCol, increaseOrDecreaseMoneyCol, totalVolumeCol, industryScoreCol);
            TableForIndustry.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }

    }

    public void setIncreaseRank1(String date) {
        ArrayList<IndustryVO> industryVOS = new ArrayList<>();
        try {
            industryVOS = industryBlSer.findTotalIndustryMaxInRate(date);
            ObservableList<TotalIndustrySimple> industryData = FXCollections.observableArrayList();
            for (int i = 0; i < industryVOS.size(); i++) {
                IndustryVO industryVO = industryVOS.get(i);
                industryData.add(i, new TotalIndustrySimple(
                        industryVO.getIndustryName(), industryVO.getCompanyNum(),
                        industryVO.getIncreaseOrDecreaseRate(), industryVO.getIncreaseOrDecreaseMoney(),
                        industryVO.getTotalVolume(), industryVO.getIndustryScore()));
            }

            TableColumn industryNameCol = new TableColumn("行业名称");
            industryNameCol.setCellFactory(
                    new PropertyValueFactory<>("industryName")
            );

            TableColumn companyNumCol = new TableColumn("公司家数");
            companyNumCol.setCellFactory(
                    new PropertyValueFactory<>("companyNum")
            );

            TableColumn increaseOrDecreaseRateCol = new TableColumn("涨跌幅");
            increaseOrDecreaseRateCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseRate")
            );

            TableColumn increaseOrDecreaseMoneyCol = new TableColumn("涨跌额");
            increaseOrDecreaseMoneyCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseMoney")
            );

            TableColumn totalVolumeCol = new TableColumn("总交易量");
            totalVolumeCol.setCellFactory(
                    new PropertyValueFactory<>("totalVolume")
            );

            TableColumn industryScoreCol = new TableColumn("行业潜力");
            industryScoreCol.setCellFactory(
                    new PropertyValueFactory<>("industryScore")
            );

            IncreaseRank1.setItems(industryData);
            IncreaseRank1.getColumns().addAll(industryNameCol, companyNumCol,
                    increaseOrDecreaseRateCol, increaseOrDecreaseMoneyCol, totalVolumeCol, industryScoreCol);
            IncreaseRank1.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }


    }

    public void setDecreaseRank1(String date) {
        ArrayList<IndustryVO> industryVOS = new ArrayList<>();
        try {
            industryVOS = industryBlSer.findTotalIndustryMaxDeRate(date);
            ObservableList<TotalIndustrySimple> industryData = FXCollections.observableArrayList();
            for (int i = 0; i < industryVOS.size(); i++) {
                IndustryVO industryVO = industryVOS.get(i);
                industryData.add(i, new TotalIndustrySimple(
                        industryVO.getIndustryName(), industryVO.getCompanyNum(),
                        industryVO.getIncreaseOrDecreaseRate(), industryVO.getIncreaseOrDecreaseMoney(),
                        industryVO.getTotalVolume(), industryVO.getIndustryScore()));
            }

            TableColumn industryNameCol = new TableColumn("行业名称");
            industryNameCol.setCellFactory(
                    new PropertyValueFactory<>("industryName")
            );

            TableColumn companyNumCol = new TableColumn("公司家数");
            companyNumCol.setCellFactory(
                    new PropertyValueFactory<>("companyNum")
            );

            TableColumn increaseOrDecreaseRateCol = new TableColumn("涨跌幅");
            increaseOrDecreaseRateCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseRate")
            );

            TableColumn increaseOrDecreaseMoneyCol = new TableColumn("涨跌额");
            increaseOrDecreaseMoneyCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseMoney")
            );

            TableColumn totalVolumeCol = new TableColumn("总交易量");
            totalVolumeCol.setCellFactory(
                    new PropertyValueFactory<>("totalVolume")
            );

            TableColumn industryScoreCol = new TableColumn("行业潜力");
            industryScoreCol.setCellFactory(
                    new PropertyValueFactory<>("industryScore")
            );

            DecreaseRank1.setItems(industryData);
            DecreaseRank1.getColumns().addAll(industryNameCol, companyNumCol,
                    increaseOrDecreaseRateCol, increaseOrDecreaseMoneyCol, totalVolumeCol, industryScoreCol);
            DecreaseRank1.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }

    }

    public void setVolumeRank11(String date) {
        ArrayList<IndustryVO> industryVOS = new ArrayList<>();
        try {
            industryVOS = industryBlSer.findTotalIndustryMaxInVolume(date);
            ObservableList<TotalIndustrySimple> industryData = FXCollections.observableArrayList();
            for (int i = 0; i < industryVOS.size(); i++) {
                IndustryVO industryVO = industryVOS.get(i);
                industryData.add(i, new TotalIndustrySimple(
                        industryVO.getIndustryName(), industryVO.getCompanyNum(),
                        industryVO.getIncreaseOrDecreaseRate(), industryVO.getIncreaseOrDecreaseMoney(),
                        industryVO.getTotalVolume(), industryVO.getIndustryScore()));
            }

            TableColumn industryNameCol = new TableColumn("行业名称");
            industryNameCol.setCellFactory(
                    new PropertyValueFactory<>("industryName")
            );

            TableColumn companyNumCol = new TableColumn("公司家数");
            companyNumCol.setCellFactory(
                    new PropertyValueFactory<>("companyNum")
            );

            TableColumn increaseOrDecreaseRateCol = new TableColumn("涨跌幅");
            increaseOrDecreaseRateCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseRate")
            );

            TableColumn increaseOrDecreaseMoneyCol = new TableColumn("涨跌额");
            increaseOrDecreaseMoneyCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseMoney")
            );

            TableColumn totalVolumeCol = new TableColumn("总交易量");
            totalVolumeCol.setCellFactory(
                    new PropertyValueFactory<>("totalVolume")
            );

            TableColumn industryScoreCol = new TableColumn("行业潜力");
            industryScoreCol.setCellFactory(
                    new PropertyValueFactory<>("industryScore")
            );

            VolumeRank11.setItems(industryData);
            VolumeRank11.getColumns().addAll(industryNameCol, companyNumCol,
                    increaseOrDecreaseRateCol, increaseOrDecreaseMoneyCol, totalVolumeCol, industryScoreCol);
            VolumeRank11.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }

    }

    public void setPotentialRank1(String date) {
        ArrayList<IndustryVO> industryVOS = new ArrayList<>();
        try {
            industryVOS = industryBlSer.findTotalIndustryPotential(date);
            ObservableList<TotalIndustrySimple> industryData = FXCollections.observableArrayList();
            for (int i = 0; i < industryVOS.size(); i++) {
                IndustryVO industryVO = industryVOS.get(i);
                industryData.add(i, new TotalIndustrySimple(
                        industryVO.getIndustryName(), industryVO.getCompanyNum(),
                        industryVO.getIncreaseOrDecreaseRate(), industryVO.getIncreaseOrDecreaseMoney(),
                        industryVO.getTotalVolume(), industryVO.getIndustryScore()));
            }

            TableColumn industryNameCol = new TableColumn("行业名称");
            industryNameCol.setCellFactory(
                    new PropertyValueFactory<>("industryName")
            );

            TableColumn companyNumCol = new TableColumn("公司家数");
            companyNumCol.setCellFactory(
                    new PropertyValueFactory<>("companyNum")
            );

            TableColumn increaseOrDecreaseRateCol = new TableColumn("涨跌幅");
            increaseOrDecreaseRateCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseRate")
            );

            TableColumn increaseOrDecreaseMoneyCol = new TableColumn("涨跌额");
            increaseOrDecreaseMoneyCol.setCellFactory(
                    new PropertyValueFactory<>("increaseOrDecreaseMoney")
            );

            TableColumn totalVolumeCol = new TableColumn("总交易量");
            totalVolumeCol.setCellFactory(
                    new PropertyValueFactory<>("totalVolume")
            );

            TableColumn industryScoreCol = new TableColumn("行业潜力");
            industryScoreCol.setCellFactory(
                    new PropertyValueFactory<>("industryScore")
            );

            PotentialRank1.setItems(industryData);
            PotentialRank1.getColumns().addAll(industryNameCol, companyNumCol,
                    increaseOrDecreaseRateCol, increaseOrDecreaseMoneyCol, totalVolumeCol, industryScoreCol);
            PotentialRank1.setVisible(true);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("找不到数据");
        }

    }

    //监听
    public void searchIndustry() {
        try {
            String industryName = SearchIndustryText.getText().toString();
            SpecificIndustryController.industryname = industryName;
            SpecificIndustry specificIndustry = new SpecificIndustry();
            specificIndustry.start(new Stage());
        } catch (NullPointerException e) {
            System.out.println("用户未输入行业名称");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*--------------------------------------------------------行业-----------IndustryController----------------------------------------------------------------------------------------- */


    /*---------------------------------------------------------策略----------QuantitativeInvestmentController----------------------------------------------------------------------------------------- */
    @FXML
    private Tab QuantitativeInvestmentTab;
    @FXML
    private TabPane TabPaneForQ;
    @FXML
    private TableView TableViewForStrategy;
    @FXML
    private LineChart LineChartForStrategy;
    @FXML
    private TableView StockPoolForStrategy;
    @FXML
    private AreaChart AreaChartForExcessReturn;
    @FXML
    private AreaChart AreaChartForWinRate;
    @FXML
    private TableView StockPoolForER;
    @FXML
    private BarChart BarChartForRelativeIndexReturn;
    @FXML
    private TableView StockPoolForIR;
    @FXML
    private TextField HoldingPeriodFieldForQ;
    @FXML
    private TextField FormationFieldForQ;
    @FXML
    private DatePicker StartTimeForQ;
    @FXML
    private DatePicker EndTimeForQ;
    @FXML
    private ChoiceBox PlateChoiceForQ;
    @FXML
    private Button QueryButtonForQ;
    @FXML
    private ChoiceBox FormationChoiceBoxForQ;
    @FXML
    private Label ErrorTextForQ;
    @FXML
    private ChoiceBox StrategyChoiceBoxForQ;
    @FXML
    private Button FormationBasedButton;
    @FXML
    private Button HoldingPeriodBasedButton;
    @FXML
    private TableView TableViewForRelativeIndexReturn;
    @FXML
    private AnchorPane QPane;
    @FXML
    private TableView EvaluationTable;

    private StrategyBlSer sser;
    private PlateBlSer pser;


    int strategyType;

    public void FormationBasedButtonOnClicked() {
        strategyType = 0;
        FormationBasedButton.setStyle("-fx-background-color: #0121e3");
        HoldingPeriodBasedButton.setStyle("-fx-background-color: #88777E");

        StockPoolBl stockPoolBl = null;
        ArrayList<StockVO> stockVOS = null;
        ArrayList<String> stockName = null;
        switch (PlateChoiceForQ.getSelectionModel().getSelectedIndex()) {
            case 0:
                stockPoolBl = StockPoolBl.ALL;
                break;
            case 1:
                stockPoolBl = StockPoolBl.SELECTPLATE;
                break;
            case 2:
                stockPoolBl = StockPoolBl.MAINPLATE;
                break;
            case 3:
                stockPoolBl = StockPoolBl.SMALLMIDDLEPLATE;
                break;
            case 4:
                stockPoolBl = StockPoolBl.STARTUPPLATE;
                break;
        }
        stockVOS = getStockPool(PlateChoiceForQ.getSelectionModel().getSelectedIndex());
        stockName = new ArrayList<String>();
        for (int i = 0; i < stockVOS.size(); i++) {
            stockName.add(stockVOS.get(i).getCode());
        }
        setAreaChartForExcessReturn(stockPoolBl, stockName);
        setAreaChartForWinRate(stockPoolBl, stockName);
    }

    public void HoldingPeriodBasedButtonOnClicked() {
        strategyType = 1;
        HoldingPeriodBasedButton.setStyle("-fx-background-color: #0121e3");
        FormationBasedButton.setStyle("-fx-background-color: #88777E");

        StockPoolBl stockPoolBl = null;
        ArrayList<StockVO> stockVOS = null;
        ArrayList<String> stockName = null;
        switch (PlateChoiceForQ.getSelectionModel().getSelectedIndex()) {
            case 0:
                stockPoolBl = StockPoolBl.ALL;
                break;
            case 1:
                stockPoolBl = StockPoolBl.SELECTPLATE;
                break;
            case 2:
                stockPoolBl = StockPoolBl.MAINPLATE;
                break;
            case 3:
                stockPoolBl = StockPoolBl.SMALLMIDDLEPLATE;
                break;
            case 4:
                stockPoolBl = StockPoolBl.STARTUPPLATE;
                break;
        }
        stockVOS = getStockPool(PlateChoiceForQ.getSelectionModel().getSelectedIndex());
        stockName = new ArrayList<String>();
        for (int i = 0; i < stockVOS.size(); i++) {
            stockName.add(stockVOS.get(i).getCode());
        }
        setAreaChartForExcessReturn(stockPoolBl, stockName);
        setAreaChartForWinRate(stockPoolBl, stockName);
    }

    public void StrategyChoiceBoxForQOnClicked() {
    }

    public void ExcessReturnPaneOnClicked() {
        FormationFieldForQ.setVisible(true);
        FormationChoiceBoxForQ.setVisible(false);
    }

    public void RelativeIndexReturnPaneOnClicked() {
        FormationFieldForQ.setVisible(true);
        FormationChoiceBoxForQ.setVisible(false);
    }

    public void QueryForQ() {
        this.sser = new StrategyController();
        this.pser = new PlateController();
        ErrorTextForQ.setVisible(false);
        if (!StartTimeForQ.getValue().isBefore(EndTimeForQ.getValue())) {
            ErrorTextForQ.setText("输入格式不正确");
            ErrorTextForQ.setVisible(true);
        } else {
            StockPoolBl stockPoolBl = null;
            ArrayList<StockVO> stockVOS = null;
            ArrayList<String> stockName = null;
            try {
                stockPoolBl = StockPoolBl.ALL;
                switch (PlateChoiceForQ.getSelectionModel().getSelectedIndex()) {
                    case 0:
                        stockPoolBl = StockPoolBl.ALL;
                        break;
                    case 1:
                        stockPoolBl = StockPoolBl.SELECTPLATE;
                        break;
                    case 2:
                        stockPoolBl = StockPoolBl.MAINPLATE;
                        break;
                    case 3:
                        stockPoolBl = StockPoolBl.SMALLMIDDLEPLATE;
                        break;
                    case 4:
                        stockPoolBl = StockPoolBl.STARTUPPLATE;
                        break;
                }
                stockVOS = getStockPool(PlateChoiceForQ.getSelectionModel().getSelectedIndex());
                stockName = new ArrayList<String>();
                for (int i = 0; i < stockVOS.size(); i++) {
                    stockName.add(stockVOS.get(i).getCode());
                }
            } catch (NullPointerException e) {
                ErrorTextForQ.setVisible(true);
                ErrorTextForQ.setText("请选择股票池");
            }

            setLineChartForStrategy(stockPoolBl, stockName);
            setBarChartForRelativeIndexReturn(stockPoolBl, stockName);
            setTableViewForStrategy(stockPoolBl, stockName);
            setTableViewForRelativeIndexReturn(stockPoolBl, stockName);
            setEvaluationTable(stockPoolBl, stockName);
            setStockPoolForStrategy(stockVOS);
            setStockPoolForIR(stockVOS);
            setStockPoolForER(stockVOS);
        }
    }

    private void setEvaluationTable(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        sser = new StrategyController();
        EvaluationTable.getColumns().remove(0, EvaluationTable.getColumns().size());

        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb = new StringBuffer();
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        InputStrategyVO inputStrategyVO = new InputStrategyVO();
        StrategyEvaluateVO resultVO = new StrategyEvaluateVO();
        StrategyVO strategyVO = new StrategyVO();
        strategyVO = new StrategyVO();
        inputStrategyVO = new InputStrategyVO();
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());

        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }

        try {
            strategyVO = this.sser.getStrategy(inputStrategyVO);
            resultVO = strategyVO.getStrategyEvaluateVO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TableColumn pointOne = new TableColumn("收益");
        TableColumn pointTwo = new TableColumn("抗风险");
        TableColumn pointThree = new TableColumn("稳定性");
        TableColumn pointFour = new TableColumn("风险收益");
        TableColumn pointFive = new TableColumn("收益空间");
        TableColumn allPoint = new TableColumn("总评");

        ObservableList<tableDataForMomentStaticalVariable> data = FXCollections.observableArrayList();
        data.add(new tableDataForMomentStaticalVariable(String.valueOf(resultVO.getYield()), String.valueOf(resultVO.getResistRisk()), String.valueOf(resultVO.getStable()), String.valueOf(resultVO.getRiskYield()), String.valueOf(resultVO.getYieldSpace()), String.valueOf(resultVO.getGeneralScore())));

        pointOne.setMinWidth(60);
        pointTwo.setMinWidth(60);
        pointThree.setMinWidth(60);
        pointFour.setMinWidth(60);
        pointFive.setMinWidth(60);
        allPoint.setMinWidth(60);

        pointOne.setCellValueFactory(
                new PropertyValueFactory<>("yearYieldRate")
        );
        pointTwo.setCellValueFactory(
                new PropertyValueFactory<>("benchmarkYearYieldRate")
        );
        pointThree.setCellValueFactory(
                new PropertyValueFactory<>("alpha")
        );
        pointFour.setCellValueFactory(
                new PropertyValueFactory<>("beta")
        );
        pointFive.setCellValueFactory(
                new PropertyValueFactory<>("sharpeRatio")
        );
        allPoint.setCellValueFactory(
                new PropertyValueFactory<>("maxWithdraw")
        );
        EvaluationTable.setItems(data);
        EvaluationTable.getColumns().addAll(pointOne, pointTwo, pointThree, pointFour, pointFive, allPoint);
        EvaluationTable.setVisible(true);
    }


    private void setLineChartForStrategy(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        sser = new StrategyController();

        LineChartForStrategy.getData().remove(0, LineChartForStrategy.getData().size());

        StrategyVO strategyVO = null;
        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb = new StringBuffer();
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        InputStrategyVO inputStrategyVO;
        strategyVO = new StrategyVO();
        inputStrategyVO = new InputStrategyVO();
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());

        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }

        try {
            strategyVO = sser.getStrategy(inputStrategyVO);
            StrategyGraphVO strategyGraphVO = new StrategyGraphVO();
            strategyGraphVO = strategyVO.getStrategyGraphVO();
            XYChart.Series seriesOne = new XYChart.Series();
            XYChart.Series seriesTwo = new XYChart.Series();
            String tem = new String();
            for (int i = 0; i < strategyGraphVO.getBenchmarkYield().size(); i++) {
                tem = strategyGraphVO.getBenchmarkYield().get(i);
                tem = tem.substring(0, tem.length() - 1);
                seriesOne.getData().add(new XYChart.Data(strategyGraphVO.getDate().get(i), Double.parseDouble(tem)));
            }
            for (int i = 0; i < strategyGraphVO.getStrategyYield().size(); i++) {
                tem = strategyGraphVO.getStrategyYield().get(i);
                tem = tem.substring(0, tem.length() - 1);
                seriesTwo.getData().add(new XYChart.Data(strategyGraphVO.getDate().get(i), Double.parseDouble(tem)));
            }
            if (seriesOne.getData().size() < 30) {
                LineChartForStrategy.setCreateSymbols(true);
            } else {
                LineChartForStrategy.setCreateSymbols(false);
            }
            seriesOne.setName("基准累计收益率");
            seriesTwo.setName("策略累计收益率");
            LineChartForStrategy.getData().add(seriesOne);
            LineChartForStrategy.getData().add(seriesTwo);
            LineChartForStrategy.setVisible(true);
        } catch (NullPointerException e) {
            ErrorTextForQ.setText("找不到数据");
            ErrorTextForQ.setVisible(true);
            TableViewForStrategy.setVisible(false);
            TableViewForRelativeIndexReturn.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAreaChartForExcessReturn(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        sser = new StrategyController();

        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        sb = new StringBuffer();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        RelationGraphVO relationGraphVO = new RelationGraphVO();
        InputStrategyVO inputStrategyVO = new InputStrategyVO();
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());

        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }
        try {
            relationGraphVO = sser.getRelationGraphData(inputStrategyVO, strategyType);
            XYChart.Series series = new XYChart.Series();
            String tem = new String();
            for (int i = 0; i < relationGraphVO.getPeriod().size(); i++) {
                tem = relationGraphVO.getOverYield().get(i);
                tem = tem.substring(0, tem.length() - 1);
                series.getData().add(new XYChart.Data(relationGraphVO.getPeriod().get(i), Double.parseDouble(tem)));
            }
            if (series.getData().size() < 30) {
                AreaChartForExcessReturn.setCreateSymbols(true);
            } else {
                AreaChartForExcessReturn.setCreateSymbols(false);
            }

            series.setName("超额收益");
            AreaChartForExcessReturn.setData(FXCollections.observableArrayList(series));
            AreaChartForExcessReturn.setVisible(true);
        } catch (NullPointerException e) {
            ErrorTextForQ.setText("找不到数据");
            ErrorTextForQ.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setAreaChartForWinRate(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        sser = new StrategyController();

        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        sb = new StringBuffer();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        RelationGraphVO relationGraphVO = new RelationGraphVO();
        InputStrategyVO inputStrategyVO = new InputStrategyVO();
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());

        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }
        try {
            relationGraphVO = sser.getRelationGraphData(inputStrategyVO, strategyType);
            XYChart.Series series = new XYChart.Series();
            String tem = new String();
            for (int i = 0; i < relationGraphVO.getPeriod().size(); i++) {
                tem = relationGraphVO.getWinRate().get(i);
                tem = tem.substring(0, tem.length() - 1);
                series.getData().add(new XYChart.Data(relationGraphVO.getPeriod().get(i), Double.parseDouble(tem)));
            }
            if (series.getData().size() < 30) {
                AreaChartForWinRate.setCreateSymbols(true);
            } else {
                AreaChartForWinRate.setCreateSymbols(false);
            }
            series.setName("策略收益");
            AreaChartForWinRate.setData(FXCollections.observableArrayList(series));
            AreaChartForWinRate.setVisible(true);
        } catch (NullPointerException e) {
            ErrorTextForQ.setText("找不到数据");
            ErrorTextForQ.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBarChartForRelativeIndexReturn(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        sser = new StrategyController();
        BarChartForRelativeIndexReturn.getData().remove(0, BarChartForRelativeIndexReturn.getData().size());

        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        sb = new StringBuffer();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        YieldRateDistributionVO yieldRateDistributionVO = new YieldRateDistributionVO();
        InputStrategyVO inputStrategyVO = new InputStrategyVO();
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());


        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }

        try {
            yieldRateDistributionVO = sser.getYieldRateDistribution(inputStrategyVO);
            BarChartForRelativeIndexReturn.getData().remove(0, BarChartForRelativeIndexReturn.getData().size());
            XYChart.Series seriesOne = new XYChart.Series();
            XYChart.Series seriesTwo = new XYChart.Series();
            for (int i = 0; i < yieldRateDistributionVO.getYieldRate().size(); i++) {
                seriesOne.getData().add(new XYChart.Data(yieldRateDistributionVO.getYieldRate().get(i), Double.parseDouble(yieldRateDistributionVO.getNegativeFrequency().get(i))));
                seriesTwo.getData().add(new XYChart.Data(yieldRateDistributionVO.getYieldRate().get(i), Double.parseDouble(yieldRateDistributionVO.getPositiveFrequency().get(i))));
            }
            seriesOne.setName("负收益次数");
            seriesTwo.setName("正收益次数");
            BarChartForRelativeIndexReturn.getData().addAll(seriesTwo, seriesOne);
            BarChartForRelativeIndexReturn.setVisible(true);
        } catch (NullPointerException e) {
            ErrorTextForQ.setText("找不到数据");
            ErrorTextForQ.setVisible(true);
            TableViewForStrategy.setVisible(false);
            TableViewForRelativeIndexReturn.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setTableViewForStrategy(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        sb = new StringBuffer();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        TableViewForStrategy.getColumns().remove(0, TableViewForStrategy.getColumns().size());
        InputStrategyVO inputStrategyVO = new InputStrategyVO();
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }

        StaticalVariableVO resultVO = new StaticalVariableVO();
        StrategyVO strategyVO = new StrategyVO();

        try {
            strategyVO = sser.getStrategy(inputStrategyVO);
            resultVO = strategyVO.getStaticalVariableVO();
        } catch (Exception e) {
            e.printStackTrace();
        }
        TableColumn yearYieldRate = new TableColumn("年化收益率");
        TableColumn benchmarkYearYieldRate = new TableColumn("基准年化收益率");
        TableColumn alpha = new TableColumn("阿尔法");
        TableColumn beta = new TableColumn("贝塔");
        TableColumn sharpeRatio = new TableColumn("夏普比率");
        TableColumn maxWithdraw = new TableColumn("最大回撤");

        ObservableList<tableDataForMomentStaticalVariable> tableDataForMomentStaticalVariables = FXCollections.observableArrayList();
        tableDataForMomentStaticalVariables.add(new tableDataForMomentStaticalVariable(resultVO.getYearYieldRate(), resultVO.getBenchmarkYearYieldRate(), resultVO.getAlpha(), resultVO.getBeta(), resultVO.getSharpeRatio(), resultVO.getMaxWithdraw()));

        yearYieldRate.setMinWidth(130);
        benchmarkYearYieldRate.setMinWidth(150);
        alpha.setMinWidth(100);
        beta.setMinWidth(100);
        sharpeRatio.setMinWidth(120);
        maxWithdraw.setMinWidth(120);

        yearYieldRate.setCellValueFactory(
                new PropertyValueFactory<>("yearYieldRate")
        );
        benchmarkYearYieldRate.setCellValueFactory(
                new PropertyValueFactory<>("benchmarkYearYieldRate")
        );
        alpha.setCellValueFactory(
                new PropertyValueFactory<>("alpha")
        );
        beta.setCellValueFactory(
                new PropertyValueFactory<>("beta")
        );
        sharpeRatio.setCellValueFactory(
                new PropertyValueFactory<>("sharpeRatio")
        );
        maxWithdraw.setCellValueFactory(
                new PropertyValueFactory<>("maxWithdraw")
        );
        TableViewForStrategy.setItems(tableDataForMomentStaticalVariables);
        TableViewForStrategy.getColumns().addAll(yearYieldRate, benchmarkYearYieldRate, alpha, beta, sharpeRatio, maxWithdraw);
        TableViewForStrategy.setVisible(true);
    }

    private void setTableViewForRelativeIndexReturn(StockPoolBl stockPoolBl, ArrayList<String> stockCodes) {
        String startDate;
        String endDate;
        StringBuffer sb = new StringBuffer();
        String[] temp;
        temp = StartTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        startDate = sb.toString();
        sb = new StringBuffer();
        temp = EndTimeForQ.getValue().toString().split("-");
        sb.append(temp[1]);
        sb.append("/");
        sb.append(temp[2]);
        sb.append("/");
        sb.append(temp[0]);
        endDate = sb.toString();

        TableViewForRelativeIndexReturn.getColumns().remove(0, TableViewForRelativeIndexReturn.getColumns().size());
        InputStrategyVO inputStrategyVO = new InputStrategyVO();
        inputStrategyVO.setFormationPeriod(FormationFieldForQ.getText());
        inputStrategyVO.setHoldingPeriod(HoldingPeriodFieldForQ.getText());
        inputStrategyVO.setStart(startDate);
        inputStrategyVO.setEnd(endDate);
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 0) {
            inputStrategyVO.setType(StrategyType.MOMENT);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 1) {
            inputStrategyVO.setType(StrategyType.AVERAGE);
        }
        if (StrategyChoiceBoxForQ.getSelectionModel().getSelectedIndex() == 2) {
            inputStrategyVO.setType(StrategyType.MA);
        }
        inputStrategyVO.setStockPoolBl(stockPoolBl);
        if (stockPoolBl == StockPoolBl.SELECTPLATE) {
            inputStrategyVO.setStockNameOrCode(stockCodes);
        } else {
            inputStrategyVO.setStockNameOrCode(new ArrayList<String>());
        }

        YieldRateDistributionVO yieldRateDistributionVO = new YieldRateDistributionVO();

        try {
            yieldRateDistributionVO = sser.getYieldRateDistribution(inputStrategyVO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TableColumn pos = new TableColumn("正收益周期数");
        TableColumn neg = new TableColumn("负收益周期数");
        TableColumn winRate = new TableColumn("赢率");
        ObservableList<tableDataForStockPool> data = FXCollections.observableArrayList();
        data.add(new tableDataForStockPool(yieldRateDistributionVO.getPositiveYieldNum(), yieldRateDistributionVO.getNegativeYieldNum(), yieldRateDistributionVO.getWinRate()));

        pos.setMinWidth(120);
        pos.setCellValueFactory(
                new PropertyValueFactory<>("stockName")
        );
        neg.setMinWidth(120);
        neg.setCellValueFactory(
                new PropertyValueFactory<>("stockCode")
        );
        winRate.setMinWidth(120);
        winRate.setCellValueFactory(
                new PropertyValueFactory<>("plateInfo")
        );
        TableViewForRelativeIndexReturn.getColumns().addAll(pos, neg, winRate);
        TableViewForRelativeIndexReturn.setItems(data);
        TableViewForRelativeIndexReturn.setVisible(true);
    }

    private ArrayList<StockVO> getStockPool(int index) {
        this.plateBlSer = new PlateController();
        ArrayList<StockVO> resultVO = new ArrayList<StockVO>();
        try {
            switch (index) {
                case 0:
                    OnePlateVO onePlateVO = new OnePlateVO();
                    onePlateVO = this.plateBlSer.getOnePlateInfo(StockPoolBl.ALL);
                    for (int i = 0; i < onePlateVO.getStockVOS().size(); i++) {
                        resultVO.add(onePlateVO.getStockVOS().get(i));
                    }
                    break;
                case 1:
                    SelfSelectStockVO selfSelectStockVO = new SelfSelectStockVO();
                    selfSelectStockVO = this.user.getSelfSelectStock(userVO.getAccount());
                    for (int i = 0; i < selfSelectStockVO.getName().size(); i++) {
                        resultVO.add(new StockVO());
                        resultVO.get(i).setName(selfSelectStockVO.getName().get(i));
                        resultVO.get(i).setCode(selfSelectStockVO.getCode().get(i));
                        resultVO.get(i).setPlate(selfSelectStockVO.getMarket().get(i));
                    }
                    break;
                case 2:
                    OnePlateVO onePlateVO2 = new OnePlateVO();
                    onePlateVO2 = this.plateBlSer.getOnePlateInfo(StockPoolBl.MAINPLATE);
                    for (int i = 0; i < onePlateVO2.getStockVOS().size(); i++) {
                        resultVO.add(onePlateVO2.getStockVOS().get(i));
                    }
                    break;
                case 3:
                    OnePlateVO onePlateVO3 = new OnePlateVO();
                    onePlateVO3 = this.plateBlSer.getOnePlateInfo(StockPoolBl.SMALLMIDDLEPLATE);
                    for (int i = 0; i < onePlateVO3.getStockVOS().size(); i++) {
                        resultVO.add(onePlateVO3.getStockVOS().get(i));
                    }
                    break;
                case 4:
                    OnePlateVO onePlateVO6 = new OnePlateVO();
                    onePlateVO6 = this.plateBlSer.getOnePlateInfo(StockPoolBl.STARTUPPLATE);
                    for (int i = 0; i < onePlateVO6.getStockVOS().size(); i++) {
                        resultVO.add(onePlateVO6.getStockVOS().get(i));
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVO;
    }

    private void setStockPoolForStrategy(ArrayList<StockVO> data) {
        StockPoolForStrategy.getColumns().remove(0, StockPoolForStrategy.getColumns().size());
        TableColumn stockName = new TableColumn("股票名称");
        TableColumn stockCode = new TableColumn("股票代码");
        TableColumn plateInfo = new TableColumn("板块信息");
        ObservableList<tableDataForStockPool> tableDataForMS = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            tableDataForMS.add(i, new tableDataForStockPool(data.get(i).getName(), data.get(i).getCode(), data.get(i).getPlate()));
        }
        stockCode.setMinWidth(85);
        stockName.setMinWidth(85);
        plateInfo.setMinWidth(85);
        stockName.setCellValueFactory(
                new PropertyValueFactory<>("stockName")
        );
        stockCode.setCellValueFactory(
                new PropertyValueFactory<>("stockCode")
        );
        plateInfo.setCellValueFactory(
                new PropertyValueFactory<>("plateInfo")
        );
        StockPoolForStrategy.setItems(tableDataForMS);
        StockPoolForStrategy.getColumns().addAll(stockCode, stockName, plateInfo);
        StockPoolForStrategy.setVisible(true);
    }

    private void setStockPoolForER(ArrayList<StockVO> data) {
        StockPoolForER.getColumns().remove(0, StockPoolForER.getColumns().size());
        TableColumn stockName = new TableColumn("股票名称");
        TableColumn stockCode = new TableColumn("股票代码");
        TableColumn plateInfo = new TableColumn("板块信息");
        ObservableList<tableDataForStockPool> tableDataForMS = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            tableDataForMS.add(i, new tableDataForStockPool(data.get(i).getName(), data.get(i).getCode(), data.get(i).getPlate()));
        }
        stockCode.setMinWidth(85);
        stockName.setMinWidth(85);
        plateInfo.setMinWidth(85);
        stockName.setCellValueFactory(
                new PropertyValueFactory<>("stockName")
        );
        stockCode.setCellValueFactory(
                new PropertyValueFactory<>("stockCode")
        );
        plateInfo.setCellValueFactory(
                new PropertyValueFactory<>("plateInfo")
        );
        StockPoolForER.setItems(tableDataForMS);
        StockPoolForER.getColumns().addAll(stockCode, stockName, plateInfo);
        StockPoolForER.setVisible(true);
    }

    private void setStockPoolForIR(ArrayList<StockVO> data) {
        StockPoolForIR.getColumns().remove(0, StockPoolForIR.getColumns().size());
        TableColumn stockName = new TableColumn("股票名称");
        TableColumn stockCode = new TableColumn("股票代码");
        TableColumn plateInfo = new TableColumn("板块信息");
        ObservableList<tableDataForStockPool> tableDataForMS = FXCollections.observableArrayList();
        for (int i = 0; i < data.size(); i++) {
            tableDataForMS.add(i, new tableDataForStockPool(data.get(i).getName(), data.get(i).getCode(), data.get(i).getPlate()));
        }
        stockCode.setMinWidth(85);
        stockName.setMinWidth(85);
        plateInfo.setMinWidth(85);
        stockName.setCellValueFactory(
                new PropertyValueFactory<>("stockName")
        );
        stockCode.setCellValueFactory(
                new PropertyValueFactory<>("stockCode")
        );
        plateInfo.setCellValueFactory(
                new PropertyValueFactory<>("plateInfo")
        );
        StockPoolForIR.setItems(tableDataForMS);
        StockPoolForIR.getColumns().addAll(stockCode, stockName, plateInfo);
        StockPoolForIR.setVisible(true);
    }


/*-------------------------------------------------------策略------------QuantitativeInvestmentController----------------------------------------------------------------------------------------- */

    /*---------------------------------------------------------用户----------UserController-------------------------------------------------*/
    @FXML
    private Tab SelfSelectStockTab;
    @FXML
    private Button AnalyzeButton;
    @FXML
    private TableView StockPoolForSelfSelect;//个人股票池表
    @FXML
    private Label ErrorTextForS;

    @FXML
    private TabPane TabPaneForUser;//整体的选择

    //我的股票
    @FXML
    private Tab MyStockTabForUser;

    //历史交易
    @FXML
    private Tab HistoryTabForUser;
    @FXML
    private TableView HistoryTableForUser;//用户历史交易表格

    //模拟盈亏
    @FXML
    private Tab ImitateTabForUser;

    //我的收藏
    @FXML
    private Tab MyCollectTabForUser;

    //我的评论
    @FXML
    private Tab MyCommentTabForUser;

    //个人信息
    @FXML
    private Tab SelfInfoTabForUser;

    private UserBlSer user;

    public void AnalyzeButtonClicked() {
        MainTabPane.getSelectionModel().select(QuantitativeInvestmentTab);
        if (setStockPoolForSelfSelect().size() < 100) {
            ErrorTextForS.setText("股票池数量不足100");
            ErrorTextForS.setVisible(true);
            return;
        } else {
            setStockPoolForStrategy(setStockPoolForSelfSelect());
            setStockPoolForER(setStockPoolForSelfSelect());
            setStockPoolForIR(setStockPoolForSelfSelect());
        }
    }

    private ArrayList<StockVO> setStockPoolForSelfSelect() {
        StockPoolForSelfSelect.getColumns().remove(0, StockPoolForSelfSelect.getColumns().size());
        this.user = new UserController();
        ArrayList<StockVO> data = new ArrayList<StockVO>();
        try {
            SelfSelectStockVO selfSelectStockVO = user.getSelfSelectStock(userVO.getAccount());
            for (int i = 0; i < selfSelectStockVO.getName().size(); i++) {
                data.add(new StockVO());
                data.get(i).setName(selfSelectStockVO.getName().get(i));
                data.get(i).setCode(selfSelectStockVO.getCode().get(i));
                data.get(i).setPlate(selfSelectStockVO.getMarket().get(i));
            }
            TableColumn stockName = new TableColumn("股票名称");
            TableColumn stockCode = new TableColumn("股票代码");
            TableColumn plateInfo = new TableColumn("板块信息");
            ObservableList<tableDataForStockPool> tableDataForMS = FXCollections.observableArrayList();
            for (int i = 0; i < data.size(); i++) {
                tableDataForMS.add(i, new tableDataForStockPool(data.get(i).getName(), data.get(i).getCode(), data.get(i).getPlate()));
            }
            stockCode.setMinWidth(120);
            stockName.setMinWidth(120);
            plateInfo.setMinWidth(120);
            stockName.setCellValueFactory(
                    new PropertyValueFactory<>("stockName")
            );
            stockCode.setCellValueFactory(
                    new PropertyValueFactory<>("stockCode")
            );
            plateInfo.setCellValueFactory(
                    new PropertyValueFactory<>("plateInfo")
            );
            MenuItem menuItem1 = new MenuItem("删除");
            StockPoolForSelfSelect.setContextMenu(new ContextMenu(menuItem1));
            menuItem1.setOnAction(event -> {
//                  Object object=TableViewForMarketSituation.getSelectionModel().getSelectedItem();
                        UserBlSer userBlSer = new UserController();
                        try {
                            Object object1 = StockPoolForSelfSelect.getSelectionModel().getSelectedItem();
                            userBlSer.deleteOneSelfStock(userVO.getAccount(), ((tableDataForStockPool) object1).getStockCode());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
            );
            StockPoolForSelfSelect.setItems(tableDataForMS);
            StockPoolForSelfSelect.getColumns().addAll(stockCode, stockName, plateInfo);
            StockPoolForSelfSelect.setVisible(true);
        } catch (NullPointerException e) {
            ErrorTextForS.setVisible(true);
            ErrorTextForS.setText("没有数据");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

/*-------------------------------------------------------用户------------UserController-------------------------------------------------*/
}
