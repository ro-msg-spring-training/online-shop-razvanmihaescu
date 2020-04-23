package ro.msg.learning.shop.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ro.msg.learning.shop.dtos.AddressDto;
import ro.msg.learning.shop.entities.Address;

@Mapper
public interface IAddressMapper {

    IAddressMapper INSTANCE = Mappers.getMapper(IAddressMapper.class);

    Address addressDtoToAddress(AddressDto addressDto);

    @InheritInverseConfiguration
    AddressDto addressToAddressDto(Address address);
}
