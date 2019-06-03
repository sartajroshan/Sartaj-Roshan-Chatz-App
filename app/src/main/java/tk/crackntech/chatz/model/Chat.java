package tk.crackntech.chatz.model;

import java.io.File;

public class Chat {

    String id;
    User user;
    String type;
    String message;
    String image;

    public Chat(){}

    public Chat(String id, User user, String type, String message, String image) {
        this.id = id;
        this.user = user;
        this.type = type;
        this.message = message;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }
}
