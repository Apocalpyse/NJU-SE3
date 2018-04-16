/**
 * Created by thinkpad on 2017/5/31.
 */
document.write("<script language=javascript src='../echarts.min.js'></script>");
var myPlate=GetArgsFromHref('plate');
var myCode=getPlateCode(myPlate);
var myStockPool=getStockPool(myPlate);
var myPlateName=getPlateName(myPlate);
var myIndex=getPlateIndex(myPlate);
//获取当前日期(前一天)
var today = new Date();
var myDate = new Date(today-1000 * 60 * 60 * 24 );
var nowY = myDate.getFullYear();
var nowM = myDate.getMonth()+1;
var nowD = myDate.getDate();
var enddate = nowM+"/"+nowD+"/"+nowY%100;//当前日期
//获取三十天前日期
var lw = new Date(myDate - 1000 * 60 * 60 * 24 * 31);//最后一个数字30可改，30天的意思
var lastY = lw.getFullYear();
var lastM = lw.getMonth()+1;
var lastD = lw.getDate();
var startdate=lastM+"/"+lastD+"/"+lastY%100;//三十天之前日期
//模拟数据
// enddate="5/12/16";
// startdate="4/12/16";
// startdate="4/12/16";
enddate="6/2/17";
startdate="4/28/16";
// var KData=[
//     {
//         "date": "5/24/17",
//         "open": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "code": "60001",
//         "AdjClose": "13",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "20%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     },
//     {
//         "date": "5/25/17",
//         "open": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "AdjClose": "10",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "30%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     },
//     {
//         "date": "5/26/17",
//         "open": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "AdjClose": "12",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "18%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     },
//     {
//         "date": "5/27/17",
//         "open": "10",
//         "high": "11",
//         "low": "9.8",
//         "close": "10.2",
//         "AdjClose": "14",
//         "code": "60001",
//         "name": "kl",
//         "market": "sa",
//         "increaseOrDecrease": "16%",
//         "preAdjClose": "1.2",
//         "plate": "1"
//     }
// ];

function ready() {
    initPlate();
}
//各个具体模块的初始化
function initPlate() {
    //行情排行榜
    document.getElementById('H3ForAll').innerHTML=myPlateName;
    initPlateSituation(enddate,myIndex);
    initPlatePieChart(myStockPool,enddate,"PieChartDiv");
    initPlateKChart(myStockPool,startdate,enddate,"KChartDiv",myPlateName);
    initList("getPlateIncList",myStockPool,enddate,"Decrease");
    initList("getPlateDecList",myStockPool,enddate,"Increase");
    initList("getPlateVolumeList",myStockPool,enddate,"Deal");
    initList("getPlatePotentialList",myStockPool,enddate,"Potential");

}
//获取plate
function GetArgsFromHref(code)
{
    var srcStr=window.location.href.toString();
    var args = srcStr.split('?');
    var retval = "";
    if(args[0] == srcStr) /*参数为空*/
    {
        return retval; /*无需做任何处理*/
    }
    var str = args[1];
    args = str.split("&");
    for(var i = 0; i < args.length; i ++)
    {
        str = args[i];
        var arg = str.split("=");
        if(arg.length <= 1) continue;
        if(arg[0] == code) retval = arg[1];
    }
    retval=decodeURI(retval);
    return retval;
}
//获取板指股票代码
function getPlateCode(plate)
{
    var theCode="";
    if(plate=="CSI300"){
        theCode="000300";
    }else if(plate=="SCI"){
        theCode="000001";
    }else if(plate=="SIASA"){
        theCode="399001";
    }else if(plate=="SPF"){
        theCode="399005";
    }else if(plate=="GEM"){
        theCode="399006";
    }else{
        theCode="000300";
    }//默认无对应板块是返回沪深300
    return theCode;
}
//获取stockPool
function getStockPool(plate)
{
    var stockPool="";
    if(plate=="CSI300"){
        stockPool="HUSHEN300";
    }else if(plate=="SCI"){
        stockPool="SHANGZHENG";
    }else if(plate=="SIASA"){
        stockPool="SHENZHENG";
    }else if(plate=="SPF"){
        stockPool="SMALLMIDDLEPLATE";
    }else if(plate=="GEM"){
        stockPool="STARTUPPLATE";
    }else{
        stockPool="HUSHEN300";
    }//默认无对应板块是返回沪深300
    return stockPool;
}
//获取板块名称
function getPlateName(plate)
{
    var theName="";
    if(plate=="CSI300"){
        theName="沪深300";
    }else if(plate=="SCI"){
        theName="上证指数";
    }else if(plate=="SIASA"){
        theName="深证成指";
    }else if(plate=="SPF"){
        theName="中小板指";
    }else if(plate=="GEM"){
        theName="创业板指";
    }else{
        theName="沪深300";
    }//默认无对应板块是返回沪深300
    return theName;
}
//获取板块对应index
function getPlateIndex(plate)
{
    var theIndex="";
    if(plate=="CSI300"){
        theIndex=2;
    }else if(plate=="SCI"){
        theIndex=0;
    }else if(plate=="SIASA"){
        theIndex=1;
    }else if(plate=="SPF"){
        theIndex=3;
    }else if(plate=="GEM"){
        theIndex=4;
    }else{
        theIndex=2;
    }//默认无对应板块是返回沪深300
    return theIndex;
}
//获取某个jsonArray里的某一列信息并返回为array/***        特别的有对于日期的处理      ***/
function getSpecificColumn(data,column)
{
    var result=[];
    for(var count=0;count<data.length;count++){
        result[count]=data[count][column];
        if(column=="date"){
            var theStr=data[count][column].split("/");
            result[count]=theStr[0]+"/"+theStr[1];
        }else{
            result[count]=parseFloat(result[count]);
        }
    }
    return result;
}
//获取数据中KChart所需要部分并返回为二维数组
function getKFormData(stockData) {
    var result=new Array();
    var date=getSpecificColumn(stockData,"date");
    var open=getSpecificColumn(stockData,"open");
    var close=getSpecificColumn(stockData,"close");
    var low=getSpecificColumn(stockData,"low");
    var high=getSpecificColumn(stockData,"high");
    for(var counts=0;counts<date.length;counts++){
        result[counts]=new Array();
    }
    for(var count=0;count<date.length;count++){
        result[count][0]=date[count];
        result[count][1]=open[count];
        result[count][2]=close[count];
        result[count][3]=low[count];
        result[count][4]=high[count];
    }
    return result;
}


function initPlateSituation(date,index) {
    var result;
    var url="/StockHubWeb/getTotalPlateInfo"+"?"+"date="+date;
    var xmlhttp;
    var txt,x,i;
    try {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                result=xmlhttp.responseText;
                var stockData=JSON.parse(result);
                // alert(stockData.averageOpen[index]);
                // stockData=KData;
                var h3=document.getElementById('H3ForM').innerHTML=stockData.totalVolume[index]+"亿";
                var h4=document.getElementById('H4ForM').innerHTML=stockData.increaseOrDecreaseMoney[index]+"亿("+stockData.increaseOrDecreaseRate[index]+"%)";
                var h6=document.getElementById('H6ForM').innerHTML=stockData.plateName[index];
                var p1=document.getElementById('P1ForM').innerHTML="市内公司数："+" "+stockData.companyNum[index];
                var p2=document.getElementById('P2ForM').innerHTML="平均开盘价："+" "+stockData.averageOpen[index];
                var p3=document.getElementById('P3ForM').innerHTML="平均收盘价："+" "+stockData.averageClose[index];
                var p4=document.getElementById('P4ForM').innerHTML="平均复权收盘价："+" "+stockData.averageAdjClose[index];

            }
        };
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取板指信息失败");
    }

}
function initPlateKChart(stockPool,start,end,componentId,stockTitle){

    var result;
    var url="/StockHubWeb/findSockPoolInfo"+"?"+"stockPool="+stockPool+"&"+"start="+startdate+"&"+"end="+enddate;
    var xmlhttp;
    var txt,x,i;
    try {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                result=xmlhttp.responseText;
                // alert(result);
                var myChart = echarts.init(document.getElementById(componentId));
                // 指定图表的配置项和数据
                //open ,close,low,high

                var stockData=getKFormData(eval(result));

                //测试数据
                // var stockData=[
                //     ['2013/1/24', 2320.26,2320.26,2287.3,2362.94],
                //     ['2013/1/25', 2300,2291.3,2288.26,2308.38],
                //     ['2013/1/28', 2295.35,2346.5,2295.35,2346.92],
                //     ['2013/1/29', 2347.22,2358.98,2337.35,2363.8],
                //     ['2013/1/30', 2360.75,2382.48,2347.89,2383.76],
                //     ['2013/1/31', 2383.43,2385.42,2371.23,2391.82],
                //     ['2013/2/1', 2377.41,2419.02,2369.57,2421.15],
                //     ['2013/2/4', 2425.92,2428.15,2417.58,2440.38],
                //     ['2013/2/5', 2411,2433.13,2403.3,2437.42],
                //     ['2013/2/6', 2432.68,2434.48,2427.7,2441.73],
                //     ['2013/2/7', 2430.69,2418.53,2394.22,2433.89],
                //     ['2013/2/8', 2416.62,2432.4,2414.4,2443.03],
                //     ['2013/2/18', 2441.91,2421.56,2415.43,2444.8],
                //     ['2013/2/19', 2420.26,2382.91,2373.53,2427.07],
                //     ['2013/2/20', 2383.49,2397.18,2370.61,2397.94],
                //     ['2013/2/21', 2378.82,2325.95,2309.17,2378.82],
                //     ['2013/2/22', 2322.94,2314.16,2308.76,2330.88],
                //     ['2013/2/25', 2320.62,2325.82,2315.01,2338.78],
                //     ['2013/2/26', 2313.74,2293.34,2289.89,2340.71],
                //     ['2013/2/27', 2297.77,2313.22,2292.03,2324.63],
                //     ['2013/2/28', 2322.32,2365.59,2308.92,2366.16],
                //     ['2013/3/1', 2364.54,2359.51,2330.86,2369.65],
                //     ['2013/3/4', 2332.08,2273.4,2259.25,2333.54],
                //     ['2013/3/5', 2274.81,2326.31,2270.1,2328.14],
                //     ['2013/3/6', 2333.61,2347.18,2321.6,2351.44],
                //     ['2013/3/7', 2340.44,2324.29,2304.27,2352.02],
                //     ['2013/3/8', 2326.42,2318.61,2314.59,2333.67],
                //     ['2013/3/11', 2314.68,2310.59,2296.58,2320.96],
                //     ['2013/3/12', 2309.16,2286.6,2264.83,2333.29],
                //     ['2013/3/13', 2282.17,2263.97,2253.25,2286.33],
                //     ['2013/3/14', 2255.77,2270.28,2253.31,2276.22],
                //     ['2013/3/15', 2269.31,2278.4,2250,2312.08],
                //     ['2013/3/18', 2267.29,2240.02,2239.21,2276.05],
                //     ['2013/3/19', 2244.26,2257.43,2232.02,2261.31],
                //     ['2013/3/20', 2257.74,2317.37,2257.42,2317.86],
                //     ['2013/3/21', 2318.21,2324.24,2311.6,2330.81],
                //     ['2013/3/22', 2321.4,2328.28,2314.97,2332],
                //     ['2013/3/25', 2334.74,2326.72,2319.91,2344.89],
                //     ['2013/3/26', 2318.58,2297.67,2281.12,2319.99],
                //     ['2013/3/27', 2299.38,2301.26,2289,2323.48],
                //     ['2013/3/28', 2273.55,2236.3,2232.91,2273.55],
                //     ['2013/3/29', 2238.49,2236.62,2228.81,2246.87]
                // ];

                //测试数据
                // stockData=getKFormData(KData);

                var data0 = splitData(stockData);


                function splitData(rawData) {
                    var categoryData = [];
                    var values = [];
                    for (var i = 0; i < rawData.length; i++) {
                        categoryData.push(rawData[i].splice(0, 1)[0]);
                        values.push(rawData[i])
                    }
                    return {
                        categoryData: categoryData,
                        values: values
                    };
                }

                function calculateMA(dayCount) {
                    var result = [];
                    for (var i = 0, len = data0.values.length; i < len; i++) {
                        if (i < dayCount) {
                            result.push('-');
                            continue;
                        }
                        var sum = 0;
                        for (var j = 0; j < dayCount; j++) {
                            sum += data0.values[i - j][1];
                        }
                        result.push(sum / dayCount);
                    }
                    return result;
                }



                option = {
                    title: {
                        text: stockTitle,
                        left: 0
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross'
                        }
                    },
                    legend: {
                        data: ['日K', 'MA5', 'MA10', 'MA20', 'MA30']
                    },
                    grid: {
                        left: '10%',
                        right: '10%',
                        bottom: '15%'
                    },
                    xAxis: {
                        type: 'category',
                        data: data0.categoryData,
                        scale: true,
                        boundaryGap : false,
                        axisLine: {onZero: false},
                        splitLine: {show: false},
                        splitNumber: 20,
                        min: 'dataMin',
                        max: 'dataMax'
                    },
                    yAxis: {
                        scale: true,
                        splitArea: {
                            show: true
                        }
                    },
                    dataZoom: [
                        {
                            type: 'inside',
                            start: 50,
                            end: 100
                        },
                        {
                            show: true,
                            type: 'slider',
                            y: '90%',
                            start: 50,
                            end: 100
                        }
                    ],
                    series: [
                        {
                            name: '日K',
                            type: 'candlestick',
                            data: data0.values,
                            markPoint: {
                                label: {
                                    normal: {
                                        formatter: function (param) {
                                            return param != null ? Math.round(param.value) : '';
                                        }
                                    }
                                },
                                data: [
                                    {
                                        name: 'XX标点',
                                        coord: ['2013/5/31', 2300],
                                        value: 2300,
                                        itemStyle: {
                                            normal: {color: 'rgb(41,60,85)'}
                                        }
                                    },
                                    {
                                        name: 'highest value',
                                        type: 'max',
                                        valueDim: 'highest'
                                    },
                                    {
                                        name: 'lowest value',
                                        type: 'min',
                                        valueDim: 'lowest'
                                    },
                                    {
                                        name: 'average value on close',
                                        type: 'average',
                                        valueDim: 'close'
                                    }
                                ],
                                tooltip: {
                                    formatter: function (param) {
                                        return param.name + '<br>' + (param.data.coord || '');
                                    }
                                }
                            },
                            markLine: {
                                symbol: ['none', 'none'],
                                data: [
                                    [
                                        {
                                            name: 'from lowest to highest',
                                            type: 'min',
                                            valueDim: 'lowest',
                                            symbol: 'circle',
                                            symbolSize: 10,
                                            label: {
                                                normal: {show: false},
                                                emphasis: {show: false}
                                            }
                                        },
                                        {
                                            type: 'max',
                                            valueDim: 'highest',
                                            symbol: 'circle',
                                            symbolSize: 10,
                                            label: {
                                                normal: {show: false},
                                                emphasis: {show: false}
                                            }
                                        }
                                    ],
                                    {
                                        name: 'min line on close',
                                        type: 'min',
                                        valueDim: 'close'
                                    },
                                    {
                                        name: 'max line on close',
                                        type: 'max',
                                        valueDim: 'close'
                                    }
                                ]
                            }
                        },
                        {
                            name: 'MA5',
                            type: 'line',
                            data: calculateMA(5),
                            smooth: true,
                            lineStyle: {
                                normal: {opacity: 0.5}
                            }
                        },
                        {
                            name: 'MA10',
                            type: 'line',
                            data: calculateMA(10),
                            smooth: true,
                            lineStyle: {
                                normal: {opacity: 0.5}
                            }
                        },
                        {
                            name: 'MA20',
                            type: 'line',
                            data: calculateMA(20),
                            smooth: true,
                            lineStyle: {
                                normal: {opacity: 0.5}
                            }
                        },
                        {
                            name: 'MA30',
                            type: 'line',
                            data: calculateMA(30),
                            smooth: true,
                            lineStyle: {
                                normal: {opacity: 0.5}
                            }
                        },

                    ]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);

            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取股票信息失败");
    }




}
function initPlatePieChart(StockPool,EndDate,ComponentId) {
    //下面实现饼状图
    var url="/StockHubWeb/getPlatePieVO"+"?"+"stockPoolBl="+StockPool+"&"+"date="+EndDate;
    var xmlhttp;
    var txt,x,i;
    try {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {

                var result=xmlhttp.responseText;
                var dataPack=JSON.parse(result);
                var decStop=dataPack.decStop;
                var rateLessNeg5=dataPack.rateLessNeg5;
                var rateFromNeg5ToZero=dataPack.rateFromNeg5ToZero;
                var rateFromZeroTo5=dataPack.rateFromZeroTo5;
                var rateMore5=dataPack.rateMore5;
                var incStop=dataPack.incStop;

                // var decStop=10;
                // var rateLessNeg5=12;
                // var rateFromNeg5ToZero=23;
                // var rateFromZeroTo5=46;
                // var rateMore5=23;
                // var incStop=5;

                var myChart = echarts.init(document.getElementById(ComponentId));

                option = {
                    title : {
                        text: '股市总体行情',
                        subtext: '(单日内)',
                        x:'center'
                    },
                    tooltip : {
                        trigger: 'item',
                        formatter: "{a} <br/>{b} : {c} ({d}%)"
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['decStop','rateLessNeg5','rateFromNeg5ToZero','rateFromZeroTo5','rateMore5','incStop']
                    },
                    series : [
                        {
                            name: '占比',
                            type: 'pie',
                            radius : '55%',
                            center: ['50%', '60%'],
                            data:[
                                {value:decStop, name:'跌停股'},
                                {value:rateLessNeg5, name:'跌幅大于5%'},
                                {value:rateFromNeg5ToZero, name:'跌幅0~5%'},
                                {value:rateFromZeroTo5, name:'涨幅0~5%'},
                                {value:rateMore5, name:'涨幅大于5%'},
                                {value:incStop, name:'涨停股'}
                            ],
                            itemStyle: {
                                emphasis: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }
        }

    }catch (e){
        alert("获取信息失败");
    }


}
function initList(type,stockPool,date,componentID) {
    var result;
    var url="/StockHubWeb/"+type+"?"+"date="+date+"&"+"stockPool="+stockPool;
    var xmlhttp;
    var txt,x,i;
    try {
        if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp = new XMLHttpRequest();
        }
        else {// code for IE6, IE5
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                var result=xmlhttp.responseText;
                // alert(result);
                initATable(date,result,componentID,10,7);

            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取排行榜信息失败");
    }

}
//初始化表格内容
function initATable(date,data,componentId,pageSize,pages) {

    //数据
    storage.setItem(componentId+"ForIndex",data);
    //初始化与否
    storage.setItem(componentId+"ForIndexInit","true");
    //获取数据的日期
    storage.setItem(componentId+"ForIndexDate",date);
    ///每页可容纳数
    storage.setItem(componentId+"ForIndexPageSize",pageSize);
    //存储最多的1-n页的n
    storage.setItem(componentId+"ForIndexPages",pages);

    var tableData=JSON.parse(data);
    initPages(tableData.length,pageSize,componentId,pages);
    // tableData=KData;
    // 路径配置
    var table = document.createElement("table");
    table.setAttribute("class","table table-hover");
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th1=document.createElement("th");
    th1.innerHTML="排行";
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4 = document.createElement("th");
    th4.innerHTML = "涨幅";
    var th5 = document.createElement("th");
    th5.innerHTML = "成交量";
    var th6 = document.createElement("th");
    th6.innerHTML = "开盘";
    var th7 = document.createElement("th");
    th7.innerHTML = "闭盘";
    var th8 = document.createElement("th");
    th8.innerHTML = "最低";
    var th9 = document.createElement("th");
    th9.innerHTML = "最高";


    //表头
    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableRow.appendChild(th8);
    tableRow.appendChild(th9);

    var th10 = document.createElement("th");
    th10.innerHTML = "行业";
    tableRow.appendChild(th10);

    tableHead.appendChild(tableRow);
    //将获取的信息逐行压入列表
    for (var i = 0; i < tableData.length&&i<pageSize; i++)
    {
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        var a1=document.createElement("a");
        a1.setAttribute("href","stock.html?codeOrName="+tableData[i].code);
        a1.innerHTML=tableData[i].code;
        td2.appendChild(a1);
        var td3=document.createElement("td");
        var a2=document.createElement("a");
        a2.setAttribute("href","stock.html?codeOrName="+tableData[i].code);
        a2.innerHTML=tableData[i].name;
        td3.appendChild(a2);
        var td4=document.createElement("td");
        td4.innerHTML=tableData[i].increaseOrDecrease;
        var td5=document.createElement("td");
        td5.innerHTML=tableData[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=tableData[i].open;
        var td7=document.createElement("td");
        td7.innerHTML=tableData[i].close;
        var td8=document.createElement("td");
        td8.innerHTML=tableData[i].low;
        var td9=document.createElement("td");
        td9.innerHTML=tableData[i].high;



        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);

        var td10=document.createElement("td");
        var a3=document.createElement("a");
        a3.setAttribute("href","industry.html?industryName="+tableData[i].industry);
        a3.innerHTML=tableData[i].industry;
        td10.appendChild(a3);
        tr.appendChild(td10);

        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);


    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById(componentId).innerHTML=tableDiv.innerHTML;
}


//初始化表格分页框,页表框id为componentId+'Pages'，页项class与要去改变的内容框的id componentId相同,页表id为componentId+"menuForPages"，但大概用不上
//pages为总共项数,pagesize为一页的项数
function initPages(pageItems,pageSize,componentId,pages) {
    // alert(pages);
    var pagesIndexs;
    if(pageItems%pageSize==0){
        pagesIndexs=pageItems/pageSize;
    }else{
        pagesIndexs=(pageItems-pageItems%pageSize)/pageSize+1;
    }

    var menu=document.createElement('ul');
    menu.setAttribute("id","menuForPages");

    //backtotop

    var li=document.createElement('li');
    var a=document.createElement('a');
    a.setAttribute("class",componentId);
    a.setAttribute("href","javascript:void(0);");
    a.setAttribute("onclick","javascript:turnPages(this.innerHTML,this.getAttribute('class'));");
    a.innerHTML="首页";
    li.appendChild(a);
    menu.appendChild(li);

    for(var count=0;(count<pagesIndexs)&&(count<pages);count++){
        // alert(count);
        var li1=document.createElement('li');
        var a1=document.createElement('a');
        a1.setAttribute("class",componentId);
        a1.setAttribute("id",componentId+count);
        a1.setAttribute("href","javascript:void(0);");
        a1.setAttribute("onclick","javascript:turnPages(this.innerHTML,this.getAttribute('class'));");
        a1.innerHTML=count+1+"";
        li1.appendChild(a1);
        menu.appendChild(li1);
    }

    //goToEnd
    var li2=document.createElement('li');
    var a2=document.createElement('a');
    a2.setAttribute("class",componentId);
    a2.setAttribute("id",componentId+count);
    a2.setAttribute("href","javascript:void(0);");
    a2.setAttribute("onclick","javascript:turnPages(this.innerHTML,this.getAttribute('class'));");
    a2.innerHTML="尾页";
    li2.appendChild(a2);
    menu.appendChild(li2);

    var nav=document.createElement('nav');
    nav.setAttribute("style","text-align: center");
    nav.appendChild(menu);
    var div=document.createElement('div');
    div.appendChild(nav);
    var pagesDiv=document.getElementById(componentId+'Pages').innerHTML=div.innerHTML;
    // alert(pagesDiv);
}
//第几页以及具体的排行榜对应的id，翻页改变表格内容的响应
function turnPages(pageIndex,componentId) {
    // alert(pageIndex+"  "+componentId);
    var data=storage.getItem(componentId+"ForIndex");
    var pageSize=storage.getItem(componentId+"ForIndexPageSize");
    var tableData=JSON.parse(data);
    var length=tableData.length;
    if(pageIndex=="首页"){
        pageIndex=1;
    }else if(pageIndex=="尾页"){
        if(length%pageSize==0){
            pageIndex=length/pageSize;
        }else{
            pageIndex=(length-length%pageSize)/pageSize+1;
        }
    }
    reRankPages(pageIndex,pageSize,length,componentId);
    var table = document.createElement("table");
    table.setAttribute("class","table table-hover");
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th1=document.createElement("th");
    th1.innerHTML="排行";
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4 = document.createElement("th");
    th4.innerHTML = "涨幅";
    var th5 = document.createElement("th");
    th5.innerHTML = "成交量";
    var th6 = document.createElement("th");
    th6.innerHTML = "开盘";
    var th7 = document.createElement("th");
    th7.innerHTML = "闭盘";
    var th8 = document.createElement("th");
    th8.innerHTML = "最低";
    var th9 = document.createElement("th");
    th9.innerHTML = "最高";


    //表头
    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableRow.appendChild(th8);
    tableRow.appendChild(th9);

    var th10 = document.createElement("th");
    th10.innerHTML = "行业";
    tableRow.appendChild(th10);

    tableHead.appendChild(tableRow);
    //将获取的信息逐行压入列表
    for (var i = (pageIndex-1)*pageSize; i < tableData.length && i<pageIndex*pageSize-1; i++)
    {
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        var a1=document.createElement("a");
        a1.setAttribute("href","stock.html?codeOrName="+tableData[i].code);
        a1.innerHTML=tableData[i].code;
        td2.appendChild(a1);
        var td3=document.createElement("td");
        var a2=document.createElement("a");
        a2.setAttribute("href","stock.html?codeOrName="+tableData[i].code);
        a2.innerHTML=tableData[i].name;
        td3.appendChild(a2);
        var td4=document.createElement("td");
        td4.innerHTML=tableData[i].increaseOrDecrease;
        var td5=document.createElement("td");
        td5.innerHTML=tableData[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=tableData[i].open;
        var td7=document.createElement("td");
        td7.innerHTML=tableData[i].close;
        var td8=document.createElement("td");
        td8.innerHTML=tableData[i].low;
        var td9=document.createElement("td");
        td9.innerHTML=tableData[i].high;



        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tr.appendChild(td8);
        tr.appendChild(td9);

        var td10=document.createElement("td");
        var a3=document.createElement("a");
        a3.setAttribute("href","industry.html?industryName="+tableData[i].industry);
        a3.innerHTML=tableData[i].industry;
        td10.appendChild(a3);
        tr.appendChild(td10);

        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);


    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById(componentId).innerHTML=tableDiv.innerHTML;

}
//点击了第几页，每页有多少数据，共有多少数据，表格id;将翻页按钮的坐标更新
function reRankPages(pageIndex,pageSize,length,componentId) {
    pageIndex=parseInt(pageIndex);
    var pages= storage.getItem(componentId+"ForIndexPages");//可有的pages
    pages=parseInt(pages);
    var max;
    if(length%pageSize==0){
        max=length/pageSize;
        max=parseInt(max);
    }else{
        max=length/pageSize+1;
        max=parseInt(max);
    }
    if(pageIndex+pages-1<=max){//从pageIndex开始
        for(var count=0;count<pages;count++){
            // alert("pageindex1:"+count+pageIndex);
            document.getElementById(componentId+count).innerHTML=count+pageIndex;
        }
    }else{//从max-pages+1开始
        for(var count2=0;count2<pages;count2++){
            // alert("pageindex2:"+count2+max-pages+1);
            document.getElementById(componentId+count2).innerHTML=count2+max-pages+1+"";
        }
    }
    // alert("pageindex:"+pageIndex+"pages:"+pages)

}
