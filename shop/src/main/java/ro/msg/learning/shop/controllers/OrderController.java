package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ro.msg.learning.shop.dtos.OrderDto;
import ro.msg.learning.shop.services.order_service.IOrderService;

@RestController
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

    @GetMapping
    @RequestMapping("/{orderId}")
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrderById(@PathVariable Integer orderId)
    {
        return orderService.getOrderById(orderId);
    }
}
