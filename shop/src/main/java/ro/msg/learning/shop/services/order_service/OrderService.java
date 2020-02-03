package ro.msg.learning.shop.services.order_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.strategies.IDeliveryStrategy;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.OrderCouldNotBeCreatedException;
import ro.msg.learning.shop.exceptions.OrderNotFoundException;
import ro.msg.learning.shop.mappers.OrderMapper;
import ro.msg.learning.shop.repositories.IOrderRepository;
import ro.msg.learning.shop.repositories.IStockRepository;
import ro.msg.learning.shop.services.location_service.ILocationService;
import ro.msg.learning.shop.services.orderDetail_service.IOrderDetailService;
import ro.msg.learning.shop.services.product_service.ProductService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    IOrderRepository orderRepository;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IDeliveryStrategy deliveryStrategy;

    @Autowired
    IStockRepository stockRepository;

    @Autowired
    ILocationService locationService;

    @Autowired
    ProductService productService;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        Order persistedOrder;

        Address address = Address.builder()
                .country(orderDto.getDeliveryLocation().getCountry())
                .city(orderDto.getDeliveryLocation().getCity())
                .street(orderDto.getDeliveryLocation().getStreet())
                .county(orderDto.getDeliveryLocation().getCounty())
                .build();


        Location location = Location.builder()
                .name("Adresa lui USERNAME")
                .address(address)
                .build();

        try {
            Order newOrder = this.convertToEntity(orderDto);
            List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetailDtos();
            List<OrderDetail> orderDetails = orderDetailDtos.stream().map(a -> orderDetailService.convertToEntity(a)).collect(Collectors.toList());

            orderDetails.forEach(orderDetail -> orderDetail.setOrder(newOrder));
            List<StockDto> stockToSubtract = deliveryStrategy.doAlgorithm(orderDetails);
            newOrder.setOrderDetail(orderDetails);
            newOrder.setDeliveryLocation(location);
            newOrder.setCreatedAt(LocalDateTime.now());
            persistedOrder = orderRepository.save(newOrder);

            stockToSubtract.forEach(a -> {
                Stock newStock = stockRepository.findByLocation_NameAndProduct_Name(a.getLocationDto().getName(), a.getProductDto().getName());
                Integer newQuantity = newStock.getQuantity() - a.getQuantity();
                newStock.setQuantity(newQuantity);
                stockRepository.save(newStock);
            });
        } catch (RuntimeException e) {
            throw new OrderCouldNotBeCreatedException(e.getMessage());
        }

        return this.convertToDto(persistedOrder);
    }

    @Override
    public OrderDto getOrderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return this.convertToDto(orderOptional.get());
        } else {
            throw new OrderNotFoundException(id);
        }
    }

    @Override
    public OrderDto convertToDto(Order order) {
        return orderMapper.convertToDto(order);
    }

    @Override
    public Order convertToEntity(OrderDto orderDto) {
        return orderMapper.convertToEntity(orderDto);
    }

    @Override
    public List<OrderDto> getOrders() {
        return orderRepository.findAll().stream().map(order -> orderMapper.convertToDto(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto updateOrder(Integer orderId, OrderDto orderDto) {
        Order order = convertToEntity(orderDto);
        order.setId(orderId);
        Order persistedOrder = orderRepository.save(order);
        return convertToDto(persistedOrder);
    }

    @Override
    public void deleteOrderById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
