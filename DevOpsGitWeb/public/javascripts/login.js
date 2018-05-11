/**
 * Created by Administrator on 2018/4/22.
 */
var login =function () {
    // var username = document.getElementById("username").value;
    // var password = $('#password').val();
    // var projectid = document.getElementById("projectid").value;
    //
    // var pwd = $('#password').val();
    var userid=$('#username').val();
    var password=$('#password').val();
    var projectid=$('#projectid').val();
    // alert("username:"+username+" password:"+password+" projectid:"+projectid);
    // $.get("/group/project",
    //     {projectid:projectid},
    //     function (data, status) {
    //         console.log("data: "+data);
    //         console.log("status: "+status);
    //     })
    window.location.href="/group/project?projectid="+projectid+"&userid="+userid;

};

var logout=function () {
    console.log("logout!!");
    window.location.href="/login";
};

var repository=function () {
    console.log("repository info");
    window.location.href="/group/project";
};
var commit=function () {
    console.log("commit info");
    window.location.href="/repository/commit";
}
var merge=function () {
    console.log("merge info");
    window.location.href="/repository/merge";
};
var sshkey=function () {
    console.log("sshkey info");
    window.location.href="/user/sshkey";
};

$(document).ready(function(){
    $('#loginBtn').click(login);
    $('#logoutBtn').click(logout);
    $('#repositoryBtn').click(repository);
    $('#commitBtn').click(commit);
    $('#mergeBtn').click(merge);
    $('#keyBtn').click(sshkey);

});

// $(document).ready(function () {
//
// })