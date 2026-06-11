package com.example.portfolio_backend.Service;

import com.example.portfolio_backend.Entity.ContactMessage;
import com.example.portfolio_backend.Entity.MessageStatus;
import com.example.portfolio_backend.Repository.ContactMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;

    public ContactMessageService(ContactMessageRepository contactMessageRepository) {
        this.contactMessageRepository = contactMessageRepository;
    }

    public ContactMessage submitMessage(ContactMessage contactMessage) {
        contactMessage.setStatus(MessageStatus.NEW);
        return contactMessageRepository.save(contactMessage);
    }

    public List<ContactMessage> getAllMessages() {
        return contactMessageRepository.findAllByOrderByIdDesc();
    }

    public ContactMessage markAsRead(Long id) {
        ContactMessage message = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setStatus(MessageStatus.READ);
        return contactMessageRepository.save(message);
    }

    public ContactMessage markAsReplied(Long id) {
        ContactMessage message = contactMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found"));

        message.setStatus(MessageStatus.REPLIED);
        return contactMessageRepository.save(message);
    }

    public void deleteMessage(Long id) {
        contactMessageRepository.deleteById(id);
    }
}
