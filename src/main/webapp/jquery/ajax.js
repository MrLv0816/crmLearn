function Ajaxlogin(loginAct, loginPwd) {
    $.ajax({
        url: "settings/user/login.do",
        type: "post",
        data: {"loginAct": loginAct, "loginPwd": loginPwd},
        dataType: "json",
        success: (function (data) {
            if (data.code === 0){
                $("#msg").html(data.msg);
            }else{
                $("#msg").html(data.msg);
            }
        })
    })
}