package com.gilt.phonebook.repository;

import com.gilt.phonebook.controller.CreateContact;
import com.gilt.phonebook.logic.SortDirection;
import com.gilt.phonebook.logic.SortField;
import com.gilt.phonebook.util.Loggable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import static com.gilt.phonebook.logic.SortDirection.ascending;

@Repository
public class EntryRepository implements Loggable {

    @PersistenceContext
    private EntityManager entityManager;

    public Iterable<EntryEntity> findAll(SortField sortField,
                                         SortDirection sortDirection) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<EntryEntity> query = cb.createQuery(EntryEntity.class);
        Root<EntryEntity> root = query.from(EntryEntity.class);

        query.select(root);

        Path<Object> sortFieldPath = root.get(sortField.toString());
        Order order = sortDirection == ascending
                ? cb.asc(sortFieldPath)
                : cb.desc(sortFieldPath);
        query.orderBy(order);

        return entityManager.createQuery(query).getResultList();
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

    public void edit(long id,
                     CreateContact contact) {
        EntryEntity entity = entityManager.find(EntryEntity.class, id);

        if (entity == null) {
            throw new IllegalArgumentException("Delete failed with id `{" + id + "}` - contact not found");
        }

        entity.setFirstName(contact.getFirstName());
        entity.setLastName(contact.getLastName());
        entity.setPhoneType(contact.getPhoneType());
        entity.setPhoneNumber(contact.getPhoneNumber());
    }
}
