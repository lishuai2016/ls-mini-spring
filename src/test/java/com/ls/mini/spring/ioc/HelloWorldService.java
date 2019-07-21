package com.ls.mini.spring.ioc;

/**
 * @program: ls-mini-spring
 * @author: lishuai
 * @create: 2019-07-21 13:38
 */
public class HelloWorldService {

    private String text;

    private OutputService outputService;

    public void helloWorld(){
        outputService.output(text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOutputService(OutputService outputService) {
        this.outputService = outputService;
    }
}
