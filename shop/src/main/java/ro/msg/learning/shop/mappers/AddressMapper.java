package ro.msg.learning.shop.mappers;

import org.springframework.stereotype.Component;
import ro.msg.learning.shop.dtos.AddressDto;
import ro.msg.learning.shop.entities.Address;

@Component
public class AddressMapper {
    public AddressDto convertToDto(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .county(address.getCounty())
                .street(address.getStreet())
                .build();
    }

    public Address convertToEntity(AddressDto addressDto) {
        return Address.builder()

                .build();
    }
}
