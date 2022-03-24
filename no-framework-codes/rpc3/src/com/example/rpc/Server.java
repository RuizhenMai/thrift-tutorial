package com.example.rpc;

import com.example.rpc.pojo.User;
import com.example.rpc.service.IUserService;
import com.example.rpc.service.UserService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static boolean running = true;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(8888);
        while (running) {
            Socket soc = server.accept();
            process(soc);
            soc.close();
        }

    }
    public static void process(Socket socket) throws IOException {
        // Get the Binary InputStream
        InputStream io = socket.getInputStream();
        // Convert the Binary InputStream into an easily-read stream
        DataInputStream dis = new DataInputStream(io);

        int uid = dis.readInt();
        System.out.println("Received request with uid " + uid);
        // Retrieve user object from UserService
        IUserService userService = new UserService();
        User user = userService.findUserById(uid);

        // Get the OutputStream
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // Write the user object to socket output
        dos.writeInt(user.getId());
        dos.writeUTF(user.getName());
        dos.flush();
    }
}
