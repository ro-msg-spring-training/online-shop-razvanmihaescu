package ro.msg.learning.shop.exceptions;

public class ProductsNotAvailableException extends RuntimeException {
    public ProductsNotAvailableException() {
        super("Product not available.");
    }
}
