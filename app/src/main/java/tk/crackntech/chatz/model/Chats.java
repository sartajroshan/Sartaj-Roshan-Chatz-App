package tk.crackntech.chatz.model;

import java.util.List;

public class Chats {
    String id;
    String name;
    List<Chat> chats;


    public Chats(){

    }



    public Chats(String id, String name, List<Chat> chats) {
        this.id = id;
        this.name = name;
        this.chats = chats;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public List<Chat> getChats() {
        return chats;
    }
}
