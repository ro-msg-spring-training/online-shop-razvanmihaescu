package ro.msg.learning.shop.exceptions;

public class OrderCouldNotBeCreatedException extends RuntimeException {
    public OrderCouldNotBeCreatedException(String message) {
        super("Sorry, the order couldn't be created! Error message: "+message);
    }
}
