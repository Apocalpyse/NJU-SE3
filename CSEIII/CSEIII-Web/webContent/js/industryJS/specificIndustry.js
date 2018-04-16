/**
 * Created by A on 2017/6/1.
 */

//初始化某具体行业的网页
function initSpecificIndustry(industryName) {
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

    initSpecificIndustryPieChart(date,industryName);
    initSpecificIndustryInfo(date,industryName);
    initIncreaseTable(date,industryName);
    initDecreaseTable(date,industryName);
    initVolumeTable(date,industryName);
    initPotentialTable(date,industryName);
}

//初始化集体行业的饼状图
function initSpecificIndustryPieChart(date, industryName) {
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
    document.getElementById('specificIndustryPieChart').innerHTML=option;
}

//初始化具体行业下面的表格
function initSpecificIndustryInfo(date, industryName) {
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
    document.getElementById('specificIndustryInfo').innerHTML=tableDiv.innerHTML;
}

//设置某行业涨幅榜
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

//设置某行业跌幅榜
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

//设置某行业成交量榜
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

//设置某行业潜力榜
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