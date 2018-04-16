package test.data;

import PO.StockPO;
import PO.TotalPlatePO;
import data.FindPlateInfo;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by chenjin on 2017/4/16.
 */
public class FindPlateInfoTest {
	FindPlateInfo findPlateInfo=new FindPlateInfo();
	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {

	}


	@Test
	public void findTotalPlateInfo() throws RemoteException {
		TotalPlatePO totalPlatePO=findPlateInfo.findTotalPlateInfo("2/1/05");
		assertEquals("46433600",totalPlatePO.getTotalVolume().get(1));
		assertEquals("67789000",totalPlatePO.getTotalVolume().get(0));
		assertEquals("46",totalPlatePO.getCompanyNum().get(0));
	}

	@Test
	public void findOnePlateInfo() throws RemoteException {
		ArrayList<StockPO> arrayList=findPlateInfo.findOnePlateInfo(0,"2/1/05");
		assertEquals(24,arrayList.size());
	}
}
