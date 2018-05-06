/**
 * Created by Administrator on 2018/4/23.
 */

var rp = require('request-promise');
var help = require('./help');
var http = require('http');
var url = require('url');
var session = require('express-session');

module.exports.getUser=function (req, res, next) {
    if(req.session.userid==null){
        var urlobj = url.parse(req.url,true);
        console.log("urlobj: userid"+urlobj.query["userid"]);
        var userid=urlobj.query["userid"];
        req.session.userid=userid;
    }
    var userid=req.session.userid;

    var data="";
    //todo userid-projectid
    http.get('http://'+help.gitIp+'/user/'+userid,function (resp) {
        var buffers = [];
        resp.on('data', function(chunk) {
            buffers.push(chunk);
        });

        resp.on('end', function(chunk) {
            var wholeData = Buffer.concat(buffers);
            var dataStr = wholeData.toString('utf8');
            // console.log('content'+wholeData);
            data=wholeData;
            console.log('data'+data);
            var user = JSON.parse(data);
            //res.send(JSON.stringify(buffers));
            req.session.user=user;
            next();
        });
    }).on('error', function(err) {
        console.log('error ' + err);
    });

};

module.exports.getSSHkey=function (req, res, next) {
    var userid=req.session.userid;
    if(userid==null){
        res.redirect("/login");
    }

    var data="";
    //todo userid-projectid
    http.get('http://'+help.gitIp+'/sshkey/'+userid,function (resp) {
        var buffers = [];
        resp.on('data', function(chunk) {
            buffers.push(chunk);
        });

        resp.on('end', function(chunk) {
            var wholeData = Buffer.concat(buffers);
            var dataStr = wholeData.toString('utf8');
            // console.log('content'+wholeData);
            data=wholeData;
            console.log('data'+data);
            var keys = JSON.parse(data);
            //res.send(JSON.stringify(buffers));
            res.render('sshkey',{
                title:"express",
                projectid:req.session.projectid,
                realname:req.session.user.username,
                keys:keys

            })
        });
    }).on('error', function(err) {
        console.log('error ' + err);
    });
};