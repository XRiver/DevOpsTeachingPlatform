var express = require('express');
var router = express.Router();
var group= require('../../api-lib/apigroup');
var repository = require('../../api-lib/apirepository');
var user = require('../../api-lib/apiuser');

/* GET project info. */
router.get('/project', function(req, res, next) {
    group.getProject(req,res,next);
});

router.get('/project',function (req, res, next) {
    user.getUser(req,res,next);
});

router.get('/project',function (req, res, next) {
    repository.getTree(req,res,next);
});

router.get('/merge', function(req, res, next) {
    res.render('mergerequest',{sess:req.session});
});
router.get('/sshkey', function(req, res, next) {
    res.render('sshkey',{sess:req.session});
});


module.exports = router;