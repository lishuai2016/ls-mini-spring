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
    public void test() {
        // 1.初始化beanfactory
        BeanFactory beanFactory = new AutowireCapableBeanFactory();

        // 2.注入bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBeanClassName("com.ls.mini.spring.ioc.HelloWorldService");
        beanFactory.registerBeanDefinition("helloWorldService", beanDefinition);

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();

    }
}
