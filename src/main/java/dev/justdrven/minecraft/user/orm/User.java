package dev.justdrven.minecraft.user.orm;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {

    private String uuid;
    private String nick;

    public String getNick() {
        return nick;
    }

    public String getUUID() {
        return uuid;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }
}
