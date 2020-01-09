package stu.napls.nabootsocket.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stu.napls.nabootsocket.core.response.Response;
import stu.napls.nabootsocket.service.MessageService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/history")
public class HistoryController {

    @Resource
    private MessageService messageService;

    @ApiOperation("Get history of the conversation.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "size", value = "Size of a page"),
            @ApiImplicitParam(name = "page", value = "Page number starting from 0")
    })
    @GetMapping("/get/{conversationUuid}")
    private Response getByConversation(@PathVariable("conversationUuid") String conversationUuid, Pageable pageable) {
        return Response.success(messageService.findByConversationUuid(conversationUuid, pageable));
    }

}
