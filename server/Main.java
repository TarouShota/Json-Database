package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {

        String address = "127.0.0.1";
        int port = 23456;

        try {
            ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));
            System.out.println("Server started!");
            Socket socket = server.accept();
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            String utfOutput = input.readUTF();
            System.out.println("Received: "+ utfOutput);

            StringBuilder number = new StringBuilder();
            for (int i = 0; i < utfOutput.length(); i++) {
                if (utfOutput.charAt(i) > 47 && utfOutput.charAt(i) < 58) {
                    number.append(utfOutput.charAt(i));
                }
            }
            String outputString = "A record # " + number + " was sent!";
            output.writeUTF(outputString);
            System.out.println("Sent: "+outputString);


//
        } catch (IOException exception) {
            System.err.println("Caught IOException: " + exception.getMessage());
        }


//        Server server = new Server();
//        System.out.println("Type your input:");
//        Scanner scanner = new Scanner(System.in);

//        while (true) {
//            String[] input = scanner.nextLine().trim().split(" +");
//            String action = input[0];
//            if(Objects.equals(action,"exit")){
//                return;
//            }
//
//            int index = Integer.parseInt(input[1]);
//            if (index > Server.size - 1 || index < 0) {
//                System.out.println("Wrong boundaries");
//                return;
//            }
//
//            StringBuilder text = new StringBuilder();
//
//            String extraSpace = "";
//            for (int i = 2; i < input.length; i++) {
//                text.append(extraSpace).append(input[i]);
//                extraSpace=" ";
//
//            }

//            switch (action) {
//                case "get": {
//                    System.out.println(server.get(index));
//                    break;
//                }
//                case "set": {
//                    System.out.println(server.set(index, text.toString()));
//                    break;
//                }
//                case "delete": {
//                    System.out.println(server.delete(index));
//                    break;
//                }
//                default: {
//                    return;
//                }
//            }
//        }


    }
}
