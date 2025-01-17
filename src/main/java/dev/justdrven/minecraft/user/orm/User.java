package dev.justdrven.minecraft.user.orm;

import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
