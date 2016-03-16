//package com.radixdigit.controller;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Scope;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.github.abel533.echarts.Option;
//import com.github.abel533.echarts.axis.CategoryAxis;
//import com.github.abel533.echarts.axis.ValueAxis;
//import com.github.abel533.echarts.code.LineType;
//import com.github.abel533.echarts.code.Magic;
//import com.github.abel533.echarts.code.Symbol;
//import com.github.abel533.echarts.code.Tool;
//import com.github.abel533.echarts.code.Trigger;
//import com.github.abel533.echarts.feature.MagicType;
//import com.github.abel533.echarts.series.Effect;
//import com.github.abel533.echarts.series.Line;
//import com.github.abel533.echarts.series.MarkLine;
//import com.github.abel533.echarts.series.MarkPoint;
//import com.github.abel533.echarts.style.ItemStyle;
//import com.google.gson.Gson;
//import com.radixdigit.base.BaseController;
//import com.radixdigit.entity.FlData;
//import com.radixdigit.entity.MarkLineData;
//import com.radixdigit.services.FlDataService;
//
//@Controller
//@Scope("prototype2")
//public class FlDataController2 extends BaseController<FlData>{
//
//	@Autowired
//	private FlDataService flDataService;
//	
//	@ResponseBody
//	@RequestMapping(value="/addData")
//	public String addData(FlData flData){
//		Gson gson = new Gson();
//		Random random = new Random();// 定义随机类
//		String yaxis = random.nextInt(100) + "";
//		
//		Calendar calendar=null;
//        calendar =new GregorianCalendar();//子类实例化
//        //当前时间
//        String ndate = calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
//        
//		Map<String,Object> map = new HashMap<String,Object>();
//		
////		Map<String,Object> parammap = new HashMap<String,Object>();
////    	parammap.put("baseCode", flData.getBaseCode());
////    	parammap.put("type", flData.getType());
////        FlData data = flDataService.selectAddData(parammap);
////        map.put("yaxis", data.getYaxis());
////		map.put("xdate", data.getXdate());
//		
//		map.put("yaxis", yaxis);
//		map.put("xdate", ndate);
//		return gson.toJson(map);
//	}
//	/**
//	 * 站心坐标折线图
//	 * @param flData
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value="/northLineSite")
//	public String northLineSite(FlData flData){
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("baseCode", flData.getBaseCode());
//		map.put("type", flData.getType());
//		List<FlData> data = flDataService.selectLineData(map);
//		List<String> xlist = new ArrayList<String>();
//		List<Object> ylist = new ArrayList<Object>();
////		for(FlData fdt:data){
////			xlist.add(fdt.getXdate());
////			ylist.add(fdt.getYaxis());
////		}
//		
//		for (int i = 0; i < 360; i++) {
//			xlist.add(i + "");
//		}
//
//		Random random = new Random();// 定义随机类
//		for (int i = 0; i < 360; i++) {
//			ylist.add(random.nextInt(100));
//		}
//		
//		Option option = new Option();
//		Gson gson = new Gson();
//		option.title("北(N)");
//        option.tooltip().trigger(Trigger.axis);
//        option.legend("站心坐标");
//        option.toolbox().show(true).feature(Tool.mark,
//                Tool.dataView,
//                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
//                Tool.restore,
//                Tool.saveAsImage).padding(20);
//        //{boolean} false，是否启用拖拽重计算特性，默认关闭
//        option.calculable(true);
//        
//       
//        //option.xAxis(new CategoryAxis().boundaryGap(false).data("10.50", "10.51", "10.52","10.53","10.54","10.55","10.56","10.57","10.58","10.59","10.60"));
//        option.xAxis(new CategoryAxis().boundaryGap(false).data(xlist.toArray()));
//        option.yAxis(new ValueAxis());
//
//        Line l1 = new Line("站心坐标");
//        
//        l1.smooth(true).symbol(Symbol.none);
//        //l1.data(10, 12, 21, 54, 260, 830, 710);
//        
//        MarkLine markLine = new MarkLine();
//        MarkLineData markLineData = new MarkLineData();
//        markLineData.setName("警戒线起点");
//        markLineData.setxAxis(-1);
//        markLineData.setyAxis("90");
//        List<MarkLineData> mlist = new ArrayList<MarkLineData>();
//        mlist.add(markLineData);
//        
//        MarkLineData markLineData2 = new MarkLineData();
//        markLineData2.setName("警戒线终点");
//        markLineData2.setxAxis(9999);
//        markLineData2.setyAxis("90");
//        markLineData.setValue("警戒线");
//        mlist.add(markLineData2);
//        //是否是虚线true实线
//        //markLine.smooth(true);
//        //炫光特效
//        Effect effect = new Effect();
//        effect.show(true);
//        //scaleSize 放大倍数，以markLine lineWidth为基准
//        effect.scaleSize(2);
//        //period 运动周期，无单位，值越大越慢，默认为15 
//        effect.period(30);
//        //color 炫光颜色，默认跟随markLine itemStyle定义颜色, 
//        effect.color("#C00000");
//        //shadowBlur 光影模糊度，默认根据scaleSize计算 
//        effect.shadowBlur(10);
//        markLine.effect(effect);
//        //标线图形样式属性
//        ItemStyle ist = new ItemStyle();
//        //solid实线
//        ist.normal().borderWidth(1).lineStyle().shadowBlur(10).type(LineType.solid);
//        markLine.setItemStyle(ist);
//        
//        markLine.data(mlist);
//        l1.setMarkLine(markLine);
//        /*******************************markPoint*********************************/
//        MarkPoint markPoint = new MarkPoint();
//        //标志图形类型
//        markPoint.symbol(Symbol.emptyCircle);
//        Map<String,Object> pmap = new HashMap<String,Object>();
//        pmap.put("value", "警戒线");
//        pmap.put("xAxis", "9999");
//        pmap.put("yAxis", "90");
//        markPoint.data(gson.toJson(pmap));;
//        System.out.println(gson.toJson(pmap));
//        l1.setMarkPoint(markPoint);
//        
//        l1.setData(ylist);
//        option.series(l1);
//        System.out.println(gson.toJson(option));
//	    return gson.toJson(option);  
//	}
//	
//	@ResponseBody
//	@RequestMapping(value="/dayLineSite")
//	public String dayLineSite(FlData flData){
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("baseCode", flData.getBaseCode());
//		map.put("type", flData.getType());
//		List<FlData> data = flDataService.selectLineData(map);
//		
//		Option option = new Option();
//		Gson gson = new Gson();
//		option.title("天(D)");
//        option.tooltip().trigger(Trigger.axis);
//        option.legend("站心坐标");
//        option.toolbox().show(true).feature(Tool.mark,
//                Tool.dataView,
//                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
//                Tool.restore,
//                Tool.saveAsImage).padding(20);
//        //{boolean} false，是否启用拖拽重计算特性，默认关闭
//        option.calculable(true);
//        
//        List<String> xlist = new ArrayList<String>();
//        for(int i = 0;i<360;i++){
//        	xlist.add(i+"");
//        }
//        //option.xAxis(new CategoryAxis().boundaryGap(false).data("10.50", "10.51", "10.52","10.53","10.54","10.55","10.56","10.57","10.58","10.59","10.60"));
//        option.xAxis(new CategoryAxis().boundaryGap(false).data(xlist.toArray()));
//        option.yAxis(new ValueAxis());
//
//        Line l1 = new Line("站心坐标");
//        
//        l1.smooth(true).symbol(Symbol.none);
//        //l1.data(10, 12, 21, 54, 260, 830, 710);
//        
//        MarkLine markLine = new MarkLine();
//        MarkLineData markLineData = new MarkLineData();
//        markLineData.setName("警戒线起点");
//        markLineData.setxAxis(-1);
//        markLineData.setyAxis("90");
//        List<MarkLineData> mlist = new ArrayList<MarkLineData>();
//        mlist.add(markLineData);
//        
//        MarkLineData markLineData2 = new MarkLineData();
//        markLineData2.setName("警戒线终点");
//        markLineData2.setxAxis(9999);
//        markLineData2.setyAxis("90");
//        markLineData.setValue("警戒线");
//        mlist.add(markLineData2);
//        //是否是虚线true实线
//        //markLine.smooth(true);
//        //炫光特效
//        Effect effect = new Effect();
//        effect.show(true);
//        //scaleSize 放大倍数，以markLine lineWidth为基准
//        effect.scaleSize(2);
//        //period 运动周期，无单位，值越大越慢，默认为15 
//        effect.period(30);
//        //color 炫光颜色，默认跟随markLine itemStyle定义颜色, 
//        effect.color("#C00000");
//        //shadowBlur 光影模糊度，默认根据scaleSize计算 
//        effect.shadowBlur(10);
//        markLine.effect(effect);
//        
//        //标线图形样式属性
//        ItemStyle ist = new ItemStyle();
//        //solid实线
//        ist.normal().borderWidth(1).lineStyle().shadowBlur(10).type(LineType.solid);
//        markLine.setItemStyle(ist);
//        
//        markLine.data(mlist);
//        l1.setMarkLine(markLine);
//        /*******************************markPoint*********************************/
//        MarkPoint markPoint = new MarkPoint();
//        //标志图形类型
//        markPoint.symbol(Symbol.emptyCircle);
//        Map<String,Object> pmap = new HashMap<String,Object>();
//        pmap.put("value", "警戒线");
//        pmap.put("xAxis", "9999");
//        pmap.put("yAxis", "90");
//        markPoint.data(gson.toJson(pmap));;
//        System.out.println(gson.toJson(pmap));
//        l1.setMarkPoint(markPoint);
//        Random random=new Random();// 定义随机类
//        List<Object> tt = new ArrayList<Object>();
//        for(int i=0;i<360;i++){
//        	tt.add(random.nextInt(100));
//        }
//        l1.setData(tt);
//        option.series(l1);
//        System.out.println(gson.toJson(option));
//	    return gson.toJson(option);  
//	}
//	
//	public static void main(String args[]){
//		 Calendar calendar=null;
//	        calendar =new GregorianCalendar();//子类实例化
//	        System.out.println("年： "+calendar.get(Calendar.YEAR));
//	        System.out.println("月 "+(calendar.get(Calendar.MONTH)+1));
//	        System.out.println("日： "+calendar.get(Calendar.DAY_OF_MONTH));
//	        System.out.println("时： "+calendar.get(Calendar.HOUR_OF_DAY));
//	        System.out.println("分： "+calendar.get(Calendar.MINUTE));
//	        System.out.println("秒： "+calendar.get(Calendar.SECOND));
//	        System.out.println("毫秒 "+calendar.get(Calendar.MILLISECOND));
//	}
//}
