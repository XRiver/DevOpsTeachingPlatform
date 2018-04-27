function execAll(id){
    if(!(id)) {
        alert('id不存在！');
        return;
    }
    $.ajax({
        url : '/unittest/test/execute-all/'+id,
        data : {
        },
        dataType : 'json',
        type : 'post',
        error:function(err){

        }
    });

}