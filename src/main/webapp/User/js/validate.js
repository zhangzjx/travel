/**
 * Created by Administrator on 2020/3/4.
 */
$(document).ready(function(){
    // 页面加载后任何需要执行的js特效
    if($.cookie("userCookie") != null&&$.cookie("userCookie")!= ""){
        console.log("userCookie存在");
        $("#logout").show();
        const userCookie = JSON.parse($.cookie("userCookie"));    //读取cookie
        $("#user_id").val(userCookie.uid);
    } else {
        console.log("userCookie不存在");
        $("#login").show()
        window.parent.location.href="index.html";
        alert("请登录后查看");
    }
});