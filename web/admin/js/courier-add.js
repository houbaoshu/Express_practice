$(document).ready(() => {
    $(".btn-info").on('click',() => {
        let windowId = layer.load();
        let username = $("input:eq(0)").val();
        let userPhone = $("input:eq(1)").val();
        let IDCardNumber = $("input:eq(2)").val();
        let password = $("input:eq(3)").val();
        $.post('/courier/insert.do',{'username': username,'userPhone':userPhone,'IDCardNumber':IDCardNumber,'password':password},function (data) {
            layer.close(windowId);
            layer.msg(data.result);
            if (data.status == 0) {
                //录入成功
                $("input").val("");
            }
        },'JSON');
    });
});