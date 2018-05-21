var express = require('express');
var router = express.Router();
var request = require('request');
var YAML = require('yamljs');
var async = require('async');
var fs = require('fs');

const token = '?private_token=RsByonYoaC-_wAkG-6mM';
const gitlabUrl = 'http://localhost:80';

/* GET users listing. */
router.get('/:group/:project', function(req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/files/.gitlab-ci.yml/raw'+token+'&ref=master',
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                var yaml_obj = YAML.parse(body);
                var stages = {};
                for(var x in yaml_obj.stages){
                    stages[yaml_obj.stages[x]]=[];
                }
                delete yaml_obj.stages;
                for(var x in yaml_obj){
                    if(x!=="before_script"&&x!=="after_script"&&x!=="cache"){
                        yaml_obj[x]['name']=x;
                        stages[yaml_obj[x].stage].push(yaml_obj[x]);
                        delete yaml_obj[x];
                    }
                }
                yaml_obj.stages=stages;
                res.render('ci-yaml',{yaml_obj:yaml_obj,yaml_str:body,group:group,project:project});
            }else{
                res.render('ci-yaml',{err:"getting .gitlab-ci.yml file content error!",group:group,project:project})
            }
        });
});

router.get('/:group/:project/init',function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    var language = 'java';
    if(req.query.language){
        language = req.query.language;
    }
    var initFunc = function() {
        fs.readFile('public/GitlabCITemplate/'+language+'.yml','utf-8',function (err, data) {
            if(err){
                res.json({ok:false,message:'getting template content error.'});
            }else{
                var commitMessage = encodeURIComponent('upload .gitlab-ci.yml file and start pipeline configuration!');
                data = data.replace(/req_projectid/g,req.query.projectid);
                var content = encodeURIComponent(data);
                request.post(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/files/.gitlab-ci.yml'+token+'&branch=master&content='+content+'&commit_message='+commitMessage,
                    function (error,response,body) {
                        if(error){
                            res.json({ok:false,message:'uploading pipeline configuration error.'});
                        }else{
                            res.json({ok:true,message:'init success!'});
                        }
                    });
            }
        })
    };
    initFunc();
});

router.get('/:group/:project/pipelines',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    var page =1;
    if(req.query.page){
        page = req.query.page;
    }
    var per_page = 10;
    if(req.query.per_page){
        per_page = req.query.per_page;
    }
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipelines'+token+'&per_page='+per_page+'&page='+page,
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                var pipelines = JSON.parse(body);
                var paraFuncs = [];
                pipelines.forEach(function (item) {
                    paraFuncs.push(function (callback) {
                        request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipelines/'+item.id+token,
                            function (error,response,body) {
                                if (!error && response.statusCode == 200) {
                                    callback(null,JSON.parse(body));
                                }else{
                                    callback('getting pipeline '+item.id+' detail error!',body);
                                }
                            });
                    })
                });
                paraFuncs.push(function (callback) {
                    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/branches'+token+'&per_page=100',
                        function (error,response,body) {
                            if (!error && response.statusCode == 200) {
                                callback(null,JSON.parse(body));
                            }else{
                                callback('getting project branches error!',body);
                            }
                        });
                });
                async.parallel(paraFuncs,function (err,result) {
                    if(err){
                        res.render('pipelines',{err:err,group:group,project:project});
                    }else{
                        res.render('pipelines',{branches:result.pop(),pipelines:result,group:group,project:project,page:page});
                    }
                });
            }else{
                res.render('pipelines',{err:"getting pipelines list error!",group:group,project:project})
            }
        });
});

router.get('/:group/:project/pipelines_create',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    var branch = req.query.branch;
    request.post(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipeline'+token+'&ref='+branch,
        function (error,response,body) {
            if (!error) {
                res.redirect('/gitlabci/'+group+'/'+project+'/pipelines');
            }else{
                res.json({ok:false,message:"新建流水线失败。"});
            }
        });
});

router.get('/:group/:project/jobs/:job_id',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    async.parallel({
        detail:function(callback){
            request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/jobs/'+req.params.job_id+token,
                function (error,response,body) {
                    if (!error && response.statusCode == 200) {
                        callback(null,body);
                    }else{
                        callback('detail error!',body);
                    }
                });
        },
        log:function (callback) {
            request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/jobs/'+req.params.job_id+'/trace'+token,
                function (error,response,body) {
                    if (!error && response.statusCode == 200) {
                        callback(null,body);
                    }else{
                        callback('log error!',body);
                    }
                });
        }
    },function(err, result){
        if(err){
            res.render('job_detail',{err:err,group:group,project:project});
        }else{
            var det_obj = JSON.parse(result.detail);
            var str = result.log;
            str=str.replace(/\u001b|\[0K|\[32;1m|\[0;m|\[31;1m/g,'');
            res.render('job_detail',{detail:det_obj,log:str,group:group,project:project});
        }
    });

});

router.get('/:group/:project/jobs/:job_id/artifacts',function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/jobs/'+req.params.job_id+'/artifacts'+token).pipe(res);
});

router.get('/:group/:project/jobs',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    var page =1;
    if(req.query.page){
        page = req.query.page;
    }
    var per_page = 20;
    if(req.query.per_page){
        per_page = req.query.per_page;
    }
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/jobs'+token+'&per_page='+per_page+'&page='+page,
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                res.render('jobs',{jobs:JSON.parse(body),page:page,group:group,project:project,page:page});
            }else{
                res.render('jobs',{err:"获取jobs失败，请稍后再试。",group:group,project:project});
            }
        });
});

router.get('/:group/:project/pipelines/:pipeline_id',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    async.parallel({pipeline:function (callback) {
        request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipelines/'+req.params.pipeline_id+token,
            function (error,response,body) {
                if (!error && response.statusCode == 200) {
                    callback(null,JSON.parse(body));
                }else{
                    callback('获取pipeline失败。',null);
                }
            });
    },pipeline_jobs:function (callback) {
        request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipelines/'+req.params.pipeline_id+'/jobs'+token+'&per_page=100',
            function (error,response,body) {
                if (!error && response.statusCode == 200) {
                    var obj = JSON.parse(body);
                    var jobs = {};
                    for(var x in obj){
                        if(jobs[obj[x]['stage']]){
                            jobs[obj[x]['stage']].push(obj[x]);
                        }else{
                            jobs[obj[x]['stage']]=[obj[x]];
                        }
                    }
                    callback(null,jobs);
                }else{
                    callback('获取pipeline jobs失败。',null);
                }
            });
    }},function (err, result) {
        if(err){
            res.render('pipeline_detail',{err:err,group:group,project:project});
        }else{
            res.render('pipeline_detail',{pipeline:result.pipeline,pipeline_jobs:result.pipeline_jobs,group:group,project:project});
        }
    });
});

router.get('/:group/:project/pipelines/:pipeline_id/jobs',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipelines/'+req.params.pipeline_id+'/jobs'+token+'&per_page=100',
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                var obj = JSON.parse(body);
                var jobs = {};
                for(var x in obj){
                    if(jobs[obj[x]['stage']]){
                        jobs[obj[x]['stage']].push(obj[x]);
                    }else{
                        jobs[obj[x]['stage']]=[obj[x]];
                    }
                }
                res.send(jobs);
            }
        });
});

router.get('/:group/:project/ci-object',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/files/.gitlab-ci.yml/raw'+token+'&ref=master',
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                var yaml_obj = YAML.parse(body);
                var stages = {};
                for(var x in yaml_obj.stages){
                    stages[yaml_obj.stages[x]]=[];
                }
                delete yaml_obj.stages;
                for(var x in yaml_obj){
                    if(x!=="before_script"&&x!=="after_script"&&x!=="cache"){
                        yaml_obj[x]['job']=x;
                        stages[yaml_obj[x].stage].push(yaml_obj[x]);
                        delete yaml_obj[x];
                    }
                }
                yaml_obj.stages=stages;
                res.send(yaml_obj);
            }
        });
});

router.get('/:group/:project/ci-yaml',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/files/.gitlab-ci.yml/raw'+token+'&ref=master',
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                res.render('ci-yaml',{ci_yaml:body,group:group,project:project});
            }else{
                res.render('ci-yaml',{err:"获取.gitlab-ci.yml失败。",group:group,project:project});
            }
        });
});

router.post('/:group/:project/ci-yaml',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    var commitMessage = encodeURIComponent('update .gitlab-ci.yml file.');
    var content = encodeURIComponent(req.body.content);
    request.put(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/files/.gitlab-ci.yml'+token+'&branch=master&content='+content+'&commit_message='+commitMessage,
        function (error,response,body) {
            if (!error) {
                res.redirect("/gitlabci/"+group+"/"+project);
            }else{
                res.render('ci-yaml',{err:"更新.gitlab-ci.yml失败。",group:group,project:project});
            }
        });
});

router.get('/:group/:project/runners',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/runners'+token,
        function (error,response,body) {
            if (!error && response.statusCode == 200) {
                var parallelFuncs = [];
                var runners = JSON.parse(body);
                var j,len;
                for(j = 0,len=runners.length; j < len; j++) {
                    parallelFuncs[j]=function (callback) {
                        request(gitlabUrl+'/api/v4/runners/'+runners.pop().id+token,
                            function (error,response,body) {
                                if (!error && response.statusCode == 200) {
                                    callback(null,JSON.parse(body));
                                }else{
                                    callback('getting ruuners error!',null)
                                }
                            });
                    }
                }
                async.parallel(parallelFuncs,function (err,result) {
                    if(err){
                        res.render('runners',{err:"获取runners失败，请稍后再试。",group:group,project:project});
                    }else{
                        res.render('runners',{runners:result,group:group,project:project});
                    }
                });
            }else{
                res.render('runners',{err:"获取runners失败，请稍后再试。",group:group,project:project});
            }
        });
});

router.get('/:group/:project/schedules', function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    async.parallel({schedules:function (callback) {
        request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipeline_schedules'+token,
            function (error,response,body) {
                if (!error && response.statusCode == 200) {
                    callback(null,JSON.parse(body));
                }else{
                    callback("getting schedules list error!",null);
                }
            });
    },branches:function (callback) {
        request(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/repository/branches'+token+'&per_page=100',
            function (error,response,body) {
                if (!error && response.statusCode == 200) {
                    callback(null,JSON.parse(body));
                }else{
                    callback('getting project branches error!',null);
                }
            });
    }},function (err, result) {
        if(err){
            res.render('schedules',{err:err,group:group,project:project});
        }else{
            res.render('schedules',{schedules:result.schedules,branches:result.branches,group:group,project:project});
        }
    });
});

router.post('/:group/:project/schedules',function(req, res, next){
    var group = req.params.group;
    var project = req.params.project;
    request.post({url:gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+'/'+project)+'/pipeline_schedules'+token,
        form:{
            description: req.body.description,
            ref: req.body.branch,
            cron: req.body.cron,
            cron_timezone: "Asia/Shanghai"
        }},
        function (error,response,body) {
            if(!error){
                res.redirect('/gitlabci/'+group+'/'+project+'/schedules');
            }else{
                res.render('schedules',{err:"添加schedules失败。",group:group,project:project});
            }
        });
});

router.get('/:group/:project/schedules_active/:schedule_id',function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    request.put({url:gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+"/"+project)+'/pipeline_schedules/'+req.params.schedule_id+token,form:{
        active: req.query.active
    }},function (error,response,body) {
        if(!error){
            res.redirect('/gitlabci/'+group+'/'+project+'/schedules');
        }else{
            res.render('schedules',{err:"updating schedule "+req.params.schedule_id+" error!",group:group,project:project});
        }
    });
});

router.get('/:group/:project/schedules_delete/:schedule_id',function (req, res, next) {
    var group = req.params.group;
    var project = req.params.project;
    request.delete(gitlabUrl+'/api/v4/projects/'+encodeURIComponent(group+"/"+project)+'/pipeline_schedules/'+req.params.schedule_id+token,function (error,response,body) {
        if(!error){
            res.redirect('/gitlabci/'+group+'/'+project+'/schedules');
        }else{
            res.render('schedules',{err:"deleting schedule "+req.params.schedule_id+" error!",group:group,project:project});
        }
    });
});
module.exports = router;
