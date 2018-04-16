package PO;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/4/1.
 */
public class StrategyStockPO  implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;//股票代码，格式同数据库表中一样，如“123”，“123456”
    private String name;//股票名称
    private ArrayList<String> date;//日期，格式如"4/29/14","4/2/14"（月/日/年）
    private ArrayList<String> adjClose;//复权后的收盘指数

    public StrategyStockPO() {
    }

    public StrategyStockPO(String code, String name, ArrayList<String> date, ArrayList<String> adjClose) {
        this.code = code;
        this.name = name;
        this.date = date;
        this.adjClose = adjClose;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getDate() {
        return date;
    }

    public void setDate(ArrayList<String> date) {
        this.date = date;
    }

    public ArrayList<String> getAdjClose() {
        return adjClose;
    }

    public void setAdjClose(ArrayList<String> adjClose) {
        this.adjClose = adjClose;
    }

}
