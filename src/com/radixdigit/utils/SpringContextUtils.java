/*
 * @Copyright: Copyright 北京网擎科技有限公司 (c) 2012
 * @修改记录： 1、2012-8-15 下午08:51:32，Administrator创建。
 */
package com.radixdigit.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Desc：spring獲取bean</br>
 * @Filename:SpringContextUtils.java</br>
 * @Author:CJ</br>
 * @Date:2012下午08:51:32</br>
 */
@SuppressWarnings("unchecked")
public class SpringContextUtils implements ApplicationContextAware {// Spring的工具类，用来获得配置文件中的bean

    private static ApplicationContext applicationContext = null;

    public void setApplicationContext(ApplicationContext applicationContext)
        throws BeansException {
        SpringContextUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }

    public static Object getBean(String name, Class requiredType)
        throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name)
        throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class getType(String name)
        throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }

    public static String[] getAliases(String name)
        throws NoSuchBeanDefinitionException {
        return applicationContext.getAliases(name);
    }
}
