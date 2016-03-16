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
  </head>
<body style="padding:0">
<div style="padding:10px;clear: both;">
    <div id="psLine" style="height:600px;"></div>
</div>
</body>
<script src="../commons/js/echarts/jquery-1.11.3.min.js"></script>
<script src="../commons/js/echarts/echarts-all.js"></script>
<script type="text/javascript">
    //图表
    var psLineChar = echarts.init(document.getElementById('psLine'));
    //定义图表options  
    var options = {  
        title : {  
            text : "未来一周气温变化",  
            subtext : "纯属虚构",  
            sublink : "http://www.baidu.com"  
        },  
        tooltip : {  
            trigger : 'axis'  
        },  
        legend : {  
            data : [ "最高气温" ]  
        },  
        toolbox : {  
            show : true,  
            feature : {  
                mark : {  
                    show : true  
                },  
                dataView : {  
                    show : true,  
                    readOnly : false  
                },  
                magicType : {  
                    show : true,  
                    type : [ 'line', 'bar' ]  
                },  
                restore : {  
                    show : true  
                },  
                saveAsImage : {  
                    show : true  
                }  
            }  
        },  
        calculable : true,  
        xAxis : [ {  
            type : 'category',  
            boundaryGap : false,  
            data : [ '1', '2', '3', '4', '5', '6', '7' ]  
        } ],  
        yAxis : [ {  
            type : 'value',  
            axisLabel : {  
                formatter : '{value} °C'  
            },  
            splitArea : {  
                show : true  
            }  
        } ],  
        grid : {  
            width : '90%'  
        },  
        series : [ {  
            name : '最高气温',  
            type : 'line',  
            data : [ 11, 22, 33, 44, 55, 33, 44 ],//必须是Integer类型的,String计算平均值会出错  
            markPoint : {  
                data : [ {  
                    type : 'max',  
                    name : '最大值'  
                }, {  
                    type : 'min',  
                    name : '最小值'  
                } ]  
            },  
            markLine : {  
                data : [ {  
                    type : 'average',  
                    name : '平均值'  
                } ]  
            }  
        } ]  
    };  
    //查询
    function loadDrugs() {
        psLineChar.clear();
        psLineChar.showLoading({text: '正在努力的读取数据中...'});
        $.getJSON('../echartsOne', function (data) {
                psLineChar.setOption(data.options, true);
                psLineChar.hideLoading();
        });
    }
    //载入图表
    loadDrugs();
</script>