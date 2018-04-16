package PO.stockPO;

import java.util.ArrayList;

/**
 * Created by A on 2017/5/21.
 */
public class ArrStockPO {
    private ArrayList<String> code;//股票代码，格式同数据库表中一样，如“123”，“123456”
    private ArrayList<String> name;//股票名称
    public ArrStockPO(){

    }
    public ArrStockPO(ArrayList<String> code, ArrayList<String> name) {
        this.code = code;
        this.name = name;
    }

    public ArrayList<String> getCode() {
        return code;
    }

    public void setCode(ArrayList<String> code) {
        this.code = code;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }
}
