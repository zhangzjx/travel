<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<head>
    <title>2</title>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
    <link rel="stylesheet" type="text/css" href="css/topFoot.css">
    <link rel="stylesheet" type="text/css" href="css/restaurants.css">

</head>
<body>
<!-- header -->
<div class="header-box">
    <div class="header-top">
        <!--
        <ul class="app-header">
          <li class="app-header-menuicon">
             <i class="layui-icon layui-icon-more-vertical"></i>
          </li>
        </ul>
        -->
        <h1 class="logo">
            <a href=""><img src="img/logo.png" class="logo-img"></a>
        </h1>
        <div class="login"  style="visibility: visible">
            <input type="button" class="login-btn layui-btn layui-btn-primary" value="注册" title="成为会员，享受专属优惠">
            <input type="button" class="login-btn layui-btn layui-btn-primary" value="登录" title="登录后搜索结果更贴心">
        </div>
        <!--
        <ul class="layui-nav header-down-nav">
          <li class="layui-nav-item"><a href="index.html" class="active">首页</a></li>
          <li class="layui-nav-item"><a href="case.html">案例</a></li>
          <li class="layui-nav-item"><a href="service.html">服务</a></li>
          <li class="layui-nav-item"><a href="about.html">关于</a></li>
        </ul>-->
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
    <div class="top-content">
        <div class="title">
            <a href="#">亚洲</a><span>&nbsp;></span>
            <a href="#">中国 </a><span>&nbsp;></span>
            <a href="#">北京市</a><span>&nbsp;></span>
            <a href="#">北京美食</a><span>&nbsp;></span>
            <a href="#">京兆尹 King’s Joy</a>
        </div>
        <!--标题-->
        <div class="restaurant-title">
            <h1><b>京兆尹 King’s Joy</b></h1>
            <div class="title-inf">
                <ul>
                    <li>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        23,638 条点评
                    </li>
                    <li><div style="width: 50px"><div class="line"></div></div></li>
                    <li>排名第 13 的 北京市美食（共 12,322 个）</li>
                    <li><div style="width: 50px"><div class="line"></div></div></li>
                    <li>中餐, 亚洲料理, 适合素食主义者</li>
                </ul>
            </div>
            <div class="title-inf">
                <ul>
                    <li>中国北京市东城区五道营胡同2号(雍和宫桥往南150米路西) 100027</li>
                    <li><div style="width: 50px"><div class="line"></div></div></li>
                    <li>+86 10 8404 9191</li>
                    <li><div style="width: 50px"><div class="line"></div></div></li>
                    <li>正在营业:上午11:00 - 下午10:00</li>
                </ul>
            </div>
        </div>
    </div>
</div>

<!-- end-top -->
<!--主要内容-->
<div class="content">
    <div class="restaurants-main">
        <!--图片-->
        <div class="main-top">
            <img src="img/res-LeLac.jpg" class="img">
            <img src="img/res-LeLac.jpg" class="img" style="margin-left:3px;">
            <img src="img/res-LeLac.jpg" class="s-img">
            <img src="img/res-LeLac.jpg" class="s-img">
        </div>
        <!--评分，详情及联系方式-->
        <div style="height:400px;margin-top: 30px;">
            <div class="review-item">
                <h3><b>评分和点评</b></h3>
                <span style="float: left;margin-top: 13px;">5.0</span><div class="f"></div>
                <p>1,638 条点评</p>
                <p>排名第 4 的 北京市中餐（共 2,978 个）</p>
                <p>排名第 12 的 北京市餐厅（共 6,448 个）</p>
                <hr style="margin: 20px 0">
                <p><b>评分</b></p>
                <div class="g"><span>食物</span><div class="f"></div></div>
                <div class="g"><span>服务</span><div class="f"></div></div>
                <div class="g"><span>实惠</span><div class="f"></div></div>
                <div class="g"><span>氛围</span><div class="f"></div></div>

            </div>
            <div class="review-item" style="margin: 0 30px">
                <div class="m">
                    <ul>
                        <li><h3><b>详情</b></h3></li>
                        <li><p><b>价位</b></p></li>
                        <li>￥98 - ￥497</li>
                        <li><p><b>美食</b></p></li>
                        <li> 中餐, 亚洲料理</li>
                        <li><p><b>特殊饮食</b></p></li>
                        <li>适合素食主义者, 适合严格素食者, 提供无麸质美食</li>
                        <li> <p style="cursor: pointer" onclick="goLook()"><b>查看所有详情</b></p></li>
                        <li>餐时, 功能</li>

                    </ul>
                </div>
            </div>
            <div class="review-item">
                <h3><b>位置和联系方式</b></h3>
                <img src="img/view-1.png" class="left-img">
                <div style="margin-top: 10px">100027 中国 北京市 东城区五道营胡同2号(雍和宫桥往南150米路西)</div>
                <div style="margin-top: 10px">网址</div>
                <div style="margin-top: 10px">+86 10 8404 9191</div>
            </div>
        </div>
        <!--点评-->
        <div class="evaluation">
            <div class="evaluation-top">
                <div class="ev-t-left"><h2><b>点评</b></h2></div>
                <div class="ev-t-right"><input type="button" class="layui-btn" value="撰写点评" title="撰写点评"></div>
                <hr>
            </div>
            <div class="evaluation-center">
                <div class="ev-c-item" style="width: 300px;">
                     <h4><b>旅行者评分</b></h4>
                    <ul>
                        <li> <input type="checkbox"></li>
                        <li> <input type="checkbox"></li>
                        <li> <input type="checkbox"></li>
                        <li> <input type="checkbox"></li>
                        <li> <input type="checkbox"></li>
                    </ul>
                    <div class="chart">
                        <div id="container" style="height: 200px;width: 260px;"></div>
                    </div>

                </div>
                <div class="ev-c-item">
                    <h4><b>旅行者类型</b></h4>
                    <ul>
                        <li><input type="checkbox">家庭</li>
                        <li><input type="checkbox">夫妻</li>
                        <li><input type="checkbox">独自旅游</li>
                        <li><input type="checkbox">商务</li>
                        <li><input type="checkbox">好友</li>
                    </ul>
                </div>
                <div class="ev-c-item">
                  <h4><b>时节</b></h4>
                    <ul>
                        <li><input type="checkbox">3 月到 5 月</li>
                        <li><input type="checkbox">6 月到 8 月</li>
                        <li><input type="checkbox">9 月到 11 月</li>
                        <li><input type="checkbox">12 月到 2 月</li>
                    </ul>
                </div>
                <div class="ev-c-item">
                   <h4><b>语言</b></h4>
                    <ul>
                        <li><input type="checkbox">所有语言</li>
                        <li><input type="checkbox">英语 (35)</li>
                        <li><input type="checkbox">中文（繁体） (4)</li>
                    </ul>
                </div>
                <hr>
                <div class="ev-c-list">
                    <div class="left">

                    </div>
                    <div class="right">

                    </div>
                    Trek25482625476
                    北京市
                    1
                    昨天点评 通过移动设备发表
                    约会的好出去
                    约会的好去处，美景，烛光，红酒，大餐... 这次提前几天预定了座位，景观非常的好，一览长安街的夜景，感觉像在空中餐厅，星光璀璨，美不胜收。 餐厅环境讲究，服务员是一位非常帅气的外国小哥哥。领我们到预留号的位置。这家店的菜品非常的棒特别是烤澳洲菲力牛排，菲力牛排讲的是火候外焦里嫩，肉质鲜嫩，保持原汁原味，鲜嫩无比。 总的来说，环境好，口味地道，服务周到。

                    用餐日期：2019年12月
                </div>


            </div>
        </div>
    </div>

</div>


</body>
<script type="text/javascript" src="js/echarts.js"></script>
<script type="text/javascript"  src="../res/layui/layui.all.js"></script>
<script type="text/javascript" src="js/layer.js"></script>
<script type="text/javascript" src="js/topFoot.js"></script>

<script type="text/javascript">
    function goLook() {
        layer.open({
            type: 1
            ,title: "详情" //不显示标题栏   title : false/标题
            ,area: ['700px', '500px']
            ,shade: 0
            ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
            ,resize: false
            ,content: '<div style="padding: 40px; line-height: 30px; "><b>价位</b><br>' +
                '￥98 - ￥497\<br>' +
                '<b>美食</b><br>' +
                '中餐, 亚洲料理<br>' +
                '<b>特殊饮食</b><br>' +
                '适合素食主义者, 适合严格素食者, 提供无麸质美食<br>' +
                '<b>餐时</b><br>' +
                '午餐, 晚餐, 饮料<br>' +
                '<b>功能</b><br>' +
                '户外用餐, 有儿童椅的餐厅, 保护区, 座位, 可供停车, 认证停车, 代客停车, 残障通道, 提供酒精饮料, 标准酒吧, 果酒和啤酒, 免费Wifi, 接受信用卡, 餐桌服务, 电子付款, 现场音乐</div>'

        });
    };
    //评分
    layui.use('rate', function(){
        const rate = layui.rate;
        //渲染
        const ins1 = rate.render({
            elem: '.f', //绑定元素
            value: 5,
            half: true,
            readonly: true,
        });
    });

    //图表
    const dom = document.getElementById("container");
    const myChart = echarts.init(dom);
    const app = {};
    option = null;
    app.title = '坐标轴刻度与标签对齐';

    option = {
        color: ['#3398DB'],
        tooltip : {
            trigger: 'axis',
            axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        yAxis : [
            {
                type : 'category',
                data : ['很差', '较差', '一般', '较好', '很棒', ],
                axisTick: {
                    show: false,
                    alignWithLabel: true
                },
                axisLine: {//取消显示坐标轴
                    show: false
                }
            }
        ],
        xAxis : [
            {
                show: false,//取消显示坐标轴,坐标轴刻度,坐标值(如果是y轴,默认的网格线也会取消显示)
                type : 'value'
            }
        ],
        series : [
            {
                name:'评价',
                type:'bar',
                barWidth: '60%',
                data:[10, 52, 200, 334, 390,]
            }
        ]
    };
    ;
    if (option && typeof option === "object") {
        myChart.setOption(option, true);
    }
</script>
</html>
