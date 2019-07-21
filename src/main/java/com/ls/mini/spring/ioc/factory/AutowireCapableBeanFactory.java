package com.ls.mini.spring.ioc.factory;

import com.ls.mini.spring.ioc.BeanDefinition;
import com.ls.mini.spring.ioc.PropertyValue;

import java.lang.reflect.Field;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 14:14
 * 把创建对象bean的过程进行分离
 * 1、创建对象
 * 2、设置bean的属性
 *
 * 可自动装配内容的BeanFactory
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {
    @Override
    protected Object doCreateBean(BeanDefinition beanDefinition) throws Exception {
        Object object = createBeanInstance(beanDefinition);
        applyPropertyValues(object,beanDefinition);
        return object;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();//根据类对象构建一个无参数的实例
    }

    //给对象设置属性信息
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());//根据字段名获得字段的信息
            declaredField.setAccessible(true);
            declaredField.set(bean, propertyValue.getValue());//设置字段的值
        }
    }

}
