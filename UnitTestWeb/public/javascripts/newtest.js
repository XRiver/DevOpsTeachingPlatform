function newTest(){
    var testId = $('#testId').val();
    var name = $('#testname').val();
    var lan = $('#lan').val();
    var branch= $('#branch').val();
    var src=$('#path').val();
    var project=$('#projectId').val();
    console.log(lan);
    if(!(lan&&testId&&name&&branch&&name)) {
        alert('请输入全部字段！');
        return;
    }


    $.ajax({
        url : '/unittest/'+project+'/createtest',
        data : {
            projectId:project,
            testId:testId,
            name:name,
            lan:lan,
            branch:branch,
            src:src
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('创建成功！');
        }
    });

}


function delTest(id){
    if(!(id)) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : '/unittest/test/delete',
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

function modTestPanel(id,testid,name,lan,branch,path){
    document.getElementById("modlabel").innerHTML="修改测试";
    document.getElementById("newTestBtn").innerHTML="修改";
    document.getElementById("newTestBtn").setAttribute("onClick","modTest("+id+")");
    document.getElementById("testId").setAttribute("value",testid);
    document.getElementById("testname").setAttribute("value",name);
    document.getElementById("branch").setAttribute("value",branch);

    if(path!=null&&path!="null"){
        document.getElementById("path").setAttribute("value",path);
    }
    var language=document.getElementById("lan");
    for(var i=0; i<language.options.length; i++) {
        if (language.options[i].value == lan) {
            language.options[i].selected = true;
            break;
        }
    }

}

function modTest(id){
    var testId = $('#testId').val();
    var name = $('#testname').val();
    var lan = $('#lan').val();
    var branch= $('#branch').val();
    var src=$('#path').val();
    console.log(lan);
    if(!(lan&&testId&&name&&branch&&name)) {
        alert('请输入全部字段！');
        return;
    }


    $.ajax({
        url : '/unittest/test/update',
        data : {
            id:id,
            testId:testId,
            name:name,
            lan:lan,
            branch:branch,
            src:src
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('修改成功！');
        }
    });

}