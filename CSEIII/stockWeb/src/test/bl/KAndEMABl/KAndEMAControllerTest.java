package test.bl.KAndEMABl;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import VO.*;
import bl.KAndEMABl.KAndEMAController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

/** 
* KAndEMAEventController Tester.
* 
* @author <Authors name> 
* @since <pre>���� 15, 2017</pre> 
* @version 1.0 
*/ 
public class KAndEMAControllerTest {
    KAndEMAController kAndEMAController;
    public KAndEMAControllerTest(){
        ClientRunner cr=new ClientRunner();
        kAndEMAController=new KAndEMAController();
    }

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getDataByCode(InputStockByCodeVO stockvo) 
* 
*/ 
@Test
public void testGetDataByCode() throws Exception { 
//TODO: Test goes here...
    InputStockByCodeVO inputStockByCodeVO=new InputStockByCodeVO("3/22/14","4/4/14","000001");
    ArrayList<StockVO> stockVOS= kAndEMAController.getDataByCode(inputStockByCodeVO);
    assertEquals(10, stockVOS.size());
    assertEquals("10.79",stockVOS.get(0).getAdjClose());
    assertEquals("3/31/14",stockVOS.get(5).getDate());
} 

/** 
* 
* Method: getDataByName(InputStockByNameVO stockvo) 
* 
*/ 
@Test
public void testGetDataByName() throws Exception { 
//TODO: Test goes here...
    InputStockByNameVO inputStockByNameVO=new InputStockByNameVO("3/22/14","4/4/14","深发展Ａ");
    ArrayList<StockVO> stockVOS= kAndEMAController.getDataByName(inputStockByNameVO);
    assertEquals(10, stockVOS.size());
    assertEquals("10.79",stockVOS.get(0).getAdjClose());
    assertEquals("3/31/14",stockVOS.get(5).getDate());

}


    /**
     *
     * Method: getEMAByCode(InputStockByCodeVO stockvo, String numOfEMA)
     *
     */
    @Test
    public void testGetEMAByCode() throws Exception {
//TODO: Test goes here...
        InputStockByCodeVO inputStockByCodeVO=new InputStockByCodeVO("3/22/13","4/4/14","000001");
        KAndEMAController kAndEMAController=new KAndEMAController();
        EMAVO emavo=kAndEMAController.getEMAByCode(inputStockByCodeVO,"10");
        //*****
//        private ArrayList<String> date;//横坐标
//        private ArrayList<String> yield;//纵坐标
//        private String maxYield;//最大纵坐标，方便界面定义图
//        private String minYield;
        //*****
        ArrayList<String> date=emavo.getDate();
        ArrayList<String> yield=emavo.getYield();
        System.out.println(date.size());
        System.out.println(yield.size());
        for (int i = 0; i < date.size(); i++) {
            System.out.print(date.get(i)+" ");
        }
        System.out.println();
        for (int i = 0; i < yield.size(); i++) {
            System.out.print(yield.get(i)+" ");
        }
        System.out.println();
        System.out.println(emavo.getMaxYield());
        System.out.println(emavo.getMinYield());
    }

    /**
     *
     * Method: getEMAByName(InputStockByNameVO stockvo, String numOfEMA)
     *
     */
    @Test
    public void testGetEMAByName() throws Exception {
//TODO: Test goes here...
    }

    /**
* 
* Method: getPieDataByCode(InputStockByCodeVO stockvo) 
* 
*/ 
@Test
public void testGetPieDataByCode() throws Exception { 
//TODO: Test goes here...
    InputStockByCodeVO inputStockByCodeVO=new InputStockByCodeVO("3/22/14","4/4/14","000001");
    PieVO pieVO=kAndEMAController.getPieDataByCode(inputStockByCodeVO);
    /*
    PieVO ought=new PieVO("深发展A","000001","03/22/2014","04/04/2014",
            "7","0","3","0");
            */
    assertEquals("深发展A",pieVO.getName());
    assertEquals("000001",pieVO.getCode());
    assertEquals("6",pieVO.getIncreaseLessDays());
    assertEquals("1",pieVO.getIncreaseMoreDays());
    assertEquals("3",pieVO.getDecreaseLessDays());
    assertEquals("0",pieVO.getDecreaseMoredays());
} 

/** 
* 
* Method: getPieDataByName(InputStockByNameVO stockvo) 
* 
*/ 
@Test
public void testGetPieDataByName() throws Exception { 
//TODO: Test goes here...
    InputStockByNameVO inputStockByNameVO=new InputStockByNameVO("3/22/14","4/4/14","深发展Ａ");
    PieVO pieVO=kAndEMAController.getPieDataByName(inputStockByNameVO);
    /*
    PieVO ought=new PieVO("深发展Ａ","000001","03/22/2014","04/04/2014",
            "7","0","3","0");
            */
    assertEquals("深发展Ａ",pieVO.getName());
    assertEquals("000001",pieVO.getCode());
    assertEquals("6",pieVO.getIncreaseLessDays());
    assertEquals("1",pieVO.getIncreaseMoreDays());
    assertEquals("3",pieVO.getDecreaseLessDays());
    assertEquals("0",pieVO.getDecreaseMoredays());
} 


} 
