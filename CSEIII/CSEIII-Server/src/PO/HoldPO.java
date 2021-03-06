package PO;

import java.util.ArrayList;

/**
 * Created by chenjin on 2017/5/6.
 */
public class HoldPO {
	private String account;
	private ArrayList<String> holdCode;
	private ArrayList<String> holdMoney;
	private ArrayList<String> holdCopies;

	public HoldPO() {
	}

	public HoldPO(String account, ArrayList<String> holdCode, ArrayList<String> holdMoney, ArrayList<String> holdCopies) {
		this.account = account;
		this.holdCode = holdCode;
		this.holdMoney = holdMoney;
		this.holdCopies = holdCopies;
	}


	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public ArrayList<String> getHoldCode() {
		return holdCode;
	}

	public void setHoldCode(ArrayList<String> holdCode) {
		this.holdCode = holdCode;
	}

	public ArrayList<String> getHoldMoney() {
		return holdMoney;
	}

	public void setHoldMoney(ArrayList<String> holdMoney) {
		this.holdMoney = holdMoney;
	}

	public ArrayList<String> getHoldCopies() {
		return holdCopies;
	}

	public void setHoldCopies(ArrayList<String> holdCopies) {
		this.holdCopies = holdCopies;
	}
}
