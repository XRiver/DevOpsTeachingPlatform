var express = require('express');
var rp = require('request-promise');
var help = require('../api-lib/help');
var teamApi = require('../api-lib/teamwork');

var router = express.Router();
router.post('/login', async function(req, res, next) {
    var usr = req.body.username;
    var pwd = req.body.password;
    if(usr && pwd && await teamApi.validateAccount(usr, pwd)) {
        var sess = req.session;
        sess.usr = usr;
        sess.username = usr;
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

router.post('/register', async function(req, res, next) {
    var username = req.body.username,
        password = req.body.password,
        role = req.body.role,
        email = req.body.email,
        name = req.body.name,
        userId = req.body.userId;
    var ret = await teamApi.register(username, password, role, email, name, userId);
    res.send(ret);
})

router.post('/modPassword', async function(req, res, next) {
    var usr = req.body.usr, oldPwd = req.body.oldPwd, newPwd = req.body.newPwd;
    if(usr && oldPwd && newPwd) {
        var result = await teamApi.modPassword(usr,oldPwd,newPwd);
        res.send(result);
    }
});

router.post('/modUserInfo', async function(req, res, next) {
    var usr = req.session.usr, email = req.body.email, name = req.body.realName, id = req.body.id;
    if(usr && email && name && id) {
        var result = await teamApi.modUserInfo(usr, email, name, id);
        res.send(result);
    }
})

router.get('/logout', async function(req, res, next) {
    var sess = req.session;
    sess.logined = false
    res.send({
        redirect:'/'
    })
})

router.post('/delGroup', async function(req, res, next) {
    ret = await teamApi.delGroup(req.body.groupId, req.session.usr);
    res.send(ret);
})

router.post('/createGroup', async function(req, res, next) {
    var gName = req.body.groupName,
        gInfo = req.body.groupInfo,
        members = req.body.members,
        creatorName = req.session.usr;
    var ret = await teamApi.createGroup(gName, gInfo, members, managerId, creatorName);
    res.send(ret);
})

router.post('/activateProject', async function(req, res, next) {
    var sess = req.session;
    sess.activeProjectId = req.body.projectId;
    sess.activeProjectInfo = await teamApi.getProjectInfo(sess.activeProjectId);
    res.send({
        status:true
    });
})

router.post('/createProjectWithGroup', async function(req, res, next) {
    var sess = req.session;
    var pName = req.body.projectName,
        pInfo = req.body.projectInfo,
        managerUsrs = req.body.managers,
        gId = req.body.groupId,
        creatorUsr = sess.usr;
    var ret = await teamApi.createProjectWithGroup(pName, pInfo, managerUsrs, gId, creatorUsr);
    res.send(ret);
})

module.exports = router;