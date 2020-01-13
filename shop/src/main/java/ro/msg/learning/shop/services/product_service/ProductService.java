package ro.msg.learning.shop.services.product_service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.ProductDto;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.exceptions.MyException;
import ro.msg.learning.shop.repositories.ProductRepository;
import ro.msg.learning.shop.services.productCategory_service.IProductCategoryService;

import java.text.ParseException;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductCategoryService productCategoryService;

    @Override
    public ProductDto createProduct(ProductDto productDto) {

        Product newProduct;
        try {
            newProduct = convertToEntity(productDto);
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
            throw new MyException();
        }

        ProductCategory productCategory = productCategoryService.getProductCategoryByName(productDto.getCategoryName());

        if (productCategory != null) {
            newProduct.setCategory(productCategory);
        } else {
            ProductCategory newCategory = new ProductCategory();
            newCategory.setName(productDto.getName());
            newCategory.setDescription(productDto.getDescription());

            ProductCategory newPersistedCategory = productCategoryService.createProductCategory(newCategory);
            newProduct.setCategory(newPersistedCategory);
        }

        return convertToDto(productRepository.save(newProduct));
    }

    @Override
    public void updateProduct(Integer id, ProductDto productDto) {

    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteProductByProductId(id);
    }

    @Override
    public List<Product> getProducts() {
        return null;
    }

    @Override
    public ProductDto getProductById(Integer productId) {
        Product selectedProduct = productRepository.findProductByProductId(productId);
        return convertToDto(selectedProduct);
    }

    private ProductDto convertToDto(Product product) {
        return modelMapper.map(product, ProductDto.class);
    }

    private Product convertToEntity(ProductDto productDto) throws ParseException {
        return modelMapper.map(productDto, Product.class);
    }
}
