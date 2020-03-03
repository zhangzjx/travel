/**************顶部及底部*******************/
/********打开注册iframe页面********/
function reg(){
    layer.open({
        type: 2,
        title: '立即加入——完全免费！',
        //maxmin: true,//最大化，最小化
        //skin: 'layui-layer-lan',
        shadeClose: true, //点击遮罩关闭层    
        area : ['440px' , '90%'],
        content:'register.html'//弹框显示的url,对应的页面  
    });
};
/********打开登录iframe页面********/
function log(){
    layer.open({
        type: 2,
        title: '欢迎回来——登录！',
        //maxmin: true,
        //skin: 'layui-layer-lan',
        shadeClose: true, //点击遮罩关闭层    
        area : ['440px' , '90%'],
        content:'login.html'//弹框显示的url,对应的页面  
    });
};
/********退出账户********/
function logout(){
    $.removeCookie('userCookie',{ path: '/'}); //path为指定路径，直接删除该路径下的cookie
    // $.cookie('userCookie', null,{ path: '/'});
    window.location.replace("index.html");
};
$(".account").click(function () {
alert("账户")
});
function fff(){
    alert("账户");
};