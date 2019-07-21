package com.ls.mini.spring.ioc.factory;

import com.ls.mini.spring.ioc.BeanDefinition;
import com.ls.mini.spring.ioc.BeanReference;
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
        Object bean = createBeanInstance(beanDefinition);
        beanDefinition.setBean(bean);
        //设置对象到beanDefinition中去   懒加载，在get的时候才进行设值，之前是在注册的时候实例化设置的
        //在这里通过实例化一个没有初始化属性的bean到beanDefinition，解决了循环引用问题？？？
        applyPropertyValues(bean,beanDefinition);
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) throws Exception {
        return beanDefinition.getBeanClass().newInstance();//根据类对象构建一个无参数的实例
    }

    //给对象设置属性信息
//    protected void applyPropertyValues(Object bean, BeanDefinition mbd) throws Exception {
//        for (PropertyValue propertyValue : mbd.getPropertyValues().getPropertyValues()) {
//            Field declaredField = bean.getClass().getDeclaredField(propertyValue.getName());//根据字段名获得字段的信息
//            declaredField.setAccessible(true);
//            declaredField.set(bean, propertyValue.getValue());//设置字段的值
//        }
//    }

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
