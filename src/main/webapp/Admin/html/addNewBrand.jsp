<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="../css/goodsManage.css">
    <script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
    <script type="text/javascript" src="js/SecondaryLinkage.js"></script>
    <style type="text/css">

    </style>
    <script type="application/javascript">
        //验证表单合法性
        function go(){
            const brandName = $("#hotel_id").val();

            if(brandName==""||brandName==null||){
                alert("表单内容不完整");
                $(this).next('input').focus();
                return false;
            }
            $("#myForm").submit();
        }
    </script>
</head>
<body onLoad="init()">
<div class="main-top"><span style="margin-left: 10px">添加新品牌</span></div>
<!--添加品牌-->
<div class="goods-main">
    <form  id="myForm" class="brand-form" action="../../PhotoServlet?action=addHotelImg" method="post" enctype="multipart/form-data">
        <div id="brand-a" >
            <div class="brand-1" >*品牌名称：</div>
            <div class="brand-2" ><input type="text" id="hotel_id" name="hotel_id" class="brand-input"/></div>

            <div class="brand-1" >*品牌LOGO：</div>
            <div class="brand-2">
                <span style="margin-right: 180px">仅支持上传JPG，JPEG，PNG格式图片</span>
                <div id="imgPreview">
                    <div id="prompt">
                        <span id="imgSpan">点击上传	</span>
                        <input  type="file" id="photo" name="photo" class="filepath" onchange="changePic(this)"
                                accept="image/jpg,image/jpeg,image/png"/>
                        <!--当vaule值改变时执行changepic函数，规定上传的文件只能是图片-->
                    </div>
                    <img src="#" id="img" />
                </div>
            </div>

            <div class="brand-3" >
                <input type="submit" value="添加" class="go-button" />
            </div>
        </div>
    </form>
</div>
</body>
<script type="application/javascript">
    function changePic() {
        $("#prompt").css("display", "none");
        const reads = new FileReader();
        const f = document.getElementById('photo').files[0];
        reads.readAsDataURL(f);
        reads.onload = function(e) {
            document.getElementById('img').src = this.result;
            $("#img").css("display", "block");
        };
    }
</script>
</html>
