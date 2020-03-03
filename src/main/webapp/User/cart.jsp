<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/11/16
  Time: 17:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>购物车</title>
    <!--转发页面时css文件的链接
    <link rel="stylesheet" href="./User/css/cart.css">
    <script type="text/javascript" src="./User/js/jquery-3.4.1.min.js"></script>
    -->
    <link rel="stylesheet" href="css/cart.css">
    <link rel="stylesheet" href="css/topFoot.css">
    <script type="text/javascript" src="js/topHeader.js"></script>
    <script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
    <script src="js/carts.js"></script>
    <style type="text/css">
        .banBtn{
            cursor:not-allowed;
        }
    </style>

</head>
<body>
<input type="hidden" id="name" value="${name }">
<input type="hidden" id="uid" name="uid" value=${user.uid}>
<!-- 头部栏位 -->
<!--页面顶部-->

<!--所有商品-->
<div class="main">
    <h4>全部商品</h4>
    <div class="cart-main">
        <div class="cart-th">
            <div class="cart-u-1" style="width: 6%;">
                <!--所有商品全选-->
                <input type="checkbox" id="all" class="whole_check">
                <label for="all"></label>全选
            </div>
            <div class="cart-u-1" style="width: 25%;">商品信息</div>
            <div class="cart-u-1" style="width: 15.8%;">商品参数</div>
            <div class="cart-u-1">单价（元）</div>
            <div class="cart-u-1">数量</div>
            <div class="cart-u-1">金额</div>
            <div class="cart-u-1">操作</div>
        </div>

        <form id="myForm" action="order.jsp" method="post" name="creator">
            <section class="cart-list">
                <div class="cartBox">
                    <!--顶部信息-->
                    <div class="shop_info">
                        <div class="all_check">
                            <!--店铺全选-->
                            <input type="checkbox" id="shop_a" class="shopChoice">
                            <label for="shop_a" class="shop"></label>
                        </div>
                        <!--
                        <div class="shop_name">
                            店铺：<a href="javascript:;">搜猎人艺术生活</a>
                        </div>
                        -->
                    </div>
                    <!--订单主要信息-->
                    <div class="order_content">
                        <%--@elvariable id="myCart" type="javax.swing.plaf.basic.ComboPopup"--%>
                        <c:forEach var="record" items="${myCart.list}">
                            <ul class="order_lists" >
                                <li class="cart-u-2" style="width: 6%;">
                                    <input type="checkbox" id="checkbox_2" class="son_check" name="course" value=${record.cid}>
                                    <label for="checkbox_2"></label>
                                </li>
                                <li class="cart-u-2" style="width: 25%;">
                                    <div class="list_img"><a href="javascript:;"><img src="./images/1.png" alt=""></a></div>
                                    <div class="list_text"><a href="javascript:;">${record.goods_name}</a></div>
                                </li>
                                <li class="item-msg" style="width: 15%;">
                                    <!--<p>规格：默认</p>
                                    <p>尺寸：16*16*3(cm)</p>
                                    -->
                                        ${record.goods_name}
                                </li>
                                <li class="cart-u-2" style="line-height: 70px">
                                    <p class="price">20</p>
                                </li>
                                <li class="cart-u-2">
                                    <div class="amount_box" style="margin-top: 30px;">
                                        <a href="javascript:;" class="reduce increment">-</a>
                                        <input type="text" value="1" class="sum">
                                        <a href="javascript:;" class="plus increment">+</a>
                                    </div>
                                </li>
                                <li class="cart-u-2" style="line-height: 70px">
                                    <p class="sum_price">￥${record.price}</p>
                                </li>
                                <li class="cart-u-2" style="line-height: 70px">
                                    <p class="del"><a href="javascript:;" class="delBtn">移除商品</a></p>
                                </li>
                            </ul>
                        </c:forEach>
                    </div>
                </div>

            </section>
        </form>
        <!---
        <section class="model_bg"></section>
        <section class="my_model">
            <p class="title">删除宝贝<span class="closeModel">X</span></p>
            <p>您确认要删除该宝贝吗？</p>
            <div class="opBtn"><a href="javascript:;" class="dialog-sure">确定</a><a href="javascript:;" class="dialog-close">关闭</a></div>
        </section>
        -->
        <!--底部分页功能-->
        <div class="a-3">
            <div style="width: 70%;text-align: left;margin-left: 20px;float: left">
                共找到${myCart.totalSize}条记录，每页${myCart.pageSize}条，共${myCart.totalPage }页，当前第${myCart.currentPage }页
            </div>
            <div style="float: left;width: 15%;">
                <!-- 首页 -->
                <c:choose>
                    <c:when test="${myCart.currentPage==1 }">首页
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/UserServlet??action=myCart&currentPage=${myCart.currentPage != 1 }'/>">首页</a>
                    </c:otherwise>
                </c:choose>
                <!-- 上一页 -->
                <c:choose>
                    <c:when test="${myCart.currentPage==1 }">上一页
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/UserServlet??action=myCart&currentPage=${myCart.currentPage-1 }'/>">上一页</a>
                    </c:otherwise>
                </c:choose>
                <!-- 下一页 -->
                <c:choose>
                    <c:when test="${myCart.currentPage==myCart.totalPage }">下一页
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/UserServlet??action=myCart&currentPage=${myCart.currentPage+1 }'/>">下一页</a>
                    </c:otherwise>
                </c:choose>
                <!-- 尾页 -->
                <c:choose>
                    <c:when test="${myCart.currentPage == myCart.totalPage }">尾页
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='/UserServlet??action=myCart&currentPage=${myCart.totalPage }'/>">尾页</a>
                    </c:otherwise>
                </c:choose>
            </div>
            <div style="float: left">
                跳至<input id="currentPage" name="currentPage" style="height: 20px;width: 30px;" >页
                <a href="<c:url value='/UserServlet??action=myCart'/>">确定</a>
            </div>
        </div>

        <!--总金额结算-->
        <div class="bar-wrapper">
            <div class="bar-right">
                <div class="piece">已选商品<strong class="piece_num">0</strong>件</div>
                <div class="totalMoney">共计:￥<strong class="total_text">0.00</strong></div>
                <a><input class="calBtn" id="calBtn" type="button" value="结算" onclick="go()"/></a>
                <!--<div class="calBtn"><a href="javascript:return false;" style="opacity: 0.2">结算</a></div>-->
            </div>
        </div>
    </div>>
</div>
<!--底部内容-->
<div class="bottom-foot" >
    <div class="bottom">
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">购物指南</div><br>
            <div class="bottom-main-list">购物流程</div>
            <div class="bottom-main-list">会员介绍</div>
            <div class="bottom-main-list">生活旅行/团购</div>
            <div class="bottom-main-list">常见问题</div>
            <div class="bottom-main-list">购物指南</div>
        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">配送方式</div><br>
            <div class="bottom-main-list">上门自提</div>
            <div class="bottom-main-list">211限时达</div>
            <div class="bottom-main-list">配送服务查询</div>
            <div class="bottom-main-list">配送费收取标准</div>
            <div class="bottom-main-list">海外配送</div>
        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">支付方式</div><br>
            <div class="bottom-main-list">货到付款</div>
            <div class="bottom-main-list">在线支付</div>
            <div class="bottom-main-list">分期付款</div>
            <div class="bottom-main-list">邮局汇款</div>
            <div class="bottom-main-list">公司转账</div>

        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">售后服务</div><br>
            <div class="bottom-main-list">售后政策</div>
            <div class="bottom-main-list">价格保护</div>
            <div class="bottom-main-list">退款说明</div>
            <div class="bottom-main-list">返修/退换货</div>
            <div class="bottom-main-list">取消订单</div>
        </div>
        <div class="bottom-main">
            <div class="bottom-main-list" style="font-size: 13pt;font-weight: bold;">特色服务</div><br>
            <div class="bottom-main-list">夺宝岛</div>
            <div class="bottom-main-list">DIY装机</div>
            <div class="bottom-main-list">延保服务</div>
            <div class="bottom-main-list">品优购E卡</div>
            <div class="bottom-main-list">品优购通信</div>
        </div>
    </div>
    <div class="foot">
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
        <div >地址：北京市昌平区 邮编：100096 电话：400-400-4000 传真：010-82935100</div>
        <div >京ICP备08001421号京公网安备110108007702
        </div>
    </div>
</div>
</body>
<script type="application/javascript">

    $(document).ready(function(){
        // 页面加载后任何需要执行的js特效
        let name = $("#name").val();
        if(name != null&&name != ""){
            $("#logout").show()
        }else {
            $("#lg").show();
        }

        //结算按钮可用性
        let total_count = $(".piece_num").val();
        let total_money = $(".total_text").val();

        if(total_money!=0 && total_count!=0){
            $('#calBtn').removeAttr("disabled");// 移除disabled属性
            $('#calBtn').removeClass("banBtn");
        }else {
            $('#calBtn').attr('disabled',"true");//添加disabled属性
            $('#calBtn').addClass("banBtn");
        }
    })
    //验证表单合法性
    function go(){
        //获取所有名字为ck的编号组件
        const ck = document.getElementsByName("course");
        const cm = document.getElementsByName("course").values();
        //ids字符串
        let s = "";
        //循环ck数组
        for(let i = 0 ; i < ck.length ; i ++)
        {   //如果被选择的选中
            if(ck[i].checked)
            {//编号字符串累加
                s+=ck[i].value+",";
            }
        }
        //确认选项
        let ok = window.confirm("确定要提交["+s+"] 记录吗？");
        //如果确认选择
        if(ok) {
            //把ids传入后台调用servlet
            let price = $(".total_text").text();
            document.location = "../UserServlet?action=addOrder&uid=${user.uid}&price="+price+"&ids="+s;
        }
        //document.location = "ProductServlet?action=delGoMor&ids="+s;
        /********
         $.post("${pageContext.request.contextPath}/UserServlet",{
           action:"addOrder",
            uid:2,
            cid:12,
            },)
         *******/
    }
</script>
<!--只能加减，没有计算
<script type="application/javascript">

    $(document).ready(function() {
        //加的效果
        $(".plus").click(function () {
            const n = $(this).prev().val();
            const itxt = parseInt(n) + 1;
            if (itxt == 0) {
                return;
            }
            $(this).prev().val(itxt);
        });
        //减的效果
        $(".mins").click(function () {
            const n = $(this).next().val();
            const itxt = parseInt(n) - 1;
            if (itxt == 0) {
                return
            }
            $(this).next().val(itxt);
        });
    });
</script>
-->
</html>
