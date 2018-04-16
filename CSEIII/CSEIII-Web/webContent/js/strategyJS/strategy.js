/**
 * Created by A on 2017/6/2.
 */
function searchStrategy() {

}

function initYieldChart(type, formationPeriod, holdingPeriod, start, end, stockPoolBl, stockNameOrCode) {
    var dataPack=[];
    var typeStr=type.toString();
    var formationPeriodStr=formationPeriod.toString();
    var holdingPeriodStr=holdingPeriod.toString();
    var startStr=start.toString();
    var endStr=end.toString();
    var stockPoolBlStr=stockPoolBl.toString();
    var stockNameOrCodeStr=stockNameOrCode.toString();
    var tempUrl="http://localhost:8080/web/StrategyBlSer/getStrategy";
    var tempData={"type":typeStr,"formationPeriod":formationPeriodStr,"holdingPeriod":holdingPeriodStr,
    "start":startStr,"end":endStr,"stockPoolBl":stockPoolBlStr,"stockNameOrCode":stockNameOrCodeStr};
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
    var staticalVariableVO=dataPack.staticalVariableVO;//数据
    var strategyGraphVO=dataPack.strategyGraphVO;//策略累计收益率比较图数据
    var strategyEvaluateVO=dataPack.strategyEvaluateVO;//策略得分数据

    //在界面显示数据
    document.getElementById('yearYieldRate').innerHTML=staticalVariableVO.yearYieldRate;
    document.getElementById('benchmarkYearYieldRate').innerHTML=staticalVariableVO.benchmarkYearYieldRate;
    document.getElementById('alpha').innerHTML=staticalVariableVO.alpha;
    document.getElementById('beta').innerHTML=staticalVariableVO.beta;
    document.getElementById('sharpeRatio').innerHTML=staticalVariableVO.sharpeRatio;
    document.getElementById('maxWithdraw').innerHTML=staticalVariableVO.maxWithdraw;


    var date=strategyGraphVO.date;
    var strategyYieldStr=strategyGraphVO.strategyYield;
    var benchmarkYieldStr=strategyGraphVO.benchmarkYield;
    var maxWithdrawStart=strategyGraphVO.maxWithdrawStart;//最大回撤开始日期
    var maxWithdrawEnd=strategyGraphVO.maxWithdrawEnd;//最大回撤结束日期
    var maxWithdrawStartFloat=0.0;//最大回撤开始值
    var maxWithdrawEndFloat=0.0;//最大回撤结束值
    var strategyYieldFloat=[];
    var benchmarkYieldFloat=[];

    var length=strategyYield.size;
    if(benchmarkYield.size<length){
        length=benchmarkYield.size;
    }
    //实现去“%”，并将其转化为float
    for(var i=0;i<length;i++){
        if(maxWithdrawStart.isEqual(date[i])){
            maxWithdrawStartFloat=parseFloat(strategyYieldStr[i].replace(/%/,""));
        }
        if(maxWithdrawEnd.isEqual(date[i])){
            maxWithdrawEndFloat=parseFloat(strategyYieldStr[i].replace(/%/,""));
        }
        strategyYieldFloat[i]=parseFloat(strategyYieldStr[i].replace(/%/,""));
        benchmarkYieldFloat[i]=parseFloat(benchmarkYieldStr[i].replace(/%/,""));
    }
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

    document.getElementById('yieldChart').innerHTML=option;

}