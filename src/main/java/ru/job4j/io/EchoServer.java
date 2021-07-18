package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            System.out.println("The server is running!");
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream out = socket.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    out.write("Hello, dear friend.".getBytes());
                    String str = in.readLine();
                    while (str != null && !str.isEmpty()) {
                        if (str.startsWith("GET") && str.contains("Hello")
                                || str.contains("hello")) {
                            System.out.println("Hello!");
                        } else if (str.contains("Exit") || str.contains("exit")) {
                            server.close();
                            System.out.println("The server is stopped!");
                        } else if (str.startsWith("GET") && !str.contains("Hello") && !str.contains("Exit")) {
                               System.out.println("What? Repeat, please you command");
                        }
                        str = in.readLine();
                    }
                   out.flush();
                }
            }
        }
    }
}
