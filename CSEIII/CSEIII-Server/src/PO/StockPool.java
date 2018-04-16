package PO;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/1.
 */
public enum StockPool  implements Serializable {
    ALL,//所有股票
    MAINPLATE,//主板股票
    SMALLMIDDLEPLATE,//中小板股票
    STARTUPPLATE,//创业板股票
    SELECTPLATE;//用户自己选择的股票，即自选股票池
    private static final long serialVersionUID = 2L;
}
