package test.bl.plateBl; 

import VO.OnePlateVO;
import VO.StockVO;
import VO.TotalPlateVO;
import bl.plateBl.PlateController;
import blSer.StockPoolBl;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

/** 
* PlateController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 12, 2017</pre> 
* @version 1.0 
*/ 
public class PlateControllerTest {
    PlateController plateController;
    public PlateControllerTest(){
        ClientRunner c=new ClientRunner();
        plateController=new PlateController();
    }

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getTotalPlateInfo() 
* 
*/ 
@Test
public void testGetTotalPlateInfo() throws Exception { 
//TODO: Test goes here...
    TotalPlateVO totalPlateVO=plateController.getTotalPlateInfo();
    System.out.println(totalPlateVO.getCompanyNum().size());
    System.out.println(totalPlateVO.getCompanyNum().get(0));
    System.out.println(totalPlateVO.getCompanyNum().get(1));
    System.out.println(totalPlateVO.getCompanyNum().get(2));
} 

/** 
* 
* Method: getTotalPlateInfo(String date) 
* 
*/ 
@Test
public void testGetTotalPlateInfoDate() throws Exception {
    TotalPlateVO totalPlateVO=plateController.getTotalPlateInfo("4/3/13");
    System.out.println(totalPlateVO.getCompanyNum().size());
    System.out.println(totalPlateVO.getCompanyNum().get(0));
    System.out.println(totalPlateVO.getCompanyNum().get(1));
    System.out.println(totalPlateVO.getCompanyNum().get(2));
//TODO: Test goes here... 
} 

/** 
* 
* Method: getOnePlateInfo(StockPoolBl stockPoolBl) 
* 
*/ 
@Test
public void testGetOnePlateInfoStockPoolBl() throws Exception { 
//TODO: Test goes here...
    OnePlateVO onePlateVO=plateController.getOnePlateInfo(StockPoolBl.MAINPLATE);
    System.out.println(onePlateVO.getDate());
    ArrayList<StockVO> stockVOS=onePlateVO.getStockVOS();
    for (int i = 0; i < stockVOS.size(); i++) {
        System.out.print(stockVOS.get(i).getCode()+" ");
    }
    System.out.println();
} 

/** 
* 
* Method: getOnePlateInfo(StockPoolBl stockPoolBl, String date) 
* 
*/ 
@Test
public void testGetOnePlateInfoForStockPoolBlDate() throws Exception {
//TODO: Test goes here...
    OnePlateVO onePlateVO=plateController.getOnePlateInfo(StockPoolBl.ALL,"4/1/13");
    System.out.println(onePlateVO.getDate());
    ArrayList<StockVO> stockVOS=onePlateVO.getStockVOS();
    //*****
    System.out.println("next is stockpos from bl");
    System.out.println("data + plate + code + name + iOD + market");
    for (int i = 0; i < stockVOS.size(); i++) {
        StockVO temp=stockVOS.get(i);
        System.out.println(temp.getDate()+" "+temp.getPlate()+" "+temp.getCode()+" "+temp.getName()+" "+temp.getIncreaseOrDecrease()+" "+temp.getMarket());
    }
    System.out.println("end for stockpos from bl");
    //*****
} 


} 
