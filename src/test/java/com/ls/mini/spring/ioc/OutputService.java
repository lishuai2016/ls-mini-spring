package com.ls.mini.spring.ioc;

import org.junit.Assert;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 16:15
 */
public class OutputService {

    private HelloWorldService helloWorldService;

    public void output(String text){
        Assert.assertNotNull(helloWorldService);
        System.out.println(text);
    }

    public void setHelloWorldService(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }
}
