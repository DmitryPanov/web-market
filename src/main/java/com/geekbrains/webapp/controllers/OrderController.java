package com.geekbrains.webapp.controllers;

import com.geekbrains.webapp.dtos.OrderDetailsDto;
import com.geekbrains.webapp.dtos.OrderDto;
import com.geekbrains.webapp.exceptions.ResourceNotFoundException;
import com.geekbrains.webapp.model.User;
import com.geekbrains.webapp.services.CartService;
import com.geekbrains.webapp.services.OrderService;
import com.geekbrains.webapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderDetailsDto orderDetailsDto, Principal principal) {
        orderService.createOrder(principal, orderDetailsDto);
    }

    @GetMapping
    public List<OrderDto> getOrdersForCurrentUser(Principal principal) {
        return orderService.findAllByUsername(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
    }
}
