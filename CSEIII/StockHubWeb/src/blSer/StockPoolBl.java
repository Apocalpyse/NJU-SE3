package blSer;

/**
 * Created by A on 2017/5/21.
 */
public enum StockPoolBl {
    ALL,//所有股票
    MAINPLATE,//主板股票
    SHANGZHENG,//上证股票
    SHENZHENG,//深证股票
    HUSHEN300,//沪深300
    SMALLMIDDLEPLATE,//中小板股票
    STARTUPPLATE,//创业板股票
    SELECTPLATE,//用户自己选择的股票，即自选股票池，自选股票池至少要100支股票，界面要先判断提醒
}
