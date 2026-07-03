package com.javaee.clientinfomanagesystem.po;
import lombok.Data;

@Data
public class user {
    private int uid;
    private String username;
    private String password;
    private String email;
    private role role;
}
