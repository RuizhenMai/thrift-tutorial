package com.example.service;

import com.example.gen.thrift.Product;
import com.example.gen.thrift.ProductService;
import org.apache.thrift.TException;

public class ProductServiceHandler implements ProductService.Iface {
    @Override
    public Product findProductById(int pid) throws TException {
        return new Product(pid, "iPhone");
    }
}
