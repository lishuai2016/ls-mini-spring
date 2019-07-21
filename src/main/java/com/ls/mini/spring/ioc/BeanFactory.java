package com.ls.mini.spring.ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 13:32
 *
 * bean工厂，系统的所有的bean都注册到这里
 */
public class BeanFactory {

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    public Object getBean(String name) {
        return this.beanDefinitionMap.get(name).getBean();//注意这里需要调用的是BeanDefinition封装的bean对象
    }

    public void registerBeanDefinition(String name,BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(name,beanDefinition);
    }
}
