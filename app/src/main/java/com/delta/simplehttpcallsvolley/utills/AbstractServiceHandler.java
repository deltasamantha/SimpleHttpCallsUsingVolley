package com.delta.simplehttpcallsvolley.utills;

/**
 * Created by Samantha on 2/11/17 8:35 PM .
 */

public class AbstractServiceHandler {
    IResponseSerializer iSerializer;

    IResponseSerializer getiSerializer() {
        return iSerializer;
    }

    public void setiSerializer(IResponseSerializer iSerializer) {
        this.iSerializer = iSerializer;
    }

}
