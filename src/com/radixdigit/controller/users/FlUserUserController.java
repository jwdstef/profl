package com.radixdigit.controller.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Symbol;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.data.LineData;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.MarkLine;
import com.google.gson.Gson;
import com.radixdigit.base.BaseController;
import com.radixdigit.entity.FlUserUser;
import com.radixdigit.entity.MarkLineData;
import com.radixdigit.entity.NetTree;
import com.radixdigit.services.FlPowerNetService;
import com.radixdigit.services.FlUserUserService;

@Controller
@Scope("prototype")
public class FlUserUserController extends BaseController<FlUserUser>{
	
	@Autowired
	private FlUserUserService flUserUserService;
	
	@Autowired
	private FlPowerNetService flPowerNetService;
	
	
	@RequestMapping(value="/login")
	public ModelAndView login(FlUserUser user,HttpSession session){
		List<NetTree> nlist = new ArrayList<NetTree>();
		nlist = flPowerNetService.selectLines();
		
		List<NetTree> towerList = new ArrayList<NetTree>();
		List<NetTree> pointList = new ArrayList<NetTree>();
		for(NetTree line:nlist){
			towerList = flPowerNetService.selectTowers(new NetTree(line.getName()));
			line.setTowerList(towerList);
			for(NetTree tower:towerList){
				pointList = flPowerNetService.selectPoints(new NetTree(tower.getName()));
				tower.setPointList(pointList);
			}
		}
		
		ModelAndView mv = new ModelAndView();
		FlUserUser flUser = flUserUserService.login(user);
		if((flUser != null) && flUser.getUserId() != null){
			session.setAttribute("user", user);
			mv.addObject("lines", nlist);
			mv.setViewName("jsp/index");
		}else{
			mv.addObject("messageFlag", 1);
			mv.setViewName("login");
		}
		return mv;
	}

	
	@RequestMapping(value="/userList")
	public ModelAndView pageList(FlUserUser user){
		List<FlUserUser> userList = flUserUserService.listPageUser(user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jsp/user_list");
		mv.addObject("userList", userList);
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 展示图表
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/echartsOne")
	public String echartsOne(HttpServletRequest request,HttpServletResponse response){
		Option option = new Option();
		Gson gson = new Gson();
		option.title("北");
        option.tooltip().trigger(Trigger.axis);
        option.legend("成交");
        option.toolbox().show(true).feature(Tool.mark,
                Tool.dataView,
                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        option.calculable(true);
        List<String> xlist = new ArrayList<String>();
        for(int i = 0;i<360;i++){
        	xlist.add(i+"");
        }
        //option.xAxis(new CategoryAxis().boundaryGap(false).data("10.50", "10.51", "10.52","10.53","10.54","10.55","10.56","10.57","10.58","10.59","10.60"));
        option.xAxis(new CategoryAxis().boundaryGap(false).data(xlist.toArray()));
        option.yAxis(new ValueAxis());

        Line l1 = new Line("成交");
        l1.smooth(true).symbol(Symbol.none).itemStyle().normal().areaStyle().typeDefault();
        //l1.data(10, 12, 21, 54, 260, 830, 710);
        
        MarkLine markLine = new MarkLine();
        MarkLineData markLineData = new MarkLineData();
        markLineData.setName("警戒线起点");
        markLineData.setxAxis(-1);
        markLineData.setyAxis("90");
        markLineData.setValue("警戒线");
        List<MarkLineData> mlist = new ArrayList<MarkLineData>();
        mlist.add(markLineData);
        
        MarkLineData markLineData2 = new MarkLineData();
        markLineData2.setName("警戒线起点");
        markLineData2.setxAxis(9999);
        markLineData2.setyAxis("90");
        markLineData.setValue("警戒线");
        mlist.add(markLineData2);
        
        markLine.data(mlist);
        l1.setMarkLine(markLine);
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
	     
	/**
	 * 展示图表
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/echartsTwo")
	public String echartsTwo(HttpServletRequest request,HttpServletResponse response){
		Option option = new Option();
		Gson gson = new Gson();
		option.tooltip().trigger(Trigger.axis);
        option.legend("邮件营销", "联盟广告", "直接访问", "搜索引擎");
        option.toolbox().show(true);
        option.calculable(true);
        option.xAxis(new CategoryAxis().boundaryGap(false).data("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
        option.yAxis(new ValueAxis());
        option.series(new Line().smooth(true).name("邮件营销").stack("总量").symbol(Symbol.none).data(120, 132, 301, 134, new LineData(90, Symbol.droplet, 5), 230, 210));


        //实现不了js的这个效果
        //line.itemStyle.normal.areaStyle = new AreaStyle();
        LineData lineData = new LineData(201, Symbol.star, 15);
        lineData.itemStyle().normal().label().show(true).textStyle().fontSize(20).fontFamily("微软雅黑").fontWeight("bold");
        option.series(new Line().smooth(true).name("联盟广告").stack("总量").symbol("image://http://echarts.baidu.com/doc/asset/ico/favicon.png").symbolSize(8).data(120, 82, lineData, new LineData(134, Symbol.none), 190, new LineData(230, Symbol.emptypin, 8), 110));
        System.out.println(gson.toJson(option));
	    return gson.toJson(option);  
	    }  
	
	@RequestMapping(value="/selectLines")
	public ModelAndView selectLines(FlUserUser user,HttpSession session){
		FlUserUser flUser = flUserUserService.login(user);
		session.setAttribute("user", user);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/jsp/index");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value="/echartsThree")
	public String echartsThree(HttpServletRequest request,HttpServletResponse response){
		Option option = new Option();
		Gson gson = new Gson();
		option.title("东", "纯属虚构");
        option.tooltip().trigger(Trigger.axis);
        option.legend("成交");
        option.toolbox().show(true).feature(Tool.mark,
                Tool.dataView,
                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        option.calculable(true);
        option.xAxis(new CategoryAxis().boundaryGap(false).data("10.50", "10.51", "10.52","10.53","10.54","10.55","10.56","10.57","10.58","10.59","10.60"));
        option.yAxis(new ValueAxis());

        Line l1 = new Line("成交");
        l1.smooth(true).itemStyle().normal().areaStyle().typeDefault();
        //l1.data(10, 12, 21, 54, 260, 830, 710);
        
        Random random=new Random();// 定义随机类
        List<Object> tt = new ArrayList<Object>();
        for(int i=0;i<10;i++){
        	tt.add(random.nextInt(100));
        }
        l1.setData(tt);
        option.series(l1);
        System.out.println(gson.toJson(option));
	    return gson.toJson(option);  
	    }
	
	@ResponseBody
	@RequestMapping(value="/echartsFour")
	public String echartsFour(HttpServletRequest request,HttpServletResponse response){
		Option option = new Option();
		Gson gson = new Gson();
		option.title("天", "纯属虚构");
        option.tooltip().trigger(Trigger.axis);
        option.legend("成交");
        option.toolbox().show(true).feature(Tool.mark,
                Tool.dataView,
                new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled),
                Tool.restore,
                Tool.saveAsImage).padding(20);
        option.calculable(true);
        option.xAxis(new CategoryAxis().boundaryGap(false).data("10.50", "10.51", "10.52","10.53","10.54","10.55","10.56","10.57","10.58","10.59","10.60"));
        option.yAxis(new ValueAxis());

        Line l1 = new Line("成交");
        l1.smooth(true).itemStyle().normal().areaStyle().typeDefault();
        //l1.data(10, 12, 21, 54, 260, 830, 710);
        
        Random random=new Random();// 定义随机类
        List<Object> tt = new ArrayList<Object>();
        for(int i=0;i<10;i++){
        	tt.add(random.nextInt(100));
        }
        l1.setData(tt);
        option.series(l1);
        System.out.println(gson.toJson(option));
	    return gson.toJson(option);  
	    }

}
