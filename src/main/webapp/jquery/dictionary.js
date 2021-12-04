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

function reverseAll() {

    $("input[name='ck']").click(function () {
        if ($("input[name='ck']:checked").length === $("input[name='ck']").length) {
            $("#selectAll").prop('checked', true);
        } else {
            $("#selectAll").prop('checked', false);
        }
    })
}