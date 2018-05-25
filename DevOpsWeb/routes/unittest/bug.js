var express = require('express');
var router = express.Router();
var request = require('request');

router.get('/:project/bugs',function (req, res, next) {
    var project=req.params.project;

    request('http://139.219.66.203:8701/bug/get-by-project?projectId='+project,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var bugs = JSON.parse(body);
                res.render('bug_list',{bugs:bugs,project:project,sess:req.session});
            }else{
                res.render('error',{err:error,project:project,sess:req.session});
            }
        });


});


router.get('/bug/:id',function (req, res, next) {
    var id=req.params.id;
    request('http://139.219.66.203:8701/bug/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var bug = JSON.parse(body);
                res.render('bug_detail',{bug:bug,sess:req.session});
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });

});

router.post('/:project/createbug',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://139.219.66.203:8701/bug/create',
            form:{
                projectId:req.body.projectId,
                name:req.body.name,
                info:req.body.info,
                imp:req.body.imp
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/bugs');
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

router.post('/bug/update',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://139.219.66.203:8701/bug/update',
            form:{
                id:req.body.id,
                name:req.body.name,
                info:req.body.info,
                imp:req.body.imp
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/bugs');
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

router.post('/bug/delete',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://139.219.66.203:8701/bug/delete',
            form:{
                id:req.body.id,
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/bugs');
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});


router.post('/bug/change',function(req, res, next){
    var manager="user1";
    if(req.session.username){
        manager=req.session.username;
    }
    request.post({url:'http://139.219.66.203:8701/bug/change',
            form:{
                id:req.body.id,
                info:req.body.info,
                state:req.body.state,
                manager:manager
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/bug/'+req.body.id);
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

module.exports = router;