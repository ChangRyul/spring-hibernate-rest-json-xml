package com.igloosec.app;

import com.igloosec.app.dto.response.UserResponse;
import com.igloosec.app.exceptions.UserNotFoundException;
import com.igloosec.app.domain.dao.UserDAO;
import com.igloosec.app.domain.tables.User;
import com.igloosec.app.service.UserService;
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
    private UserDAO userDAO;

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
