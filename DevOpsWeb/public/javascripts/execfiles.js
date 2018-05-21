function execFile(id){
    var files=new Array();
    var list = document.getElementsByName('file');
    for(var i=0;i<list.length;i++){
        if(list[i].checked){
            files.push(list[i].value);
        }
    }
    var username="example";
    $.ajax({
        url : '/unittest/test/execute/'+id,
        data : {
            id:id,
            file:JSON.stringify(files),
            username:username
        },
        dataType : 'json',
        type : 'post',
        error:function(data){

        }
    });



}