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
    /*
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
    */

    $("#teamList").html($("#teamList").html()+"<tr><td>2</td><td>软工小队2</td><td>无</td><td>徐江河</td><td><button type='button' onclick='delGroup(2)' class='btn btn-outline-warning btn-sm'>删除团队 </button></td></tr>");
    setTimeout(function(){
        alert('添加成功！')
    },1000)
}

var showGroupDetail = function() {
    $('#grpDetailPane').show();
}