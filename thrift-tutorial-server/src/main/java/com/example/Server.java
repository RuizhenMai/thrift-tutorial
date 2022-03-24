package com.example;

import com.example.gen.thrift.ProductService;
import com.example.gen.thrift.UserService;
import com.example.service.ProductServiceHandler;
import com.example.service.UserServiceHandler;

import org.apache.thrift.TMultiplexedProcessor;
import org.apache.thrift.TProcessor;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportException;

public class Server {

    public static void main(String[] args) throws TTransportException {
        TProcessor processor = buildMultiplexProcessor();
        startSimpleServer(processor);
    }

    public static TProcessor buildSimpleProcessor() {
        UserServiceHandler handler = new UserServiceHandler();
        UserService.Processor<UserServiceHandler> processor = new UserService.Processor<>(handler);
        return processor;
    }

    public static TProcessor buildMultiplexProcessor() {
        TMultiplexedProcessor processor = new TMultiplexedProcessor();
        processor.registerProcessor("UserService",
                new UserService.Processor<>(new UserServiceHandler()));
        processor.registerProcessor("ProductService",
                new ProductService.Processor<>(new ProductServiceHandler()));

        return processor;
    }

    public static void startSimpleServer(TProcessor processor) throws TTransportException {
        TServerTransport serverTransport = new TServerSocket(9090);
        TServer tServer = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));

        System.out.println("Started a simple server on port " + 9090);
        tServer.serve();
    }
}
