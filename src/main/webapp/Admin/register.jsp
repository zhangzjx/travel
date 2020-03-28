<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <script type="text/javascript" src="js/topHeader.js"></script>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" href="css/register.css">
    <script type="application/javascript">

    </script>
</head>
<body>
<!--头部-->
<div style="width:936px; margin-top:50px;font-family:微软雅黑;font-size:20pt;">
    <a class="logo" title="青橙" href="index.jsp"></a>

</div>
<div style="margin: 0 20px;">
    <div class="main-top"><span style="margin-left: 10px">注册</span></div>

    <form id="myForm" action="../UserServlet?action=regist" method="post">

        <div style="width: 100%; margin-top: 80px;">
            <div id="register_a">
                <div class="register_a_1" >手机号:</div>
                <div class="register_a_1" >邮箱:</div>
                <div class="register_a_1" >用户名:</div>
                <div class="register_a_1" >登陆密码:</div>
                <div class="register_a_1" >确认密码:</div>
            </div>
            <div id="register_b">
                <div class="register_b_1" >
                    <input type="text" id="phone" name="phone" class="el-input"/>
                </div>
                <div class="register_b_1" >
                    <input type="text" id="email" name="email" class="el-input"/>
                </div>
                <div class="register_b_1" >
                    <input type="text" id="username" name="username" class="el-input"/>
                    <span id="msg">请输入昵称</span>
                </div>
                <div class="register_b_1" >
                    <input type="password" id="password" name="password" class="el-input"/>
                </div>
                <div class="register_b_1" >
                    <input type="password" id="passwordTwo" name="passwordTwo" class="el-input"/>
                </div>

            </div>
        </div>
        <div class="register_a_3" >
            <input name="m1" type="checkbox" value="2" checked=""><span>同意协议并注册《品优购用户协议》</span>
            <br><br><br>
            <a ><input class="go-button" type="button" value="注册" onclick="go()"/></a>
        </div>
    </form>
</div>
<!--底部-->
<div class="login-foot">
    <ul>
        <li>关于我们</li>
        <li>联系我们</li>
        <li>联系客服</li>
        <li>商家入驻</li>
        <li>营销中心</li>
        <li>手机品优购</li>
        <li>销售联盟</li>
        <li>品优购社区</li>
    </ul>
    <div >地址：北京市昌平区 邮编：100096 电话：400-400-4000 传真：010-00005100</div>
    <div >京ICP备08001421号京公网安备110108007702
    </div>
</div>
</body>

<script type="text/javascript">
    /** 验证账号唯一性*/
    $(document).ready(function(){
        $("#username").focus(function(){
            $("#username").css("background-color","#FFFFCC");
        });
        $("#username").blur(function(){
            const username = $("#username").val();
            $("#username").css("background-color","#D6D6FF");
            $.post("${pageContext.request.contextPath}/UserServlet",{
                    action:"validateName",
                    user:$(this).val()
                },
                function(data){
                    if(data=="true"){
                        $("#msg").html("恭喜你，用户名可用！");
                    }else{
                        $("#msg").html("用户名已存在");
                        $("#username").focus();
                    }
                });
        });
    });
    //验证表单合法性
    function go(){
        const phone = document.getElementById("phone").value;
        const password = document.getElementById("password").value;
        const email = document.getElementById("email").value;
        const username = document.getElementById("username").value;
        const passwordTwo = document.getElementById("passwordTwo").value;
        if(phone==""||phone==null||username==""||username==null||
            password==""||password==null||passwordTwo=="" ||passwordTwo==null
            ||email==""||email==null){
            alert("请填写完整内容");
            $(this).next('input').focus();
            return false;
        }
        $("#myForm").submit();
    }
</script>
</html>
