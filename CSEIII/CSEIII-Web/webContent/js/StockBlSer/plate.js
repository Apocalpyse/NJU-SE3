/**
 * Created by thinkpad on 2017/5/31.
 */

//总的初始化分成三个部分
function ready() {
    //三个板块
    initMainBoard();
    initStartUpBoard();
    initSmeBoard();
    
}
//各个具体模块的初始化
function initMainBoard() {
    //行情排行榜
    initMainPlateSituation();
    initMainIncrease();
    initMainDecrease();
    initMainDeal();
    initMainPotential();
}
function initStartUpBoard() {
    //行情排行榜
    initStartUpPlateSituation();
    initStartUpIncrease();
    initStartUpDecrease();
    initStartUpDeal();
    initStartUpPotential();
}
function initSmeBoard() {
    //行情排行榜
    initSmePlateSituation();
    initSmeIncrease();
    initSmeDecrease();
    initSmeDeal();
    initSmePotential();
}
//一些基础的共用信息  日期等

//获取当前日期
var today = new Date();
var myDate = new Date(today-1000 * 60 * 60 * 24 );
var nowY = myDate.getFullYear();
var nowM = myDate.getMonth()+1;
var nowD = myDate.getDate();
var enddate = (nowM<10 ? "0" + nowM : nowM)+"/"+(nowD<10 ? "0"+ nowD : nowD)+"/"+nowY;//当前日期

//获取三十天前日期
var lw = new Date(myDate - 1000 * 60 * 60 * 24 * 31);//最后一个数字30可改，30天的意思
var lastY = lw.getFullYear();
var lastM = lw.getMonth()+1;
var lastD = lw.getDate();
var startdate=(lastM<10 ? "0" + lastM : lastM)+"/"+(lastD<10 ? "0"+ lastD : lastD)+"/"+lastY;//三十天之前日期


//主板
function initMainPlateSituation() {
    var code="000300";
    //主板

    var stockInfo={"start":startdate,"end":startdate,"code":code};
    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getOnePlateInfo",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,
        success: function (stockData) {
            var h4=document.getElementById('H4ForM').innerHTML=stockData[0].close+"("+stockData[0].increaseOrDecrease+")";
            var p1=document.getElementById('P1ForM').innerHTML="开盘："+" "+stockData[0].open;
            var p2=document.getElementById('P2ForM').innerHTML="闭盘："+" "+stockData[0].close;
            var p3=document.getElementById('P3ForM').innerHTML="最高："+" "+stockData[0].high;
            var p4=document.getElementById('P4ForM').innerHTML="最低："+" "+stockData[0].low;

        }

    });

}
function initMainIncrease() {
    var plateInfo={"date":enddate,"StockPoolBl":"MAINPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getIncList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('IncreaseForM').innerHTML=tableDiv.innerHTML;
        }
    });

}
function initMainDecrease() {
    var plateInfo={"date":enddate,"StockPoolBl":"MAINPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getDecList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DecreaseForM').innerHTML=tableDiv.innerHTML;
        }
    });

}
function initMainDeal() {
    var plateInfo={"date":enddate,"StockPoolBl":"MAINPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getVolumeList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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
            th4.innerHTML="成交量";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].volume;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DealForM').innerHTML=tableDiv.innerHTML;
        }
    });

}
function initMainPotential() {
    var plateInfo={"date":enddate,"StockPoolBl":"MAINPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getPotentialList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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
            th4.innerHTML="潜力值";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].potential;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('PotentialForM').innerHTML=tableDiv.innerHTML;
        }
    });

}
//创业板
function initStartUpPlateSituation() {
    var code="399006";
    //
    var stockInfo={"start":startdate,"end":startdate,"code":code};
    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getOnePlateInfo",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,
        success: function (stockData) {
            var h4=document.getElementById('H4ForSU').innerHTML=stockData[0].close+"("+stockData[0].increaseOrDecrease+")";
            var p1=document.getElementById('P1ForSU').innerHTML="开盘："+" "+stockData[0].open;
            var p2=document.getElementById('P2ForSU').innerHTML="闭盘："+" "+stockData[0].close;
            var p3=document.getElementById('P3ForSU').innerHTML="最高："+" "+stockData[0].high;
            var p4=document.getElementById('P4ForSU').innerHTML="最低："+" "+stockData[0].low;

        }

    });

}
function initStartUpIncrease() {
    var plateInfo={"date":enddate,"StockPoolBl":"STARTUPPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getIncList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('IncreaseForSU').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initStartUpDecrease() {
    var plateInfo={"date":enddate,"StockPoolBl":"STARTUPPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getDecList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DecreaseForSU').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initStartUpDeal() {
    var plateInfo={"date":enddate,"StockPoolBl":"STARTUPPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getVolumeList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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
            th4.innerHTML="潜力值";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].volume;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DealForSU').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initStartUpPotential() {
    var plateInfo={"date":enddate,"StockPoolBl":"STARTUPPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getPotentialList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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
            th4.innerHTML="潜力值";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].potential;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('IncreaseForSU').innerHTML=tableDiv.innerHTML;
        }
    });
}
//中小板
function initSmePlateSituation() {
    var code="399005";
    //
    var stockInfo={"start":startdate,"end":startdate,"code":code};
    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getOnePlateInfo",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:stockInfo,
        success: function (stockData) {
            var h4=document.getElementById('H4ForS').innerHTML=stockData[0].close+"("+stockData[0].increaseOrDecrease+")";
            var p1=document.getElementById('P1ForS').innerHTML="开盘："+" "+stockData[0].open;
            var p2=document.getElementById('P2ForS').innerHTML="闭盘："+" "+stockData[0].close;
            var p3=document.getElementById('P3ForS').innerHTML="最高："+" "+stockData[0].high;
            var p4=document.getElementById('P4ForS').innerHTML="最低："+" "+stockData[0].low;

        }

    });

}
function initSmeIncrease() {
    var plateInfo={"date":enddate,"StockPoolBl":"SMALLMIDDLEPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getIncList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('IncreaseForS').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initSmeDecrease() {
    var plateInfo={"date":enddate,"StockPoolBl":"SMALLMIDDLEPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getDecList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].increaseOrDecrease;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DecreaseForS').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initSmeDeal() {
    var plateInfo={"date":enddate,"StockPoolBl":"SMALLMIDDLEPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getVolumeList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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
            th4.innerHTML="成交量";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].volume;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('DealForS').innerHTML=tableDiv.innerHTML;
        }
    });
}
function initSmePotential() {
    var plateInfo={"date":enddate,"StockPoolBl":"SMALLMIDDLEPLATE"};

    $.ajax({
        url: "http://localhost:8080/web/PlateBLSer/getPotentialList",
        type:"POST",
        dataType: "json",
        contentType: "application/json; charset=utf-8",
        //设置响应时间
        timeout: 1000,
        async: false,
        // 默认是true，即为异步方式，$.Ajax执行后，会继续执行ajax后面的脚本，直到服务器端返回数据后，触发$.Ajax里的success方法，这时候执行的是两个线程。
        // 若要将其设置为false，则所有的请求均为同步请求，在没有返回值之前，同步请求将锁住浏览器，用户其它操作必须等待请求完成才可以执行。
        data:plateInfo,


        success: function (tableData) {
            var table = document.createElement("table");
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
            th4.innerHTML="潜力值";

            tableRow.appendChild(th1);
            tableRow.appendChild(th2);
            tableRow.appendChild(th3);
            tableRow.appendChild(th4);
            tableHead.appendChild(tableRow);

            for (var i = 0; i < tableData.length; i++)
            {
                var tr=document.createElement("tr");
                var td1=document.createElement("td");
                td1.innerHTML=i+1+"";
                var td2=document.createElement("td");
                td2.innerHTML=tableData[i].code;
                var td3=document.createElement("td");
                td3.innerHTML=tableData[i].name;
                var td4=document.createElement("td");
                td4.innerHTML=tableData[i].potential;
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tableBody.appendChild(tr);
            }
            //获取列表各行内容
            table.appendChild(tableHead);
            table.appendChild(tableBody);

            var tableDiv=document.createElement("div");
            tableDiv.appendChild(table);
            document.getElementById('PotentialForS').innerHTML=tableDiv.innerHTML;
        }
    });
}

