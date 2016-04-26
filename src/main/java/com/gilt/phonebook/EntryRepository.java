package com.gilt.phonebook;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class EntryRepository {

    @PersistenceContext
    private EntityManager entityManager;

    Iterable<EntryEntity> findAll() {
        TypedQuery<EntryEntity> query = entityManager.createQuery("SELECT e FROM EntryEntity e", EntryEntity.class);
        return query.getResultList();
    }

    public void create(EntryEntity entryEntity) {
        entityManager.persist(entryEntity);
    }
}
