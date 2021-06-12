$(document).ready(() => {
    // 隐藏第二个header和content的内容
    $(".header:eq(1)").hide();
    $(".content:eq(1)").hide();
    // 查询成功后显示快递员信息
    $(".btn-info:eq(0)").on('click',() => {
        let windowID = layer.load;
        let userPhone = $(".inline-input").val();
        $.getJSON('/courier/find.do',{'userPhone':userPhone},function (data) {
            layer.close();
            layer.msg(data.result);
            if (data.status == 0) {
                $(".header:eq(1),.content:eq(1)").fadeIn(1000);
                $("input:eq(1)").val(data.data.username());
                $("input:eq(2)").val(data.data.userPhone());
                $("input:eq(3)").val(data.data.IDCardNumber());
                $("input:eq(4)").val(data.data.password());
            }
        });
    });
    //删除快递员
    $(".btn-info").on('click',() => {
        let windowId = layer.load();
        let username = $("input:eq(1)").val();
        let userPhone = $("input:eq(2)").val();
        let IDCardNumber = $("input:eq(3)").val();
        let password = $("input:eq(4)").val();
        $.post('/courier/delete.do',{'username': username,'userPhone':userPhone,'IDCardNumber':IDCardNumber,'password':password},function (data) {
            layer.close(windowId);
            layer.msg(data.result);
            if (data.status == 0) {
                //删除成功
                $("input").val("");
            }
        },'JSON');
    });
});