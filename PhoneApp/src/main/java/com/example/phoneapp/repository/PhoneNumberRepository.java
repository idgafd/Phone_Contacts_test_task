package com.example.phoneapp.repository;

import com.example.phoneapp.model.Contact;
import com.example.phoneapp.model.PhoneNumber;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PhoneNumberRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public PhoneNumber save(PhoneNumber phoneNumber) {
        entityManager.persist(phoneNumber);
        return phoneNumber;
    }


    public PhoneNumber findById(Long id) {
        return entityManager.find(PhoneNumber.class, id);
    }

    public List<PhoneNumber> findByContactId(Long contactId) {
        TypedQuery<PhoneNumber> query = entityManager.createQuery(
                "SELECT pn FROM PhoneNumber pn WHERE pn.contact.id = :contactId",
                PhoneNumber.class
        );
        query.setParameter("contactId", contactId);
        return query.getResultList();
    }

    public PhoneNumber update(PhoneNumber phoneNumber) {
        return entityManager.merge(phoneNumber);
    }

    public void delete(PhoneNumber phoneNumber) {
        entityManager.remove(phoneNumber);
    }
}

