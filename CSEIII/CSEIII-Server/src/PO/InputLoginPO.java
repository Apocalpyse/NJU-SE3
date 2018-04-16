package PO;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/26.
 */
public class InputLoginPO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String account;
    private String password;

    public InputLoginPO() {
    }

    public InputLoginPO(String account, String password) {
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
