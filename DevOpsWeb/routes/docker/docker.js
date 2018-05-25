var express = require('express');

var router = express.Router();

router.get('/task', function(req, res, next) {
    res.render('dockerTask',{
        realName:"徐江河",
        sess:req.session
    })
})

router.get('/administration', function(req, res, next) {
    res.render('dockerAdmin',{
        realName:"徐江河",
        sess:req.session
    })
})

module.exports = router;
