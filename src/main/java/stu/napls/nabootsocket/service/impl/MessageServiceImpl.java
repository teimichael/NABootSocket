package stu.napls.nabootsocket.service.impl;

import org.springframework.stereotype.Service;
import stu.napls.nabootsocket.model.Message;
import stu.napls.nabootsocket.repository.MessageRepository;
import stu.napls.nabootsocket.service.MessageService;

import javax.annotation.Resource;
import java.util.List;

@Service("messageService")
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageRepository messageRepository;

    @Override
    public List<Message> findByConversationUuid(String conversationUuid) {
        return messageRepository.findByConversationUuid(conversationUuid);
    }

    @Override
    public Message update(Message message) {
        return messageRepository.save(message);
    }
}
