<!DOCTYPE html>
<%@ include file="./common/include.jsp"%>
<html >
<head>
	<base href="<%=basePath%>" />
	<title>输电铁塔变形监测系统</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<!-- basic styles -->
	<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
	<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
	<!--[if IE 7]>
	  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
	<![endif]-->
	<!-- page specific plugin styles -->
	<!-- ace styles -->
	<link rel="stylesheet" href="assets/css/ace.min.css" />
	<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
	<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
	<link href="assets/js/validationEngine/validationEngine.jquery.css" type="text/css" />
	<!--[if lte IE 8]>
	  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
	<![endif]-->
	<!-- inline styles related to this page -->
	<!-- ace settings handler -->
	<script src="assets/js/ace-extra.min.js"></script>
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="assets/js/html5shiv.js"></script>
	<script src="assets/js/respond.min.js"></script>
	<![endif]-->
</head>
<body>
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed')}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
							<i class="icon-leaf"></i>
							输电铁塔变形监测系统
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="assets/avatars/user.jpg" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
									李晓明
								</span>

								<i class="icon-caret-down"></i>
							</a>

							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<li>
									<a href="#">
										<i class="icon-cog"></i>
										设置
									</a>
								</li>

								<li>
									<a href="#">
										<i class="icon-user"></i>
										个人资料
									</a>
								</li>

								<li class="divider"></li>

								<li>
									<a href="#">
										<i class="icon-off"></i>
										退出
									</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>


		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>
										<ul class="nav nav-list">
				<c:choose>
					<c:when test="${not empty nlist}">
						<c:forEach items="${nlist}" var="net" varStatus="vs">
						<li><a href="#" class="dropdown-toggle">
										<i class="icon-desktop"></i> <span class="menu-text">
											${net.name } </span> <b class="arrow icon-angle-down"></b>
								</a>
						</li>
						</c:forEach>
					</c:when>
				</c:choose>
				</ul>
			
				<!--
					<ul class="nav nav-list">
						<li class="active open">
							<a href="#" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text"> 苏南线 </span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">
								<li>
									<a href="#" class="dropdown-toggle">
										<i class="icon-double-angle-right"></i>
										杆塔001
										<b class="arrow icon-angle-down"></b>
									</a>

									<ul class="submenu">
										<li>
											<a href="#">
												<i class="icon-leaf"></i>
												站点001
											</a>
										</li>

										<li>
											<a href="#" class="dropdown-toggle">
												<i class="icon-pencil"></i>

												站点002
												<b class="arrow icon-angle-down"></b>
											</a>

											<ul class="submenu">
												<li>
													<a href="#">
														<i class="icon-plus"></i>
														添加产品
													</a>
												</li>

												<li>
													<a href="#">
														<i class="icon-eye-open"></i>
														查看商品
													</a>
												</li>
											</ul>
										</li>
									</ul>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:void(0);" class="dropdown-toggle">
								<i class="icon-desktop"></i>
								<span class="menu-text"> 承租人管理 </span>

								<b class="arrow icon-angle-down"></b>
							</a>
							<ul class="submenu">
								<li class="active">
									<a href="javascript:void(0)" onclick="onMenuChange('userList','个人客户');">
										<i class="icon-double-angle-right"></i>
										个人客户
									</a>
									<ul class="submenu">
										<li>
											<a href="#">
												<i class="icon-leaf"></i>
												第一级
											</a>
										</li>

										<li>
											<a href="#" class="dropdown-toggle">
												<i class="icon-pencil"></i>

												第四级
												<b class="arrow icon-angle-down"></b>
											</a>

											<ul class="submenu">
												<li>
													<a href="#">
														<i class="icon-plus"></i>
														添加产品
													</a>
												</li>

												<li>
													<a href="#">
														<i class="icon-eye-open"></i>
														查看商品
													</a>
												</li>
											</ul>
										</li>
									</ul>
								</li>
								<li>
									<a href="javascript:void(0)" onclick="onMenuChange('jsp/charts.jsp','法人客户');">
										<i class="icon-double-angle-right"></i>
										法人客户
									</a>
								</li>
							</ul>
						</li>
					</ul>
					 /.nav-list -->
					
					
					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>
				<div class="main-content">
					<div class="page-content" style="min-height: 600px;width:100%;height:100%;background-color:#F2F2F2;">
						<iframe name="main" allowtransparency="true" style="min-height: 600px; visibility: inherit; background-color :#ffffff;z-index: 0;" scrolling="no" id="main" align="left" width="100%" height="100%"  border="0" frameborder="0" src="userList" onload="iFrameHeight(this)">
						</iframe>
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->
		</div><!-- /.main-container -->
		<!-- 新增页面start -->
		<div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="update" aria-hidden="true">
			<div class="modal-dialog" style="width: 530px">
				<form name="addForm" action="" method="post" class="form-horizontal" role="form">
					<div class="modal-content" >
						<div class="modal-header" style="background-color:#009245;vertical-align:top;">
							<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
								<i class="icon-remove"></i>
							</button>
							<h4 class="modal-title custom_align" id="Heading" style="color:white;">
								<i class="icon-edit"></i>&nbsp;修改密码
							</h4>
						</div>
						<div class="modal-body" >
					  		<div class="form-group">
					  			<label for="" class="col-sm-3 control-label"><span style="color:red;">*&nbsp;</span>原密码:</label>
							    <div class="col-sm-9">
							      <input type="password" class="form-control" id="passwordOld" name="passwordOld" placeholder="请输入原密码.须以字母开头,字母和数字组成,6~18个字符"
								      	required pattern="^.{6,30}$" title="以字母开头，字母和数字组成,6~18个字符" onkeyup="this.value=this.value.replace(',','');this.value=this.value.replace(' ','');" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(' ',''));clipboardData.setData('text',clipboardData.getData('text').replace(',',''));">
							    </div>
					     	</div>
					     	<div class="form-group">
					  			<label for="" class="col-sm-3 control-label"><span style="color:red;">*&nbsp;</span>新密码:</label>
							    <div class="col-sm-9">
							      <input type="password" class="form-control" id="passwordNew1" name="password" placeholder="请输入新密码.须以字母开头,字母和数字组成,6~18个字符"
								      	required pattern="^[a-zA-Z]\w{5,17}$" title="以字母开头，字母和数字组成,6~18个字符" onkeyup="this.value=this.value.replace(',','');this.value=this.value.replace(' ','');" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(' ',''));clipboardData.setData('text',clipboardData.getData('text').replace(',',''));">
							    </div>
					     	</div>
					     	<div class="form-group">
					  			<label for="" class="col-sm-3 control-label"><span style="color:red;">*&nbsp;</span>确认新密码:</label>
							    <div class="col-sm-9">
							      <input type="password" class="form-control" id="passwordNew2" placeholder="请再次输入新密码.须以字母开头,字母和数字组成,6~18个字符"
								      	required pattern="^[a-zA-Z]\w{5,17}$" title="以字母开头,字母和数字组成,6~18个字符" onkeyup="this.value=this.value.replace(',','');this.value=this.value.replace(' ','');" onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(' ',''));clipboardData.setData('text',clipboardData.getData('text').replace(',',''));">
							    </div>
					     	</div>
						</div>
						<div class="modal-footer " style="text-align: center;margin-top:-10px; ">
							<button type="button" class="btn btn-success" style="width: 30%;" id="submitBtn">
								<i class="icon-save"></i> 确定
							</button>
							<button type="button" class="btn btn-success" style="width:30%;" data-dismiss="modal" aria-hidden="true">
								<i class="icon-remove"></i> 取消
							</button>
						</div>
					</div>
				</form>
				<!-- /.modal-content -->
			</div>
			<!-- /.modal-dialog -->
		</div>
		<!-- 新增页面end -->
		<!-- basic scripts -->
		<!--[if !IE]> -->
		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"script>");
		</script>
		<!-- <![endif]-->
		<!--[if IE]>
		<script type="text/javascript">
		 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"script>");
		</script>
		<![endif]-->
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"script>");
		</script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<!--[if lte IE 8]>
		  <script src="assets/js/excanvas.min.js"></script>
		<![endif]-->
		<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="assets/js/jquery.slimscroll.min.js"></script>
		<script src="assets/js/jquery.sparkline.min.js"></script>
		<script src="assets/js/flot/jquery.flot.min.js"></script>
		<script src="assets/js/flot/jquery.flot.resize.min.js"></script>
		<!-- ace scripts -->
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script src="assets/js/bootstrap-hover-dropdown.js"></script>
		<!-- inline scripts related to this page -->
		<script type="text/javascript" src="assets/js/validationEngine/jquery.validationEngine.min.js"></script>
		<script type="text/javascript">
		$('.dropdown-menu').mouseleave(function(){
			   this.slideUp();
		});
    	
		$(function() {
			$('.submenu li').click(function(e) {
				$('.submenu li').removeClass('active');
				$('.nav li').removeClass('active');
				$(this).parent().parent().attr("class","active open");
				$(this).addClass('active');
			});
		});
			function onMenuChange(url,pageTitle){
				$("#main").attr("src",url);
				$("#pageTitle").html(pageTitle);
			}
		
			jQuery(function($) {
				$('.easy-pie-chart.percentage').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
					var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
					var size = parseInt($(this).data('size')) || 50;
					$(this).easyPieChart({
						barColor: barColor,
						trackColor: trackColor,
						scaleColor: false,
						lineCap: 'butt',
						lineWidth: parseInt(size/10),
						animate: /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase()) ? false : 1000,
						size: size
					});
				})
			
				$('.sparkline').each(function(){
					var $box = $(this).closest('.infobox');
					var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
					$(this).sparkline('html', {tagValuesAttribute:'data-values', type: 'bar', barColor: barColor , chartRangeMin:$(this).data('min') || 0} );
				});
			
			
			
			
			  var placeholder = $('#piechart-placeholder').css({'width':'90%' , 'min-height':'150px'});
			  var data = [
				{ label: "social networks",  data: 38.7, color: "#68BC31"},
				{ label: "search engines",  data: 24.5, color: "#2091CF"},
				{ label: "ad campaigns",  data: 8.2, color: "#AF4E96"},
				{ label: "direct traffic",  data: 18.6, color: "#DA5430"},
				{ label: "other",  data: 10, color: "#FEE074"}
			  ]
			  function drawPieChart(placeholder, data, position) {
			 	  $.plot(placeholder, data, {
					series: {
						pie: {
							show: true,
							tilt:0.8,
							highlight: {
								opacity: 0.25
							},
							stroke: {
								color: '#fff',
								width: 2
							},
							startAngle: 2
						}
					},
					legend: {
						show: true,
						position: position || "ne", 
						labelBoxBorderColor: null,
						margin:[-30,15]
					}
					,
					grid: {
						hoverable: true,
						clickable: true
					}
				 })
			 }
			 drawPieChart(placeholder, data);
			
			 /**
			 we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
			 so that's not needed actually.
			 */
			 placeholder.data('chart', data);
			 placeholder.data('draw', drawPieChart);
			
			
			
			  var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
			  var previousPoint = null;
			
			  placeholder.on('plothover', function (event, pos, item) {
				if(item) {
					if (previousPoint != item.seriesIndex) {
						previousPoint = item.seriesIndex;
						var tip = item.series['label'] + " : " + item.series['percent']+'%';
						$tooltip.show().children(0).text(tip);
					}
					$tooltip.css({top:pos.pageY + 10, left:pos.pageX + 10});
				} else {
					$tooltip.hide();
					previousPoint = null;
				}
				
			 });
			
			
			
			
			
			
				var d1 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d1.push([i, Math.sin(i)]);
				}
			
				var d2 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.5) {
					d2.push([i, Math.cos(i)]);
				}
			
				var d3 = [];
				for (var i = 0; i < Math.PI * 2; i += 0.2) {
					d3.push([i, Math.tan(i)]);
				}
				
			
				var sales_charts = $('#sales-charts').css({'width':'100%' , 'height':'220px'});
				$.plot("#sales-charts", [
					{ label: "Domains", data: d1 },
					{ label: "Hosting", data: d2 },
					{ label: "Services", data: d3 }
				], {
					hoverable: true,
					shadowSize: 0,
					series: {
						lines: { show: true },
						points: { show: true }
					},
					xaxis: {
						tickLength: 0
					},
					yaxis: {
						ticks: 10,
						min: -2,
						max: 2,
						tickDecimals: 3
					},
					grid: {
						backgroundColor: { colors: [ "#fff", "#fff" ] },
						borderWidth: 1,
						borderColor:'#555'
					}
				});
			
			
				$('#recent-box [data-rel="tooltip"]').tooltip({placement: tooltip_placement});
				function tooltip_placement(context, source) {
					var $source = $(source);
					var $parent = $source.closest('.tab-content')
					var off1 = $parent.offset();
					var w1 = $parent.width();
			
					var off2 = $source.offset();
					var w2 = $source.width();
			
					if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
					return 'left';
				}
			
			
				$('.dialogs,.comments').slimScroll({
					height: '300px'
			    });
				
				
				//Android's default browser somehow is confused when tapping on label which will lead to dragging the task
				//so disable dragging when clicking on label
				var agent = navigator.userAgent.toLowerCase();
				if("ontouchstart" in document && /applewebkit/.test(agent) && /android/.test(agent))
				  $('#tasks').on('touchstart', function(e){
					var li = $(e.target).closest('#tasks li');
					if(li.length == 0)return;
					var label = li.find('label.inline').get(0);
					if(label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation() ;
				});
			
				$('#tasks').sortable({
					opacity:0.8,
					revert:true,
					forceHelperSize:true,
					placeholder: 'draggable-placeholder',
					forcePlaceholderSize:true,
					tolerance:'pointer',
					stop: function( event, ui ) {//just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
						$(ui.item).css('z-index', 'auto');
					}
					}
				);
				$('#tasks').disableSelection();
				$('#tasks input:checkbox').removeAttr('checked').on('click', function(){
					if(this.checked) $(this).closest('li').addClass('selected');
					else $(this).closest('li').removeClass('selected');
				});
				
			
			});
			
			function iFrameHeight(obj) {  
				var cwin=obj; 
				if (document.getElementById){ 
					if (cwin && !window.opera){ 
					if (cwin.contentDocument && cwin.contentDocument.body.offsetHeight) 
						cwin.height = cwin.contentDocument.body.offsetHeight + 20; //FF NS 
					else if(cwin.Document && cwin.Document.body.scrollHeight) 
						cwin.height = cwin.Document.body.scrollHeight + 10;//IE 
					}else{ 
						if(cwin.contentWindow.document && cwin.contentWindow.document.body.scrollHeight) 
						cwin.height = cwin.contentWindow.document.body.scrollHeight;//Opera 
					} 
				} 
			}  
			
			function showUpdateDialog(){
				$("#passwordOld").val("");
				$("#passwordNew1").val("");
				$("#passwordNew2").val("");
				$('#update').modal('show');
			}
			
			$("#submitBtn").bind("click",function(){
				var passwordOld = $("#passwordOld").val();
				var password = $("#passwordNew1").val();
				var password2 = $("#passwordNew2").val();
			    if(!passwordOld) {
			      alert("请输入原密码!");
			      return;
			    } 
			    if(!password) {
			      	alert("请输入新密码!");
			      	return;
			    } else if(/\s/.test(password)) {
			      	alert('新密码不能包含空格!');
			      	return;
			    } else if(/^\d+$/.test(password)) {
			      	alert('新密码不能全部是数字!');
			      	return;
			    } else if(/^[a-z]+$/i.test(password)) {
			      	alert('新密码不能全部是字母!');
			      	return;
			    } else if(!/^[a-z\d]+$/i.test(password)) {
	      			alert('新密码只能由含数字和字母组成!');
	      			return;
			    } 
			    if(!password2) {
			      	alert("请再次输入新密码!");
			      	return;
			    } else if(password!=password2) {
			      	alert('两次输入新密码不一致!');
			      	return;
			    }
			    $.ajax({
		    		url : '<%=basePath%>/updatePassword.do',
		    		type : 'post',
		    		data : {passwordOld:passwordOld,password:password},
		    		datatype : 'json',
		    		error:function(){ 
		    			alert("请求失败,请稍后再试...");
		          	},
		    		success : function(result){
	    				alert(result.msg);
	    				if(result.success == true){
		    				window.location.href="<%=basePath%>/logout.do";
		    			}
		    		}
		    	});
			});
		</script>
</body>
</html>

