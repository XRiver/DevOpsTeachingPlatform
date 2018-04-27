var express = require('express');
var router = express.Router();
var request = require('request');


router.get('/:testId/testcase/:id',function (req, res, next) {
    var id=req.params.id;
    var testId=req.params.testId;
    request('http://localhost:8701/testcase/get?id='+id,
        function (error,response,body) {
            if (!error&& response.statusCode == 200) {
                var testcase = JSON.parse(body);
                res.render('testcase_detail',{testcase:testcase,testid:testId});
            }else{
                res.render('error',{err:error});
            }
        });

});




module.exports = router;