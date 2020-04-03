package com.kramer;

import java.io.*;
import java.net.ServerSocket;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting GroupChat-Server");

        try (ServerSocket serverSocket = new ServerSocket(8000)) {
            while (true) {
                new Client(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.out.println("Server exception: " + e.getMessage());
        }
    }
}
