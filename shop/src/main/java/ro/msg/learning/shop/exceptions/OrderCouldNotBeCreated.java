package ro.msg.learning.shop.exceptions;

public class OrderCouldNotBeCreated extends RuntimeException {
    public OrderCouldNotBeCreated(String message) {
        super("Sorry, the order couldn't be created! Error message: "+message);
    }
}
