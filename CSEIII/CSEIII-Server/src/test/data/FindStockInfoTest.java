package test.data;

import PO.StockPO;
import data.FindStockInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by chenjin on 2017/3/18.
 */
public class FindStockInfoTest {
	FindStockInfo findStockInfo = new FindStockInfo();

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public  void testFindStockInfoByCode() throws Exception{
		ArrayList<StockPO> al=new ArrayList<StockPO>();
		al=findStockInfo.findStockInfoByCode("2/1/05","2/11/05","1");
		assertEquals(9,al.size());
		assertEquals("1.37",al.get(1).getPreAdjClose());
		assertEquals("18549300",al.get(3).getVolume());
	}

	@Test
	public  void testFindStockInfoByName() throws Exception{
		ArrayList<StockPO> al=new ArrayList<StockPO>();
		al=findStockInfo.findStockInfoByName("2/16/05","2/23/05","*ST中华A");
		assertEquals(6,al.size());
		assertEquals("2.16",al.get(1).getPreAdjClose());
		assertEquals("1416600",al.get(3).getVolume());
	}

	@Test
	public  void testFindStockInfoOneday() throws Exception{
		ArrayList<StockPO> al=new ArrayList<StockPO>();
		al=findStockInfo.findStockInfoOneday("2/1/05");
		assertEquals("477300",al.get(0).getVolume());
		assertEquals("许继电气",al.get(1).getName());
	}

	@Test
	public  void testFiAndAllStock() throws Exception{
		ArrayList<StockPO> al=new ArrayList<StockPO>();
		ArrayList<StockPO> al2=new ArrayList<StockPO>();
		al=findStockInfo.findAllStock("2");
		al2=findStockInfo.findAllStock("ST国农");
		assertEquals("4.403",al.get(1).getIncreaseOrDecrease());
		assertEquals("万科A",al.get(4).getName());
		assertEquals("424300",al2.get(1).getVolume());
		assertEquals("3.814",al2.get(3).getIncreaseOrDecrease());
	}



}
