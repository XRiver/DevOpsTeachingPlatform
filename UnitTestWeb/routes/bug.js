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



module.exports = router;