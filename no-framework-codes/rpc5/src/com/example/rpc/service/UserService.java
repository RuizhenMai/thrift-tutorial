package com.example.rpc.service;

import com.example.rpc.pojo.User;

public class UserService implements IUserService{
    @Override
    public User findUserById(int id) {
        return new User(id, "Alice");
    }
}
