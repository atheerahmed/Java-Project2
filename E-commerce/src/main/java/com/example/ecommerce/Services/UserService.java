package com.example.ecommerce.Services;

import com.example.ecommerce.Models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    private ArrayList<User>users=new ArrayList<>();

    public ArrayList<User> getUser(){
        return users;
    }

    public boolean isAdd(User user) {
        return users.add(user);
    }

    public boolean isUpdate(User user) {
        for (int i = 0; i < users.size() ; i++) {
            if (users.get(i).getId().equals(user.getId()))
                users.set(i,user);
            return true;
        }
        return false;
    }

    public boolean isDelete(User user) {
        return users.remove(user);


    }


    public User getUserByid(String userid) {
        for (User user:users ) {
            if (userid.equals(user.getId()))
                return user;
        }
        return null;
    }



}
