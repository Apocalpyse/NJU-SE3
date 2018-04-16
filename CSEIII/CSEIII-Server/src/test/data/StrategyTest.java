package test.data;

import PO.InputStrategyPO;
import PO.StockPool;
import PO.StrategyStockPO;
import data.Strategy;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by chenjin on 2017/4/17.
 */
public class StrategyTest {
	Strategy strategy=new Strategy();
	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void findStrategyStockInfo() throws RemoteException {
		InputStrategyPO inputStrategyPO=new InputStrategyPO("2/1/05","2/3/05", StockPool.MAINPLATE,new ArrayList<String>());
		ArrayList<StrategyStockPO> arrayList=strategy.findStrategyStockInfo(inputStrategyPO);
		assertEquals(44,arrayList.size());
	}

	@Test
	public void findBenchmarkStockInfo() throws RemoteException {
		StrategyStockPO strategyStockPO=strategy.findBenchmarkStockInfo("2/1/05","2/4/05","000300");
		assertEquals("955.95",strategyStockPO.getAdjClose().get(0));
		assertEquals("993.22",strategyStockPO.getAdjClose().get(2));
	}

	@Test
	public void getPreviousTradeDate() throws RemoteException{
		String s1=strategy.getPreviousTradeDate("2/14/05/",3);
		String s2=strategy.getPreviousTradeDate("2/4/05",2);
		assertEquals("2/2/05",s1);
		assertEquals("2/2/05",s2);
	}
	@Test
	public void isTradeDate() throws RemoteException{
		assertEquals(true,strategy.isTradeDate("2/1/05"));
		assertEquals(false,strategy.isTradeDate("2/14/05"));
	}


}
