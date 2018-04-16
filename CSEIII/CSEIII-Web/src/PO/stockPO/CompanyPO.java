package PO.stockPO;

/**
 * Created by delong chang on 2017/6/1.
 */
public class CompanyPO {
    private String code;//代码
    private String stockName;//股票名称
    private String industry;//行业
    private String area;//地区
    private String totalassets;//总资产
    private String liquidassets;//流动资产
    private String fixedassets;//固定资产
    private String uptime;//上市日期
    private String holders;//股东人数

    public CompanyPO() {
    }

    public CompanyPO(String code, String stockName, String industry, String area, String totalassets,
                     String liquidassets, String fixedassets, String uptime, String holders) {
        this.code = code;
        this.stockName = stockName;
        this.industry = industry;
        this.area = area;
        this.totalassets = totalassets;
        this.liquidassets = liquidassets;
        this.fixedassets = fixedassets;
        this.uptime = uptime;
        this.holders = holders;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTotalassets() {
        return totalassets;
    }

    public void setTotalassets(String totalassets) {
        this.totalassets = totalassets;
    }

    public String getLiquidassets() {
        return liquidassets;
    }

    public void setLiquidassets(String liquidassets) {
        this.liquidassets = liquidassets;
    }

    public String getFixedassets() {
        return fixedassets;
    }

    public void setFixedassets(String fixedassets) {
        this.fixedassets = fixedassets;
    }

    public String getUptime() {
        return uptime;
    }

    public void setUptime(String uptime) {
        this.uptime = uptime;
    }

    public String getHolders() {
        return holders;
    }

    public void setHolders(String holders) {
        this.holders = holders;
    }
}
