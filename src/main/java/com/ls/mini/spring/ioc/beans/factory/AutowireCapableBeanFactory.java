package com.ls.mini.spring.ioc.beans.factory;

import com.ls.mini.spring.ioc.beans.BeanDefinition;
import com.ls.mini.spring.ioc.BeanReference;
import com.ls.mini.spring.ioc.beans.PropertyValue;

import java.lang.reflect.Field;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 14:14
可自动装配内容的BeanFactory
 */
public class AutowireCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());
            declaredField.setAccessible(true);
            Object value = propertyValue.getValue();
            if (value instanceof BeanReference) {
                BeanReference beanReference = (BeanReference) value;
                value = getBean(beanReference.getName());//获取ref实例，这里会不会产生循环依赖？？？
            }
            declaredField.set(bean, value);
        }
    }

}
