
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>会员注册</title>
    <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/register.css">
    <script src="js/jquery-3.4.1.min.js"></script>
    <style type="text/css">


    </style>
</head>
<body>

<div style="margin: 0 20px;">
    <div class="main-top"><span style="margin-left: 10px">注册</span></div>
    <form id="myForm" action="../UserServlet?action=register" method="post">
        <div style="width: 100%; margin-top: 80px; height:300px;">
            <div class="register-left">
                <ul>
                    <li>手机号:</li>
                    <li>用户名:</li>
                    <li>邮箱:</li>
                    <li>登录密码:</li>
                    <li>确认密码:</li>
                </ul>
            </div>
            <div class="register-right">
                <ul>
                    <li><input type="text" id="phone" name="phone" class="el-input"/></li>
                    <li><input type="text" id="username" name="username" class="el-input"/>
                        <span id="msg">请输入昵称</span>
                    </li>
                    <li><input type="text" id="email" name="email" class="el-input"/></li>
                    <li><input type="password" id="password" name="password" class="el-input"/></li>
                    <li><input type="password" id="passwordTwo" name="passwordTwo" class="el-input"/></li>
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
                        $("#msg").html("恭喜你，用户名可用！");
                    }else{
                        $("#msg").html("用户名已存在");
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
