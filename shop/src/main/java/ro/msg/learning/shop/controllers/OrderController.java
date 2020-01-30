package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.services.order_service.IOrderService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Orders")
public class OrderController {


    @Autowired
    private IOrderService orderService;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }

    @GetMapping("/{orderId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }

    @PutMapping("/{orderId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OrderDto updateOrder(@PathVariable Integer orderId, OrderDto orderDto) {
        return orderService.updateOrder(orderId, orderDto);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteOrderById(@PathVariable Integer orderId) {
        orderService.deleteOrderById(orderId);
    }
}
