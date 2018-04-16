/**
 * Created by A on 2017/5/25.
 */

//用户点击个股的搜索按钮，进行的搜索
function getStockWholeData() {
    var codeOrName=document.getElementById("searchStockCodeOrNameInput").value;
    if((codeOrName!=null)&&(codeOrName!="")){
        //获取当前时间
        var xmlhttp = new ActiveXObject("MSXML2.XMLHTTP.3.0");
        xmlhttp.open("GET", "http://bjtime.cn", false);
        xmlhttp.setRequestHeader("If-Modified-Since", "bjtime");
        xmlhttp.send();
        var dateStr = xmlhttp.getResponseHeader("Date");
        var dateTemp = new Date(dateStr);
        var year = dateTemp.getFullYear();
        var month = dateTemp.getMonth() + 1;
        var date1 = dateTemp.getDate();
        var date=month/date1/year;

        var start=date;
        $.ajax({
            url: "http://localhost:8080/web/Calculate/getBeforeSomeTradeDate",
            type:"POST",

            dataType:"json",
            async:false,//or true?
            data:{"date":dateStr,"somedays":90},
            contentType: "application/json; charset=utf-8",
            success: function(data){
                start=data;
                alert("values:"+data);
            },
            fail:function () {
                alert("failed");
            },
            error:function () {
                alert("error");
            }
        });

        var tempStart=date;
        $.ajax({
            url: "http://localhost:8080/web/Calculate/getBeforeSomeTradeDate",
            type:"POST",

            dataType:"json",
            async:false,//or true?
            data:{"date":dateStr,"somedays":8},
            contentType: "application/json; charset=utf-8",
            success: function(data){
                start=data;
                alert("values:"+data);
            },
            fail:function () {
                alert("failed");
            },
            error:function () {
                alert("error");
            }
        });

        var dataPack=[];
        var tempUrl='';
        var tempData='';
        if((codeOrName.charAt(0)>='0')&&(codeOrName.charAt(0)<='9')){//如果是code
            tempUrl="http://localhost:8080/web/KAndEMABlSer/getDataByCode";
            tempData={"code":codeOrName,"start":tempStart,"end":date};
        }else {
            tempUrl="http://localhost:8080/web/KAndEMABlSer/getDataByName";
            tempData={"name":codeOrName,"start":tempStart,"end":date};
        }
        $.ajax({
            url: tempUrl,
            type:"POST",

            dataType:"json",
            async:false,//or true?
            data:tempData,
            contentType: "application/json; charset=utf-8",
            success: function(data){
                dataPack=data;
                alert("values:"+data);
            },
            fail:function () {
                alert("failed");
            },
            error:function () {
                alert("error");
            }
        });
        var industryName=dataPack[0].industryName;

        //下面进行界面的初始化
        initSingleStockSimpleKChart(codeOrName,start,date);
        initSingleStockSimplePieChart(date,industryName);
        initSingleStockRadarChart(codeOrName,start,date);
        initSingleStockKAndEMAChart(codeOrName,start,date);
        initIncreaseTable(date,industryName);
        initDecreaseTable(date,industryName);
        initVolumeTable(date,industryName);
        initPotentialTable(date,industryName);
    }else {
        alert("请输入股票名称或代码");
    }
};

//设置股票简单数据及简易K线图
function initSingleStockSimpleKChart(stockNameOrCode, start,end) {
    var dataPack;
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var startStr=start;
    var endStr=end;
    var mothodToCall="";
    var parameterName="";
    if((stockNameOrCodeStr.charAt(0)>='0')&&(stockNameOrCodeStr.charAt(0))<='9'){//如果是stockCode
        $.ajax({
            url: "http://localhost:8080/web/KAndEMABlSer/getDataByCode",
            type:"POST",
            dataType:"json",
            async:false,//or true?
            data:{"code":stockNameOrCodeStr,"start":startStr,"end":endStr},
            contentType: "application/json; charset=utf-8",
            success: function(data){
                dataPack=data;
                alert("values:"+data);
            },
            fail:function () {
                alert("failed");
            },
            error:function () {
                alert("error");
            }
        });
    }else {
        $.ajax({
            url: "http://localhost:8080/web/KAndEMABlSer/getDataByName",
            type:"POST",
            dataType:"json",
            async:false,//or true?
            data:{"name":stockNameOrCodeStr,"start":startStr,"end":endStr},
            contentType: "application/json; charset=utf-8",
            success: function(data){
                dataPack=data;
                alert("values:"+data);
                //下面要加之后的显示操作
            },
            fail:function () {
                alert("failed");
            },
            error:function () {
                alert("error");
            }
        });
    }

    //以下显示股票的信息
    var length=dataPack.length;
    //设置股票简易数据
    var inOrDeMoney=dataPack[length-1].adjClose-dataPack[length-1].preAdjClose;
    document.getElementById('singleStockName').innerHTML=dataPack[length-1].name;
    document.getElementById('singleStockCurrent').innerHTML=dataPack[length-1].open;
    document.getElementById('singleStockInOrDe').innerHTML
        =dataPack[length-1].increaseOrDecrease+"("+inOrDeMoney+")";

    //设置股票简易k线图表
    var xData=[];//横坐标
    var yData=[];//纵坐标
    for(var i=0;i<length;i++){
        xData[i]=dataPack[i].date;
        yData[i]=dataPack[i].adjClose;
    }
    var colors = ['#5793f3', '#d14a61', '#675bba'];
    option = {
        color: colors,
        tooltip: {
            trigger: 'none',
            axisPointer: {
                type: 'cross'
            }
        },
        // legend: {
        //     data:[ '2016 降水量']
        // },
        grid: {
            top: 70,
            bottom: 50
        },
        xAxis: [
            {
                type: 'category',
                axisTick: {
                    alignWithLabel: true
                },
                axisLine: {
                    onZero: false,
                    lineStyle: {
                        color: colors[1]
                    }
                },
                axisPointer: {
                    label: {
                        formatter: function (params) {
                            return '股票价格  ' + params.value
                                + (params.seriesData.length ? '：' + params.seriesData[0].data : '');
                        }
                    }
                },
                data: xData
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [

            {
                name:'股票简易K线图',
                type:'line',
                smooth: true,
                data: yData
            }
        ]
    };
    document.getElementById('singleStockSimpleKChart').innerHTML=option;

}

//设置股票所在行业的饼状图
function initSingleStockSimplePieChart(date, industryName) {
    var dataPack="";
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findIndustryPieVO",
        type:"POST",

        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr,"industryName":industryNameStr},
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });

    //下面实现饼状图
    var decStop=dataPack.decStop;
    var rateLessNeg5=dataPack.rateLessNeg5;
    var rateFromNeg5ToZero=dataPack.rateFromNeg5ToZero;
    var rateFromZeroTo5=dataPack.rateFromZeroTo5;
    var rateMore5=dataPack.rateMore5;
    var incStop=dataPack.incStop;

    option = {
        title : {
            text: '某行业股票统计饼状图',
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
    document.getElementById('singleStockSimplePieChart').innerHTML=option;

}

//设置股票的分数雷达图
function initSingleStockRadarChart(stockNameOrCode, start, end) {
    var dataPack;
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var startStr=start;
    var endStr=end;
    var mothodToCall="";
    var parameterName="";
    var tempUrl="";
    var tempData={};
    if(start.toString()==end.toString()){
        tempUrl="http://localhost:8080/web/StockBlSer/getStockGoalOne";
        tempData={"codeOrName":stockNameOrCodeStr,"date":startStr};
    }else {
        tempUrl="http://localhost:8080/web/StockBlSer/getStockGoal";
        tempData={"codeOrName":stockNameOrCodeStr,"start":startStr,"end":endStr};
    }
    $.ajax({
        url: tempUrl,
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:tempData,
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });


    //以下将信息显示在雷达图中
    // Schema:
// date,AQIindex,PM2.5,PM10,CO,NO2,SO2
    var oneData=[parseInt(dataPack.stability),parseInt(dataPack.prof),parseInt(dataPack.mobility),
    parseInt(dataPack.safety),parseInt(dataPack.development)];


    var OneStockRadarData = [
        oneData,
    ];

    var lineStyle = {
        normal: {
            width: 1,
            opacity: 0.5
        }
    };

    option = {
        backgroundColor: '#161627',
        title: {
            text: '总评: '+dataPack.total,
            left: 'center',
            textStyle: {
                color: '#eee'
            }
        },
        legend: {
            bottom: 5,
            data: [stockNameOrCodeStr],
            itemGap: 20,
            textStyle: {
                color: '#fff',
                fontSize: 14
            },
            selectedMode: 'single'
        },
        // visualMap: {
        //     show: true,
        //     min: 0,
        //     max: 20,
        //     dimension: 6,
        //     inRange: {
        //         colorLightness: [0.5, 0.8]
        //     }
        // },
        radar: {
            indicator: [
                {name: '稳定性', max: 100},
                {name: '收益性', max: 100},
                {name: '流动性', max: 100},
                {name: '安全性', max: 100},
                {name: '成长性', max: 100}
            ],
            shape: 'circle',
            splitNumber: 5,
            name: {
                textStyle: {
                    color: 'rgb(238, 197, 102)'
                }
            },
            splitLine: {
                lineStyle: {
                    color: [
                        'rgba(238, 197, 102, 0.1)', 'rgba(238, 197, 102, 0.2)',
                        'rgba(238, 197, 102, 0.4)', 'rgba(238, 197, 102, 0.6)',
                        'rgba(238, 197, 102, 0.8)', 'rgba(238, 197, 102, 1)'
                    ].reverse()
                }
            },
            splitArea: {
                show: false
            },
            axisLine: {
                lineStyle: {
                    color: 'rgba(238, 197, 102, 0.5)'
                }
            }
        },
        series: [
            {
                name: stockNameOrCodeStr,
                type: 'radar',
                lineStyle: lineStyle,
                data: OneStockRadarData,
                symbol: 'none',
                itemStyle: {
                    normal: {
                        color: '#F9713C'
                    }
                },
                areaStyle: {
                    normal: {
                        opacity: 0.1
                    }
                }
            }
        ]
    };
    document.getElementById('singleStockSimpleRadarChart').innerHTML=option;
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
        tempUrl="http://localhost:8080/web/KAndEMABlSer/getDataByCode";
        tempData={"code":stockNameOrCodeStr,"start":startStr,"end":endStr};
    }else {
        tempUrl="http://localhost:8080/web/KAndEMABlSer/getDataByName";
        tempData={"name":stockNameOrCodeStr,"start":startStr,"end":endStr};
    }
    $.ajax({
        url: tempUrl,
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:tempData,
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });


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
        var values = []
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
    document.getElementById('singleStockKAndEMAChart').innerHTML=option;

}

//设置股票所在行业涨幅榜
function initIncreaseTable(date, industryName) {
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
    var dataPack=[];
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findIndustryMaxInRate",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr,"industryName":industryNameStr},
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });

    //以下实现行业涨幅榜
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
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
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].code;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].name;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].potential;//需要后加的股票潜力
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
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
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findIndustryMaxDeRate",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr,"industryName":industryNameStr},
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });

    //以下实现行业涨幅榜
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
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
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].code;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].name;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].potential;//需要后加的股票潜力
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
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
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findIndustryMaxInVolume",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr,"industryName":industryNameStr},
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });

    //以下实现行业涨幅榜
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
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
    th4.innerHTML="涨跌幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].code;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].name;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].potential;//需要后加的股票潜力
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
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
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findIndustryPotential",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr,"industryName":industryNameStr},
        contentType: "application/json; charset=utf-8",
        success: function(data){
            dataPack=data;
            alert("values:"+data);
        },
        fail:function () {
            alert("failed");
        },
        error:function () {
            alert("error");
        }
    });

    //以下实现行业涨幅榜
    var table = document.createElement("table");

    var tbody = document.createElement("tbody");
    table.appendChild(tbody);
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
    th4.innerHTML="涨跌幅";
    var th5=document.createElement("th");
    th5.innerHTML="成交量";
    var th6=document.createElement("th");
    th6.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].code;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].name;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].potential;//需要后加的股票潜力
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('potentialTable').innerHTML=tableDiv.innerHTML;
}
