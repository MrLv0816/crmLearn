function ajaxUtil(url, data, fun) {
    $.ajax({
        url: url,
        type: "post",
        data: data,
        dataType: "json",
        traditional: true,
        success: (fun)
    })
}


function Ajaxlogin(loginAct, loginPwd, autoLogin) {

    var data = {"loginAct": loginAct, "loginPwd": loginPwd, "autoLogin": autoLogin}

    ajaxUtil("settings/user/login.do", data, function (data) {


        if (data.code === 0) {
            document.location.href = "workbench/toIndex.do"
        } else {
            $("#msg").html(data.msg);
        }


    })
}


function Typesave() {
    $("#submitBtn").click(function () {

        var data = {"code": $("#code").val(), "name": $("#name").val(), "description": $("#description").val()}

        ajaxUtil("settings/dictionary/type/save.do", data, function (result) {


            if (result.code === 0) {
                document.location.href = "settings/dictionary/toIndex.do";
            } else {
                alert(result.msg);
            }


        })
    })
}

function TypeEdit() {
    $("#submitBtn").click(function () {

        var data = {"code": $("#code").val(), "name": $("#name").val(), "description": $("#description").val()}

        ajaxUtil("settings/dictionary/type/edit.do", data, function (result) {

            if (result.code === 0) {
                document.location.href = "settings/dictionary/toIndex.do";
            } else {
                alert(result.msg);
            }


        })
    })
}

function TypeDeletes() {


    var codes = [];
    $("input[name = ck]:checked").each(function () {
        codes.push($(this).val())
    })
    var data = {"codes": codes}

    ajaxUtil("settings/dictionary/type/delete.do", data, function (result) {

        if (result.code === 0) {
            document.location.href = "settings/dictionary/toIndex.do";
        } else {
            alert(result.msg);
        }


    })
}

function checkCode() {
    $("#code").blur(function () {

        var data = {"code": $(this).val()};

        ajaxUtil("settings/dictionary/type/checkCode.do", data, function (result) {

            if (result.code > 0) {
                $("#msg").html("  " + result.msg);
            } else {
                $("#msg").html('');
            }


        })
    })
}

//异步加载内部数据
function getValueData() {

    ajaxUtil("settings/dictionary/value/data.do", {}, function (result) {
        var dataHtml = ''
        for (var i = 0; i < result.length; i++) {
            dataHtml +=
                "<tr className=\"" + (i % 2 === 0 ? 'active' : '') + "\">\n" +
                "        <td><input type=\"checkbox\" name=\"ck\"/></td>\n" +
                "        <td>" + result[i].id + "</td>\n" +
                "        <td>" + result[i].value + "</td>\n" +
                "        <td>" + result[i].text + "</td>\n" +
                "        <td>" + result[i].orderNo + "</td>\n" +
                "        <td>" + result[i].typeCode + "</td>\n" +
                "    </tr>";
        }
        $("#dataTbody").html(dataHtml);

    })
}


//异步加载内部数据
function getActivityData(page, limit, data) {
    if (data == null || data === '') {
        data = {"page": page, "limit": limit};
    }

    ajaxUtil("workbench/activity/data.do", data, function (result) {
        var dataHtml = '';
        $.each(result.data, function (i, data) {
            dataHtml +=
                "<tr className=\"" + (i % 2 === 0 ? 'active' : '') + "\">\n" +
                "        <td><input name='ck' value='" + data.id + "' type=\"checkbox\"/></td>\n" +
                "        <td><a style=\"text-decoration: none; cursor: pointer;\" href=\"/workbench/activity/toDetail.do?id=" + data.id + "\" \">" + data.name + "</a>\n" +
                "        </td>\n" +
                "        <td>" + data.user.name + "</td>\n" +
                "        <td>" + data.startDate + "</td>\n" +
                "        <td>" + data.endDate + "</td>\n" +
                "    </tr>";
        })
        var tbody = $("#tbodyData")
        tbody.empty();
        tbody.html(dataHtml)

        //初始化分页组件,并加载数据
        $("#activityPage").bs_pagination({
            currentPage: result.pageNum, // 页码
            rowsPerPage: result.pageSize, // 每页显示的记录条数
            maxRowsPerPage: 20, // 每页最多显示的记录条数
            totalPages: result.totalPages, // 总页数
            totalRows: result.totalCounts, // 总记录条数

            visiblePageLinks: 3, // 显示几个卡片

            showGoToPage: true,
            showRowsPerPage: true,
            showRowsInfo: true,
            showRowsDefaultInfo: true,

            //当点击分页组件的按钮,或输入内容时,我们会触发此回调函数
            onChangePage: function (event, data) {
                //参数1,当前页
                //参数2,每页条数
                getActivityData(data.currentPage, data.rowsPerPage);
            }
        });
        reverseAll();

    })
}


function CreateActivity() {


    var data = {
        "owner": $("#create-marketActivityOwner").val(),
        "name": $("#create-marketActivityName").val(),
        "startDate": $("#create-startTime").val(),
        "endDate": $("#create-endTime").val(),
        "cost": $("#create-cost").val(),
        "description": $("#create-description").val()
    }

    ajaxUtil("workbench/activity/create.do", data, function (result) {
        if (result.code === 1) {
            alert(result.msg);
        } else {
            alert("创建成功!")
            window.location.href = "workbench/activity/toIndex.do";
        }
    })

}

function showEditActivityModal() {

    var ck = $("input[name = ck]:checked");
    if (ck.length !== 1) {
        alert("请正确勾选需要修改的数据");
        return;
    }

    var data = {"id": ck.val()}

    ajaxUtil("workbench/activity/data.do", data, function (result) {
        if (result.code < 0) {
            alert(result.msg);
        } else {

            ajaxUtil("settings/user/data.do", {}, function (resultData) {

                var dataHtml = '';
                var owner = $("#edit-marketActivityOwner");

                if (resultData.code < 0) {
                    alert(resultData.msg);
                } else {

                    $.each(resultData.data, function (i, data) {

                        if (result.data[0].owner === data.id) {
                            dataHtml += "<option value=\'" + data.id + "\' selected='selected'>" + data.name + "</option>"
                        } else {
                            dataHtml += "<option value=\'" + data.id + "\'>" + data.name + "</option>"
                        }

                    })
                    owner.empty();
                    owner.html(dataHtml);

                }
            })

            $("#edit-marketActivityName").val(result.data[0].name);
            $("#edit-startTime").val(result.data[0].startDate);
            $("#edit-endTime").val(result.data[0].endDate);
            $("#edit-cost").val(result.data[0].cost);
            $("#edit-description").val(result.data[0].description);
            $("#editActivityModal").modal('show');
        }
    })


}


function showCreateActivityModal() {


    ajaxUtil("settings/user/data.do", {}, function (result) {

        var dataHtml = '';
        var owner = $("#create-marketActivityOwner");

        if (result.code < 0) {
            alert(result.msg);
        } else {
            $.each(result.data, function (i, data) {
                dataHtml += "<option value=\'" + data.id + "\'>" + data.name + "</option>"
            })
            owner.empty();
            owner.html(dataHtml);

        }


    })
}

function updateAcivity() {
    var data = {
        "id": $("input[name = ck]:checked").val(),
        "owner": $("#edit-marketActivityOwner").val(),
        "name": $("#edit-marketActivityName").val(),
        "startDate": $("#edit-startTime").val(),
        "endDate": $("#edit-endTime").val(),
        "cost": $("#edit-cost").val(),
        "description": $("#edit-description").val()
    }

    ajaxUtil("workbench/activity/update.do", data, function (result) {

        if (result.code === 0) {
            window.location.href = "workbench/activity/toIndex.do";
        } else {
            alert(result.msg);
        }

    })
}

function searchBtn(page, limit) {
    var name = $("#hiddenName").val($("#name").val()).val()
    var owner = $("#hiddenOwenr").val($("#owner").val()).val()
    var startDate = $("#hiddenStartDate").val($("#startTime").val()).val()
    var endDate = $("#hiddenEndDate").val($("#endTime").val()).val()

    var data = {"page": page, "limit": limit, "name": name, "owner": owner, "startDate": startDate, "endDate": endDate};
    ajaxUtil("workbench/activity/search.do", data, function (result) {

        var dataHtml = '';

        $.each(result.data, function (i, data) {
            dataHtml +=
                "<tr className=\"" + (i % 2 === 0 ? 'active' : '') + "\">\n" +
                "        <td><input name='ck' value='" + data.id + "' type=\"checkbox\"/></td>\n" +
                "        <td><a style=\"text-decoration: none; cursor: pointer;\" href=\"/workbench/activity/toDetail.do?id=" + data.id + "\" \">" + data.name + "</a>\n" +
                "        </td>\n" +
                "        <td>" + data.user.name + "</td>\n" +
                "        <td>" + data.startDate + "</td>\n" +
                "        <td>" + data.endDate + "</td>\n" +
                "    </tr>";
        })

        var tbody = $("#tbodyData")
        tbody.empty();
        tbody.html(dataHtml)


        //初始化分页组件,并加载数据
        $("#activityPage").bs_pagination({
            currentPage: result.pageNum, // 页码
            rowsPerPage: result.pageSize, // 每页显示的记录条数
            maxRowsPerPage: 20, // 每页最多显示的记录条数
            totalPages: result.totalPages, // 总页数
            totalRows: result.totalCounts, // 总记录条数

            visiblePageLinks: 3, // 显示几个卡片

            showGoToPage: true,
            showRowsPerPage: true,
            showRowsInfo: true,
            showRowsDefaultInfo: true,

            //当点击分页组件的按钮,或输入内容时,我们会触发此回调函数
            onChangePage: function (event, data) {
                //参数1,当前页
                //参数2,每页条数
                searchBtn(data.currentPage, data.rowsPerPage);
            }
        });
        reverseAll();

    })

}

function ActivityDeletes() {

    if (confirm("您确定要删除嘛?")) {

        var codes = [];

        if ($("input[name = 'ck']:checked") < 1) {
            alert("请正确勾选需要删除的内容!");
            return;
        }

        $("input[name = 'ck']:checked").each(function () {
            codes.push($(this).val());
        })

        var data = {"codes": codes}
        ajaxUtil("workbench/activity/delete.do", data, function (result) {


            if (result.code === 0) {
                window.location.href = "workbench/activity/toIndex.do";
            } else {
                alert(result.msg);
            }


        })
    }
}