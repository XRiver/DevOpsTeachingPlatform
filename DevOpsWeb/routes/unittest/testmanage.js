var express = require('express');
var router = express.Router();
var request = require('request');


router.get('/:project/tests',function (req, res, next) {
    var project=req.params.project;

    request('http://139.219.66.203:8701/test/get-by-project?projectId='+project,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var tests = JSON.parse(body);
                res.render('test_list',{tests:tests,project:project,sess:req.session});
            }else{
                res.render('test_list',{err:error,project:project,sess:req.session});
            }
        });


});


router.get('/test/:id',function (req, res, next) {
    var id=req.params.id;
    request('http://139.219.66.203:8701/test/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var test = JSON.parse(body);
                res.render('test_detail',{test:test,sess:req.session});
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});



router.post('/:project/createtest',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://139.219.66.203:8701/test/create',
            form:{
                projectId:project,
                testId:req.body.testId,
                name:req.body.name,
                lan:req.body.lan,
                branch:req.body.branch,
                src:req.body.src
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/tests');
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

router.post('/test/update',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://139.219.66.203:8701/test/update',
            form:{
                id:req.body.id,
                testId:req.body.testId,
                name:req.body.name,
                lan:req.body.lan,
                branch:req.body.branch,
                src:req.body.src
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/tests');
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

router.post('/test/delete',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://139.219.66.203:8701/test/delete',
            form:{
                id:req.body.id,
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/'+project+'/tests');
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});


module.exports = router;