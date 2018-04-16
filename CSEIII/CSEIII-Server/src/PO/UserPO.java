package PO;

import java.io.Serializable;

public class UserPO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String account;//用户账户，也是用户唯一标志，登录时使用，每个用户的账户是唯一的。账户格式要求只能包含英文字母、数字，且必须以英文字母开头。
	private String username;//用户名，即用户昵称，类似与qq昵称
	private String password;//用户密码
	private String realName;//用户真实姓名
	private String mail;//邮箱
	private String birth;//生日，8位，如“19970516”
	private String phone;//手机号码，11位

	public UserPO() {
	}

	public UserPO( String account, String username, String password) {
		this.account=account;
		this.username = username;
		this.password = password;
	}

	public UserPO( String account, String username, String password, String realName, String mail, String birth, String phone) {
		this.account=account;
		this.username = username;
		this.password = password;
		this.realName = realName;
		this.mail = mail;
		this.birth = birth;
		this.phone = phone;
	}



	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
