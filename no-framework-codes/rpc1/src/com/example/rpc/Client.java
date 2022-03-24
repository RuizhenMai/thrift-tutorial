package com.example.rpc;

import com.example.rpc.pojo.User;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        // Connect to Socket Server
        Socket socket = new Socket("127.0.0.1", 8888);

        // Send Request to the server
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeInt(123);
        dos.flush();

        // Retrieve the result
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        User user = new User(dis.readInt(), dis.readUTF());
        System.out.println("user = " + user);

        socket.close();
    }
}
