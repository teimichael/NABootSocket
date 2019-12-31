package stu.napls.nabootsocket.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import stu.napls.nabootsocket.core.dictionary.StatusCode;
import stu.napls.nabootsocket.core.exception.Assert;
import stu.napls.nabootsocket.core.response.Response;
import stu.napls.nabootsocket.model.User;
import stu.napls.nabootsocket.model.vo.Auth;
import stu.napls.nabootsocket.model.vo.SocketRegister;
import stu.napls.nabootsocket.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("/access")
public class AccessController {

    private static final Logger logger = LoggerFactory.getLogger(AccessController.class);

    @Resource
    private UserService userService;

    @MessageMapping("/auth")
    @SendToUser("/auth")
    public Response auth(Auth auth, SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        String uuid;
        // TODO token verification
        switch (auth.getToken()) {
            case "t1":
                uuid = "u1";
                break;
            case "t2":
                uuid = "u2";
                break;
            default:
                uuid = null;
        }
        Assert.notNull(uuid, HttpStatus.UNAUTHORIZED.value(), "Authentication failed.");

        User user = userService.findUserByUuid(uuid);
        Assert.notNull(user, HttpStatus.UNAUTHORIZED.value(), "User does not exist.");

        user.setSessionId(simpMessageHeaderAccessor.getSessionId());
        userService.update(user);

        return Response.success("Login successfully.", user);
    }

    @MessageMapping("/logout")
    @SendToUser("/auth")
    public Response logout(SimpMessageHeaderAccessor simpMessageHeaderAccessor) {
        User user = userService.findUserBySessionId(simpMessageHeaderAccessor.getSessionId());
        Assert.notNull(user, HttpStatus.UNAUTHORIZED.value(), "Authentication failed.");
        user.setSessionId(null);
        userService.update(user);
        return Response.success("Logout successfully",user);
    }

    @PostMapping("/register")
    @ResponseBody
    public Response register(HttpServletRequest httpServletRequest, @RequestBody SocketRegister socketRegister) {
        System.out.println(httpServletRequest.getRemoteHost());
        Assert.isTrue(userService.findUserByUuid(socketRegister.getUuid()) == null, "User has been registered");
        User user = new User();
        user.setUuid(socketRegister.getUuid());
        user.setCreateDate(new Date());
        user.setStatus(StatusCode.NORMAL);
        userService.update(user);
        return Response.success("Register successfully");
    }

}
