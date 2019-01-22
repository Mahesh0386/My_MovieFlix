package io.bhannur.api.service;

import main.java.io.bhannur.api.entity.User;
import main.java.io.bhannur.api.repository.UserRepository;
import main.java.io.bhannur.api.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private User user ;
    Random rand = new Random();
    private int ID = rand.nextInt(1000);
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_shouldCreateUser_whenValidUserDetailsAreSent() {
        user = new User();
        user.setRole("Admin");
        user.setUserID("TestUser_00_"+ID);
        user.setEmail("testuser@movieflix.com");
        user.setFirstName("Test");
        user.setLastName("User");
        user.setPassword("123");
        String userID = user.getUserID();

        when(userRepository.createUser((user))).thenReturn(user);

        User userReturned = userRepository.createUser(user);

        assertEquals(userID, userReturned.getUserID());
        assertEquals("Admin", userReturned.getRole());
        assertEquals("testuser@movieflix.com", userReturned.getEmail());
        assertEquals("Test", userReturned.getFirstName());
        assertEquals("User", userReturned.getLastName());
        assertEquals("123", userReturned.getPassword());

    }


}

