package com.ls.mini.spring.ioc.aop;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 17:40
 *
 * 被代理的对象
 */
public class TargetSource {
    private Class<?>[] targetClass;//接口数组

    private Object target;

    public TargetSource(Object target, Class<?>... targetClass) {
        this.target = target;
        this.targetClass = targetClass;
    }

    public Class<?>[] getTargetClass() {
        return targetClass;
    }

    public Object getTarget() {
        return target;
    }
}
