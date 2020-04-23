package ro.msg.learning.shop.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.repositories.IOrderDetailRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final IOrderDetailRepository orderDetailRepository;

    public void persistOrderDetails(List<OrderDetail> orderDetails) {
        orderDetailRepository.saveAll(orderDetails);
    }
}
