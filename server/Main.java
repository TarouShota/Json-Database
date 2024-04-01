package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        String address = "127.0.0.1";
        int port = 23456;
        System.out.println("Server started!");
        Server server = new Server();
        while (true) {
            try (ServerSocket socketServer = new ServerSocket(port, 50, InetAddress.getByName(address));) {

                Socket socket = socketServer.accept();

                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());

                String utfOutput = input.readUTF();
                String[] receivedText = utfOutput.trim().split(" +");


                String action = receivedText[0];
                if (Objects.equals(action, "exit")) {
                    output.writeUTF("OK");
                    return;
                }

                int index = Integer.parseInt(receivedText[1]);
                if (index > Server.size - 1 || index < 0) {
                    output.writeUTF("ERROR");
                }

                StringBuilder text = new StringBuilder();

                String extraSpace = "";
                for (int i = 2; i < receivedText.length; i++) {
                    text.append(extraSpace).append(receivedText[i]);
                    extraSpace = " ";
                }


                switch (action) {
                    case "get": {
                        output.writeUTF(server.get(index));
                        break;
                    }
                    case "set": {
                        output.writeUTF(server.set(index, text.toString()));
                        break;
                    }
                    case "delete": {
                        output.writeUTF(server.delete(index));
                        break;
                    }
                    default: {
                        return;
                    }
                }
            } catch (IOException exception) {
                System.err.println("Caught IOException: " + exception.getMessage());
            }


        }


    }
}
