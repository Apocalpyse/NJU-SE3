package data;

import PO.InputLoginPO;
import PO.SelfSelectStockPO;
import PO.UserPO;
import dataSer.UserDataSer;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;

import static java.nio.charset.Charset.forName;

/**
 * Created by chenjin on 2017/4/10.
 */
public class User implements UserDataSer{

	public static void main(String[] args){




		User user=new User();
		ArrayList<String> a1=new ArrayList<String>();
		ArrayList<String> a2=new ArrayList<String>();
		ArrayList<String> a3=new ArrayList<String>();

a1.add("301");
		a2.add("东方市场");
		a3.add("主板");

	a1.add("2180");
		a2.add("万力达");
		a3.add("中小板");


		a1.add("300168");
		a2.add("万达信息");
		a3.add("创业板");



		UserPO userPO=new UserPO("b123","李四","123456","陈进","572008964@qq.com","19950401","13951723565");
		InputLoginPO inputLoginPO=new InputLoginPO("b123","123456");
		try {
		System.out.println(user.isMySelfSelectStock("3011","a123"));
		} catch (Exception e) {
			e.printStackTrace();
		}
}



	@Override
	public boolean register(UserPO userPO) throws Exception {
		User user=new User();
		CsvReader r = null;
		boolean sign=true;
		String path = "src/resourceUser/用户个人信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (r.readRecord()){
			String string= r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(userPO.getAccount())){
				sign=false;
				break;
			}
		}
		if(sign){
			CsvWriter writer = null;
			String content="今天天气真好";
			byte[] encrypt = user.encrypt(userPO.getPassword(),content);
			String s0=user.parseByte2HexStr(encrypt);
			FileOutputStream fos = new FileOutputStream(path,true);
			writer = new CsvWriter(fos,',', forName("UTF-8"));
			String s1=userPO.getAccount()+"\t"+userPO.getUsername()+"\t"+s0+"\t"+
					userPO.getRealName()+"\t"+userPO.getMail()+"\t"+userPO.getBirth()+"\t"+userPO.getPhone();
			writer.write(s1);
			writer.endRecord();
			writer.close();
		}
		return sign;

	}

	@Override
	public boolean login(InputLoginPO inputLoginPO) throws Exception {
		User user=new User();
		CsvReader r = null;
		boolean sign=false;
		String path = "src/resourceUser/用户个人信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (r.readRecord()){
			String content="今天天气真好";
			byte[] encrypt =user.encrypt(inputLoginPO.getPassword(),content);
			String string= r.getRawRecord();
			String[] arr = string.split("\t");
			String s0=user.parseByte2HexStr(encrypt);
			if(arr[0].equals(inputLoginPO.getAccount())&&arr[2].equals(s0)){
				sign=true;
				break;
			}
		}
		return sign;
	}

	@Override
	public boolean changeUserInfo(UserPO userPO) throws Exception {
		User user=new User();
		CsvReader r = null;
		boolean sign=false;
		ArrayList<String> arrayList=new ArrayList<String>();
		String path = "src/resourceUser/用户个人信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (r.readRecord()){
			String content="今天天气真好";
			byte[] encrypt = user.encrypt(userPO.getPassword(),content);
			String s0=user.parseByte2HexStr(encrypt);
			String string= r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(userPO.getAccount())){
				string=userPO.getAccount()+"\t"+userPO.getUsername()+"\t"+s0+"\t"+
						userPO.getRealName()+"\t"+userPO.getMail()+"\t"+userPO.getBirth()+"\t"+userPO.getPhone();
				sign=true;
				arrayList.add(string);
			}
			else {
				arrayList.add(string);
			}
		}
		CsvWriter csvWriter=new CsvWriter(path, ',', forName("UTF-8"));
		for(int i=0;i<arrayList.size();i++){
			csvWriter.write(arrayList.get(i));
			csvWriter.endRecord();
		}
		csvWriter.close();
		return sign;
	}

	@Override
	public UserPO getUserInfo(String account) throws Exception {
		User user=new User();
		CsvReader r = null;
		UserPO userPO = null;
		String path = "src/resourceUser/用户个人信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (r.readRecord()){
			String string= r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(account)){
				String content="今天天气真好";
				byte[] by=user.parseHexStr2Byte(arr[2]);
				byte[] decrypt = user.decrypt(by, content);
				userPO=new UserPO(arr[0],arr[1],new String(decrypt),arr[3],arr[4],arr[5],arr[6]);
				break;
			}
		}
		return userPO;
	}

	@Override
	public SelfSelectStockPO getSelfSelectStock(String account) throws Exception {
		CsvReader r = null;
		SelfSelectStockPO selfSelectStockPO = null;
		String path = "src/resourceUser/用户股票信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<String> a1=new ArrayList<String>();
		ArrayList<String> a2=new ArrayList<String>();
		ArrayList<String> a3=new ArrayList<String>();
		while (r.readRecord()){
			String string= r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(account)){
				String[] code=arr[1].split("-");
				String[] name=arr[2].split("-");
				String[] market=arr[3].split("-");
				for(int i=0;i<code.length;i++){
					a1.add(code[i]);
				}
				for(int i=0;i<name.length;i++){
					a2.add(name[i]);
				}
				for(int i=0;i<market.length;i++){
					a3.add(market[i]);
				}
			}

		}
		return new SelfSelectStockPO(account,a1,a2,a3);
	}

	@Override
	public boolean addSelfSelectStock(SelfSelectStockPO selfSelectStockPO) throws Exception {
		boolean sign=false;
		ArrayList<String> arrayList=new ArrayList<String>();
		CsvReader r = null;
		String path = "src/resourceUser/用户股票信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (r.readRecord()){
			String string= r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(selfSelectStockPO.getAccount())){
				String string1=arr[1]+"-",string2=arr[2]+"-",string3=arr[3]+"-";
				for(int i=0;i<selfSelectStockPO.getCode().size()-1;i++){
					string1=string1+selfSelectStockPO.getCode().get(i)+"-";
				}
				string1=string1+selfSelectStockPO.getCode().get(selfSelectStockPO.getCode().size()-1);
				for(int i=0;i<selfSelectStockPO.getCode().size()-1;i++){
					string2=string2+selfSelectStockPO.getName().get(i)+"-";
				}
				string2=string2+selfSelectStockPO.getName().get(selfSelectStockPO.getCode().size()-1);
				for(int i=0;i<selfSelectStockPO.getCode().size()-1;i++){
					string3=string3+selfSelectStockPO.getMarket().get(i)+"-";
				}
				string3=string3+selfSelectStockPO.getMarket().get(selfSelectStockPO.getCode().size()-1);
				string=selfSelectStockPO.getAccount()+"\t"+string1+"\t"+string2+"\t"+string3;
				sign=true;
				arrayList.add(string);
			}
			else {
				arrayList.add(string);
			}
		}
		CsvWriter csvWriter=new CsvWriter(path, ',', forName("UTF-8"));
		if(sign){
			for(int i=0;i<arrayList.size();i++){
				csvWriter.write(arrayList.get(i));
				csvWriter.endRecord();
			}
		}
		else{
			for(int i=0;i<arrayList.size();i++){
				csvWriter.write(arrayList.get(i));
				csvWriter.endRecord();
			}
			String string1="",string2="",string3="";
			for(int i=0;i<selfSelectStockPO.getCode().size()-1;i++){
				string1=string1+selfSelectStockPO.getCode().get(i)+"-";
			}
			string1=string1+selfSelectStockPO.getCode().get(selfSelectStockPO.getCode().size()-1);
			for(int i=0;i<selfSelectStockPO.getCode().size()-1;i++){
				string2=string2+selfSelectStockPO.getName().get(i)+"-";
			}
			string2=string2+selfSelectStockPO.getName().get(selfSelectStockPO.getCode().size()-1);
			for(int i=0;i<selfSelectStockPO.getCode().size()-1;i++){
				string3=string3+selfSelectStockPO.getMarket().get(i)+"-";
			}
			string3=string3+selfSelectStockPO.getMarket().get(selfSelectStockPO.getCode().size()-1);
			String str=selfSelectStockPO.getAccount()+"\t"+string1+"\t"+string2+"\t"+string3;
			csvWriter.write(str);
			csvWriter.endRecord();

		}
		csvWriter.close();
		return true;
	}

	@Override
	public boolean addOneSelfSelectStock(String account, String stockCodeOrName) throws Exception {
		boolean sign=false;
		User user = new User();
		CsvReader r = null;
		String path;
		stockCodeOrName=stockCodeOrName.replace("*","1");
		stockCodeOrName=stockCodeOrName.replace(" ","");
		stockCodeOrName=stockCodeOrName.replace("A","Ａ");
		if (user.isInt(stockCodeOrName)) {
			path = "src/resourceCode/" + stockCodeOrName + ".csv";
		} else {
			path = "src/resourceName/mainPlate/" + stockCodeOrName + ".csv";
			File file = new File(path);
			if (!file.exists()) {
				path = "src/resourceName/smallMiddlePlate/" + stockCodeOrName + ".csv";
				File file1 = new File(path);
				if (!file1.exists()) {
					path = "src/resourceName/startupPlate/" + stockCodeOrName + ".csv";
				}
			}
		}

		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		r.readRecord();
			String string = r.getRawRecord();
			String[] arr = string.split("\t");
			ArrayList<String> a1=new ArrayList<String>();
			ArrayList<String> a2=new ArrayList<String>();
			ArrayList<String> a3=new ArrayList<String>();
			a1.add(arr[8]);
		arr[9]=arr[9].replace(" ","");
		arr[9]=arr[9].replace("A","Ａ");
			a2.add(arr[9]);
			a3.add(arr[13]);
		return user.addSelfSelectStock(new SelfSelectStockPO(account,a1,a2,a3));
	}

	@Override
	public boolean deleteSelfSelectStock(SelfSelectStockPO selfSelectStockPO) throws Exception {
		boolean sign=false;
		ArrayList<String> arrayList=new ArrayList<String>();
		CsvReader r = null;
		String path = "src/resourceUser/用户股票信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		User user=new User();
		ArrayList<String> a1 = null,a2=null,a3=null;
		SelfSelectStockPO agoSelfSelectStockPO=user.getSelfSelectStock(selfSelectStockPO.getAccount());
		a1=agoSelfSelectStockPO.getCode();
		a2=agoSelfSelectStockPO.getName();
		a3=agoSelfSelectStockPO.getMarket();
		while (r.readRecord()) {
			String string = r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(selfSelectStockPO.getAccount())){
				String[] code=arr[1].split("-");
				String[] name=arr[2].split("-");
				String[] market=arr[3].split("-");
				for(int i=0;i<selfSelectStockPO.getCode().size();i++){
					for(int j=0;j<a1.size();j++){
						if(selfSelectStockPO.getCode().get(i).equals(a1.get(j))){
							a1.remove(j);
							a2.remove(j);
							a3.remove(j);
							j--;
						}
					}

				}
			}
			else {
				arrayList.add(string);
			}
		}
		CsvWriter csvWriter=new CsvWriter(path, ',', forName("UTF-8"));
		for(int i=0;i<arrayList.size();i++){
			csvWriter.write(arrayList.get(i));
			csvWriter.endRecord();
		}
		csvWriter.close();
		if(a1.size()==0){
			return true;
		}
		else {
			return user.addSelfSelectStock(new SelfSelectStockPO(selfSelectStockPO.getAccount(),a1,a2,a3));
		}
	}

	@Override
	public boolean isMySelfSelectStock(String stockCodeOrName, String account) throws Exception {
		boolean sign=false;
		CsvReader r = null;
		String path = "src/resourceUser/用户股票信息.csv";
		try {
			r = new CsvReader(path, ',', forName("UTF-8"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (r.readRecord()) {
			String string = r.getRawRecord();
			String[] arr = string.split("\t");
			if(arr[0].equals(account)){
				String[] code=arr[1].split("-");
				String[] name=arr[2].split("-");
				for (int i=0;i<code.length;i++){
					if(code[i].equals(stockCodeOrName)){
						sign=true;
						break;
					}
				}
				for (int i=0;i<name.length;i++){
					if(name[i].equals(stockCodeOrName)){
						sign=true;
						break;
					}
				}
			}

		}
		return sign;
	}

	public boolean isInt(String string){
		try {
			int num=Integer.valueOf(string);//把字符串强制转换为数字
			return true;//如果是数字，返回True
		} catch (Exception e) {
			return false;//如果抛出异常，返回False
		}
	}

	/**
	 * AES加密字符串
	 *
	 * @param content
	 *            需要被加密的字符串
	 * @param password
	 *            加密需要的密码
	 * @return 密文
	 */
	public static byte[] encrypt(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者

			kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
			// 128位的key生产者
			//加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行

			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥

			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
			// null。

			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥

			Cipher cipher = Cipher.getInstance("AES");// 创建密码器

			byte[] byteContent = content.getBytes("utf-8");

			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器

			byte[] result = cipher.doFinal(byteContent);// 加密

			return result;

		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密AES加密过的字符串
	 *
	 * @param content
	 *            AES加密过过的内容
	 * @param password
	 *            加密时的密码
	 * @return 明文
	 */
	public static byte[] decrypt(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
			byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化为解密模式的密码器
			byte[] result = cipher.doFinal(content);
			return result; // 明文

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}


