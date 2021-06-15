$(document).ready(() => {
    $(function () {
        $("#admin_list").bootstrapTable({
            url: "/admin/list.do",//数据地址
            striped: true,//是否显示行的间隔
            pageNumber: 1,//初始化加载第几页
            pagination: true,//是否分页
            sidePagination: 'server',
            pageSize: 5,
            pageList: [5, 10, 20],
            showRefresh: true,
            queryParams: function (params) {
                let temp = {
                    offset: params.offset,
                    pageNumber: params.limit
                };
                return temp;
            },
            columns: [
                {
                    title: "编号",
                    field: "id",
                    sortable: false
                },
                {
                    title: "姓名",
                    field: "username",
                    sortable: false
                },
                {
                    title: "手机号码",
                    field: "userPhone",
                    sortable: false
                },
                {
                    title: "身份证",
                    field: "idCardNumber",
                    sortable: false
                },
                {
                    title: "密码",
                    field: "password",
                    sortable: false
                },
                {
                    title: "派件数",
                    field: "numOfDelivered",
                    sortable: false
                },
                {
                    title: "注册时间",
                    field: "registerTime",
                    sortable: false
                },
                {
                    title: "上次登录时间",
                    field: "lastLogTime",
                    sortable: false
                }
            ]
        });
    });
})
