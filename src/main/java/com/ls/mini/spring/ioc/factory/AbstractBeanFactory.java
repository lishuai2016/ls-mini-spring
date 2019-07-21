package com.ls.mini.spring.ioc.factory;

import com.ls.mini.spring.ioc.BeanDefinition;

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

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<String, BeanDefinition>();

    @Override
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 主要逻辑是根据BeanDefinition定义的bean信息，先构建一个bean对象，然后再放入map
     * @param name
     * @param beanDefinition
     */
    @Override
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) throws Exception{
        Object object = doCreateBean(beanDefinition);//模板方法
        beanDefinition.setBean(object);
        beanDefinitionMap.put(name,beanDefinition);
    }

    /**
     * 初始化bean
     * @param beanDefinition
     * @return
     */
    protected abstract Object doCreateBean(BeanDefinition beanDefinition) throws Exception;
}
