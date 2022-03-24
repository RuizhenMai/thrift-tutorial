package com.example.service;

import com.example.gen.thrift.User;
import com.example.gen.thrift.UserService;
import org.apache.thrift.TException;

public class UserServiceHandler implements UserService.Iface {
    @Override
    public User findUserById(int uid) throws TException {
        return new User(uid, "Alice");
    }
}
