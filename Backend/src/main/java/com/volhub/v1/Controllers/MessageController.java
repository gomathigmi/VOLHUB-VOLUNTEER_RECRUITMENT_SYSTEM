package com.volhub.v1.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.volhub.v1.Modals.MessageModal;
import com.volhub.v1.Services.MessageService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@CrossOrigin(origins = "*")
@RequestMapping("/chat")
@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/sendmessage")
    public String postMethodName(@RequestBody MessageModal msg) {

        messageService.savemessage(msg);

        return "Message is Saved";
    }

    @GetMapping("/get/msg")
    public List<MessageModal> getMsg(@RequestParam String conversationid) {
        System.out.println(conversationid);
        return messageService.GetMsg(conversationid);
    }

   

}
