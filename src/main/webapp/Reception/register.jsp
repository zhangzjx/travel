
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员注册</title>
    <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
    <script src="js/jquery-3.4.1.min.js"></script>

    <style type="text/css">

        form{height: 380px;border: solid 1px #d2d2d2;text-align:center;}
        .register-left{width: 80%;height: 300px;margin-left:10%;text-align: left;}
        ul li {list-style: none;}
        .register-left li{width: 100%;height: 40px;line-height:40px;}
        .register-go{width: 100%;height: 10px;margin-top: 30px;line-height:30px;}
        .el-input{width: 80%;height: 35px;margin-top: 5px;border:#000 solid 1px;border-radius: 3px;}

    </style>
</head>
<body>

<div style="width:100%;">

    <form id="myForm" action="../UserServlet?action=register" method="post">
        <div style="width: 100%; margin-top: 30px; height:350px;">
            <div class="register-left">
                <ul>
                    <li>手机号:</li>
                    <li><input type="text" id="phone" name="phone" placeholder="仅支持大陆手机号"  class="el-input"/></li>
                    <li>用户名:</li>
                    <li><input type="text" id="username" name="username" placeholder="输入昵称"  class="el-input"/>
                        <span id="msg">输入昵称</span>
                    </li>
                    <li>邮箱:</li>
                    <li><input type="text" id="email" name="email" placeholder="输入邮箱" class="el-input"/></li>
                    <li>登录密码:</li>
                    <li><input type="password" id="password" name="password" placeholder="不少于六位"  class="el-input"/></li>
                </ul>
            </div>

        </div>
        <div class="register-go" >
            <input name="m1" type="checkbox" value="2" checked="">
            <span>若继续操作，即表示您同意我们的<strong><a href="teams.html">使用条款</a></strong>并确认已经阅读我们的隐私政策。</span>
            <br><br><br>
            <a ><input class="layui-btn" type="button" value="注册" onclick="go()"/></a>
        </div>
    </form>
</div>
<!--底部-->

</body>
<script type="text/javascript">
    const phone = document.getElementById("phone").value;
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const email = document.getElementById("email").value;
    const passwordTwo = document.getElementById("passwordTwo").value;
    /** 验证账号唯一性**/
    $(document).ready(function(){
        $("#username").blur(function(){
            //使用$.ajax()发送异步请求
            $.ajax({
                url:"${pageContext.request.contextPath}/UserServlet" , // 请求路径
                type:"POST" , //请求方式
                //data: "username=jack&age=23",//请求参数
                data:{action:"validateName", user:$(this).val()},
                success:function (data) {
                    if(data=="true"){
                        $("#msg").html("可使用！");
                    }else{
                        $("#msg").html("已存在");
                        $("#username").focus();
                    }
                    //alert(data);
                },//响应成功后的回调函数
                error:function () {
                    alert("出错啦...")
                },//表示如果请求响应出现错误，会执行的回调函数

                dataType:"text"//设置接受到的响应数据的格式
            });
        });
    })
    //验证表单合法性
    function go(){

        if(phone==""||phone==null||username==""||username==null||
            password==""||password==null||passwordTwo=="" ||passwordTwo==null
            ||email==""||email==null){
            alert("请填写完整内容");
            $(this).next('input').focus();
            return false;
        }
        /*****用ajax提交返回注册成功信息**************/
        $("#myForm").submit();
    }
</script>
</html>
