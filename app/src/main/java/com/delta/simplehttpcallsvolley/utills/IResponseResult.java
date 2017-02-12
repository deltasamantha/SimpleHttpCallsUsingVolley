package com.delta.simplehttpcallsvolley.utills;

/**
 * Created by Samantha on 2/11/17 1:39 PM .
 */

public interface IResponseResult {
    abstract void response(Object result, int tagNo, boolean success);

    abstract void response(Object result, boolean success);
}
