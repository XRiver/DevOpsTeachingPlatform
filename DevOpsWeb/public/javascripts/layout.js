var logout = function(){
    $.get('/fapi/logout', function(data, status) {
        if(status=='success') {
            window.location.href = data.redirect;
        } else {
            alert('暂时无法连接至服务器。')
        }
    });
}