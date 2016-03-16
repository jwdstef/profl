<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <!DOCTYPE html>
<head>
  <title>电网展示</title>
  
		<!-- basic styles -->

		<link href="../assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="../assets/css/font-awesome.min.css" />
		<!-- page specific plugin styles -->

		<link rel="stylesheet" href="../assets/css/jquery-ui-1.10.3.custom.min.css" />
		<link rel="stylesheet" href="../assets/css/jquery.gritter.css" />

		<!-- ace styles -->

		<link rel="stylesheet" href="../assets/css/ace.min.css" />
		<link rel="stylesheet" href="../assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="../assets/css/ace-skins.min.css" />
  </head>
<body>
	<div class="row">
		<div class="col-xs-9">
			<div style="float: left; width: 950px;">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active" id="bxjz"><a data-toggle="tab" href="#home">
							<i class="blue icon-home bigger-110"></i>站心坐标
					</a></li>

					<li id="bxjd"><a data-toggle="tab" href="#profile"><i
							class="pink icon-dashboard bigger-110"></i> 形变量 <span
							class="badge badge-danger">4</span> </a></li>
				</ul>
				<div class="tab-content">
					<div id="psLine" style="height: 250px;"></div>
					<div id="psLine2" style="height: 250px;"></div>
				</div>
				<ul class="nav nav-tabs" id="myTab2">
					<li class="active" id="bxjz1"><a data-toggle="tab"
						href="#home1"> <i class="blue icon-home bigger-110"></i>站心坐标
					</a></li>

					<li id="bxjd1"><a data-toggle="tab" href="#profile1"><i
							class="pink icon-dashboard bigger-110"></i> 形变量 <span
							class="badge badge-danger">4</span> </a></li>
				</ul>
				<div class="tab-content">
					<div id="psLine3" style="height: 250px;"></div>
					<div id="psLine4" style="height: 250px;"></div>
				</div>
				<ul class="nav nav-tabs" id="myTab3">
					<li class="active" id="bxjz1"><a data-toggle="tab"
						href="#home1"> <i class="blue icon-home bigger-110"></i>站心坐标
					</a></li>

					<li id="bxjd1"><a data-toggle="tab" href="#profile1"><i
							class="pink icon-dashboard bigger-110"></i> 形变量 <span
							class="badge badge-danger">4</span> </a></li>
				</ul>
				<div class="tab-content">
					<div id="psLine5" style="height: 250px;"></div>
					<div id="psLine6" style="height: 250px;"></div>
				</div>
			</div>

		</div>
		<div class="col-xs-3">
		<input type="hidden" id="nodeNum" value="${param.nodeNum }">
		 <div>
			<div style="margin-top:100px;background-image:url('../assets/images/gyt.png');height:600px;background-repeat:no-repeat;"></div>
			<div id="node1" style="position:absolute;height:30px;width:30px; top:130px;left:90px;background-image:url('../assets/images/sok.png');background-repeat:no-repeat;"></div>
			<div id="node2" style="position:absolute;height:30px;width:30px; top:130px;left:240px;background-image:url('../assets/images/sok.png');background-repeat:no-repeat;"></div>
			<div id="node3" style="position:absolute;height:30px;width:30px; top:220px;left:90px;background-image:url('../assets/images/sok.png');background-repeat:no-repeat;"></div>
			<div id="node4" style="position:absolute;height:30px;width:30px; top:220px;left:240px;background-image:url('../assets/images/sok.png');background-repeat:no-repeat;"></div>
			<div id="node5" style="position:absolute;height:40px;width:40px; top:440px;left:157px;background-image:url('../assets/images/pnode.gif');background-repeat:no-repeat;"></div>
		 </div>
		 <div style="border-style:solid; border-width:1px; border-color:#000">
		 	监测站1变形量(N,E,U)
		 	监测站2变形量(N,E,U)
		 	监测站3变形量(N,E,U)
		 	监测站4变形量(N,E,U)
		 	监测站5变形量(N,E,U)
		 </div>
		</div>
</body>
<script src="../commons/js/echarts/jquery-1.11.3.min.js"></script>
<script src="../commons/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
	//图表  
	var psLineChar = echarts.init(document.getElementById('psLine'));

	var psLineChar2 = echarts.init(document.getElementById('psLine2'));
	
	var psLineChar3 = echarts.init(document.getElementById('psLine3'));
	var psLineChar4 = echarts.init(document.getElementById('psLine4'));
	//查询  
	function loadDrugsOne() {
		psLineChar.clear();
		psLineChar.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite', function(data) {
			psLineChar.setOption(JSON.parse(data), true);
			psLineChar.hideLoading();
		});
	}
	
	function loadDrugsTwo() {
		psLineChar2.clear();
		psLineChar2.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../echartsTwo', function(data) {
			psLineChar2.setOption(JSON.parse(data), true);
			psLineChar2.hideLoading();
		});
	}
	
	function loadDrugsThree() {
		psLineChar3.clear();
		psLineChar3.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../echartsThree', function(data) {
			psLineChar3.setOption(JSON.parse(data), true);
			psLineChar3.hideLoading();
		});
	}
	
	function loadDrugsFour() {
		psLineChar4.clear();
		psLineChar4.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../echartsFour', function(data) {
			psLineChar4.setOption(JSON.parse(data), true);
			psLineChar4.hideLoading();
		});
	}
	
	var lastData = 11;
	var axisData;
	var timeTicket;
	var i = 7;
	function dot() {

		lastData += Math.random()
				* ((Math.round(Math.random() * 10) % 2) == 0 ? 1 : -1);
		lastData = lastData.toFixed(1) - 0;
		axisData = (new Date()).toLocaleTimeString().replace(/^\D*/, '');

		// 动态数据接口 addData
		psLineChar.addData([ [ 0, // 系列索引
		lastData, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData // 坐标轴标签
		] ]);
	}
	loadDrugsOne();
	clearInterval(timeTicket);
	timeTicket = setInterval("dot()", 2000);
	loadDrugsTwo();
	
	loadDrugsThree();
	
	loadDrugsFour();
	$("#bxjd").on("click",function(){
		$("#psLine").hide();
		$("#psLine2").show();
	}) 
	$("#bxjz").on("click",function(){
		$("#psLine2").hide();
		$("#psLine").show();
	})
	$("#bxjz1").on("click",function(){
		$("#psLine4").hide();
		$("#psLine3").show();
	}) 
	$("#bxjd1").on("click",function(){
		$("#psLine3").hide();
		$("#psLine4").show();
	}) 
	
	window.onload = function() {
		alert(111111);
		//初始化高压塔信号状态
		//设置点击过来的那个闪烁
		//$("#node"+).css("background-image","url(on.jpg)");
		$("#psLine2").hide();
		$("#psLine").show();
		$("#psLine4").hide();
		$("#psLine3").show();
	}; 

	//载入图表  
	//window.setInterval("loadDrugs()",2000);
</script>
<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='../assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		<script src="../assets/js/bootstrap.min.js"></script>
		<script src="../assets/js/typeahead-bs2.min.js"></script>

		<script src="../assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="../assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="../assets/js/bootbox.min.js"></script>
		<script src="../assets/js/jquery.easy-pie-chart.min.js"></script>
		<script src="../assets/js/jquery.gritter.min.js"></script>
		<script src="../assets/js/spin.min.js"></script>

		<!-- ace scripts -->

		<script src="../assets/js/ace-elements.min.js"></script>
		<script src="../assets/js/ace.min.js"></script>