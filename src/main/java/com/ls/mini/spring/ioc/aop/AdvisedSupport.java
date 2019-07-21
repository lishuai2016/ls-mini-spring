package com.ls.mini.spring.ioc.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 17:38
 *
 * 代理相关的元数据
 *
 * 封装被代理对象和对应的方法拦截器
 */
public class AdvisedSupport {

    private TargetSource targetSource;//目标对象

    private MethodInterceptor methodInterceptor;//方法拦截器

    private MethodMatcher methodMatcher;//方法匹配器

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
