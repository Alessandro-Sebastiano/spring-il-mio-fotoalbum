package org.project.java.springilmiofotoalbum.api;

import org.project.java.springilmiofotoalbum.model.Message;
import org.project.java.springilmiofotoalbum.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/contact")
public class MessageRestController {

    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public Message storeMessage(@RequestBody Message message) {
        messageService.storeMessage(message);
        return message;
    }

}
