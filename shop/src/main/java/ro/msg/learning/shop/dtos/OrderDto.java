package ro.msg.learning.shop.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import ro.msg.learning.shop.entities.Address;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {

    //add an Alias in JSON Request that you can use instead of field name
    @JsonAlias(value = "orderTimestamp")
    private LocalDateTime createdAt;

    private Address deliveryAddress;

    private List<OrderDetailDto> orderDetail;
}
