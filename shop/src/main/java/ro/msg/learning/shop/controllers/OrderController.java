package ro.msg.learning.shop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.mappers.IOrderMapper;
import ro.msg.learning.shop.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return IOrderMapper.INSTANCE.orderToOrderDto(orderService.createOrder(orderDto));
    }

    @GetMapping("/{orderId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Integer orderId) {
        return IOrderMapper.INSTANCE.orderToOrderDto(orderService.getOrderById(orderId));
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getOrders() {
        return orderService.getOrders().stream().map(IOrderMapper.INSTANCE::orderToOrderDto).collect(Collectors.toList());
    }

    @PutMapping("/{orderId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@PathVariable Integer orderId, OrderDto orderDto) {
        return IOrderMapper.INSTANCE.orderToOrderDto(orderService.updateOrder(orderId, orderDto));
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable Integer orderId) {
        orderService.deleteOrderById(orderId);
    }
}
