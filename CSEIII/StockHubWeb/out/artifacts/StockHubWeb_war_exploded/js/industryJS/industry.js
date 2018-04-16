/**
 * Created by A on 2017/6/1.
 */

//初始化整体行业的网页
function initWholeIndustry() {
    //获取当前时间
    // var myDate = new Date();
    // var nowY = myDate.getFullYear();
    // var nowM = myDate.getMonth()+1;
    // var nowD = myDate.getDate();
    // var date = nowM+"/"+nowD+"/"+nowY%100;//当前日期
    var date="6/8/17";

    //下面开始初始化
    initAllIndustryTable();
    initIndustryWholePieChart(date);
    initIndustryTotalInfo(date);
    initIncreaseTotalTable(date);
    initDecreaseTotalTable(date);
    initVolumeTotalTable(date);
    initPotentialTotalTable(date);
}

//设置行业一览表
function initAllIndustryTable() {
    var dataPack="";
    var tempUrl="/StockHubWeb/getAllIndustryName";
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
            // alert("in line 45:"+tempUrl+" "+result);
            paintAllIndustryTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintAllIndustryTable(dataPack) {
    // alert("in line 53:"+dataPack+" "+dataPack.industry[0]);
    var table = document.createElement("table");

    var tableBody=document.createElement("tbody");
    var length=dataPack.industry.length;
    for (var i = 0; i < length/6; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        for (var j=0;j<6;j++){
            var td=document.createElement("td");
            if((dataPack.industry[6*i+j]==null)||(dataPack.industry[6*i+j]=="")||(dataPack.industry[6*i+j]=="undefined")){
                td.innerHTML="";
            }else {
                td.innerHTML="<a href='specificIndustry.html?industryName="+dataPack.industry[6*i+j]+"' target='_blank'>"+dataPack.industry[6*i+j]+"</a>";
            }
            tr.appendChild(td);
        }
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('allIndustryTable').innerHTML=tableDiv.innerHTML;
}

//设置行业总体饼状图
function initIndustryWholePieChart(date) {
    var dataPack="";
    var dateStr=date.toString();
    var tempUrl="/StockHubWeb/findTotalIndustryPieVO?" +
        "date="+dateStr;
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
            paintIndustryWholePieChart(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintIndustryWholePieChart(dataPack) {
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
        // legend: {
        //     orient: 'vertical',
        //     left: 'left',
        //     data: ['跌停','-10%~-5%','-5%~0%','0%~5%','5%~10%','涨停']
        // },
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
    // document.getElementById('industryWholePieChart').innerHTML=option;
    var myChart = echarts.init(document.getElementById('industryWholePieChart'));
    myChart.setOption(option);
}

//设置行业总体情况表格
function initIndustryTotalInfo(date) {
    var dateStr=date.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findIndustryInfoOneday?" +
        "date="+dateStr;
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
            paintIndustryTotalInfo(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();
}

function paintIndustryTotalInfo(dataPack) {
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML="<a href='specificIndustry.html?industryName="+dataPack[i].industryName+"' target='_blank'>"+dataPack[i].industryName+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate+"%";
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
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
    document.getElementById('industryTotalInfo').innerHTML=tableDiv.innerHTML;

}

//设置总体行业涨幅榜
function initIncreaseTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findTotalIndustryMaxInRate?" +
        "date="+dateStr;
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
            paintIncreaseTotalTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();
}

function paintIncreaseTotalTable(dataPack) {
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML="<a href='specificIndustry.html?industryName="+dataPack[i].industryName+"' target='_blank'>"+dataPack[i].industryName+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate+"%";
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
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
    document.getElementById('increaseTotalTable').innerHTML=tableDiv.innerHTML;
}

//设置总体行业跌幅榜
function initDecreaseTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findTotalIndustryMaxDeRate?" +
        "date="+dateStr;
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
            paintDecreaseTotalTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();
}

function paintDecreaseTotalTable(dataPack) {
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML="<a href='specificIndustry.html?industryName="+dataPack[i].industryName+"' target='_blank'>"+dataPack[i].industryName+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate+"%";
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
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
    document.getElementById('decreaseTotalTable').innerHTML=tableDiv.innerHTML;
}

//设置总体行业成交量榜
function initVolumeTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findTotalIndustryMaxInVolume?" +
        "date="+dateStr;
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
            paintVolumeTotalTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();
}

function paintVolumeTotalTable(dataPack) {
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML="<a href='specificIndustry.html?industryName="+dataPack[i].industryName+"' target='_blank'>"+dataPack[i].industryName+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate+"%";
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
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
    document.getElementById('volumeTotalTable').innerHTML=tableDiv.innerHTML;
}

//设置总体行业潜力榜
function initPotentialTotalTable(date) {
    var dateStr=date.toString();
    var dataPack=[];
    var tempUrl="/StockHubWeb/findTotalIndustryPotential?" +
        "date="+dateStr;
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
            paintPotentialTotalTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();
}

function paintPotentialTotalTable(dataPack) {
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
    tableRow.appendChild(th6);
    tableHead.appendChild(tableRow);

    var length=dataPack.length;
    if(length>10){
        length=10;
    }
    for (var i = 0; i < length; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        var td2=document.createElement("td");
        td2.innerHTML="<a href='specificIndustry.html?industryName="+dataPack[i].industryName+"' target='_blank'>"+dataPack[i].industryName+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML=dataPack[i].companyNum;
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecreaseMoney;
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].increaseOrDecreaseRate+"%";
        var td6=document.createElement("td");
        td6.innerHTML=dataPack[i].totalVolume;
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
    document.getElementById('potentialTotalTable').innerHTML=tableDiv.innerHTML;
}
