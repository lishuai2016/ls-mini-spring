模拟spring IOC和aop的简单实现

# 参考

- [tiny-spring](https://github.com/lishuai2016/tiny-spring)

- [tiny-spring 分析](https://www.zybuluo.com/dugu9sword/note/382745)



# 版本说明

> v1

新增：

简单模拟通过容器注册和获得bean的过程


> v2

新增：

模拟抽象spring的工厂类

- 定义了工厂接口BeanFactory：[抽象的工厂接口，定义了通过工厂获得bean和注册bean的方法],

- 一个抽象类AbstractBeanFactory：[实现了获得bean和注册bean的逻辑，通过一个map来维护bean信息,使用模板方法，定义一个抽象的创建bean的抽象方法doCreateBean]

- 一个工厂的默认实现AutowireCapableBeanFactory: [实现了模板方法doCreateBean，根据beanDefinition中的类对象，构建一个实例对象]

> v3

新增：

在BeanDefinition中新增PropertyValues字段，封装了构建bean的一些属性，用于bean的初始化。在com.ls.mini.spring.ioc.factory.AutowireCapableBeanFactory.doCreateBean中，
把实例化bean，分为两步：1、创建对象；2、设置bean的属性

> v4

新增：

一个是资源定位Resource接口以及对应的实现类

一个是从resource定位的配置文件中读取bean的配置的接口BeanDefinitionReader，生成BeanDefinition

主要实现了从xml配置文件解析配置的bean信息，生成BeanDefinition并注册到bean的容器中。

> v5

新增：

容器bean的懒加载机制，在初始化的时候仅仅是解析配置文件的信息到BeanDefinition然后注册到map中，
此时，并没有实例化BeanDefinition中的bean，在使用getbean的时候，会首先判断BeanDefinition是否注册，
没有的话报错，假如注册过的话，在检测BeanDefinition.getbean是否已经实例化了，没有的话进行实例化；

备注：这里面也出现了循环引用的问题。

比如：
```xml

    <bean name="outputService" class="com.ls.mini.spring.ioc.OutputService">
        <property name="helloWorldService" ref="helloWorldService"></property>
    </bean>

    <bean name="helloWorldService" class="com.ls.mini.spring.ioc.HelloWorldService">
        <property name="text" value="Hello World!"></property>
        <property name="outputService" ref="outputService"></property>
    </bean>
```

这里的解决方式是，在通过类构建实例的时候，还没有初始化属性时，把bean对象设置到BeanDefinition中，
这样出现依赖的对象之间获得对方的实例，不是完全的属性也初始化完毕，这样就避免了循环依赖的问题。
