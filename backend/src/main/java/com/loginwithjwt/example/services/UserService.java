package com.loginwithjwt.example.services;

import com.loginwithjwt.example.models.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private List<User> userList = new ArrayList<>();

    private UserService(){
        userList.add(new User(UUID.randomUUID().toString(),"sudhir","sudhir@gmail.com"));
        userList.add(new User(UUID.randomUUID().toString(),"anand","ananad@gmail.com"));
        userList.add(new User(UUID.randomUUID().toString(),"tikina","tikina@gmail.com"));
    }

    public List<User> getUsers(){
        return this.userList;
    }
}
