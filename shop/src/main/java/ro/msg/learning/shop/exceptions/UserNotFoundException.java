package ro.msg.learning.shop.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer userId) {
        super("Sorry, the user " + userId + "could not be found!");
    }

    public UserNotFoundException(String username) {
        super("Sorry, the user " + username + "could not be found!");
    }
}
