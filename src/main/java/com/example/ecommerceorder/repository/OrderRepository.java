package com.example.ecommerceorder.repository;


import com.example.ecommerceorder.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}


