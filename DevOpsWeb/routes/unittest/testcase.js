var express = require('express');
var router = express.Router();
var request = require('request');


router.get('/:testId/testcase/:id',function (req, res, next) {
    var id=req.params.id;
    var testId=req.params.testId;
    request('http://139.219.66.203:8701/testcase/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var testcase = JSON.parse(body);
                res.render('testcase_detail',{testcase:testcase,testid:testId,sess:req.session});
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });

});


router.post('/:testId/testcase/create',function(req, res, next){
    var test=req.params.testId;
    request.post({url:'http://139.219.66.203:8701/testcase/create',
            form:{
                caseId:req.body.caseId,
                testId:req.body.testId,
                name:req.body.name,
                info:req.body.info,
                file:req.body.file
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/test/'+test);
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

router.post('/:testId/testcase/update',function(req, res, next){
    var test=req.params.testId;
    request.post({url:'http://139.219.66.203:8701/testcase/update',
            form:{
                id:req.body.id,
                caseId:req.body.caseId,
                name:req.body.name,
                info:req.body.info,
                file:req.body.file
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/test/'+test);
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});

router.post('/:testId/testcase/delete',function(req, res, next){
    var test=req.params.testId;
    request.post({url:'http://139.219.66.203:8701/testcase/delete',
            form:{
                id:req.body.id,
            }},
        function (error,response,body) {
            if(!error){
                res.redirect('/unittest/test'+test);
            }else{
                res.render('error',{err:error,sess:req.session});
            }
        });
});



module.exports = router;