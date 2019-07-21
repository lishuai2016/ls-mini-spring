package com.ls.mini.spring.ioc.factory;

import com.ls.mini.spring.ioc.BeanDefinition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 14:09
 *
 * 抽象的工厂类
 * 实现了获得bean和注册bean的逻辑，通过一个map来维护bean信息
 *
 * 使用模板方法，定义一个抽象的创建bean的抽象方法
 */
public abstract class AbstractBeanFactory implements BeanFactory{

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();//IOC容器

    private final List<String> beanDefinitionNames = new ArrayList<String>();//即可工厂中的所有已经注册bean的名称


    /**
     * 这里可以实现懒加载
     * @param name
     * @return
     * @throws Exception
     */
    @Override
    public Object getBean(String name) throws Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        if (beanDefinition == null) {
            throw new IllegalArgumentException("No bean named " + name + " is defined");
        }
        Object bean = beanDefinition.getBean();//在这里没有实力话，再进行实例的创建
        if (bean == null) {
            bean = doCreateBean(beanDefinition);
        }
        return bean;
    }

    /**
     * 上一个版本v4在注册的时候进行对象bean的创建，现在把创建实例可以延迟到获取的时候再进行创建
     * @param name
     * @param beanDefinition
     * @throws Exception
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception {
        beanDefinitionMap.put(name, beanDefinition);
        beanDefinitionNames.add(name);
    }

    /**
     * 初始化bean实例的接口，通过主动调用来完成对象bean的实例化
     * @throws Exception
     */
    public void preInstantiateSingletons() throws Exception {
        for (Iterator it = this.beanDefinitionNames.iterator(); it.hasNext();) {
            String beanName = (String) it.next();
            getBean(beanName);
        }
    }

    /**
     * 初始化bean
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
