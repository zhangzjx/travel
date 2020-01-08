<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>导出发票</title>
    <script src="js/jquery-3.4.1.min.js"></script>
    <style type="text/css">
      ul{
        width: 100px;
        float: left;
      }
      li{width: 200px;height: 30px}
      ul li{
        list-style-type:none;
      }
    </style>
  </head>
  <body>
  <div style="width: 300px;margin: 100px 0 0 500px">
    <div id="u"></div>
    <form  id="myForm" action="ExcelServlet?action=test1" method="post" >

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

      <ul>
        <li><input type="text" name="DDBH" readonly value="10010"></li>
        <li><input type="text" name="KPLX" readonly value="普通发票"></li>
        <li><input type="text" name="SPMC" readonly value="明细"></li>
        <li><input type="text" name="FPTT" readonly value="个人"></li>
        <li><input id="nsrsbh" name="nsrsbh"  placeholder="识别号"/></li>
        <li><input type="text" name="SKY"></li>
        <li><input type="text" name="FKFDH"></li>
        <li><input type="text" name="FKFDZ"></li>
      </ul>
      <input type="submit" value="提交" STYLE="margin: 0 0 0 150px;float: left;">
      <input type="button" onclick="go()" value="查看待开票" STYLE="margin: 20px 0 0 150px;float: left;">


    </form>

  </div>
  </body>
  <script type="text/javascript">

    function go(){
      document.location = "ExcelServlet?action=findAll";
    }
  </script>
</html>
