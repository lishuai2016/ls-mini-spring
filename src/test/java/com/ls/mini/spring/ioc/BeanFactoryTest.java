package com.ls.mini.spring.ioc;

import com.ls.mini.spring.ioc.factory.AutowireCapableBeanFactory;
import com.ls.mini.spring.ioc.factory.BeanFactory;
import org.junit.Test;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 13:37
 */
public class BeanFactoryTest {
    @Test
    public void test() throws Exception {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2.bean定义
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.ls.mini.spring.ioc.HelloWorldService");

        // 3.设置属性
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("text", "Hello World!"));
        beanDefinition.setPropertyValues(propertyValues);

        // 4.生成bean
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 5.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();

    }
}
