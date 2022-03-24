package com.example.rpc.service;

import com.example.rpc.pojo.Product;

public class ProductService implements IProductService{
    @Override
    public Product findProductById(Integer id) {
        return new Product(id, "Smartphone");
    }
}
