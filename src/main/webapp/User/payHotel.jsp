<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付</title>
    <link rel="stylesheet" href="css/pay.css">
    <link rel="stylesheet" href="css/topFoot.css">

</head>
<body>

<!--页面顶部-->
<div class="header-box">
    <div class="header-top">
        <div class="logo">
            <a href="index.html"><img src="img/logo.png" class="logo-img"></a>
        </div>
        <div class="login" id="login" style="display:none;">
            <input type="button" onclick="reg()" class="login-btn layui-btn layui-btn-primary" value="注册" title="成为会员，享受专属优惠">
            <input type="button" onclick="log()" class="login-btn layui-btn layui-btn-primary" value="登录" title="登录后搜索结果更贴心">
        </div>
        <div class="login" id="logout" style="display:none;" >
            <div class="account" >
                <a href="myInf.html">
                    <img src="img/account.png" alt="账户" style="width: 25px;height: 25px;">
                    <br>
                    <span style=" font-size: 9pt; ">账户</span>
                </a>
            </div>
            <input type="button" onclick="logout()" class="login-btn layui-btn layui-btn-primary" value="退出" title="退出">

        </div>
    </div>
    <div class="header-foot">
        <div class="header-foot-item" >
            <a href="hotels.html"><img src="img/index-1.png" class="foot-img"><span>住宿</span></a>
        </div>
        <div class="header-foot-item">
            <a href="attractions.html"><img src="img/index-4.png" class="foot-img"><span>景点</span></a>
        </div>
    </div>
</div>
<div style="width: 1200px;margin:0 auto;">
    <!--主内容-->
    <div class="pay">
        <div class="checkout-tit">
            <h4 class="tit-txt"><span class="success-icon"></span><span>订单提交成功，请您及时付款，以便尽快为您发货~~</span></h4>
            <div class="paymark">
                <span class="fl">请您在提交订单<em class="orange time">4小时</em>之内完成支付，超时订单会自动取消。订单号：<em>${oid}</em></span>
                <span class="fr"><em>应付金额：</em><em  class="orange money">￥<span id="payPrice"></span></em></span>
            </div>
        </div>
        <div class="check-info">
            <h4>重要说明：</h4>
            <ol>
                <li>品优购商城支付平台目前支持<span class="zfb">支付宝</span>支付方式。</li>
                <li>其它支付渠道正在调试中，敬请期待。</li>
                <li>为了保证您的购物支付流程顺利完成，请保存以下支付宝信息。</li>
            </ol>
        </div>

        <div class="checkout-steps">
            <!--收件人信息-->
            <h5>支付平台</h5>
            <div class="step-cont">
                <ul class="payType">
                    <li><img src="img/pay1.jpg"></li>
                    <li><img src="img/pay2.jpg"></li>
                    <li><img src="img/pay3.jpg"></li>
                    <li><img src="img/pay4.jpg"></li>
                </ul>
            </div>

            <div class="submit">
                <a class="sui-btn" href="" target="_blank" onclick="payOrder()">立即支付</a>
            </div>

        </div>
    </div>
</div>
<!--底部内容-->
<!-- footer -->
<div class="bottom-foot" >
    <h2>热门目的地</h2>
    SeeWorld 是全球领先的旅游网站，旨在帮助旅行者计划和预定完美旅行。
    浏览超过 2 亿条点评和评论以及酒店、餐厅和景点等照片。
    您还可以查找低价机票、免费旅行指南、全球度假租屋名录以及热门论坛，它们提供有关每个目的地的建议等信息。
    有如此之多的用户在每次旅行之前都会首先访问 Booking 网站也不足为奇。
    <h2>全球领先的旅行信息平台。</h2>
    SeeWorld 每月帮助近五亿名旅行者尽享每次旅行。
    使用 SeeWorld 网站和 App 浏览数亿条点评和建议，内容涵盖住宿、餐厅、体验、航空公司和邮轮。
    无论是在计划还是在路上，旅行者都可以通过SeeWorld  比较酒店、航班和邮轮的超低价格、预订热门游览和景点并在绝佳餐厅订位。
    终极旅行伴侣 SeeWorld 服务范围涵盖 49 个市场，支持 28 种语言。
    <div class="bottom">
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">热门地区</div><br>
            <div class="bottom-main-list"><a href="#">北京市</a></div>
            <div class="bottom-main-list"><a href="#">香港特别行政区</a></div>
            <div class="bottom-main-list"><a href="#">重庆市</a></div>
            <div class="bottom-main-list"><a href="#">上海市</a></div>
            <div class="bottom-main-list"><a href="#">广东省</a></div>
        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">中部地区</div><br>
            <div class="bottom-main-list"><a href="#">山西省</a></div>
            <div class="bottom-main-list"><a href="#">河南省</a></div>
            <div class="bottom-main-list"><a href="#">河北省</a></div>
            <div class="bottom-main-list"><a href="#">湖北省</a></div>
            <div class="bottom-main-list"><a href="#">湖南省</a></div>
        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">北方地区</div><br>
            <div class="bottom-main-list"><a href="#">黑龙江省</a></div>
            <div class="bottom-main-list"><a href="#">吉林省</a></div>
            <div class="bottom-main-list"><a href="#">辽宁省</a></div>
            <div class="bottom-main-list"><a href="#">河北省</a></div>
            <div class="bottom-main-list"><a href="#">山东省</a></div>

        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">西南地区</div><br>
            <div class="bottom-main-list"><a href="#">四川省</a></div>
            <div class="bottom-main-list"><a href="#">贵州省</a></div>
            <div class="bottom-main-list"><a href="#">云南省</a></div>
            <div class="bottom-main-list"><a href="#">重庆市</a></div>
            <div class="bottom-main-list"><a href="#">西藏自治区</a></div>
        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;"><a href="#">西北地区</a></div><br>
            <div class="bottom-main-list"><a href="#">陕西省</a></div>
            <div class="bottom-main-list"><a href="#">甘肃省</a></div>
            <div class="bottom-main-list"><a href="#">青海省</a></div>
            <div class="bottom-main-list"><a href="#">宁夏回族自治区</a></div>
            <div class="bottom-main-list"><a href="#">新疆维吾尔自治区</a></div>
        </div>
    </div>
    <div class="foot">
        <ul>
            <li><a href="#"> 关于我们</a></li>
            <li><a href="#"> 新闻动态</a></li>
            <li><a href="#"> 工作机会</a></li>
            <li><a href="#"> 商务合作</a></li>
            <li><a href="#"> 会员中心</a></li>
            <li><a href="#"> 常见问题</a></li>
            <li><a href="#"> 意见反馈</a></li>
            <li><a href="#"> 联系我们</a></li>
            <li><a href="#"> 营业执照</a></li>
        </ul>
        <div >
            © 2020 SeeWorld 版权所有。<a href="#">使用条款</a> | <a href="#">隐私政策</a> | <a href="#">网站工作原理</a>
            * SeeWorld不是旅行社，也不是旅游预订服务代理商。我们提供免费、客观、公正的旅游资讯服务。
        </div>
        <div > 京ICP证080678号京ICP备09040564号京公网安备11010502036828号
        </div>
    </div>
</div>
<!-- end-footer -->
</body>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="application/javascript" src="js/topFoot.js"></script>
<link rel="stylesheet" type="text/css" href="../res/layui/css/layui.css">
<script src="js/jquery.cookie.js" charset="utf-8"></script>
<script src="js/validate.js"></script>
<script type="application/javascript">
    $(document).ready(function() {
        let payPrice = $.cookie("totalPrice");
        $("#payPrice").html(payPrice);
    })
</script>
<script type="application/javascript">
    function payOrder(){
        document.location = "../UserServlet?action=payOrderHotel&status=2&oid=${oid }";
    }
</script>
</html>
