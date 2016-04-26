package com.gilt.phonebook.repository;

import com.gilt.phonebook.util.Loggable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class EntryRepository implements Loggable {

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<EntryEntity> findAll() {
        TypedQuery<EntryEntity> query = entityManager.createQuery("SELECT e FROM EntryEntity e", EntryEntity.class);
        return query.getResultList();
    }

    public void create(EntryEntity entryEntity) {
        entityManager.persist(entryEntity);
    }

    public void delete(long id) {
        EntryEntity entity = entityManager.find(EntryEntity.class, id);

        if (entity == null) {
            log().info("Delete failed with id `{}` - contact not found", id);
            return;
        }

        entityManager.remove(entity);
    }
}
