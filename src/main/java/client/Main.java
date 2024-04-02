package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;


public class Main {
    @Parameter(names = {"-type", "-t"})
    String requestType;
    @Parameter(names = {"--index", "-i"})
    Integer index;
    @Parameter(names = {"--mValue", "-m"})
    String textValue;

    public static void main(String... argv) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();
    }
    public void run() {
        System.out.println("Client started!");
        String address = "127.0.0.1";
        int port = 23456;
        String toSend = requestType + " " + index + " " + textValue;
        // System.out.println(toSend);
        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());


            output.writeUTF(toSend);
            System.out.println("Sent: " + toSend);
            System.out.println("Received: " + input.readUTF());

        } catch (IOException exception) {
            System.err.println("Exception riot: " + exception.getMessage());
        }

    }
}
