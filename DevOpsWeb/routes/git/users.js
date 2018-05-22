var express = require('express');
var router = express.Router();
var user = require("../../api-lib/apiuser");

router.get('/sshkey', function(req, res, next) {
  user.getSSHkey(req,res,next);
});
module.exports = router;
