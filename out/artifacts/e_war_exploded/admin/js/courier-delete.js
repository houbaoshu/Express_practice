$(document).ready(() => {
    // 隐藏第二个header和content的内容
    $(".header:eq(1)").hide();
    $(".content:eq(1)").hide();
    let adminId = null;
    let g_userPhone = null;
    // 查询成功后显示快递员信息
    $(".btn-info:eq(0)").on('click',() => {
        let windowId = layer.load();
        let userPhone = $(".inline-input").val();
        $.getJSON('/admin/find.do',{'userPhone':userPhone},function (data) {
             layer.close(windowId);
            layer.msg(data.result);
            if (data.status == 0) {
                $(".header:eq(1),.content:eq(1)").fadeIn(1000);
                adminId = data.data.id;
                g_userPhone = data.data.userPhone;
                $("input:eq(1)").val(data.data.username);
                $("input:eq(2)").val(data.data.userPhone);
                $("input:eq(3)").val(data.data.idCardNumber);
                $("input:eq(4)").val(data.data.password);
            }
        });
    });
    //删除快递员
    $(".btn-info:eq(1)").on('click',() => {
        let windowId = layer.load();
        let username = $("input:eq(1)").val();
        let userPhone = $("input:eq(2)").val();
        let idCardNumber = $("input:eq(3)").val();
        let password = $("input:eq(4)").val();
        $.post('/admin/delete.do',{'id':adminId,'username': username,'userPhone':userPhone,'idCardNumber':idCardNumber,'password':password},function (data) {
            layer.close(windowId);
            layer.msg(data.result);
            if (data.status == 0) {
                //删除成功
                $(".header:eq(1),.content:eq(1)").hide();
            }
        },'JSON');
    });
});