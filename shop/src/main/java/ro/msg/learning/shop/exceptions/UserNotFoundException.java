package ro.msg.learning.shop.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer username) {
        super("Sorry, the user "+username+"could not be found!");
    }
}
