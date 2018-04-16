/**
 * Created by A on 2017/6/2.
 */
var type="MOMENT";
var formationPeriod=10;
var holdingPeriod=5;
var start="11/01/16";
var end="06/15/17";
var stockPoolBl="ALL";
var stockNameOrCode=[];

function searchStrategy() {
    type="MOMENT";//策略种类
    var strategySelect=document.getElementById('strategySelect');
    var index=strategySelect.selectedIndex;//selectedIndex代表的是用户所选中项的index
    if(index==0){
        type="MOMENT";
    }else if(index==1){
        type="AVERAGE";
    }else if(index==2){
        type="MA";
    }

    formationPeriod=document.getElementById("formationInput").value;
    holdingPeriod=document.getElementById("holdInput").value;
    var startTempStr=document.getElementById("startDate").value;
    var endTempStr=document.getElementById("endDate").value;
    start=parseInt(startTempStr.charAt(5)+startTempStr.charAt(6)).toString()+"/"+
        parseInt(startTempStr.charAt(8)+startTempStr.charAt(9)).toString()+"/"+
        startTempStr.charAt(2)+startTempStr.charAt(3);
    end=parseInt(endTempStr.charAt(5)+endTempStr.charAt(6)).toString()+"/"+
        parseInt(endTempStr.charAt(8)+endTempStr.charAt(9)).toString()+"/"+
        endTempStr.charAt(2)+endTempStr.charAt(3);

    stockPoolBl="ALL";
    var plateSelect=document.getElementById('plateSelect');
    var plateIndex=plateSelect.selectedIndex;//selectedIndex代表的是用户所选中项的index
    if(plateIndex==0){
        stockPoolBl="ALL";
    }else if(plateIndex==1){
        stockPoolBl="SHANGZHENG";
    }else if(plateIndex==2){
        stockPoolBl="SHENZHENG";
    }else if(plateIndex==3){
        stockPoolBl="HUSHEN300";
    }else if(plateIndex==4){
        stockPoolBl="SMALLMIDDLEPLATE";
    }else if(plateIndex==5){
        stockPoolBl="STARTUPPLATE";
    }

    stockNameOrCode="empty";

    initYieldChart(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode);
    initBestFormationAndPeriod(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode,0);//先默认是0,即默认是以形成期为基准
    initRelativeIndexReturn(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode);
}

/**
 * 初始化界面的累计收益率比较图、比较数据、以及策略得分的雷达图
 * @param type
 * @param formationPeriod
 * @param holdingPeriod
 * @param start
 * @param end
 * @param stockPoolBl
 * @param stockNameOrCode
 */
function initYieldChart(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode) {
    var dataPack=[];
    var typeStr=type.toString();
    var formationPeriodStr=formationPeriod.toString();
    var holdingPeriodStr=holdingPeriod.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    var stockPoolBlStr=stockPoolBl.toString();
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var tempUrl="/StockHubWeb/getStrategy?" +
        "type="+typeStr+"&formationPeriod="+formationPeriodStr+"&holdingPeriod="+holdingPeriodStr+
        "&start="+startStr+"&end="+endStr+"&stockPoolBl="+stockPoolBlStr+"&stockNameOrCode="+stockNameOrCodeStr;
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
            // alert("in line 98:"+tempUrl);
            // alert("in line 98:"+dataPack);
            paintYieldChart(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintYieldChart(dataPack) {
    // var staticalVariableVO=dataPack.staticalVariableVO;//数据
    // var strategyGraphVO=dataPack.strategyGraphVO;//策略累计收益率比较图数据
    // var strategyEvaluateVO=dataPack.strategyEvaluateVO;//策略得分数据

    // alert("in line 110:"+staticalVariableVO+"   "+strategyGraphVO+"  "+strategyEvaluateVO);
    //在界面显示数据
    var table = document.createElement("table");
    var tableBody = document.createElement("tbody");
    var tr=document.createElement("tr");
    var td1=document.createElement("td");
    td1.innerHTML="年化收益率";
    tr.appendChild(td1);
    tableBody.appendChild(tr);
    tr=document.createElement("tr");
    var td2=document.createElement("td");
    td2.innerHTML=dataPack.yearYieldRate;
    tr.appendChild(td2);
    tableBody.appendChild(tr);
    table.appendChild(tableBody);
    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('yearYieldRate').innerHTML=tableDiv.innerHTML;

    table = document.createElement("table");
    tableBody = document.createElement("tbody");
    tr=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="基准年化收益率";
    tr.appendChild(td1);
    tableBody.appendChild(tr);
    tr=document.createElement("tr");
    td2=document.createElement("td");
    td2.innerHTML=dataPack.benchmarkYearYieldRate;
    tr.appendChild(td2);
    tableBody.appendChild(tr);
    table.appendChild(tableBody);
    tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('benchmarkYearYieldRate').innerHTML=tableDiv.innerHTML;

    table = document.createElement("table");
    tableBody = document.createElement("tbody");
    tr=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="阿尔法";
    tr.appendChild(td1);
    tableBody.appendChild(tr);
    tr=document.createElement("tr");
    td2=document.createElement("td");
    td2.innerHTML=dataPack.alpha;
    tr.appendChild(td2);
    tableBody.appendChild(tr);
    table.appendChild(tableBody);
    tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('alpha').innerHTML=tableDiv.innerHTML;

    table = document.createElement("table");
    tableBody = document.createElement("tbody");
    tr=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="贝塔";
    tr.appendChild(td1);
    tableBody.appendChild(tr);
    tr=document.createElement("tr");
    td2=document.createElement("td");
    td2.innerHTML=dataPack.beta;
    tr.appendChild(td2);
    tableBody.appendChild(tr);
    table.appendChild(tableBody);
    tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('beta').innerHTML=tableDiv.innerHTML;

    table = document.createElement("table");
    tableBody = document.createElement("tbody");
    tr=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="夏普比率";
    tr.appendChild(td1);
    tableBody.appendChild(tr);
    tr=document.createElement("tr");
    td2=document.createElement("td");
    td2.innerHTML=dataPack.sharpeRatio;
    tr.appendChild(td2);
    tableBody.appendChild(tr);
    table.appendChild(tableBody);
    tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('sharpeRatio').innerHTML=tableDiv.innerHTML;

    table = document.createElement("table");
    tableBody = document.createElement("tbody");
    tr=document.createElement("tr");
    td1=document.createElement("td");
    td1.innerHTML="最大回撤";
    tr.appendChild(td1);
    tableBody.appendChild(tr);
    tr=document.createElement("tr");
    td2=document.createElement("td");
    td2.innerHTML=dataPack.maxWithdraw;
    tr.appendChild(td2);
    tableBody.appendChild(tr);
    table.appendChild(tableBody);
    tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('maxWithdraw').innerHTML=tableDiv.innerHTML;


    var date=dataPack.date;
    var strategyYieldStr=dataPack.strategyYield;
    var benchmarkYieldStr=dataPack.benchmarkYield;
    var maxWithdrawStart=dataPack.maxWithdrawStart;//最大回撤开始日期
    var maxWithdrawEnd=dataPack.maxWithdrawEnd;//最大回撤结束日期
    var maxWithdrawStartFloat=0.0;//最大回撤开始值
    var maxWithdrawEndFloat=0.0;//最大回撤结束值
    var strategyYieldFloat=[];
    var benchmarkYieldFloat=[];

    var length=strategyYieldStr.length;
    if(benchmarkYieldStr.length<length){
        length=benchmarkYieldStr.length;
    }
    //实现去“%”，并将其转化为float
    for(var i=0;i<length;i++){
        if(maxWithdrawStart==date[i]){
            maxWithdrawStartFloat=parseFloat(strategyYieldStr[i].replace(/%/,""));
        }
        if(maxWithdrawEnd==date[i]){
            maxWithdrawEndFloat=parseFloat(strategyYieldStr[i].replace(/%/,""));
        }
        strategyYieldFloat[i]=parseFloat(strategyYieldStr[i].replace(/%/,""));
        benchmarkYieldFloat[i]=parseFloat(benchmarkYieldStr[i].replace(/%/,""));
    }
    alert("in line 242:"+strategyYieldFloat+" "+benchmarkYieldFloat);
    var maxWithdrawStartData=[maxWithdrawStart,maxWithdrawStartFloat];
    var maxWithdrawEndData=[maxWithdrawEnd,maxWithdrawEndFloat];
    //显示累计收益率比较图
    var colors = ['#5793f3', '#d14a61', '#675bba'];
    option = {
        color: colors,

        title: {
            text: '累计收益率'
        },
        tooltip: {
            trigger: 'axis'
        },
        legend: {
            data:['策略','基准']
        },
        toolbox: {
            show: true,
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                dataView: {readOnly: false},
                magicType: {type: ['line', 'bar']},
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            data: date
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} %'
            }
        },
        series: [
            {
                name:'策略',
                type:'line',
                data:strategyYieldFloat,
                markPoint: {
                    data: [
                        { name: '最大回撤开始',
                            coord: maxWithdrawStartData
                        },
                        { name: '最大回撤结束',
                            coord: maxWithdrawEndData
                        }
                    ]
                }
            },
            {
                name:'基准',
                type:'line',
                data:benchmarkYieldFloat
            }
        ]
    };

    // document.getElementById('yieldChart').innerHTML=option;
    var myChart = echarts.init(document.getElementById('yieldChart'));
    myChart.setOption(option);

    //显示策略评分的雷达图
    var oneData=[dataPack.yield,dataPack.resistRisk,dataPack.stable,
        dataPack.riskYield,dataPack.yieldSpace];

    var OneStockRadarData = [
        oneData
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
            text: '总评: '+strategyEvaluateVO.generalScore,
            left: 'center',
            textStyle: {
                color: '#eee'
            }
        },
        legend: {
            bottom: 5,
            data: ['策略评分'],
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
                {name: '收益性', max: 100},
                {name: '抗风险性', max: 100},
                {name: '稳定性', max: 100},
                {name: '风险收益', max: 100},
                {name: '收益空间', max: 100}
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
                name: '策略评分',
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
    // document.getElementById('strategyRadar').innerHTML=option;
    myChart = echarts.init(document.getElementById('strategyRadar'));
    myChart.setOption(option);
}

/**
 * 初始化界面的最优形成期/持有期图
 * 从servlet获得策略超额收益率与不同形成期/持有期的关系图以及策略胜率与不同形成期/持有期的关系图的数据
 * @param type InputStrategyVO中的type
 * @param formationPeriod
 * @param holdingPeriod
 * @param start
 * @param end
 * @param stockPoolBl
 * @param stockNameOrCode
 * @param type1 type可以为0或1，type=0时，代表用户选择的是形成期，type=1时，代表用户选择的是持有期
 */
function initBestFormationAndPeriod(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode,type1) {
    var dataPack=[];
    var typeStr=type.toString();
    var formationPeriodStr=formationPeriod.toString();
    var holdingPeriodStr=holdingPeriod.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    var stockPoolBlStr=stockPoolBl.toString();
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var type1Int=parseInt(type1);
    var tempUrl="/StockHubWeb/getRelationGraphData?" +
        "type="+typeStr+"&formationPeriod="+formationPeriodStr+"&holdingPeriod="+holdingPeriodStr+
        "&start="+startStr+"&end="+endStr+"&stockPoolBl="+stockPoolBlStr+
        "&stockNameOrCode="+stockNameOrCodeStr+"&type1="+type1Int;

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
            paintBestFormationAndPeriod(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintBestFormationAndPeriod(dataPack) {
    var period=dataPack.period;//相对强弱计算周期
    var overYield=dataPack.overYield;//超额收益
    var winRate=dataPack.winRate;//胜率

    //先在界面显示表格
    var table = document.createElement("table");
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");
    var th1=document.createElement("th");
    th1.innerHTML="相对强弱计算周期";
    var th2=document.createElement("th");
    th2.innerHTML="超额收益";
    var th3=document.createElement("th");
    th3.innerHTML="一年内胜率%";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < period.length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=period[i];
        var td2=document.createElement("td");
        td2.innerHTML=overYield[i];
        var td3=document.createElement("td");
        td3.innerHTML=winRate[i];
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('bestFormationTable').innerHTML=tableDiv.innerHTML;

    //数据转换
    var x = [];
    var yOverYield=[];
    var yWinRate=[];

    for(var i=0;i<period.length;i++){
        x[i]=period[i];
        var tempYield=overYield[i].replace("%","");
        yOverYield[i]=parseFloat(tempYield);
        var tempWin=winRate[i].replace("%","");
        yWinRate[i]=parseFloat(tempWin);
    }

    //在界面显示超额收益vs全样本-不同计算周期
    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '超额收益vs全样本-不同计算周期',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: x
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} %'
            },
            boundaryGap: [0, '100%']
        },
        series: [
            {
                name:'超额收益',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: yOverYield
            }
        ]
    };
    // document.getElementById('bestFormationChart1').innerHTML=option;
    var myChart = echarts.init(document.getElementById('bestFormationChart1'));
    myChart.setOption(option);

    //在界面显示策略胜率%-不同计算周期
    option = {
        tooltip: {
            trigger: 'axis',
            position: function (pt) {
                return [pt[0], '10%'];
            }
        },
        title: {
            left: 'center',
            text: '策略胜率-不同计算周期',
        },
        toolbox: {
            feature: {
                dataZoom: {
                    yAxisIndex: 'none'
                },
                restore: {},
                saveAsImage: {}
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: x
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value} %'
            },
            boundaryGap: [0, '100%']
        },
        series: [
            {
                name:'策略胜率',
                type:'line',
                smooth:true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: 'rgb(255, 70, 131)'
                    }
                },
                areaStyle: {
                    normal: {
                        color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                            offset: 0,
                            color: 'rgb(255, 158, 68)'
                        }, {
                            offset: 1,
                            color: 'rgb(255, 70, 131)'
                        }])
                    }
                },
                data: yWinRate
            }
        ]
    };
    // document.getElementById('bestFormationChart2').innerHTML=option;
    myChart = echarts.init(document.getElementById('bestFormationChart2'));
    myChart.setOption(option);

    //添加以形成期/持有期按钮选择
    document.getElementById('ForOrHoldSelect').innerHTML =
        "</br><input name=\"按钮\" type=\"button\" onClick=\"initBestFormationAndPeriod1()\" value=\"以形成期为基准\"><\/input>" +
        "<input name=\"按钮\" type=\"button\" onClick=\"initBestFormationAndPeriod2()\" value=\"以持有期为基准\"><\/input>";

}

//以形成期为基准
function initBestFormationAndPeriod1(){
    initBestFormationAndPeriod(type,formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode,0);
}
//以持有期为基准
function initBestFormationAndPeriod2(){
    initBestFormationAndPeriod(type,formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode,1);
}

/**
 * 初始化界面的收益率分布直方图
 * @param type
 * @param formationPeriod
 * @param holdingPeriod
 * @param start
 * @param end
 * @param stockPoolBl
 * @param stockNameOrCode
 */
function initRelativeIndexReturn(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode) {
    var dataPack=[];
    var typeStr=type.toString();
    var formationPeriodStr=formationPeriod.toString();
    var holdingPeriodStr=holdingPeriod.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    var stockPoolBlStr=stockPoolBl.toString();
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var tempUrl="/StockHubWeb/getYieldRateDistribution?" +
        "type="+typeStr+"&formationPeriod="+formationPeriodStr+"&holdingPeriod="+holdingPeriodStr+
        "&start="+startStr+"&end="+endStr+"&stockPoolBl="+stockPoolBlStr+
        "&stockNameOrCode="+stockNameOrCodeStr;

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
            paintRelativeIndexReturn(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintRelativeIndexReturn(dataPack) {
    /**
     * private String positiveYieldNum;//正收益周期数
     private String negativeYieldNum;//负收益周期数
     private String winRate;//赢率
     private ArrayList<String> yieldRate;//从最小到最大排列，如 1%,5%，6%
     private ArrayList<String> positiveFrequency;//正收益频数
     private ArrayList<String> negativeFrequency;//负收益频数
     */
    var positiveYieldNum=dataPack.positiveYieldNum;//正收益周期数
    var negativeYieldNum=dataPack.negativeYieldNum;//负收益周期数
    var winRate=dataPack.winRate;//赢率
    var yieldRate=dataPack.yieldRate;//从最小到最大排列，如 1%,5%，6%
    var positiveFrequency=dataPack.positiveFrequency;//正收益频数
    var negativeFrequency=dataPack.negativeFrequency;//负收益频数

    var xData=[];
    var positiveData=[];
    var negativeData=[];
    for(var i=0;i<yieldRate.length;i++){
        xData[i]=yieldRate[i].toString();
        positiveData[i]=parseInt(positiveFrequency);
        negativeData[i]=parseInt(negativeFrequency);
    }
    //显示数据
    option = {
        title : {
            text: '相对指数收益分布图',
            subtext: '                    正收益周期数：'+positiveYieldNum
            +',    负收益周期数：'+negativeYieldNum
            +',    赢率：'+winRate
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['正收益次数','负收益次数']
        },
        toolbox: {
            show : true,
            feature : {
                dataView : {show: true, readOnly: false},
                magicType : {show: true, type: ['line', 'bar']},
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : xData
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'正收益次数',
                type:'bar',
                data:positiveData,
                color: ['#FF0000']
            },
            {
                name:'负收益次数',
                type:'bar',
                data:negativeData,
                color: ['#00e500']
            }
        ]
    };

    // document.getElementById('relativeIndexReturn').innerHTML=option;
    var myChart = echarts.init(document.getElementById('relativeIndexReturn'));
    myChart.setOption(option);
}