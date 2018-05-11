var express = require('express');
var router = express.Router();
var group= require('../lib/apigroup');
var repository = require('../lib/apirepository');
var user = require('../lib/apiuser');

/* GET project info. */
router.get('/project', function(req, res, next) {
    group.getProject(req,res,next);
});

router.get('/project',function (req, res, next) {
    user.getUser(req,res,next);
});

router.get('/project',function (req, res, next) {
    repository.getTree(req,res,next);
});

router.get('/merge', function(req, res, next) {
    res.render('mergerequest');
});
router.get('/sshkey', function(req, res, next) {
    res.render('sshkey');
});


// /* GET project info. */
// router.get('/project', function(req, res, next) {
//     console.log("url: "+req.url);
//     var urlobj = url.parse(req.url,true);
//     console.log("urlobj: "+urlobj.query["projectid"]);
//     var projectid=urlobj.query["projectid"];
//
//     // var project = group.getProject(projectid);
//     // var projectname = project["name"];
//
//     var project = null;
//     http.get("http://"+help.gitIp+"/project/"+projectid,function (req, res) {
//         req.on('data',function (data) {
//             console.log("apigroup data: "+data);
//         })
//     }).then(function (data) {
//         console.log("apigroup data: "+data);
//         res.render('index',
//                  {title:"express",
//                      projectid:projectid});
//     });
//     // rp(
//     //     {
//     //         uri:"http://"+help.gitIp+"/project/"+projectid,
//     //         method:"GET",
//     //         json:true,
//     //     }
//     // ).then(function(body){
//     //     ret = body;
//     //     console.log("apigroup body: "+body);
//     //     res.render('index',
//     //         {title:"express",
//     //             projectid:projectid});
//     // });
//     // res.render('index',
//     //     {title:"express",
//     //     projectid:projectid,
//     //     projectname:projectname});
//     // res.send('respond with a resource');
// });

module.exports = router;