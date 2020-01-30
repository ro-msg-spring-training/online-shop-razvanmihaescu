package ro.msg.learning.shop.exceptions;

public class ProductCategoryNotFoundException extends RuntimeException {
    public ProductCategoryNotFoundException(Integer id) {
        super("Could not find category with id:" + id);
    }

    public ProductCategoryNotFoundException(String productCategoryName)  {
        super("Could not find category with name:" + productCategoryName);
    }
}
