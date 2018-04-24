var express = require('express');
var rp = require('request-promise');
var help = require('../api-lib/help');
var teamApi = require('../api-lib/teamwork');

var router = express.Router();
router.post('/login', function(req, res, next) {
    var usr = req.body.username;
    var pwd = req.body.password;
    if(usr && pwd && teamApi.validateAccount(usr, pwd)) {
        var sess = req.session;
        sess.usr = usr;
        sess.logined = true;
        res.send({
            status:true,
            redirect:'/index?p=welcome'
        });
    } else {
        res.send({
            status:false
        });
    }
});

router.post('/modPassword', function(req, res, next) {
    var usr = req.body.usr, oldPwd = req.body.oldPwd, newPwd = req.body.newPwd;
    if(usr && oldPwd && newPwd) {
        var result = teamApi.modPassword(usr,oldPwd,newPwd);
        res.send(result);
    }
});

router.post('/modUserInfo', function(req, res, next) {
    var usr = req.session.usr, email = req.body.email, name = req.body.realName, id = req.body.id;
    if(usr && email && name && id) {
        var result = teamApi.modUserInfo(usr, email, name, id);
        res.send(result);
    }
})

router.get('/logout', function(req, res, next) {
    var sess = req.session;
    sess.logined = false
    res.send({
        redirect:'/'
    })
})

router.post('/delGroup', function(req, res, next) {
    ret = teamApi.delGroup(req.body.groupId);
    res.send(ret);
})

router.post('/createGroup', function(req, res, next) {
    var gName = req.body.groupName,
        gInfo = req.body.groupInfo,
        members = req.body.members,
        managerId = req.body.managerId,
        creatorId = teamApi.getUserInfo(req.session.usr).userId;
    var ret = teamApi.createGroup(gName, gInfo, members, managerId, creatorId);
    res.send(ret);
})

router.post('/activateProject', function(req, res, next) {
    var sess = req.session;
    sess.activeProjectId = req.body.projectId;
    sess.activeProjectInfo = teamApi.getProjectInfo(sess.activeProjectId);
    res.send({
        status:true
    });
})

router.post('/createProjectWithGroup', function(req, res, next) {
    var sess = req.session;
    var pName = req.body.projectName,
        pInfo = req.body.projectInfo,
        managerUsrs = req.body.managers,
        gId = req.body.groupId,
        creatorUsr = sess.usr;
    var ret = teamApi.createProjectWithGroup(pName, pInfo, managerUsrs, gId, creatorUsr);
    res.send(ret);
})

module.exports = router;