var express = require('express');
var teamAPI = require('../api-lib/teamwork');

var router = express.Router();

/*
渲染首页使用的数据：
  真实姓名； realName
  当前激活的项目详细资料；  activeProjectId: 没有时为-1,否则就是对应字符串  activeProjectInfo:-1或ProjectVO
  
  各个子界面所需要的内容
*/
router.get('/', async function(req, res, next) {
  var sess = req.session;
  if(!sess.logined) {
    res.redirect('/');
    return;
  }
  
  sess.userInfo = await teamAPI.getUserInfo(sess.usr);
  var userInfo = sess.userInfo;
  
  var activeProjectId = null;
  if (sess.activeProjectId) {
    activeProjectId = sess.activeProjectId;
  } else {
    activeProjectId = -1;
  }

  var activeProjectInfo = null;
  if (sess.activeProjectInfo) {
    activeProjectInfo = sess.activeProjectInfo;
  } else if (sess.activeProjectId) {
    sess.activeProjectInfo = await teamAPI.getProjectInfo(sess.activeProjectId);
    activeProjectInfo = sess.activeProjectInfo;
  } else {
    activeProjectInfo = -1;
  }

  var renderOptions = {
    realName: userInfo.name,
    activeProjectId: activeProjectId,
    activeProjectInfo: activeProjectInfo,
  };

  switch(req.query.p) {
    case "welcome":
      res.render('index', renderOptions);
    break;
    case "deploy":
    res.render('deploy', renderOptions);
    case "ci":
    res.render('ci', renderOptions);
    break;
    case "modUserInfo":
    res.render('modUserInfo', renderOptions);
    break;
    case "modPassword":
    res.render('modPassword', renderOptions);
    break;
    case "teamManage":
    await renderTeamManage(renderOptions, req, res);
    break;
    case "projectManage":
    await renderProjectManage(renderOptions, req, res);
    break;
    default:
    break;
  }
});

var renderTeamManage = async function(baseRenderOptions, req, res) {
  groups = await teamAPI.getGroupsByUsr(req.session.usr);
  baseRenderOptions.myGroups = groups;
  res.render('teamManage', baseRenderOptions);
}
var renderProjectManage = async function(baseRenderOptions, req, res) {
  projects = await teamAPI.getProjectList(req.session.usr);
  baseRenderOptions.myProjects = projects;
  res.render('projectManage', baseRenderOptions);
}

router.get('/test', function(req,res,next){
  res.render('deploy',{
    realName:"徐江河",
    activeProjectId: 123421
  });
});

module.exports = router;
