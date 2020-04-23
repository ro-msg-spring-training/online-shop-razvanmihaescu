package ro.msg.learning.shop.dtos;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocationDto {

    private String name;

    private AddressDto addressDto;
}
