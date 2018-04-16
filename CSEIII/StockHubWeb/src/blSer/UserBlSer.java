package blSer;

import VO.InputLoginVO;
import VO.userVO.SelfSelectStockVO;
import VO.userVO.UserVO;

/**
 * Created by A on 2017/5/21.
 */
public interface UserBlSer {
    /**
     * 用户注册，返回用户是否注册成功
     * @param userVO
     * @return
     * @throws Exception
     */
    public boolean register(UserVO userVO) throws Exception;

    /**
     * 用户登录，返回用户是否登录成功
     * @param inputLoginVO
     * @return
     * @throws Exception
     */
    public boolean login(InputLoginVO inputLoginVO) throws Exception;

    /**
     * 修改用户信息，返回是否修改成功（用户account不允许修改,界面层不能允许用户修改account）
     * @param userVO
     * @return
     * @throws Exception
     */
    public boolean changeUserInfo(UserVO userVO) throws Exception;

    /**
     * 根据用户账户获取用户信息
     * @param account
     * @return
     * @throws Exception
     */
    public UserVO getUserInfo(String account) throws Exception;

    /**
     * 获取用户的自选股
     * @param account
     * @return
     * @throws Exception
     */
    public SelfSelectStockVO getSelfSelectStock(String account) throws Exception;

    /**
     * 用户增加自选股票
     * @param selfSelectStockVO
     * @return
     * @throws Exception
     */
    public boolean addSelfSelectStock(SelfSelectStockVO selfSelectStockVO) throws Exception;

    /**
     * 用户收藏某只股票
     * @param account
     * @param stockCodeOrName 可能是股票代码或名称
     * @return
     * @throws Exception
     */
    public boolean addOneSelfSelectStock(String account, String stockCodeOrName) throws Exception;

    /**
     * 用户删除自选股票
     * @param selfSelectStockVO
     * @return
     * @throws Exception
     */
    public boolean deleteSelfSelectStock(SelfSelectStockVO selfSelectStockVO) throws Exception;

    /**
     * 根据股票代码或名称，删除用户的自选股票
     * @param account
     * @param stockCodeOrName
     * @return
     * @throws Exception
     */
    public boolean deleteOneSelfStock(String account, String stockCodeOrName) throws Exception;

    /**
     * 判断某只股票是不是已经是用户的自选股
     * @param stockCodeOrName
     * @param account
     * @return 若是用户的自选股，返回true；若不是，返回false
     * @throws Exception
     */
    public boolean isMySelfSelectStock(String stockCodeOrName, String account) throws Exception;
}
