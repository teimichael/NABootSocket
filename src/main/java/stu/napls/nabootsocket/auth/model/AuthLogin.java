package stu.napls.nabootsocket.auth.model;

import lombok.Data;

@Data
public class AuthLogin {

    private String username;

    private String password;

    private String source;
}
