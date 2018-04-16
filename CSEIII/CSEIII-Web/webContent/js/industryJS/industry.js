/**
 * Created by A on 2017/6/1.
 */

//初始化整体行业的网页
function initWholeIndustry() {
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

    //下面开始初始化
    initIndustryWholePieChart(date);
    initIndustryTotalInfo(date);
    initIncreaseTotalTable(date);
    initDecreaseTotalTable(date);
    initVolumeTotalTable(date);
    initPotentialTotalTable(date);
}

//设置行业总体饼状图
function initIndustryWholePieChart(date) {
    var dataPack="";
    var dateStr=date.toString();
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findTotalIndustryPieVO",
        type:"POST",

        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr},
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
            text: '行业整体股票情况统计饼状图',
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
    document.getElementById('industryWholePieChart').innerHTML=option;
}

//设置行业总体情况表格
function initIndustryTotalInfo(date) {
    var dateStr=date.toString();
    var dataPack=[];
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findIndustryInfoOneday",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr},
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
    th2.innerHTML="行业名称";
    var th3=document.createElement("th");
    th3.innerHTML="公司家数";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌额";
    var th5=document.createElement("th");
    th5.innerHTML="涨跌幅";
    var th6=document.createElement("th");
    th6.innerHTML="总成交量";
    var th7=document.createElement("th");
    th7.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].industryName;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
        var td7=document.createElement("td");
        td7.innerHTML=dataPack[i].industryScore;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('industryTotalInfo').innerHTML=tableDiv.innerHTML;
}

//设置总体行业涨幅榜
function initIncreaseTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findTotalIndustryMaxInRate",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr},
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
    th2.innerHTML="行业名称";
    var th3=document.createElement("th");
    th3.innerHTML="公司家数";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌额";
    var th5=document.createElement("th");
    th5.innerHTML="涨跌幅";
    var th6=document.createElement("th");
    th6.innerHTML="总成交量";
    var th7=document.createElement("th");
    th7.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].industryName;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
        var td7=document.createElement("td");
        td7.innerHTML=dataPack[i].industryScore;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('increaseTotalTable').innerHTML=tableDiv.innerHTML;
}

//设置总体行业跌幅榜
function initDecreaseTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findTotalIndustryMaxDeRate",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr},
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
    th2.innerHTML="行业名称";
    var th3=document.createElement("th");
    th3.innerHTML="公司家数";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌额";
    var th5=document.createElement("th");
    th5.innerHTML="涨跌幅";
    var th6=document.createElement("th");
    th6.innerHTML="总成交量";
    var th7=document.createElement("th");
    th7.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].industryName;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
        var td7=document.createElement("td");
        td7.innerHTML=dataPack[i].industryScore;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('decreaseTotalTable').innerHTML=tableDiv.innerHTML;
}

//设置总体行业成交量榜
function initVolumeTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findTotalIndustryMaxInVolume",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr},
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
    th2.innerHTML="行业名称";
    var th3=document.createElement("th");
    th3.innerHTML="公司家数";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌额";
    var th5=document.createElement("th");
    th5.innerHTML="涨跌幅";
    var th6=document.createElement("th");
    th6.innerHTML="总成交量";
    var th7=document.createElement("th");
    th7.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].industryName;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
        var td7=document.createElement("td");
        td7.innerHTML=dataPack[i].industryScore;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('volumeTotalTable').innerHTML=tableDiv.innerHTML;
}

//设置总体行业潜力榜
function initPotentialTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    $.ajax({
        url: "http://localhost:8080/web/IndustryBlSer/findTotalIndustryPotential",
        type:"POST",
        dataType:"json",
        async:false,//or true?
        data:{"date":dateStr},
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
    th2.innerHTML="行业名称";
    var th3=document.createElement("th");
    th3.innerHTML="公司家数";
    var th4=document.createElement("th");
    th4.innerHTML="涨跌额";
    var th5=document.createElement("th");
    th5.innerHTML="涨跌幅";
    var th6=document.createElement("th");
    th6.innerHTML="总成交量";
    var th7=document.createElement("th");
    th7.innerHTML="潜力";

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableRow.appendChild(th7);
    tableHead.appendChild(tableRow);

    for (var i = 0; i < 10; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML=dataPack[i].industryName;
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate;
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
        var td7=document.createElement("td");
        td7.innerHTML=dataPack[i].industryScore;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tr.appendChild(td6);
        tr.appendChild(td7);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('potentialTotalTable').innerHTML=tableDiv.innerHTML;
}
