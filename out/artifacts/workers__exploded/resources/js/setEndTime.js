function setEnd(time) {
    var time = document.getElementById('endTime').value;
    time = time+":00";
    var contextPath = "${pageContext.request.contextPath}";
    $.ajax({
        type: "get",
        url: "setEndTime/" + time,
        data: time,
        success: window.location.href = "setEndTime/" + time
    });
    location.reload();
}