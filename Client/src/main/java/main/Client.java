package main;

import IOManager.Server.MultiBox;
import commandManager.CommandStock;
import commandManager.commands.BaseCommand;
import exceptions.BuildObjectException;
import exceptions.CommandInterruptedException;
import exceptions.WrongAmountOfArgumentsException;

import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;

public class Client {
    SocketChannel socketChannel;
    Selector selector;
    SelectionKey selectionKey;
    Scanner scanner = new Scanner(System.in);
    ObjectOutputStream outputStream;

    ObjectInputStream inputStream;
    int port;
    ByteBuffer buffer;
    ByteArrayOutputStream byteArrayOutputStream;
    ByteArrayInputStream byteArrayInputStream;
    ByteBuffer headerBuffer;
    Boolean flag;

    CommandStock commandStock = new CommandStock();
    String host;
    InetSocketAddress inetSocketAddress;

    public Client() {
        while (true){
        try {
            System.out.println("Write the host name.");
            host = scanner.nextLine();
            System.out.println("Write the port number.");
            port = scanner.nextInt();
            inetSocketAddress = new InetSocketAddress(InetAddress.getByName(host), port);
            break;
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        }

        acceptance();

        while (true) {
            try {
                String line = "";
                System.out.println("Ready to write.");
                while (scanner.hasNextLine()){
                    line = scanner.nextLine();
                    if (!line.isEmpty()) break;
                }
                String[] args = line.trim().split(" +");
                if (commandStock.getSpecialCommands().containsKey(args[0])) {
                    BaseCommand command = commandStock.getSpecialCommands().get(args[0]);
                    MultiBox multiBox = command.execute(args);
                    toWrite(multiBox);
                } else {
                toWrite(args);
                }

                System.out.println("Ready to read.");

                toRead();
            } catch (SocketException e){
                acceptance();
            } catch (IOException e){
                System.out.println("Connection lost. Attempting to reconnect...");
                acceptance();
            } catch (BuildObjectException | WrongAmountOfArgumentsException | CommandInterruptedException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void setup() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            outputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        buffer = ByteBuffer.allocate(1024*1024);
        byteArrayInputStream = new ByteArrayInputStream(buffer.array());
        try {
            selector = Selector.open();
            selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);

            boolean flag1 = false;
            while(true) {
                int readyChannels = selector.select(100);
                Set selectionSet = selector.selectedKeys();
                if (readyChannels > 0) {
                    // System.out.println(buffer.position());
                    socketChannel.read(buffer);
                    // System.out.println(buffer.position());
                    selectionSet.remove(selectionKey);
                    flag1 = true;
                } else if (flag1) break;
            }

            buffer.flip();
            headerBuffer = ByteBuffer.allocate(100);
            headerBuffer.put(buffer);
            inputStream = new ObjectInputStream(byteArrayInputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        flag = false;
    }

    private void acceptance() {
        while (true){
            try {
                System.out.println("Trying to connect...");
                socketChannel = SocketChannel.open();
                if (!socketChannel.isConnected()){
                socketChannel.connect(inetSocketAddress);
                socketChannel.finishConnect();
                }
                socketChannel.configureBlocking(false);
                System.out.println("Connection successful");
                setup();
                break;
            } catch (ConnectException e){
                try {
                    socketChannel.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void toRead() throws IOException {
        try {
            buffer.clear();
            buffer.put(new byte[1024*1024]);
            buffer.clear();
            if (flag) {
                byteArrayInputStream.reset();
            } else {
                headerBuffer.flip();
                buffer.put(headerBuffer);}

            boolean flag1 = false;
            while(true) {
                int readyChannels = selector.select(100);
                Set<SelectionKey> selectionSet = selector.selectedKeys();
                if (readyChannels > 0) {
                    // System.out.println(buffer.position());
                    socketChannel.read(buffer);
                    // System.out.println(buffer.position());
                    selectionSet.remove(selectionKey);
                    flag1 = true;
                } else if (flag1) break;
            }

            MultiBox<?> multiBox = (MultiBox<?>) inputStream.readObject();
            multiBox.getAllObjects().forEach((obj) -> {
                String line = (String) obj;
                System.out.println(line);
            });
            flag = true;
        } catch (ClassNotFoundException e) {
            System.out.println("Unsupported class - " + e.getMessage());
        } catch (IOException e) {
            if (!socketChannel.isOpen()){
                throw new IOException("Server is down");
            }
            System.out.println(e.getMessage());
        }
    }

    public void toWrite(String[] args) throws IOException {
        MultiBox<?> multiBox = new MultiBox<>(args[0]);
        if (args.length > 1){
            int count = 1;
            while (args.length != count){
                multiBox.addToBox(args[count]);
                count += 1;
            }
        }
        outputStream.writeObject(multiBox);
        ByteBuffer outBuff = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        outputStream.reset();
        outputStream.flush();
        byteArrayOutputStream.reset();
        socketChannel.write(outBuff);
    }

    public void toWrite(MultiBox<?> multiBox) throws IOException{
        outputStream.writeObject(multiBox);
        ByteBuffer outBuff = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
        outputStream.reset();
        outputStream.flush();
        byteArrayOutputStream.reset();
        socketChannel.write(outBuff);
    }
}
