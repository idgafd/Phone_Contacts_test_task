package com.example.phoneapp.service;

import com.example.phoneapp.model.Email;
import com.example.phoneapp.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailService {
    private final EmailRepository emailRepository;

    @Autowired
    public EmailService(EmailRepository emailRepository) {
        this.emailRepository = emailRepository;
    }

    public List<Email> getEmailsByContactId(Long contactId) {
        return emailRepository.findByContactId(contactId);
    }

    public Email createEmail(Email email) {
        return emailRepository.save(email);
    }

    public Optional<Email> updateEmail(Long id, Email email) {
        Optional<Email> existingEmailOptional = Optional.ofNullable(emailRepository.findById(id));
        if (existingEmailOptional.isPresent()) {
            Email existingEmail = existingEmailOptional.get();
            existingEmail.setEmail(email.getEmail());
            return Optional.of(emailRepository.save(existingEmail));
        }
        return Optional.empty();
    }

    public boolean deleteEmail(Long id) {
        Optional<Email> emailOptional = Optional.ofNullable(emailRepository.findById(id));
        if (emailOptional.isPresent()) {
            emailRepository.delete(emailOptional.get());
            return true;
        }
        return false;
    }
}

