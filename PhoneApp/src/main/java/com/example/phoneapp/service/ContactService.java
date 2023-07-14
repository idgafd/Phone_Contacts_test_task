package com.example.phoneapp.service;

import com.example.phoneapp.model.Contact;
import com.example.phoneapp.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return Optional.ofNullable(contactRepository.findById(id));
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Optional<Contact> updateContact(Long id, Contact contact) {
        Optional<Contact> existingContactOptional = Optional.ofNullable(contactRepository.findById(id));
        if (existingContactOptional.isPresent()) {
            Contact existingContact = existingContactOptional.get();
            existingContact.setName(contact.getName());
            return Optional.of(contactRepository.save(existingContact));
        }
        return Optional.empty();
    }

    public boolean deleteContact(Long id) {
        Optional<Contact> contactOptional = Optional.ofNullable(contactRepository.findById(id));
        if (contactOptional.isPresent()) {
            contactRepository.delete(contactOptional.get());
            return true;
        }
        return false;
    }
}
