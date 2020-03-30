package ro.msg.learning.shop.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDto {

    @JsonAlias(value = "customer")
    private String username;

    //add an Alias in JSON Request that you can use instead of field name
    @JsonAlias(value = "address")
    private AddressDto deliveryLocation;

    @JsonAlias(value = "orderDetail")
    private List<OrderDetailDto> orderDetailDtos;
}
