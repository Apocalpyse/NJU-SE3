package test.data;

import PO.InputLoginPO;
import PO.SelfSelectStockPO;
import PO.UserPO;
import data.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by chenjin on 2017/4/17.
 */
public class UserTest {
	User user=new User();
	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {

	}

	@Test
	public void login() throws Exception{
		assertEquals(true,user.login(new InputLoginPO("a123","123456")));
		assertEquals(false,user.login(new InputLoginPO("a123","123456789")));
	}

	@Test
	public void getUserInfo() throws Exception{
		UserPO userPO=user.getUserInfo("a123");
		assertEquals("陈进",userPO.getRealName());
		assertEquals("572008964@qq.com",userPO.getMail());
	}

	@Test
	public void getSelfSelectStock() throws Exception{
		SelfSelectStockPO selfSelectStockPO=user.getSelfSelectStock("a123");
		assertEquals("301",selfSelectStockPO.getCode().get(0));
		assertEquals("万力达",selfSelectStockPO.getName().get(1));
		assertEquals("创业板",selfSelectStockPO.getMarket().get(2));

	}

	@Test
	public void isMySelfSelectStock() throws Exception{
		assertEquals(true,user.isMySelfSelectStock("301","a123"));
		assertEquals(true,user.isMySelfSelectStock("万力达","a123"));
		assertEquals(false,user.isMySelfSelectStock("万力","a123"));


	}
}
