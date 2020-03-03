<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% //System.out.println("jsp结果为"+request.getSession().getAttribute("myList"));%>
<html>
<head>
    <title>list</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <style type="text/css">
        .el-select{width: 100px;height: 35px;font-size: 10pt;margin-top: 10px;
            float: left;}
        .operating{width: 94.5%;height: 30px;margin: 10px 0;padding: 10px 30px;background-color:#f2f2f2}
        .table{width: 100%;}
        .pagination{width: 100%;height: 40px;line-height:40px;text-align: center;font-family: 微软雅黑;font-size: 9pt;border-bottom: solid 1px #dddddd;border-left: solid 1px #dddddd;border-right: solid 1px #dddddd;float: left;}
        .el-input{width: 200px;height: 40px;margin-top: 10px;}
        #tit li{margin: 10px 0}
        #result li{margin: 10px 0}
        #tab th{text-align: center}
        td{/**对超出容器的部分强制截取，高度不确定则换行*/
            height: 50px;
            text-align: center;
            overflow: hidden;
            /**显示省略符号来代表被修剪的文本。*/
            text-overflow: ellipsis;
            /**禁止换行*/
            white-space: nowrap;}
        /* 复选框 */
        input[type="checkbox"] {
            -webkit-appearance: none; /*清除复选框默认样式*/
            border: 1px solid #627BF6;
            vertical-align: middle;
            width: 20px;
            height: 20px;
            float: left;
            margin-top: 0;
            margin-left: 3px;
            margin-right: 0 !important;
            border-radius: 3px;
        }
        input[type="checkbox"]:checked {
            /* background-position: -48px 0; */
            background: #627BF6 url(../image/hotel-check.png) ; /*复选框的背景图*/
            background-size: contain;
        }

    </style>
</head>
<body style="margin: 0 20px 100px 0;">
<div>
    <form  class="layui-form" style="height:60px;" action="<c:url value='/AdminHotelServlet?action=searchGo'/>" method="post">
        <div style="float:left;margin: 10px 30px 0 0">
            <span style="font-size: 12pt">条件：</span>
            <div class="layui-input-inline">
                <select id="sKey" name="sKey">
                    <option value=""></option>
                    <option value="name">酒店名称</option>
                    <option value="star">酒店星级</option>
                    <option value="address">酒店地址</option>
                </select>
            </div>
        </div>

        <div style="float:left;">
            <span style="font-size: 12pt">输入搜索：</span>
            <input type="text" id="sValue" name="sValue" class="el-input" placeholder="酒店名称/酒店地址">
            <button class="layui-btn layui-btn-sm" lay-submit lay-filter="formSearch">搜索</button>
        </div>

    </form>
    <div class="operating">
        <button class="layui-btn layui-btn-sm" onclick="goAdd()">添加</button>
        <button class="layui-btn layui-btn-sm" lay-event="delete" onclick="delMore()">删除</button>
    </div>
    <table class="layui-table table" style="table-layout:fixed;" id="tab">
        <colgroup>
            <col width="50">
            <col width="60">
            <col width="180">
            <col width="100">
            <col width="80">

            <col width="80">
            <col width="180">
            <col width="60">
            <col width="60">
            <col width="140">
        </colgroup>
        <thead>
        <tr>
            <th> </th>
            <th>序号</th>
            <th>名称</th>
            <th>说明</th>
            <th>总房间数</th>

            <th>省份</th>
            <th>地址</th>
            <th>星级</th>
            <th>评分</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="record" items="${myList.list}">
            <tr>
                <td><input type="checkbox" name="number" value=${record.hId}></td>
                <td title="${record.hId}">${record.hId}</td>
                <td title="${record.hName}">${record.hName}</td>
                <td title="${record.hLabel}">${record.hLabel}</td>
                <td title="${record.provinceid}">${record.provinceid}</td>

                <td title="${record.provinceid}">${record.provinceid}</td>
                <td title="${record.hAddress}">${record.hAddress}</td>
                <td title="${record.hStar}">${record.hStar}</td>
                <td title="${record.hStar}">${record.hStar}</td>
                <td>
                    <div id="op">
                        <!--
                     <div class="a-2">
                <a href="<c:url value='/AdminHotelServlet?action=findOne&id=${record.id}'/>" >查看</a>|
                <a href="<c:url value='/AdminHotelServlet?action=updateBeforeGo&id=${record.id}'/>">修改</a>|
                <a onclick="javascript:return del()" href="<c:url value='/AdminHotelServlet?action=deleteGo&id=${record.id}'/>" >删除</a>
            </div>
                    -->
                        <a class="layui-btn layui-btn-xs detail" value="${record.hId}">查看</a>
                        <a class="layui-btn layui-btn-xs edit" value="${record.hId}">编辑</a>
                        <a class="layui-btn layui-btn-xs layui-btn-danger del" value="${record.hId}">删除</a>
                    </div>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>

    <div class="pagination">
        <div style="width: 70%;text-align: left;margin-left: 20px;float: left">
            共找到${myList.totalSize}条记录，每页${myList.pageSize}条，共${myList.totalPage }页，当前第${myList.currentPage }页
        </div>
        <div style="float: left;width: 15%;">
            <!-- 首页 -->
            <c:choose>
                <c:when test="${myList.currentPage==1 }">首页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminHotelServlet?action=findAllHotel&currentPage=${myList.currentPage != 1 }'/>">首页</a>
                </c:otherwise>
            </c:choose>
            <!-- 上一页 -->
            <c:choose>
                <c:when test="${myList.currentPage==1 }">上一页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminHotelServlet?action=findAllHotel&currentPage=${myList.currentPage-1 }'/>">上一页</a>
                </c:otherwise>
            </c:choose>
            <!-- 下一页 -->
            <c:choose>
                <c:when test="${myList.currentPage==myList.totalPage }">下一页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminHotelServlet?action=findAllHotel&currentPage=${myList.currentPage+1 }'/>">下一页</a>
                </c:otherwise>
            </c:choose>
            <!-- 尾页 -->
            <c:choose>
                <c:when test="${myList.currentPage == myList.totalPage }">尾页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminHotelServlet?action=findAllHotel&currentPage=${myList.totalPage }'/>">尾页</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div style="float: left">
            跳至<input  type="text" id="num" name="currentPage" style="height: 20px;width: 30px;">页
            <input type="button" onclick="gk()" style="border: none;background-color: #fff" value="确定">
            <!--<a href="<c:url value='/AdminHotelServlet?action=findAllHotel&currentPage=2'/>">确定</a>-->
        </div>
    </div>

</div>
</body>
<script type="text/html" id="detailDialog">
    <div id="tit" style="width: 80px;height: 300px;float: left">
        <ul>
            <li>酒店名称</li>
            <li>酒店简介</li>
            <li>酒店地址</li>
            <li>酒店电话</li>
            <li>酒店星级</li>
            <li>酒店详情</li>
        </ul>
    </div>
    <div id="result" style="width: 600px;height: 300px;float: left;">

    </div>
</script>
<script type="text/html" id="editDialog">
    <form class="layui-form" id="myForm" style="padding: 20px;" lay-filter="editDialogForm">
        <!--
<input type="hidden" action="addAttractions">
-->
        <input type="hidden" id="hId" name="hId" value="">
        <div class="layui-form-item">
            <label class="layui-form-label">酒店名称：</label>
            <div class="layui-input-block" style="width: 400px;">
                <input type="text" id="hotel_name" name="hotel_name" lay-verify="required" lay-reqtext="不能为空" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">酒店标签：</label>
            <div class="layui-input-block" style="width: 400px;">
                <input type="text" id="hotel_label" name="hotel_label" lay-verify="required" lay-reqtext="不能为空" autocomplete="off" class="layui-input">
            </div>
        </div>
        <!--
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">门票价格：</label>
                <div class="layui-input-inline" style="width: 100px;">
                    <input type="text" id="price" name="price" placeholder="￥" autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        -->
        <div class="layui-form-item">
            <label class="layui-form-label">酒店地址：</label>
            <div class="layui-input-block" style="width: 400px;">
                <input type="text" id="hotel_address" name="hotel_address" lay-verify="required" lay-reqtext="不能为空" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">酒店电话：</label>
                <div class="layui-input-inline">
                    <!--lay-verify="required|phone"-->
                    <input type="tel" id="hotel_phone" name="hotel_phone"  autocomplete="off" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">酒店星级：</label>
            <div class="layui-input-block" style="width: 100px;">
                <input type="text" id="hotel_star" name="hotel_star"  autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">酒店简介：</label>
            <div class="layui-input-block" style="width: 600px;">
                <textarea id="content" name="content" style="height:400px;" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <div class="layui-input-block">
                <button type="button" id="goTo" class="layui-btn" lay-filter="go" lay-submit="" >修改</button>
            </div>
        </div>
    </form>
</script>
<script type="text/javascript"  src="../../res/layui/layui.all.js"></script>
<script>
    $(document).ready(function(){
        //document.location = "../../AdminHotelServlet?action=findAllHotel";
        //$.post("${pageContext.request.contextPath}/AdminHotelServlet",{
        $.post("../../AdminHotelServlet",{
            action:"findAllHotel",
        },);
    });
</script>
<script type="application/javascript">
    $(document).ready(function(){

        layui.use(['form'], function() {
            const form = layui.form
                , layer = layui.layer;
            /**查看详情*/
            $(".detail").click(function() {
                const id = $(this).attr("value");
                /**获取单条数据信息*/
                $.ajax({
                    url: "../../AdminHotelServlet",  // 请求路径
                    type:"Get" , //请求方式
                    dataType:"json",//设置接受到的响应数据的格式
                    data:{action:"findOneH",
                        hId:id,
                    },
                    success:function (data) {
                        //JSON.stringify 将JavaScript 对象转换为 JSON 字符串
                        const json = JSON.stringify(data);
                        //alert("返回的数据："+json);
                        console.log(data,'数组');
                        const hName = JSON.stringify(data.hName).replace(/\"/g, "");  //这里去掉所有"
                        const hLabel = JSON.stringify(data.hLabel).replace(/\"/g, "");
                        const hAddress = JSON.stringify(data.hAddress).replace(/\"/g, "");
                        const hPhone = JSON.stringify(data.hPhone).replace(/\"/g, "");
                        const hStar = JSON.stringify(data.hStar).replace(/\"/g, "");
                        const hFormation = JSON.stringify(data.hFormation).replace(/\"/g, "");

                        let str = '';
                        str = "<ul><li>" + hName + "</li><li>" + hLabel + "</li><li>" + hAddress + "</li>" +
                            "<li>" + hPhone + "</li><li>" + hStar + "</li><li>" + hFormation + "</li></ul>";
                        //追加到table中
                        $("#result").html(str);
                        //showData(data);//我们仅做数据展示
                        //location.reload();
                    },//响应成功后的回调函数
                    error:function () {
                        alert("出错啦...")
                    },//表示如果请求响应出现错误，会执行的回调函数
                });

                /**打开弹窗*/
                layer.open({
                    type: 1,
                    title: '详细信息',
                    area: ['70%', '80%'],
                    shade: 0,
                    offset: '10px',//弹窗位置
                    //resize: false,
                    content: $('#detailDialog').html(), //这里content是一个普通的String
                    btn: ['确定', '取消'],
                    /****
                     success: function (index, layero) {
                form.render();
            },
                     yes: function (index, layero) {
            },
                     btn2: function (index, layero) {//return false 开启该代码可禁止点击该按钮关闭
            }
                     **/
                });

            });
            /**获取信息*/
            $(".edit").click(function() {
                const id = $(this).attr("value");
                /**获取单条数据信息*/
                $.ajax({
                    url: "../../AdminHotelServlet",  // 请求路径
                    type:"Get" , //请求方式
                    dataType:"json",//设置接受到的响应数据的格式
                    data:{action:"findOneH",
                        hId:id,
                    },
                    success:function (data) {
                        //JSON.stringify 将JavaScript 对象转换为 JSON 字符串
                        const json = JSON.stringify(data);
                        //alert("返回的数据："+json);
                        console.log(json);
                        const hId= JSON.stringify(data.hId).replace(/\"/g, "");
                        const hName = JSON.stringify(data.hName).replace(/\"/g, "");  //这里去掉所有"
                        const hLabel = JSON.stringify(data.hLabel).replace(/\"/g, "");
                        const hAddress = JSON.stringify(data.hAddress).replace(/\"/g, "");
                        const hPhone = JSON.stringify(data.hPhone).replace(/\"/g, "");
                        const hStar = JSON.stringify(data.hStar).replace(/\"/g, "");
                        const hFormation = JSON.stringify(data.hFormation).replace(/\"/g, "");
                        $("#hId").val(hId);
                        $("#hotel_name").val(hName);
                        $("#hotel_label").val(hLabel);
                        $("#hotel_address").val(hAddress);
                        $("#hotel_phone").val(hPhone);
                        $("#hotel_star").val(hStar);
                        $("#content").html(hFormation);
                        //showData(data);//我们仅做数据展示
                        //location.reload();
                    },//响应成功后的回调函数
                    error:function () {
                        alert("出错啦...")
                    },//表示如果请求响应出现错误，会执行的回调函数
                });
                //展示数据
                function showData(data) {
                    let str = "";
                    for(let i = 0; i < data.length; i++){    //遍历data数组
                        const ls = data[i];
                        alert(ls);
                        str +="<span>测试："+ls.spName+"</span>";
                        $("#sValue").html(str);//在html页面id=test的标签里显示html内容
                    }
                }
                /**打开弹窗*/
                layer.open({
                    type: 1,
                    title: '修改信息',
                    area: ['70%', '80%'],
                    shade: 0,
                    offset: '10px',//弹窗位置
                    //resize: false,
                    content: $('#editDialog').html(), //这里content是一个普通的String
                    btn: ['确定', '取消'],
                });

            });
            //监听提交
            form.on('submit(go)', function(data){
                //使用$.ajax()发送异步请求
                $.ajax({
                    //url:"${pageContext.request.contextPath}/AdminHotel" , // JSP页面使用 请求路径
                    url:"../../AdminHotelServlet?action=changeHInf" , // 请求路径
                    type:"POST" , //请求方式
                    data:$('#myForm').serialize(),// 序列化表单值
                    success:function (data) {
                        alert("修改成功");
                        document.location = "../../AdminHotelServlet?action=findAllHotel";
                        //form.render();//菜单渲染 把内容加载进去
                        //location.reload();
                    },//响应成功后的回调函数
                    error:function () {
                        alert("出错啦...")
                    },//表示如果请求响应出现错误，会执行的回调函数
                    dataType:"text"//设置接受到的响应数据的格式
                });
                return true;//不跳转
            });
        });


    });
    /**删除一条*/
    $(".del").click(function(){
        const id = $(this).attr("value");
        const msg = "确定要删除吗？\n请确认！";
        if (confirm(msg)==true){
            $.ajax({
                type: "post",
                url: "../../AdminHotelServlet",
                data:{action:"deleteH",
                    hId:id,
                },
                dataType: "json",
                success: function(data){
                    document.location = "../../AdminHotelServlet?action=findAllHotel";
                    alert("删除成功");
                    // $("#showMsg").html(data.msg);//修改id为showMsg标签的html
                }, error: function(){
                    alert("请求出错");
                }
            })
        }else{
            return false;
        }
    });
    /**
     function del() {
        const msg = "确定要删除吗？\n请确认！";
        if (confirm(msg)==true){
            alert("删除成功");
            return true;
        }else{
            return false;
        }
    }
     */
    /**页码跳转*/
    function Jump() {
        const num = document.getElementById("num").value;
        document.location = "../../AdminHotelServlet?action=findAllHotel&currentPage="+num;
    }
    /**删除多条记录*/
    function delMore() {
        //获取所有名字为ck的编号组件
        const ck = document.getElementsByName("number");
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
        if (s==""){
            alert("请至少选择一条数据");
            return false;
        }
        //确认选项
        let ok = window.confirm("确定要删除["+s+"] 记录吗？");
        //如果确认选择
        if(ok) {
            alert("删除成功");
            //把ids传入后台调用servlet
            document.location = "../../AdminHotelServlet?action=delHMore&ids="+s;
        }
    }

    function goAdd() {
        layer.open({
            type: 1,
            title: '添加',
            area: ['50%', '70%'],
            shade: 0,
            //resize: false,
            content: $('#editDialog').html(), //这里content是一个普通的String
            btn: ['确定', '取消'],
            /**
             success: function (index, layero) {
                form.render();
            },
             yes: function (index, layero) {
            },
             btn2: function (index, layero) {//return false 开启该代码可禁止点击该按钮关闭
            }
             */
        });
    };

</script>

</html>