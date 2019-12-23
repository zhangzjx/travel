let i = 0,
    timer;
const sum = $(".image li").length;

$(function() {
    init();
});


function init() {
    $(".image li").eq(0).show();

    $(".box").hover(function() {
        $(".arrow").show();
        clearInterval(timer);
    }, function() {
        $(".arrow").hide();
        iLunbo();
    })
    iLunbo();

    $(".arrow").hover(function() {
        clearInterval(timer);
    });
    /*左箭头控制轮播*/
    $(".left").click(function() {
        clearInterval(timer);
        if (i == 0) {
            i = sum;
        }
        i--;
        startLunbo();
        iLunbo();
    });

    /*右箭头控制轮播*/
    $(".right").click(function() {
        clearInterval(timer);
        if (i == sum - 1) {
            i = -1;
        }
        i++;
        startLunbo();
        iLunbo();
    });

    /*提示信息变换*/
    $(".num>li").hover(function() {
        clearInterval(timer);
        i = $(this).index();
        // console.log(i);
        startLunbo();
    });
}


/*自动轮播*/
function iLunbo() {
    timer = setInterval(function() {
        i++;
        if (i == sum - 1) {
            i = -1;
        }
        startLunbo();
    }, 3000)
}

/*图片轮播和提示信息*/
function startLunbo() {
    if (i == 6) {
        i = 0;
    }
    $(".image>li").eq(i).fadeIn().siblings().fadeOut();
    $(".num>li").eq(i).addClass("current").siblings().removeClass("current");
}

/**window.onload = function(){
    function $(param){
        if(arguments[1] == true){
            return document.querySelectorAll(param);
        }else{
            return document.querySelector(param);
        }
    }
    const  $box = $(".box");
    const  $box1 = $(".box-1 ul li",true);
    //底部小圆点
    const  $box2 = $(".box-2 ul");
    const  $box3 = $(".box-3");
    //用于获取轮播图图片个数
    const  $length = $box1.length;
    //底部小圆点
    let str ="";
    for(let i =0; i<$length; i++){
        if(i==0){
            str +="<li class='on'>"+(i+1)+"</li>";
        }else{
            str += "<li>"+(i+1)+"</li>";
        }
    }
    $box2.innerHTML = str;
    //记录当前位子
    let current = 0;

    //启动定时器,设置时间为3秒一次
    let timer;
    timer = setInterval(go,2000);

    //当鼠标移入当前图片时，清除计时
    for(let k=0; k<$length; k++){
        $box1[k].onmouseover = function(){
            clearInterval(timer);
        }
        $box1[k].onmouseout = function(){
            timer = setInterval(go,2000);
        }
    }
    for(let p=0; p<$box3.children.length; p++){
        $box3.children[p].onmouseover = function(){
            clearInterval(timer);
        };
        $box3.children[p].onmouseout = function(){
            timer = setInterval(go,2000);
        }
    }
    //底部小圆点控制图片
    for(let u =0; u<$length; u++){
        $box2.children[u].index  = u;
        $box2.children[u].onmouseover = function(){
            clearInterval(timer);
            for(var j=0;j<$length;j++){
                $box1[j].style.display = "none";
                $box2.children[j].className = "";
            }
            this.className = "on";
            $box1[this.index].style.display = "block";
            current = this.index +1;
        }
        $box2.children[u].onmouseout = function(){
            timer = setInterval(go,2000);
        }
    }

    //点击上一张
    $box3.children[0].onclick = function(){
        back();
    }
    //点击下一张
    $box3.children[1].onclick = function(){
        go();
    }
    //点击下一张
    function go(){
        for(let j =0; j<$length; j++){
            $box1[j].style.display = "none";
            $box2.children[j].className = "";
        }
        if($length == current){
            current = 0;
        }
        $box1[current].style.display = "block";
        $box2.children[current].className = "on";
        current++;
    }
    //点击上一张
    function back(){
        for(let j =0; j<$length; j++){
            $box1[j].style.display = "none";
            $box2.children[j].className = "";
        }
        if(current == 0){
            current = $length;
        }
        $box1[current-1].style.display = "block";
        $box2.children[current-1].className = "on";
        current--;
    }
}
 */