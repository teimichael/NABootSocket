package stu.napls.nabootsocket.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.nabootsocket.core.response.Response;
import stu.napls.nabootsocket.model.Conversation;
import stu.napls.nabootsocket.service.ConversationService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/conversation")
public class ConversationController {

    @Resource
    private ConversationService conversationService;

    @ApiOperation("Get conversation list of user.")
    @GetMapping("/get/user/{uuid}")
    private Response getListByUser(@PathVariable("uuid") String uuid) {
        List<Conversation> conversations = conversationService.findByUserUuid(uuid);
        return Response.success(conversations);
    }

}
