package com.example.rpc;

import com.example.rpc.pojo.User;
import com.example.rpc.service.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService service = Stub.getStub();
        User user = service.findUserById(123);
        System.out.println(user);

    }
}
