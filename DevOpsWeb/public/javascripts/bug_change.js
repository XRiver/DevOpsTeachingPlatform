function BugChange(){
    var state = $('#state').val();
    var id= $('#bugId').val();
    var info=$('#info').val();
    var manager=' ';
    if(!state) {
        alert('请输入全部字段！');
        return;
    }
    $.ajax({
        url : '/unittest/bug/change',
        data : {
            id:id,
            info:info,
            state:state,
        },
        dataType : 'json',
        type : 'post',
        error:function(err){
            location.reload(true);
            alert('状态更改成功！');
        }
    });

}