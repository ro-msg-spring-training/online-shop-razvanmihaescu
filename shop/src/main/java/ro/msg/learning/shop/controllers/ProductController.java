package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.services.ProductService;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    private ProductService productService;
}
