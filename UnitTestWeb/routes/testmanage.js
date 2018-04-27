var express = require('express');
var router = express.Router();
var request = require('request');


router.get('/:project/tests',function (req, res, next) {
    var project=req.params.project;

    request('http://localhost:8701/test/get-by-project?projectId='+project,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var tests = JSON.parse(body);
                res.render('test_list',{tests:tests,project:project});
            }else{
                res.render('test_list',{err:error,project:project});
            }
        });


});


router.get('/test/:id',function (req, res, next) {
    var id=req.params.id;
    request('http://localhost:8701/test/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var test = JSON.parse(body);
                res.render('test_detail',{test:test});
            }else{
                res.render('error',{err:error});
            }
        });
});



router.post('/:project/createtest',function(req, res, next){
    var project = req.params.project;
    request.post({url:'http://localhost:8701/test/create',
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
                res.render('error',{err:error});
            }
        });
});




module.exports = router;