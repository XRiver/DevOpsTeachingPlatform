$(document).ready(function () {
	var projectKey="Student";
	function toaster(message, type) {
		var toaster = $("#toaster");
		toaster.append('<div class="toast-item"><div class="message">' + message + '</div>' +
		'<i class="close fa fa-close"></i></div>');
		var thisItem = toaster.children().last();
		$(thisItem.children(".close").eq(0)).bind("click", function () {
			thisItem.slideUp(function() {
				//thisItem.remove();
			});
		});
		if (type == "success") thisItem.addClass("success");
		else if (type == "error") thisItem.addClass("error");
		thisItem.fadeIn();
		setTimeout(function() {
			thisItem.slideUp(function() {
			//	thisItem.remove();
			});
		}, 3000);
	}
	
	function fillIndex(){
		$.ajax({
			type:"GET",
			url:"http://localhost:8088/staticCheck/statistic/"+projectKey,
			async:true,
			success:function(result){
				$("#lastTime").html(result.lastAnalyse);
				$("#health").html(result.healthDegree);
				$("#risk").html(result.riskIndex);
				$("#problemNo").html(result.unresolvedProblems);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	function fillData(){
		//填充参数
		$.ajax({
			type:"GET",
			url:"http://localhost:8088/staticCheck/config/"+projectKey,
			async:true,
			success:function(result){
				//$("#projectName").val(result.projectName);
				$('#langSelec').selectpicker('val',result.language);
				$('#encodeSelec').selectpicker('val',result.sourceEncoding);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
			}
		});
	}
	fillIndex();
	fillData();
	getDetail=function (){
		$('#loading').show();
		$.ajax({
			type:"POST",
			url:"http://localhost:8088/staticCheck/statistic/"+projectKey,
			async:true,
			success:function(result){
				//请求完成，隐藏模态框
				$('#loading').hide();
				toaster("检查成功！","success");
				window.location.href="detail.html?projectKey="+escape(projectKey);
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				toaster("请先配置参数！","error");
				//请求完成，隐藏模态框
				$('#loading').hide();
			}
		});

	}

	$("#submitConfig").click(function(){
		//像后台传参
		$.ajax({
			type:"POST",
			contentType: "application/json",
			data:	JSON.stringify({
				"projectKey":projectKey,
				//"projectName":$("#projectName").val(),
				"language":$("#langSelec").val(),
				"sourceEncoding":$("#encodeSelec").val()
			}),
			url:"http://localhost:8088/staticCheck/config",
			async:true,
			success:function(result){
				$("#paramConfig").modal('hide');  //手动关闭

			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				console.log(textStatus);
				$("#paramConfig").modal('hide');  //手动关闭

			}
		});
	});



});




function adminLightItem() {
	if ($("#admin-nav-items")) {
		var items = $("#admin-nav-items").children();
		for(var i = 0; i < items.length; i++) {
			var item = $(items[i]);
			var url = window.location.href;
			if (url.indexOf(item.attr("url")) != -1) {
				item.addClass("active");
				break;
			}
		}

	}
}

function datePicker() {
	try {
		$('.date-picker').each(function () {
			$(this).datetimepicker({
				lang:'ch',
				timepicker:false,
				format:'Y-m-d',
				formatDate:'Y/m/d',
				minDate: '1916/01/01',
				maxDate:'+1970/03/01',
				yearStart: 1916,
				yearEnd: 2016,
				scrollInput: false
			})
		});
	} catch (e) {
		console.log(e);
	}
}

function getWeek(day) {
	switch (day) {
	case 0: return '日';
	case 1: return '一';
	case 2: return '二';
	case 3: return '三';
	case 4: return '四';
	case 5: return '五';
	case 6: return '六';
	}
}

//Date 格式化
Date.prototype.Format = function(fmt) {
	var o = {
			"M+" : this.getMonth()+1,                 //月份
			"d+" : this.getDate(),                    //日
			"h+" : this.getHours(),                   //小时
			"m+" : this.getMinutes(),                 //分
			"s+" : this.getSeconds(),                 //秒
			"q+" : Math.floor((this.getMonth()+3)/3), //季度
			"S"  : this.getMilliseconds()             //毫秒
	};
	if(/(y+)/.test(fmt))
		fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
	for(var k in o)
		if(new RegExp("("+ k +")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
	return fmt;
};

