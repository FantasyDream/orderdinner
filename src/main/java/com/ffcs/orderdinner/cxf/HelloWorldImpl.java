package com.ffcs.orderdinner.cxf;

import org.springframework.stereotype.Component;

@Component("helloWorld")
public class HelloWorldImpl implements HelloWorld {
    @Override
    public String say(String string) {
        return "hello"+string;
    }
}
