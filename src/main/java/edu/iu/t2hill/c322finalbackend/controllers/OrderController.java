package edu.iu.t2hill.c322finalbackend.controllers;

import edu.iu.t2hill.c322finalbackend.model.Flower;
import edu.iu.t2hill.c322finalbackend.model.Receipt;
import edu.iu.t2hill.c322finalbackend.repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {
    private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public int addOrder(@RequestBody Receipt receipt) {
        try {
            return orderRepository.addOrder(receipt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receipt> find(@PathVariable int id) {
        try {
            Receipt receipt = orderRepository.find(id);
            if(receipt != null) {
                return ResponseEntity
                        .status(HttpStatus.FOUND)
                        .body(receipt);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(null);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
