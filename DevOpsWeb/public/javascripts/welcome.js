var hostname = 'localhost:4444';

var login = function() {
    var usr = $('#usrLogin').val();
    var pwd = $('#pwdLogin').val();
    if(!usr || !pwd) {
        alert('请输入用户名/密码！');
        return;
    }
    $.post('/fapi/login',
    {
        username:usr,
        password:pwd
    },
    function(data, status){
        if(status=='success' && data.status==true) {
            window.location.href = data.redirect;
        } else {
            alert('登录失败，请检查密码。');
        }
    });
}

var register = function() {
    var usr = $('#usrRegister').val();
    var pwd1 = $('#pwd1Register').val();
    var pwd2 = $('#pwd2Register').val();
    var type = $("input[name='typeOpt']:checked").val()=='student'?0:1;
    var email = $('#emailRegister').val();
    var name = $('#nameRegister').val();
    var id = $('#idRegister').val();
    if(!(usr&&pwd1&&pwd2&&email&&name&&id)) {
        alert('请输入全部字段！');
        return;
    }
    if(pwd1!=pwd2) {
        alert('两次输入的密码不一致，请检查。');
        return;
    }
    $.post('http://'+hostname+'/identification/register',
    {
        username:usr,
        password:pwd1,
        role:type,
        email:email,
        name:name,
        userId:id
    },
    function(data, status){
        if(status=='success') {
            if(data.success) {
                alert('注册成功！');
            } else {
                alert('注册失败：'+data.msg);
            }
        } else {
            alert('暂时无法处理注册请求。');
        }
    });
}

$(document).ready(function(){
    $('#loginBtn').click(login);
    $('#registerBtn').click(register);
});