package ro.msg.learning.shop.services.order_service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetail;
import ro.msg.learning.shop.exceptions.OrderNotFoundException;
import ro.msg.learning.shop.repositories.IOrderRepository;
import ro.msg.learning.shop.services.orderDetail_service.IOrderDetailService;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository ordersRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    IOrderDetailService orderDetailService;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order persistedOrder = null;

        try {
            Order newOrder = convertToEntity(orderDto);
            List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetail();
            List<OrderDetail> orderDetails = orderDetailDtos.stream().map(a -> orderDetailService.convertToEntity(a)).collect(Collectors.toList());

            orderDetails.forEach(orderDetail -> {
                orderDetail.setId(null);//avoiding Generation ID override
                orderDetail.setOrder(newOrder);
            });


            newOrder.setOrderDetail(orderDetails);

            persistedOrder = ordersRepository.save(newOrder);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return convertToDto(persistedOrder);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Optional<Order> orderOptional = ordersRepository.findById(id);
        if (orderOptional.isPresent()) {
            return convertToDto(orderOptional.get());
        } else {
            throw new OrderNotFoundException(id);
        }
    }

    public OrderDto convertToDto(Order order) {
        return modelMapper.map(order, OrderDto.class);
    }

    public Order convertToEntity(OrderDto orderDto) throws ParseException {
        return modelMapper.map(orderDto, Order.class);
    }
}
