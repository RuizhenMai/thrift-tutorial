package com.example.rpc;

import com.example.rpc.pojo.User;
import com.example.rpc.service.IUserService;
import com.example.rpc.service.UserService;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        ServerSocket server = new ServerSocket(8888);
        while (running) {
            Socket soc = server.accept();
            process(soc);
            soc.close();
        }

    }
    public static void process(Socket socket) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Get the Binary InputStream
        InputStream io = socket.getInputStream();
        // Convert the Binary InputStream into an easily-read stream
        ObjectInputStream ois = new ObjectInputStream(io);



        // Retrieve user object from UserService
        IUserService userService = new UserService();
        String methodName = ois.readUTF();
        System.out.println("Received request with method "+ methodName);

        Method findUserById = userService.getClass().getMethod(methodName, (Class<?>[]) ois.readObject());

        // serService.findUserById(uid);
        Object[] args = (Object[]) ois.readObject();
        User user = (User) findUserById.invoke(userService, args);


        // Get the OutputStream
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        // Write the user object to socket output
        oos.writeObject(user);
    }
}
