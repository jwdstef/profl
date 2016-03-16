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
		<div class="col-xs-6">
			<div class="alert alert-block alert-success">
				<i class=" icon-bullhorn red"></i>  <strong class="blue">
					&nbsp;&nbsp;开始时间&nbsp;:&nbsp;<small></small>
				</strong><span class="blue" id="startTime"></span>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="alert alert-block alert-danger">
				<i class=" icon-cogs blue"></i>  <strong class="blue">
					&nbsp;&nbsp;3D距离(米)&nbsp;:&nbsp; <small></small>
				</strong> <span class="blue" id="changeShot"></span>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-6">
			<div class="alert alert-block alert-success">
				<i class=" icon-bullhorn red"></i>  <strong class="blue">
					&nbsp;&nbsp;当前时间&nbsp;:&nbsp; <small></small>
				</strong> <span class="blue" id="fdateTime"></span>
			</div>
		</div>
		<div class="col-xs-6">
			<div class="alert alert-block alert-danger">
				<i class=" icon-cog blue"></i>  <strong class="blue">
					&nbsp;&nbsp;距离增量(厘米)&nbsp;:&nbsp;<small></small>
				</strong><span class="blue" id="ddd"></span>
			</div>
		</div>
	</div>
		<div class="row">
			<div class="col-xs-9">
				<ul class="nav nav-tabs" id="myTab">
					<li class="active" id="bxjz"><a data-toggle="tab" href="#home">
							<i class="blue icon-home bigger-110"></i>站心坐标
					</a></li>

					<li id="bxjd"><a data-toggle="tab" href="#profile"><i
							class="pink icon-dashboard bigger-110"></i> 变形量 <span
							class="badge badge-danger"></span> </a></li>
				</ul>
				<div class="tab-content">
					<div id="psLine" style="height: 250px;"></div>
					<div id="psLine2" style="height: 250px;"></div>
				</div>
				<!-- ----------------北---开始----------------------- -->
				<div class="row">
					<div class="col-xs-3"></div>
					<div class="col-xs-8">
						<div class="infobox infobox-red infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class="icon-cloud-upload"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">N增量(厘米)</div>
								<div class="infobox-content">
									<span id="nzl">0</span>
								</div>
							</div>
						</div>
						<div class="infobox infobox-green infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class=" icon-globe"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">变形均值(厘米)</div>
								<div class="infobox-content">
									<span id="nChangeAvalue">0</span>
								</div>
							</div>
						</div>
						<div class="infobox infobox-blue infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class="icon-download-alt"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">变形精度(厘米)</div>
								<div class="infobox-content">
									<span id="nChangeShot">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-1"></div>
				</div>
				<ul class="nav nav-tabs" id="myTab2">
					<li class="active" id="bxjz1"><a data-toggle="tab"
						href="#home1"> <i class="blue icon-home bigger-110"></i>站心坐标
					</a></li>

					<li id="bxjd1"><a data-toggle="tab" href="#profile1"><i
							class="pink icon-dashboard bigger-110"></i> 变形量 <span
							class="badge badge-danger"></span> </a></li>
				</ul>
				<div class="tab-content">
					<div id="psLine3" style="height: 250px;"></div>
					<div id="psLine4" style="height: 250px;"></div>
				</div>
				<!-- ----------------东---开始----------------------- -->
				<div class="row">
					<div class="col-xs-3"></div>
					<div class="col-xs-8">
					<div class="infobox infobox-red infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class=" icon-cloud-upload"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">E增量(厘米)</div>
								<div class="infobox-content">
									<span id="ezl">0</span>
								</div>
							</div>
						</div>
						<div class="infobox infobox-green infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class=" icon-globe"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">变形均值(厘米)</div>
								<div class="infobox-content">
									<span id="eChangeAvalue">0</span>
								</div>
							</div>
						</div>
						<div class="infobox infobox-blue infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class="icon-download-alt"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">变形精度(厘米)</div>
								<div class="infobox-content">
									<span id="eChangeShot">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-1"></div>
				</div>

				<ul class="nav nav-tabs" id="myTab3">
					<li class="active" id="bxjz2"><a data-toggle="tab"
						href="#home1"> <i class="blue icon-home bigger-110"></i>站心坐标
					</a></li>

					<li id="bxjd2"><a data-toggle="tab" href="#profile1"><i
							class="pink icon-dashboard bigger-110"></i> 变形量 <span
							class="badge badge-danger"></span> </a></li>
				</ul>
				<div class="tab-content">
					<div id="psLine5" style="height: 250px;"></div>
					<div id="psLine6" style="height: 250px;"></div>
				</div>
				<!-- -----------------天--开始----------------------- -->
				<div class="row">
					<div class="col-xs-3"></div>
					<div class="col-xs-8">
					<div class="infobox infobox-red infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class=" icon-cloud-upload"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content">U增量(厘米)</div>
								<div class="infobox-content">
									<span id="uzl">0</span>
								</div>
							</div>
						</div>
						<div class="infobox infobox-green infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class=" icon-globe"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content" width="100px">变形均值(厘米)</div>
								<div class="infobox-content">
									<span id="dChangeAvalue">0</span>
								</div>
							</div>
						</div>
						<div class="infobox infobox-blue infobox-small infobox-dark">
							<div class="infobox-icon">
								<i class="icon-download-alt"></i>
							</div>

							<div class="infobox-data">
								<div class="infobox-content" width="100px">变形精度(厘米)</div>
								<div class="infobox-content">
									<span id="dChangeShot">0</span>
								</div>
							</div>
						</div>
					</div>
					<div class="col-xs-1"></div>
				</div>

			</div>
			<div class="col-xs-3">
				<input type="hidden" id="nodeNum" value="${param.nodeNum }">
				<img style="width: 100%; height: 350px;"
					src="../assets/images/gyt.png" />
				<img id="node01" src="../assets/images/sok.png" style="position: absolute; height: 30px; width: 30px; top: 20px; left: 60px;"/>
				<img id="node02" src="../assets/images/sok.png" style="position: absolute; height: 30px; width: 30px;top: 20px; left: 190px;"/>
				<img id="node03" src="../assets/images/sok.png" style="position: absolute; height: 30px; width: 30px; top: 100px; left: 60px;"/>
				<img id="node04" src="../assets/images/sok.png" style="position: absolute; height: 30px; width: 30px; top: 100px; left: 195px;"/>
				<img id="node05" src="../assets/images/sok.png" style="position: absolute; height: 30px; width: 30px; top: 280px; left: 132px;"/>
				<div id="pstate"
					style="font-size:18px;border-style: solid; border-width: 1px; border-color: #000; margin-top: 100px">
					监测站1变形量(N,E,U)<br> 监测站2变形量(N,E,U)<br> 监测站3变形量(N,E,U)<br>
					监测站4变形量(N,E,U)<br> 监测站5变形量(N,E,U)
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
	
	var psLineChar5 = echarts.init(document.getElementById('psLine5'));
	var psLineChar6 = echarts.init(document.getElementById('psLine6'));
	
	//3d数组
	var strd = "0,0,0,0,0,0";
	globalInfo();
	function globalInfo() {
		  $.ajax({  
		        type:'get',      
		        url:'../globalInfo',  
		        data:{baseCode:$("#nodeNum").val(),strd:strd},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		        	$("#startTime").empty().html(JSON.parse(data).startTime);
		        	
		        	$("#changeAvalue").empty().html(JSON.parse(data).changeAvalue);
		        	$("#changeShot").empty().html(JSON.parse(data).changeShot);
		        	$("#ddd").empty().html(JSON.parse(data).ddd);
		        	strd = JSON.parse(data).jstrd;
		        }  
		    }); 
		}
	var timeGlobal;
	clearInterval(timeGlobal);
	timeGlobal = setInterval("globalInfo()", 1000);
	
	neuData();
	
	function neuData() {
		  $.ajax({  
		        type:'get',      
		        url:'../neuData',  
		        data:{baseCode:$("#nodeNum").val()},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		        	var nodeNum ='${param.nodeNum }';
		    		var str= new Array();
		    		str = nodeNum.split("_");
		        	$("#pstate").empty().html(JSON.parse(data).onep+"<br>"+JSON.parse(data).twop+"<br>"+
		        			JSON.parse(data).threep+"<br>"+JSON.parse(data).fourp+"<br>"+JSON.parse(data).fivep+"<br>");
		        	if(JSON.parse(data).onep == "监测站1变形量(异常)"){
		        		if(str[2] == "01"){
		        			$("#node01").css({"height":"40px","width":"40px"});
		        			$("#node01").attr('src',"../assets/images/error01.png");
		        		}else{
		        			$("#node01").css({"height":"30px","width":"30px"});
		        			$("#node01").attr('src',"../assets/images/error01.png");
		        		}
		        	}else{
		        		if(str[2] == "01"){
		        			$("#node01").css({"height":"40px","width":"40px"});
		        			$("#node01").attr('src',"../assets/images/ok01.png");
		        		}else{
		        			$("#node01").css({"height":"30px","width":"30px"});
		        			$("#node01").attr('src',"../assets/images/ok01.png");
		        		}
		        	}
		        	if(JSON.parse(data).twop == "监测站2变形量(异常)"){
		        		if(str[2] == "02"){
		        			$("#node02").css({"height":"40px","width":"40px"});
		        			$("#node02").attr('src',"../assets/images/error02.png");
		        		}else{
		        			$("#node02").css({"height":"30px","width":"30px"});
		        			$("#node02").attr('src',"../assets/images/error02.png");
		        		}
		        	}else{
		        		if(str[2] == "02"){
		        			$("#node02").css({"height":"40px","width":"40px"});
		        			$("#node02").attr('src',"../assets/images/ok02.png");
		        		}else{
		        			$("#node02").css({"height":"30px","width":"30px"});
		        			$("#node02").attr('src',"../assets/images/ok02.png");
		        		}
		        	}
		        	if(JSON.parse(data).threep == "监测站3变形量(异常)"){
		        		if(str[2] == "03"){
		        			$("#node03").css({"height":"40px","width":"40px"});
		        			$("#node03").attr('src',"../assets/images/error03.png");
		        		}else{
		        			$("#node03").css({"height":"30px","width":"30px"});
		        			$("#node03").attr('src',"../assets/images/error03.png");
		        		}
		        	}else{
		        		if(str[2] == "03"){
		        			$("#node03").css({"height":"40px","width":"40px"});
		        			$("#node03").attr('src',"../assets/images/ok03.png");
		        		}else{
		        			$("#node03").css({"height":"30px","width":"30px"});
		        			$("#node03").attr('src',"../assets/images/ok03.png");
		        		}
		        	}
		        	if(JSON.parse(data).fourp == "监测站4变形量(异常)"){
		        		if(str[2] == "04"){
		        			$("#node04").css({"height":"40px","width":"40px"});
		        			$("#node04").attr('src',"../assets/images/error04.png");
		        		}else{
		        			$("#node04").css({"height":"30px","width":"30px"});
		        			$("#node04").attr('src',"../assets/images/error04.png");
		        		}
		        	}else{
		        		if(str[2] == "04"){
		        			$("#node04").css({"height":"40px","width":"40px"});
		        			$("#node04").attr('src',"../assets/images/ok04.png");
		        		}else{
		        			$("#node04").css({"height":"30px","width":"30px"});
		        			$("#node04").attr('src',"../assets/images/ok04.png");
		        		}
		        	}
		        	if(JSON.parse(data).fivep == "监测站5变形量(异常)"){
		        		if(str[2] == "05"){
		        			$("#node05").css({"height":"40px","width":"40px"});
		        			$("#node05").attr('src',"../assets/images/error05.png");
		        		}else{
		        			$("#node05").css({"height":"30px","width":"30px"});
		        			$("#node05").attr('src',"../assets/images/error05.png");
		        		}
		        	}else{
		        		if(str[2] == "05"){
		        			$("#node05").css({"height":"40px","width":"40px"});
		        			$("#node05").attr('src',"../assets/images/ok05.png");
		        		}else{
		        			$("#node05").css({"height":"30px","width":"30px"});
		        			$("#node05").attr('src',"../assets/images/ok05.png");
		        		}
		        	}
		        }  
		    }); 
		}
	
	var timeState;
	clearInterval(timeState);
	timeState = setInterval("neuData()", 1000);
	
	function changeData(type) {
	  $.ajax({  
	        type:'get',      
	        url:'../tChangeData',  
	        data:{baseCode:$("#nodeNum").val(),type:type},  
	        cache:false,  
	        dataType:'json',  
	        success:function(data){
	        	if(type == "0"){
	        		$("#nChangeAvalue").empty().html(JSON.parse(data).defmean.substr(0,10));
	        		$("#nChangeShot").empty().html(JSON.parse(data).depepre.substr(0,10));
	        	}else if(type == "1"){
	        		$("#eChangeAvalue").empty().html(JSON.parse(data).defmean.substr(0,10));
	        		$("#eChangeShot").empty().html(JSON.parse(data).depepre.substr(0,10));
	        	}else if(type == "2"){
	        		$("#dChangeAvalue").empty().html(JSON.parse(data).defmean.substr(0,10));
	        		$("#dChangeShot").empty().html(JSON.parse(data).depepre.substr(0,10));
	        	}
	        }  
	    }); 
	}
	
	//北
	var time0;
	clearInterval(time0);
	time0 = setInterval("changeData('0')", 1000);
	//东
	var time1;
	clearInterval(time1);
	time1 = setInterval("changeData('1')", 1000);
	//天
	var time2;
	clearInterval(time2);
	time2 = setInterval("changeData('2')", 1000);
	  
	//查询  
	function loadDrugsOne() {
		psLineChar.clear();
		psLineChar.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite?type=0&tsort=0&baseCode=${param.nodeNum }', function(data) {
			psLineChar.setOption(JSON.parse(data), true);
			psLineChar.hideLoading();
		});
	}
	
	function loadDrugsTwo() {
		psLineChar2.clear();
		psLineChar2.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite?type=0&tsort=1&baseCode=${param.nodeNum }', function(data) {
			psLineChar2.setOption(JSON.parse(data), true);
			psLineChar2.hideLoading();
		});
	}
	
	function loadDrugsThree() {
		psLineChar3.clear();
		psLineChar3.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite?type=1&tsort=0&baseCode=${param.nodeNum }', function(data) {
			psLineChar3.setOption(JSON.parse(data), true);
			psLineChar3.hideLoading();
		});
	}
	
	function loadDrugsFour() {
		psLineChar4.clear();
		psLineChar4.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite?type=1&tsort=1&baseCode=${param.nodeNum }', function(data) {
			psLineChar4.setOption(JSON.parse(data), true);
			psLineChar4.hideLoading();
		});
	}
	
	function loadDrugsFive() {
		psLineChar5.clear();
		psLineChar5.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite?type=2&tsort=0&baseCode=${param.nodeNum }', function(data) {
			psLineChar5.setOption(JSON.parse(data), true);
			psLineChar5.hideLoading();
		});
	}
	
	function loadDrugsSix() {
		psLineChar6.clear();
		psLineChar6.showLoading({
			text : '正在努力的读取数据中...'
		});
		$.getJSON('../northLineSite?type=2&tsort=1&baseCode=${param.nodeNum }', function(data) {
			psLineChar6.setOption(JSON.parse(data), true);
			psLineChar6.hideLoading();
		});
	}
	
	 //根据剩余时间字符串计算出总秒数
    function getTotalSecond(timestr) {
        var reg = /\d+/g;
        var timenums = new Array();
        while ((r = reg.exec(timestr)) != null) {
            timenums.push(parseInt(r));
        }
        var second = 0, i = 0;
        if (timenums.length == 4) {
            second += timenums[0] * 24 * 3600;
            i = 1;
        }
        second += timenums[i] * 60 + timenums[++i];
        return second;
    }

    //根据剩余秒数生成时间格式
    function getNewSyTime(sec) {
        var s = sec % 60;
        sec = (sec - s) / 60; //min
        var m = sec % 60;
        sec = (sec - m) / 60; //hour
        var h = sec % 24;
        var d = (sec - h) / 24;//day
        var syTimeStr = "";
        

        syTimeStr += ("0" + m.toString()).substr(-2) + ":"
                    + ("0" + s.toString()).substr(-2) ;

        return syTimeStr;
    }
    
	
    /**
    * d : 字符串时间，格式为 yyyy-MM-dd HH:mm:ss
    * num : 秒
    * return : 返回 字符串 ，格式跟传入的相同
    */
    function dateCon(d,num){
        var d = new Date(d.substring(0,4),
        d.substring(5,7)-1,
        d.substring(8,10),
        d.substring(11,13),
        d.substring(14,16),
        d.substring(17,19));
        d.setTime(d.getTime()+num*1000);
        //alert(d.toLocaleString());
        return d.getFullYear()+"-"
        +(d.getMonth()+1)
        +"-"+d.getDate()
        +" "+d.getHours()
        +":"+d.getMinutes()
        +":"+d.getSeconds();
    }
    
	var lastData1 = 0;
	var axisData1= '0:00';
	var timeTicket1;
	var fdateTime = 0;
	function ndot() {
		  $.ajax({  
		        type:'get',      
		        url:'../addData',  
		        data:{baseCode:$("#nodeNum").val(),type:"0",tsort:"0"},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		        	if(typeof(JSON.parse(data).yaxis) != "undefined"){
		        	  lastData1 = JSON.parse(data).yaxis;
		        	}
		        	if(axisData1 != '0:00'){
		        		var totalSec = getTotalSecond(axisData1) + 1;
		        		axisData1 = getNewSyTime(totalSec);
		        	}else{
		        		axisData1 = JSON.parse(data).xdate;
		        	}
		        	if(fdateTime != 0){
		        		var fSec = getTotalSecond(fdateTime.substring(14,19)) + 1;
		        		fdateTime = fdateTime.substring(0,14)+getNewSyTime(fSec);
		        	}else{
		        		fdateTime = JSON.parse(data).fdateTime;
		        	}
		        	$("#fdateTime").empty().html(fdateTime);
		        	$("#nzl").empty().html(JSON.parse(data).zl);
		        }  
		    });
		// 动态数据接口 addData
		psLineChar.addData([ [ 0, // 系列索引
		lastData1, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData1 // 坐标轴标签
		] ]);
	}
	
	var lastData2 = 0;
	var timeTicket2;
	function ndot2() {
		  $.ajax({  
		        type:'get',      
		        url:'../addData',  
		        data:{baseCode:$("#nodeNum").val(),type:"0",tsort:"1"},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		          if(typeof(JSON.parse(data).yaxis) != "undefined"){
		        	lastData2 = JSON.parse(data).yaxis;
		        	}
		        }  
		    });
		// 动态数据接口 addData
		psLineChar2.addData([ [ 0, // 系列索引
		lastData2, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData1 // 坐标轴标签
		] ]);
	}
	
	var lastData3 = 0;
	var timeTicket3;
	function ndot3() {
		  $.ajax({  
		        type:'get',      
		        url:'../addData',  
		        data:{baseCode:$("#nodeNum").val(),type:"1",tsort:"0"},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		         if(typeof(JSON.parse(data).yaxis) != "undefined"){
		        	lastData3 = JSON.parse(data).yaxis;
		        	$("#ezl").empty().html(JSON.parse(data).zl);
		         }
		        }  
		    });  
		
		// 动态数据接口 addData
		psLineChar3.addData([ [ 0, // 系列索引
		lastData3, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData1 // 坐标轴标签
		] ]);
	}
	
	var lastData4 = 0;
	var timeTicket4;
	function ndot4() {
		  $.ajax({  
		        type:'get',      
		        url:'../addData',  
		        data:{baseCode:$("#nodeNum").val(),type:"1",tsort:"1"},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		        if(typeof(JSON.parse(data).yaxis) != "undefined"){ 
		        	lastData4 = JSON.parse(data).yaxis;
		        	 }
		        }  
		    });
   
		// 动态数据接口 addData
		psLineChar4.addData([ [ 0, // 系列索引
		lastData4, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData1 // 坐标轴标签
		] ]);
	}
	
	var lastData5 = 0;
	var timeTicket5;
	function ndot5() {
		  $.ajax({  
		        type:'get',      
		        url:'../addData',  
		        data:{baseCode:$("#nodeNum").val(),type:"2",tsort:"0"},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		         if(typeof(JSON.parse(data).yaxis) != "undefined"){
		        	lastData5 = JSON.parse(data).yaxis;
		        	$("#uzl").empty().html(JSON.parse(data).zl);
		         }
		        }  
		    });  
	 
		// 动态数据接口 addData
		psLineChar5.addData([ [ 0, // 系列索引
		lastData5, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData1 // 坐标轴标签
		] ]);
	}
	
	var lastData6 = 0;
	var timeTicket6;
	function ndot6() {
		  $.ajax({  
		        type:'get',      
		        url:'../addData',  
		        data:{baseCode:$("#nodeNum").val(),type:"2",tsort:"1"},  
		        cache:false,  
		        dataType:'json',  
		        success:function(data){
		          if(typeof(JSON.parse(data).yaxis) != "undefined"){
		        	lastData6 = JSON.parse(data).yaxis;
		          }
		        }  
		    });
		// 动态数据接口 addData
		psLineChar6.addData([ [ 0, // 系列索引
		lastData6, // 新增数据
		false, // 新增数据是否从队列头部插入
		false, // 是否增加队列长度，false则自定删除原有数据，队头插入删队尾，队尾插入删队头
		axisData1 // 坐标轴标签
		] ]);
	}
	
	loadDrugsOne();
	clearInterval(timeTicket1);
	timeTicket1 = setInterval("ndot()", 1100);
	
	loadDrugsTwo();
	clearInterval(timeTicket2);
	timeTicket2 = setInterval("ndot2()", 1100);
	
	loadDrugsThree();
	clearInterval(timeTicket3);
	timeTicket3 = setInterval("ndot3()", 1100);
	
	loadDrugsFour();
	clearInterval(timeTicket4);
	timeTicket4 = setInterval("ndot4()", 1100);
	
	loadDrugsFive();
	clearInterval(timeTicket5);
	timeTicket5 = setInterval("ndot5()", 1100);
	
	loadDrugsSix();
	clearInterval(timeTicket6);
	timeTicket6 = setInterval("ndot6()", 1100);
	
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
	
	$("#bxjz2").on("click",function(){
		$("#psLine6").hide();
		$("#psLine5").show();
	}) 
	$("#bxjd2").on("click",function(){
		$("#psLine5").hide();
		$("#psLine6").show();
	}) 
	
	window.onload = function() {
		$("#psLine2").hide();
		$("#psLine").show();
		$("#psLine4").hide();
		$("#psLine3").show();
		$("#psLine6").hide();
		$("#psLine5").show();
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