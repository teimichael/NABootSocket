package stu.napls.nabootsocket.auth.model;

import lombok.Data;

@Data
public class AuthPreregister {

    private String username;

    private String password;

    private String source;
}
