package com.example.phoneapp.service;

import com.example.phoneapp.model.PhoneNumber;
import com.example.phoneapp.repository.PhoneNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }

    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    public Optional<PhoneNumber> getPhoneNumberById(Long id) {
        return Optional.ofNullable(phoneNumberRepository.findById(id));
    }

    public List<PhoneNumber> getPhoneNumbersByContactId(Long contactId) {
        return phoneNumberRepository.findByContactId(contactId);
    }

    public PhoneNumber createPhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public Optional<PhoneNumber> updatePhoneNumber(Long id, PhoneNumber phoneNumber) {
        Optional<PhoneNumber> existingPhoneNumberOptional = Optional.ofNullable(phoneNumberRepository.findById(id));
        if (existingPhoneNumberOptional.isPresent()) {
            PhoneNumber existingPhoneNumber = existingPhoneNumberOptional.get();
            existingPhoneNumber.setPhoneNumber(phoneNumber.getPhoneNumber());
            return Optional.of(phoneNumberRepository.save(existingPhoneNumber));
        }
        return Optional.empty();
    }

    public boolean deletePhoneNumber(Long id) {
        Optional<PhoneNumber> phoneNumberOptional = Optional.ofNullable(phoneNumberRepository.findById(id));
        if (phoneNumberOptional.isPresent()) {
            phoneNumberRepository.delete(phoneNumberOptional.get());
            return true;
        }
        return false;
    }
}
