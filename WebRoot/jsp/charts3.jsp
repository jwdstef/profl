<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <!DOCTYPE html>
<head>
  <title>ECharts</title>
  
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
	<ul class="nav nav-tabs" id="myTab">
		<li class="active" id="bxjz"><a  data-toggle="tab" href="#home"> <i
				class="blue icon-home bigger-110"></i>变形均值
		</a></li>

		<li id="bxjd"><a data-toggle="tab" href="#profile"><i class="pink icon-dashboard bigger-110"></i> 
		变形精度 <span class="badge badge-danger">4</span>
		</a></li>
	</ul>
	<div class="tab-content">
		<div id="home" class="tab-pane in active">
			<div id="psLine" style="height: 300px;"></div>
		</div>
		<div id="profile" class="tab-pane">
			<div id="psLine2" style="height: 300px;"></div>
		</div>
	</div>
</body>
<script src="../commons/js/echarts/jquery-1.11.3.min.js"></script>
<script src="../commons/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
	//图表  
	var psLineChar = echarts.init(document.getElementById('psLine2'));

	var psLineChar2 = echarts.init(document.getElementById('psLine'));
	//查询  
	function loadDrugsOne() {
		psLineChar.clear();
		psLineChar.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../echartsTwo', function(data) {
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
	$("#bxjz").on("click",function(){
		loadDrugsOne();
		clearInterval(timeTicket);
		timeTicket = setInterval("dot()", 2000);
	}) 
	

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