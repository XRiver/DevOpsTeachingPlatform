var express = require('express');
var router = express.Router();
var user = require("../lib/apiuser");


/* GET users listing. */
router.get('/', function(req, res, next) {
  res.send('respond with a resource');
});

router.get('/sshkey', function(req, res, next) {
  user.getSSHkey(req,res,next);
});
module.exports = router;
