package io.bhannur.api.service;

import main.java.io.bhannur.api.entity.User;
import main.java.io.bhannur.api.exception.UserApiExceptions;
import main.java.io.bhannur.api.repository.UserRepository;
import main.java.io.bhannur.api.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    private User user;
    Random rand = new Random();
    private int ID = rand.nextInt(1000);

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_shouldCreateUser_whenValidUserDetailsAreSent() throws UserApiExceptions {
        User expected = User.builder().userID("123").email("test@gmail.com").build();
        when(userRepository.createUser(Mockito.any())).thenReturn(expected);
        User actual = userService.createUser(expected);
        assertEquals(expected.getUserID(),actual.getUserID());
    }

    @Test
    public void create_shouldNotCreateUser_whenInValidEmailIsGiven(){

        User input =  User.builder().userID("123").email("testgmail.com").build();
        try {
            userService.createUser(input);
        } catch (UserApiExceptions userApiExceptions) {
            assertEquals("Email not valid",userApiExceptions.getMessage());
        }
    }


}

