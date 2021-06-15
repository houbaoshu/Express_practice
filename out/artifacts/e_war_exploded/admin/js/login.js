layui.define(['element'],function(exports){

    var $ = layui.$;
    $('.input-field').on('change',function(){
        var $this = $(this),
            value = $.trim($this.val()),
            $parent = $this.parent();

        if(value !== '' && !$parent.hasClass('field-focus')){
            $parent.addClass('field-focus');
        }else{
            $parent.removeClass('field-focus');
        }
    })
    exports('login');
});
/*
$(document).ready(() => {
    $(".login-button").on("click",() => {
        let  username = $("#username").val();
        let password = $("#password").val();
        //1、 先使用layer，弹出load图标
        const windowId = layer.load();
        //2、 ajax与服务器交互
        $.post("login.do", {username: username, password: password}, function (data) {
            //3、 关闭load窗口
            layer.close(windowId);
            //4、将服务器回复的结果进行显示
            layer.msg(data.result);
            if (data.status == 0) {
                //跳转到主页
                location.assign("index.html");
            }
        },"JSON");
    })
})*/
