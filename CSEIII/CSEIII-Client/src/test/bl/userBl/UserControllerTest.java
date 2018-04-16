package test.bl.userBl; 

import VO.InputLoginVO;
import VO.SelfSelectStockVO;
import VO.UserVO;
import bl.userBl.UserController;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import rmi.ClientRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/** 
* UserController Tester. 
* 
* @author <Authors name> 
* @since <pre>���� 15, 2017</pre> 
* @version 1.0 
*/ 
public class UserControllerTest {
    UserController userController;
    public UserControllerTest(){
        ClientRunner c=new ClientRunner();
        userController=new UserController();
    }

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: register(UserVO userVO) 
* 
*/ 
@Test
public void testUser() throws Exception {
//TODO: Test goes here...
    //register
    UserVO userVO1=new UserVO("123","user1","111222");
    UserVO userVO2=new UserVO("456","user2","123456");
    UserVO userVO3=new UserVO("456","user4","222333");
    boolean rresult1=userController.register(userVO1);
    boolean rresult2=userController.register(userVO2);
    boolean rresult3=userController.register(userVO3);
    assertEquals(true,rresult1);
    assertEquals(true,rresult2);
    assertEquals(false,rresult3);//该账号已被注册，所以返回false

    //login
    InputLoginVO inputLoginVO1=new InputLoginVO("123","111222");
    InputLoginVO inputLoginVO2=new InputLoginVO("123","222111");
    InputLoginVO inputLoginVO3=new InputLoginVO("321","111222");
    InputLoginVO inputLoginVO4=new InputLoginVO("321","222111");
    boolean lresult1=userController.login(inputLoginVO1);
    boolean lresult2=userController.login(inputLoginVO2);
    boolean lresult3=userController.login(inputLoginVO3);
    boolean lresult4=userController.login(inputLoginVO4);
    assertEquals(true,lresult1);
    assertEquals(false,lresult2);
    assertEquals(false,lresult3);
    assertEquals(false,lresult4);

    //change user info
    UserVO cuserVO1=new UserVO("123","user3","111222");//修改user1的名称为user3
    UserVO cuserVO2=new UserVO("111","user3","111222");
    boolean cresult1=userController.changeUserInfo(cuserVO1);
    boolean cresult2=userController.changeUserInfo(cuserVO2);
    assertEquals(true,cresult1);
    assertEquals(false,cresult2);
    UserVO cresultUserVO=userController.getUserInfo("123");
    assertEquals("user3",cresultUserVO.getUsername());

    //get user info
    UserVO guserVO1=userController.getUserInfo("123");
    UserVO guserVO2=userController.getUserInfo("456");
    assertEquals("user3",guserVO1.getUsername());
    assertEquals("user2",guserVO2.getUsername());

    //get self select stock
    SelfSelectStockVO gselfSelectStockVO1=userController.getSelfSelectStock("123");
    assertEquals("123",gselfSelectStockVO1.getAccount());
    assertEquals(0,gselfSelectStockVO1.getCode().size());

    //add self select stock
    String code1="000001";
    String code2="000002";
    String code3="000003";
    String name1="name1";
    String name2="name2";
    String name3="name3";
    String market1="market1";
    String market2="market2";
    String market3="market3";
    ArrayList<String> codes1=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
    ArrayList<String> names1=new ArrayList<>();//股票名称
    ArrayList<String> markets1=new ArrayList<>();//股票市场
    codes1.add(code1);
    codes1.add(code2);
    codes1.add(code3);
    names1.add(name1);
    names1.add(name2);
    names1.add(name3);
    markets1.add(market1);
    markets1.add(market2);
    markets1.add(market3);

    ArrayList<String> codes2=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
    ArrayList<String> names2=new ArrayList<>();//股票名称
    ArrayList<String> markets2=new ArrayList<>();//股票市场
    codes2.add(code2);
    codes2.add(code3);
    names2.add(name2);
    names2.add(name3);
    markets2.add(market2);
    markets2.add(market3);

    SelfSelectStockVO selfSelectStockVO1=new SelfSelectStockVO("123",codes1,names1,markets1);
    SelfSelectStockVO selfSelectStockVO2=new SelfSelectStockVO("456",codes2,names2,markets2);
    boolean aresult1=userController.addSelfSelectStock(selfSelectStockVO1);
    boolean aresult2=userController.addSelfSelectStock(selfSelectStockVO2);

    assertEquals(true,aresult1);
    assertEquals(true,aresult2);

    //接下来获取数据，看是否有增加股票到数据层
    SelfSelectStockVO resultSelfSelectStockVO1=userController.getSelfSelectStock("123");
    assertEquals("123",resultSelfSelectStockVO1.getAccount());
    assertEquals(3,resultSelfSelectStockVO1.getCode().size());
    assertEquals("000001",resultSelfSelectStockVO1.getCode().get(0));
    assertEquals("market3",resultSelfSelectStockVO1.getMarket().get(2));

    SelfSelectStockVO resultSelfSelectStockVO2=userController.getSelfSelectStock("456");
    assertEquals("456",resultSelfSelectStockVO2.getAccount());
    assertEquals(2,resultSelfSelectStockVO2.getCode().size());
    assertEquals("000002",resultSelfSelectStockVO2.getCode().get(0));
    assertEquals("market3",resultSelfSelectStockVO2.getMarket().get(1));

    //delete self select stock
    ArrayList<String> codes3=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
    ArrayList<String> names3=new ArrayList<>();//股票名称
    ArrayList<String> markets3=new ArrayList<>();//股票市场
    codes3.add(code3);
    codes3.add(code2);
    names3.add(name3);
    names3.add(name2);
    markets3.add(market3);
    markets3.add(market2);
    SelfSelectStockVO selfSelectStockVO3=new SelfSelectStockVO("123",codes3,names3,markets3);
    boolean dresult=userController.deleteSelfSelectStock(selfSelectStockVO3);
    assertEquals(true, dresult);

    //接下来获取数据，看数据层是否删除股票
    SelfSelectStockVO resultSelfSelectStockVO3=userController.getSelfSelectStock("123");
    assertEquals("123",resultSelfSelectStockVO3.getAccount());
    assertEquals(1,resultSelfSelectStockVO3.getCode().size());
    assertEquals("000001",resultSelfSelectStockVO3.getCode().get(0));
    assertEquals(1,resultSelfSelectStockVO3.getName().size());
    assertEquals("market1",resultSelfSelectStockVO3.getMarket().get(0));



}

/**
*
* Method: isMySelfSelectStock(String stockIdOrName, String account)
*
*/
@Test
public void testIsMySelfSelectStock() throws Exception {
//TODO: Test goes here...
    boolean result1=userController.isMySelfSelectStock("000005","123");
    boolean result2=userController.isMySelfSelectStock("000001","123");
    boolean result3=userController.isMySelfSelectStock("000002","456");
    assertEquals(false,result1);
    assertEquals(true,result2);
    assertEquals(true,result3);
}

///**
//*
//* Method: login(InputLoginVO inputLoginVO)
//*
//*/
//@Test
//public void testLogin() throws Exception {
////TODO: Test goes here...
//    InputLoginVO inputLoginVO1=new InputLoginVO("123","111222");
//    InputLoginVO inputLoginVO2=new InputLoginVO("123","222111");
//    InputLoginVO inputLoginVO3=new InputLoginVO("321","111222");
//    InputLoginVO inputLoginVO4=new InputLoginVO("321","222111");
//    boolean result1=userController.login(inputLoginVO1);
//    boolean result2=userController.login(inputLoginVO2);
//    boolean result3=userController.login(inputLoginVO3);
//    boolean result4=userController.login(inputLoginVO4);
//    assertEquals(true,result1);
//    assertEquals(false,result2);
//    assertEquals(false,result3);
//    assertEquals(false,result4);
//}
//
///**
//*
//* Method: changeUserInfo(UserVO userVO)
//*
//*/
//@Test
//public void testChangeUserInfo() throws Exception {
////TODO: Test goes here...
//    UserVO userVO1=new UserVO("123","user3","111222");//修改user1的名称为user3
//    UserVO userVO2=new UserVO("111","user3","111222");
//    boolean result1=userController.changeUserInfo(userVO1);
//    boolean result2=userController.changeUserInfo(userVO2);
//    assertEquals(true,result1);
//    assertEquals(false,result2);
//    UserVO resultUserVO=userController.getUserInfo("123");
//    assertEquals("user3",resultUserVO.getUsername());
//}
//
///**
//*
//* Method: getUserInfo(String account)
//*
//*/
//@Test
//public void testGetUserInfo() throws Exception {
////TODO: Test goes here...
//    UserVO userVO1=userController.getUserInfo("123");
//    UserVO userVO2=userController.getUserInfo("456");
//    assertEquals("user3",userVO1.getUsername());
//    assertEquals("user2",userVO2.getUsername());
//}
//
///**
//*
//* Method: getSelfSelectStock(String account)
//*
//*/
//@Test
//public void testGetSelfSelectStock() throws Exception {
////TODO: Test goes here...
//    SelfSelectStockVO selfSelectStockVO=userController.getSelfSelectStock("123");
//    assertEquals("123",selfSelectStockVO.getAccount());
//    assertEquals(0,selfSelectStockVO.getCode().size());
//}
//
///**
//*
//* Method: addSelfSelectStock(SelfSelectStockVO selfSelectStockVO)
//*
//*/
//@Test
//public void testAddSelfSelectStock() throws Exception {
////TODO: Test goes here...
//    String code1="000001";
//    String code2="000002";
//    String code3="000003";
//    String name1="name1";
//    String name2="name2";
//    String name3="name3";
//    String market1="market1";
//    String market2="market2";
//    String market3="market3";
//    ArrayList<String> codes1=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
//    ArrayList<String> names1=new ArrayList<>();//股票名称
//    ArrayList<String> markets1=new ArrayList<>();//股票市场
//    codes1.add(code1);
//    codes1.add(code2);
//    codes1.add(code3);
//    names1.add(name1);
//    names1.add(name2);
//    names1.add(name3);
//    markets1.add(market1);
//    markets1.add(market2);
//    markets1.add(market3);
//
//    ArrayList<String> codes2=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
//    ArrayList<String> names2=new ArrayList<>();//股票名称
//    ArrayList<String> markets2=new ArrayList<>();//股票市场
//    codes2.add(code2);
//    codes2.add(code3);
//    names2.add(name2);
//    names2.add(name3);
//    markets2.add(market2);
//    markets2.add(market3);
//
//    SelfSelectStockVO selfSelectStockVO1=new SelfSelectStockVO("123",codes1,names1,markets1);
//    SelfSelectStockVO selfSelectStockVO2=new SelfSelectStockVO("456",codes2,names2,markets2);
//    boolean result1=userController.addSelfSelectStock(selfSelectStockVO1);
//    boolean result2=userController.addSelfSelectStock(selfSelectStockVO2);
//
//    assertEquals(true,result1);
//    assertEquals(true,result2);
//
//    //接下来获取数据，看是否有增加股票到数据层
//    SelfSelectStockVO resultSelfSelectStockVO1=userController.getSelfSelectStock("123");
//    assertEquals("123",resultSelfSelectStockVO1.getAccount());
//    assertEquals(3,resultSelfSelectStockVO1.getCode().size());
//    assertEquals("000001",resultSelfSelectStockVO1.getCode().get(0));
//    assertEquals("market3",resultSelfSelectStockVO1.getMarket().get(2));
//
//    SelfSelectStockVO resultSelfSelectStockVO2=userController.getSelfSelectStock("456");
//    assertEquals("456",resultSelfSelectStockVO2.getAccount());
//    assertEquals(2,resultSelfSelectStockVO2.getCode().size());
//    assertEquals("000002",resultSelfSelectStockVO2.getCode().get(0));
//    assertEquals("market3",resultSelfSelectStockVO2.getMarket().get(1));
//
//    //is my self select stock
//    boolean iresult1=userController.isMySelfSelectStock("000001","123");
//    boolean iresult2=userController.isMySelfSelectStock("000003","123");
//    boolean iresult3=userController.isMySelfSelectStock("000002","456");
//    assertEquals(false,iresult1);
//    assertEquals(true,iresult2);
//    assertEquals(true,iresult3);
//}

/**
*
* Method: deleteSelfSelectStock(SelfSelectStockVO selfSelectStockVO)
*
*/
@Test
public void testDeleteSelfSelectStock() throws Exception {
//TODO: Test goes here...
    String code1="000003";
    String code2="000002";
    String name1="name1";
    String name2="name2";
    String market1="market1";
    String market2="market2";
    ArrayList<String> codes1=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
    ArrayList<String> names1=new ArrayList<>();//股票名称
    ArrayList<String> markets1=new ArrayList<>();//股票市场
    codes1.add(code1);
//    codes1.add(code2);
    names1.add(name1);
    names1.add(name2);
    markets1.add(market1);
    markets1.add(market2);
    SelfSelectStockVO selfSelectStockVO1=new SelfSelectStockVO("456",codes1,null,null);
    boolean result=userController.deleteSelfSelectStock(selfSelectStockVO1);
    assertEquals(true, result);

    //接下来获取数据，看数据层是否删除股票
//    SelfSelectStockVO resultSelfSelectStockVO1=userController.getSelfSelectStock("123");
//    assertEquals("123",resultSelfSelectStockVO1.getAccount());
//    assertEquals(1,resultSelfSelectStockVO1.getCode().size());
//    assertEquals("000001",resultSelfSelectStockVO1.getCode().get(0));
//    assertEquals(1,resultSelfSelectStockVO1.getName().size());
//    assertEquals("market3",resultSelfSelectStockVO1.getMarket().get(0));
}

//
///**
//*
//* Method: isMySelfSelectStock(String stockIdOrName, String account)
//*
//*/
//@Test
//public void testIsMySelfSelectStock() throws Exception {
////TODO: Test goes here...
//    boolean result1=userController.isMySelfSelectStock("000005","123");
//    boolean result2=userController.isMySelfSelectStock("000003","123");
//    boolean result3=userController.isMySelfSelectStock("000002","456");
//    assertEquals(false,result1);
//    assertEquals(true,result2);
//    assertEquals(true,result3);
//}


} 
