package com.ls.mini.spring.ioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 17:45
 *
 * 基于jdk的动态代理
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] { advised.getTargetSource()
                .getTargetClass() }, this);
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        MethodInterceptor methodInterceptor = advised.getMethodInterceptor();//方法拦截器
        return methodInterceptor.invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method,
                args));//用方法拦截器包装被调用的方法，进而可以插入拦截器中的逻辑
    }

}

