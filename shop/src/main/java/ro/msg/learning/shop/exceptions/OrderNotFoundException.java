package ro.msg.learning.shop.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Integer id) {
        super("Could not find order with id:" + id);
    }
}
