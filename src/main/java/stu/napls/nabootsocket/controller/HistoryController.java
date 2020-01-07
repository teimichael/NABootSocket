package stu.napls.nabootsocket.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.nabootsocket.auth.annotation.Auth;
import stu.napls.nabootsocket.core.response.Response;
import stu.napls.nabootsocket.service.MessageService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Resource
    private MessageService messageService;

    @ApiOperation("Get history of the conversation.")
    @GetMapping("/get/{conversationId}")
    private Response getByConversation(@PathVariable("conversationId") Long conversationId) {
        return Response.success(messageService.findByConversationId(conversationId));
    }

}
