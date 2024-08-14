package com.volhub.v1.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.MessageModal;
import com.volhub.v1.Repositories.Chatrepo;

@Service
public class MessageServiceImplementatio implements MessageService {

    @Autowired
    private Chatrepo msgrepo;

    @Override
    public MessageModal savemessage(MessageModal message) {
        return msgrepo.save(message);
    }

    @Override
    public List<MessageModal> GetAllmsg() {
        return msgrepo.findAll();
    }

    @Override
    public List<MessageModal> GetMsg(String id) {

        return msgrepo.findByConversationId(id);
    }

}
