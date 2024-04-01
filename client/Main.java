package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Main {
    @Parameter(names = {"-type", "-t"})
    String requestType;
    @Parameter(names = {"--index", "-i"})
    Integer index;
    @Parameter(names = {"--mValue", "-m"})
    String text;

    public static void main(String... argv) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);
        main.run();
    }

    public void run() {
        System.out.printf("%s %d %s", requestType, index, text);
        System.out.println("Client started!");
        String address = "127.0.0.1";
        int port = 23456;

        try {
            Socket socket = new Socket(InetAddress.getByName(address), port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            output.writeUTF("Give me a record # " + 12);
            System.out.println("Sent: Give me a record # 12");
            System.out.println("Received: " + input.readUTF());

        } catch (IOException exception) {
            System.err.println("Exception riot: " + exception.getMessage());
        }


    }
}
