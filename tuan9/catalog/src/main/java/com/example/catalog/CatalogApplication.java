package com.example.catalog;

import com.example.catalog.entity.Order;
import com.example.catalog.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/order")
public class CatalogApplication {

    @Autowired
    private OrderRepository orderRepository;

    public void initTableOrders() {
        orderRepository.saveAll(Stream.of(
                new Order("Laptop", "electronics", "gray", 50000),
                new Order("Shorts", "Clothes", "Black", 150000),
                new Order("mobile", "electronics", "white", 20000),
                new Order("Pant", "Clothes", "Black", 200000)
        ).collect(Collectors.toList()));
    }

    @RequestMapping
    public List<Order> getOrder() {
        return orderRepository.findAll();
    }

    @GetMapping("/{category}")
    public List<Order> getOrderByCategory(@PathVariable String category) {
        return orderRepository.findByCategory(category);
    }

    public static void main(String[] args) {
        SpringApplication.run(CatalogApplication.class, args);
    }

}
