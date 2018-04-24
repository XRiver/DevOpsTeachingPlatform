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

/**
 * @returns {} {status:bool, msg:"..."}
*/
var modUserInfo = function(usr, email, realName, id) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/user/modify",
        method:"POST",
        body:{
            username:usr,
            email: email,
            name: realName,
            userId: id
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

/**
 * 
 * @param {*} usr 
 * @returns List<GroupVO>
 */
var getGroupsByUsr = function(usr) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/group/getGroupList",
        method:"GET",
        qs: {
            username: usr
        },
        json:true
    }).then(function(body){
        ret = body;
    });
    return ret;
}

var delGroup = function(teamId) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/"+teamId+"/delete",
        method:"POST",
        body:{
            groupId: teamId
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

/**
 * @returns {} {status: str}
 * @param {*} gName 
 * @param {*} gInfo 
 * @param {*} members 
 * @param {*} managerId 
 * @param {*} creatorId 
 */
var createGroup = function(gName, gInfo, members, managerId, creatorId) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/group/create",
        method:"POST",
        body:{
            groupName: gName,
            info: gInfo,
            memberList: members,
            managerId: managerId,
            creatorId: creatorId
        },
        json:true
    }).then(function(body){
        if(body.groupName) {
            ret = {
                status:'success',
            };
        } else {
            ret = {
                status:'fail',
            }
        }
        
    })
    return ret;
}

var getProjectList = function(usr) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/project/getProjectList",
        method:"GET",
        qs: {
            username: usr
        },
        json:true
    }).then(function(body){
        ret = body;
    });
    return ret;
}

var getProjectInfo = function(pid) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/project/"+pid+"/getInfo",
        method:"GET",
        json:true
    }).then(function(body){
        ret = body;
    });
    return ret;
}

var createProjectWithGroup = function(pName, pInfo, managerUsrs, gId, creatorUsr) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/project/createWithGroup",
        method:"POST",
        body:{
            projectName: pName,
            info: pInfo,
            managerList: managerUsrs,
            groupId: gId,
            creatorName: creatorUsr
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

var createProjectWithoutGroup = function(pName, pInfo, managerUsrs, memberUsrs, creatorUsr) {
    var ret = null;
    rp({
        uri:"http://"+help.teamIp+"/project/createWithoutGroup",
        method:"POST",
        body:{
            projectName: pName,
            info: pInfo,
            managerList: managerUsrs,
            memberList: memberUsrs,
            creatorName: creatorUsr
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
    modPassword: modPassword,
    modUserInfo: modUserInfo,
    getGroupsByUsr: getGroupsByUsr,
    delGroup: delGroup,
    createGroup: createGroup,
    getProjectList: getProjectList,
    createProjectWithGroup: createProjectWithGroup,
    createProjectWithoutGroup: createProjectWithoutGroup
}

module.exports = out;