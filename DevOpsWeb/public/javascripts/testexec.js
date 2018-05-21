function execAll(id){
    if(!(id)) {
        alert('id不存在！');
        return;
    }
    document.getElementById("execTd"+id).innerHTML="<img src='/images/loading1.gif' alt='执行中'>";
    document.getElementById("execBtn"+id).setAttribute("type","hidden");

/*
    $.ajax({
        url : '/unittest/test/execute-all/'+id,
        data : {
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
        }
    });
*/

}