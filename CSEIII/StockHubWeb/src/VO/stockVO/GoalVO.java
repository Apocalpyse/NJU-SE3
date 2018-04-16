package VO.stockVO;

/**
 * Created by A on 2017/5/21.
 */
public class GoalVO {
    private String code;//股票代码
    private String name;//股票名字
    private String total;
    private String stability;//稳定性，通过股价的方差来求
    private String prof;//收益性，起止股价的增长率
    private String mobility;//流动性
    private String safety;//安全性，股票跑赢大盘的概率
    private String development;//成长性，股票所在行业的增长率

    public GoalVO() {
    }

    public GoalVO(String code, String name, String total, String stability, String prof, String mobility, String safety, String development) {
        this.code=code;
        this.name=name;
        this.total = total;
        this.stability = stability;
        this.prof = prof;
        this.mobility = mobility;
        this.safety = safety;
        this.development = development;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getStability() {
        return stability;
    }

    public void setStability(String stability) {
        this.stability = stability;
    }

    public String getProf() {
        return prof;
    }

    public void setProf(String prof) {
        this.prof = prof;
    }

    public String getMobility() {
        return mobility;
    }

    public void setMobility(String mobility) {
        this.mobility = mobility;
    }

    public String getSafety() {
        return safety;
    }

    public void setSafety(String safety) {
        this.safety = safety;
    }

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

}
