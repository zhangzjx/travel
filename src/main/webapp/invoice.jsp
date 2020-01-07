<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%System.out.println(request.getSession().getAttribute("invoice")); %>

<html>
<head>
    <title>待开具发票</title>
    <style type="text/css">
        ul li{list-style-type:none;}
        #title ul{width: 1160px;height: 40px;border: coral solid 1px;}
        #content ul{width: 1160px;height: 40px;border: coral solid 1px;}
        #title li{width: 140px;height: 40px;float: left;text-align: center;line-height: 40px}
        #content li{width: 140px;height: 30px;float: left;text-align: center;line-height: 30px;font-size: 10pt}
        .paging{
            width: 100%;
            height: 40px;
            line-height:40px;
            text-align: center;
            font-family: 微软雅黑;
            font-size: 9pt;
            border-bottom: solid 1px #dddddd;
            border-left: solid 1px #dddddd;
            border-right: solid 1px #dddddd;
            float: left;
        }

    </style>
</head>
<body>
<div style="width: 1200px;border:solid 1px;margin: 100px auto">
     <div id="title">
        <ul>
            <li><span>订单编号</span></li>
            <li><span>发票类型</span></li>
            <li><span>发票内容</span></li>
            <li><span>发票抬头</span></li>
            <li><span>纳税人识别号</span></li>
            <li><span>收票人</span></li>
            <li><span>联系电话</span></li>
            <li><span>详细地址</span></li>
        </ul>
     </div>
    <div id="content">

        <c:forEach var="record" items="${invoice.list}">
            <ul>
                <li><span>${invoice.ddbh}</span></li>
                <li><span>${record.kplx}</span></li>
                <li><span>${record.spmc}</span></li>
                <li><span>${record.fptt}</span></li>
                <li><span>${record.nsrsbh}</span></li>
                <li><span>${record.sky}</span></li>
                <li><span>${record.fkfdh}</span></li>
                <li><span>${record.fkfdz}</span></li>
            </ul>
        </c:forEach>

    </div>

    <div class="paging">
        <div style="width: 80%;text-align: left;margin-left: 20px;float: left">
            共找到${invoice.totalSize}条记录，每页${invoice.pageSize}条，共${invoice.totalPage }页，当前第${invoice.currentPage }页
        </div>
        <div style="float: left;width: 15%;">
            <!-- 首页 -->
            <c:choose>
                <c:when test="${invoice.currentPage==1 }">首页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/FpServlet?action=findAll&currentPage=${invoice.currentPage != 1 }'/>">首页</a>
                </c:otherwise>
            </c:choose>
            <!-- 上一页 -->
            <c:choose>
                <c:when test="${invoice.currentPage==1 }">上一页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/FpServlet?action=findAll&currentPage=${invoice.currentPage-1 }'/>">上一页</a>
                </c:otherwise>
            </c:choose>
            <!-- 下一页 -->
            <c:choose>
                <c:when test="${invoice.currentPage==invoice.totalPage }">下一页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/FpServlet?action=findAll&currentPage=${invoice.currentPage+1 }'/>">下一页</a>
                </c:otherwise>
            </c:choose>
            <!-- 尾页 -->
            <c:choose>
                <c:when test="${invoice.currentPage == invoice.totalPage }">尾页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/FpServlet?action=findAll&currentPage=${invoice.totalPage }'/>">尾页</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <a href="index.jsp"><input type="button" value="首页" STYLE="margin: 20px 0 0 565px"></a>
    <input type="button" onclick="goExcel()" value="导出Excel" STYLE="margin: 20px 0 20px 550px">

</div>
</body>
<script type="text/javascript">
    //验证表单合法性
    function goExcel(){
        document.location = "FpServlet?action=testExcel";
    }
</script>
</html>
