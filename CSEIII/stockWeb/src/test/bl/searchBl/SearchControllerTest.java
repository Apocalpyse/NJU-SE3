package test.bl.searchBl; 

import VO.StockVO;
import bl.KAndEMABl.KAndEMAController;
import bl.searchBl.SearchController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** 
* SearchController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 16, 2017</pre> 
* @version 1.0 
*/ 
public class SearchControllerTest {
    SearchController searchController;
    public SearchControllerTest(){
        ClientRunner cr=new ClientRunner();
        searchController=new SearchController();
    }

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: findAllStock(String codeOrName) 
* 
*/ 
@Test
public void testFindAllStock() throws Exception { 
//TODO: Test goes here...
    /*
    ArrayList<StockVO> stockVOS1=searchController.findAllStock("1");
    ArrayList<StockVO> stockVOS2=searchController.findAllStock("深发展Ａ");
    assertEquals("000001",stockVOS1.get(0).getCode());
    assertEquals("深发展A",stockVOS1.get(0).getName());
    assertEquals("000001",stockVOS2.get(0).getCode());
    assertEquals("深发展A",stockVOS2.get(0).getName());
    */
} 


} 
