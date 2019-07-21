package com.ls.mini.spring.ioc.context;

import com.ls.mini.spring.ioc.beans.factory.AbstractBeanFactory;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 17:02
 */
public abstract class AbstractApplicationContext implements ApplicationContext {
    protected AbstractBeanFactory beanFactory;//委托

    public AbstractApplicationContext(AbstractBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public void refresh() throws Exception{
    }

    @Override
    public Object getBean(String name) throws Exception {//获取bean实例委托给当前的beanFactory
        return beanFactory.getBean(name);
    }
}
