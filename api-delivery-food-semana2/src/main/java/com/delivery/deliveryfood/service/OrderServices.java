package com.delivery.deliveryfood.service;

import com.delivery.deliveryfood.domain.Order;
import com.delivery.deliveryfood.request.OrderRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServices {
    private List<Order> orders = new ArrayList<>();

    public Order createOrder(OrderRequest orderRequest) {
        if (orderRequest.getItems().isEmpty()) {
            throw new IllegalArgumentException("La lista de items no puede estar vacía");
        }

        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerName(orderRequest.getCustomerName());
        order.setCustomerEmail(orderRequest.getCustomerEmail());
        order.setStatus("En proceso");
        order.setCreationTime(LocalDateTime.now());
        order.setEstimatedDeliveryTime(LocalDateTime.now().plusMinutes(30));
        order.setItems(orderRequest.getItems());
        orders.add(order);
        return order;
    }

    public String getOrderStatus(String orderId) {
        Order order = findOrderById(orderId);
        if (order != null) {
            return order.getStatus();
        } else {
            throw new OrderNotFoundException("Pedido no encontrado");
        }
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order findOrderById(String orderId) {
        return orders.stream().filter(order -> orderId.equals(order.getId())).findFirst().orElse(null);
    }

    public static class OrderNotFoundException extends RuntimeException {
        public OrderNotFoundException(String message) {
            super(message);
        }
    }
}
