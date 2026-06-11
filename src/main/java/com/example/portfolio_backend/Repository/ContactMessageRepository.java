package com.example.portfolio_backend.Repository;

import com.example.portfolio_backend.Entity.ContactMessage;
import com.example.portfolio_backend.Entity.MessageStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {
    List<ContactMessage> findAllByOrderByIdDesc();
    List<ContactMessage> findByStatusOrderByIdDesc(MessageStatus status);
}
