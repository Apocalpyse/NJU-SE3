package VO;

/**
 * Created by Administrator on 2017/3/31.
 */
public class InputLoginVO {
    private String account;//用户帐户
    private String password;//用户密码

    public InputLoginVO() {
    }

    public InputLoginVO(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
