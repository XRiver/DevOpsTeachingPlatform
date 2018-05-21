/**
 * Created by Administrator on 2018/4/23.
 */
var rp = require('request-promise');
var help = require('./help');
var http = require('http');
var url = require('url');
var session = require('express-session')

module.exports.getCommits = function (req, res, next) {
    var projectid=req.session.projectid;
    var data="";
    http.get('http://'+help.gitIp+'/repository/allcommit/'+projectid,function (resp) {
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
            var commits = JSON.parse(data);
            //res.send(JSON.stringify(buffers));
            res.render('commitlist',{
                title:"express",
                projectid:projectid,
                realname:req.session.user.username,
                commits:commits
            })
        });
    }).on('error', function(err) {
        console.log('error ' + err);
    });
};

module.exports.getMerges = function (req, res, next) {
    var projectid=req.session.projectid;
    if(projectid==null){
        res.redirect("/login");
    }

    var data = "";
    http.get('http://' + help.gitIp + '/repository/allmergerequest/' + projectid, function (resp) {
        var buffers = [];
        resp.on('data', function (chunk) {
            buffers.push(chunk);
        });

        resp.on('end', function (chunk) {
            var wholeData = Buffer.concat(buffers);
            var dataStr = wholeData.toString('utf8');
            // console.log('content'+wholeData);
            data = wholeData;
            console.log('data' + data);
            var merges = JSON.parse(data);
            //res.send(JSON.stringify(buffers));
            res.render('mergerequest', {
                title: "express",
                projectid: projectid,
                realname: "yhqqq",
                merges: merges

            })
        });
    }).on('error', function (err) {
        console.log('error ' + err);
    });

};

module.exports.getTree = function (req, res, next) {
    var projectid=req.session.projectid;
    if(projectid==null){
        res.redirect("/login");
    }
    var urlobj = url.parse(req.url,true);
    // console.log("urlobj: "+urlobj.query["projectid"]);
    var path=urlobj.query["path"];

    console.log("api repository tree path : "+path);

    var myurl='http://'+help.gitIp+'/file/tree/'+projectid;

    if(path!=null){
        myurl=myurl+'?path='+path;
    }
    var data="";
    http.get(myurl,function (resp) {
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
            var trees = JSON.parse(data);
            //console.log('trees : '+trees[0].id);
            //res.send(JSON.stringify(buffers));

            res.render('index',{
                projectid:req.session.projectid,
                realname:req.session.user.username,
                project:req.session.project,
                trees:trees
            })

        });
    }).on('error', function(err) {
        console.log('error ' + err);
    });

    // res.render('index',{
    //     title:"express",
    //     projectid:req.session.projectid,
    //     realname:"yhqqq",
    //     project:req.session.project
    //
    // })
};