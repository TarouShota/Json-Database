package server;

import java.util.Arrays;

public class Server {
    static int size = 1001;
    private final String[] database = new String[size];

    public String get(int index) {
        if (this.database[index] == null || this.database[index].isBlank()) return "ERROR";
        return this.database[index];
    }

    public String set(int index, String text) {
        database[index] = text;
        return "OK";
    }

    public String delete(int index) {
        database[index] = "";
        return "OK";
    }
}
