/**
 * Created by thinkpad on 2017/6/8.
 */
var storage = window.localStorage;
var newsData1 =
    {
        "title": "新闻标题1",
        "author": "stockhub",
        "time": "17/5/25",
        "source": "stockhub.net",
        "view": "188",
        "praise": "100",
        "criticize": "20",
        "content": "习近平24日上午视察海军机关，代表党中央和中央军委，他强调，海军是战略性军种，在国家安全和发展全局中具有十分重要的地位。要以党在新形势下的强军目标为引领，贯彻新形势下军事战略方针，坚持政治建军、改革强军、依法治军，瞄准世界一流，锐意开拓进取，加快转型建设，努力建设一支强大的现代化海军，为实现中国梦强军梦提供坚强力量支撑。",
        "comment": ["我想问最近最火的tfboys呢他们的八卦呢我想问最近最火的tfboys呢,他们的八卦呢我想问最近最火的tfboys呢？！他们的八卦呢？！", "我想问最近最火的tfboys呢他们的八卦呢我想问最近最火的tfboys呢,他们的八卦呢我想问最近最火的tfboys呢？！他们的八卦呢？！", "我想问最近最火的tfboys呢他们的八卦呢我想问最近最火的tfboys呢,他们的八卦呢我想问最近最火的tfboys呢？！他们的八卦呢？！"],
        "commentAccount": ["per1", "per2", "per3"]
    };
/*
 *函数功能：从href获得参数
 *sHref: http://www.cscenter.com.cn/arg.htm?arg1=d&arg2=re
 *sArgName:arg1, arg2
 *return: the value of arg. d, re
 */
//获取新闻标题

function GetArgsFromHref(code)
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
var newsID=GetArgsFromHref('ID');
var newsType=GetArgsFromHref('type');

function getNewsData(ID) {
    var result;
    var url;
    if(newsType=="H") {
        url = "/StockHubWeb/getNewsT" + "?" + "newsID=" + ID;
    }else  if(newsType=="I") {
        url = "/StockHubWeb/getInvestT" + "?" + "investID=" + ID;
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
                // alert("newsdata:"+result);
                initNews(result);
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("数据获取失败，请刷新后再试");
    }
}
// initNews(newsData1);
function initNews(newsData) {
    //获取数据
    newsData=JSON.parse(newsData);
    //强制转换不适用于json
    if(newsType=="H"){
        document.getElementById('typeTitle').innerHTML="新闻";
    }else if(newsType=="I"){
        document.getElementById('typeTitle').innerHTML="投资参考";
    }else {
        document.getElementById('typeTitle').innerHTML="文章";
    }
    var newsContent = document.createElement("div");
    newsContent.setAttribute("class", "page-header col-md-12");
    //标题部分
    var header = document.createElement("div");
    header.setAttribute("class", "page-header col-md-12");
    var title = document.createElement("h1");
    title.innerHTML = newsData.title;
    var classify = document.createElement("small");
    classify.setAttribute("id","classify");
    classify.innerHTML = "类别:" + newsData.classify;
    var time = document.createElement("small");
    time.innerHTML = "时间:" + newsData.time;
    time.setAttribute("id","time");
    // var source = document.createElement("small");
    // source.innerHTML = "来源:" + newsData.source;
    var view = document.createElement("small");
    view.innerHTML = "浏览:" + newsData.view;
    view.setAttribute("id","view");
    var collectSpan=document.createElement("span");
    collectSpan.setAttribute("class","glyphicon glyphicon-star");
    var collectButton = document.createElement("button");
    collectButton.setAttribute("id","collectButton");
    collectButton.setAttribute("class","btn btn-primary btn-xs pull-right");
    collectButton.appendChild(collectSpan);
    collectButton.setAttribute("onclick","javascript:Collect();");

    title.appendChild(classify);
    title.appendChild(time);
    // title.appendChild(source);
    title.appendChild(view);
    title.appendChild(collectButton);
    header.appendChild(title);
    //主体部分

    var words = document.createElement("p");
    words.setAttribute("class", "col-md-12");
    words.innerHTML = newsData.content;
    //点赞，点踩
    //            <button type="button" class="btn btn-xs btn-success">特别小的成功按钮</button>
    var upADown = document.createElement("div");
    upADown.setAttribute("class", "col-md-2 col-center-block");
    var ups = document.createElement("button");
    ups.setAttribute("id", "ups");
    ups.setAttribute("class", "btn btn-xs btn-success");
    ups.setAttribute("onclick","javascript:Praise();");
    ups.innerHTML = "赞一个" + "(" + newsData.praise + ")";
    var downs = document.createElement("button");
    downs.setAttribute("id", "downs");
    downs.setAttribute("class", "btn btn-xs btn-fail");
    downs.setAttribute("onclick","javascript:Criticize();");
    downs.innerHTML = "踩一下" + "(" + newsData.criticize + ")";
    upADown.appendChild(ups);
    upADown.appendChild(downs);
    //讨论区
    var comments = document.createElement("div");
    comments.setAttribute("class", "col-md-12 comment_whole");
    for (var i = 0; i < newsData.comment.length; i++) {
//        alert(i+":"+newsData.commentAccount[i]+" "+newsData.comment[i]);
        var oneComment = document.createElement("div");
        oneComment.setAttribute("class", "comment_A");
        var user = document.createElement("p");
        user.setAttribute("class", "comment_name");
        user.innerHTML = newsData.commentAccount[i]+":";
        var commentWords = document.createElement("p");
        commentWords.setAttribute("class", "comment_comment");
        commentWords.innerHTML = newsData.comment[i];
        oneComment.appendChild(user);
        oneComment.appendChild(commentWords);
        comments.appendChild(oneComment);
    }


    var inputArea=document.createElement("div");
    var formGroup=document.createElement("div");
    formGroup.setAttribute("class","form-group col-md-10 col-center-block ");
    var formLabel=document.createElement("label");
    formLabel.setAttribute("for","name");
    formLabel.innerHTML="评论：";
    var textArea=document.createElement("textarea");
    textArea.setAttribute("class","form-control");
    textArea.setAttribute("rows","4");
    textArea.setAttribute("id","commentInputArea");
    var AButton=document.createElement("button");
    AButton.setAttribute("class","btn btn-default pull-right");
    AButton.setAttribute("id","commentInputButton");
    AButton.setAttribute("onclick","javascript:Comment();");
    AButton.innerHTML="发布";

    formGroup.appendChild(formLabel);
    formGroup.appendChild(textArea);
    formGroup.appendChild(AButton);

    inputArea.appendChild(formGroup);



    newsContent.appendChild(header);
    newsContent.appendChild(words);
    newsContent.appendChild(upADown);
    newsContent.appendChild(comments);
    newsContent.appendChild(inputArea);
    document.getElementById('NewsContent').innerHTML = newsContent.innerHTML;


}

function addPraiseOrCriticize(type,ID)
{
    var url;
    if(newsType=="H") {
        url = "/StockHubWeb/"+type+"?" + "newsID=" + ID;
    }else  if(newsType=="I") {
        url = "/StockHubWeb/"+type+ "?" + "investID=" + ID;
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
        xmlhttp.open("GET",url,true);//true为异步加载
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                // ups.innerHTML = "赞一个" + "(" + newsData.praise + ")";
                if(xmlhttp.responseText=="true"){
                    /*********      具体返回结果可能有不同      *********/
                    if(type=="addPraise"||type=="addNewsPraise"){
                        var ups=document.getElementById('ups');
                        var html=ups.innerHTML.split('(')[1].split(')')[0];
                        html=parseInt(html)+1;
                        ups.innerHTML = "赞一个" + "(" + html + ")";
                        // alert("点赞成功");
                    }else if(type=="addNewsCriticize"||type=="addCriticize"){
                        var downs=document.getElementById('downs');
                        var html2=downs.innerHTML.split('(')[1].split(')')[0];
                        html2=parseInt(html2)+1;
                        downs.innerHTML = "踩一个" + "(" + html2 + ")";
                        // alert("点踩成功");
                    }else if(type=="addNewsView"||type=="addView"){
                        var view=document.getElementById('view');
                        var html3=view.innerHTML.split(':')[1];
                        html3=parseInt(html3)+1;
                        html3.innerHTML = "浏览量:" + "(" + html3 + ")";
                        // alert("浏览成功");
                    }
                }

            }
        }
    }catch (e){
        // alert("获取信息失败");
    }
}

function addComment(Type,text,account,newsID)
{
    var url;
    if(newsType=="H") {
        url="/StockHubWeb/"+Type+"?"+"comAccount="+account+"&"+"com="+text+"&"+"newsID="+newsID;
    }else  if(newsType=="I") {
        url="/StockHubWeb/"+Type+"?"+"comAccount="+account+"&"+"com="+text+"&"+"investID="+newsID;
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
        xmlhttp.open("GET",url,true);//true为异步加载
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                // alert(xmlhttp.responseText);
                if(xmlhttp.responseText=="true"){
                    /*********      具体返回结果可能有不同      *********/
                    document.getElementById('commentInputArea').value="";
                    alert("评论成功");
                }else{
                    alert("评论失败");
                }

            }
        }
    }catch (e){
        alert("无法评论，请检查网络连接");
    }
}

function collectNews(Type,account,newsID)
{
    var url;
    if(newsType=="H") {
        url="/StockHubWeb/"+Type+"?"+"account="+account+"&"+"newsID="+newsID;
    }else  if(newsType=="I") {
        url="/StockHubWeb/"+Type+"?"+"account="+account+"&"+"investID="+newsID;
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
        xmlhttp.open("GET",url,true);//true为异步加载
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {

                if(xmlhttp.responseText=="true"){
                    /*********      具体返回结果可能有不同      *********/
                    alert("收藏成功");
                }else {
                    alert("收藏失败");
                }

            }
        }
    }catch (e){
        alert("无法收藏，请检查网络连接");
    }
}
function checkCollectNews(Type,account,newsID)
{
    var url;
    if(newsType=="H") {
        url="/StockHubWeb/"+Type+"?"+"account="+account+"&"+"newsID="+newsID;
    }else  if(newsType=="I") {
        url="/StockHubWeb/"+Type+"?"+"account="+account+"&"+"investID="+newsID;
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
        xmlhttp.open("GET",url,true);//true为异步加载
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {

                if(xmlhttp.responseText=="true"){
                    /*********      具体返回结果可能有不同      *********/
                    alert("请勿重复收藏");
                }else {
                    if(newsType=="H"){
                        collectNews("addCollectionNews",account, newsID);
                        alert("收藏成功");
                    }else{
                        collectNews("addCollectionInvest",account, newsID);
                        alert("收藏成功");
                    }
                }

            }
        }
    }catch (e){
        alert("无法收藏，请检查网络连接");
    }
}

function Praise() {
    // alert("p");
    if(newsType=="H") {
        addPraiseOrCriticize("addNewsPraise", newsID);
    }else if(newsType="I"){
        addPraiseOrCriticize("addPraise", newsID);
    }
}//点赞
function Criticize() {
    // alert("C1");
    if(newsType=="H") {
        addPraiseOrCriticize("addNewsCriticize", newsID);
    }else if(newsType="I"){
        addPraiseOrCriticize("addCriticize", newsID);
    }
}//点踩
function Comment() {
    // alert("C2");
    var loginState = localStorage.getItem("alreadyLogin");
    if (loginState == "true") {
        var text = document.getElementById('commentInputArea').value;
        //innerhtml 可能有问题
        if (text == "") {
            alert("请先输入内容");
        } else {
            if(newsType=="H") {
                addComment("addNewsComment",text, localStorage.getItem("account"), newsID);
                document.getElementById('commentInputArea').innerHTML = "";
            }else if(newsType="I"){
                addComment("addComment",text, localStorage.getItem("account"), newsID);
                document.getElementById('commentInputArea').innerHTML = "";
            }
        }
    } else {
        alert("请登录后再继续该操作")
    }
}//评论
function Collect() {
    // alert("C3");
    var loginState = localStorage.getItem("alreadyLogin");
    if (loginState == "true") {
        if(newsType=="H") {
            checkCollectNews("isAlreadyCollectionNews",localStorage.getItem("account"), newsID);
        }else if(newsType="I"){
            checkCollectNews("isAlreadyCollection",localStorage.getItem("account"), newsID);
        }
    } else {
        alert("请登录后再继续该操作")
    }
}//收藏
function addView() {
    if(newsType=="H") {
        addPraiseOrCriticize("addNewsView", newsID);
    }else if(newsType="I"){
        addPraiseOrCriticize("addView", newsID);
    }
}//浏览量