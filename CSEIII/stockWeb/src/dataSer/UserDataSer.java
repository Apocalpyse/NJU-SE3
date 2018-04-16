package dataSer;

import PO.InputLoginPO;
import PO.SelfSelectStockPO;
import PO.UserPO;


import java.rmi.Remote;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/26.
 */
public interface UserDataSer extends Remote {
    /**
     * 用户注册，在数据层添加用户信息，并返回用户是否注册成功
     * @param userPO
     * @return
     * @throws Exception
     */
    public boolean register(UserPO userPO) throws Exception;

    /**
     * 用户登录，数据层检查用户名与用户密码是否正确，并返回用户是否登录成功
     * @param inputLoginPO
     * @return
     * @throws Exception
     */
    public boolean login(InputLoginPO inputLoginPO) throws Exception;

    /**
     * 修改用户信息，在数据层修改用户信息后，并返回是否修改成功（用户id不允许修改）
     * 注意：修改用户信息要根据用户的account修改
     * @param userPO
     * @return
     * @throws Exception
     */
    public boolean changeUserInfo(UserPO userPO) throws Exception;

    /**
     * 根据用户名获取用户信息
     * @param account
     * @return
     * @throws Exception
     */
    public UserPO getUserInfo(String account) throws Exception;

    /**
     * 获取用户的自选股
     * @param account
     * @return
     * @throws Exception
     */
    public SelfSelectStockPO getSelfSelectStock(String account) throws Exception;

    /**
     * 用户增加自选股票
     * @param selfSelectStockPO
     * @return
     * @throws Exception
     */
    public boolean addSelfSelectStock(SelfSelectStockPO selfSelectStockPO) throws Exception;

    /**
     * 根据股票代码或名称，新增用户的自选股票
     * @param account
     * @param stockCodeOrName
     * @return
     * @throws Exception
     */
    public boolean addOneSelfSelectStock(String account, String stockCodeOrName) throws Exception;

    /**
     * 用户删除自选股票
     * @param selfSelectStockPO
     * @return
     * @throws Exception
     */
    public boolean deleteSelfSelectStock(SelfSelectStockPO selfSelectStockPO) throws Exception;

    /**
     * 判断某只股票是不是已经是用户的自选股
     * @param stockCodeOrName
     * @param account
     * @return
     * @throws Exception
     */
    public boolean isMySelfSelectStock(String stockCodeOrName,String account) throws Exception;

}
