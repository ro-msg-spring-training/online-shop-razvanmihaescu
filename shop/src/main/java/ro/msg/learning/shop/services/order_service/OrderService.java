package ro.msg.learning.shop.services.order_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.IDeliveryStrategy;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.entities.Address;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.exceptions.OrderCouldNotBeCreated;
import ro.msg.learning.shop.exceptions.OrderNotFoundException;
import ro.msg.learning.shop.mappers.OrderDetailMapper;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.repositories.IOrderRepository;
import ro.msg.learning.shop.services.orderDetail_service.IOrderDetailService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository ordersRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderDetailMapper orderDetailMapper;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IDeliveryStrategy deliveryStrategy;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order persistedOrder = null;

        Address address = Address.builder()
                .country(orderDto.getDeliveryLocation().getCountry())
                .city(orderDto.getDeliveryLocation().getCity())
                .street(orderDto.getDeliveryLocation().getStreet())
                .county(orderDto.getDeliveryLocation().getCounty())
                .build();


        Location location = Location.builder()
                .name("Numele locatiei")
                .address(address)
                .build();

        deliveryStrategy.doAlgorithm();//TODO strategy algorithm

        try {
            Order newOrder = orderMapper.convertToEntity(orderDto);
            List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetail();
            List<OrderDetail> orderDetails = orderDetailDtos.stream().map(a -> orderDetailMapper.convertToEntity(a)).collect(Collectors.toList());

            orderDetails.forEach(orderDetail -> {
                orderDetail.setId(null);//avoiding Generation ID override
                orderDetail.setOrder(newOrder);
            });

            newOrder.setOrderDetail(orderDetails);
            newOrder.setDeliveryLocation(location);
            newOrder.setCreatedAt(LocalDateTime.now());
            persistedOrder = ordersRepository.save(newOrder);

        } catch (RuntimeException e) {
            throw new OrderCouldNotBeCreated();
        }
        return orderMapper.convertToDto(persistedOrder);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Optional<Order> orderOptional = ordersRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderMapper.convertToDto(orderOptional.get());
        } else {
            throw new OrderNotFoundException(id);
        }
    }
}
