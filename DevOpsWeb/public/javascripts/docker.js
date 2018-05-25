var showDeployInfo = function() {
    ws = new WebSocket("ws://139.219.66.203:8887/deploy/startTask/16/river")
    ws.onmessage = function(evt) {

        $("#deployInfoArea").text($("#deployInfoArea").text()+'\n'+evt.data)
    };
    ws.onclose = function(evt) {

        $("#deployInfoArea").text($("#deployInfoArea").text()+'\n'+evt.data)
    };
    ws.onopen = function(evt) {

        $("#deployInfoArea").text(evt.data)
    };
}

var showPanes = function() {
    $('#taskPane').show();
    $('#imagePane').show();
}

var testHost = function() {
    setTimeout(function(){
        alert('主机可用，联通成功。')
    },1000)
}

var showTaskDetail = function() {
    $('#taskDetailPane').show();
}