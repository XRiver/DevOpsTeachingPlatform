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

var out = {
    getUserInfo: getUserInfo,
    getProjectInfo: getProjectInfo
}

module.exports = out;