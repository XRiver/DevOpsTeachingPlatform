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

module.exports = router;