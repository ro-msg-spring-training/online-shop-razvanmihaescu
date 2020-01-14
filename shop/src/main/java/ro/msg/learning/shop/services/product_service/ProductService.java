package ro.msg.learning.shop.services.product_service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.MyException;
import ro.msg.learning.shop.exceptions.ProductNotFoundException;
import ro.msg.learning.shop.repositories.IProductRepository;
import ro.msg.learning.shop.services.productCategory_service.IProductCategoryService;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private IProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductCategoryService productCategoryService;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product newProduct = null;
        try {
            newProduct = convertToEntityAndCheckCategory(productDto);
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
            throw new MyException();
        }
        return convertToDto(productRepository.save(newProduct));
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {
        Product product;
        try {
            product = convertToEntityAndCheckCategory(productDto);
            product.setProductId(id);
            productRepository.save(product);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getProducts() {
        List<Product> posts = productRepository.findAll();
        return posts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            return convertToDto(productOptional.get());
        } else throw new ProductNotFoundException(productId);
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntityAndCheckCategory(ProductDto productDto) throws ParseException {
        Product newProduct = modelMapper.map(productDto, Product.class);

        ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDto.getCategoryName());

        if (productCategory != null) {
            newProduct.setCategory(productCategory);
        } else {
            ProductCategory newCategory = new ProductCategory();
            newCategory.setName(productDto.getCategoryName());
            newCategory.setDescription(productDto.getCategoryDescription());

            ProductCategory persistedCategory = productCategoryService.createProductCategory(newCategory);
            newProduct.setCategory(persistedCategory);
        }
        return newProduct;
    }
}
