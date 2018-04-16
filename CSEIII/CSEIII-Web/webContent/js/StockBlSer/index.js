
/**
 * Created by thinkpad on 2017/5/22.
 */
/**
function JSMarketSituationVO(date, totalVolume, increaseStopStockNum, decreaseStopStockNum, increaseMoreStockNum, decreaseMoreStockNum,  openMinusCloseMoreNum,  openMinusCloseLessNum) {
    // /**
    //  *  用户查询日期或者某一日期的股票交易市场行情相关数据。
    //
    // private String date;//日期，格式如"4/29/14","4/2/14"（月/日/年）
    // private String totalVolume;//当日总交易量
    // private String increaseStopStockNum;//涨停股票数
    // private String decreaseStopStockNum;//跌停股票数
    // private String increaseMoreStockNum;//涨幅超过5%的股票数
    // private String decreaseMoreStockNum;//跌幅超过5%的股票数
    // private String openMinusCloseMoreNum;//开盘‐收盘大于5%*上一个交易日收盘价的股票个数
    // private String openMinusCloseLessNum;//开盘‐收盘小于‐5%*上一个交易日收盘价的股票个数
    this.date = date;
    this.totalVolume = totalVolume;
    this.increaseStopStockNum = increaseStopStockNum;
    this.decreaseStopStockNum = decreaseStopStockNum;
    this.increaseMoreStockNum = increaseMoreStockNum;
    this.decreaseMoreStockNum = decreaseMoreStockNum;
    this.openMinusCloseMoreNum = openMinusCloseMoreNum;
    this.openMinusCloseLessNum = openMinusCloseLessNum;
    this.showDate=function () {
        return date;
    }
    this.showTotalVolume=function () {
        return totalVolume;
    }
    this.showIncreaseStopStockNum =function () {
        return increaseStopStockNum ;
    }
    this.showDecreaseStopStockNum =function () {
        return decreaseMoreStockNum ;
    }
    this.showIncreaseMoreStockNum =function () {
        return increaseMoreStockNum ;
    }
    this.showDecreaseMoreStockNum=function () {
        return decreaseMoreStockNum;
    }
    this.showOpenMinusCloseMoreNum=function () {
        return openMinusCloseMoreNum;
    }
    this.showOpenMinusCloseLessNum=function () {
        return openMinusCloseLessNum;
    }
}


function JSNewsVO(newsID, titleForNews,author, time,  sourceOfNews,  view, praise, criticize,  contentOfNews,  comment,  commentAccount) {
    // /**
    //  *  用户查询新闻相关数据。
    //
    // private String newsID;
    // private String title;//标题
    // private String author;//作者
    // private String time;//发表时间
    // private String source;//来源
    // private int view;//浏览数目
    // private int praise;//点赞数目
    // private int criticize;//点踩数目
    // private String content;//新闻内容
    // private ArrayList<String> comment;//评论
    // private ArrayList<String> commentAccount;//评论对应account

    this.newsID = newsID;
    this.titleForNews = titleForNews;
    this.author = author;
    this.time = time;
    this.sourceOfNews = sourceOfNews;
    this.view = view;
    this.praise = praise;
    this.criticize = criticize;
    this.contentOfNews = contentOfNews;
    this.comment = comment;
    this.commentAccount = commentAccount;
    this.showNewsID=function () {
        return newsID;
    }
    this.showTitleForNews=function () {
        return titleForNews;
    }
    this.showAuthor=function () {
        return author;
    }
    this.showTime=function () {
        return time;
    }
    this.showSourceOfNews=function () {
        return sourceOfNews;
    }
    this.showView=function () {
        return view;
    }
    this.showPraise=function () {
        return praise;
    }
    this.showCriticize=function () {
        return criticize;
    }
    this.showContentOfNews =function () {
        return contentOfNews ;
    }
    this.showComment=function () {
        return comment;
    }
    this.showCommentAccount=function () {
        return commentAccount;
    }
}
*/



var dataForMarket=[
    {"date":"date1",
    "totalVolume":"totalVolume1",
    "increaseStopStockNum":"increaseStopStockNum1",
    "decreaseStopStockNum":"decreaseStopStockNum1",
    "increaseMoreStockNum":"increaseMoreStockNum1",
    "decreaseMoreStockNum":"decreaseMoreStockNum1",
    "openMinusCloseMoreNum":"openMinusCloseMoreNum1",
    "openMinusCloseLessNum":"openMinusCloseLessNum1"},
    {"date":"date2",
        "totalVolume":"totalVolume2",
        "increaseStopStockNum":"increaseStopStockNum2",
        "decreaseStopStockNum":"decreaseStopStockNum2",
        "increaseMoreStockNum":"increaseMoreStockNum2",
        "decreaseMoreStockNum":"decreaseMoreStockNum2",
        "openMinusCloseMoreNum":"openMinusCloseMoreNum2",
        "openMinusCloseLessNum":"openMinusCloseLessNum2"},
    {"date":"date3",
        "totalVolume":"totalVolume3",
        "increaseStopStockNum":"increaseStopStockNum3",
        "decreaseStopStockNum":"decreaseStopStockNum3",
        "increaseMoreStockNum":"increaseMoreStockNum3",
        "decreaseMoreStockNum":"decreaseMoreStockNum3",
        "openMinusCloseMoreNum":"openMinusCloseMoreNum3",
        "openMinusCloseLessNum":"openMinusCloseLessNum3"}];
// <a class="type" href="/S/SH000001" target="_blank">
//     <h5>上证指数</h5>
//     <div class="current">3083.51</div>
//     <div class="growth"> <i>+</i>0.72%(<i>+</i>22.01)</div>
// </a>
// <!--板指简易图表-->
// <a class="chart" href="/S/SH000001" target="_blank">
//     <svg version="1.1" xmlns="http://www.w3.org/2000/svg" id="SH000001">
//     <path class="last-close" d="M0 16L70 16" stroke="#666c72" stroke-linecap="round" stroke-width="1" stroke-dasharray="3,3" fill="none" data-value="3061.5"></path>
//     </svg>
//     </a>
//获取当前日期
var today = new Date();
var myDate = new Date(today-1000 * 60 * 60 * 24 );
var nowY = myDate.getFullYear();
var nowM = myDate.getMonth()+1;
var nowD = myDate.getDate();
var enddate = (nowM<10 ? "0" + nowM : nowM)+"/"+(nowD<10 ? "0"+ nowD : nowD)+"/"+nowY;//当前日期

//获取三十天前日期
var lw = new Date(myDate - 1000 * 60 * 60 * 24 * 31);//最后一个数字30可改，30天的意思
var lastY = lw.getFullYear();
var lastM = lw.getMonth()+1;
var lastD = lw.getDate();
var startdate=(lastM<10 ? "0" + lastM : lastM)+"/"+(lastD<10 ? "0"+ lastD : lastD)+"/"+lastY;//三十天之前日期


// var data=[
//     {
//         "date": "17/5/24",
//         "pen": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "1%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     },
//     {
//         "date": "17/5/25",
//         "pen": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "1%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     },
//     {
//         "date": "17/5/26",
//         "pen": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "1%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     },
//     {
//         "date": "17/5/27",
//         "pen": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "1%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     }
// ];
function initSZMarketChart(){
    var code1="SH000001";
    //上证

    var stockInfo1={"start":startdate,"end":enddate,"code":code1};
    $.ajax({
        url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout:1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo1,


        success: function(data1){

            // 路径配置
            require.config({
                paths: {
                    echarts: '../dist'
                }
            });

            // 使用
            require(
                [
                    'echarts',
                    'echarts/theme/macarons',
                    'echarts/chart/k' // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec,theme) {
                    // 基于准备好的dom，初始化echarts图表


                    var getdate = [];
                    var getatr = [];
                    var KInfo1 = [];
                    for (var i = 0; i < data1.length; i++) {
                        getdate[i] = data1[i].date;
                        KInfo1[0] = data1[i].open;
                        KInfo1[1] = data1[i].close;
                        KInfo1[2] = data1[i].low;
                        KInfo1[3] = data1[i].high;
                        getatr[i] = KInfo1;
                    }

//            var myChart1 = echarts.init(document.querySelector("#divForSCI"));
                    var myChart1 = ec.init(document.getElementById('divForSCIChart'),theme);


                    // 指定图表的配置项和数据

                    var option = {
                        title: {
                            text: ''
                        },
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var res = params[0].seriesName + ' ' + params[0].name;
                                res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                                res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                                return res;
                            }
                        },
                        legend: {
                            data: ['上证指数']
                        },
                        toolbox: {
                            show: false,
                            feature: {
                                mark: {show: true},
                                dataZoom: {show: true},
                                dataView: {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        dataZoom: {
                            show: true,
                            realtime: true,
                            start: 50,
                            end: 100
                        },
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: true,
                                axisTick: {onGap: false},
                                splitLine: {show: false},
                                data: getdate
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                scale: true,
                                boundaryGap: [0.01, 0.01]
                            }
                        ],
                        series: [//开盘，收盘，最低，最高
                            {
                                name: '上证指数',
                                type: 'k',
                                data: getatr
                            }
                        ]
                    };


                    // 使用刚指定的配置项和数据显示图表。
                    myChart1.setOption(option);
                    alert("end");
                }
            );


        }
    });
}
function initCYMarketChart(){

    var code2="SZ399006";
    //创业
    var stockInfo2={"start":startdate,"end":enddate,"code":code2};

    $.ajax({
        url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout:1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo2,


        success: function(data2){

            // 路径配置
            require.config({
                paths: {
                    echarts: '../dist'
                }
            });

            // 使用
            require(
                [
                    'echarts',
                    'echarts/theme/macarons',
                    'echarts/chart/k' // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec,theme) {
                    // 基于准备好的dom，初始化echarts图表


                    var getdate = [];
                    var getatr = [];
                    var KInfo2 = [];
                    for (var i = 0; i < data2.length; i++) {
                        getdate[i] = data2[i].date;
                        KInfo2[0] = data2[i].open;
                        KInfo2[1] = data2[i].close;
                        KInfo2[2] = data2[i].low;
                        KInfo2[3] = data2[i].high;
                        getatr[i] = KInfo2;
                    }

//            var myChart1 = echarts.init(document.querySelector("#divForSCI"));
                    var myChart2 = ec.init(document.getElementById('divForSCIChart'),theme);


                    // 指定图表的配置项和数据

                    var option = {
                        title: {
                            text: ''
                        },
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var res = params[0].seriesName + ' ' + params[0].name;
                                res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                                res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                                return res;
                            }
                        },
                        legend: {
                            data: ['上证指数']
                        },
                        toolbox: {
                            show: false,
                            feature: {
                                mark: {show: true},
                                dataZoom: {show: true},
                                dataView: {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        dataZoom: {
                            show: true,
                            realtime: true,
                            start: 50,
                            end: 100
                        },
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: true,
                                axisTick: {onGap: false},
                                splitLine: {show: false},
                                data: getdate
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                scale: true,
                                boundaryGap: [0.01, 0.01]
                            }
                        ],
                        series: [//开盘，收盘，最低，最高
                            {
                                name: '上证指数',
                                type: 'k',
                                data: getatr
                            }
                        ]
                    };


                    // 使用刚指定的配置项和数据显示图表。
                    myChart2.setOption(option);

                }
            );


        }
    });
}
function initHSMarketChart(){
    var code3="HKHSI";
    //恒生
    var stockInfo3={"start":startdate,"end":enddate,"code":code3};
    $.ajax({
        url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout:1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo3,


        success: function(data3){

            // 路径配置
            require.config({
                paths: {
                    echarts: '../dist'
                }
            });

            // 使用
            require(
                [
                    'echarts',
                    'echarts/theme/macarons',
                    'echarts/chart/k' // 使用柱状图就加载bar模块，按需加载
                ],
                function (ec,theme) {
                    // 基于准备好的dom，初始化echarts图表


                    var getdate = [];
                    var getatr = [];
                    var KInfo3 = [];
                    for (var i = 0; i < data3.length; i++) {
                        getdate[i] = data3[i].date;
                        KInfo3[0] = data3[i].open;
                        KInfo3[1] = data3[i].close;
                        KInfo3[2] = data3[i].low;
                        KInfo3[3] = data3[i].high;
                        getatr[i] = KInfo3;
                    }

//            var myChart1 = echarts.init(document.querySelector("#divForSCI"));
                    var myChart3= ec.init(document.getElementById('divForCSI300Chart'),theme);


                    // 指定图表的配置项和数据

                    var option = {
                        title: {
                            text: ''
                        },
                        tooltip: {
                            trigger: 'axis',
                            formatter: function (params) {
                                var res = params[0].seriesName + ' ' + params[0].name;
                                res += '<br/>  开盘 : ' + params[0].value[0] + '  最高 : ' + params[0].value[3];
                                res += '<br/>  收盘 : ' + params[0].value[1] + '  最低 : ' + params[0].value[2];
                                return res;
                            }
                        },
                        legend: {
                            data: ['上证指数']
                        },
                        toolbox: {
                            show: false,
                            feature: {
                                mark: {show: true},
                                dataZoom: {show: true},
                                dataView: {show: true, readOnly: false},
                                magicType: {show: true, type: ['line', 'bar']},
                                restore: {show: true},
                                saveAsImage: {show: true}
                            }
                        },
                        dataZoom: {
                            show: true,
                            realtime: true,
                            start: 50,
                            end: 100
                        },
                        xAxis: [
                            {
                                type: 'category',
                                boundaryGap: true,
                                axisTick: {onGap: false},
                                splitLine: {show: false},
                                data: getdate
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value',
                                scale: true,
                                boundaryGap: [0.01, 0.01]
                            }
                        ],
                        series: [//开盘，收盘，最低，最高
                            {
                                name: '上证指数',
                                type: 'k',
                                data: getatr
                            }
                        ]
                    };


                    // 使用刚指定的配置项和数据显示图表。
                    myChart3.setOption(option);

                }
            );


        }

    });
}

function numSub(num1, num2) {
    var baseNum, baseNum1, baseNum2;
    try {
        baseNum1 = num1.toString().split(".")[1].length;
    } catch (e) {
        baseNum1 = 0;
    }
    try {
        baseNum2 = num2.toString().split(".")[1].length;
    } catch (e) {
        baseNum2 = 0;
    }
    baseNum = Math.pow(10, Math.max(baseNum1, baseNum2));
    var precision = (baseNum1 >= baseNum2) ? baseNum1 : baseNum2;
    return ((num1 * baseNum - num2 * baseNum) / baseNum).toFixed(precision);
};

function initSZMarket() {
    var code1 = "SH000001";
    //上证
    var stockInfo1={"start":enddate,"end":enddate,"code":code1};

    $.ajax({
        url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo1,


        success: function (data1) {

            document.getElementById('valueOfSCI').innerHTML=data1[0].close;
            document.getElementById('growthPercentageOfSCI').innerHTML=data1[0].increaseOrDecrease;
            document.getElementById('growthAmountOfSCI').innerHTML=numSub(data1[0].close,data1[0].open)();
        },
        complete : function(XMLHttpRequest,status){ //请求完成后最终执行参数
            if(status=='timeout'){//超时,status还有success,error等值的情况
                // ajaxTimeOut.abort(); //取消请求
                alert("超时");
            }
        }
    });
}
function initCYMarket(){
    var code2="SZ399006";
    //创业
    var stockInfo2={"start":enddate,"end":enddate,"code":code2};

    $.ajax({
        url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo2,


        success: function (data2) {
            document.getElementById('valueOfCSI').innerHTML=data2[0].close;
            document.getElementById('growthPercentageOfCSI').innerHTML=data2[0].increaseOrDecrease;
            document.getElementById('growthAmountOfCSI').innerHTML=numSub(data2[0].close,data2[0].open)();
        }
    });
}
function initHSMarket(){
    var code3="HKHSI";
    //恒生

    var stockInfo3 = {"start": startdate, "end": enddate, "code": code3};

    $.ajax({
        url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo3,


        success: function (data3) {
            document.getElementById('valueOfCSI300').innerHTML=data3[0].close;
            document.getElementById('growthPercentageOfCSI300').innerHTML=data3[0].increaseOrDecrease;
            document.getElementById('growthAmountOfCSI300').innerHTML=numSub(data3[0].close,data3[0].open)();
        }
    });
}












//行情排行榜
function initQuotationIncrease(){
    // var type="all";
    var stockInfo={"date":enddate};

    $.ajax({
        url: "http://localhost:8080/web/MarketSituationBLSer/getIncreaseList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,


        success: function (tableData) {
            var table = document.createElement("table");
            var tableHead=document.createElement("thead");
            var tableBody=document.createElement("tbody");
            var tableRow=document.createElement("tr");
            var th1=document.createElement("th");
            th1.innerHTML="排行";
            var th2=document.createElement("th");
            th2.innerHTML="代码";
            var th3=document.createElement("th");
            th3.innerHTML="名称";
            var th4=document.createElement("th");
            th4.innerHTML="涨幅";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < 10; i++)
            {//手动剪切了一部分的信息
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);


            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('IncreaseTable').innerHTML=tableDiv.innerHTML;

        }
    });
}
function initQuotationDecrease(){
// var type="all";
    var stockInfo={"date":enddate};

    $.ajax({
        url: "http://localhost:8080/web/MarketSituationBLSer/getDecreaseList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,


        success: function (tableData) {
            var table = document.createElement("table");
            var tableHead=document.createElement("thead");
            var tableBody=document.createElement("tbody");
            var tableRow=document.createElement("tr");
            var th1=document.createElement("th");
            th1.innerHTML="排行";
            var th2=document.createElement("th");
            th2.innerHTML="代码";
            var th3=document.createElement("th");
            th3.innerHTML="名称";
            var th4=document.createElement("th");
            th4.innerHTML="跌幅";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < 10; i++)
            {//手动剪切了一部分的信息
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DecreaseTable').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initQuotationDeal(){
    // var type="all";
    var stockInfo={"date":enddate};

    $.ajax({
        url: "http://localhost:8080/web/MarketSituationBLSer/getVolumeList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,


        success: function (tableData) {
            var table = document.createElement("table");
            var tableHead=document.createElement("thead");
            var tableBody=document.createElement("tbody");
            var tableRow=document.createElement("tr");
            var th1=document.createElement("th");
            th1.innerHTML="排行";
            var th2=document.createElement("th");
            th2.innerHTML="代码";
            var th3=document.createElement("th");
            th3.innerHTML="名称";
            var th4=document.createElement("th");
            th4.innerHTML="成交量";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < 10; i++)
            {//手动剪切了一部分的信息
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].volume;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DealTable').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initQuotationPotential(){
    // var type="all";
    var stockInfo={"date":enddate};

    $.ajax({
        url: "http://localhost:8080/web/MarketSituationBLSer/getPotentialList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,


        success: function (tableData) {
            var table = document.createElement("table");
            var tableHead=document.createElement("thead");
            var tableBody=document.createElement("tbody");
            var tableRow=document.createElement("tr");
            var th1=document.createElement("th");
            th1.innerHTML="排行";
            var th2=document.createElement("th");
            th2.innerHTML="代码";
            var th3=document.createElement("th");
            th3.innerHTML="名称";
            var th4=document.createElement("th");
            th4.innerHTML="潜力";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < 10; i++)
            {//手动剪切了一部分的信息
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].potential;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('PotentialTable').innerHTML=tableDiv.innerHTML;
        }
    });
}

function initNews(){
    // var type="all";
    var newsInfo={"date":enddate};

    $.ajax({
        url: "",
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:newsInfo,


        success: function (newsData) {
            var Headline=document.getElementById('HeadLines')
            var Emergency=document.getElementById('Emergencies').innerHTML="";
            var dataDiv=document.createElement("div");
            for (var indexI = 0; indexI < 5; indexI++)
            {//手动剪切了一部分的信息
                var adiv=document.createElement("div");
                var id="H"+indexI;
                adiv.setAttribute("id",id);
                var aclass="well";
                adiv.setAttribute("class",aclass);
                var aa=document.createElement("a");
                var id2="H"+indexI+"A";
                aa.setAttribute("id",id2);
                aa.setAttribute("href","news.html?"+newsData[indexI].title);
                aa.setAttribute("target","_blank");
                aa.innerHTML=newsData[indexI].title;
                adiv.appendChild(aa);
                dataDiv.appendChild(adiv);
            }
            Headline.innerHTML=dataDiv.innerHTML;
        }
    });
}
function initInvestmentReference() {
    var investInfo={"date":enddate};

    $.ajax({
        url: "",
        type: "GET",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:investInfo,


        success: function (investData) {
            var Investment=document.getElementById('Investments');
            var dataDiv=document.createElement("div");
            for (var i = 0; i < 5; i++)
            {//手动剪切了一部分的信息
                var adiv=document.createElement("div");
                var id="I"+i;
                adiv.setAttribute("id",id);
                var aclass="well";
                adiv.setAttribute("class",aclass);
                var aa=document.createElement("a");
                var id2="I"+i+"A";
                aa.setAttribute("id",id2);
                aa.setAttribute("href","news.html?"+investData[i].title);
                aa.setAttribute("target","_blank");
                aa.innerHTML=investData[i].title;
                adiv.appendChild(aa);
                dataDiv.appendChild(adiv);
            }
            Investment.innerHTML=dataDiv.innerHTML;
        }
    });
}
function ready() {
    //三个扳指
    initSZMarket();
    initCYMarket();
    initHSMarket();
    //对应的K线图
    initSZMarketChart();
    initCYMarketChart();
    initHSMarketChart();
    //行情排行榜
    initQuotationIncrease();
    initQuotationDecrease();
    initQuotationDeal();
    initQuotationPotential();
    //新闻
    initNews();
    //投资参考
    initInvestmentReference();
}

