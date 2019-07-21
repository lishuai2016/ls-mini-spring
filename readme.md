模拟spring IOC和aop的简单实现

# 参考

- [tiny-spring](https://github.com/lishuai2016/tiny-spring)

- [tiny-spring 分析](https://www.zybuluo.com/dugu9sword/note/382745)



# 版本说明

- v1

简单模拟通过容器注册和获得bean的过程


- v2

模拟抽象spring的工厂类

定义了工厂接口BeanFactory：[抽象的工厂接口，定义了通过工厂获得bean和注册bean的方法],

一个抽象类AbstractBeanFactory：[实现了获得bean和注册bean的逻辑，通过一个map来维护bean信息,使用模板方法，定义一个抽象的创建bean的抽象方法doCreateBean]

一个工厂的默认实现AutowireCapableBeanFactory: [实现了模板方法doCreateBean，根据beanDefinition中的类对象，构建一个实例对象]
