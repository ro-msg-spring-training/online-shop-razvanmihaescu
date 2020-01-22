package ro.msg.learning.shop.exceptions;

public class OrderCouldNotBeCreated extends RuntimeException {
    public OrderCouldNotBeCreated() {
        super("Sorry, the order couldn't be created!");
    }
}
