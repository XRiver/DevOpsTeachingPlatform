var express = require('express');
var rp = require('request-promise');
var help = require('../api-lib/help');

var router = express.Router();
router.post('/login', function(req, res, next) {
    var usr = req.body.username;
    var pwd = req.body.password;
    if(usr && pwd && validateAccount(usr, pwd)) {
        var sess = req.session;
        sess.usr = usr;
        sess.logined = true;
        res.send({
            status:0,
            redirect:'/index?p=welcome'
        });
    } else {
        res.send({
            status:1
        });
    }
});

function validateAccount(usr,pwd) {
    // if(usr=='123'&&pwd=='456') {
    //     return true;
    // } else {
    //     return false;
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

module.exports = router;