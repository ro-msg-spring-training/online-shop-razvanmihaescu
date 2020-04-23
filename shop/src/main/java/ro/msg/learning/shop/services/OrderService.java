package ro.msg.learning.shop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.msg.learning.shop.configuration.DeliveryStrategyConfiguration;
import ro.msg.learning.shop.dtos.OrderDetailDto;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.dtos.StockDto;
import ro.msg.learning.shop.entities.*;
import ro.msg.learning.shop.exceptions.OrderCouldNotBeCreatedException;
import ro.msg.learning.shop.exceptions.OrderNotFoundException;
import ro.msg.learning.shop.mappers.IOrderDetailMapper;
import ro.msg.learning.shop.mappers.IOrderMapper;
import ro.msg.learning.shop.repositories.IOrderRepository;
import ro.msg.learning.shop.repositories.IStockRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final IOrderRepository orderRepository;
    private final DeliveryStrategyConfiguration deliveryStrategy;
    private final IStockRepository stockRepository;
    private final MailService mailService;
    private final UserService userService;

    public Order createOrder(OrderDto orderDto) {
        Order persistedOrder;
        User user = userService.getUserByUsername(orderDto.getUsername());

        Address address = Address.builder()
                .country(orderDto.getDeliveryLocation().getCountry())
                .city(orderDto.getDeliveryLocation().getCity())
                .street(orderDto.getDeliveryLocation().getStreet())
                .county(orderDto.getDeliveryLocation().getCounty())
                .build();


        Location location = Location.builder()
                .name("Adresa lui " + user.getUsername())
                .address(address)
                .build();

        try {
            Order newOrder = IOrderMapper.INSTANCE.orderDtoToOrder(orderDto);
            List<OrderDetailDto> orderDetailDtos = orderDto.getOrderDetailDtos();
            List<OrderDetail> orderDetails = orderDetailDtos.stream().map(IOrderDetailMapper.INSTANCE::orderDetailDtoToOrderDetail).collect(Collectors.toList());

            orderDetails.forEach(orderDetail -> orderDetail.setOrder(newOrder));
            List<StockDto> stockToSubtract = deliveryStrategy.getDeliveryStrategy().doAlgorithm(orderDetails);
            newOrder.setOrderDetail(orderDetails);
            newOrder.setDeliveryLocation(location);
            newOrder.setCustomer(user); // problem because of doubling the same user
            newOrder.setCreatedAt(LocalDateTime.now());
            persistedOrder = orderRepository.save(newOrder);

            stockToSubtract.forEach(a -> {
                Stock newStock = stockRepository.findByLocation_NameAndProduct_Name(a.getLocationDto().getName(), a.getProductDto().getName());
                Integer newQuantity = newStock.getQuantity() - a.getQuantity();
                newStock.setQuantity(newQuantity);
                stockRepository.save(newStock);
            });

            mailService.sendOrderSuccessfullyCreatedEmail(persistedOrder.getCustomer().getEmail(), persistedOrder.getId());
        } catch (RuntimeException e) {
            throw new OrderCouldNotBeCreatedException(e.getMessage());
        }

        return persistedOrder;
    }

    public Order getOrderById(Integer id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        } else {
            throw new OrderNotFoundException(id);
        }
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }

    public Order updateOrder(Integer orderId, OrderDto orderDto) {
        Order newOrder = IOrderMapper.INSTANCE.orderDtoToOrder(orderDto);
        newOrder.setId(orderId);
        return orderRepository.save(newOrder);
    }

    public void deleteOrderById(Integer orderId) {
        orderRepository.deleteById(orderId);
    }
}
