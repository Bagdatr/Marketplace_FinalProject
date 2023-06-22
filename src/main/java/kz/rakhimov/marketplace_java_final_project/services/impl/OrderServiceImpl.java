package kz.rakhimov.marketplace_java_final_project.services.impl;

import kz.rakhimov.marketplace_java_final_project.entities.Item;
import kz.rakhimov.marketplace_java_final_project.entities.Order;
import kz.rakhimov.marketplace_java_final_project.repositories.ItemRepository;
import kz.rakhimov.marketplace_java_final_project.repositories.OrderRepository;
import kz.rakhimov.marketplace_java_final_project.services.MainUserService;
import kz.rakhimov.marketplace_java_final_project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderRepository orderRepository;
    @Autowired
    public ItemRepository itemRepository;

    @Autowired
    public MainUserService userService;

    @Override
    public Order addItemToOrder(Long itemId, String userEmail) {
        Order order = new Order();
        Item item = itemRepository.findAllById(itemId);
        order.setItem(item);
        order.setEmail(userEmail);
        return orderRepository.save(order);
    }

    @Override
    public List<Item> getAllItemsInOrder(String userEmail) {
        List<Order> orderList = orderRepository.findAllByEmail(userEmail);
        List<Item> itemsList = new ArrayList<>();
        for (int i = 0; i < orderList.size(); i++) {
            itemsList.add(orderList.get(i).getItem());
        }
        return itemsList;
    }

    @Override
    public void deleteItemFromOrder(Long itemId) {
        orderRepository.deleteByItemId(itemId);
    }

}