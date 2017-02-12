package com.delta.simplehttpcallsvolley.utills;

import com.delta.simplehttpcallsvolley.model.User;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Samantha on 2/11/17 8:33 PM .
 */

public interface IResponseSerializer {
    abstract ArrayList<User> serializeResponse(Object result);
}
