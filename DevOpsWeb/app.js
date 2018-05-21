var express = require('express');
var path = require('path');
var favicon = require('serve-favicon');
var logger = require('morgan');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');
var session = require('express-session');

var welcome = require('./routes/welcome');
var index = require('./routes/index');
var fapi = require('./routes/fapi');

// 创建express对象
var app = express();
// 启用Jade渲染引擎
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'jade');
//设置中间件链
app.use(favicon(path.join(__dirname, 'public', 'favicon.ico'))); // 设置favicon目录
app.use(logger('dev')); // 添加便于分析请求的logger
app.use(bodyParser.json()); // 自动将json形式的请求body转换为对象
app.use(bodyParser.urlencoded({ extended: false })); // 自动将form的body数据转换为对象
app.use(cookieParser('xjhsec')); // 自动cookie加密
app.use(session({secret: 'xujianghe'})); // 启用会话管理，必须搭配cookie加密
app.use(express.static(path.join(__dirname, 'public'))); // 设置静态资源目录

// 设置子路径的路由
app.use('/', welcome);
app.use('/index', index);
app.use('/fapi', fapi);

// Unit test module
var testRouter = require('./routes/unittest/testmanage');
var bugRouter = require('./routes/unittest/bug');
var testcaseRouter = require('./routes/unittest/testcase');
var testexecRouter = require('./routes/unittest/testexec');
var reportRouter = require('./routes/unittest/report');
app.use('/unittest', testRouter);
app.use('/unittest', bugRouter );
app.use('/unittest', testcaseRouter);
app.use('/unittest', testexecRouter);
app.use('/unittest', reportRouter);

// GitlabCI module
var gitlabciRouter = require('./routes/gitlabci/gitlabci');
app.use('/gitlabci',gitlabciRouter);

// Jenkins module
var jenkinsRouter = require('./routes/jenkins/jenkins');
app.use('/jenkins',jenkinsRouter);

// 错误处理1：当无法在设计好的路由里找到请求的路径时，提示404状态码
app.use(function(req, res, next) {
  var err = new Error('Not Found');
  err.status = 404;
  next(err);
});

// 错误处理2：若出现的错误不是404，则返回500状态码，并渲染错误提示页面
app.use(function(err, req, res, next) {
  res.locals.message = err.message;
  res.locals.error = req.app.get('env') === 'development' ? err : {};
  res.status(err.status || 500);
  res.render('error');
});

module.exports = app;
