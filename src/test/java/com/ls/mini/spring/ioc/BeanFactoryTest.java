package com.ls.mini.spring.ioc;

import com.ls.mini.spring.ioc.factory.AutowireCapableBeanFactory;
import com.ls.mini.spring.ioc.factory.BeanFactory;
import com.ls.mini.spring.ioc.io.ResourceLoader;
import com.ls.mini.spring.ioc.xml.XmlBeanDefinitionReader;
import org.junit.Test;

import java.util.Map;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 13:37
 */
public class BeanFactoryTest {
    @Test
    public void test() throws Exception {
        // 1.读取配置
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");

        // 2.初始化BeanFactory并注册bean     ，把Registry中的BeanDefinition遍历输入beanFactory中？？？
        BeanFactory beanFactory = new AutowireCapableBeanFactory();
        for (Map.Entry<String, BeanDefinition> beanDefinitionEntry : xmlBeanDefinitionReader.getRegistry().entrySet()) {
            beanFactory.registerBeanDefinition(beanDefinitionEntry.getKey(), beanDefinitionEntry.getValue());
        }

        // 3.获取bean
        HelloWorldService helloWorldService = (HelloWorldService) beanFactory.getBean("helloWorldService");
        helloWorldService.helloWorld();

    }
}
