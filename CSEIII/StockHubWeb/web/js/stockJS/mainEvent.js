/**
 * Created by A on 2017/6/13.
 */
function initMainEvent() {
    var titleStr=getArgsFromHref("title");
    var timeStr=getArgsFromHref("time");

    var dataPack=[];
    var tempUrl="/StockHubWeb/getDetialMainEventInfo?" +
        "title="+titleStr+"&time="+timeStr;
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
            dataPack=eval(result);
        }
    };
    xmlhttp.open("GET",tempUrl,false);
    xmlhttp.send();

    var industryStr=dataPack.Industry;
    var contentStr=dataPack.content;

    //在界面显示内容
    document.getElementById('typeTitle').innerHTML="重大事项";
    var newsContent = document.createElement("div");
    newsContent.setAttribute("class", "page-header col-md-12");
    //标题部分
    var header = document.createElement("div");
    header.setAttribute("class", "page-header col-md-12");
    var title = document.createElement("h1");
    title.innerHTML = titleStr;
    var time = document.createElement("small");
    time.innerHTML = "时间：" + timeStr;
    var source = document.createElement("small");
    source.innerHTML = "行业：" + industryStr;
    var collectSpan=document.createElement("span");
    collectSpan.setAttribute("class","glyphicon glyphicon-star");
    title.appendChild(time);
    title.appendChild(source);
    header.appendChild(title);
    //内容
    var words = document.createElement("p");
    words.setAttribute("class", "col-md-12");
    words.innerHTML = contentStr;

    newsContent.appendChild(header);
    newsContent.appendChild(words);

    document.getElementById('NewsContent').innerHTML = newsContent.innerHTML;

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