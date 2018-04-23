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

router.post('modPassword', function(req, res, next) {
    var usr = req.body.usr, oldPwd = req.body.oldPwd, newPwd = req.body.newPwd;
    if(usr && oldPwd && newPwd) {
        var result = teamApi.modPassword(usr,oldPwd,newPwd);
        res.send(result);
    }
});



module.exports = router;