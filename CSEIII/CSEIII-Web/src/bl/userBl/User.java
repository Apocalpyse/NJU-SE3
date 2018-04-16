package bl.userBl;

import PO.userPO.InputLoginPO;
import PO.userPO.SelfSelectStockPO;
import PO.userPO.UserPO;
import VO.InputLoginVO;
import VO.userVO.SelfSelectStockVO;
import VO.userVO.UserVO;
import data.userData.UserData;
import dataSer.UserDataSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class User {
    private UserDataSer userDataSer=new UserData();

//    public User(){
//        userDataSer= RemoteHelper.getInstance().getUserDataSer();
//    }

    /**
     * 用户注册，返回用户是否注册成功
     * @param userVO
     * @return
     * @throws Exception
     */
    public boolean register(UserVO userVO) throws Exception {
        UserPO userPO=new UserPO(userVO);
        boolean result=userDataSer.register(userPO);

        return result;
    }

    /**
     * 用户登录，返回用户是否登录成功
     * @param inputLoginVO
     * @return
     * @throws Exception
     */
    public boolean login(InputLoginVO inputLoginVO) throws Exception {
        InputLoginPO inputLoginPO=new InputLoginPO(inputLoginVO.getAccount(), inputLoginVO.getPassword());
        boolean result=userDataSer.login(inputLoginPO);

        return result;
    }

    /**
     * 修改用户信息，返回是否修改成功（用户account不允许修改）
     * @param userVO
     * @return
     * @throws Exception
     */
    public boolean changeUserInfo(UserVO userVO) throws Exception {
        UserPO userPO=new UserPO(userVO);
        boolean result=userDataSer.changeUserInfo(userPO);
        return result;
    }

    /**
     * 根据用户account获取用户信息
     * @param account
     * @return
     * @throws Exception
     */
    public UserVO getUserInfo(String account) throws Exception {
        UserPO userPO=userDataSer.getUserInfo(account);
        UserVO userVO=new UserVO(userPO);
        return userVO;
    }

    /**
     * 获取用户的自选股
     * @param account
     * @return
     * @throws Exception
     */
    public SelfSelectStockVO getSelfSelectStock(String account) throws Exception {
        SelfSelectStockPO selfSelectStockPO=userDataSer.getSelfSelectStock(account);
        if(selfSelectStockPO==null){
            ArrayList<String> code=new ArrayList<>();//股票代码，格式同数据库表中一样，如“123”，“123456”
            ArrayList<String> name=new ArrayList<>();//股票名称
            ArrayList<String> market=new ArrayList<>();
            SelfSelectStockVO selfSelectStockVO=new SelfSelectStockVO(account,code,name,market);
            return selfSelectStockVO;
        }
        SelfSelectStockVO selfSelectStockVO=new SelfSelectStockVO(selfSelectStockPO);
        return selfSelectStockVO;
    }

    /**
     * 用户增加自选股票
     * @param selfSelectStockVO
     * @return
     * @throws Exception
     */
    public boolean addSelfSelectStock(SelfSelectStockVO selfSelectStockVO) throws Exception {
        SelfSelectStockPO selfSelectStockPO=new SelfSelectStockPO(selfSelectStockVO);
        boolean result=userDataSer.addSelfSelectStock(selfSelectStockPO);
        return result;
    }

    /**
     * 用户收藏某只股票
     * @param account
     * @param stockCodeOrName 可能是股票代码或名称
     * @return
     * @throws Exception
     */
    public boolean addOneSelfSelectStock(String account, String stockCodeOrName) throws Exception {
        boolean result=userDataSer.addOneSelfSelectStock(account,stockCodeOrName);
        return result;
    }

    /**
     * 用户删除自选股票
     * @param selfSelectStockVO
     * @return
     * @throws Exception
     */
    public boolean deleteSelfSelectStock(SelfSelectStockVO selfSelectStockVO) throws Exception {
        SelfSelectStockPO selfSelectStockPO=new SelfSelectStockPO(selfSelectStockVO);
        boolean result=userDataSer.deleteSelfSelectStock(selfSelectStockPO);
        return result;
    }

    /**
     * 删除用户一支自选股
     * @param account
     * @param stockCodeOrName
     * @return
     */
    public boolean deleteOneSelfStock(String account, String stockCodeOrName) throws Exception{
        ArrayList<String> code=new ArrayList<>();
        code.add(stockCodeOrName);
        SelfSelectStockPO selfSelectStockPO=new SelfSelectStockPO(account,code,null,null);
        boolean result=userDataSer.deleteSelfSelectStock(selfSelectStockPO);
        return result;
    }

    /**
     * 判断某只股票是不是已经是用户的自选股
     * @param stockIdOrName
     * @return 若是用户的自选股，返回true；若不是，返回false
     * @throws Exception
     */
    public boolean isMySelfSelectStock(String stockIdOrName,String account) throws Exception {
        boolean result=userDataSer.isMySelfSelectStock(stockIdOrName,account);
        return result;
    }

}
