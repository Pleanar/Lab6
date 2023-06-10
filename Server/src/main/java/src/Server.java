package src;

import IOManager.Server.MultiBox;

import java.io.*;
import java.net.*;

public class Server {
    static ServerSocket serverSocket;
    static Socket socket;

    public Server(){
        try {
            serverSocket = new ServerSocket(53762);
            System.out.println(serverSocket.getLocalPort());
            while (true) {
                socket = serverSocket.accept();
                if (socket != null){
                    System.out.println("New connection was formed.");
                    while (socket.isConnected()) {
                        try {
                            acceptance();
                        } catch (IOException e){
                            System.out.println("Connection was lost.");
                            System.out.println("Waiting for new connection.");
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void acceptance() throws IOException{
        InputStream in = socket.getInputStream();
        ObjectInputStream inputStream = new ObjectInputStream(in);

        MultiBox multiBox = null;
        try {
            multiBox = (MultiBox<?>) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println("Unsupported object class.");
        }

        System.out.println(multiBox.getAllObjects());
        multiBox.addToBox("Acceptance confirmed.");

        System.out.println("I've read it!");

        OutputStream out = socket.getOutputStream();
        ObjectOutputStream outputStream = new ObjectOutputStream(out);
        outputStream.writeObject(multiBox);
        System.out.println("I wrote it!");
    }
}