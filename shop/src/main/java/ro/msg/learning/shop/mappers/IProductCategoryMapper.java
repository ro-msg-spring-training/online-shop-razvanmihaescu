package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.ProductCategoryDto;
import ro.msg.learning.shop.entities.ProductCategory;

@Mapper
public interface IProductCategoryMapper {

    IProductCategoryMapper INSTANCE = Mappers.getMapper(IProductCategoryMapper.class);

    ProductCategory productCategoryDtoToProductCategory(ProductCategoryDto productCategoryDto);

    @InheritInverseConfiguration
    ProductCategoryDto productCategoryToProductCategoryDto(ProductCategory productCategory);
}
