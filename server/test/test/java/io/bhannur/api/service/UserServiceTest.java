package test.java.io.bhannur.api.service;

import main.java.io.bhannur.api.entity.User;
import main.java.io.bhannur.api.repository.UserRepositoryImpl;
import main.java.io.bhannur.api.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private User user ;

    @Mock
    private UserRepositoryImpl userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() {
        user = new User();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void create_shouldCreateUser_whenValidUserDetailsAreSent() {
        when(userRepository.createUser(any(User.class))).thenReturn(new User());
        assertThat(userService.createUser(user), is(notNullValue()));
    }

    //Using Answer to set an id to the user which is passed in as a parameter to the mock method.
    @Test
    public void create_returnsNewUserWithId() {

        when(userRepository.createUser(any(User.class))).thenAnswer(new Answer<User>() {

            @Override
            public User answer(InvocationOnMock invocation) {
                Object[] arguments = invocation.getArguments();
                System.out.println(arguments[0]);
                if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                    User user = (User) arguments[0];
                    user.setUserID("test@gmail.com");
                    return user;
                }
                return null;
            }
        });

        assertThat(userService.createUser(user), is(notNullValue()));
        assertEquals("test@gmail.com",user.getUserID());
    }

    //Throwing an exception from the mocked method
    @Test(expected = RuntimeException.class)
    public void testAddCustomer_throwsException() {
        when(userRepository.createUser(any(User.class))).thenThrow(RuntimeException.class);
         userService.createUser(user);
    }

    @Test
    public void deleteUser_shouldDeleteUser()
    {
    }

}

