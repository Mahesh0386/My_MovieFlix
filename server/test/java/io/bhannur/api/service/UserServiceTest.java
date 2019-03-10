package io.bhannur.api.service;

import io.bhannur.api.entity.User;
import io.bhannur.api.exception.BadRequestException;
import io.bhannur.api.exception.UserApiExceptions;
import io.bhannur.api.repository.UserRepository;
import io.bhannur.api.service.Impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    public void create_shouldCreateUser_whenValidUserDetailsAreSent() throws UserApiExceptions, BadRequestException {
        User expected = User.builder().userID("123").email("test@gmail.com").build();
        when(userRepository.createUser(Mockito.any())).thenReturn(expected);
        User actual = userService.createUser(expected);
        assertEquals(expected.getUserID(), actual.getUserID());
    }

    @Test
    public void create_shouldNotCreateUser_whenInValidEmailIsGiven() throws BadRequestException {
        User input = User.builder().userID("123").email("testgmail.com").build();
        try {
            userService.createUser(input);
        } catch (UserApiExceptions userApiExceptions) {
            assertEquals("Email not valid", userApiExceptions.getMessage());
        }
    }

    @Test
    public void create_shouldNotCreateUser_whenUserAlreadyExists() throws UserApiExceptions {
        User user = User.builder().userID("123").email("test@gmail.com").build();

        when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user);

        try {
            userService.createUser(user);
        } catch (BadRequestException badRequestException) {
            assertEquals(badRequestException.getMessage(), "User with this email already exists");
        }
    }


}

