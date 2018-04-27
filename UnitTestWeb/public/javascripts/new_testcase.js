function newTestcase(){
    var caseId = $('#caseId').val();
    var name = $('#testname').val();
    var info = $('#info').val();
    var file= $('#file').val();
    var testId=$('#testId').val();
    if(!(testId&&name&&caseId&&name)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : 'http://localhost:8701/testcase/create',
        data : {
            caseId:caseId,
            testId:testId,
            name:name,
            info:info,
            file:file
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('创建成功！');
        }
    });

}

function delTestcase(id){
    if(!(id)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : 'http://localhost:8701/testcase/delete',
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

function modCasePanel(id,caseid,name,file,info){
    document.getElementById("modlabel").innerHTML="修改用例";
    document.getElementById("newCaseBtn").innerHTML="修改";
    document.getElementById("newCaseBtn").setAttribute("onClick","modTestcase("+id+")");
    document.getElementById("caseId").setAttribute("value",caseid);
    document.getElementById("testname").setAttribute("value",name);

    if(file!=null&&file!="null"){
        document.getElementById("file").setAttribute("value",file);
    }
    if(info!=null&&info!="null"){
        document.getElementById("info").setAttribute("value",info);
    }


}


function modTestcase(id){
    var caseId = $('#caseId').val();
    var name = $('#testname').val();
    var info = $('#info').val();
    var file= $('#file').val();
    if(!(id&&name&&caseId&&name)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : 'http://localhost:8701/testcase/update',
        data : {
            caseId:caseId,
            id:id,
            name:name,
            info:info,
            file:file
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('修改成功！');
        }
    });

}