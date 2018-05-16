var express = require('express');
var router = express.Router();
var request = require('request');

router.get('/:project/bugs',function (req, res, next) {
    var project=req.params.project;

    request('http://localhost:8701/bug/get-by-project?projectId='+project,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var bugs = JSON.parse(body);
                res.render('bug_list',{bugs:bugs,project:project});
            }else{
                res.render('error',{err:error,project:project});
            }
        });


});


router.get('/bug/:id',function (req, res, next) {
    var id=req.params.id;
    request('http://localhost:8701/bug/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var bug = JSON.parse(body);
                res.render('bug_detail',{bug:bug});
            }else{
                res.render('error',{err:error});
            }
        });

});

router.post('/:project/createbug',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://localhost:8701/bug/create',
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
                res.render('error',{err:error});
            }
        });
});

router.post('/bug/update',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://localhost:8701/bug/update',
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
                res.render('error',{err:error});
            }
        });
});

router.post('/bug/delete',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://localhost:8701/bug/delete',
            form:{
                id:req.body.id,
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/bugs');
            }else{
                res.render('error',{err:error});
            }
        });
});


router.post('/bug/change',function(req, res, next){
    var manager="default";
    /**
    if(req.session.username!=null){
        manager=req.session.username;
    }
   */
    request.post({url:'http://localhost:8701/bug/change',
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
                res.render('error',{err:error});
            }
        });
});

module.exports = router;