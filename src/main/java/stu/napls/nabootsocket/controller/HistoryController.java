package stu.napls.nabootsocket.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.nabootsocket.core.response.Response;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @ApiOperation("Get history of the conversation.")
    @GetMapping("/get/{conversationId}")
    private Response getByConversation(@PathVariable("conversationId") String conversationId) {
        return Response.success("");
    }
}
