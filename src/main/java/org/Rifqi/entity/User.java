package org.Rifqi.entity;

import java.util.UUID;

public class User {
    private UUID userId;
    private String userName;
    private String password;
        public User(String userName,String password){
            this.userId=UUID.randomUUID();
            this.userName=userName;
            this.password=password;
    }
public boolean checkLogin(String userName,String password){
 return this.userName.equalsIgnoreCase(userName) && this.password.equals(password);
}
}
