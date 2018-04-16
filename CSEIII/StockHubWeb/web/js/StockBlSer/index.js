
/**
 * Created by thinkpad on 2017/5/22.
 */
document.write("<script language=javascript src='../echarts.min.js'></script>");
var storage=window.localStorage;
//获取当前日期(前一天)
var today = new Date();
var myDate = new Date(today-1000 * 60 * 60 * 24 );
var nowY = myDate.getFullYear()-1;
var nowM = myDate.getMonth()+1;
var nowD = myDate.getDate();
var enddate = nowM+"/"+nowD+"/"+nowY%100;//当前日期
//获取三十天前日期
var lw = new Date(myDate - 1000 * 60 * 60 * 24 * 31);//最后一个数字30可改，30天的意思
var lastY = lw.getFullYear()-1;
var lastM = lw.getMonth()+1-3;
var lastD = lw.getDate();
var startdate=lastM+"/"+lastD+"/"+lastY%100;//三十天之前日期
// alert(enddate+"  "+startdate);
//模拟数据
enddate="6/2/17";
startdate="2/28/17";
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


//获取某个jsonArray里的某一列信息并返回为array/***        特别的有对于涨跌幅/日期的处理      ***/
// function getSpecificColumn(data,column){
//     var result=[];
//     for(var count=0;count<data.length;count++){
//         result[count]=data[count][column];
//         if(column=="increaseOrDecrease"){
//             result[count]=(data[count][column].split("%"))[0];
//         }else if(column=="date"){
//             var theStr=data[count][column].split("/");
//             result[count]=theStr[0]+"/"+theStr[1];
//         }
//     }
//
//     return result;
// }
//获取股票信息

//暂不使用有返回值的http连接
function getSingleStockData(code,start,end) {
    var result;
    var url="/StockHubWeb/getDataByCode"+"?"+"code="+code+"&"+"start="+start+"&"+"end="+end;
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
                var stockData=eval(result);

            }
        }
        xmlhttp.open("GET",url,false);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取股票信息失败");
    }
}

//获取排行榜信息
function initRankList(type,date,componentId) {
    var result;
    var url="/StockHubWeb/"+type+"?"+"date="+date;
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
                initATable(date,result,componentId,5,3);
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取排行榜信息失败");
    }
}
//获取排行榜信息
function initPotentialRankList(type,date,componentId) {
    var result;
    var url="/StockHubWeb/"+type+"?"+"start="+date+"&"+"end="+date+"&"+"stockPool="+"ALL";
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
                initATable(date,result,componentId,5,3);
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
    if(componentId!="PotentialTable") {
        var th4 = document.createElement("th");
        th4.innerHTML = "涨幅";
        var th5 = document.createElement("th");
        th5.innerHTML = "成交量";
    }else{
        var th6 = document.createElement("th");
        th6.innerHTML = "得分";
    }


    //表头
    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    if(componentId!="PotentialTable") {
        tableRow.appendChild(th4);
        tableRow.appendChild(th5);
    }else{
        tableRow.appendChild(th6);
    }

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
        // alert(a1.getAttribute('href'));
        a1.innerHTML=tableData[i].code;
        td2.appendChild(a1);
        var td3=document.createElement("td");
        var a2=document.createElement("a");
        a2.setAttribute("href","stock.html?codeOrName="+tableData[i].code);
        a2.innerHTML=tableData[i].name;
        td3.appendChild(a2);
        if(componentId!="PotentialTable") {
            var td4=document.createElement("td");
            td4.innerHTML=tableData[i].increaseOrDecrease;
            var td5=document.createElement("td");
            td5.innerHTML=tableData[i].volume;
        }else{
            var td6=document.createElement("td");
            td6.innerHTML=tableData[i].total;
        }



        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        if(componentId!="PotentialTable") {
            tr.appendChild(td4);
            tr.appendChild(td5);
        }else{
            tr.appendChild(td6);
        }


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
    if(componentId!="PotentialTable") {
        var th4 = document.createElement("th");
        th4.innerHTML = "涨幅";
        var th5 = document.createElement("th");
        th5.innerHTML = "成交量";
    }else{
        var th6 = document.createElement("th");
        th6.innerHTML = "得分";
    }


    //表头
    tableRow.appendChild(th1);
    tableRow.appendChild(th2);
    tableRow.appendChild(th3);
    if(componentId!="PotentialTable") {
        tableRow.appendChild(th4);
        tableRow.appendChild(th5);
    }else{
        tableRow.appendChild(th6);
    }

    tableHead.appendChild(tableRow);
    //将获取的信息逐行压入列表
    for (var i = (pageIndex-1)*pageSize; i < tableData.length && i<pageIndex*pageSize; i++)
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
        if(componentId!="PotentialTable") {
            var td4=document.createElement("td");
            td4.innerHTML=tableData[i].increaseOrDecrease;
            var td5=document.createElement("td");
            td5.innerHTML=tableData[i].volume;
        }else{
            var td6=document.createElement("td");
            td6.innerHTML=tableData[i].total;
        }



        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        if(componentId!="PotentialTable") {
            tr.appendChild(td4);
            tr.appendChild(td5);
        }else{
            tr.appendChild(td6);
        }


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
    // alert(pageIndex+pageSize+length+componentId);
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
//zhe线图
function initMarketChart(stockPool,start,end,componentId,stockTitle){
    //数据获取


    var result;
    var url="/StockHubWeb/getOnePlateKChart"+"?"+"stockPool="+stockPool+"&"+"startDate="+start+"&"+"endDate="+end;
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
                var KData=JSON.parse(result);
                // 路径配置
                componentId="divFor"+componentId+"Chart";
                var myChart = echarts.init(document.getElementById(componentId));
                // 指定图表的配置项和数据

                var colors = ['#5793f3', '#d14a61', '#675bba'];

                var dates1=KData.date;
                // alert(dates1);
                var data1=KData.adjClose;
                var data2=KData.inOrDeYield;
                // alert(data2);

                option = {
                    color: colors,

                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'cross'
                        }
                    },
                    grid: {
                        right: '15%',
                        left:'25%'
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
                            data: dates1
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
                                formatter: '{value} (%)'
                            }
                        },
                        {
                            type: 'value',
                            name: '股票价格',
                            // min: 0,
                            // max: 25,
                            position: 'left',
                            axisLine: {
                                lineStyle: {
                                    color: colors[2]
                                }
                            },
                            axisLabel: {
                                formatter: '{value} '
                            }
                        }
                    ],
                    series: [
                        {
                            name:'涨跌幅',
                            type:'line',
                            data:data2
                        },
                        {
                            name:'股票价格',
                            type:'line',
                            yAxisIndex: 1,
                            data:data1
                        }
                    ]
                };
                myChart.setOption(option);
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取股票信息失败");
    }




}
//板指
function initMarketStatistic(date,componentIds) {
    // alert(date);
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
                // alert(result);
                var data=JSON.parse(result);
                // alert(data.totalVolume[count]);
                // data=KData;
                for(var count=0;count<5;count++){
                    var vId = "valueOf" + componentIds[count];
                    var gpId = "growthPercentageOf" + componentIds[count];
                    var gaId = "growthAmountOf" + componentIds[count];
                    document.getElementById(vId).innerHTML = data.totalVolume[count]+"亿";
                    document.getElementById(gpId).innerHTML = data.increaseOrDecreaseRate[count]+"%";
                    document.getElementById(gaId).innerHTML = data.increaseOrDecreaseMoney[count]+"亿";
                }
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取股票信息失败");
    }

}



// initNews("getNewsD",enddate,"HeadLines");
// var newsData=[
//     {"newsID":"12340","title":"aTitle"},
//     {"newsID":"12341","title":"aTitle"},
//     {"newsID":"12342","title":"aTitle"},
//     {"newsID":"12343","title":"aTitle"},
//     {"newsID":"12344","title":"aTitle"}
// ];

//测试代码
// var news=document.getElementById('HeadLines');
// var dataDiv=document.createElement("div");
// serries="H";
// for (var indexI = 0; indexI<5 && indexI < newsData.length; indexI++)
// {//手动剪切了一部分的信息(最多为五)
//     var adiv=document.createElement("div");
//     var id=serries+indexI;
//     adiv.setAttribute("id",id);
//     adiv.setAttribute("class","well");
//     var aa=document.createElement("a");
//     var id2=serries+indexI+"A";
//     aa.setAttribute("id",id2);
//     aa.setAttribute("href","news.html?"+"ID="+newsData[indexI].newsID+"&"+"type="+serries);
//     //设置链接的位置(设置ID)
//     aa.setAttribute("target","_blank");
//     aa.innerHTML=newsData[indexI].title;
//     adiv.appendChild(aa);
//     dataDiv.appendChild(adiv);
// }
// news.innerHTML=dataDiv.innerHTML;

function initNews(type,date,ID) {

    var url;
    var serries;
    if(type=="getNewsD"){
        serries="H";
        url="/StockHubWeb/"+type+"?"+"date="+date;
    }else if(type=="getNewsM"){
        serries="H";
        url="/StockHubWeb/"+type;
    }else if(type=="getInvestD"){
        serries="I";
        url="/StockHubWeb/"+type+"?"+"date="+date;
    }else if(type=="getInvestM"){
        serries="I";
        url="/StockHubWeb/"+type;
    }else if(type=="getNewsC1"){
        serries="B";
        url="/StockHubWeb/"+type+"?"+"classify="+date;
    }else if(type=="emergencies"){
        serries="E";
    }else{
        serries="Other";
    }
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
                // alert("initNews");

                /*************        较为关键的类型区分部分      ************/
                var newsData = xmlhttp.responseText;
                // alert(newsData);
                newsData=JSON.parse(newsData);
                // newsData=newsData1;
                var news=document.getElementById(ID);
                var dataDiv=document.createElement("div");
                for (var indexI = 0; indexI<5 && indexI < newsData.length; indexI++)
                {//手动剪切了一部分的信息(最多为五)
                    var adiv=document.createElement("div");
                    var id=serries+indexI;
                    adiv.setAttribute("id",id);
                    adiv.setAttribute("class","well");
                    var aa=document.createElement("a");
                    var id2=serries+indexI+"A";
                    aa.setAttribute("id",id2);
                    aa.setAttribute("href","news.html?"+"ID="+newsData[indexI].newsID+"&"+"type="+serries);
                    //设置链接的位置(设置ID)
                    aa.setAttribute("target","_blank");
                    aa.innerHTML=newsData[indexI].title;
                    adiv.appendChild(aa);
                    dataDiv.appendChild(adiv);
                }
                news.innerHTML=dataDiv.innerHTML;
            }
        };
        xmlhttp.open("GET",url,true);//true为异步加载
        xmlhttp.send();

    }catch (e){
        alert("获取新闻信息失败");
    }

}


function ready() {
    //五个扳指
    var code1 = "000001";
    var stockTitle1="上证指数";
    var componentID1="SCI";
    var code2 ="399001";
    var stockTitle2="深圳成指";
    var componentID2="SIASA";
    var code3="000300";
    var stockTitle3="沪深300";
    var componentID3="CSI300";
    var code4="399005";
    var stockTitle4="中小板指";
    var componentID4="SPF";
    var code5="399006";
    var stockTitle5="创业板指";
    var componentID5="GEM";

    // //对应的K线图
    initMarketChart("HUSHEN300",startdate,enddate,componentID3,stockTitle3);
    initMarketChart("SHANGZHENG",startdate,enddate,componentID1,stockTitle1);
    initMarketChart("SHENZHENG",startdate,enddate,componentID2,stockTitle2);
    initMarketChart("SMALLMIDDLEPLATE",startdate,enddate,componentID4,stockTitle4);
    initMarketChart("STARTUPPLATE",startdate,enddate,componentID5,stockTitle5);

    // //对应板指
    var componentIDs=[componentID1,componentID2,componentID3,componentID4,componentID5];
    initMarketStatistic(enddate,componentIDs);

    // 行情排行榜
    initRankList("getIncreaseList",enddate,"IncreaseTable");
    initRankList("getDecreaseList",enddate,"DecreaseTable");
    initRankList("getVolumeList",enddate,"DealTable");
    initPotentialRankList("getStockGoalTwenty",enddate,"PotentialTable");
    // // //新闻(投资参考)
    var type1="getNewsM";
    var newsID1="HeadLines";
    var type2="getInvestM";
    var newsID2="Investments";
    var type3="getNewsC1";
    var newsID3="Bond";
    initNews(type1,enddate,newsID1);
    initNews(type2,enddate,newsID2);
    // initNews(type3,"证券",newsID3);




}
