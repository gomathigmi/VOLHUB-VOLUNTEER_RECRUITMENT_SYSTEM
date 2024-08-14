package com.volhub.v1.Services;

import java.util.List;

import com.volhub.v1.Modals.MessageModal;

public interface MessageService {

    public MessageModal savemessage(MessageModal message);

    public List<MessageModal> GetAllmsg();

    public List<MessageModal> GetMsg(String id);

}
