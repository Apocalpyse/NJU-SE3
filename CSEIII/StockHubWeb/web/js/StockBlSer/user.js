/**
 * Created by thinkpad on 2017/6/3.
 */
var storage=window.localStorage;
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
enddate="6/8/17";
startdate="4/28/17";
// var KData=[{"date":"5/24/17","open":"10","high":"11","low":"9.8","close":"10.2","code":"60001","AdjClose":"13","name":"kl","market":"sa","increaseOrDecrease":"20%","preAdjClose":"1.2","plate":"1"},{"date":"5/25/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"10","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"30%","preAdjClose":"1.2","plate":"1"},{"date":"5/26/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"12","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"18%","preAdjClose":"1.2","plate":"1"},{"date":"5/27/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"14","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"16%","preAdjClose":"1.2","plate":"1"},{"date":"5/24/17","open":"10","high":"11","low":"9.8","close":"10.2","code":"60001","AdjClose":"13","name":"kl","market":"sa","increaseOrDecrease":"20%","preAdjClose":"1.2","plate":"1"},{"date":"5/25/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"10","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"30%","preAdjClose":"1.2","plate":"1"},{"date":"5/26/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"12","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"18%","preAdjClose":"1.2","plate":"1"},{"date":"5/27/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"14","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"16%","preAdjClose":"1.2","plate":"1"},{"date":"5/24/17","open":"10","high":"11","low":"9.8","close":"10.2","code":"60001","AdjClose":"13","name":"kl","market":"sa","increaseOrDecrease":"20%","preAdjClose":"1.2","plate":"1"},{"date":"5/25/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"10","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"30%","preAdjClose":"1.2","plate":"1"},{"date":"5/26/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"12","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"18%","preAdjClose":"1.2","plate":"1"},{"date":"5/27/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"14","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"16%","preAdjClose":"1.2","plate":"1"},{"date":"5/24/17","open":"10","high":"11","low":"9.8","close":"10.2","code":"60001","AdjClose":"13","name":"kl","market":"sa","increaseOrDecrease":"20%","preAdjClose":"1.2","plate":"1"},{"date":"5/25/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"10","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"30%","preAdjClose":"1.2","plate":"1"},{"date":"5/26/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"12","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"18%","preAdjClose":"1.2","plate":"1"},{"date":"5/27/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"14","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"16%","preAdjClose":"1.2","plate":"1"},{"date":"5/24/17","open":"10","high":"11","low":"9.8","close":"10.2","code":"60001","AdjClose":"13","name":"kl","market":"sa","increaseOrDecrease":"20%","preAdjClose":"1.2","plate":"1"},{"date":"5/25/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"10","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"30%","preAdjClose":"1.2","plate":"1"},{"date":"5/26/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"12","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"18%","preAdjClose":"1.2","plate":"1"},{"date":"5/27/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"14","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"16%","preAdjClose":"1.2","plate":"1"},{"date":"5/24/17","open":"10","high":"11","low":"9.8","close":"10.2","code":"60001","AdjClose":"13","name":"kl","market":"sa","increaseOrDecrease":"20%","preAdjClose":"1.2","plate":"1"},{"date":"5/25/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"10","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"30%","preAdjClose":"1.2","plate":"1"},{"date":"5/26/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"12","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"18%","preAdjClose":"1.2","plate":"1"},{"date":"5/27/17","open":"10","high":"11","low":"9.8","close":"10.2","AdjClose":"14","code":"60001","name":"kl","market":"sa","increaseOrDecrease":"16%","preAdjClose":"1.2","plate":"1"}];

var selfInfoButton=document.getElementById('selfInfoButton');
selfInfoButton.onclick=function () {
    var loginState=storage.getItem("alreadyLogin");
    if(loginState=="true") {
        initUserInfo();
    }else{
        alert("请登录后再试");
    }
};
// var selfSelectStockButton=document.getElementById('selfSelectStockButton');
// selfSelectStockButton.onclick=function () {
//     var loginState=storage.getItem("alreadyLogin");
//     if(loginState=="true") {
//         var account = storage.getItem("account");
//         initAnyOfTheCharts("selfSelectStockButton", account,  "UserContent");
//     }else{
//         alert("请登录后再试");
//     }
// };
//列表展示
// var buyButton=document.getElementById('buyButton');
// buyButton.onclick=function () {
//     var loginState=storage.getItem("alreadyLogin");
//     if(loginState=="true") {
//         var account = storage.getItem("account");
//         getProfit(startdate,enddate,account);
//     }else{
//         alert("请登录后再试");
//     }
// };
//列表展示历史购买
// var historyBuyButton=document.getElementById('historyBuyButton');
// historyBuyButton.onclick=function () {
//     var loginState=storage.getItem("alreadyLogin");
//     if(loginState=="true") {
//         var account = storage.getItem("account");
//         initAnyOfTheCharts("historyBuyButton", account, "UserContent");
//     }else{
//         alert("请登录后再试");
//     }
// };
//列表展示个人收藏新闻
var selfSelectNewsButton=document.getElementById('selfSelectNewsButton');
selfSelectNewsButton.onclick=function () {
    var loginState=storage.getItem("alreadyLogin");
    if(loginState=="true") {
        var account = storage.getItem("account");
        initAnyOfTheCharts("selfSelectNewsButton", account,  "UserContent");
    }else{
        alert("请登录后再试");
    }
}
//列表展示个人收藏的投资
var selfSelectInvestButton=document.getElementById('selfSelectInvestButton');
selfSelectInvestButton.onclick=function () {
    var loginState=storage.getItem("alreadyLogin");
    if(loginState=="true") {
        var account = storage.getItem("account");
        initAnyOfTheCharts("selfSelectNewsButton", account, "UserContent");
    }else{
        alert("请登录后再试");
    }
}
/************      buyButtonColumn未填写,buy的blser对应不是很清楚      *************/
function initAnyOfTheCharts(buttonID,account,componentID){
    var result;
    var url;
    var columns;
    var type="";
   if(buttonID=="selfSelectNewsButton"){
        url="/StockHubWeb/getCollectionNews"+"?"+"account="+account;
        columns=["title"];
        type="H";
    }else  if(buttonID=="selfSelectInvestButton"){
        url="/StockHubWeb/getCollectionInvest"+"?"+"account="+account;
        columns=["title"];
        type="I";
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
                result=xmlhttp.responseText;
                // alert(result);
                initList(result,columns,componentID,type);
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取信息失败");
    }
}
function initList(tableData,columns,componentId,type) {



    // alert(storage.getItem(componentId+"ForIndex"));
    tableData=JSON.parse(tableData);
    // alert("data:"+tableData+"length:"+tableData.length);
    // tableData=KData;

    var table = document.createElement("table");
    table.setAttribute("class","table table-hover");
    var tableHead=document.createElement("thead");
    var tableBody=document.createElement("tbody");
    var tableRow=document.createElement("tr");

    var th1=document.createElement("th");
    th1.innerHTML="排行";
    tableRow.appendChild(th1);
    for(var count=0;count<columns.length;count++){
        var th=document.createElement("th");
        th.innerHTML=columns[count];
        tableRow.appendChild(th);
    }
    tableHead.appendChild(tableRow);

    for (var i = 0; i < tableData.length; i++)
    {
        var tr=document.createElement("tr");
        var td1=document.createElement("td");
        td1.innerHTML=i+1+"";
        tr.appendChild(td1);

        var aLine=tableData[i];
        for(var count2=0;count2<columns.length;count2++){
            var td=document.createElement("td");
            var eValue=aLine[''+columns[count2]+''];
                td.innerHTML=eValue+"";

            tr.appendChild(td);
        }
        tableBody.appendChild(tr);
    }
    //获取列表各行内容
    table.appendChild(tableHead);
    table.appendChild(tableBody);

    var tableDiv=document.createElement("div");
    tableDiv.appendChild(table);
    var userDiv=document.getElementById(componentId);
    userDiv.setAttribute("class","bs-docs-example");
    userDiv.innerHTML=tableDiv.innerHTML;

}

// function initList(tableData,columns,componentId,pageSize,pages) {
//
//     //数据
//     storage.setItem(componentId+"ForIndex",tableData);
//     //初始化与否
//     storage.setItem(componentId+"ForIndexInit","true");
//     //
//     storage.setItem(componentId+"ForIndexPageSize",pageSize);
//     //存储最多的1-n页的n
//     storage.setItem(componentId+"ForIndexPages",pages);
//     //存储列项
//     storage.setItem(componentId+"ForIndexColumns",columns);
//
//     // alert(storage.getItem(componentId+"ForIndex"));
//     tableData=JSON.parse(tableData);
//     // alert("data:"+tableData+"length:"+tableData.length);
//     // tableData=KData;
//
//     initPages(tableData.length,pageSize,componentId,pages);
//
//     var table = document.createElement("table");
//     table.setAttribute("class","table table-hover");
//     var tableHead=document.createElement("thead");
//     var tableBody=document.createElement("tbody");
//     var tableRow=document.createElement("tr");
//
//     var th1=document.createElement("th");
//     th1.innerHTML="排行";
//     tableRow.appendChild(th1);
//     for(var count=0;count<columns.length;count++){
//         var th=document.createElement("th");
//         th.innerHTML=columns[count];
//         tableRow.appendChild(th);
//     }
//     tableHead.appendChild(tableRow);
//
//     for (var i = 0; i < tableData.length && i<pageSize; i++)
//     {
//         // alert(":"+i);
//         var tr=document.createElement("tr");
//         var td1=document.createElement("td");
//         td1.innerHTML=i+1+"";
//         tr.appendChild(td1);
//
//         var aLine=tableData[i];
//         for(var count2=0;count2<columns.length;count2++){
//             var td=document.createElement("td");
//             var eValue=aLine[''+columns[count2]+''];
//             td.innerHTML=eValue+"";
//             tr.appendChild(td);
//         }
//         tableBody.appendChild(tr);
//     }
//     //获取列表各行内容
//     table.appendChild(tableHead);
//     table.appendChild(tableBody);
//
//     var tableDiv=document.createElement("div");
//     tableDiv.appendChild(table);
//     var userDiv=document.getElementById(componentId);
//     userDiv.setAttribute("class","bs-docs-example");
//     userDiv.innerHTML=tableDiv.innerHTML;
//
// }
//
// //初始化表格分页框,页表框id为componentId+'Pages'，页项class与要去改变的内容框的id componentId相同,页表id为componentId+"menuForPages"，但大概用不上
// //pages为总共项数,pagesize为一页的项数,columns为表项的数组
// function initPages(pageItems,pageSize,componentId,pages) {
//     // alert(pages);
//     var pagesIndexs;
//     if(pageItems%pageSize==0){
//         pagesIndexs=pageItems/pageSize;
//     }else{
//         pagesIndexs=(pageItems-pageItems%pageSize)/pageSize+1;
//     }
//
//     // alert(pagesIndexs+"   "+pages);
//     var menu=document.createElement('ul');
//     menu.setAttribute("id","menuForPages");
//
//     //backtotop
//
//     var li=document.createElement('li');
//     var a=document.createElement('a');
//     a.setAttribute("class",componentId);
//     a.setAttribute("href","javascript:void(0);");
//     a.setAttribute("onclick","javascript:turnPages(this.innerHTML,this.getAttribute('class'));");
//     a.innerHTML="首页";
//     li.appendChild(a);
//     menu.appendChild(li);
//
//     for(var count=0;(count<pagesIndexs)&&(count<pages);count++){
//         // alert(count);
//         var li1=document.createElement('li');
//         var a1=document.createElement('a');
//         a1.setAttribute("class",componentId);
//         a1.setAttribute("id",componentId+count);
//         a1.setAttribute("href","javascript:void(0);");
//         a1.setAttribute("onclick","javascript:turnPages(this.innerHTML,this.getAttribute('class'));");
//         a1.innerHTML=count+1+"";
//         // alert(a1.innerHTML);
//         li1.appendChild(a1);
//         menu.appendChild(li1);
//     }
//
//     //goToEnd
//     var li2=document.createElement('li');
//     var a2=document.createElement('a');
//     a2.setAttribute("class",componentId);
//     a2.setAttribute("id",componentId+count);
//     a2.setAttribute("href","javascript:void(0);");
//     a2.setAttribute("onclick","javascript:turnPages(this.innerHTML,this.getAttribute('class'));");
//     a2.innerHTML="尾页";
//     li2.appendChild(a2);
//     menu.appendChild(li2);
//
//     var nav=document.createElement('nav');
//     nav.setAttribute("style","text-align: center");
//     nav.appendChild(menu);
//     var div=document.createElement('div');
//     div.appendChild(nav);
//     var userDiv=document.getElementById(componentId+'Pages').innerHTML=div.innerHTML;
//     // alert(pagesDiv);
// }
// //第几页以及具体的排行榜对应的id，翻页改变表格内容的响应
// function turnPages(pageIndex,componentId) {
//     // alert(pageIndex+" id： "+componentId);
//     var tableData=storage.getItem(componentId+"ForIndex");
//     // alert(tableData);
//     var pageSize=storage.getItem(componentId+"ForIndexPageSize");
//     // alert(pageSize);
//     var columns=storage.getItem(componentId+"ForIndexColumns");
//     // alert(columns);
//     var tableData=JSON.parse(tableData);
//     columns=columns.split(',');
//     var length=tableData.length;
//     if(pageIndex=="首页"){
//         pageIndex=1;
//     }else if(pageIndex=="尾页"){
//         if(length%pageSize==0){
//             pageIndex=length/pageSize;
//         }else{
//             pageIndex=(length-length%pageSize)/pageSize+1;
//         }
//     }
//     reRankPages(pageIndex,pageSize,length,componentId);
//     var table = document.createElement("table");
//     table.setAttribute("class","table table-hover");
//     var tableHead=document.createElement("thead");
//     var tableBody=document.createElement("tbody");
//     var tableRow=document.createElement("tr");
//
//     var th1=document.createElement("th");
//     th1.innerHTML="排行";
//     tableRow.appendChild(th1);
//     for(var count=0;count<columns.length;count++){
//         var th=document.createElement("th");
//         th.innerHTML=columns[count];
//         tableRow.appendChild(th);
//     }
//     // alert(columns);
//     tableHead.appendChild(tableRow);
//
//     //将获取的信息逐行压入列表
//     for (var i = (pageIndex-1)*pageSize; i < tableData.length && i<pageIndex*pageSize; i++)
//     {
//
//         var tr=document.createElement("tr");
//         var td1=document.createElement("td");
//         td1.innerHTML=i+1+"";
//         tr.appendChild(td1);
//
//         var aLine=tableData[i];
//         for(var count2=0;count2<columns.length;count2++){
//             var td=document.createElement("td");
//             var eValue=aLine[''+columns[count2]+''];
//             td.innerHTML=eValue+"";
//             tr.appendChild(td);
//         }
//         tableBody.appendChild(tr);
//     }
//     //获取列表各行内容
//     table.appendChild(tableHead);
//     table.appendChild(tableBody);
//
//     var tableDiv=document.createElement("div");
//     tableDiv.appendChild(table);
//     var userDiv=document.getElementById(componentId);
//     userDiv.setAttribute("class","bs-docs-example");
//     userDiv.innerHTML=tableDiv.innerHTML;
//
// }
// //点击了第几页，每页有多少数据，共有多少数据，表格id;将翻页按钮的坐标更新
// function reRankPages(pageIndex,pageSize,length,componentId) {
//     pageIndex=parseInt(pageIndex);
//     var pages= storage.getItem(componentId+"ForIndexPages");//可有的pages
//     pages=parseInt(pages);
//     var max;
//     if(length%pageSize==0){
//         max=length/pageSize;
//         max=parseInt(max);
//
//     }else{
//         max=(length-length%pageSize)/pageSize+1;
//         max=parseInt(max);
//     }
//     if(pageIndex+pages-1<=max){//从pageIndex开始
//         for(var count=0;count<pages;count++){
//             // alert("pageindex1:"+count+pageIndex);
//             document.getElementById(componentId+count).innerHTML=count+pageIndex;
//         }
//     }else{//从max-pages+1开始
//         for(var count2=0;count2<pages;count2++){
//             // alert("pageindex2:"+count2+max-pages+1);
//             // alert(isNaN(count2));
//             document.getElementById(componentId+count2).innerHTML=count2+max-pages+1+"";
//         }
//     }
//
//     // alert("pageindex:"+pageIndex+"pages:"+pages)
//
// }
   // storage.setItem('alreadyLogin','true');
   // storage.setItem('account','a000001');
   // storage.setItem('mail','286674013@qq.com');
   // storage.setItem('username','helloworld');
   // storage.setItem('realName','chr');
   // storage.setItem('password','000001');
   // storage.setItem('birth','03/11/97');
   // storage.setItem('phone','13002506530');
   // storage.setItem('html','13002506530');


function getProfit(start,end,account) {
    var result;
    var url="/StockHubWeb/getRealBuy"+"?"+"account="+account+"&"+"start="+startdate+"&"+"end="+enddate;;
    var columns;

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
                // initList(result,columns,componentID,10,5);
                getMethodProfit(result,start,end);
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取信息失败");
    }
}
function getMethodProfit(realBuy,start,end) {
    var result;
    var url="/StockHubWeb/methodProfit?"+"start="+start+"&"+"end="+end;
    var columns;

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
                realBuy=JSON.parse(realBuy);
                result=JSON.parse(result);


                var myChart = echarts.init(document.getElementById('UserContent'));
                // 指定图表的配置项和数据

                var colors = ['#5793f3', '#d14a61', '#675bba'];

                var dates1=result.dates;
                // alert(dates1);
                var data1=result.datesProfit;
                var data2=realBuy.datesProfit;
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
                        right: '20%',
                        left:'20%'
                    },
                    toolbox: {
                        feature: {
                            dataView: {show: true, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    legend: {
                        data:['累计收益率','最优收益率']
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
                            name: '累计收益率',
                            // min: -15,
                            // max: 15,
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
                            name: '累计收益率',
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
                            name:'实际收益率',
                            type:'line',
                            data:data2
                        },
                        {
                            name:'最优收益率',
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
        alert("获取信息失败");
    }
}

function initUserInfo() {
    var userInfoDiv=document.createElement("div");


    var userAccount=document.createElement("input");
    userAccount.setAttribute("id","account");
    userAccount.setAttribute("type","text");
    userAccount.setAttribute("class","form-control  input-lg ");
    userAccount.setAttribute("readonly","true");
    userAccount.setAttribute("placeholder","账户名");
    userAccount.setAttribute("value",storage.getItem('account'));

    var userAccountSpan=document.createElement("span");
    userAccountSpan.setAttribute("class","input-group-addon");
    userAccountSpan.innerHTML="登录名";
    var userAccountDiv=document.createElement("div");
    userAccountDiv.setAttribute("class","input-group");
    userAccountDiv.appendChild(userAccountSpan);
    userAccountDiv.appendChild(userAccount);


    var userName=document.createElement("input");
    userName.setAttribute("id","username");
    userName.setAttribute("type","text");
    userName.setAttribute("class","form-control  input-lg");
    userName.setAttribute("placeholder","昵称");
    userName.setAttribute("value",storage.getItem('username'));

    var userNameSpan=document.createElement("span");
    userNameSpan.setAttribute("class","input-group-addon");
    userNameSpan.innerHTML="昵称";
    var userNameDiv=document.createElement("div");
    userNameDiv.setAttribute("class","input-group");
    userNameDiv.appendChild(userNameSpan);
    userNameDiv.appendChild(userName);

    var realName=document.createElement("input");
    realName.setAttribute("id","realName");
    realName.setAttribute("type","text");
    realName.setAttribute("class","form-control  input-lg");
    realName.setAttribute("placeholder","姓名");
    realName.setAttribute("value",storage.getItem('realName'));

    var realNameSpan=document.createElement("span");
    realNameSpan.setAttribute("class","input-group-addon");
    realNameSpan.innerHTML="姓名";
    var realNameDiv=document.createElement("div");
    realNameDiv.setAttribute("class","input-group");
    realNameDiv.appendChild(realNameSpan);
    realNameDiv.appendChild(realName);

    var mail=document.createElement("input");
    mail.setAttribute("id","mail");
    mail.setAttribute("type","text");
    mail.setAttribute("class","form-control  input-lg");
    mail.setAttribute("placeholder","邮箱");
    mail.setAttribute("value",storage.getItem('mail'));

    var mailSpan=document.createElement("span");
    mailSpan.setAttribute("class","input-group-addon");
    mailSpan.innerHTML="邮箱";
    var mailDiv=document.createElement("div");
    mailDiv.setAttribute("class","input-group");
    mailDiv.appendChild(mailSpan);
    mailDiv.appendChild(mail);

    var phone=document.createElement("input");
    phone.setAttribute("id","phone");
    phone.setAttribute("type","text");
    phone.setAttribute("class","form-control  input-lg");
    phone.setAttribute("placeholder","电话");
    phone.setAttribute("value",storage.getItem('phone'));

    var phoneSpan=document.createElement("span");
    phoneSpan.setAttribute("class","input-group-addon");
    phoneSpan.innerHTML="电话";
    var phoneDiv=document.createElement("div");
    phoneDiv.setAttribute("class","input-group");
    phoneDiv.appendChild(phoneSpan);
    phoneDiv.appendChild(phone);

    var birth=document.createElement("input");
    birth.setAttribute("id","birth");
    birth.setAttribute("type","text");
    birth.setAttribute("class","form-control  input-lg");
    birth.setAttribute("placeholder","生日");
    birth.setAttribute("value",storage.getItem('birth'));

    var birthSpan=document.createElement("span");
    birthSpan.setAttribute("class","input-group-addon");
    birthSpan.innerHTML="生日";
    var birthDiv=document.createElement("div");
    birthDiv.setAttribute("class","input-group");
    birthDiv.appendChild(birthSpan);
    birthDiv.appendChild(birth);


    var button=document.createElement("button");
    button.setAttribute("id","saveEdition");
    button.setAttribute("class","btn btn-primary btn-lg btn-block");
    button.setAttribute("placeholder","保存修改");
    button.setAttribute("onclick","javascript:updateUserInfo();");
    button.innerHTML="保存修改";

    var clearDiv=document.createElement("div");
    clearDiv.setAttribute("class","clearfix");

    userInfoDiv.appendChild(userAccountDiv);
    userInfoDiv.appendChild(userNameDiv);
    userInfoDiv.appendChild(realNameDiv);
    userInfoDiv.appendChild(mailDiv);
    userInfoDiv.appendChild(phoneDiv);
    userInfoDiv.appendChild(birthDiv);
    userInfoDiv.appendChild(button);
    userInfoDiv.appendChild(clearDiv);



    document.getElementById('UserContent').innerHTML=userInfoDiv.innerHTML;
}

// String account=request.getParameter("account");
// String username=request.getParameter("username");
// String password=request.getParameter("password");
// String realName=request.getParameter("realName");
// String mail=request.getParameter("mail");
// String birth=request.getParameter("birth");
// String phone=request.getParameter("phone");

//
function updateUserInfo() {
    url="/StockHubWeb/changeUserInfo"+"?"+"account="+document.getElementById('account').value+"&"+"username="+document.getElementById('username').value+"&"+"realName="+document.getElementById('realName').value+"&"+"mail="+document.getElementById('mail').value+"&"+"birth="+document.getElementById('birth').value+"&"+"phone="+document.getElementById('phone').value+"&"+"password="+storage.getItem('password');
    var result;
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
                if(result=="true"){
                    alert("成功更新用户:"+document.getElementById('account').value+"的信息");
                }else{
                    alert("修改用户信息失败,该登录名已被使用");
                }
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取信息失败");
    }
}
function updateLocalInfo() {
    alert("用来在每次操作前更新本地用户信息,暂不考虑实现");
}


