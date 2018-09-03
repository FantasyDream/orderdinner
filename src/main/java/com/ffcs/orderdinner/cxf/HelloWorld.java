package com.ffcs.orderdinner.cxf;

import javax.jws.WebService;

@WebService
public interface HelloWorld {

    String say(String string);

}
