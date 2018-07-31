package main.java.io.bhannur.api.repository;

import main.java.io.bhannur.api.entity.Shows;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class ShowRepositoryImpl implements ShowsRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Shows> findAll() {
        TypedQuery<Shows> query = em.createNamedQuery("Shows.findAll", Shows.class);
        return query.getResultList();
    }




}
