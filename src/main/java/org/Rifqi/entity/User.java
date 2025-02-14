package org.Rifqi.entity;

import java.util.UUID;

public class User {
    private final UUID userId;
    private final String userName;
    private final String password;

    public User(String userName, String password) {
        this.userId = UUID.randomUUID();
        this.userName = userName;
        this.password = password;
    }

    public boolean checkLogin(String userName, String password) {
        return this.userName.equalsIgnoreCase(userName) && this.password.equals(password);
    }

    public String getUsername() {
        return userName;
    }
}
