package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AddressDto {

    private String country;

    private String city;

    private String county;

    private String street;
}
