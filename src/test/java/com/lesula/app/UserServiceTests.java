package com.lesula.app;

import com.lesula.app.domain.dao.IUserDAO;
import com.lesula.app.domain.tables.User;
import com.lesula.app.dto.response.UserResponse;
import com.lesula.app.exceptions.UserNotFoundException;
import com.lesula.app.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by enrico on 9/8/14.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTests {

    @Mock
    private IUserDAO userDAO;

    @InjectMocks
    private UserService userService;

    @Test(expected=UserNotFoundException.class)
    public void testThrowsUsernotFoundException() throws UserNotFoundException {
        when(userDAO.findById(1)).thenReturn(null);
        userService.getUserDetails(1);
    }

    @Test
    public void testReturnsUser() throws UserNotFoundException {
        User user = new User();
        user.setId(1);
        when(userDAO.findById(1)).thenReturn(user);
        UserResponse userDetails = userService.getUserDetails(1);
        assertTrue(userDetails.getId() == user.getId());
    }


}
