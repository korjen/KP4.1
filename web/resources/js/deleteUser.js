function RestDelete(id) {
    $.ajax({
        type: "DELETE",
        url: "deleteUser/" + id,
        // success: window.location.href = "deleteUser/" + id
        success: $('#' + id).remove()
    })
    // $('#' + id).remove();
}