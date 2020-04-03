package com.kramer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {
    private static final String EXIT_CODE = "exit";

    private Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        System.out.println("New client connected");

        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);

            while (true) {
                String message = input.readLine();

                if (message.equalsIgnoreCase(EXIT_CODE)) {
                    break;
                }

                output.println("Echo from server: " + message);
            }
        } catch (IOException e) {
            System.out.println("Error with client: " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println("Error closing client socket: " + e.getMessage());
            }
        }
    }
}
