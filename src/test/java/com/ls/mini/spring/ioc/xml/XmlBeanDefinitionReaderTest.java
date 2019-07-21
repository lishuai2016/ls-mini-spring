package com.ls.mini.spring.ioc.xml;

import com.ls.mini.spring.ioc.BeanDefinition;
import com.ls.mini.spring.ioc.io.ResourceLoader;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 15:30
 */
public class XmlBeanDefinitionReaderTest {
    @Test
    public void test() throws Exception {
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(new ResourceLoader());
        xmlBeanDefinitionReader.loadBeanDefinitions("tinyioc.xml");
        Map<String, BeanDefinition> registry = xmlBeanDefinitionReader.getRegistry();
        Assert.assertTrue(registry.size() > 0);
    }
}
