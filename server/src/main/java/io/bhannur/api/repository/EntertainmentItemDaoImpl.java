package main.java.io.bhannur.api.repository;

import main.java.io.bhannur.api.entity.EntertainmentItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class EntertainmentItemDaoImpl implements EntertainmentItemDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<EntertainmentItem> findAll() {
        TypedQuery<EntertainmentItem> query = em.createNamedQuery("EntertainmentItem.findAll", EntertainmentItem.class);
        return query.getResultList();
    }

    @Override
    public EntertainmentItem createItem(EntertainmentItem item) {
        em.persist(item);
        return item;
    }

    @Override
    public EntertainmentItem updateItem(EntertainmentItem item) {
        return em.merge(item);
    }

    @Override
    public EntertainmentItem findOneItem(String id) {
        return em.find(EntertainmentItem.class, id);
    }


}
