var rp = require('request-promise');
var help = require('./help');

/**
 * @param username 用户名
 * @returns UserVO(对照teamwork组API文档) 
 */
var getUserInfo = function(username) {
    return rp({
        uri:"http://"+help.teamIp+"/user/getInfo",
        method:"GET",
        qs:{
            username:username
        },
        json:true
    }).then(function(body){
        return body;
    });
}

var register = function(usr, pwd, role, email, name, uid) {
    return rp({
        uri:'http://'+help.teamIp+'/identification/signUp',
        method:'POST',
        form:{
            username:usr,
            password:pwd,
            role:role,
            email:email,
            name:name,
            userId:uid
        },
        json:true
    })
    .then(function(body){
        console.log(body)
        return {
            status:body.success,
            msg:body.msg
        };
    })
    .catch(function(err) {
        console.log(err)
        return {
            status:false,
            msg:"团队服务出现未知错误"
        };
    });
}

var validateAccount = function(usr,pwd) {
    return rp({
        uri:'http://'+help.teamIp+'/identification/logIn',
        method:'POST',
        form:{
            username:usr,
            password:pwd
        },
        json:true
    })
    .then(function(body){
        console.log(body)
        if(body.success) {
            return true;
        } else {
            return false;
        }
    })
    .catch(function(err){
        console.log(err);
        return false;
    });
}

/**
 * 
 * @param {} usr 
 * @param {} oldPwd 
 * @param {} newPwd 
 * @returns {} {status:bool, msg:"..."}
 */
var modPassword = function(usr, oldPwd, newPwd) {
    return rp({
        uri:"http://"+help.teamIp+"/user/modifyPassword",
        method:"POST",
        body:{
            username:usr,
            oldPassword:oldPwd,
            newPassword:newPwd
        },
        json:true
    }).then(function(body){
        return {
            status:body.success,
            msg:body.msg
        };
    })
}

/**
 * @returns {} {status:bool, msg:"..."}
*/
var modUserInfo = function(usr, email, realName, id) {
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
        return {
            status:body.success,
            msg:body.msg
        };
    })
}

/**
 * 
 * @param {*} usr 
 * @returns List<GroupVO>
 */
var getGroupsByUsr = function(usr) {
    rp({
        uri:"http://"+help.teamIp+"/"+usr+"/getMyGroups",
        method:"GET",
        json:true
    }).then(function(body){
        return body;
    });
}

var delGroup = function(teamId, requesterName) {
    rp({
        uri:"http://"+help.teamIp+"/"+teamId+"/deleteGroup",
        method:"POST",
        body:{
            groupId: teamId,
            memberName: requesterName
        },
        json:true
    }).then(function(body){
        return {
            status:body.success,
            msg:body.msg
        };
    })
}

/**
 * @returns {} {status: str}
 * @param {*} gName 
 * @param {*} gInfo 
 * @param {*} members 
 * @param {*} managerId 
 * @param {*} creatorId 
 */
var createGroup = function(gName, gInfo, members, creatorName) {
    return rp({
        uri:"http://"+help.teamIp+"/group/createGroup",
        method:"POST",
        body:{
            groupName: gName,
            info: gInfo,
            memberList: members,
            visibility: "public", //TODO 收集输入？ private/internal/public
            creatorName: creatorName
        },
        json:true
    }).then(function(body){
        if(body.groupName) {
            return {status:true};
        } else {
            return {status:false}
        }
    })
}

var getProjectList = function(usr) {
    return rp({
        uri:"http://"+help.teamIp+"/project/getProjectList",
        method:"GET",
        qs: {
            username: usr
        },
        json:true
    }).then(function(body){
        return body;
    });
}

var getProjectInfo = function(pid) {
    return rp({
        uri:"http://"+help.teamIp+"/project/"+pid+"/getInfo",
        method:"GET",
        json:true
    }).then(function(body){
        return body;
    });
}

var createProjectWithGroup = function(pName, pInfo, managerUsrs, gId, creatorUsr) {
    return rp({
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
        return {
            status:body.success,
            msg:body.msg
        };
    })
}

var createProjectWithoutGroup = function(pName, pInfo, managerUsrs, memberUsrs, creatorUsr) {
    return rp({
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
        return {
            status:body.success,
            msg:body.msg
        };
    })
}

var out = {
    getUserInfo: getUserInfo,
    register: register,
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