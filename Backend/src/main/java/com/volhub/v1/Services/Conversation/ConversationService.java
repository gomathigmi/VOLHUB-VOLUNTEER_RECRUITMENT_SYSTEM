package com.volhub.v1.Services.Conversation;

import com.volhub.v1.Modals.ConverSationModel;

public interface ConversationService {
     public ConverSationModel createOrGetConversation(String user1, String user2);
}
