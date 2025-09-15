package com.example.ecommerceorder.service;

import com.example.ecommerceorder.entity.Order;
import com.example.ecommerceorder.entity.Product;
import com.example.ecommerceorder.exception.ProductNotAvailableException;
import com.example.ecommerceorder.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final RestTemplate restTemplate;
    private final OrderRepository orderRepository;

    public Order placeOrder(Long productId) {
        String url = "https://fakestoreapi.com/products/" + productId;

        ResponseEntity<Product> response = restTemplate.getForEntity(url, Product.class);
        Product product = response.getBody();

        if (product != null && product.getRating().getCount() > 0) {
            Order order = new Order();
            order.setProductId(product.getId());
            order.setProductName(product.getTitle());
            order.setPrice(product.getPrice());
            order.setCreatedAt(LocalDateTime.now());

            return orderRepository.save(order);
        } else {
            throw new ProductNotAvailableException("Product not available");
        }
    }
}


