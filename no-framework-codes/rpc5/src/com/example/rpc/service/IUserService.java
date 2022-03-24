package com.example.rpc.service;

import com.example.rpc.pojo.User;

public interface IUserService {
    User findUserById(int id);
}
