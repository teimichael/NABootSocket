package stu.napls.nabootsocket.service.impl;

import org.springframework.stereotype.Service;
import stu.napls.nabootsocket.model.Conversation;
import stu.napls.nabootsocket.repository.ConversationRepository;
import stu.napls.nabootsocket.service.ConversationService;

import javax.annotation.Resource;
import java.util.List;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {

    @Resource
    private ConversationRepository conversationRepository;

    @Override
    public List<Conversation> findByUsersUuid(String uuid) {
        return conversationRepository.findByUsersUuid(uuid);
    }

    @Override
    public Conversation update(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
