package com.example.phoneapp.repository;

import com.example.phoneapp.model.Email;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EmailRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Email save(Email email) {
        entityManager.persist(email);
        return email;
    }

    public Email findById(Long id) {
        return entityManager.find(Email.class, id);
    }

    public List<Email> findByContactId(Long contactId) {
        TypedQuery<Email> query = entityManager.createQuery(
                "SELECT e FROM Email e WHERE e.contact.id = :contactId",
                Email.class
        );
        query.setParameter("contactId", contactId);
        return query.getResultList();
    }

    public Email update(Email email) {
        return entityManager.merge(email);
    }

    public void delete(Email email) {
        entityManager.remove(email);
    }
}
