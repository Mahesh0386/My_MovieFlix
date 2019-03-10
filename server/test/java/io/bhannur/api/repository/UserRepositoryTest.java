package io.bhannur.api.repository;

import lombok.RequiredArgsConstructor;
import main.java.io.bhannur.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RequiredArgsConstructor
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager em;


    /*@Test
    public void findAllReturns_AllUsersfromDB()
    {
        User user = new User().builder().userID("mahesh123").build();

        List<User> users = userRepository.findAll();
        assertNotNull(users.size());
    }*/

}
