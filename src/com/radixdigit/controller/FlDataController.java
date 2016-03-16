package com.radixdigit.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.LineType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Effect;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.MarkLine;
import com.github.abel533.echarts.series.MarkPoint;
import com.github.abel533.echarts.style.ItemStyle;
import com.google.gson.Gson;
import com.radixdigit.base.BaseController;
import com.radixdigit.entity.FlData;
import com.radixdigit.entity.FlGlobalInfo;
import com.radixdigit.entity.MarkLineData;
import com.radixdigit.services.FlDataService;

@Controller
@Scope("prototype")
public class FlDataController extends BaseController<FlData>{

	@Autowired
	private FlDataService flDataService;
	
	@ResponseBody
	@RequestMapping(value="/addData")
	public String addData(@RequestParam("tsort") String tsort,@RequestParam("type") String type,@RequestParam("baseCode") String baseCode){
		Gson gson = new Gson();
        
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> parammap = new HashMap<String,Object>();
    	parammap.put("baseCode", baseCode);
    	parammap.put("type", type);
    	parammap.put("iflag", "2");
    	parammap.put("bc", baseCode.substring(0, 6));
    	
    	FlData data = new FlData();
    	FlData data2 = new FlData();
    	Double zl = 0d;
    	String zll = "";
    	if(("0").equals(tsort)){
			data = flDataService.selectAddData(parammap);
			data2 = flDataService.firstData(parammap);
			if(data != null && data2!=null){
				zl = Double.parseDouble(data.getYaxis())-Double.parseDouble(data2.getYaxis());
			}
		}else{
			data = flDataService.selectDefAddData(parammap);
			//flDataService.updateFlData(parammap);
		}
    	if(zl.toString().length()>11){
    		zll = String.format("%.8f", zl*100);
    	}else{
    		zll = zl.toString();
    	}
    	if(data != null){
	        map.put("yaxis", data.getYaxis());
			map.put("zl", zll);
    	}else{
    		map.put("zl", "0.0");
    	}
    	FlData datatime = flDataService.selectTimeData(parammap);
    	if(datatime != null){
    		map.put("xdate", datatime.getXdate());
    		map.put("fdateTime", datatime.getFdateTime());
    	}
		
		return gson.toJson(map);
	}
	
	
	@ResponseBody
	@RequestMapping(value="/tChangeData")
	public String tChangeData(@RequestParam("type") String type,@RequestParam("baseCode") String baseCode){
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> parammap = new HashMap<String,Object>();
    	parammap.put("baseCode", baseCode);
    	parammap.put("type", type);
        FlData data = flDataService.selectChangeData(parammap);
        if(data != null){
	        map.put("defmean", data.getDefmean());
			map.put("depepre", data.getDepepre());
			map.put("changeAvalue", data.getChangeAvalue());
			map.put("changeShot", data.getChangeShot());
        }else{
        	map.put("defmean", "0");
			map.put("depepre", "0");
			map.put("changeAvalue", "0");
			map.put("changeShot", "0");
        }
		return gson.toJson(map);
	}
	
	//得到全局值(开始时间)
	@ResponseBody
	@RequestMapping(value="/globalInfo")
	public String globalInfo(@RequestParam("baseCode") String baseCode,@RequestParam("strd") String strd){
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> parammap = new HashMap<String,Object>();
    	parammap.put("baseCode", baseCode);
    	parammap.put("bc", baseCode.substring(0, 6));
    	FlGlobalInfo fg = flDataService.globalData(parammap);
    	FlData fd = flDataService.timeNow(parammap);
    	FlData ftime = flDataService.selectTimeData(parammap);
    	
    	
    	Double nzl = 0d;
    	parammap.put("type", "0");
    	FlData ndata = flDataService.selectAddData(parammap);
    	FlData ndata2 = flDataService.firstData(parammap);
		if(ndata != null && ndata2!=null){
			nzl = Double.parseDouble(ndata.getYaxis())-Double.parseDouble(ndata2.getYaxis());
		}
    	
		Double ezl = 0d;
    	parammap.put("type", "1");
    	FlData edata = flDataService.selectAddData(parammap);
    	FlData edata2 = flDataService.firstData(parammap);
		if(edata != null && edata2!=null){
			ezl = Double.parseDouble(edata.getYaxis())-Double.parseDouble(edata2.getYaxis());
		}
		
		Double uzl = 0d;
    	parammap.put("type", "2");
    	FlData udata = flDataService.selectAddData(parammap);
    	FlData udata2 = flDataService.firstData(parammap);
		if(udata != null && udata2!=null){
			uzl = Double.parseDouble(udata.getYaxis())-Double.parseDouble(udata2.getYaxis());
		}
		
		Double ddd = Math.sqrt(nzl*nzl+ezl*ezl+uzl*uzl);
		
		//第一个3D距离
		String[] std = strd.split(",");
    	String jstrd = "";
    	if(std.length==6){
    		String[] senstr = strd.split(",", 2);
    		jstrd = senstr[1];
    		String[] vst = jstrd.split(",");
    		
    		Double[] ds=new Double[vst.length];
    		for(int i=0;i<vst.length;i++){
    		  ds[i]=Double.valueOf(vst[i]);
    		}
    		Arrays.sort(ds);
    		jstrd = jstrd+","+ddd*100;
    		ddd = ds[3];
        	map.put("jstrd",jstrd);
    	}
    	
		map.put("ddd", ddd);
        if(fg != null){
        	map.put("changeAvalue", fd.getChangeAvalue());
        	map.put("changeShot", fd.getChangeShot());
	        map.put("startTime", fg.getStartTime());
        }else{
        	map.put("changeAvalue", "0");
        	map.put("changeShot", "0");
        	map.put("startTime", "无");
        }
        if(ftime != null){
	        map.put("fdateTime", ftime.getFdateTime());
        }else{
        	map.put("fdateTime", "无");
        }
		return gson.toJson(map);
	}
	
	@ResponseBody
	@RequestMapping(value="/neuData")
	public String neuData(@RequestParam("baseCode") String baseCode){
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String,Object>();
		
		Map<String,Object> parammap = new HashMap<String,Object>();
		parammap.put("baseCodeAll", baseCode);
    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"01");
    	parammap.put("type", "0");
        FlData data = flDataService.selectDefAddData(parammap);
        FlGlobalInfo fgi = flDataService.selectPointState(parammap);
		if (data != null && fgi != null &&  !fgi.getPointState().equals("1")) {
				// 点1北
				String oneNorth = data != null?data.getYaxis():"0.01";
				parammap.clear();
				parammap.put("baseCode", baseCode.substring(0, 6) + "_" + "01");
				parammap.put("type", "1");
				FlData data1 = flDataService.selectDefAddData(parammap);
				// 点1东
				String oneEast = data1 != null?data1.getYaxis():"0.01";
				parammap.clear();
				parammap.put("baseCode", baseCode.substring(0, 6) + "_" + "01");
				parammap.put("type", "2");
				FlData data2 = flDataService.selectDefAddData(parammap);
				// 点1天
				String oneU = data2 != null?data2.getYaxis():"0.01";

				map.put("onep", "监测站1变形量(" + 
				oneNorth + "," 
						+ oneEast + ","
						+ oneU + ")");
		}else if(fgi != null && fgi.getPointState().equals("1")) {
			map.put("onep", "监测站1变形量(异常)");
		}else if(fgi == null){
			map.put("onep", "监测站1变形量(异常)");
		}else{
			map.put("onep", "监测站1变形量(-0.181,0.0105,0.0236)");
		}
        /**************2******************/
        parammap.clear();
    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"02");
    	parammap.put("type", "0");
        FlData data11 = flDataService.selectDefAddData(parammap);
        FlGlobalInfo fgi11 = flDataService.selectPointState(parammap);
		if (data11 != null && fgi11 != null && !fgi11.getPointState().equals("1")) {
	        //点2北
	        String twoNorth = data11 != null?data11.getYaxis():"0.01";
	        parammap.clear();
	    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"02");
	    	parammap.put("type", "1");
	        FlData data12 = flDataService.selectDefAddData(parammap);
	        //点2东
	        String twoEast = data12 != null?data12.getYaxis():"0.01";
	        parammap.clear();
	        parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"02");
	    	parammap.put("type", "2");
	        FlData data13 = flDataService.selectDefAddData(parammap);
	        //点2天
	        String twoU = data13 != null?data13.getYaxis():"0.01";
	        map.put("twop", "监测站2变形量("+twoNorth+","+twoEast+","+twoU+")");
		   }else if(fgi11 != null && fgi11.getPointState().equals("1")) {
				map.put("twop", "监测站2变形量(异常)");
		   }else if(fgi11 == null){
				map.put("twop", "监测站2变形量(异常)");
			}else{
				map.put("twop", "监测站2变形量(-0.031,0.2105,0.1027)");
			}
        /**************3******************/
        parammap.clear();
    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"03");
    	parammap.put("type", "0");
        FlData data21 = flDataService.selectDefAddData(parammap);
        FlGlobalInfo fgi21 = flDataService.selectPointState(parammap);
        if(data21 != null && fgi21 != null &&  !fgi21.getPointState().equals("1")){
	        //点2北
	        String threeNorth = data21!=null?data21.getYaxis():"0.01";
	        parammap.clear();
	    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"03");
	    	parammap.put("type", "1");
	        FlData data22 = flDataService.selectDefAddData(parammap);
	        //点2东
	        String threeEast = data22 != null?data22.getYaxis():"0.01";
	        parammap.clear();
	        parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"03");
	    	parammap.put("type", "2");
	        FlData data23 = flDataService.selectDefAddData(parammap);
	        //点2天
	        String threeU = data23 != null?data23.getYaxis():"0.01";
	        map.put("threep", "监测站3变形量("+threeNorth+","+threeEast+","+threeU+")");
           }else if(fgi21 != null && fgi21 != null &&  fgi21.getPointState().equals("1")) {
				map.put("threep", "监测站3变形量(异常)");
           }else if(fgi21 == null){
				map.put("threep", "监测站3变形量(异常)");
			}else{
				map.put("threep", "监测站3变形量(-0.137,0.2205,0.2037)");
			}
        /**************4******************/
        parammap.clear();
    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"04");
    	parammap.put("type", "0");
        FlData data31 = flDataService.selectDefAddData(parammap);
        FlGlobalInfo fgi31 = flDataService.selectPointState(parammap);
        if(data31 != null && fgi31 != null && !fgi31.getPointState().equals("1")){
	        //点2北
	        String fourNorth = data31 != null?data31.getYaxis():"0.01";
	        parammap.clear();
	    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"04");
	    	parammap.put("type", "1");
	        FlData data32 = flDataService.selectDefAddData(parammap);
	        //点2东
	        String fourEast = data32 != null?data32.getYaxis():"0.01";
	        parammap.clear();
	        parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"04");
	    	parammap.put("type", "2");
	        FlData data33 = flDataService.selectDefAddData(parammap);
	        //点2天
	        String fourU = data33 != null?data33.getYaxis():"0.01";
	        map.put("fourp", "监测站4变形量("+fourNorth+","+fourEast+","+fourU+")");
			}else if(fgi31 != null && fgi31.getPointState().equals("1")) {
				map.put("fourp", "监测站4变形量(异常)");
			 }else if(fgi31 == null){
					map.put("fourp", "监测站4变形量(异常)");
			}else{
				map.put("fourp", "监测站4变形量(-0.137,0.2205,0.2037)");
			}
        /**************5******************/
        parammap.clear();
    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"05");
    	parammap.put("type", "0");
        FlData data41 = flDataService.selectDefAddData(parammap);
        FlGlobalInfo fgi41 = flDataService.selectPointState(parammap);
        if(data41 != null && fgi41 != null && !fgi41.getPointState().equals("1")){
	        //点2北
	        String fiveNorth = data41 != null?data41.getYaxis():"0.01";
	        parammap.clear();
	    	parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"05");
	    	parammap.put("type", "1");
	        FlData data42 = flDataService.selectDefAddData(parammap);
	        //点2东
	        String fiveEast = data42 != null?data42.getYaxis():"0.01";
	        parammap.clear();
	        parammap.put("baseCode", baseCode.substring(0, 6)+"_"+"05");
	    	parammap.put("type", "2");
	        FlData data43 = flDataService.selectDefAddData(parammap);
	        //点2天
	        String fiveU = data43!=null?data43.getYaxis():"0.01";
	        map.put("fivep", "监测站5变形量("+fiveNorth+","+fiveEast+","+fiveU+")");
		}else if(fgi41 != null && fgi41.getPointState().equals("1")) {
			map.put("fivep", "监测站5变形量(异常)");
		 }else if(fgi41 == null){
				map.put("fivep", "监测站5变形量(异常)");
		}else{
			map.put("fivep", "监测站5变形量(-0.437,0.2705,0.3077)");
		}
		return gson.toJson(map);
	}
	
	/**
	 * 站心坐标折线图
	 * @param flData
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/northLineSite")
	public String northLineSite(@RequestParam("tsort") String tsort,@RequestParam("type") String type,@RequestParam("baseCode") String baseCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("baseCode", baseCode);
		map.put("type", type);
		List<FlData> data = new ArrayList<FlData>();
		if(("0").equals(tsort)){
			data = flDataService.selectLineData(map);
		}else{
			data = flDataService.selectDefLineData(map);
		}
		List<String> xlist = new ArrayList<String>();
		List<Object> ylist = new ArrayList<Object>();
		if(data != null && !data.isEmpty()){
//			for(FlData fdt:data){
//				xlist.add(fdt.getXdate());
//				ylist.add(fdt.getYaxis());
//			}
			for (int i = 0; i < 30; i++) {
				xlist.add("0:00");
				ylist.add("0");
				}
		}else{
			for (int i = 0; i < 360; i++) {
			xlist.add(i + "");
			}
			Random random = new Random();// 定义随机类
			for (int i = 0; i < 360; i++) {
				ylist.add(random.nextInt(100));
			}
		}
		
		Option option = new Option();
		Gson gson = new Gson();
		if("0".equals(type)){
			option.title("北(N)","单位:厘米");
		}else if("1".equals(type)){
			option.title("东(E)","单位:厘米");
		}else if("2".equals(type)){
			option.title("天(U)","单位:厘米");
		}
        option.tooltip().trigger(Trigger.axis);
        
        Line l1 = new Line();
        
        if(("0").equals(tsort)){
        	option.legend("站心坐标");
        	 l1.setName("站心坐标");
		}else{
			option.legend("变形量");
			l1.setName("变形量");
		}
        option.toolbox().show(true).feature(Tool.dataView,
                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        //{boolean} false，是否启用拖拽重计算特性，默认关闭
        option.calculable(true);
        
       
        option.xAxis(new CategoryAxis().boundaryGap(false).data(xlist.toArray()));
        option.yAxis(new ValueAxis());
       
        l1.smooth(true);
        
        MarkLine markLine = new MarkLine();
        MarkLineData markLineData = new MarkLineData();
        markLineData.setName("警戒线起点");
        markLineData.setxAxis(-1);
        markLineData.setyAxis("90");
        List<MarkLineData> mlist = new ArrayList<MarkLineData>();
        mlist.add(markLineData);
        
        MarkLineData markLineData2 = new MarkLineData();
        markLineData2.setName("警戒线终点");
        markLineData2.setxAxis(9999);
        markLineData2.setyAxis("90");
        markLineData.setValue("警戒线");
        mlist.add(markLineData2);
        //是否是虚线true实线
        //markLine.smooth(true);
        //炫光特效
        Effect effect = new Effect();
        effect.show(true);
        //scaleSize 放大倍数，以markLine lineWidth为基准
        effect.scaleSize(2);
        //period 运动周期，无单位，值越大越慢，默认为15 
        effect.period(30);
        //color 炫光颜色，默认跟随markLine itemStyle定义颜色, 
        effect.color("#C00000");
        //shadowBlur 光影模糊度，默认根据scaleSize计算 
        effect.shadowBlur(10);
        markLine.effect(effect);
        //标线图形样式属性
        ItemStyle ist = new ItemStyle();
        //solid实线
        ist.normal().borderWidth(1).lineStyle().shadowBlur(10).type(LineType.solid);
        markLine.setItemStyle(ist);
        
        markLine.data(mlist);
        l1.setMarkLine(markLine);
        /*******************************markPoint*********************************/
        MarkPoint markPoint = new MarkPoint();
        //标志图形类型
        markPoint.symbol(Symbol.emptyCircle);
        Map<String,Object> pmap = new HashMap<String,Object>();
        pmap.put("value", "警戒线");
        pmap.put("xAxis", "9999");
        pmap.put("yAxis", "90");
        markPoint.data(gson.toJson(pmap));
        System.out.println(gson.toJson(pmap));
        l1.setMarkPoint(markPoint);
        
        l1.setData(ylist);
        option.series(l1);
        System.out.println(gson.toJson(option));
	    return gson.toJson(option);  
	}
	
	@ResponseBody
	@RequestMapping(value="/dayLineSite")
	public String dayLineSite(FlData flData){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("baseCode", flData.getBaseCode());
		map.put("type", flData.getType());
		List<FlData> data = flDataService.selectLineData(map);
		
		Option option = new Option();
		Gson gson = new Gson();
		option.title("天(D)");
        option.tooltip().trigger(Trigger.axis);
        option.legend("站心坐标");
        option.toolbox().show(true).feature(Tool.mark,
                Tool.dataView,
                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        //{boolean} false，是否启用拖拽重计算特性，默认关闭
        option.calculable(true);
        
        List<String> xlist = new ArrayList<String>();
        for(int i = 0;i<360;i++){
        	xlist.add(i+"");
        }
        option.xAxis(new CategoryAxis().boundaryGap(false).data(xlist.toArray()));
        option.yAxis(new ValueAxis());

        Line l1 = new Line("站心坐标");
        
        l1.smooth(true).symbol(Symbol.circle);
        
        MarkLine markLine = new MarkLine();
        MarkLineData markLineData = new MarkLineData();
        markLineData.setName("警戒线起点");
        markLineData.setxAxis(-1);
        markLineData.setyAxis("90");
        List<MarkLineData> mlist = new ArrayList<MarkLineData>();
        mlist.add(markLineData);
        
        MarkLineData markLineData2 = new MarkLineData();
        markLineData2.setName("警戒线终点");
        markLineData2.setxAxis(9999);
        markLineData2.setyAxis("90");
        markLineData.setValue("警戒线");
        mlist.add(markLineData2);
        //是否是虚线true实线
        //markLine.smooth(true);
        //炫光特效
        Effect effect = new Effect();
        effect.show(true);
        //scaleSize 放大倍数，以markLine lineWidth为基准
        effect.scaleSize(2);
        //period 运动周期，无单位，值越大越慢，默认为15 
        effect.period(30);
        //color 炫光颜色，默认跟随markLine itemStyle定义颜色, 
        effect.color("#C00000");
        //shadowBlur 光影模糊度，默认根据scaleSize计算 
        effect.shadowBlur(10);
        markLine.effect(effect);
        
        //标线图形样式属性
        ItemStyle ist = new ItemStyle();
        //solid实线
        ist.normal().borderWidth(1).lineStyle().shadowBlur(10).type(LineType.solid);
        markLine.setItemStyle(ist);
        
        markLine.data(mlist);
        l1.setMarkLine(markLine);
        /*******************************markPoint*********************************/
        MarkPoint markPoint = new MarkPoint();
        //标志图形类型
        markPoint.symbol(Symbol.emptyCircle);
        Map<String,Object> pmap = new HashMap<String,Object>();
        pmap.put("value", "警戒线");
        pmap.put("xAxis", "9999");
        pmap.put("yAxis", "90");
        markPoint.data(gson.toJson(pmap));
        System.out.println(gson.toJson(pmap));
        l1.setMarkPoint(markPoint);
        Random random=new Random();// 定义随机类
        List<Object> tt = new ArrayList<Object>();
        for(int i=0;i<360;i++){
        	tt.add(random.nextInt(100));
        }
        l1.setData(tt);
        option.series(l1);
        System.out.println(gson.toJson(option));
	    return gson.toJson(option);  
	}
	
	public static void main(String args[]){
		double x1 = 0.098777700026;
		String[] str = new String[]{"0.098777700026","0.098777700027","0.098777700028","0.098777700029","0.098777700030"};
		String strs = "0.098777700026,0.098777700027,0.098777700028,0.098777700029";
		String[] ss = strs.split(",",2);
		
		System.out.println(ss[1]);
	}
}
