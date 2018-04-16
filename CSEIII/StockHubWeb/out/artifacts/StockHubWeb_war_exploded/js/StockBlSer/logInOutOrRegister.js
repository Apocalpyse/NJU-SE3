/**
 * Created by thinkpad on 2017/6/3.
 */



var storage = window.localStorage;
// storage.clear();
//获取登录是否成功信息


function getLoginResult(account,password)
{
    var result="noResult";
    var url="/StockHubWeb/login"+"?"+"account="+account+"&"+"password="+password;
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
                    $('#loginDiv').modal('hide');
                    getLoginInfo(account);
                }else{
                    alert("登录失败");
                }


            }
        };
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取登录信息失败");
    }
}
//
function getLoginInfo(account)
{
    // alert(account);
    var result;
    var url="/StockHubWeb/getUserInfo"+"?"+"account="+account;
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

                result = xmlhttp.responseText;
                // alert(result);
                setLoginInfo(result);
            }
        };
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e1){
        alert("获取用户信息失败");
    }
}

function setLoginInfo(userData) {
    localStorage.clear();
    // alert("info:  "+userData);
    userData=JSON.parse(userData);
    storage.setItem('alreadyLogin','true');
    storage.setItem('account',userData.account);
    // alert(storage.getItem('account'));
    storage.setItem('mail',userData.mail);
    storage.setItem('username',userData.username);
    storage.setItem('realName',userData.realName);
    storage.setItem('password',userData.password);
    storage.setItem('birth',userData.birth);
    storage.setItem('phone',userData.phone);
    initLoginInfo();
}
function alertStorageInfo() {
    var storageItems="";
    for(var icount=0;icount<storage.length;icount++){
        storageItems=storageItems+storage.key(icount)+":"+storage.getItem(storage.key(icount))+"  +  ";
    }
    alert(storageItems);
}

//输出现有的items
//    storage.setItem('alreadyLogin','true');
//    storage.setItem('account','a000001');
//    storage.setItem('mail','286674013@qq.com');
//    storage.setItem('username','helloworld');
//    storage.setItem('realName','chr');
//    storage.setItem('password','000001');
//    storage.setItem('birth','03/11/97');
//    storage.setItem('phone','13002506530');
//    storage.setItem('html','13002506530');
//
//    var initStatus = storage.getItem('alreadyLogin');
//    var getAccount = storage.getItem('account');
//    var getMail = storage.getItem('mail');
//    var getUser = storage.getItem('username');
//    var getReal = storage.getItem('realName');
//    var getPass = storage.getItem('password');
//    var getBir  = storage.getItem('birth');
//    var getPhone = storage.getItem('phone');

function initLogin() {
    var getStatus = storage.getItem('alreadyLogin');
    if(getStatus!="true"){//未登陆，初始化登录div
        // alert("如果您想获得更完善的服务，欢迎注册与登录 ");
        initNoLoginInfo();
        //一开始的尝试
    }else{
        var getAccount = storage.getItem('account');
        getLoginInfo(getAccount);
        initLoginInfo();
    }
}
initLogin();


//未登录情况下的初始化工作
function initNoLoginInfo() {
    var deleteOrigin = document.getElementById('userButton');
    if(deleteOrigin!=null) {
        deleteOrigin.parentNode.removeChild(deleteOrigin);
    }
    var IorOButton=document.createElement("button");
    IorOButton.setAttribute("class","btn btn-primary btn-lg");
    IorOButton.setAttribute("id","userButton");
    IorOButton.setAttribute("data-toggle","modal");
    IorOButton.setAttribute("data-target","#loginDiv");
    IorOButton.innerHTML="登录";

    var ADiv=document.createElement("div");
    ADiv.appendChild(IorOButton);

    var buttonDiv=document.getElementById('buttonDiv');
    buttonDiv.innerHTML=ADiv.innerHTML;

    // var loginButton=document.getElementById('buttonDiv');
}

//已登录情况下的初始化工作
function initLoginInfo() {

    var deleteOrigin = document.getElementById('userButton');
    if(deleteOrigin!=null) {
        deleteOrigin.parentNode.removeChild(deleteOrigin);
    }

    var IorOButton2=document.createElement("button");
    IorOButton2.setAttribute("id","userButton");
    IorOButton2.setAttribute("class","btn btn-primary btn-lg dropdown-toggle");
    IorOButton2.setAttribute("type","button");
    IorOButton2.setAttribute("data-toggle","dropdown");
    IorOButton2.innerHTML=storage.getItem('username');
    var ASpan=document.createElement("span");
    ASpan.setAttribute("class","caret");
    IorOButton2.appendChild(ASpan);

    var AUl=document.createElement("ul");
    AUl.setAttribute("class","dropdown-menu");
    var li1=document.createElement("li");
    var a1=document.createElement("a");
    a1.setAttribute("href","user.html");
    /*********        涉及页面跳转           ************/
    a1.innerHTML="个人主页";
    li1.appendChild(a1);

    var li2=document.createElement("li");
    var AButton2=document.createElement("button");
    AButton2.setAttribute("class","btn  btn-block");
    AButton2.setAttribute("id","logoutButton");
    AButton2.setAttribute("onclick","javascript:logout();");
    AButton2.innerHTML="登出";
    li2.appendChild(AButton2);
    AUl.appendChild(li1);
    AUl.appendChild(li2);

    var ADiv=document.createElement("div");
    ADiv.appendChild(IorOButton2);
    ADiv.appendChild(AUl);


    var buttonDiv=document.getElementById('buttonDiv');
    buttonDiv.setAttribute("class","btn-group-vertical");
    buttonDiv.innerHTML=ADiv.innerHTML;


}

function login(){
    var getStatus = storage.getItem('alreadyLogin');
    var account=document.getElementById('loginAccount').value;
    var password=document.getElementById('loginPassword').value;
    if(getStatus=="true"){//未登录情况下
        alert("已登录，请勿登录多个帐号")
    }else{
        getLoginResult(account,password);
    }
}
function logout(){
    clearUserInfo();
    initNoLoginInfo();
}
function clearUserInfo() {
    storage.setItem('alreadyLogin','false');
    storage.setItem('account','null');
    storage.setItem('mail','null');
    storage.setItem('username','null');
    storage.setItem('realName','null');
    storage.setItem('password','null');
    storage.setItem('birth','null');
    storage.setItem('phone','null');
    storage.setItem('html','null');
}
function register(account,username,password) {
    var result="false";
    var url="/StockHubWeb/register"+"?"+"account="+account+"&"+"username="+username+"&"+"password="+password;
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
                    result="true";
                    $('#registerDiv').modal('hide');
                    // getLoginInfo(account);//注册后可以直接登录
                    alert("注册成功,请重新登录");
                }else{
                    alert("注册失败");
                }

            }
        };
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("注册失败");
    }
}
var loginNowButton=document.getElementById('loginNowButton');
loginNowButton.onclick=function () {
    login();
}//立即登录
var registerNowButton=document.getElementById('registerNowButton');
registerNowButton.onclick=function () {
    var account=document.getElementById('registerAccount').value;
    var username=document.getElementById('registerUserName').value;
    var password=document.getElementById('registerPassword').value;
    var password2=document.getElementById('registerPassword2').value;
    if(password==password2) {
        register(account, username, password);
    }else{
        alert("密码不一致");
    }
}//立即注册

function CheckPasswordResult(account,password,password2)
{
    var result="noResult";
    var url="/StockHubWeb/login"+"?"+"account="+account+"&"+"password="+password;
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
                    $('#loginDiv').modal('hide');
                    resetPassword(account,password2);
                }else{
                    alert("登录失败");
                }


            }
        };
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取登录信息失败");
    }
}
function resetPassword(account,password) {
    getLoginInfo(account);

    url="/StockHubWeb/changeUserInfo"+"?"+"account="+storage.getItem('account').value+"&"+"username="+storage.getItem('username').value+"&"+"realName="+storage.getItem('realName').value+"&"+"mail="+storage.getItem('mail').value+"&"+"birth="+storage.getItem('birth').value+"&"+"phone="+storage.getItem('phone').value+"&"+"password="+password;
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
                    alert("成功更新用户密码");
                }else{
                    alert("修改用户信息失败,该登录名已被使用");
                }
                clearUserInfo();
            }
        }
        xmlhttp.open("GET",url,true);//false才可以返回值，否则返回为空的值
        xmlhttp.send();

    }catch (e){
        alert("获取信息失败");
    }
}
var resetNowButton=document.getElementById('resetNowButton');
resetNowButton.onclick=function () {
    var account=document.getElementById('resetAccount').value;
    var password=document.getElementById('resetPassword').value;
    var password2=document.getElementById('resetPassword2').value;
    var password3=document.getElementById('resetPassword3').value;
    if(password2==password3) {
        CheckPasswordResult(account, password, password2);
        $('#resetDiv').modal('hide');
    }else{
        alert("前后密码不一致");
    }
}//立即注册

//        var modals = $('.modal')
//        for(var i = 0, len = modals.length; i < len; i++){
//            $(modals[i]).modal('hide');
//        }
//        //关闭所有model

// 打开一个，关闭其他;实现界面切换
$('#loginDiv').on('show.bs.modal', function () {
    $('#registerDiv').modal('hide');
    $('#resetDiv').modal('hide');
});
$('#registerDiv').on('show.bs.modal', function () {
    $('#loginDiv').modal('hide');
    $('#resetDiv').modal('hide');
});
$('#resetDiv').on('show.bs.modal', function () {
    $('#registerDiv').modal('hide');
    $('#loginDiv').modal('hide');
});
