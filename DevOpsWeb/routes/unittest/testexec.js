var express = require('express');
var router = express.Router();
var request = require('request');

router.post('/test/execute-all/:testId',function(req, res, next){
    var id = req.params.testId;
    var username="user1";
    if(req.session.username){
        username=req.session.username;
    }
    request.post({url:'http://139.219.66.203:8701/test/execute-all',
            form:{
                id:id,
                username:username
            }},
        function (error,response,body) {
            if(!error&& response.statusCode == 200){
                var report = JSON.parse(body);
                res.render('report_detail',{report:report,testid:id});
            }else{
                res.render('error',{err:error});
            }
        });
});

router.get('/test/execute/:id',function(req, res, next){
    var id=req.params.id;
    request('http://139.219.66.203:8701/test/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var test = JSON.parse(body);
                res.render('exec_part',{test:test});
            }else{
                res.render('error',{err:error});
            }
        });
});


router.post('/test/execute/:id',function(req, res, next){
    var id = req.params.id;
    var username="user1";
    if(req.session.username){
        username=req.session.username;
    }
    var files=req.body.file;
    var filestr=JSON.stringify(files);
    request.post({url:'http://139.219.66.203:8701/test/execute',
            form:{
                id:id,
                username:username,
                file:filestr
            }},
        function (error,response,body) {
            if(!error&& response.statusCode == 200){
                var report = JSON.parse(body);
                res.render('report_detail',{report:report,testid:id});
            }else{
                res.render('error',{err:error});
            }
        });

});


module.exports = router;