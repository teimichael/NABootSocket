package stu.napls.nabootsocket.model.vo;

import lombok.Data;
import stu.napls.nabootsocket.auth.model.AuthLogin;

@Data
public class LoginVO {

    private String sessionId;

    private AuthLogin authLogin;
}
