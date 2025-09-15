package com.example.ecommerceorder.controller;

import com.example.ecommerceorder.entity.Order;
import com.example.ecommerceorder.exception.ProductNotAvailableException;
import com.example.ecommerceorder.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @Operation(summary = "Place an order for a product by ID")
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestParam Long productId) {
        try {
            Order order = orderService.placeOrder(productId);
            return ResponseEntity.ok(order);
        } catch (ProductNotAvailableException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Internal Error: " + ex.getMessage());
        }
    }
}


