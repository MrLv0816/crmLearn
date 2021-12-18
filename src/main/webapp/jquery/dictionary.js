//多选框一键全选与反选
function selectAll() {
    $("#selectAll").click(function () {
        if ($(this).prop('checked')) {

            $("input[name = 'ck']").each(function () {
                $(this).prop('checked', true);
            })

        } else {

            $("input[name = 'ck']").each(function () {
                $(this).prop('checked', false);
            })

        }
    })

}

//全选框反选操作
function reverseAll() {

    $("input[name = 'ck']").click(function () {

        if ($("input[name='ck']:checked").length === $("input[name='ck']").length) {
            $("#selectAll").prop('checked', true);
        } else {
            $("#selectAll").prop('checked', false);
        }
    })
}


//前往编辑页面,附带将要修改数据的 唯一标识
function toEdit() {
    var code = $("input[type=checkbox]:checked")
    if (code.length === 1) {
        window.location.href = "settings/dictionary/type/toEdit.do?code=" + code[0].value
    } else {
        alert("请正确勾选!")
    }
}