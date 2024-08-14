package com.volhub.v1.Services.Conversation;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.volhub.v1.Modals.ConverSationModel;
import com.volhub.v1.Repositories.Conversationrepo;

@Service
public class ConversationServiceImplementation implements ConversationService {

    @Autowired
    private Conversationrepo conversationrepo;

    public ConverSationModel createOrGetConversation(String user1, String user2) {
        
        List<ConverSationModel> existingConversations = conversationrepo.findByMembers(user1, user2);

        for (ConverSationModel conversation : existingConversations) {
            if (conversation.getMembers().containsAll(Arrays.asList(user1, user2)) && conversation.getMembers().size() == 2) {
                return conversation; // Conversation exists
            }
        }

        // If no conversation exists, create a new one
        ConverSationModel newConversation = new ConverSationModel();
        newConversation.setMembers(Arrays.asList(user1, user2));
        return conversationrepo.save(newConversation);
    }
}
