<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>

<head>
    <title>个人信息</title>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/topFoot.css">
    <link rel="stylesheet" type="text/css" href="css/personal.css">

    <style type="text/css">
        body{background-color: #EEEEEE}
        .tab-pane{display:none;}
        .active{display:block;}
        /*********单个景点详情***********/
        /***顶部***/
        .top{width: 1160px;height:140px;margin: 20px auto;padding:20px;background-color: #fff;}
        .top-header{width: 1160px;height:100px;margin: 0 auto;}
        .top-header .img{ width:90px;height:90px;border-radius:50%;float: left; }
        .top-item{width: 100px;height: 100px;margin-left: 10px;text-align: left;float: left;}
        .top-item a{color: #000000;}
        .top-item a:hover{text-decoration:none;color: #00ff00;}
        .top-item a:visited{text-decoration:none;coler:#ff5809;}
        .top-item-right{width: 120px;height: 30px;margin:0 0 0 700px;cursor: pointer;border: solid 1px;border-radius:3px;float: left;}
        .top-item-right .inf{width: 80px;height: 30px;text-align: center;line-height:30px;border-right: solid 1px;float: left;}
        .top-item-right .inf:hover{background-color: #EEEEEE}
        .top-item-right .img{width: 30px;height: 30px;float: left;}
        .top-item-right .img:hover{background-color: #EEEEEE}
        .top-bottom{width: 1160px;height:30px;margin: 20px auto 0;}
        .top-bottom-item{width: 60px;height: 30px;float: left;text-align: center;line-height: 30px;cursor: pointer}
        /**设置对象在被用户激活（在鼠标点击与释放之间发生的事件）时的样式表属性*/
        .top-item a:active{}
        /*********主要内容*******/
        .content{width: 1200px;min-height: 300px;;margin: 0 auto}
        .content-main{width: 600px;min-height: 300px;margin: 30px  0;border: darkblue solid 1px;background-color: #fff;}


    </style>
</head>
<body>
<!-- header -->
<div class="header-box">
    <div class="header-top">
        <h1 class="logo">
            <a href="index.html"><img src="img/logo.png" class="logo-img"></a>
        </h1>
        <div class="login"  style="visibility: visible">
            <input type="button" onclick="reg()" class="login-btn layui-btn layui-btn-primary" value="注册" title="成为会员，享受专属优惠">
            <input type="button" onclick="log()" class="login-btn layui-btn layui-btn-primary" value="登录" title="登录后搜索结果更贴心">
        </div>

    </div>
    <div class="header-foot">
        <div class="header-foot-item" >
            <a href=""><img src="img/index-1.png" class="foot-img"><span>住宿</span></a>
        </div>
        <div class="header-foot-item">
            <a href=""><img src="img/index-2.png" class="foot-img"><span>机票</span></a>
        </div>
        <div class="header-foot-item">
            <a href=""><img src="img/index-3.png" class="foot-img"><span>租车</span></a>
        </div>
        <div class="header-foot-item">
            <a href=""><img src="img/index-4.png" class="foot-img"><span>观光和活动</span></a>
        </div>
    </div>

</div>
<!-- end-header -->
<!-- top -->
<div class="top">
    <!--右侧顶部-->
    <div class="top-header">
        <img src="img/l-2.jpg" alt="头像" class="img">
        <div class="top-item">
            <br>
            <a href=""><strong style="font-size: 15pt;">粉丝</strong></a><br>
            <span>4</span>
        </div>
        <div class="top-item">
            <br>
            <a href=""><strong style="font-size: 15pt;">已关注</strong></a><br>
            <span>6</span>
        </div>
        <div class="top-item-right">
            <div class="inf">编辑简介</div>
            <img src="img/l-4.jpg" class="img">
        </div>
    </div>
    <div class="top-bottom">
        <div id="change-one" class="top-bottom-item">订单</div>
        <div id="change-two" class="top-bottom-item">点评</div>
    </div>
</div>

<!-- end-top -->
<!-- content -->
<div class="content">
    <div id="one" class="content-main tab-pane active">
        写您的第一条点评！
        您的意见很重要！开始在 TripAdvisor 点评酒店、景点玩乐和更多内容。写点评
    </div>
    <div id="two" class="content-main tab-pane">
        点评精选及图片
    </div>
</div>

</body>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript"  src="../res/layui/layui.all.js"></script>
<script type="text/javascript" src="js/layer.js"></script>
<script type="text/javascript" src="js/topFoot.js"></script>

<script type="text/javascript">

    $("#change-one").click(function () {
        document.getElementById('two').classList.remove("active");
        document.getElementById('two').classList.add("tab-pane");
        document.getElementById('one').classList.remove("tab-pane");
        document.getElementById('one').classList.add("active");
    });
    $("#change-two").click(function () {
        document.getElementById('one').classList.remove("active");
        document.getElementById('one').classList.add("tab-pane");
        document.getElementById('two').classList.remove("tab-pane");
        document.getElementById('two').classList.add("active");
        //document.getElementById('one').className = "tab-pane";
        //document.getElementById('two').className = "active";
    });

</script>
</html>
