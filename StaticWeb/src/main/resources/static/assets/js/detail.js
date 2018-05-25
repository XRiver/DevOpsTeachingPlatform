$(document).ready(function(){
	/**

	 *paramStr:name=name&type=type...

	 *keyList;[name,key,...]

	 */

	function getValue(paramStr,keyList){

		var valueList=[];

		paramStr=paramStr.split("&");

		for(var i=0;i<paramStr.length;i++){

			var value=paramStr[i];//name=name

			value=unescape(value.split("=")[1]);//name

			valueList.push(value);

		}

		return valueList;

	}
	var paramStr=window.location.href.split("?")[1];
	var projectKey=getValue(paramStr,["projectKey"]);
	function initBugs(){
		//填充参数
		$.ajax({
			type:"GET",
			url:"http://localhost:8088/staticCheck/measure/"+"bugs/"+projectKey,
			async:true,
			success:function(result){
				var bugChart = echarts.init(document
						.getElementById('chart-bug'));

				var date = [];
				var data=[];
				for (var i = 0; i < result.length; i++) {
					var his=result[i];
					date.push(his.date);
					data.push(his.value);
				}
				bugoption = {
						title: {
							text: 'Bugs趋势图'
						},
						xAxis: {
							type: 'category',
							data:date,
							name:"时间"
						},
						yAxis: {
							type: 'value',
							name:"问题数量"
						},
						series: [{
							data: data,
							type: 'line'
						}]
				};
				bugChart.setOption(bugoption);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	function initCodeSmell(){
		//填充参数
		$.ajax({
			type:"GET",
			url:"http://localhost:8088/staticCheck/measure/"+"code_smells/"+projectKey,
			async:true,
			success:function(result){
				var bugChart = echarts.init(document
						.getElementById('chart-codeSmell'));

				var date = [];
				var data=[];
				for (var i = 0; i < result.length; i++) {
					var his=result[i];
					date.push(his.date);
					data.push(his.value);
				}
				bugoption = {
						title: {
							text: 'CodeSmell趋势图'
						},
						xAxis: {
							type: 'category',
							data:date,
							name:"时间"
						},
						yAxis: {
							type: 'value',
							name:"问题数量"
						},
						series: [{
							data: data,
							type: 'line'
						}]
				};
				bugChart.setOption(bugoption);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	function initVulne(){
		//填充参数
		$.ajax({
			type:"GET",
			url:"http://localhost:8088/staticCheck/measure/"+"vulnerabilities/"+projectKey,
			async:true,
			success:function(result){
				var bugChart = echarts.init(document
						.getElementById('chart-vulnerabilities'));

				var date = [];
				var data=[];
				for (var i = 0; i < result.length; i++) {
					var his=result[i];
					date.push(his.date);
					data.push(his.value);
				}
				bugoption = {
						title: {
							text: 'Vulnerabilities趋势图'
						},
						xAxis: {
							type: 'category',
							data:date,
							name:"时间"
						},
						yAxis: {
							type: 'value',
							name:"问题数量"
						},
						series: [{
							data: data,
							type: 'line'
						}]
				};
				bugChart.setOption(bugoption);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	function initProblemCons(bugs,codeSmell,Vulnerabilities){
		var myChart = echarts.init(document
				.getElementById('chart-area'));
		option = {
				tooltip: {
					trigger: 'item',
					formatter: "{a} <br/>{b}: {c} ({d}%)"
				},
				legend: {
					orient: 'vertical',
					x: 'right',
					data:['Bugs','Code Smells','Vulnerabilites']
				},
				series: [
					{
						name:'问题构成',
						type:'pie',
						radius: ['50%', '70%'],
						avoidLabelOverlap: false,
						label: {
							emphasis: {
								show: true,
								textStyle: {
									fontSize: '30',
									fontWeight: 'bold'
								}
							}
						},
						data:[
							{value:bugs, name:'Bugs'},
							{value:codeSmell, name:'Code Smells'},
							{value:Vulnerabilities, name:'Vulnerabilites'}
							]
					}
					]
		};
		myChart.setOption(option);
	}
	function fillGeneralData(){
		//填充参数
		$.ajax({
			type:"GET",
			url:"http://localhost:8088/staticCheck/measure/"+projectKey,
			async:true,
			success:function(result){
				$("#classes").html(result.classes);
				$("#functions").html(result.functions);
				$("#loc").html(result.loc);
				$("#commentRate").html(result.commentRate);
				$("#comment").html(result.commentsLines);
				initProblemCons(result.bug,result.codeSmell,result.vulnerability);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	fillGeneralData();
	initBugs();
	initCodeSmell();
	initVulne();
	//根据窗口调整表格高度
	$(window).resize(function() {
		$('#mytab').bootstrapTable('resetView', {
			height: tableHeight()
		})
	})

	function transfer(str){
		str=str.replace(/\//g, "%2F");
		str=str.replace(/\:/g, "%3A");
		return str;
	}

	function transferBlank(str){
		str=str.replace("/\ /g","%nbsp;");
		return str;
	}

	function showRule(row,result){
		//清空
		$("#codeArea").empty();
		//哪一行有问题
		var lineNo=row.lineNo;
		var code=result.code;
		//得到说明
		$.ajax({
			type:"GET",
			//contentType : 'application/json',
			url:"http://localhost:8088/staticCheck/rule/"+row.rule,
			//data:	{"key":row.filePath},
			success:function(result){
				for(var i=0;i<code.length-1;i++){
					if(lineNo==(i+1)){//背景是红色,
						$("#codeArea").append("<div class='problem-line'><span class='source-meta w-source-line-number'> "+(i+1)+"</span>"+code[i]+"</div><br />");
						//且在该行下面加上一些说明
						$("#codeArea").append("<div class='rule-area'>"+result.describ+"</div>");
						continue;
					}
					$("#codeArea").append("<span class='source-meta source-line-number'> "+(i+1)+"</span>"+code[i]+"<br />");
				}
			},
			error:function(err){}
		});	
	}

	window.getEvents={

			"click #checkButton":function(e,value,row,index){
				//获得代码显示
				$.ajax({
					type:"POST",
					//contentType : 'application/json',
					url:"http://localhost:8088/staticCheck/code",
					data:	{"key":row.filePath},
					success:function(result){
						showRule(row,result);
					},
					error:function(err){
						console.log(err);
					}
				});
			}
	};
	$("#codeCheckTable").bootstrapTable({
		method: 'get',
		url:"http://localhost:8088/staticCheck/problem/"+projectKey,
		pageNumber: 1, //初始化加载第一页，默认第一页
		pagination:true,//是否分页
		sidePagination:'server',//指定服务器端分页
		pageSize:5,//单页记录数
		pageList:[5,10],//分页步进值
		showRefresh:true,//刷新按钮
		clickToSelect: true,//是否启用点击选中行
		toolbarAlign:'right',//工具栏对齐方式
		queryParams : function(params) {
			return {
				offset: params.offset+1,
				pageSize: params.limit,
				fileName:$("#fileName").val(),
				severity:$("#severity").val(),
				type:$("#type").val()
			};
		},
		columns:[
			{
				title:'问题类型',
				field:'type',
				sortable:false
			},
			{
				title:'严重性',
				field:'severity',
				sortable:true
			},
			{
				title:'所在文件',
				field:'filePath',
				sortable:false,
				formatter:function(value,row,index){
					return value.split(":")[1];
				}
			},
			{
				title:'问题描述',
				field:'message'
			},
			{
				title:'所在行数',
				field:'lineNo'
			},
			{
				title:'操作',
				field:'Button',
				formatter:function(value,row,index){
					return '<button id="checkButton" type="button" class="btn btn-info" data-toggle="modal" data-target="#detailProblem">点击查看</button> ';
				},
				events:getEvents
			}
			],
			locale:'zh-CN',//中文支持,
			responseHandler:function(res){
				//在ajax获取到数据，渲染表格之前，修改数据源
				return res;
			}
	})
	$("#filterButton").click(function(){
		$("#codeCheckTable").bootstrapTable(("refresh"));
	});

	function tableHeight(){
		//可以根据自己页面情况进行调整
		return $(window).height() -280;
	}
});