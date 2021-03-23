function RestProfile(id) {
    var contextPath = "${pageContext.request.contextPath}";
    $.ajax({
        type: "get",
        url: "userProfile/" + id,
        data: id,
        success: window.location.href = "userProfile/" + id
    })
}