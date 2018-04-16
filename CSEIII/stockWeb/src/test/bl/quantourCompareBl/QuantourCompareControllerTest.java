package test.bl.quantourCompareBl; 

import VO.*;
import bl.quantourCompareBl.QuantourCompareController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** 
* KAndEMAEventController Tester.
* 
* @author <Authors name> 
* @since <pre>���� 16, 2017</pre> 
* @version 1.0 
*/ 
public class QuantourCompareControllerTest {
    QuantourCompareController quantourCompareController;
    public QuantourCompareControllerTest(){
        ClientRunner cr=new ClientRunner();
        quantourCompareController=new QuantourCompareController();
    }

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: quantourTableCompareByCode(ArrayList<InputStockByCodeVO> stockvo) 
* 
*/ 
@Test
public void testQuantourTableCompareByCode() throws Exception { 
//TODO: Test goes here...
    InputStockByCodeVO inputStockByCodeVO1=new InputStockByCodeVO("04/01/2014","04/03/2014","000001");
    InputStockByCodeVO inputStockByCodeVO2=new InputStockByCodeVO("04/01/2014","04/03/2014","000010");
    ArrayList<InputStockByCodeVO> inputStockByCodeVOS=new ArrayList<InputStockByCodeVO>();
    inputStockByCodeVOS.add(inputStockByCodeVO1);
    inputStockByCodeVOS.add(inputStockByCodeVO2);
    ArrayList<StockCompareTotalVO> stockCompareTotalVOS=quantourCompareController.quantourTableCompareByCode(inputStockByCodeVOS);
    StockCompareTotalVO stockCompareTotalVO1=stockCompareTotalVOS.get(0);
    StockCompareTotalVO stockCompareTotalVO2=stockCompareTotalVOS.get(1);
    assertEquals(2,stockCompareTotalVOS.size());
    assertEquals("11.02",stockCompareTotalVO1.getHigh());
    assertEquals("10.7",stockCompareTotalVO1.getLow());
    assertEquals("0.00009",stockCompareTotalVO1.getLogarithmicYieldVariance());
    assertEquals("SST华新",stockCompareTotalVO2.getName());
    assertEquals("-0.316%",stockCompareTotalVO2.getIncreaseOrDecrease());
    assertEquals("6.43",stockCompareTotalVO2.getHigh());
    assertEquals("0.00002",stockCompareTotalVO2.getLogarithmicYieldVariance());

} 

/** 
* 
* Method: quantourChartCompareByCode(ArrayList<InputStockByCodeVO> stockvo) 
* 
*/ 
@Test
public void testQuantourChartCompareByCode() throws Exception { 
//TODO: Test goes here...
    //public ArrayList<ArrayList<StockCompareEverydayVO>> quantourChartCompareByCode(ArrayList<InputStockByCodeVO> stockvos)
    InputStockByCodeVO inputStockByCodeVO1=new InputStockByCodeVO("04/01/2014","04/03/2014","000001");
    InputStockByCodeVO inputStockByCodeVO2=new InputStockByCodeVO("04/01/2014","04/03/2014","000010");
    ArrayList<InputStockByCodeVO> inputStockByCodeVOS=new ArrayList<InputStockByCodeVO>();
    inputStockByCodeVOS.add(inputStockByCodeVO1);
    inputStockByCodeVOS.add(inputStockByCodeVO2);
    ArrayList<ArrayList<StockCompareEverydayVO>> result=quantourCompareController.quantourChartCompareByCode(inputStockByCodeVOS);
    assertEquals(2,result.size());
    assertEquals(3,result.get(0).size());
    assertEquals(3,result.get(1).size());
    assertEquals("4/1/14",result.get(0).get(0).getDate());
    assertEquals("深发展A",result.get(0).get(0).getName());
    assertEquals("0.00278",result.get(0).get(0).getLogarithmicYield());
    assertEquals("SST华新",result.get(1).get(0).getName());
} 

/** 
* 
* Method: quantourTableCompareByName(ArrayList<InputStockByNameVO> stockvo) 
* 
*/ 
@Test
public void testQuantourTableCompareByName() throws Exception { 
//TODO: Test goes here...
    InputStockByNameVO inputStockByNameVO1=new InputStockByNameVO("04/01/2014","04/03/2014","深发展Ａ");
    InputStockByNameVO inputStockByNameVO2=new InputStockByNameVO("04/01/2014","04/03/2014","SST华新");
    ArrayList<InputStockByNameVO> inputStockByNameVOS=new ArrayList<InputStockByNameVO>();
    inputStockByNameVOS.add(inputStockByNameVO1);
    inputStockByNameVOS.add(inputStockByNameVO2);
    ArrayList<StockCompareTotalVO> stockCompareTotalVOS=quantourCompareController.quantourTableCompareByName(inputStockByNameVOS);
    StockCompareTotalVO stockCompareTotalVO1=stockCompareTotalVOS.get(0);
    StockCompareTotalVO stockCompareTotalVO2=stockCompareTotalVOS.get(1);
    assertEquals(2,stockCompareTotalVOS.size());
    assertEquals("11.02",stockCompareTotalVO1.getHigh());
    assertEquals("10.7",stockCompareTotalVO1.getLow());
    assertEquals("0.00009",stockCompareTotalVO1.getLogarithmicYieldVariance());
    assertEquals("SST华新",stockCompareTotalVO2.getName());
    assertEquals("-0.316%",stockCompareTotalVO2.getIncreaseOrDecrease());
    assertEquals("6.43",stockCompareTotalVO2.getHigh());
    assertEquals("0.00002",stockCompareTotalVO2.getLogarithmicYieldVariance());
} 

/** 
* 
* Method: quantourChartCompareByName(ArrayList<InputStockByNameVO> stockvo) 
* 
*/ 
@Test
public void testQuantourChartCompareByName() throws Exception { 
//TODO: Test goes here...
    InputStockByNameVO inputStockByNameVO1=new InputStockByNameVO("04/01/2014","04/03/2014","深发展Ａ");
    InputStockByNameVO inputStockByNameVO2=new InputStockByNameVO("04/01/2014","04/03/2014","SST华新");
    ArrayList<InputStockByNameVO> inputStockByNameVOS=new ArrayList<InputStockByNameVO>();
    inputStockByNameVOS.add(inputStockByNameVO1);
    inputStockByNameVOS.add(inputStockByNameVO2);
    ArrayList<ArrayList<StockCompareEverydayVO>> result=quantourCompareController.quantourChartCompareByName(inputStockByNameVOS);
    assertEquals(2,result.size());
    assertEquals(3,result.get(0).size());
    assertEquals(3,result.get(1).size());
    assertEquals("4/1/14",result.get(0).get(0).getDate());
    assertEquals("深发展Ａ",result.get(0).get(0).getName());
    assertEquals("0.00278",result.get(0).get(0).getLogarithmicYield());
    assertEquals("SST华新",result.get(1).get(0).getName());
} 


} 
