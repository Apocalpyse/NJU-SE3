/**
 * Created by A on 2017/6/1.
 */

//参数：industryName


//初始化某具体行业的网页
function initSpecificIndustry() {
    var industryName=getArgsFromHref("industryName");

    document.getElementById("bannerIndustryName").innerHTML=industryName;
    document.getElementById("industryName").innerHTML=industryName;

    //获取当前时间
    // var myDate = new Date();
    // var nowY = myDate.getFullYear();
    // var nowM = myDate.getMonth()+1;
    // var nowD = myDate.getDate();
    // var date = nowM+"/"+nowD+"/"+nowY%100;//当前日期
    var date="6/8/17";

    initAllStockNameTable(industryName);
    initSpecificIndustryPieChart(date,industryName);
    initSpecificIndustryInfo(date,industryName);
    initIncreaseTable(date,industryName);
    initDecreaseTable(date,industryName);
    initVolumeTable(date,industryName);
    initPotentialTable(date,industryName);
    initSimpleEventInfo(date,industryName);
    initSimpleTrendInfo(date,industryName);
    initSimpleInvestInfo(date,industryName);
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

//设置行业内股票一览表
function initAllStockNameTable(industryName) {
    var dataPack="";
    var tempUrl="/StockHubWeb/getIndustryAllStock?" +
        "industryName="+industryName;
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
            paintAllStockNameTable(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintAllStockNameTable(dataPack) {
    // alert("in line 53:"+dataPack+" "+dataPack.industry[0]);
    var table = document.createElement("table");

    var tableBody=document.createElement("tbody");
    var length=dataPack.stockName.length;
    for (var i = 0; i < length/6; i++)
    {//手动剪切了一部分的信息
        var tr=document.createElement("tr");
        for (var j=0;j<6;j++){
            var td=document.createElement("td");
            if((dataPack.stockName[6*i+j]==null)||(dataPack.stockName[6*i+j]=="")||(dataPack.stockName[6*i+j]=="undefined")){
                td.innerHTML="";
            }else {
                td.innerHTML="<a href='stock.html?codeOrName="+dataPack.stockName[6*i+j]+"' target='_blank'>"+dataPack.stockName[6*i+j]+"</a>";
            }
            tr.appendChild(td);
        }
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('allStockNameTable').innerHTML=tableDiv.innerHTML;
}

//初始化具体行业的饼状图
function initSpecificIndustryPieChart(date, industryName) {
    var dataPack="";
    var dateStr=date.toString();
    var industryNameStr=industryName.toString();
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
            paintSpecificIndustryPieChart(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintSpecificIndustryPieChart(dataPack) {
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
    // document.getElementById('specificIndustryPieChart').innerHTML=option;
    var myChart = echarts.init(document.getElementById('specificIndustryPieChart'));
    myChart.setOption(option);
}

//初始化具体行业下面的表格
function initSpecificIndustryInfo(date, industryName) {
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
            paintSpecificIndustryInfo(dataPack);
        }
    };
    xmlhttp.open("GET",tempUrl,true);
    xmlhttp.send();
}

function paintSpecificIndustryInfo(dataPack) {
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
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
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
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
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
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
    //以下实现行业跌幅榜
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
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
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
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
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
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

    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    tableRow.appendChild(th4);
    tableRow.appendChild(th5);
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
        td2.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].code+"</a>";
        var td3=document.createElement("td");
        td3.innerHTML="<a href='stock.html?codeOrName="+dataPack[i].code+"' target='_blank'>"+dataPack[i].name+"</a>";
        var td4=document.createElement("td");
        td4.innerHTML=dataPack[i].increaseOrDecrease+"%";
        var td5=document.createElement("td");
        td5.innerHTML=dataPack[i].volume;
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    document.getElementById('potentialTable').innerHTML=tableDiv.innerHTML;
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
        document.getElementById("investNews").innerHTML+="<li class=\"list-group-item\"><a href='investNews.html?title="+dataPack[i].title+"&time="+dataPack[i].time+"'>"+dataPack[i].title+"  "+dataPack[i].time+"</a></li>";
    }
}