package stu.napls.nabootsocket.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public List<Message> findByConversationUuidOrderByTimestamp(String conversationUuid, Pageable pageable) {
        return messageRepository.findByConversationUuidOrderByTimestamp(conversationUuid, pageable);
    }

    @Override
    public Message update(Message message) {
        return messageRepository.save(message);
    }
}
