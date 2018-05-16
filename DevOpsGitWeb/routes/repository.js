var express = require('express');
var router = express.Router();
var repository = require('../lib/apirepository');


/* GET users listing. */
router.get('/', function(req, res, next) {
    res.send('respond with a resource');
});

router.get('/commit', function(req, res, next) {
    repository.getCommits(req,res,next);
});

router.get('/merge', function(req, res, next) {
    repository.getMerges(req,res,next);
});

router.get('/trees',function (req,res,next) {
    // var urlobj = url.parse(req.url,true);
    // console.log("urlobj: "+urlobj.query["projectid"]);
    var path=req.query.path;
    console.log("trees path : " +path );
    res.redirect('/group/project?path='+path);
});

module.exports = router;