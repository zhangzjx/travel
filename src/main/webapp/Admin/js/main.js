$(document).ready(function(){
    if($.cookie("adminCookie") != null&&$.cookie("adminCookie")!= ""){
        console.log("adminCookie存在");
        const userCookie = JSON.parse($.cookie("adminCookie"));    //读取cookie
        const admin_id = userCookie.admin_id;
        $("#admin_id").val(admin_id);

    } else {
        console.log("adminCookie不存在");
        window.parent.location.href="login.html";
    }
});