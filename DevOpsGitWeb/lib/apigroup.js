var rp = require('request-promise');
var help = require('./help');
var http = require('http');
var url = require('url');
var session = require('express-session');

module.exports.getProject=function (req,res,next) {
    var ret = null;
    // console.log("url: "+req.url);
    if(req.session.projectid==null){
        var urlobj = url.parse(req.url,true);
        // console.log("urlobj: "+urlobj.query["projectid"]);
        var projectid=urlobj.query["projectid"];
        req.session.projectid=projectid;
    }

    var projectid=req.session.projectid;
    console.log("function getProjet to do")

    // rp.get("http://"+help.gitIp+"/project/"+projectid)
    //     .on('response', function(resp) {
    //         console.log(resp);
    //     res.render('index',{
    //         title:"express",
    //         projectid:projectid
    //     })
    // });

    // var options = {
    //     uri: 'http://'+help.gitIp+'/project/'+projectid,
    //     // qs: {
    //     //     access_token: 'xxxxx xxxxx' // -> uri + '?access_token=xxxxx%20xxxxx'
    //     // },
    //     pote:3000,
    //     headers: {
    //         'User-Agent': 'Request-Promise'
    //     },
    //     json: true // Automatically parses the JSON string in the response
    // };

    var data="";
    http.get('http://'+help.gitIp+'/project/'+projectid,function (resp) {
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
            var project = JSON.parse(data);
            req.session.project=project;
            next();
            //res.send(JSON.stringify(buffers));
            // res.render('index',{
            //         title:"express",
            //         projectid:projectid,
            //         realname:"yhqqq",
            //         project:project
            //
            //     })
        });
    }).on('error', function(err) {
        console.log('error ' + err);
    });
    // rp(options)
    //     .then(function (data) {
    //         console.log('body ');
    //         console.log('body ' + JSON.stringify(body));
    //         res.render('index',{
    //                     title:"express",
    //                     projectid:projectid
    //                 })
    //     })
    //     .catch(function (err) {
    //         console.log('error');
    //         // API call failed...
    //     });
};
