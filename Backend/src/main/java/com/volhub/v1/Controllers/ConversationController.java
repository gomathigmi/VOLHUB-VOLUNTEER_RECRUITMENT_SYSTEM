package com.volhub.v1.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.ConverSationModel;
import com.volhub.v1.Services.Conversation.ConversationService;

@CrossOrigin(origins = "*")
@RequestMapping("/conversation")
@RestController
public class ConversationController {

    @Autowired
    private ConversationService conversationservice;

    @PostMapping("/create")
    public ResponseEntity<ConverSationModel> createConversation(@RequestParam String user1,
            @RequestParam String user2) {
        ConverSationModel conversation = conversationservice.createOrGetConversation(user1, user2);
        return ResponseEntity.ok(conversation);
    }

}
