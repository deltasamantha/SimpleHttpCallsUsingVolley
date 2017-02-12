package com.delta.simplehttpcallsvolley.adapters;

import com.delta.simplehttpcallsvolley.model.User;

import java.util.ArrayList;

/**
 * Created by Samantha on 2/11/17 9:07 PM .
 */

public class UserDetailDecorator {
    private ArrayList<User> userList;


    public UserDetailDecorator(ArrayList<User> userList){
        setUserList(userList);
    }

    public String getName(int index){
        return userList.get(index).getName();
    }

    public String getId(int index){
        return userList.get(index).getId();
    }

    private void setUserList(ArrayList<User> userList){
        this.userList = userList;
    }

}
