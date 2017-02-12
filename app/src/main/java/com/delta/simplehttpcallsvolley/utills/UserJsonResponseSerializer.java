package com.delta.simplehttpcallsvolley.utills;

import com.delta.simplehttpcallsvolley.model.User;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Samantha on 2/11/17 8:12 PM .
 */

public class UserJsonResponseSerializer implements IResponseSerializer {

    private ArrayList<User> list;

    public UserJsonResponseSerializer(){
        list = new ArrayList<>();
    }

    private ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    private void addUser(User user){
        list.add(user);
    }

    @Override
    public ArrayList<User> serializeResponse(Object result){
        try {
            JSONObject obj = (JSONObject) result;
            if (obj != null) {
                JSONArray userArray = obj.getJSONArray("contacts");
                User user;
                for (int i = 0; i < userArray.length(); i++) {
                    JSONObject userObject = (JSONObject) userArray.get(i);
                    String userName = passNil(userObject.getString("name"));
                    String userId = passNil(userObject.getString("id"));
                    user = new User(userId, userName);
                    addUser(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getList();
    }

    private String passNil(String str) {
        return str == null ? "" : str;
    }

}
