/**
 * Created by A on 2017/5/25.
 */
    // document.write("<script language=javascript src='../echarts.min.js'></script>");

function initFirst() {
    var codeOrName=getArgsFromHref("codeOrName");
    if((codeOrName==null)||(codeOrName=='')){//如果不存在

    }else {//如果有codeOrName，那么久加载页面

        initInterface(codeOrName);
    }
}

/**
 * 根据链接获取参数值
 * @param code 要获取的参数名称
 * @returns {string} 参数值
 * @constructor
 */
function getArgsFromHref(code)
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

function initInterface(codeOrName) {
    //获取当前时间
    var myDate = new Date();
    // var nowY = myDate.getFullYear();
    // var nowM = myDate.getMonth()+1;
    // var nowD = myDate.getDate();
    // var date = nowM+"/"+nowD+"/"+nowY%100;//当前日期
    var date="6/8/17";
    var start="2/1/16";
    var tempStart="2/1/16";
    // var start=date;
    // var tempStart1 = new Date(myDate-1000 * 60 * 60 * 24*90 );
    // var start=(tempStart1.getMonth()+1)+"/"+tempStart1.getDate()+"/"+(tempStart1.getFullYear()%100);
    //
    // var tempStart2 = new Date(myDate-1000 * 60 * 60 * 24*90 );
    // var tempStart=(tempStart2.getMonth()+1)+"/"+tempStart2.getDate()+"/"+(tempStart2.getFullYear()%100);
    // var industryName=getIndustryName(codeOrName,start,date);//这个参数中的start最好改成tempStart

    // alert("codeOrName="+codeOrName+",start="+start+",date="+date);

    var industryName="";//"银行";//getIndustryName(codeOrName,start,date);//初始化全局变量
    var code="";
    var name="";
    var dataPack=[];
    var tempUrl="";
    if((codeOrName.charAt(0)>='0')&&(codeOrName.charAt(0)<='9')){//如果是code
        tempUrl="/StockHubWeb/getDataByCode?" +
            "code="+codeOrName+"&start="+tempStart+"&end="+date;
    }else {
        tempUrl="/StockHubWeb/getDataByName?" +
            "name="+codeOrName+"&start="+tempStart+"&end="+date;
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var result=xmlhttp.responseText;
            dataPack=JSON.parse(result);
            industryName=dataPack[0].industry;
            code=dataPack[0].code;
            name=dataPack[0].name;
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();

    //下面进行界面的初始化
    document.getElementById('chooseStockArea').innerHTML="";
    initSingleStockSimpleKChart(codeOrName,start,date);
    initSingleStockSimplePieChart(date,industryName);
    initSingleStockRadarChart(codeOrName,start,date);
    initSingleStockKAndEMAChart(codeOrName,start,date);
    addStockIndustryTableS();
    initIncreaseTable(date,industryName);
    initDecreaseTable(date,industryName);
    initVolumeTable(date,industryName);
    initPotentialTable(date,industryName);
    initCompanyInfo(code);
    initSimpleEventInfo(date,industryName);
    initSimpleTrendInfo(date,industryName);
    initSimpleInvestInfo(date,industryName);
}

//用户点击个股的搜索按钮，进行的搜索
function getStockWholeData() {
    var codeOrName=document.getElementById("searchStockCodeOrNameInput").value;
    if((codeOrName!=null)&&(codeOrName!="")){
        var isExist=isStockExist(codeOrName);
        if(isExist=="true"){//如果股票存在，操作
            //到时候需要将下面操作的代码放到这里
            initInterface(codeOrName);
        }else {
            alert("抱歉！您输入的股票不存在");
        }
    }else {
        alert("请输入股票名称或代码");
    }
}

//判断股票是否存在
function isStockExist(codeOrName) {

    var dataPack=[];
    var tempUrl="/StockHubWeb/isStockExist?" +
        "stockCodeOrName="+codeOrName;

    var isExist=false;

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            isExist=xmlhttp.responseText;
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();
    return isExist;
}

//设置股票简单数据及简易K线图
function initSingleStockSimpleKChart(code, start,end) {
    var codeStr=code.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    // var codeStr="000001";
    // var startStr="2/1/16";
    // var endStr="5/30/17";

    var dataPack=[];
    var tempUrl="/StockHubWeb/getSimpleKChartInfo?" +
        "code="+codeStr+"&start="+startStr+"&end="+endStr;

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            dataPack=JSON.parse(result);
            paintSingleStockSimpleKChart(dataPack);
            // dataPack=JSON.parse(result);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();

    // dataPack=JSON.parse(dataPack);


}

function paintSingleStockSimpleKChart(dataPack) {
    // alert(dataPack+"最后"+dataPack.lastDateIncOrDecRate+"dataPack.stockName="+dataPack.stockName
    // +"dataPack.lastDatePrice="+dataPack.lastDatePrice);
    //以下显示股票的信息
    //设置股票简易数据
    var inOrDeMoney=dataPack.lastDateIncOrDecRate;
    var iOrDeRate=(parseFloat(inOrDeMoney)/parseFloat(dataPack.lastDatePrice)*100).toFixed(2)+"%";
    // alert("inOrDeMoney="+inOrDeMoney+",iOrDeRate="+iOrDeRate);
    document.getElementById('singleStockName').innerHTML=dataPack.stockName;//codeStr+"start:"+startStr+"end:"+endStr;
    document.getElementById('singleStockCurrent').innerHTML=dataPack.lastDatePrice;
    document.getElementById('singleStockInOrDe').innerHTML
        =iOrDeRate+"("+inOrDeMoney+")";
    // document.getElementById('stockExe').innerHTML="<input id='addButton1' type='image' src='images/添加.png' value='"+dataPack.stockCode+"' width='15%' height='15%' onclick='addSelfStock()'/> <br> <input type='image' src='images/购买.png' width='15%' height='15%'  data-toggle='modal' data-target='#buyDiv'> ";

    //设置股票简易k线图表
    // alert("这次需要的"+dataPack.date+",1="+dataPack.inOrDeYield+",2="+dataPack.adjClose);
    var xData=[];//dataPack.date;//横坐标
    var iOrDsData=[];//dataPack.inOrDeYield;//纵坐标1
    var stockPriceData=[];//dataPack.adjClose;//纵坐标2
    for(var i=0;i<dataPack.date.length;i++){
        xData[i]=dataPack.date[i];
        iOrDsData[i]=parseFloat(dataPack.inOrDeYield[i]);
        stockPriceData[i]=parseFloat(dataPack.adjClose[i]);
    }
    // alert("in line 264:x="+xData+"\n"+"iOrDe="+iOrDsData+"\nprice="+stockPriceData
    //     +"\nxlenght="+xData.length+"\niOrDelength="+iOrDsData.length+"\nstockPrice="+stockPriceData.length);

    var colors = ['#5793f3', '#d14a61', '#675bba'];

    option = {
        color: colors,

        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        grid: {
            right: '20%'
        },
        toolbox: {
            feature: {
                dataView: {show: true, readOnly: false},
                restore: {show: true},
                saveAsImage: {show: true}
            }
        },
        legend: {
            data:['涨跌幅','股票价格']
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                data: xData
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '涨跌幅',
                min: -15,
                max: 15,
                position: 'right',
                axisLine: {
                    lineStyle: {
                        color: colors[0]
                    }
                },
                axisLabel: {
                    formatter: '{value} %'
                }
            },
            {
                type: 'value',
                name: '股票价格',
                min: 0,
                // max: 25,
                position: 'left',
                axisLine: {
                    lineStyle: {
                        color: colors[2]
                    }
                }
            }
        ],
        series: [
            {
                name:'涨跌幅',
                type:'line',
                data:iOrDsData
            },
            {
                name:'股票价格',
                type:'line',
                yAxisIndex: 1,
                data:stockPriceData
            }
        ]
    };

    var myChart = echarts.init(document.getElementById("singleStockSimpleKChart"));
    myChart.setOption(option);
}

//设置股票所在行业的饼状图
function initSingleStockSimplePieChart(date, industryName) {
    var dataPack="";
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    // alert("in line 255:"+industryNameStr+","+industryName);
    var tempUrl="/StockHubWeb/findIndustryPieVO?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            //下面实现饼状图
            paintSingleStockSimplePieChart(dataPack,industryNameStr);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();

}

function paintSingleStockSimplePieChart(dataPack,industryName) {
    // document.getElementById("stockSIndustry").innerHTML=
    //     "<h5>股票所在行业：</h5>"+"<a href='specificIndustry.html?industryName="+industryName+" ' target='_blank'>"+industryName+"</a>";

    var myChart = echarts.init(document.getElementById('singleStockSimplePieChart'));
    var decStop=dataPack.decStop;
    var rateLessNeg5=dataPack.rateLessNeg5;
    var rateFromNeg5ToZero=dataPack.rateFromNeg5ToZero;
    var rateFromZeroTo5=dataPack.rateFromZeroTo5;
    var rateMore5=dataPack.rateMore5;
    var incStop=dataPack.incStop;
    // alert("in line 346:"+decStop+" "+rateMore5);
    option = {
        title : {
            text: industryName+"行业股票涨跌情况统计",
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['跌停','-10%~-5%','-5%~0%','0%~5%','5%~10%','涨停']
        },
        series : [
            {
                name: '股票涨跌情况',
                type: 'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:decStop, name:'跌停'},
                    {value:rateLessNeg5, name:'-10%~-5%'},
                    {value:rateFromNeg5ToZero, name:'-5%~0%'},
                    {value:rateFromZeroTo5, name:'0%~5%'},
                    {value:rateMore5, name:'5%~10%'},
                    {value:incStop, name:'涨停'}
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
    // document.getElementById('singleStockSimplePieChart').innerHTML=option;
    myChart.setOption(option);
}

//设置股票的分数雷达图
function initSingleStockRadarChart(stockNameOrCode, start, end) {
    var dataPack;
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    var tempUrl="";
    var tempData=[];
    if(start.toString()==end.toString()){
        tempUrl="/StockHubWeb/getStockGoalOne?" +
            "codeOrName="+stockNameOrCodeStr+"&date="+startStr;
        // tempData={"codeOrName":stockNameOrCodeStr,"date":startStr};
    }else {
        tempUrl="/StockHubWeb/getStockGoal?" +
            "codeOrName="+stockNameOrCodeStr+"&start="+startStr+"&end="+endStr;
        // tempData={"codeOrName":stockNameOrCodeStr,"start":startStr,"end":endStr};
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            //以下将信息显示在雷达图中
            paintSingleStockRadarChart(dataPack,stockNameOrCodeStr);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintSingleStockRadarChart(dataPack,stockNameOrCodeStr) {
    var myChart = echarts.init(document.getElementById('singleStockSimpleRadarChart'));
    // Schema:
// date,AQIindex,PM2.5,PM10,CO,NO2,SO2
    var oneData=[parseInt(dataPack.stability),parseInt(dataPack.prof),parseInt(dataPack.mobility),
        parseInt(dataPack.safety),parseInt(dataPack.development)];

    option = {
        title: {
            text: '总评:'+dataPack.total
        },
        tooltip: {
            trigger: 'axis'
        },
        // legend: {
        //     // x: 'center',
        //     data:[stockNameOrCodeStr]
        // },
        radar: [
            {
                indicator: [
                    {text: '稳定性', max: 100},
                    {text: '收益性', max: 100},
                    {text: '流动性', max: 100},
                    {text: '安全性', max: 100},
                    {text: '成长性', max: 100}
                ],
                // center: ['25%','40%'],
                // radius: 80
            }
        ],
        series: [
            {
                type: 'radar',
                tooltip: {
                    trigger: 'item'
                },
                itemStyle: {normal: {areaStyle: {type: 'default'}}},
                data: [
                    {
                        value: oneData,
                        name: stockNameOrCodeStr
                    }
                ]
            }
        ]
    };

    myChart.setOption(option);
}

//设置股票K线图和均线图
function initSingleStockKAndEMAChart(stockNameOrCode,start,end) {
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    var dataPack="";
    var tempUrl="";
    var tempData="";
    if((stockNameOrCodeStr.charAt(0)>='0')&&(stockNameOrCodeStr.charAt(0)<='9')){//如果是code
        tempUrl="/StockHubWeb/getDataByCode?" +
            "code="+stockNameOrCodeStr+"&start="+startStr+"&end="+endStr;
        // tempData={"code":stockNameOrCodeStr,"start":startStr,"end":endStr};
    }else {
        tempUrl="/StockHubWeb/getDataByName?" +
            "name="+stockNameOrCodeStr+"&start="+startStr+"&end="+endStr;
        // tempData={"name":stockNameOrCodeStr,"start":startStr,"end":endStr};
    }
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintSingleStockKAndEMAChart(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();

}

function paintSingleStockKAndEMAChart(dataPack) {
    // 数据意义：开盘(open)，收盘(close)，最低(lowest)，最高(highest)


    var data=[];
    for (var i=0;i<dataPack.length;i++){
        var tempWholeData=dataPack[i];
        data[i]=[tempWholeData.date,parseFloat(tempWholeData.open),
            parseFloat(tempWholeData.close),parseFloat(tempWholeData.low),parseFloat(tempWholeData.high)];
    }
    var data0 = splitData(data);

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

    var stockName=dataPack[0].name.toString();
    option = {
        title: {
            text: stockName,
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
    // document.getElementById('singleStockKAndEMAChart').innerHTML=option;
    var myChart = echarts.init(document.getElementById('singleStockKAndEMAChart'));
    myChart.setOption(option);
}

//在界面增加行业内榜单的组件
function addStockIndustryTableS() {
    document.getElementById("stockIndustryTablesAll").innerHTML=
        "<h3 class=\"hdg\">行业内排行榜</h3>"
        +"<div id=\"stockIndustryTables\" class=\"bs-example bs-example-tabs\" role=\"tabpanel\" data-example-id=\"togglable-tabs\"></div>";
    document.getElementById("stockIndustryTables").innerHTML+=
        "<ul id=\"myTab1\" class=\"nav nav-tabs\" role=\"tablist\"></ul>";
    document.getElementById("myTab1").innerHTML+=
        "<li role=\"presentation\" class=\"active\"><a href=\"#industryInc\" id=\"industryIncTab\" role=\"tab\" data-toggle=\"tab\" aria-controls=\"industryInc\" aria-expanded=\"true\">涨幅榜</a></li>"
        +"<li role=\"presentation\"><a href=\"#industryDec\" role=\"tab\" id=\"industryDecTab\" data-toggle=\"tab\" aria-controls=\"industryDec\">跌幅榜</a></li>"
        +"<li role=\"presentation\"><a href=\"#indusrtyVolume\" role=\"tab\" id=\"indusrtyVolumeTab\" data-toggle=\"tab\" aria-controls=\"indusrtyVolume\">成交量榜</a></li>"
        +"<li role=\"presentation\"><a href=\"#industryPotential\" role=\"tab\" id=\"industryPotentialTab\" data-toggle=\"tab\" aria-controls=\"industryPotential\">潜力榜</a></li>";
    document.getElementById("stockIndustryTables").innerHTML+=
        "<div id=\"myTabContent1\" class=\"tab-content\"></div>";
    document.getElementById("myTabContent1").innerHTML+=
        "<div role=\"tabpanel\" class=\"tab-pane fade in active\" id=\"industryInc\" aria-labelledby=\"industryIncTab\">" +
        "<div class=\"bs-docs-example \"><table id=\"increaseTable\" class=\"table table-hover\"></table></div>" +
        "</div>"
        +"<div role=\"tabpanel\" class=\"tab-pane fade\" id=\"industryDec\" aria-labelledby=\"industryDecTab\">" +
        "<table id=\"decreaseTable\" class=\"table table-hover\"></table>" +
        "</div>"
        +"<div role=\"tabpanel\" class=\"tab-pane fade\" id=\"indusrtyVolume\" aria-labelledby=\"indusrtyVolumeTab\">" +
        "<table id=\"volumeTable\" class=\"table table-hover\"></table>" +
        "</div>"
        +"<div role=\"tabpanel\" class=\"tab-pane fade\" id=\"industryPotential\" aria-labelledby=\"industryPotentialTab\">" +
        "<table id=\"potentialTable\" class=\"table table-hover\"></table>" +
        "</div>";
}

//设置股票所在行业涨幅榜
function initIncreaseTable(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findIndustryMaxInRate?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintIncreaseTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintIncreaseTable(dataPack) {
    //以下实现行业涨幅榜
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4=document.createElement("th");
    th4.innerHTML="涨幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="开盘";
    var th9=document.createElement("th");
    th9.innerHTML="收盘";
    var th10=document.createElement("th");
    th10.innerHTML="板块";

    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th9);
    tableRow.appendChild(th10);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td2=document.createElement("td");
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].open;
        var td9=document.createElement("td");
        td9.innerHTML=dataPack[i].close;
        var td10=document.createElement("td");
        var plate=toPlatePara(dataPack[i].plate);
        td10.innerHTML="<a href='plate.html?plate="+plate+"'target='_blank'>"+dataPack[i].plate+"</a>";
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('increaseTable').innerHTML=tableDiv.innerHTML;
}

//设置股票所在行业跌幅榜
function initDecreaseTable(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findIndustryMaxDeRate?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintDecreaseTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintDecreaseTable(dataPack) {
    //以下实现行业涨幅榜
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4=document.createElement("th");
    th4.innerHTML="跌幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="开盘";
    var th9=document.createElement("th");
    th9.innerHTML="收盘";
    var th10=document.createElement("th");
    th10.innerHTML="板块";

    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th9);
    tableRow.appendChild(th10);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td2=document.createElement("td");
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].open;
        var td9=document.createElement("td");
        td9.innerHTML=dataPack[i].close;
        var td10=document.createElement("td");
        var plate=toPlatePara(dataPack[i].plate);
        td10.innerHTML="<a href='plate.html?plate="+plate+"'target='_blank'>"+dataPack[i].plate+"</a>";
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('decreaseTable').innerHTML=tableDiv.innerHTML;
}

//设置股票所在行业成交量榜
function initVolumeTable(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findIndustryMaxInVolume?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintVolumeTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintVolumeTable(dataPack) {
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="开盘";
    var th9=document.createElement("th");
    th9.innerHTML="收盘";
    var th10=document.createElement("th");
    th10.innerHTML="板块";

    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th9);
    tableRow.appendChild(th10);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td2=document.createElement("td");
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].open;
        var td9=document.createElement("td");
        td9.innerHTML=dataPack[i].close;
        var td10=document.createElement("td");
        var plate=toPlatePara(dataPack[i].plate);
        td10.innerHTML="<a href='plate.html?plate="+plate+"'target='_blank'>"+dataPack[i].plate+"</a>";
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('volumeTable').innerHTML=tableDiv.innerHTML;
}

//设置股票所在行业潜力榜
function initPotentialTable(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findIndustryPotential?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintPotentialTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintPotentialTable(dataPack) {
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="开盘";
    var th9=document.createElement("th");
    th9.innerHTML="收盘";
    var th10=document.createElement("th");
    th10.innerHTML="板块";

    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th9);
    tableRow.appendChild(th10);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td2=document.createElement("td");
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].open;
        var td9=document.createElement("td");
        td9.innerHTML=dataPack[i].close;
        var td10=document.createElement("td");
        var plate=toPlatePara(dataPack[i].plate);
        td10.innerHTML="<a href='plate.html?plate="+plate+"'target='_blank'>"+dataPack[i].plate+"</a>";
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('potentialTable').innerHTML=tableDiv.innerHTML;
}

//设置公司信息表格
function initCompanyInfo(code) {
    var codeStr=code.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/getCompanyInfo?" +
        "code="+codeStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintCompanyInfo(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintCompanyInfo(dataPack) {
    //解析数据
    var stockName=dataPack.stockName;//股票名称
    var industry=dataPack.industry;//行业
    var area=dataPack.area;//地区
    var totalassets=dataPack.totalassets;//总资产
    var liquidassets=dataPack.liquidassets;//流动资产
    var fixedassets=dataPack.fixedassets;//固定资产
    var uptime=dataPack.uptime;//上市日期
    var holders=dataPack.holders;//股东人数

    //在界面产生表格
    document.getElementById("companyInfoTableArea").innerHTML=
        "<h3 class=\"hdg \">公司资料</h3>" +
        "<div class=\"bs-docs-example \"> <table id=\"companyInfoTable\" class=\"table table-bordered\"> </table> </div>";

    //在界面表格显示
    var table = document.createElement("table");
    var tableBody=document.createElement("tbody");

    var tr1=document.createElement("tr");
    var td1=document.createElement("td");
    td1.innerHTML="公司名称";
    var td2=document.createElement("td");
    td2.innerHTML=stockName;
    tr1.appendChild(td1);
    tr1.appendChild(td2);
    tableBody.appendChild(tr1);

    var tr2=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="所属行业";
    td2=document.createElement("td");
    td2.innerHTML="<a href='specificIndustry.html?industryName="+industry+" ' target='_blank'>"+industry+"</a>";
    tr2.appendChild(td1);
    tr2.appendChild(td2);
    tableBody.appendChild(tr2);

    var tr3=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="公司所在地";
    td2=document.createElement("td");
    td2.innerHTML=area;
    tr3.appendChild(td1);
    tr3.appendChild(td2);
    tableBody.appendChild(tr3);

    var tr4=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="上市日期";
    td2=document.createElement("td");
    td2.innerHTML=uptime;
    tr4.appendChild(td1);
    tr4.appendChild(td2);
    tableBody.appendChild(tr4);

    var tr5=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="股东人数";
    td2=document.createElement("td");
    td2.innerHTML=holders;
    tr5.appendChild(td1);
    tr5.appendChild(td2);
    tableBody.appendChild(tr5);

    var tr6=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="总资产";
    td2=document.createElement("td");
    td2.innerHTML=totalassets;
    tr6.appendChild(td1);
    tr6.appendChild(td2);
    tableBody.appendChild(tr6);

    var tr7=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="流动资产";
    td2=document.createElement("td");
    td2.innerHTML=liquidassets;
    tr7.appendChild(td1);
    tr7.appendChild(td2);
    tableBody.appendChild(tr7);

    var tr8=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="固定资产";
    td2=document.createElement("td");
    td2.innerHTML=fixedassets;
    tr8.appendChild(td1);
    tr8.appendChild(td2);
    tableBody.appendChild(tr8);

    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('companyInfoTable').innerHTML=tableDiv.innerHTML;
}

//初始化重大事项的简单版list
function initSimpleEventInfo(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/getSimpleEventInfo?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintSimpleEventInfo(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintSimpleEventInfo(dataPack) {
    document.getElementById("hugeEventArea").innerHTML=
        "<h3 class=\"hdg \">重大事项</h3>" +
        "<ul id=\"hugeEvent\" class=\"list-group \"></ul>";

    //显示获取的数据
    for(var i=0;i<dataPack.length;i++){
        var hrefStr="mainEvent.html?title="+dataPack[i].title+"&time="+dataPack[i].time;
        document.getElementById("hugeEvent").innerHTML+=
            "<li class=\"list-group-item\"><a href="+hrefStr+" target='_blank'>"+dataPack[i].title+"  "+dataPack[i].time+"</a></li>";
    }
}

//初始化行情走势的简单版list
function initSimpleTrendInfo(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/getSimpleTrendInfo?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintSimpleTrendInfo(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintSimpleTrendInfo(dataPack) {
    document.getElementById("hugeEventArea").innerHTML=
        "<h3 class=\"hdg \">行情走势</h3> <ul id=\"trendInfo\" class=\"list-group \"> </ul>";

    //显示获取的数据
    for(var i=0;i<dataPack.length;i++){
        var hrefStr="trend.html?title="+dataPack[i].title+"&time="+dataPack[i].time;
        document.getElementById("trendInfo").innerHTML+=
            "<li class=\"list-group-item\"><a href="+hrefStr+" target='_blank'>"+dataPack[i].title+"  "+dataPack[i].time+"</a></li>";
    }
}

//初始化投资参考的简单版list
function initSimpleInvestInfo(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/getSimpleInvestInfo?" +
        "date="+dateStr+"&industryName="+industryNameStr;
    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintSimpleInvestInfo(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintSimpleInvestInfo(dataPack) {
    //显示获取的数据
    document.getElementById("investNewsArea").innerHTML=
        "<h3 class=\"hdg\">投资新闻</h3>" +
        "<ul id=\"investNews\" class=\"list-group \"> </ul>";
    for(var i=0;i<dataPack.length;i++){
        document.getElementById("investNews").innerHTML+="<li class=\"list-group-item\"><a>"+dataPack[i].title+"  "+dataPack[i].time+"</a></li>";
    }
}

//选择器选择股票功能
function chooser() {
   //先获取界面数据
    var dataTemp=document.getElementById("date").value;
    var date=parseInt(dataTemp.charAt(5)+dataTemp.charAt(6)).toString()+"/"+
        parseInt(dataTemp.charAt(8)+dataTemp.charAt(9)).toString()+"/"+
        dataTemp.charAt(2)+dataTemp.charAt(3);

    var stockPool="ALL";
    var plateSelect=document.getElementById('plateSelect');
    var plateIndex=plateSelect.selectedIndex;//selectedIndex代表的是用户所选中项的index
    if(plateIndex==0){
        stockPool="ALL";
    }else if(plateIndex==1){
        stockPool="SHANGZHENG";
    }else if(plateIndex==2){
        stockPool="SHENZHENG";
    }else if(plateIndex==3){
        stockPool="HUSHEN300";
    }else if(plateIndex==4){
        stockPool="SMALLMIDDLEPLATE";
    }else if(plateIndex==5){
        stockPool="STARTUPPLATE";
    }

    /**
     * private String date;
     private StockPool stockPool;
     private String industryPool;
     private ArrayList<String> open1，open2;//当日开盘价范围,第一个小值，第二个大值
     private ArrayList<String> close;//当日收盘价范围,第一个小值，第二个大值
     private ArrayList<String> inDecrease;//当日股价涨跌幅范围,第一个小值，第二个大值
     private ArrayList<String> volume;//当日总交易量范围,第一个小值，第二个大值
     */
    var industryPool=document.getElementById("industry").value;
    if((industryPool==null)||(industryPool=="")){
        industryPool="empty";
    }
    var open1=document.getElementById("openLowest").value;
    if((open1==null)||(open1=="")){
        open1="empty";
    }
    var open2=document.getElementById("openHighest").value;
    if((open2==null)||(open2=="")){
        open2="empty";
    }
    var close1=document.getElementById("closeLowest").value;
    if((close1==null)||(close1=="")){
        close1="empty";
    }
    var close2=document.getElementById("closeHighest").value;
    if((close2==null)||(close2=="")){
        close2="empty";
    }
    var inDecrease1=document.getElementById("iOrDeLowest").value;
    if((inDecrease1==null)||(inDecrease1=="")){
        inDecrease1="empty";
    }
    var inDecrease2=document.getElementById("iOrDeHighest").value;
    if((inDecrease2==null)||(inDecrease2=="")){
        inDecrease2="empty";
    }
    var volume1=document.getElementById("volumeLowest").value;
    if((volume1==null)||(volume1=="")){
        volume1="empty";
    }
    var volume2=document.getElementById("volumeHighest").value;
    if((volume2==null)||(volume2=="")){
        volume2="empty";
    }

    //调用逻辑层方法
    var dataPack=[];
    var tempUrl="/StockHubWeb/choose?" +
        "date="+date+"&stockPool="+stockPool+"&industryPool="+industryPool+
        "&open1="+open1+"&open2="+open2+"&close1="+close1+"&close2="+close2+
        "&inDecrease1="+inDecrease1+"&inDecrease2="+inDecrease2+
        "&volume1="+volume1+"&volume2="+volume2;

    var xmlhttp;
    if (window.XMLHttpRequest)
    {// code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }
    else
    {// code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {

            var result=xmlhttp.responseText;
            // dataPack=eval(result);
            dataPack=JSON.parse(result);
            paintChooser(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();

}

function paintChooser(dataPack) {
    var table = document.createElement("table");

    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th2=document.createElement("th");
    th2.innerHTML="代码";
    var th3=document.createElement("th");
    th3.innerHTML="名称";
    var th4=document.createElement("th");
    th4.innerHTML="涨幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="开盘";
    var th9=document.createElement("th");
    th9.innerHTML="收盘";
    var th10=document.createElement("th");
    th10.innerHTML="行业";
    var th11=document.createElement("th");
    th11.innerHTML="板块";

    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th9);
    tableRow.appendChild(th10);
    tableRow.appendChild(th11);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>20){
        length=20;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].code;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].name;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].open;
        var td9=document.createElement("td");
        td9.innerHTML=dataPack[i].close;
        var td10=document.createElement("td");
        td10.innerHTML=dataPack[i].industry;
        var td11=document.createElement("td");
        td11.innerHTML=dataPack[i].plate;
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td9);
        tr.appendChild(td10);
        tr.appendChild(td11);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('chooseStockArea').innerHTML=tableDiv.innerHTML;
}

function toPlatePara(plateName) {
    var result="CSI300";
    if(plateName=="上证"){
        result="SCI";
    }else if(plateName=="深证"){
        result="SIASA";
    }else if(plateName=="沪深300"){
        result="CSI300";
    }else if(plateName=="中小板"){
        result="SPF";
    }else if(plateName=="创业板"){
        result="GEM";
    }
    return result;
}

//添加用户自选股
function addSelfStock() {
    var stockCodeOrName=document.getElementById('addButton1').value;
    var storage=window.localStorage;

    var result="false";
    var isLogin=storage.getItem('alreadyLogin');
    if(isLogin=="true"){//如果已经登录
        var account=storage.getItem('account');

        var tempUrl="/StockHubWeb/addOneSelfSelectStock?" +
            "account="+account+"&stockCodeOrName="+stockCodeOrName;
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                result=xmlhttp.responseText;
            }
        };
        xmlhttp.open("GET",tempUrl,true);
        xmlhttp.send();

        if(result==true){

        }else {
            alert("购买失败.");
        }
        return result;
    }else {
        alert("您还未登录，需登录后使用此功能.");
        return false;
    }
}

//用户购买股票
function buyStock() {
    var storage=window.localStorage;

    var result="false";
    var isLogin=storage.getItem('alreadyLogin');
    // private String account;
    // private String date;//买的日期
    // private String code;//买的股票代码
    // private String volume;//买的股票分数
    // private boolean isBuy
    if(isLogin=="true"){//如果已经登录
        var account=storage.getItem('account');
        var date="6/8/17";
        var code=document.getElementById("buyStockCode").value;
        var volume=document.getElementById("buyStockVolume").value;

        var tempUrl="/StockHubWeb/realBuy?" +
            "account="+account+"&date="+date+"&code="+code+
            "&volume="+volume+"&isBuy=true";
        var xmlhttp;
        if (window.XMLHttpRequest)
        {// code for IE7+, Firefox, Chrome, Opera, Safari
            xmlhttp=new XMLHttpRequest();
        }
        else
        {// code for IE6, IE5
            xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
        }
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {

                result=xmlhttp.responseText;

            }
        };
        xmlhttp.open("GET",tempUrl,true);
        xmlhttp.send();

        if(result==true){
            $('#buyDiv').modal('hide');
        }else {
            alert("购买失败.")
        }
        return result;
    }else {
        alert("您还未登录，需登录后使用此功能.")
        return false;
    }

}