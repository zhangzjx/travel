<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% //System.out.println("jsp结果为"+request.getSession().getAttribute("myList"));%>
<html>
<head>
    <title>list</title>
    <link rel="stylesheet" href="../../layui/css/layui.css">
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <style type="text/css">
        .el-select{width: 100px;height: 35px;font-size: 10pt;margin-top: 10px;}
        .operating{width: 94.5%;height: 30px;margin: 10px 0;padding: 10px 30px;background-color:#f2f2f2}
        .table{width: 100%;}
        .a-3{width: 100%;height: 40px;line-height:40px;text-align: center;font-family: 微软雅黑;font-size: 9pt;border-bottom: solid 1px #dddddd;border-left: solid 1px #dddddd;border-right: solid 1px #dddddd;float: left;}
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
<div >
    <form  action="<c:url value='/AdminAttractionsServlet?action=searchGo'/>" method="post">
        <span style="font-size: 12pt">条件：</span>

        <select id="sKey" name="sKey" class="el-select">
            <option value=""></option>
            <option value="SpName">景点名称</option>
            <option value="SpStar">景点星级</option>
            <option value="SpAddress">景点地址</option>
        </select>
        <span style="font-size: 12pt">输入搜索：</span>
        <input type="text" id="sValue" name="sValue" class="el-input" placeholder="景点名称/景点地址">
        <button class="layui-btn layui-btn-sm" lay-submit lay-filter="formSearch">搜索</button>


    </form>
    <div class="operating">
        <button class="layui-btn layui-btn-sm" onclick="goAdd()">添加</button>
        <button class="layui-btn layui-btn-sm" onclick="delMore()">批量删除</button>
    </div>
    <table class="layui-table table" style="table-layout:fixed;" id="tab">
        <colgroup>
            <col width="50">
            <col width="60">
            <col width="180">
            <col width="60">
            <col width="100">
            <col width="60">
            <col width="60">
            <col width="180">
            <col width="60">
            <col width="60">
            <col width="180">
        </colgroup>
        <thead>
        <tr>
            <th> </th>
            <th>序号</th>
            <th>名称</th>
            <th>说明</th>
            <th>营业时间</th>
            <th>价格</th>
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
                <td><input type="checkbox" name="number" value=${record.spId}></td>
                <td>${record.spId}</td>
                <td title="${record.spName}">${record.spName}</td>
                <td title="${record.spLebel}">${record.spLebel}</td>
                <td title="${record.spTime}">${record.spTime}</td>
                <td title="${record.spPrice}">${record.spPrice}</td>
                <td title="${record.spName}">${record.province}</td>
                <td title="${record.spAddress}">${record.spAddress}</td>
                <td title="${record.spName}">${record.star}</td>
                <td title="${record.spName}">${record.country}</td>
                <td>
                    <div id="op">
                        <!--
                     <div class="a-2">
                <a href="<c:url value='/AdminAttractionsServlet?action=findOne&id=${record.id}'/>" >查看</a>|
                <a href="<c:url value='/AdminAttractionsServlet?action=updateBeforeGo&id=${record.id}'/>">修改</a>|
                <a onclick="javascript:return del()" href="<c:url value='/AdminAttractionsServlet?action=deleteGo&id=${record.id}'/>" >删除</a>
            </div>
                    -->
                        <a class="layui-btn layui-btn-xs detail" value="${record.spId}">查看</a>
                        <a class="layui-btn layui-btn-xs edit" value="${record.spId}">编辑</a>
                        <a class="layui-btn layui-btn-xs layui-btn-danger del" value="${record.spId}">删除</a>
                        <!--
                        <a class="layui-btn layui-btn-danger layui-btn-xs" onclick="javascript:return del()" href="<c:url value='/AdminAttractionsServlet?action=deleteAt&id=${record.spId}'/>" >删除</a>
                   -->
                    </div>
                </td>
            </tr>
        </c:forEach>

        </tbody>

    </table>

    <div class="a-3">
        <div style="width: 70%;text-align: left;margin-left: 20px;float: left">
            共找到${myList.totalSize}条记录，每页${myList.pageSize}条，共${myList.totalPage }页，当前第${myList.currentPage }页
        </div>
        <div style="float: left;width: 15%;">
            <!-- 首页 -->
            <c:choose>
                <c:when test="${myList.currentPage==1 }">首页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminAttractionsServlet?action=findAllAttractions&currentPage=${myList.currentPage != 1 }'/>">首页</a>
                </c:otherwise>
            </c:choose>
            <!-- 上一页 -->
            <c:choose>
                <c:when test="${myList.currentPage==1 }">上一页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminAttractionsServlet?action=findAllAttractions&currentPage=${myList.currentPage-1 }'/>">上一页</a>
                </c:otherwise>
            </c:choose>
            <!-- 下一页 -->
            <c:choose>
                <c:when test="${myList.currentPage==myList.totalPage }">下一页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminAttractionsServlet?action=findAllAttractions&currentPage=${myList.currentPage+1 }'/>">下一页</a>
                </c:otherwise>
            </c:choose>
            <!-- 尾页 -->
            <c:choose>
                <c:when test="${myList.currentPage == myList.totalPage }">尾页
                </c:when>
                <c:otherwise>
                    <a href="<c:url value='/AdminAttractionsServlet?action=findAllAttractions&currentPage=${myList.totalPage }'/>">尾页</a>
                </c:otherwise>
            </c:choose>
        </div>
        <div style="float: left">
            跳至<input  type="text" id="num" name="currentPage" style="height: 20px;width: 30px;">页
            <input type="button" onclick="Jump()" style="border: none;background-color: #fff" value="确定">
            <!--<a href="<c:url value='/AdminAttractionsServlet?action=findAllAttractions&currentPage=2'/>">确定</a>-->
        </div>
    </div>

</div>
</body>
<script type="text/html" id="detailDialog">
    <div id="tit" style="width: 80px;height: 300px;float: left">
        <ul>
            <li>景点名称</li>
            <li>景点简介</li>
            <li>营业时间</li>
            <li>景点地址</li>
            <li>景点电话</li>
            <li>景点详情</li>
            <li>景点星级</li>
            <li>景点电话</li>
        </ul>
    </div>
    <div id="result" style="width: 600px;height: 300px;float: left;">
        <ul>
            <li></li>
        </ul>
    </div>
</script>
<script type="text/html" id="editDialog">
    <form class="layui-form" action="" style="padding: 20px;" lay-filter="editDialogForm">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-inline">
                <input type="text" name="username" placeholder="请输入用户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">单选框</label>
            <div class="layui-input-inline">
                <input type="radio" name="sex" value="男" title="男">
                <input type="radio" name="sex" value="女" title="女" checked>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">选择城市</label>
            <div class="layui-input-inline">
                <select name="city">
                    <option value="城市-0">城市-0</option>
                    <option value="城市-1">城市-1</option>
                    <option value="城市-2">城市-2</option>
                    <option value="城市-3">城市-3</option>
                    <option value="城市-4">城市-4</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">请填写签名</label>
            <div class="layui-input-block">
                <textarea name="sign" placeholder="请填写签名" class="layui-textarea"></textarea>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">积分</label>
            <div class="layui-input-inline">
                <input type="number" name="experience" placeholder="请输入积分" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">评分</label>
            <div class="layui-input-inline">
                <input type="number" name="score" placeholder="请输入评分" autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">选择职业</label>
            <div class="layui-input-inline">
                <select name="classify">
                    <option value="作家">作家</option>
                    <option value="词人">词人</option>
                    <option value="酱油">酱油</option>
                    <option value="诗人">诗人</option>
                </select>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">财富</label>
            <div class="layui-input-inline">
                <input type="number" name="wealth" placeholder="请输入财富" autocomplete="off" class="layui-input">
            </div>
        </div>

    </form>
</script>
<script type="text/javascript"  src="../../res/layui/layui.all.js"></script>

<script type="text/javascript">

    $(".detail").click(function() {
        const id = $(this).attr("value");
        /**获取单条数据信息*/
        $.ajax({
            url: "../../AdminAttractionsServlet",  // 请求路径
            type:"Get" , //请求方式
            dataType:"json",//设置接受到的响应数据的格式
            data:{action:"findOneAt",
                spId:id,
            },
            success:function (data) {
                //map型数据
                let str = '';
                let spName ;
                for(const i in data){
                 //  alert(i);
                  // alert(data[i])
                    str +="<li>" + data[i] + "<li>";
                }
                $("#result").html(str);
                //const json = JSON.stringify(data);
               // alert(json);
               // showData(data);//我们仅做数据展示
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
                alert("zhixng");

                $("#sValue").html(str);//在html页面id=test的标签里显示html内容
            }
        }

        layer.open({
            type: 1,
            title: '详细信息',
            area: ['70%', '80%'],
            shade: 0,
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
<script type="application/javascript">
    $(document).ready(function(){
        // 页面加载后任何需要执行的js特效
        //document.location = "../../AdminAttractionsServlet?action=findAllAttractions";
        $.post("${pageContext.request.contextPath}/AdminAttractionsServlet",{
            action:"findAllAttractions",
        },)
    });
    $(".del").click(function(){
        const id = $(this).attr("value");
        const msg = "确定要删除吗？\n请确认！";
        if (confirm(msg)==true){
            $.ajax({
                type: "post",
                url: "../../AdminAttractionsServlet",
                data:{action:"deleteAt",
                    spId:id,
                },
                dataType: "json",
                success: function(data){
                    document.location = "../../AdminAttractionsServlet?action=findAllAttractions";
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
        document.location = "../../AdminAttractionsServlet?action=findAllAttractions&currentPage="+num;
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
            document.location = "../../AdminAttractionsServlet?action=delAtMore&ids="+s;
        }
    }
</script>

<script>
    layuiModules=['table', 'layer','form'];
    function mounted() {
        //第一个实例

        table.on('toolbar(test)', function (obj) {
            const checkStatus = table.checkStatus(obj.config.id);
            switch (obj.event) {
                case 'add':
                    layer.msg('添加');
                    layer.open({
                        type: 1,
                        title: '添加',
                        area:['50%','70%'],
                        content: $('#editDialog').html(), //这里content是一个普通的String
                        btn: ['确定', '取消'],
                        success: function (index, layero) {
                            form.render();
                        },
                        yes: function (index, layero) {
                        },
                        btn2: function (index, layero) {
                            //return false 开启该代码可禁止点击该按钮关闭
                        }
                    });
                    break;
                case 'delete':
                    console.log(checkStatus);
                    layer.confirm('真的删除选中行么', function (index) {
                        layer.close(index);
                        //向服务端发送删除指令
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            }
        });
        //监听工具条
        table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
            var tr = obj.tr; //获得当前行 tr 的DOM对象
            if (layEvent === 'detail') { //查看
                //do somehing
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function (index) {
                    obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
                //do something
                layer.open({
                    type: 1,
                    title: '编辑',
                    area:['50%','70%'],
                    content: $('#editDialog').html(), //这里content是一个普通的String
                    btn: ['确定', '取消'],
                    success: function (index, layero) {
                        form.render();
                        form.val("editDialogForm",data);
                    },
                    yes: function (index, layero) {
                    },
                    btn2: function (index, layero) {
                        //return false 开启该代码可禁止点击该按钮关闭
                    }
                });
                //同步更新缓存对应的值
                obj.update({
                    username: '123'
                    , title: 'xxx'
                });
            }
        });
    }
</script>
</html>