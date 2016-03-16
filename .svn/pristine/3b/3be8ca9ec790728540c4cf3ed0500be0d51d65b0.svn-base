package com.radixdigit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.radixdigit.base.BaseController;

/**
 * Spring3MVC拦截器定义
 * @author chengjun
 *
 */
//implements HandlerInterceptor
public class InterceptorOfSystem extends HandlerInterceptorAdapter{
	  private Logger log = Logger.getLogger(InterceptorOfSystem.class);  
      
	    public InterceptorOfSystem() {  
	        // TODO Auto-generated constructor stub  
	    	log.info("初始化拦截");
	    }  
	  
	    private String mappingURL;
	        public void setMappingURL(String mappingURL) {    
	               this.mappingURL = mappingURL;    
	       }   
	  
	        /** 
		     * 在业务处理器处理请求之前被调用 
		     * 如果返回false 
		     *     从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链 
		     *  
		     * 如果返回true 
		     *    执行下一个拦截器,直到所有的拦截器都执行完毕 
		     *    再执行被拦截的Controller 
		     *    然后进入拦截器链, 
		     *    从最后一个拦截器往回执行所有的postHandle() 
		     *    接着再从最后一个拦截器往回执行所有的afterCompletion() 
		     */  

	    public boolean preHandle(HttpServletRequest request,  
	            HttpServletResponse response, Object handler) throws Exception {  
	        // TODO Auto-generated method stub  
	    
	        log.info("开始拦截");  
	        String url=request.getRequestURL().toString();    
	        if(mappingURL==null || mappingURL==""|| url.matches(mappingURL)){    
	            response.setContentType("text/html; charset=UTF-8");
	            response.sendRedirect(request.getContextPath()+"/index.jsp");
	            log.info("拦截对象方法标示为空,返回配置地址");
	            return false;   
	        } 
	        BaseController.setRequest(request);
	        BaseController.setResponse(response);
	        return true;  
	    }  
	   
	    public void postHandle(HttpServletRequest request,  
	            HttpServletResponse response, Object handler,  
	            ModelAndView modelAndView) throws Exception {  
	        // TODO Auto-generated method stub  
	    
	    	System.out.println("拦截之后");
	        log.info("URL="+request.getRequestURI());  
	    }  
	  

	    
	    /** 
	     * 在DispatcherServlet完全处理完请求后被调用  
	     *  
	     *   当有拦截器抛出异常时,会从当前拦截器往回执行所有的拦截器的afterCompletion() 
	     */
	     
	    public void afterCompletion(HttpServletRequest request,  
	            HttpServletResponse response, Object handler, Exception ex)  
	            throws Exception {  
	    	
	    	// TODO Auto-generated method stub  
	        log.info("拦截完毕");
	        System.out.println("URL="+request.getRequestURI());
	        
	    }  
	

}
