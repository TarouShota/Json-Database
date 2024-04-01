package server;

import java.util.Arrays;

public class Server {
    static int size=101;
    private final String[] database = new String[size];

    public Server(){
        Arrays.fill(this.database,"");
    }
    public String get(int index)
    {
        if(this.database[index].isBlank()) return "ERROR";
        return this.database[index];
    }

    private boolean isIndexOutOfBounds(int index) {
        return index > size - 1 || index < 0;
    }

    public String set(int index,String text){
       database[index] = text;
       return "OK";
    }
    public String delete(int index){
        database[index]="";
        return "OK";
    }
}
