package com.ls.mini.spring.ioc.factory;

import com.ls.mini.spring.ioc.BeanDefinition;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 14:14
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) {
        Object object = null;
        try {
            object = beanDefinition.getBeanClass().newInstance();//根据类对象构建一个无参数的实例
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return object;
    }
}
