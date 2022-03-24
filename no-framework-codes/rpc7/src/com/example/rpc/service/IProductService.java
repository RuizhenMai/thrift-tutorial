package com.example.rpc.service;

import com.example.rpc.pojo.Product;

public interface IProductService {
    Product findProductById(Integer id);
}
