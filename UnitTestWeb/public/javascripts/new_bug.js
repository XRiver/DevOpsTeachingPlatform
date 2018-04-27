function newBug(){
    var name = $('#bugname').val();
    var imp = $('#imp').val();
    var info = $('#info').val();
    var projectId= $('#projectId').val();
    if(!(projectId&&name&&imp)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : 'http://localhost:8701/bug/create',
        data : {
            projectId:projectId,
            name:name,
            info:info,
            imp:imp
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('创建成功！');
        }
    });

}


function delBug(id){
    if(!(id)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : 'http://localhost:8701/bug/delete',
        data : {
            id:id
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('删除成功！');
        }
    });

}

function modBugPanel(id,name,importance,info){
    document.getElementById("modlabel").innerHTML="修改缺陷";
    document.getElementById("newbugBtn").innerHTML="修改";
    document.getElementById("newbugBtn").setAttribute("onClick","modBug("+id+")");
    document.getElementById("bugname").setAttribute("value",name);

    if(info!=null&&info!="null"){
        document.getElementById("info").setAttribute("value",info);
    }
    var imp=document.getElementById("imp");
    for(var i=0; i<imp.options.length; i++) {
        if (imp.options[i].value == importance) {
            imp.options[i].selected = true;
            break;
        }
    }

}


function modBug(id){
    var name = $('#bugname').val();
    var imp = $('#imp').val();
    var info = $('#info').val();
    if(!(projectId&&name&&imp)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : 'http://localhost:8701/bug/update',
        data : {
            id:id,
            name:name,
            info:info,
            imp:imp
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('更改成功！');
        }
    });

}
