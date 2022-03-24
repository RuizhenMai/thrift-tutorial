package com.example.rpc;

import com.example.rpc.pojo.User;
import com.example.rpc.service.IUserService;

import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class Stub {
    public static IUserService getStub() {
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // Connect to Socket Server
                Socket socket = new Socket("127.0.0.1", 8888);

                // Send Request to the server
                ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                // Send method name
                oos.writeUTF(method.getName());
                // Send method params
                oos.writeObject(method.getParameterTypes());
                oos.writeObject(args);

                oos.flush();

                // Retrieve the result
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                User user = (User) ois.readObject();

                socket.close();
                return user;
            }
        };

        Object o = Proxy.newProxyInstance(IUserService.class.getClassLoader(), new Class[]{IUserService.class}, h);
        return (IUserService) o;
    }
}
