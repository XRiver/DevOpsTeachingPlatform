var express = require('express');
var router = express.Router();
var request = require('request');

router.get('/:testid/reports',function (req, res, next) {
    var id=req.params.testid;

    request('http://139.219.66.203:8701/report/get-by-test?testId='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var reports = JSON.parse(body);
                res.render('report_list',{reports:reports,testid:id,sess:req.session});
            }else{
                res.render('report_list',{err:error,testid:id,sess:req.session});
            }
        });


});


router.get('/:testid/report/:id',function (req, res, next) {
    var id=req.params.id;
    var testid=req.params.testid;
    request('http://139.219.66.203:8701/report/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var report = JSON.parse(body);
                res.render('report_detail',{report:report,testid:testid,sess:req.session});
            }else{
                res.render('report_detail',{err:error,sess:req.session});
            }
        });

});



module.exports = router;