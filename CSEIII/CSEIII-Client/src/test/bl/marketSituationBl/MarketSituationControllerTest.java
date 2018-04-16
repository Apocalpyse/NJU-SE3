package test.bl.marketSituationBl; 

import VO.MarketSituationVO;
import VO.StockVO;
import bl.marketSituationBl.MarketSituationController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** 
* MarketSituationEventController Tester.
* 
* @author <Authors name> 
* @since <pre>���� 16, 2017</pre> 
* @version 1.0 
*/ 
public class MarketSituationControllerTest {
    MarketSituationController marketSituationController;
    public MarketSituationControllerTest(){
        ClientRunner cr=new ClientRunner();
        marketSituationController =new MarketSituationController();
    }

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getMarketSituation(String date) 
* 
*/ 
@Test
public void testGetMarketSituation() throws Exception { 
//TODO: Test goes here...
    MarketSituationVO marketSituationVO=marketSituationController.getMarketSituation("04/01/2014");
    assertEquals("4/1/14", marketSituationVO.getDate());
    assertEquals("1766584900",marketSituationVO.getTotalVolume());
} 

/** 
* 
* Method: getIncreaseList(String date) 
* 
*/ 
@Test
public void testGetIncreaseList() throws Exception { 
//TODO: Test goes here...
    ArrayList<StockVO> stockVOS=marketSituationController.getIncreaseList("4/1/14");
    boolean isRight=true;
    for (int i = 1; i < stockVOS.size(); i++) {
        StockVO stockVO1=stockVOS.get(i-1);
        StockVO stockVO2=stockVOS.get(i);
        double iOrD1=Double.parseDouble(stockVO1.getIncreaseOrDecrease());
        double iOrD2=Double.parseDouble(stockVO2.getIncreaseOrDecrease());
        if(iOrD1<iOrD2){
            isRight=false;
        }

    }
    assertEquals(true,isRight);

} 

/** 
* 
* Method: getDecreaseList(String date) 
* 
*/ 
@Test
public void testGetDecreaseList() throws Exception { 
//TODO: Test goes here...
    ArrayList<StockVO> stockVOS=marketSituationController.getDecreaseList("4/1/14");
    boolean isRight=true;
    for (int i = 1; i < stockVOS.size(); i++) {
        StockVO stockVO1=stockVOS.get(i-1);
        StockVO stockVO2=stockVOS.get(i);
        double iOrD1=Double.parseDouble(stockVO1.getIncreaseOrDecrease());
        double iOrD2=Double.parseDouble(stockVO2.getIncreaseOrDecrease());
        if(iOrD1>iOrD2){
            isRight=false;
        }

    }
    assertEquals(true,isRight);

} 

/** 
* 
* Method: getVolumeList(String date) 
* 
*/ 
@Test
public void testGetVolumeList() throws Exception { 
//TODO: Test goes here...
    ArrayList<StockVO> stockVOS=marketSituationController.getVolumeList("4/1/14");
    boolean isRight=true;
    for (int i = 1; i < stockVOS.size(); i++) {
        StockVO stockVO1=stockVOS.get(i-1);
        StockVO stockVO2=stockVOS.get(i);
        double iOrD1=Double.parseDouble(stockVO1.getVolume());
        double iOrD2=Double.parseDouble(stockVO2.getVolume());
        if(iOrD1<iOrD2){
            isRight=false;
        }

    }
    assertEquals(true,isRight);
}

} 
