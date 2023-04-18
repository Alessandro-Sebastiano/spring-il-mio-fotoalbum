package org.project.java.springilmiofotoalbum.service;

import org.project.java.springilmiofotoalbum.model.Message;
import org.project.java.springilmiofotoalbum.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public ResponseEntity<Message> storeMessage(Message message) {
        Message newMessage = new Message();
        newMessage.setEmail(message.getEmail());
        newMessage.setMessage(message.getMessage());
        messageRepository.save(newMessage);
        return ResponseEntity.ok(newMessage);
    }

}
