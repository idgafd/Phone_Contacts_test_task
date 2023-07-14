package com.example.phoneapp.repository;

import com.example.phoneapp.model.Contact;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ContactRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Contact save(Contact contact) {
        entityManager.persist(contact);
        return contact;
    }

    public Contact findById(Long id) {
        return entityManager.find(Contact.class, id);
    }

    public List<Contact> findAll() {
        TypedQuery<Contact> query = entityManager.createQuery("SELECT c FROM Contact c", Contact.class);
        return query.getResultList();
    }

    public Contact update(Contact contact) {
        return entityManager.merge(contact);
    }

    public void delete(Contact contact) {
        entityManager.remove(contact);
    }
}
