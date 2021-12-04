function Ajaxlogin(loginAct, loginPwd, autoLogin) {
    $.ajax({
        url: "settings/user/login.do",
        type: "post",
        data: {"loginAct": loginAct, "loginPwd": loginPwd, "autoLogin": autoLogin},
        dataType: "json",
        success: (function (data) {
            if (data.code === 0) {
                document.location.href="workbench/toIndex.do"
            } else {
                $("#msg").html(data.msg);
            }
        })
    })
}