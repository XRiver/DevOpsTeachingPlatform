function chPwd(){
    var usr = $('#usrInput').val();
    var oldpwd = $('#oldPwdInput').val();
    var newpwd1 = $('#newPwdInput1').val();
    var newpwd2 = $('#newPwdInput2').val();
    if(!(usr&&oldpwd&&newpwd1&&newpwd2)) {
        alert('请输入全部字段！');
        return;
    }
    if(newpwd1!=newpwd2) {
        alert('请正确填写重复新密码！');
        return;
    }
    $.post('/fapi/modPassword',
    {
        usr: usr,
        oldPwd: oldpwd,
        newPwd: newpwd1
    },
    function(data, status){
        if(status=="success") {
            if(data.status==true) {
                alert('密码修改成功');
            } else {
                alert('密码修改失败:'+data.msg);
            }
        } else {
            alert('密码修改失败。');
        }
    });
}