var delGroup = function(gid) {
    $.post('/fapi/delGroup',
    {
        groupId: gid
    },
    function(data, status){
        if(status=="success") {
            if(data.status==true) {
                alert('删除成功！');
                document.location.reload(true);
            } else {
                alert('删除失败:'+data.msg);
            }
        } else {
            alert('暂时无法连接至服务器。');
        }
    });
}

var createGroup = function() {
    var gName = $('#groupNameInput').val();
    var gInfo = $('#infoInput').val();
    var members = $('#memberInput').val().split("\\s+");
    $.post('/fapi/createGroup',
    {
        groupName: gName,
        groupInfo: gInfo,
        menbers: members,
        managerId: managerId
    },
    function(data, status) {
        if(status=="success") {
            if(data.status) {
                alert('创建成功！');
                document.location.reload(true);
            } else {
                alert('创建失败.');
            }
        } else {
            alert('暂时无法连接至服务器。');
        }
    })
}