package com.delta.simplehttpcallsvolley.model;

import java.io.Serializable;

/**
 * Created by Samantha on 2/11/17 1:31 PM .
 */

public class User implements Serializable{
    private String id, name;

    public User(String id, String name) {
        setId(id);
        setName(name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setName(String name) {
        this.name = name;
    }
}
