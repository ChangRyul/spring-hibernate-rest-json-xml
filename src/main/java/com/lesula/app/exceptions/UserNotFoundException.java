package com.lesula.app.exceptions;

/**
 * Created by enrico on 9/5/14.
 */
public class UserNotFoundException extends AppException {

   public UserNotFoundException(int id){
       super(String.format("User with id %d not found", id));
   }

    @Override
    public String getCode() {
        return "1";
    }
}
