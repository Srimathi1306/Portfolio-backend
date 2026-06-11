package com.example.portfolio_backend.Controller;

import com.example.portfolio_backend.Entity.ContactMessage;
import com.example.portfolio_backend.Service.ContactMessageService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ContactMessageController {

    private final ContactMessageService contactMessageService;

    public ContactMessageController(ContactMessageService contactMessageService) {
        this.contactMessageService = contactMessageService;
    }

    @PostMapping("/contact")
    public ContactMessage submitMessage(@Valid @RequestBody ContactMessage contactMessage) {
        return contactMessageService.submitMessage(contactMessage);
    }

    @GetMapping("/admin/messages")
    public List<ContactMessage> getAllMessages() {
        return contactMessageService.getAllMessages();
    }

    @PutMapping("/admin/messages/{id}/read")
    public ContactMessage markAsRead(@PathVariable Long id) {
        return contactMessageService.markAsRead(id);
    }

    @PutMapping("/admin/messages/{id}/replied")
    public ContactMessage markAsReplied(@PathVariable Long id) {
        return contactMessageService.markAsReplied(id);
    }

    @DeleteMapping("/admin/messages/{id}")
    public void deleteMessage(@PathVariable Long id) {
        contactMessageService.deleteMessage(id);
    }
}
