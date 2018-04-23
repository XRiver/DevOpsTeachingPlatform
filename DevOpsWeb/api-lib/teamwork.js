var rp = require('request-promise');
var help = require('./help');

/**
 * @param username 用户名
 * @returns UserVO(对照teamwork组API文档) 
 */
var getUserInfo = function(username) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/user/getInfo",
        method:"GET",
        qs:{
            username:username
        },
        json:true
    }).then(function(body){
        ret = body;
    });
    return ret;
}

/**
 * @param projectId 
 * @returns ProjectVO 
 */
var getProjectInfo = function(projectId) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/"+projectId+"/getInfo",
        method:"GET",
        json:true
    }).then(function(body){
        ret = body;
    });
    return ret;
}

var validateAccount = function(usr,pwd) {
    // if(usr=='123'&&pwd=='456') {
    //     return true;
    // } 
    var ret = false;
    rp({
        uri:'http://'+help.teamIp+'/identification/login',
        method:'POST',
        body:{
            username:usr,
            password:pwd
        },
        json:true
    })
    .then(function(body){
        if(body.success) {
            ret = true;
        }
    })
    .catch(function(err){
        console.log(err);
    });
    return ret;
}

/**
 * 
 * @param {} usr 
 * @param {} oldPwd 
 * @param {} newPwd 
 * @returns {} {status:bool, msg:"..."}
 */
var modPassword = function(usr, oldPwd, newPwd) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/user/modifyPassword",
        method:"POST",
        body:{
            username:usr,
            oldPassword:oldPwd,
            newPassword:newPwd
        },
        json:true
    }).then(function(body){
        ret = {
            status:body.success,
            msg:body.msg
        };
    })
    return ret;
}

var out = {
    getUserInfo: getUserInfo,
    getProjectInfo: getProjectInfo,
    validateAccount: validateAccount,
    modPassword: modPassword
}

module.exports = out;