var modUserInfo = function() {
    var email = $('#emailInput').val();
    var realName = $('#realNameInput').val();
    var id = $('#idInput').val();
    if(!(email&&realName&&id)) {
        alert('请输入全部字段！');
        return;
    }
    $.post('/fapi/modUserInfo',
    {
        email: email,
        realName: realName,
        id: id
    },
    function(data, status){
        if(status=="success") {
            if(data.status==true) {
                alert('个人信息修改成功');
            } else {
                alert('修改失败:'+data.msg);
            }
        } else {
            alert('暂时无法连接至服务器。');
        }
    });
}