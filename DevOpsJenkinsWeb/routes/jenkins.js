var express = require('express');
var router = express.Router();
var request = require('request');
var async = require('async');
var moment = require('moment');

const jenkinsUrl = 'http://139.219.66.203:8071'


router.get('/:group/:project', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    request(jenkinsUrl + '/job/' + name + '/info', function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project});
            } else {
                res.render('jenkins_home', {info: obj.t, group: group, project: project});
            }
        } else {
            res.render('jenkins_error', {err: "访问主页失败", group: group, project: project})
        }
    });
});
router.post('/:group/:project/update', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    console.log(req.body.description);
    var name = group + "-" + project;
    request.post({
        url: jenkinsUrl + '/job/' + name + '/update',
        form: {
            description: req.body.description,
            jenkinsFilePath: "Jenkinsfile"
        }

    }, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project});
            } else {
                res.redirect('/jenkins/' + group + "/" + project);
            }

        } else {
            res.render('jenkins_error', {err: "更新失败", group: group, project: project})
        }
    })
})
router.get('/:group/:project/period', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    request(jenkinsUrl + '/job/' + name + '/period', function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project});
            }
            else {

                res.render('jenkins_home_period', {info: obj, name: name, group: group, project: project});
            }
        } else {
            res.render('jenkins_error', {err: "访问触发周期失败", group: group, project: project})
        }
    })
})
router.post('/:group/:project/period', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    request.post({
        url: jenkinsUrl + '/job/' + name + '/period',
        form: {
            period: req.body.period
        }

    }, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project});
            } else {
                res.redirect('/jenkins/' + group + "/" + project + "/period");
            }

        } else {
            res.render('jenkins_error', {err: "更新触发周期失败", group: group, project: project})
        }
    })
})
router.post('/:group/:project/build', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    request.post({
        url: jenkinsUrl + '/job/' + name + '/build',
        form: {
            name: name
        }
    }, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project});
            } else {
                res.redirect('/jenkins/' + group + "/" + project);
            }
        } else {
            res.render('jenkins_error', {err: "扫描失败", group: group, project: project})
        }
    })
})
router.get('/:group/:project/computers', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    request(jenkinsUrl + "/computers", function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            res.render('jenkins_computers', {computers: obj.t, name: name, group: group, project: project});
        } else {
            res.render('jenkins_error', {err: "获取系统节点失败", group: group, project: project})
        }
    })
})

router.get('/:group/:project/branch/:branchName', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    var branchName = req.params.branchName;
    async.parallel({
        information: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/info", function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    if (!obj.success) {
                        callback(obj.information, null);
                    } else {
                        callback(null, obj.t);
                    }
                } else {
                    callback("进入分支失败", null);
                }
            })
        }, hasArtifact: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/hasArtifact", function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    callback(null, obj.success);
                } else {
                    callback("获取制品信息失败", null);
                }
            })
        }
    }, function (err, result) {
        if (err) {
            res.render('jenkins_error', {err: err, group: group, project: project, branchName: branchName})
        } else {
            res.render('jenkins_branch_home', {
                info: result.information,
                success: result.hasArtifact,
                group: group,
                project: project,
                branchName: branchName
            })
        }

    })
    /*request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/info", function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project, branchName: branchName});
            } else {
                res.render('jenkins_branch_home', {info: obj.t, group: group, project: project, branchName: branchName})
            }
        } else {
            res.render('jenkins_error', {err: "进入分支失败", group: group, project: project, branchName: branchName})
        }
    })*/
})
router.post('/:group/:project/branch/:branchName/build', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    var branchName = req.params.branchName;
    request.post({
        url: jenkinsUrl + '/job/' + name + '/branch/' + branchName + '/build',
        form: {
            name: name,
            branchName: branchName
        }
    }, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            var obj = JSON.parse(body);
            if (!obj.success) {
                res.render('jenkins_error', {info: obj, group: group, project: project, branchName: branchName});
            } else {
                res.redirect('/jenkins/' + group + "/" + project + "/branch/" + branchName);
            }
        } else {
            res.render('jenkins_error', {err: "构建发起失败", group: group, project: project, branchName: branchName})
        }
    })

})
router.get('/:group/:project/branch/:branchName/:number', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    var branchName = req.params.branchName;
    var number = req.params.number;
    async.parallel({
        information: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/build/" + number, function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    if (!obj.success) {
                        callback(obj, null);
                    } else {
                        callback(null, obj.t);
                    }
                }
                else {
                    callback("访问构建失败", null);
                }
            })
        }, nodes: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/build/" + number + "/nodes", function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    callback(null, obj);
                } else {
                    callback("获取节点失败", null);
                }
            })
        }, log: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/build/" + number + "/log", function (error, response, body) {
                if (!error && response.statusCode == 200) {

                    callback(null, body);
                } else {
                    callback("获取节点日志失败", null);
                }
            })
        }
    }, function (err, result) {
        if (err) {
            res.render('jenkins_error', {err: err, group: group, project: project, branchName: branchName})
        } else {

            for (var i = 0; i < result.nodes.length; i++) {
                if (result.nodes[i].startTime) {
                    result.nodes[i].startTime = moment(result.nodes[i].startTime).format('YYYY年MM月DD日HH:mm:ss');
                }
            }
            res.render('jenkins_branch_build', {
                info: result.information,
                group: group,
                project: project,
                branchName: branchName,
                number: number,
                time: moment(result.information.timestamp).format('YYYY年MM月DD日HH:mm:ss'),
                duration: result.information.duration / 1000.0,
                estimatedDuration: result.information.estimatedDuration / 1000.0,
                nodes: result.nodes,
                log: result.log
            })
        }
    })
})
router.get('/:group/:project/branch/:branchName/:number/node/:nodeId', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    var branchName = req.params.branchName;
    var number = req.params.number;
    var nodeId = req.params.nodeId;
    async.parallel({
        information: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/build/" + number + "/nodes/" + nodeId, function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    callback(null, obj);
                }
                else {
                    callback("访问节点失败", null);
                }
            })
        }, steps: function (callback) {
            request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/build/" + number + "/nodes/" + nodeId + "/steps", function (error, response, body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    callback(null, obj);
                } else {
                    callback("获取步骤失败", null);
                }
            })
        }
    }, function (err, result) {
        if (err) {
            res.render('jenkins_error', {
                err: err,
                group: group,
                project: project,
                branchName: branchName,
                number: number
            })
        } else {
            var time;
            if (result.information.startTime) {
                time = moment(result.information.startTime).format('YYYY年MM月DD日HH:mm:ss');
            } else {
                time = null;
            }
            for (var i = 0; i < result.steps.length; i++) {
                if (result.steps[i].startTime) {
                    result.steps[i].startTime = moment(result.steps[i].startTime).format('YYYY年MM月DD日HH:mm:ss');
                }
            }
            res.render('jenkins_branch_build_step', {
                info: result.information,
                group: group,
                project: project,
                branchName: branchName,
                number: number,
                nodeId: nodeId,
                time: time,
                steps: result.steps
            })
        }
    })
})
router.get('/:group/:project/branch/:branchName/:number/node/:nodeId/step/:stepId', function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var name = group + "-" + project;
    var branchName = req.params.branchName;
    var number = req.params.number;
    var nodeId = req.params.nodeId;
    var stepId = req.params.stepId;
    console.log(stepId+" "+nodeId+" "+number+" "+branchName);
    request(jenkinsUrl + "/job/" + name + "/branch/" + branchName + "/build/" + number + "/nodes/" + nodeId + "/steps/" + stepId + "/log", function (error, response, body) {
        console.log(body);
        if (!error && response.statusCode == 200) {
            res.render('jenkins_branch_build_step_log', {
                group: group,
                project: project,
                branchName: branchName,
                number: number,
                nodeId: nodeId,
                stepId: stepId,
                log: body
            })
        } else {
            res.render('jenkins_error', {
                err: err,
                group: group,
                project: project,
                branchName: branchName,
                number: number,
                nodeId: nodeId
            })
        }
    })
})

module.exports = router;


