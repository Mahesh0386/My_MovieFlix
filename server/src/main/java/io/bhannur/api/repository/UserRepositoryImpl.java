package main.java.io.bhannur.api.repository;

import main.java.io.bhannur.api.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

    @Repository
    public class UserRepositoryImpl implements UserRepository {

        @PersistenceContext
        private EntityManager em;

        @Override
        public List<User> findAll() {
            TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
            return query.getResultList();
        }

        @Override
        public User findOne(String id) {
            return em.find(User.class, id);
        }

        @Override
        public User findByEmail(String email) {
            TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
            query.setParameter("pEmail", email);

            List<User> userloyees = query.getResultList();
            if (userloyees != null && userloyees.size() == 1) {
                return userloyees.get(0);
            } else {
                return null;
            }
        }

        @Override
        public User create(User user) {
            em.persist(user);
            return user;
        }

        @Override
        public User update(User user) {
            return em.merge(user);
        }

        @Override
        public void delete(User user) {
            em.remove(user);
        }
    }