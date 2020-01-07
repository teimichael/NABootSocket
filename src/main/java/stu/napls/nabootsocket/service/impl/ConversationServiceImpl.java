package stu.napls.nabootsocket.service.impl;

import org.springframework.stereotype.Service;
import stu.napls.nabootsocket.core.dictionary.AppCode;
import stu.napls.nabootsocket.model.Conversation;
import stu.napls.nabootsocket.model.User;
import stu.napls.nabootsocket.repository.ConversationRepository;
import stu.napls.nabootsocket.service.ConversationService;

import javax.annotation.Resource;
import java.util.List;

@Service("conversationService")
public class ConversationServiceImpl implements ConversationService {

    @Resource
    private ConversationRepository conversationRepository;

    @Override
    public List<Conversation> findByUuid(String uuid) {
        return conversationRepository.findByUsersContaining(uuid);
    }

    @Override
    public Conversation findPrivateByUuids(String uuid0, String uuid1) {
        List<Conversation> conversations = conversationRepository.findByTypeAndUsersContaining(AppCode.Conversation.PRIVATE.getValue(), uuid0);
        Conversation result = null;

        for (Conversation conversation :
                conversations) {
            if (conversation.getUsers().contains(uuid1)) {
                result = conversation;
                break;
            }
        }

        return result;
    }

    @Override
    public Conversation update(Conversation conversation) {
        return conversationRepository.save(conversation);
    }
}
