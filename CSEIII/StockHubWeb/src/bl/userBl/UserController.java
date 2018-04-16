package bl.userBl;

import VO.InputLoginVO;
import VO.userVO.SelfSelectStockVO;
import VO.userVO.UserVO;
import bl.calculateBl.Translate;
import blSer.UserBlSer;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class UserController implements UserBlSer {
    /**
     * 用户注册，返回用户是否注册成功
     * @param userVO
     * @return
     * @throws Exception
     */
    @Override
    public boolean register(UserVO userVO) throws Exception {
        User user=new User();
        boolean result=user.register(userVO);
        return result;
    }

    /**
     * 用户登录，返回用户是否登录成功
     * @param inputLoginVO
     * @return
     * @throws Exception
     */
    @Override
    public boolean login(InputLoginVO inputLoginVO) throws Exception {
        User user=new User();
        boolean result=user.login(inputLoginVO);
        return result;
    }

    /**
     * 修改用户信息，返回是否修改成功（用户account不允许修改）
     * @param userVO
     * @return
     * @throws Exception
     */
    @Override
    public boolean changeUserInfo(UserVO userVO) throws Exception {
        User user=new User();
        boolean result=user.changeUserInfo(userVO);
        return result;
    }

    /**
     * 根据用户account获取用户信息
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public UserVO getUserInfo(String account) throws Exception {
        User user=new User();
        UserVO result=user.getUserInfo(account);
        return result;
    }

    /**
     * 获取用户的自选股
     * @param account
     * @return
     * @throws Exception
     */
    @Override
    public SelfSelectStockVO getSelfSelectStock(String account) throws Exception {
        User user=new User();
        SelfSelectStockVO selfSelectStockVO=user.getSelfSelectStock(account);
        return selfSelectStockVO;
    }

    /**
     * 用户增加自选股票
     * @param selfSelectStockVO
     * @return
     * @throws Exception
     */
    @Override
    public boolean addSelfSelectStock(SelfSelectStockVO selfSelectStockVO) throws Exception {
        ArrayList<String> oriCodeS=selfSelectStockVO.getCode();
        ArrayList<String> dataCodeS=new ArrayList<>();
        Translate translate=new Translate();
        for (int i = 0; i < oriCodeS.size(); i++) {
            dataCodeS.add(translate.longToShort(oriCodeS.get(i)));
        }
        selfSelectStockVO.setCode(dataCodeS);
        User user=new User();
        boolean result=user.addSelfSelectStock(selfSelectStockVO);
        return result;
    }

    /**
     * 用户收藏某只股票
     * @param account
     * @param stockCodeOrName 可能是股票代码或名称
     * @return
     * @throws Exception
     */
    @Override
    public boolean addOneSelfSelectStock(String account, String stockCodeOrName) throws Exception {
        if(stockCodeOrName.charAt(0)=='0'){
            Translate translate=new Translate();
            stockCodeOrName=translate.longToShort(stockCodeOrName);
        }
        User user=new User();
        boolean reslut=user.addOneSelfSelectStock(account,stockCodeOrName);
        return reslut;
    }

    /**
     * 用户删除自选股票
     * @param selfSelectStockVO
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteSelfSelectStock(SelfSelectStockVO selfSelectStockVO) throws Exception {
        ArrayList<String> oriCodeS=selfSelectStockVO.getCode();
        ArrayList<String> dataCodeS=new ArrayList<>();
        Translate translate=new Translate();
        for (int i = 0; i < oriCodeS.size(); i++) {
            dataCodeS.add(translate.longToShort(oriCodeS.get(i)));
        }
        selfSelectStockVO.setCode(dataCodeS);
        User user=new User();
        boolean result=user.deleteSelfSelectStock(selfSelectStockVO);
        return result;
    }

    /**
     * 删除用户一支自选股
     * @param account
     * @param stockCodeOrName
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteOneSelfStock(String account, String stockCodeOrName) throws Exception {
        if(stockCodeOrName.charAt(0)=='0'){
            Translate translate=new Translate();
            stockCodeOrName=translate.longToShort(stockCodeOrName);
        }
        User user=new User();
        boolean result=user.deleteOneSelfStock(account,stockCodeOrName);
        return result;
    }

    /**
     * 判断某只股票是不是已经是用户的自选股
     * @param stockIdOrName
     * @param account
     * @return 若是用户的自选股，返回true；若不是，返回false
     * @throws Exception
     */
    @Override
    public boolean isMySelfSelectStock(String stockIdOrName,String account) throws Exception {
        if(stockIdOrName.charAt(0)=='0'){
            Translate translate=new Translate();
            stockIdOrName=translate.longToShort(stockIdOrName);
        }
        User user=new User();
        boolean result=user.isMySelfSelectStock(stockIdOrName,account);
        return result;
    }
}
