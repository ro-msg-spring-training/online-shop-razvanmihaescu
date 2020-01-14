package ro.msg.learning.shop.services.order_service;

import org.springframework.beans.factory.annotation.Autowired;
import ro.msg.learning.shop.entities.Orders;
import ro.msg.learning.shop.exceptions.MyException;
import ro.msg.learning.shop.repositories.IOrdersRepository;

import java.util.Optional;

public class OrderService implements IOrderService{

    @Autowired
    IOrdersRepository ordersRepository;

    @Override
    public Orders createOrder(Orders order) {
        return null;
    }

    @Override
    public Orders getOrderById(Integer id) {
        Optional<Orders> ordersOptional = ordersRepository.findById(id);
        if (ordersOptional.isPresent()) {
            return ordersOptional.get();
        } else {
            throw new MyException();
        }
    }
}
