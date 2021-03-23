function setStart() {
    var time = document.getElementById('startTime').value;;
    time = time+":00";
    var contextPath = "${pageContext.request.contextPath}";
    $.ajax({
        type: "get",
        url: "setStartTime/" + time,
        data: time,
        success: window.location.href = "setStartTime/" + time
    });
    location.reload();
}