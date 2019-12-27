/**************顶部及底部*******************/
function reg(){
    layer.open({
        type: 2,
        title: '立即加入——完全免费！',
        //maxmin: true,//最大化，最小化
        //skin: 'layui-layer-lan',
        shadeClose: true, //点击遮罩关闭层    
        area : ['440px' , '90%'],
        content:'register.jsp'//弹框显示的url,对应的页面  
    });
}
function log(){
    layer.open({
        type: 2,
        title: '欢迎回来——登录！',
        //maxmin: true,
        skin: 'layui-layer-lan',
        shadeClose: true, //点击遮罩关闭层    
        area : ['400px' , '280px'],
        content:'login.html'//弹框显示的url,对应的页面  
    });
}