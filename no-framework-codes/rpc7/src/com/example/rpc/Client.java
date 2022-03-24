package com.example.rpc;

import com.example.rpc.pojo.Product;
import com.example.rpc.pojo.User;
import com.example.rpc.service.IProductService;
import com.example.rpc.service.IUserService;

public class Client {
    public static void main(String[] args) {
        IUserService userService = (IUserService) Stub.getStub(IUserService.class);
        User user = userService.findUserById(123);
        System.out.println(user);

        IProductService productService = (IProductService) Stub.getStub(IProductService.class);
        Product product = productService.findProductById(321);
        System.out.println(product);
    }
}
