package kz.rakhimov.marketplace_java_final_project.controllers;

import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.Order;
import kz.rakhimov.marketplace_java_final_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/order")
public class OrderController {
    @Autowired
    public OrderService orderService;

    @PostMapping
    public Order addItemToOrder(@RequestBody Order order){
        return orderService.addItemToOrder(order.getItem().getId(), order.getEmail());
    }

    @GetMapping(value="/{email}")
    public List<Item> getAllItemsInOrder(@PathVariable ("email") String userEmail){
        return orderService.getAllItemsInOrder(userEmail);
    }

    @DeleteMapping(value="/{id}")
    public void deleteItemFromOrder(@PathVariable("id") Long itemId){
        orderService.deleteItemFromOrder(itemId);
    }

}
