package dev.justdrven.minecraft.user.request;

public class UserRequest {

    private String uuid;
    private String nick;

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getNick() {
        return nick;
    }

    public String getUuid() {
        return uuid;
    }
}
