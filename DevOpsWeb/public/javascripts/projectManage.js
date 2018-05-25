var activateProject = function(pid) {
    $.post('/fapi/activateProject',
    {
        projectId: pid
    },
    function(data, status){
        if(status=="success") {
            if(data.status==true) {
                alert('激活成功！');
                document.location.reload(true);
            } else {
                alert('激活失败。');
            }
        } else {
            alert('暂时无法连接至服务器。');
        }
    });
}

var createProject = function() {
    /*
    var pName = $('#projectNameInput').val();
    var pInfo = $('#projectInfoInput').val();
    var managers = $('#managersInput').val().split("\\s+");
    var groupId = $('#groupIdInput').val();
    $.post('/fapi/createProjectWithGroup',
    {
        projectName: pName,
        projectInfo: pInfo,
        managers: managers,
        groupId: gid
    },
    function(data, status) {
        if(status=="success") {
            if(data.status==true) {
                alert('创建成功！');
                document.location.reload(true);
            } else {
                alert('创建失败:'+data.msg);
            }
        } else {
            alert('暂时无法连接至服务器。');
        }
    })
    */
    $('#projectList').html($('#projectList').html()+
        '<tr><td>2</td><td>project2</td><td>软工2课程作业</td><td>徐江河</td> \
        <td><button type="button" onclick="activateProject(2)" class="btn btn-outline-success btn-sm">激活项目 </button></td></tr>');
    setTimeout(function(){
        alert('添加成功！')
    },1000)
}

var showProjectDetail = function() {
    $('#projectDetailPane').show();
}